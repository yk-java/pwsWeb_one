/**
 * @Title: DeviceService.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.device.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-22 下午3:58:33
 * @version V1.0
 */

package com.glens.pwCloudOs.om.deviceMgr.device.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.afinal.http.entityhandler.BinaryEntityHandler;
import com.glens.eap.platform.afinal.http.entityhandler.StringEntityHandler;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.om.deviceMgr.device.dao.DeviceDao;
import com.glens.pwCloudOs.om.deviceMgr.device.dao.MctDeviceMongoDao;
import com.glens.pwCloudOs.om.deviceMgr.device.dao.PmDeviceOpLogMongoDao;
import com.glens.pwCloudOs.om.deviceMgr.device.utils.MathUtil;
import com.glens.pwCloudOs.om.deviceMgr.device.vo.GeoExtend;
import com.glens.pwCloudOs.om.deviceMgr.device.vo.GeoPoint;
import com.glens.pwCloudOs.om.deviceMgr.device.vo.GeoPolygon;
import com.map.coords.algorithm.Converter;
import com.map.coords.algorithm.Point;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

public class DeviceService extends EAPAbstractService {

	private MctDeviceMongoDao mctDeviceMongoDao;

	private FinalHttp httpClient;

	private PmDeviceOpLogMongoDao deviceOpLogMongoDao;

	public Map<String, List<Map<String, Object>>> getMctDeviceListShowAttr(
			Map<String, Object> params) {

		DeviceDao deviceDao = (DeviceDao) getDao();
		List<Map<String, Object>> attrList = deviceDao.getMctDeviceAttr(params);
		Map<String, List<Map<String, Object>>> resultMap = new HashMap<String, List<Map<String, Object>>>();
		if (attrList != null && attrList.size() > 0) {

			List<Map<String, Object>> searchAttrList = getSearchAttr(attrList);
			List<Map<String, Object>> listShowAttrList = getListShowAttr(attrList);

			resultMap.put("searchFields", searchAttrList);
			resultMap.put("listFields", listShowAttrList);
		}

		return resultMap;
	}

	// 电表运维
	public Map<String, List<Map<String, Object>>> getMctDeviceListShowAttr1(
			Map<String, Object> params) {

		DeviceDao deviceDao = (DeviceDao) getDao();
		List<Map<String, Object>> attrList = deviceDao
				.getMctDeviceAttr1(params);
		Map<String, List<Map<String, Object>>> resultMap = new HashMap<String, List<Map<String, Object>>>();
		if (attrList != null && attrList.size() > 0) {

			List<Map<String, Object>> searchAttrList = getSearchAttr(attrList);
			List<Map<String, Object>> listShowAttrList = getListShowAttr(searchAttrList);

			resultMap.put("searchFields", searchAttrList);
			resultMap.put("listFields", listShowAttrList);
		}

		return resultMap;
	}

	public List<Map<String, Object>> getMctDeviceAttr(Map<String, Object> params) {

		DeviceDao deviceDao = (DeviceDao) getDao();
		List<Map<String, Object>> attrList = deviceDao.getMctDeviceAttr(params);
		if (attrList != null && attrList.size() > 0) {

			for (Map<String, Object> attrItem : attrList) {

				int dtype = Integer.parseInt(attrItem.get("dtype").toString());
				if (dtype == 4) {

					// 先处理本地数据源
					int enumSource = Integer.parseInt(attrItem
							.get("enumSource").toString());
					if (enumSource == 1) {

						String enumValue = (String) attrItem.get("enumValue");
						attrItem.put("enumValue", enumValue.split(","));
					}
				}
			}
		}

		return attrList;
	}

	public List<Map<String, Object>> getMobileMctDeviceAttr(
			Map<String, Object> params) {

		DeviceDao deviceDao = (DeviceDao) getDao();
		List<Map<String, Object>> attrList = deviceDao
				.getMobileMctDeviceAttr(params);
		if (attrList != null && attrList.size() > 0) {

			for (Map<String, Object> attrItem : attrList) {

				int dtype = Integer.parseInt(attrItem.get("dtype").toString());
				if (dtype == 4) {

					// 先处理本地数据源
					int enumSource = Integer.parseInt(attrItem
							.get("enumSource").toString());
					if (enumSource == 1) {

						String enumValue = (String) attrItem.get("enumValue");
						attrItem.put("enumValue", enumValue.split(","));
					}
				}
			}
		}

		return attrList;
	}

	public List<Map<String, Object>> getMctDeviceAttr1(
			Map<String, Object> params) {

		DeviceDao deviceDao = (DeviceDao) getDao();
		List<Map<String, Object>> attrList = deviceDao
				.getMctDeviceAttr1(params);
		return attrList;
	}

	// 获取压缩文件
	public Object getZipImgs(String codes, String names) {
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext()
				.getBean("pwcloudosConfig");

		AjaxParams ajaxParams = new AjaxParams();
		ajaxParams.put("fileCodes", codes);
		ajaxParams.put("fileNames", names);
		System.out.println(config.getZipFiles());
		BinaryEntityHandler handler = new BinaryEntityHandler();
		httpClient.setEntityHandler(handler);
		Object content = httpClient.postSync(config.getPrefix()+ config.getZipFiles(), ajaxParams);
		httpClient.setEntityHandler(new StringEntityHandler());

		return content;
	}

	public PageBean getMctDevicePage(Map<String, Object> params) {

		int currentPage = (Integer) params.get("currentPage");
		int perpage = (Integer) params.get("perpage");
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {

			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null
				&& !"".equals(params.get("deviceTypeCode"))) {

			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		if (params.get("startTime") != null
				&& !"".equals(params.get("startTime"))) {
			queryParams.put("startTime", params.get("startTime"));
		}
		if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
			queryParams.put("endTime", params.get("endTime"));
		}
		if (params.get("qcStartTime") != null
				&& !"".equals(params.get("qcStartTime"))) {
			queryParams.put("qcStartTime", params.get("qcStartTime"));
		}
		if (params.get("qcEndTime") != null
				&& !"".equals(params.get("qcEndTime"))) {
			queryParams.put("qcEndTime", params.get("qcEndTime"));
		}
		if (params.get("QUALITY_AUDIT") != null
				&& !"".equals(params.get("QUALITY_AUDIT"))) {
			queryParams.put("QUALITY_AUDIT", params.get("QUALITY_AUDIT"));
		}
		if (params.get("hasPic") != null && !"".equals(params.get("hasPic"))) {
			queryParams.put("hasPic", params.get("hasPic"));
		}
		if (params.get("hasPosition") != null
				&& !"".equals(params.get("hasPosition"))) {
			queryParams.put("hasPosition", params.get("hasPosition"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null
				&& !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				if (attrMap.get(key) == null || "".equals(attrMap.get(key))) {
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}

		String paramJson = JSONObject.toJSONString(queryParams);
		PageBean page = this.mctDeviceMongoDao.getMctDevicePage(paramJson,
				searchName, currentPage, perpage);

		return page;
	}

	// 数据质量统计
	public List<Map<String, Object>> dataQuality(Map<String, Object> params) {

		int currentPage = (Integer) params.get("currentPage");
		int perpage = (Integer) params.get("perpage");
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {

			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null
				&& !"".equals(params.get("deviceTypeCode"))) {

			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		if (params.get("startTime") != null
				&& !"".equals(params.get("startTime"))) {
			queryParams.put("startTime", params.get("startTime"));
		}
		if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
			queryParams.put("endTime", params.get("endTime"));
		}
		if (params.get("QUALITY_AUDIT") != null
				&& !"".equals(params.get("QUALITY_AUDIT"))) {
			queryParams.put("QUALITY_AUDIT", params.get("QUALITY_AUDIT"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null
				&& !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				if (attrMap.get(key) == null || "".equals(attrMap.get(key))) {
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}

		String paramJson = JSONObject.toJSONString(queryParams);
		List<Map<String, Object>> list = this.mctDeviceMongoDao.dataQuality(
				paramJson, searchName, currentPage, perpage);

		return list;
	}

	// 手机端列表
	public PageBean getPhoneMctDevicePage(Map<String, Object> params) {

		int currentPage = (Integer) params.get("currentPage");
		int perpage = (Integer) params.get("perpage");
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {

			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null
				&& !"".equals(params.get("deviceTypeCode"))) {

			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		if (params.get("startTime") != null
				&& !"".equals(params.get("startTime"))) {
			queryParams.put("startTime", params.get("startTime"));
		}
		if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
			queryParams.put("endTime", params.get("endTime"));
		}
		if (params.get("QUALITY_AUDIT") != null
				&& !"".equals(params.get("QUALITY_AUDIT"))) {
			queryParams.put("QUALITY_AUDIT", params.get("QUALITY_AUDIT"));
		}
		if (params.get("hasPic") != null && !"".equals(params.get("hasPic"))) {
			queryParams.put("hasPic", params.get("hasPic"));
		}
		if (params.get("hasPosition") != null
				&& !"".equals(params.get("hasPosition"))) {
			queryParams.put("hasPosition", params.get("hasPosition"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null
				&& !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				if (attrMap.get(key) == null || "".equals(attrMap.get(key))) {
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}

		String paramJson = JSONObject.toJSONString(queryParams);
		PageBean page = this.mctDeviceMongoDao.getPhoneMctDevicePage(paramJson,
				searchName, currentPage, perpage);

		return page;
	}

	// 导出图片
	public List exportImgs(Map<String, Object> params) {

		int currentPage = (Integer) params.get("currentPage");
		int perpage = (Integer) params.get("perpage");
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {

			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null
				&& !"".equals(params.get("deviceTypeCode"))) {

			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		if (params.get("startTime") != null
				&& !"".equals(params.get("startTime"))) {
			queryParams.put("startTime", params.get("startTime"));
		}
		if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
			queryParams.put("endTime", params.get("endTime"));
		}
		if (params.get("qcStartTime") != null
				&& !"".equals(params.get("qcStartTime"))) {
			queryParams.put("qcStartTime", params.get("qcStartTime"));
		}
		if (params.get("qcEndTime") != null
				&& !"".equals(params.get("qcEndTime"))) {
			queryParams.put("qcEndTime", params.get("qcEndTime"));
		}
		if (params.get("QUALITY_AUDIT") != null
				&& !"".equals(params.get("QUALITY_AUDIT"))) {
			queryParams.put("QUALITY_AUDIT", params.get("QUALITY_AUDIT"));
		}
		if (params.get("hasPic") != null && !"".equals(params.get("hasPic"))) {
			queryParams.put("hasPic", params.get("hasPic"));
		}
		if (params.get("hasPosition") != null
				&& !"".equals(params.get("hasPosition"))) {
			queryParams.put("hasPosition", params.get("hasPosition"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null
				&& !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				if (attrMap.get(key) == null || "".equals(attrMap.get(key))) {
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}

		String paramJson = JSONObject.toJSONString(queryParams);
		List list = this.mctDeviceMongoDao.exportImgs(paramJson, searchName,
				currentPage, perpage);

		return list;
	}

	public List<Map> getMctDeviceList(Map<String, Object> params) {

		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {

			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null
				&& !"".equals(params.get("deviceTypeCode"))) {

			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		if (params.get("startTime") != null
				&& !"".equals(params.get("startTime"))) {
			queryParams.put("startTime", params.get("startTime"));
		}
		if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
			queryParams.put("endTime", params.get("endTime"));
		}
		if (params.get("qcStartTime") != null
				&& !"".equals(params.get("qcStartTime"))) {
			queryParams.put("qcStartTime", params.get("qcStartTime"));
		}
		if (params.get("qcEndTime") != null
				&& !"".equals(params.get("qcEndTime"))) {
			queryParams.put("qcEndTime", params.get("qcEndTime"));
		}
		if (params.get("QUALITY_AUDIT") != null
				&& !"".equals(params.get("QUALITY_AUDIT"))) {
			queryParams.put("QUALITY_AUDIT", params.get("QUALITY_AUDIT"));
		}
		if (params.get("hasPic") != null && !"".equals(params.get("hasPic"))) {
			queryParams.put("hasPic", params.get("hasPic"));
		}
		if (params.get("hasPosition") != null
				&& !"".equals(params.get("hasPosition"))) {
			queryParams.put("hasPosition", params.get("hasPosition"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null
				&& !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				if (attrMap.get(key) == null || "".equals(attrMap.get(key))) {
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}

		String paramJson = JSONObject.toJSONString(queryParams);
		List<Map> res = this.mctDeviceMongoDao.getMctDeviceList(paramJson,
				searchName);

		return res;
	}

	public int insertMctDevice(Map<String, Object> params) {

		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("companyCode", params.get("companyCode"));
		valueMap.put("proNo", params.get("proNo"));
		valueMap.put("mctCode", params.get("mctCode"));
		valueMap.put("deviceObjName", params.get("deviceObjName"));
		valueMap.put("x", params.get("x"));
		valueMap.put("y", params.get("y"));
		valueMap.put("xqAddressCode", params.get("xqAddressCode"));
		valueMap.put("xqAddressName", params.get("xqAddressName"));
		valueMap.put("addressCode", params.get("addressCode"));
		valueMap.put("addressName", params.get("addressName"));
		valueMap.put("qrCode", params.get("qrCode"));
		valueMap.put("problemCode", params.get("problemCode"));
		valueMap.put("problemName", params.get("problemName"));
		valueMap.put("healthCode", params.get("healthCode"));
		valueMap.put("healthName", params.get("healthName"));
		valueMap.put("remarks", params.get("remarks"));
		valueMap.put("proName", params.get("proName"));
		valueMap.put("deviceTypeCode", params.get("deviceTypeCode"));
		if (params.get("attrJson") != null
				&& !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				valueMap.put(key, attrMap.get(key));
			}
		}

		if (valueMap.get("x") != null && valueMap.get("y") != null
				&& !"".equals(valueMap.get("x"))
				&& !"".equals(valueMap.get("y"))) {

			double x = Double.parseDouble(valueMap.get("x").toString());
			double y = Double.parseDouble(valueMap.get("y").toString());
			if (x > 0 && y > 0) {

				Point point = Converter.BD09ToWGS84(x, y);
				if (point != null) {

					valueMap.put("rx", point.getLongitude());
					valueMap.put("ry", point.getLatitude());
				}
			}

		}

		// 生成编码
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				"simpleCodeWorker");
		valueMap.put("deviceObjCode", codeWorker.createCode("MCT"));
		String valueJson = JSONObject.toJSONString(valueMap);
		int icount = this.mctDeviceMongoDao.insertMctDevice(valueJson, null);

		return icount;
	}

	public int insertMctDevice1(Map<String, Object> params,
			String deviceObjCode, UserToken token) {

		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("companyCode", params.get("companyCode"));
		valueMap.put("proNo", params.get("proNo"));
		valueMap.put("mctCode", params.get("mctCode"));
		valueMap.put("deviceObjName", params.get("deviceObjName"));
		valueMap.put("x", params.get("x"));
		valueMap.put("y", params.get("y"));
		valueMap.put("xqAddressCode", params.get("xqAddressCode"));
		valueMap.put("xqAddressName", params.get("xqAddressName"));
		valueMap.put("addressCode", params.get("addressCode"));
		valueMap.put("addressName", params.get("addressName"));
		valueMap.put("qrCode", params.get("qrCode"));
		valueMap.put("problemCode", params.get("problemCode"));
		valueMap.put("problemName", params.get("problemName"));
		valueMap.put("healthCode", params.get("healthCode"));
		valueMap.put("healthName", params.get("healthName"));
		valueMap.put("remarks", params.get("remarks"));
		valueMap.put("proName", params.get("proName"));
		valueMap.put("deviceTypeCode", params.get("deviceTypeCode"));
		valueMap.put("codes", params.get("codes"));

		if (params.get("attrJson") != null
				&& !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				valueMap.put(key, attrMap.get(key));
			}
		}

		if (valueMap.get("x") != null && valueMap.get("y") != null
				&& !"".equals(valueMap.get("x"))
				&& !"".equals(valueMap.get("y"))) {

			double x = Double.parseDouble(valueMap.get("x").toString());
			double y = Double.parseDouble(valueMap.get("y").toString());
			if (x > 0 && y > 0) {

				Point point = Converter.BD09ToWGS84(x, y);
				if (point != null) {

					valueMap.put("rx", point.getLongitude());
					valueMap.put("ry", point.getLatitude());
				}
			}
		}

		// 生成编码

		valueMap.put("deviceObjCode", deviceObjCode);
		String valueJson = JSONObject.toJSONString(valueMap);
		int icount = this.mctDeviceMongoDao.insertMctDevice(valueJson, token);

		return icount;
	}

	public int deviceIsHave(Map<String, Object> params) {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("companyCode", params.get("companyCode"));
		valueMap.put("deviceObjName", params.get("deviceObjName"));

		// String valueJson = JSONObject.toJSONString(valueMap);
		int icount = this.mctDeviceMongoDao.deviceIsHave(valueMap);

		return icount;
	}

	public int updateMctDevice(Map<String, Object> params, UserToken token) {

		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("companyCode", params.get("companyCode"));
		valueMap.put("proNo", params.get("proNo"));
		valueMap.put("mctCode", params.get("mctCode"));
		valueMap.put("deviceObjName", params.get("deviceObjName"));
		// valueMap.put("x", params.get("x"));
		// valueMap.put("y", params.get("y"));
		// valueMap.put("xqAddressCode", params.get("xqAddressCode"));
		// valueMap.put("xqAddressName", params.get("xqAddressName"));
		// valueMap.put("addressCode", params.get("addressCode"));
		// valueMap.put("addressName", params.get("addressName"));
		valueMap.put("qrCode", params.get("qrCode"));
		valueMap.put("problemCode", params.get("problemCode"));
		valueMap.put("problemName", params.get("problemName"));
		valueMap.put("healthCode", params.get("healthCode"));
		valueMap.put("healthName", params.get("healthName"));
		valueMap.put("remarks", params.get("remarks"));
		valueMap.put("proName", params.get("proName"));
		valueMap.put("deviceObjCode", params.get("deviceObjCode"));
		valueMap.put("deviceTypeCode", params.get("deviceTypeCode"));
		if (params.get("attrJson") != null
				&& !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				valueMap.put(key, attrMap.get(key));
			}
		}

		String valueJson = JSONObject.toJSONString(valueMap);
		int icount = this.mctDeviceMongoDao.updateMctDevice(valueJson, token);

		return icount;
	}

	public int batchUpdateMctDevice(Map<String, Object> params, UserToken token) {
		int rows = this.mctDeviceMongoDao.batchUpdateMctDevice(params, token);
		return rows;
	}

	public Map<String, Object> getMctDevice(String deviceObjCode) {

		Map<String, Object> mctDevice = this.mctDeviceMongoDao
				.getMctDevice(deviceObjCode);

		return mctDevice;
	}

	public int deleteMctDevice(String deviceObjCode, UserToken token) {

		int icount = this.mctDeviceMongoDao.deleteMctDevice(deviceObjCode,
				token);

		return icount;
	}

	public int updateDeviceLoc(Map<String, Object> params, UserToken token) {

		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("deviceObjCode", params.get("deviceObjCode"));
		valueMap.put("x", params.get("x"));
		valueMap.put("y", params.get("y"));
		valueMap.put("rx", params.get("rx"));
		valueMap.put("ry", params.get("ry"));
		String valueJson = JSONObject.toJSONString(valueMap);
		int icount = this.mctDeviceMongoDao.updateDeviceLoc(valueJson, token);

		return icount;
	}

	public List<Map<String, Object>> getMctView(String companyCode) {

		DeviceDao deviceDao = (DeviceDao) getDao();

		return deviceDao.getMctView(companyCode);
	}

	public List<Map<String, String>> getMctViewPros(Map<String, Object> params) {

		DeviceDao deviceDao = (DeviceDao) getDao();
		Map<String, String> mctProMap = deviceDao.getMctViewPros(params);
		List<Map<String, String>> mctProList = new ArrayList<Map<String, String>>();
		if (mctProMap != null && mctProMap.size() > 0) {

			String proNos = mctProMap.get("proNos");
			String proNames = mctProMap.get("proNames");
			String proMctCodes = mctProMap.get("proMctCodes");
			String proMctNames = mctProMap.get("proMctNames");
			if (proNos != null && !"".equals(proNos) && proNames != null
					&& !"".equals(proNames) && proMctCodes != null
					&& !"".equals(proMctCodes) && proMctNames != null
					&& !"".equals(proMctNames)) {

				String[] proNo = proNos.split(",");
				String[] proName = proNames.split(",");
				String[] proMctCode = proMctCodes.split(",");
				String[] proMctName = proMctNames.split(",");

				for (int i = 0; i < proNo.length; i++) {

					Map<String, String> proMap = new HashMap<String, String>();
					proMap.put("proNo", proNo[i]);
					proMap.put("proName", proName[i]);
					proMap.put("proMctCode", proMctCode[i]);
					proMap.put("proMctName", proMctName[i]);

					mctProList.add(proMap);
				}
			}
		}

		return mctProList;
	}

	public Map<String, Object> mctDeviceDistribute(Map<String, Object> params) {

		DeviceDao deviceDao = (DeviceDao) getDao();
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {

			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null
				&& !"".equals(params.get("deviceTypeCode"))) {

			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null
				&& !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				if (attrMap.get(key) == null || "".equals(attrMap.get(key))) {
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}

		String paramJson = JSONObject.toJSONString(queryParams);
		String groupField = (String) params.get("groupField");
		List<String> featureFieldList = deviceDao.getFeatureField(params);
		List<Map> mctDeviceList = this.mctDeviceMongoDao
				.getMctDeviceDistribute(paramJson, searchName, groupField,
						featureFieldList);
		List<Map<String, Object>> groupList = this.mctDeviceMongoDao
				.mctDeviceCount(paramJson, searchName, groupField);
		Map<String, Object> resultList = new HashMap<String, Object>();

		if (mctDeviceList != null && mctDeviceList.size() > 0
				&& groupList != null && groupList.size() > 0) {

			resultList.put("mctDeviceList", mctDeviceList);
			resultList.put("groupList", groupList);
		}

		return resultList;
	}

	public List<Map<String, Object>> spatialQueryMctDevice(
			Map<String, Object> params) {

		DeviceDao deviceDao = (DeviceDao) getDao();
		List<Map<String, Object>> mctDeviceList = new ArrayList<Map<String, Object>>();
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {

			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null
				&& !"".equals(params.get("deviceTypeCode"))) {

			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null
				&& !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				if (attrMap.get(key) == null || "".equals(attrMap.get(key))) {
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}

		GeoPolygon polygon = null;
		if (params.get("xs") != null && !"".equals(params.get("xs"))) {

			String x = (String) params.get("xs");
			String y = (String) params.get("ys");
			if (x != null && !"".equals(x) && y != null && !"".equals(y)) {

				String[] xs = x.split(",");
				String[] ys = y.split(",");
				if (xs != null && xs.length > 0 && ys != null && ys.length > 0) {

					polygon = new GeoPolygon(x, y);
					GeoExtend extend = polygon.getExtend();

					DBObject xquery = new BasicDBObject();
					xquery.put("$gte", extend.getMinx());
					xquery.put("$lte", extend.getMaxx());
					queryParams.put("x", xquery);

					DBObject yquery = new BasicDBObject();
					yquery.put("$gte", extend.getMiny());
					yquery.put("$lte", extend.getMaxy());
					queryParams.put("y", yquery);

				}
			}
		}

		String paramJson = JSONObject.toJSONString(queryParams);
		List<String> featureFieldList = deviceDao.getFeatureField(params);
		List<Map<String, Object>> res = this.mctDeviceMongoDao
				.spatialQueryMctDevice(paramJson, searchName, featureFieldList);
		if (res != null && res.size() > 0) {

			for (Map<String, Object> deviceItem : res) {

				if (polygon != null) {

					if (deviceItem.get("x") != null
							&& !"".equals(deviceItem.get("x"))) {

						Double _x = (Double) deviceItem.get("x");
						Double _y = (Double) deviceItem.get("y");
						GeoPoint p = new GeoPoint(_x, _y);
						if (MathUtil.inPolygon(polygon, p)) {

							mctDeviceList.add(deviceItem);
						}
					}
				} else {

					mctDeviceList.add(deviceItem);
				}
			}
		}

		return mctDeviceList;
	}

	public List<Map<String, Object>> spatialQueryMctDeviceExport(
			Map<String, Object> params) {

		List<Map<String, Object>> mctDeviceList = new ArrayList<Map<String, Object>>();
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {

			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null
				&& !"".equals(params.get("deviceTypeCode"))) {

			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null
				&& !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				if (attrMap.get(key) == null || "".equals(attrMap.get(key))) {
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}

		GeoPolygon polygon = null;
		if (params.get("xs") != null && !"".equals(params.get("xs"))) {

			String x = (String) params.get("xs");
			String y = (String) params.get("ys");
			if (x != null && !"".equals(x) && y != null && !"".equals(y)) {

				String[] xs = x.split(",");
				String[] ys = y.split(",");
				if (xs != null && xs.length > 0 && ys != null && ys.length > 0) {

					polygon = new GeoPolygon(x, y);
					GeoExtend extend = polygon.getExtend();

					DBObject xquery = new BasicDBObject();
					xquery.put("$gte", extend.getMinx());
					xquery.put("$lte", extend.getMaxx());
					queryParams.put("x", xquery);

					DBObject yquery = new BasicDBObject();
					yquery.put("$gte", extend.getMiny());
					yquery.put("$lte", extend.getMaxy());
					queryParams.put("y", yquery);

				}
			}
		}

		String paramJson = JSONObject.toJSONString(queryParams);
		List<Map> res = this.mctDeviceMongoDao.getMctDeviceList(paramJson,
				searchName);
		if (res != null && res.size() > 0) {

			for (Map<String, Object> deviceItem : res) {

				if (polygon != null) {

					if (deviceItem.get("x") != null
							&& !"".equals(deviceItem.get("x"))) {

						Double _x = (Double) deviceItem.get("x");
						Double _y = (Double) deviceItem.get("y");
						GeoPoint p = new GeoPoint(_x, _y);
						if (MathUtil.inPolygon(polygon, p)) {

							mctDeviceList.add(deviceItem);
						}
					}
				} else {

					mctDeviceList.add(deviceItem);
				}
			}
		}

		return mctDeviceList;
	}

	public List<Map<String, Object>> spatialCircleQueryMctDevice(
			Map<String, Object> params) {

		DeviceDao deviceDao = (DeviceDao) getDao();
		List<Map<String, Object>> mctDeviceList = new ArrayList<Map<String, Object>>();
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {

			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null
				&& !"".equals(params.get("deviceTypeCode"))) {

			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null
				&& !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				if (attrMap.get(key) == null || "".equals(attrMap.get(key))) {
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}

		float cx = 0f;
		float cy = 0f;
		float r = 0f;
		if (params.get("x") != null && !"".equals(params.get("y"))) {

			String x = (String) params.get("xs");
			String y = (String) params.get("ys");
			cx = Float.parseFloat(params.get("x").toString());
			cy = Float.parseFloat(params.get("y").toString());
			r = Float.parseFloat(params.get("r").toString());
			if (x != null && !"".equals(x) && y != null && !"".equals(y)) {

				String[] xs = x.split(",");
				String[] ys = y.split(",");
				if (xs != null && xs.length > 0 && ys != null && ys.length > 0) {

					double minx = Double.parseDouble(xs[0]);
					double maxx = Double.parseDouble(xs[1]);
					double miny = Double.parseDouble(ys[0]);
					double maxy = Double.parseDouble(ys[1]);

					DBObject xquery = new BasicDBObject();
					xquery.put("$gte", minx);
					xquery.put("$lte", maxx);
					queryParams.put("x", xquery);

					DBObject yquery = new BasicDBObject();
					yquery.put("$gte", miny);
					yquery.put("$lte", maxy);
					queryParams.put("y", yquery);
				}
			}
		}

		String paramJson = JSONObject.toJSONString(queryParams);
		List<String> featureFieldList = deviceDao.getFeatureField(params);
		List<Map<String, Object>> res = this.mctDeviceMongoDao
				.spatialQueryMctDevice(paramJson, searchName, featureFieldList);
		if (res != null && res.size() > 0) {

			for (Map<String, Object> deviceItem : res) {

				if (cx > 0) {

					if (deviceItem.get("x") != null
							&& !"".equals(deviceItem.get("x"))) {

						Double _x = (Double) deviceItem.get("x");
						Double _y = (Double) deviceItem.get("y");
						if (MathUtil.inCircle(_x, _y, cx, cy, r)) {

							mctDeviceList.add(deviceItem);
						}
					}
				} else {

					mctDeviceList.add(deviceItem);
				}
			}
		}

		return mctDeviceList;
	}

	public List<Map<String, Object>> getDeviceObjNames(String deviceTypeCode,
			String companyCode, String proNo, String dependFields) {

		return this.mctDeviceMongoDao.getDeviceObjNames(deviceTypeCode,
				companyCode, proNo, dependFields);
	}

	public GeoPoint coordsConvertBD09ToWGS84(double x, double y) {

		GeoPoint gpoint = null;
		if (x > 0 && y > 0) {

			Point p = Converter.BD09ToWGS84(x, y);
			if (p != null) {

				gpoint = new GeoPoint(p.getLongitude(), p.getLatitude());
			}
		}

		return gpoint;
	}

	public GeoPoint coordsConvertWGS84ToBD09(double x, double y) {

		GeoPoint gpoint = null;
		if (x > 0 && y > 0) {

			Point p = Converter.WGS84ToBD09(x, y);
			if (p != null) {

				gpoint = new GeoPoint(p.getLongitude(), p.getLatitude());
			}
		}

		return gpoint;
	}

	public List<Map<String, Object>> getNullCoordsDevices(
			Map<String, Object> params) {

		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {

			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null
				&& !"".equals(params.get("deviceTypeCode"))) {

			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null
				&& !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				if (attrMap.get(key) == null || "".equals(attrMap.get(key))) {
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}

		String paramJson = JSONObject.toJSONString(queryParams);
		List<Map<String, Object>> deviceList = this.mctDeviceMongoDao
				.getNullCoordsDevices(paramJson, searchName);

		return deviceList;
	}

	public List<Map> getNoPicDevices(Map<String, Object> params) {

		List<Map> deviceList = getMctDeviceList(params);
		if (deviceList != null && deviceList.size() > 0) {

			for (int i = deviceList.size() - 1; i >= 0; i--) {

				Map deviceItem = deviceList.get(i);
				long icount = mctDeviceMongoDao.getDevicePicCount(
						(String) deviceItem.get("companyCode"),
						(String) deviceItem.get("deviceObjCode"));
				if (icount > 0) {

					deviceList.remove(i);
				}
			}
		}

		return deviceList;

	}

	public PageBean getMemberOpStats(Map<String, Object> params) {

		DeviceDao deviceDao = (DeviceDao) getDao();
		int currentPage = (Integer) params.get("currentPage");
		int perpage = (Integer) params.get("perpage");
		String companyCode = (String) params.get("companyCode");
		String startTime = "";
		String endTime = "";
		if (params.get("startTime") != null
				&& !"".equals(params.get("startTime"))) {

			startTime = (String) params.get("startTime");
		}

		if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {

			endTime = (String) params.get("endTime");
		}
		PageBean memberPage = deviceDao.selectPmMemberPage(params, currentPage,
				perpage);
		if (memberPage != null && memberPage.getList() != null
				&& memberPage.getList().size() > 0) {

			List<Map<String, Object>> opStatsList = deviceOpLogMongoDao
					.memberOpStats(companyCode, startTime, endTime,
							memberPage.getList());
			for (int i = 0; i < memberPage.getList().size(); i++) {

				Map<String, Object> memberItem = (Map<String, Object>) memberPage
						.getList().get(i);
				setMemberOpStats(memberItem, opStatsList);
			}
		}

		return memberPage;
	}

	private void setMemberOpStats(Map<String, Object> memberItem,
			List<Map<String, Object>> opStatsList) {

		if (opStatsList != null && opStatsList.size() > 0) {

			for (Map<String, Object> opStatsItem : opStatsList) {

				if (memberItem.get("employeeCode").equals(
						opStatsItem.get("employeeCode"))) {

					memberItem.put("addCount", opStatsItem.get("addCount"));
					memberItem.put("updateCount",
							opStatsItem.get("updateCount"));
					memberItem.put("auditCount", opStatsItem.get("auditCount"));
					memberItem.put("delCount", opStatsItem.get("delCount"));
				}
			}
		} else {

			memberItem.put("addCount", 0);
			memberItem.put("updateCount", 0);
			memberItem.put("auditCount", 0);
			memberItem.put("delCount", 0);
		}
	}

	private List<Map<String, Object>> getSearchAttr(
			List<Map<String, Object>> attrList) {

		List<Map<String, Object>> searchAttrList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> attrItem : attrList) {

			int dimSearch = Integer.parseInt(attrItem.get("dimSearch")
					.toString());
			int dtype = Integer.parseInt(attrItem.get("dtype").toString());
			if (dimSearch == 1) {

				searchAttrList.add(attrItem);
				if (dtype == 4) {

					// 先处理本地数据源
					int enumSource = Integer.parseInt(attrItem
							.get("enumSource").toString());
					if (enumSource == 1) {

						String enumValue = (String) attrItem.get("enumValue");
						attrItem.put("enumValue", enumValue.split(","));
					}
				}
			}
		}

		return searchAttrList;
	}

	private List<Map<String, Object>> getListShowAttr(
			List<Map<String, Object>> attrList) {

		List<Map<String, Object>> listShowAttrList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> attrItem : attrList) {

			int dimList = (Integer) attrItem.get("dimList");
			if (dimList == 1) {

				listShowAttrList.add(attrItem);
			}
		}

		return listShowAttrList;
	}

	public int deviceQualityCheck(Map map, UserToken token) {
		//
		int res = this.mctDeviceMongoDao.deviceQualityCheck(map, token);
		return res;
	}

	public MctDeviceMongoDao getMctDeviceMongoDao() {
		return mctDeviceMongoDao;
	}

	public void setMctDeviceMongoDao(MctDeviceMongoDao mctDeviceMongoDao) {
		this.mctDeviceMongoDao = mctDeviceMongoDao;
	}

	public FinalHttp getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(FinalHttp httpClient) {
		this.httpClient = httpClient;
	}

	public PmDeviceOpLogMongoDao getDeviceOpLogMongoDao() {
		return deviceOpLogMongoDao;
	}

	public void setDeviceOpLogMongoDao(PmDeviceOpLogMongoDao deviceOpLogMongoDao) {
		this.deviceOpLogMongoDao = deviceOpLogMongoDao;
	}

	public List<Map<String, Object>> getPicClass(String deviceTypeCode) {

		Map m = new HashMap();

		m.put("deviceTypeCode", deviceTypeCode);

		DeviceDao deviceDao = (DeviceDao) getDao();

		return deviceDao.getPicClass(m);
	}

	public List queryPmMdList(String proNo, String queryDate) {
		// TODO Auto-generated method stub
		return mctDeviceMongoDao.queryPmMdList(proNo, queryDate);
	}

}
