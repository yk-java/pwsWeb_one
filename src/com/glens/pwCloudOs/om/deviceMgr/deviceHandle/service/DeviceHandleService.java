/**
 * @Title: DeviceHandleService.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.deviceHandle.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-12-5 上午11:44:42
 * @version V1.0
 */

package com.glens.pwCloudOs.om.deviceMgr.deviceHandle.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.core.utils.ReflectionUtils;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.om.deviceMgr.deviceHandle.dao.DeviceMongoHandleDao;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceVo;
import com.map.coords.algorithm.Converter;
import com.map.coords.algorithm.Point;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

public class DeviceHandleService extends EAPAbstractService {

	private static final String DEVICE_FILE_URL = "e:/device_code.txt";

	private DeviceMongoHandleDao deviceHandleMongoDao;

	private FinalHttp httpClient;

	private static Map<String, String> map = new HashMap<String, String>();
	static {
		map.put("1", "服务器内部错误");
		map.put("2", "请求参数非法");
		map.put("3", "权限校验失败");
		map.put("4", "配额校验失败");
		map.put("5", "ak不存在或者非法");
		map.put("101", "服务禁用");
		map.put("102", "不通过白名单或者安全码不对");
	}

	public int updateDeviceLoc(Map<String, Object> params) {

		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("deviceObjCode", params.get("deviceObjCode"));
		valueMap.put("x", params.get("x"));
		valueMap.put("y", params.get("y"));
		valueMap.put("rx", params.get("rx"));
		valueMap.put("ry", params.get("ry"));
		String valueJson = JSONObject.toJSONString(valueMap);
		int icount = this.deviceHandleMongoDao.updateDeviceLoc(valueJson);

		return icount;
	}

	public String batchUpdateFieldMctDeviceLoc(Map<String, Object> params,
			Map<String, String> resultMap) {

		String result = "成功";
		String taskCode = (String) params.get("taskCode");

		Criteria criteria = new Criteria();
		criteria.and("companyCode").is(params.get("companyCode"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {
			criteria.and("mctCode").is(params.get("mctCode"));
		}
		criteria.and("x").in("", null, 0);

		List<DeviceVo> mctDeviceList = this.deviceHandleMongoDao
				.getMctDeviceList(criteria);
		// 字段名称
		String fieldEname = (String) params.get("fieldEname");

		int successCount = 0;
		int failCount = 0;

		for (DeviceVo dv : mctDeviceList) {

			String val = (String) ReflectionUtils.getFieldValue(dv, fieldEname);
			if (StringUtils.isBlank(val))
				continue;

			val = val.replace("#", "号");
			val = val.replaceAll(" +", "");
			String geocorderUrl = getGeocoderUrl(val);

			Object geoResult = httpClient.getSync(geocorderUrl, null);

			if (geoResult != null && !"".equals(geoResult)) {

				JSONObject geoJson = JSONObject.parseObject(geoResult
						.toString());

				System.out.println(geoJson);

				int status = geoJson.getIntValue("status");
				if (status == 0) {
					JSONObject geoResultObj = geoJson.getJSONObject("result");
					if (geoResultObj.get("location") != null) {
						successCount++;

						JSONObject geoLocObj = geoResultObj
								.getJSONObject("location");
						Map<String, Object> mctDevice = new HashMap<String, Object>();
						mctDevice.put("x", geoLocObj.getDouble("lng"));
						mctDevice.put("y", geoLocObj.getDouble("lat"));
						Point p = Converter.BD09ToWGS84(
								geoLocObj.getDouble("lng"),
								geoLocObj.getDouble("lat"));
						mctDevice.put("rx", p.getLongitude());
						mctDevice.put("ry", p.getLatitude());
						mctDevice.put(fieldEname, val);
						updateDeviceLoc(mctDevice, fieldEname);
						resultMap.put(taskCode, "总数据:" + mctDeviceList.size()
								+ ",已匹配数据:" + successCount + "," + val
								+ "坐标匹配成功");
					}
				} else {
					failCount++;
					resultMap.put(taskCode, "总数据:" + mctDeviceList.size()
							+ ",已匹配数据:" + successCount + "," + val
							+ "坐标匹配失败,错误代码：" + status);
				}

			}
		}

		result = "总数据:" + mctDeviceList.size() + ",匹配成功数据:" + successCount
				+ ",匹配失败数据：" + failCount;

		return result;
	}

	private int updateDeviceLoc(Map params, String fieldEname) {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put(fieldEname, params.get(fieldEname));
		valueMap.put("x", params.get("x"));
		valueMap.put("y", params.get("y"));
		valueMap.put("rx", params.get("rx"));
		valueMap.put("ry", params.get("ry"));
		String valueJson = JSONObject.toJSONString(valueMap);
		int icount = this.deviceHandleMongoDao.updateDeviceLoc(valueJson,
				fieldEname);

		return icount;
	}

	public int batchUpdateDeviceLoc(Map<String, Object> params) {
		DBObject queryParams = new BasicDBObject();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {

			queryParams.put("mctCode", params.get("mctCode"));
		}

		List<Map<String, Object>> mctDeviceList = this.deviceHandleMongoDao
				.getMctDeviceList(queryParams);
		int icount = 0;
		if (mctDeviceList != null && mctDeviceList.size() > 0) {

			Map<String, Map<String, Double>> xyTool = new HashMap<String, Map<String, Double>>();
			Integer zero = 0;
			for (Map mctDevice : mctDeviceList) {

				if (mctDevice.get("deviceObjName") != null
						&& !"".equals(mctDevice.get("deviceObjName"))) {

					if (mctDevice.get("x") == null
							|| "".equals(mctDevice.get("x"))
							|| mctDevice.get("x") == zero) {

						if (xyTool.get("deviceObjName") != null) {

							Map<String, Double> xy = xyTool
									.get("deviceObjName");
							mctDevice.put("x", xy.get("x"));
							mctDevice.put("y", xy.get("y"));

							icount += updateDeviceLoc(mctDevice);
						} else {

							String geocorderUrl = getGeocoderUrl(mctDevice
									.get("deviceObjName").toString()
									.replace("#", "号"));
							Object geoResult = httpClient.getSync(geocorderUrl,
									null);
							if (geoResult != null && !"".equals(geoResult)) {

								JSONObject geoJson = JSONObject
										.parseObject(geoResult.toString());
								int status = geoJson.getIntValue("status");
								if (status == 0) {

									JSONObject geoResultObj = geoJson
											.getJSONObject("result");
									if (geoResultObj.get("location") != null) {

										JSONObject geoLocObj = geoResultObj
												.getJSONObject("location");
										mctDevice.put("x",
												geoLocObj.getDouble("lng"));
										mctDevice.put("y",
												geoLocObj.getDouble("lat"));

										Map<String, Double> xy = new HashMap<String, Double>();
										xy.put("x", geoLocObj.getDouble("lng"));
										xy.put("y", geoLocObj.getDouble("lat"));
										xyTool.put(
												mctDevice.get("deviceObjName")
														.toString(), xy);

										icount += updateDeviceLoc(mctDevice);
									}
								}
							}
						}
					}

				}
			}
		}

		return icount;
	}

	/**
	 * 处理数据用，把PM_MCT_LIST中前期把真实坐标的设备产生百度坐标存放 在原来的x、y字段，并把真实坐标存放在rx、ry字段
	 * 
	 * @param @return
	 * @return 一个Map类型记录
	 * @throws
	 * @author:
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */

	public int batchConvertWGS84ToBD09(Map<String, Object> params) {

		int icount = 0;
		// 搜索条件
		DBObject query = new BasicDBObject();
		query.put("companyCode", params.get("companyCode"));
		query.put("deviceTypeCode", params.get("deviceTypeCode"));
		Pattern pattern = Pattern.compile("^.*B.*$", Pattern.CASE_INSENSITIVE);
		BasicDBList deviceObjCodeParam = new BasicDBList();
		deviceObjCodeParam.add(new BasicDBObject("deviceObjCode", pattern));

		BasicDBList xyQuryList = new BasicDBList();
		xyQuryList.add(new BasicDBObject("rx", 0));
		xyQuryList.add(new BasicDBObject("rx", null));
		xyQuryList.add(new BasicDBObject("ry", 0));
		xyQuryList.add(new BasicDBObject("ry", null));
		xyQuryList.add(new BasicDBObject("rx", ""));
		xyQuryList.add(new BasicDBObject("ry", ""));
		query.put("$or", xyQuryList);
		BasicDBList deviceCodeList = getDevicesFromFile();
		if (deviceCodeList != null && deviceCodeList.size() > 0) {

			deviceObjCodeParam.add(new BasicDBObject("deviceObjCode",
					new BasicDBObject("$nin", deviceCodeList)));
		}
		query.put("$and", deviceObjCodeParam);

		// 搜索

		List<Map<String, Object>> deviceList = this.deviceHandleMongoDao
				.getMctDeviceList(query);
		for (Map<String, Object> deviceItem : deviceList) {

			if (deviceItem.get("x") != null && !"".equals(deviceItem.get("x"))) {

				double x = Double.parseDouble(deviceItem.get("x").toString());
				if (x > 0) {

					double y = Double.parseDouble(deviceItem.get("y")
							.toString());
					Point p = Converter.WGS84ToBD09(x, y);
					if (p != null) {

						deviceItem.put("rx", x);
						deviceItem.put("ry", y);
						deviceItem.put("x", p.getLongitude());
						deviceItem.put("y", p.getLatitude());

						icount += this.deviceHandleMongoDao
								.saveDevice(deviceItem);
					}
				}
			}
		}

		return icount;
	}

	/**
	 * 处理数据用，把PM_MCT_LIST中没有真实坐标的设备， 把其中的显示坐标转化成真实坐标
	 * 
	 * @param @return
	 * @return 一个Map类型记录
	 * @throws
	 * @author:
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */

	public int batchConvertBD09ToWGS84(Map<String, Object> params) {

		int icount = 0;
		// 搜索条件
		DBObject query = new BasicDBObject();
		query.put("companyCode", params.get("companyCode"));
		query.put("deviceTypeCode", params.get("deviceTypeCode"));
		BasicDBList deviceCodeList = getDevicesFromFile();
		if (deviceCodeList != null && deviceCodeList.size() > 0) {

			query.put("deviceObjCode", new BasicDBObject("$in", deviceCodeList));
		}

		// 搜索

		List<Map<String, Object>> deviceList = this.deviceHandleMongoDao
				.getMctDeviceList(query);
		for (Map<String, Object> deviceItem : deviceList) {

			if (deviceItem.get("x") != null && !"".equals(deviceItem.get("x"))) {

				double x = Double.parseDouble(deviceItem.get("x").toString());
				if (x > 0) {

					double y = Double.parseDouble(deviceItem.get("y")
							.toString());
					Point p = Converter.BD09ToWGS84(x, y);
					if (p != null) {

						deviceItem.put("rx", p.getLongitude());
						deviceItem.put("ry", p.getLatitude());

						icount += this.deviceHandleMongoDao
								.saveDevice(deviceItem);
					}
				}
			}
		}

		return icount;
	}

	private String getGeocoderUrl(String address) {

		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext()
				.getBean("pwcloudosConfig");
		String geocoderUrl = config.getGeocoderUrl();
		geocoderUrl = String.format(geocoderUrl, address);

		return geocoderUrl;
	}

	private BasicDBList getDevicesFromFile() {

		BasicDBList list = new BasicDBList();
		File file = new File(DEVICE_FILE_URL);
		if (file.isFile() && file.exists()) {

			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {

					if (!"".equals(lineTxt)) {

						list.add(lineTxt);
					}
				}
				read.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
		}

		return list;
	}

	public DeviceMongoHandleDao getDeviceHandleMongoDao() {
		return deviceHandleMongoDao;
	}

	public void setDeviceHandleMongoDao(
			DeviceMongoHandleDao deviceHandleMongoDao) {
		this.deviceHandleMongoDao = deviceHandleMongoDao;
	}

	public FinalHttp getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(FinalHttp httpClient) {
		this.httpClient = httpClient;
	}

}
