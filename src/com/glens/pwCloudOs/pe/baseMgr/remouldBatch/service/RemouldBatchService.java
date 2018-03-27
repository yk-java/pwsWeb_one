package com.glens.pwCloudOs.pe.baseMgr.remouldBatch.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.om.deviceMgr.device.service.DeviceService;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.dao.DeviceBookDao;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceBookVo;
import com.glens.pwCloudOs.pe.baseMgr.remouldBatch.dao.RemouldBatchMongoDao;
import com.glens.pwCloudOs.pe.proReserve.proDevice.dao.ProDeviceDao;
import com.glens.pwCloudOs.pe.proReserve.proDevice.dao.ProDeviceMongoDao;

public class RemouldBatchService extends EAPAbstractService {
	private RemouldBatchMongoDao remouldBatchMongoDao;
	private DeviceBookDao deviceBookDao;
	private DeviceService deviceService;
	
	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	private FinalHttp httpClient;
	public FinalHttp getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(FinalHttp httpClient) {
		this.httpClient = httpClient;
	}

	public DeviceBookDao getDeviceBookDao() {
		return deviceBookDao;
	}

	public void setDeviceBookDao(DeviceBookDao deviceBookDao) {
		this.deviceBookDao = deviceBookDao;
	}

	ProDeviceDao proDeviceDao;
	
	public ProDeviceDao getProDeviceDao() {
		return proDeviceDao;
	}

	public void setProDeviceDao(ProDeviceDao proDeviceDao) {
		this.proDeviceDao = proDeviceDao;
	}

	public RemouldBatchMongoDao getRemouldBatchMongoDao() {
		return remouldBatchMongoDao;
	}

	public void setRemouldBatchMongoDao(RemouldBatchMongoDao remouldBatchMongoDao) {
		this.remouldBatchMongoDao = remouldBatchMongoDao;
	}
	private ProDeviceMongoDao proDeviceMongoDao;
	

	public ProDeviceMongoDao getProDeviceMongoDao() {
		return proDeviceMongoDao;
	}

	public void setProDeviceMongoDao(ProDeviceMongoDao proDeviceMongoDao) {
		this.proDeviceMongoDao = proDeviceMongoDao;
	}

	/**
	 * 查询改造批次下的设备清单
	 * @param vo
	 * @return
	 */
	public PageBean queryBatchDevice(Map<String, Object> params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		paramsN.put("attrJson", params.get("attrJson"));
		paramsN.put("currentPage", params.get("currentPage"));
		paramsN.put("perpage", params.get("perpage"));
		paramsN.put("companyCode", params.get("companyCode"));
		paramsN.put("proNo", params.get("proNo"));
		paramsN.put("reserveProNo", params.get("reserveProNo"));
		
		paramsN.put("reserveConfirm", params.get("reserveConfirm"));
		paramsN.put("reserveStatus", params.get("reserveStatus"));
		
		paramsN.put("searchName", params.get("searchName"));
		paramsN.put("sysProcessFlag", params.get("sysProcessFlag"));
		paramsN.put("remouldBatchCode", params.get("remouldBatchCode"));
		PageBean pageBean = remouldBatchMongoDao.queryBatchDevice(paramsN);
		return pageBean;
	}
	
	public PageBean getExportList(Map<String, Object> params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		paramsN.put("attrJson", params.get("attrJson"));
		paramsN.put("currentPage", params.get("currentPage"));
		paramsN.put("perpage", params.get("perpage"));
		paramsN.put("companyCode", params.get("companyCode"));
		paramsN.put("proNo", params.get("proNo"));
		paramsN.put("reserveProNo", params.get("reserveProNo"));
		
		paramsN.put("reserveConfirm", params.get("reserveConfirm"));
		paramsN.put("reserveStatus", params.get("reserveStatus"));
		
		paramsN.put("searchName", params.get("searchName"));
		paramsN.put("sysProcessFlag", params.get("sysProcessFlag"));
		paramsN.put("remouldBatchCode", params.get("remouldBatchCode"));
		PageBean pageBean = remouldBatchMongoDao.getExportList(paramsN);
		return pageBean;
	}
	
	public PageBean queryBatchDevice1(Map<String, Object> params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		
		paramsN.put("companyCode", params.get("companyCode"));
		paramsN.put("reseverOrgcode", params.get("reseverOrgcode"));
		//paramsN.put("reserveStatus", null);//未改造的
		paramsN.put("searchName", params.get("searchName"));
		
		paramsN.put("reserveStatus", params.get("reserveStatus"));
		
		PageBean pageBean = remouldBatchMongoDao.queryBatchDevice1(paramsN);
		return pageBean;
	}
	
	/**
	 * 添加储备项目下的设备信息到改造批次下
	 * @param vo
	 * @return
	 */
	public int addProDeviceToBatch(Map<String, Object> params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		paramsN.put("attrJson", params.get("attrJson"));
		paramsN.put("currentPage", params.get("currentPage"));
		paramsN.put("perpage", params.get("perpage"));
		paramsN.put("companyCode", params.get("companyCode"));
		paramsN.put("proNo", params.get("proNo"));
		paramsN.put("reserveProNo", params.get("reserveProNo"));
		paramsN.put("rpAuditState", params.get("rpAuditState"));
		paramsN.put("searchName", params.get("searchName"));
		paramsN.put("sysProcessFlag", params.get("sysProcessFlag"));
		List<Map<String, Object>> lst= remouldBatchMongoDao.queryProDeviceList(paramsN);
		String remouldBatchCode = (String)params.get("remouldBatchCode");
		remouldBatchMongoDao.addDeviceToBatch(lst, remouldBatchCode);
		int rows = remouldBatchMongoDao.signProDevice(paramsN);
		return rows;
	}

	/**
	 * 分配批次下设备的改造单位
	 * @param vo
	 * @return
	 */
	public int setDeviceToPro(Map<String, Object> params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		paramsN.put("attrJson", params.get("attrJson"));
		paramsN.put("currentPage", params.get("currentPage"));
		paramsN.put("perpage", params.get("perpage"));
		paramsN.put("companyCode", params.get("companyCode"));
		paramsN.put("proNo", params.get("proNo"));
		paramsN.put("reserveProNo", params.get("reserveProNo"));
		paramsN.put("rpAuditState", params.get("rpAuditState"));
		paramsN.put("searchName", params.get("searchName"));
		paramsN.put("sysProcessFlag", params.get("sysProcessFlag"));
		paramsN.put("remouldBatchCode", params.get("remouldBatchCode"));
		String reseverOrgcode = (String)params.get("reseverOrgcode");
		String reseverOrgname = (String)params.get("reseverOrgname");
		String planRebuildTime=(String)params.get("planRebuildTime");
		int rows = remouldBatchMongoDao.updateDevice(paramsN, reseverOrgcode, reseverOrgname,planRebuildTime);
		return rows;
	}
	
	//手机端表箱报竣工
	public int upDevice(Map<String, Object> params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		paramsN.put("attrJson", params.get("attrJson"));
		paramsN.put("companyCode", params.get("companyCode"));
		paramsN.put("proNo", params.get("proNo"));
		paramsN.put("reserveProNo", params.get("reserveProNo"));
		paramsN.put("price", params.get("price"));
		paramsN.put("deviceBhIds", params.get("deviceBhIds"));
		paramsN.put("deviceByqName", params.get("deviceByqName"));//台区名称
		paramsN.put("deviceLineName", params.get("deviceLineName"));//线路名称
		paramsN.put("deviceBxId", params.get("deviceBxId"));//表箱编号
		//String deviceObjCode = (String)params.get("deviceObjCode");
		paramsN.put("mctCode", params.get("mctCode"));
		paramsN.put("deviceObjCode", params.get("deviceObjCode"));
		
		
	     String reserveB1Img=params.get("reserveB1Img").toString();
		 String reserveB2Img=params.get("reserveB2Img").toString();
		 String reserveA1Img=params.get("reserveA1Img").toString();
		 String reserveA2Img=params.get("reserveA2Img").toString();
		 String reserveOpImg=params.get("reserveOpImg").toString();
		 String reserveSignImg=params.get("reserveSignImg").toString();
		 String reservePcutImg=params.get("reservePcutImg").toString();
		 
		 
		 
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
			
	    //改造前1
		if(reserveB1Img!=null && reserveB1Img !="" && !reserveB1Img.equals("")){
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", reserveB1Img);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						paramsN.put("reserveB1Img", fileCode);
					}
				}
			}
		}
		//改造前2
		if(reserveB2Img!=null && reserveB2Img !="" && !reserveB2Img.equals("")){
			AjaxParams ajaxParams1 = new AjaxParams();
			ajaxParams1.put("base64Img", reserveB2Img);
			Object content1 = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams1);
			if (content1 != null) {
				JSONObject jsonObj1 = JSONObject.parseObject(content1.toString());
				if ("200".equals(jsonObj1.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj1.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						paramsN.put("reserveB2Img", fileCode);
					}
				}
			}
		}
		
		//改造后1
		if(reserveA1Img!=null && reserveA1Img !="" && !reserveA1Img.equals("")){
			AjaxParams ajaxParams2 = new AjaxParams();
			ajaxParams2.put("base64Img", reserveA1Img);
			Object content2 = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams2);
			if (content2 != null) {
				JSONObject jsonObj = JSONObject.parseObject(content2.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						paramsN.put("reserveA1Img", fileCode);
					}
				}
			}
		}
		
		//改造后2
		
		if(reserveA2Img!=null && reserveA2Img !="" && !reserveA2Img.equals("")){
			AjaxParams ajaxParams3 = new AjaxParams();
			ajaxParams3.put("base64Img", reserveA2Img);
			Object content3 = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams3);
			if (content3 != null) {
				JSONObject jsonObj = JSONObject.parseObject(content3.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						paramsN.put("reserveA2Img", fileCode);
					}
				}
			}
		}
		
		//工作票
		
		if(reserveOpImg!=null && reserveOpImg !="" && !reserveOpImg.equals("")){
			AjaxParams ajaxParams4 = new AjaxParams();
			ajaxParams4.put("base64Img", reserveOpImg);
			Object content4 = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams4);
			if (content4 != null) {
				JSONObject jsonObj = JSONObject.parseObject(content4.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						paramsN.put("reserveOpImg", fileCode);
					}
				}
			}
		}
		
		//用户关系表
		if(reserveSignImg!=null && reserveSignImg !="" && !reserveSignImg.equals("")){
			AjaxParams ajaxParams5 = new AjaxParams();
			ajaxParams5.put("base64Img", reserveSignImg);
			Object content5 = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams5);
			if (content5 != null) {
				JSONObject jsonObj = JSONObject.parseObject(content5.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						paramsN.put("reserveSignImg", fileCode);
					}
				}
			}
		}
		//用户关系表
		
		if(reservePcutImg!=null && reservePcutImg !="" && !reservePcutImg.equals("")){
			AjaxParams ajaxParams6 = new AjaxParams();
			ajaxParams6.put("base64Img", reservePcutImg);
			Object content6 = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams6);
			if (content6 != null) {
				JSONObject jsonObj = JSONObject.parseObject(content6.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						paramsN.put("reservePcutImg", fileCode);
					}
				}
			}
		}
		
		
		
		
		
		 
		

		//remouldBatchMongoDao.phoneAddDeviceToBatch(paramsN);
		//int rows = remouldBatchMongoDao.updateDeviceByPhone(paramsN,reserveB1Img,reserveB2Img,reserveA1Img,reserveA2Img,reserveOpImg,reserveSignImg,reservePcutImg);
		int rows = remouldBatchMongoDao.updateDeviceByPhone(paramsN);
		
		
		
		return rows;
	}
	
	//web端表箱报竣工
		public int upDevice1(Map<String, Object> params) {
			Map<String, Object> paramsN = new HashMap<String, Object>();
			paramsN.put("attrJson", params.get("attrJson"));
			paramsN.put("companyCode", params.get("companyCode"));
			paramsN.put("proNo", params.get("proNo"));
			paramsN.put("reserveProNo", params.get("reserveProNo"));
			paramsN.put("price", params.get("price"));
			paramsN.put("deviceBhIds", params.get("deviceBhIds"));
			paramsN.put("deviceByqName", params.get("deviceByqName"));//台区名称
			paramsN.put("deviceLineName", params.get("deviceLineName"));//线路名称
			paramsN.put("deviceBxId", params.get("deviceBxId"));//表箱编号
			//String deviceObjCode = (String)params.get("deviceObjCode");
			paramsN.put("mctCode", params.get("mctCode"));
			paramsN.put("deviceObjCode", params.get("deviceObjCode"));
			
			
		     String reserveB1Img=params.get("reserveB1Img").toString();
			 String reserveB2Img=params.get("reserveB2Img").toString();
			 String reserveA1Img=params.get("reserveA1Img").toString();
			 String reserveA2Img=params.get("reserveA2Img").toString();
			 String reserveOpImg=params.get("reserveOpImg").toString();
			 String reserveSignImg=params.get("reserveSignImg").toString();
			 String reservePcutImg=params.get("reservePcutImg").toString();
			 
			 
			 paramsN.put("reserveB1Img", reserveB1Img);
			 paramsN.put("reserveB2Img", reserveB2Img);
			 
			 paramsN.put("reserveA1Img", reserveA1Img);
			 paramsN.put("reserveA2Img", reserveA2Img);
			 paramsN.put("reserveOpImg", reserveOpImg);
			
			 paramsN.put("reserveSignImg", reserveSignImg);
			 paramsN.put("reservePcutImg", reservePcutImg);
//			remouldBatchMongoDao.phoneAddDeviceToBatch(paramsN);
			//int rows = remouldBatchMongoDao.updateDeviceByPhone(paramsN,reserveB1Img,reserveB2Img,reserveA1Img,reserveA2Img,reserveOpImg,reserveSignImg,reservePcutImg);
			int rows = remouldBatchMongoDao.updateDeviceByPhone(paramsN);
			
			
			
			return rows;
		}

	public PageBean queryProDevice(Map<String, Object> params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		paramsN.put("attrJson", params.get("attrJson"));
		paramsN.put("currentPage", params.get("currentPage"));
		paramsN.put("perpage", params.get("perpage"));
		paramsN.put("companyCode", params.get("companyCode"));
		paramsN.put("proNo", params.get("proNo"));
		paramsN.put("reserveProNo", params.get("reserveProNo"));
		paramsN.put("rpAuditState", params.get("rpAuditState"));
		paramsN.put("searchName", params.get("searchName"));
		paramsN.put("sysProcessFlag", params.get("sysProcessFlag"));
		PageBean pageBean = remouldBatchMongoDao.queryProDevice(paramsN);
		return pageBean;
	}

	/* 批量删除 */
	public int batDelProDevice(Map params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		paramsN.put("attrJson", params.get("attrJson"));
		paramsN.put("currentPage", params.get("currentPage"));
		paramsN.put("perpage", params.get("perpage"));
		paramsN.put("companyCode", params.get("companyCode"));
		paramsN.put("proNo", params.get("proNo"));
		paramsN.put("reserveProNo", params.get("reserveProNo"));
		paramsN.put("rpAuditState", params.get("rpAuditState"));
		paramsN.put("searchName", params.get("searchName"));
		paramsN.put("sysProcessFlag", params.get("sysProcessFlag"));
		paramsN.put("remouldBatchCode", params.get("remouldBatchCode"));
		List<Map<String, Object>> list = remouldBatchMongoDao.queryRemouldProDeviceList(paramsN);
		int rows=0;
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> vo = list.get(i);
			Map<String, Object> params2=new HashMap<String, Object>();
			params2.put("companyCode", vo.get("companyCode"));
			params2.put("reserveProNo", vo.get("reserveProNo"));
			params2.put("deviceObjCode", vo.get("deviceObjCode"));
			int r = remouldBatchMongoDao.signProDeviceDel(params2);
			rows+=r;
		}
		remouldBatchMongoDao.batDelDeviceToBatch(paramsN);
		return rows;
	}
	
	/* 删除 */
	public int delProDevice(Map params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		paramsN.put("companyCode", params.get("companyCode"));
		paramsN.put("reserveProNo", params.get("reserveProNo"));
		paramsN.put("deviceObjCode", params.get("deviceObjCode"));
		paramsN.put("remouldBatchCode", params.get("remouldBatchCode"));
		remouldBatchMongoDao.delDeviceToBatch(paramsN);
		int rows = remouldBatchMongoDao.signProDeviceDel(paramsN);
		return rows;
	}
	
	public List<Map<String, Object>> getRemouldStatistics(Map<String, Object> params) {
		
		String companyCode = (String) params.get("companyCode");
		String reserveProNo = (String) params.get("reserveProNo");
		String remouldBatchCode = (String) params.get("remouldBatchCode");
		List<Map<String, Object>> remouldStatisticsList = new ArrayList<Map<String,Object>>();
		
		List<Map<String, Object>> materialAttrs = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> attrList = deviceService.getMctDeviceAttr(params);
		for (Iterator iterator = attrList.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			try {
				if(1==(Integer)map.get("dimPeBsMaterial")){
					materialAttrs.add(map);
				}
			} catch (Exception e) {
			}
		}
		List<Map<String, Object>> statisticsList = this.remouldBatchMongoDao.censusRemouldBatchDevice(companyCode, 
				reserveProNo, remouldBatchCode, materialAttrs);
		if (statisticsList != null && statisticsList.size() > 0) {
			
			Map<String, Object> totalItem = new HashMap<String, Object>();
			totalItem.put("reseverOrgcode", "");
			totalItem.put("reseverOrgname", "总计");
			for (Map<String, Object> statistics : statisticsList) {
				for(String key : statistics.keySet()){
					if("reseverOrgcode".equals(key) || "reseverOrgname".equals(key)){
						continue;
					}
					Double val = 0d;
					try {
						val = (Double)totalItem.get(key);
						if(val==null){
							val=0d;
						}
					} catch (Exception e) {
						val=0d;
					}
					try {
						Double val2 = (Double)statistics.get(key);
						if(val2==null){
							val2=0d;
						}
						val=val+val2;
					} catch (Exception e) {
						val=val+0d;
					}
					totalItem.put(key, val);
				}
			}
//			totalItem.put("reseverOrgcode", "");
//			totalItem.put("reseverOrgname", "总计");
//			int totalMeterBoxCount = 0;
//			int totalHouseHold = 0;
//			int totalEstateCount = 0;
//			int totalUnitCount = 0;
//			Map<String, Object> totalMaterial = new LinkedHashMap<String, Object>();
//			for (Map<String, Object> statistics : statisticsList) {
//				
//				Map<String, Object> statisticsItem = new HashMap<String, Object>();
//				statisticsItem.put("reseverOrgcode", statistics.get("reseverOrgcode"));
//				statisticsItem.put("reseverOrgname", statistics.get("reseverOrgname"));
//				statisticsItem.put("meterBoxCount", statistics.get("meterBoxCount"));
//				statisticsItem.put("houseHold", statistics.get("houseHold"));
//				statisticsItem.put("estateCount", statistics.get("estateCount"));
//				statisticsItem.put("unitCount", statistics.get("unitCount"));
//				
//				//总计
//				totalMeterBoxCount += Float.parseFloat(statistics.get("meterBoxCount").toString());
//				totalHouseHold += Float.parseFloat(statistics.get("houseHold").toString());
//				totalEstateCount += Float.parseFloat(statistics.get("estateCount").toString());
//				totalUnitCount += Float.parseFloat(statistics.get("unitCount").toString());
//				
//				List<Map<String, Object>> deviceList = this.remouldBatchMongoDao.getReserveOrgDevices(companyCode, reserveProNo, 
//						remouldBatchCode, (String) statistics.get("reseverOrgcode"));
//				if (deviceList != null && deviceList.size() > 0) {
//					
//					Map<String, Object> materialMap = new LinkedHashMap<String, Object>();
//					statisticsItem.put("material", materialMap);
//					for (Map<String, Object> deviceItem : deviceList) {
//						
//						List<Map<String, Object>> materialList = this.remouldBatchMongoDao.getReserveDeviceMaterials(companyCode, reserveProNo, 
//								(String) deviceItem.get("deviceObjCode"));
//						if (materialList != null && materialList.size() > 0) {
//							
//							for (Map<String, Object> materialItem : materialList) {
//								
//								if (!materialMap.containsKey(materialItem.get("materialTypeCode"))) {
//									
//									materialMap.put(materialItem.get("materialTypeCode").toString(), Float.parseFloat(materialItem.get("needCount").toString()));
//								}
//								else {
//									
//									Float needCount = Float.parseFloat(materialItem.get("needCount").toString())
//											+ (Float) materialMap.get(materialItem.get("materialTypeCode").toString());
//									materialMap.put(materialItem.get("materialTypeCode").toString(), needCount);
//								}
//								
//								if (!totalMaterial.containsKey(materialItem.get("materialTypeCode"))) {
//									
//									totalMaterial.put(materialItem.get("materialTypeCode").toString(), Float.parseFloat(materialItem.get("needCount").toString()));
//								}
//								else {
//									
//									Float needCount = Float.parseFloat(materialItem.get("needCount").toString()) 
//											+ (Float) totalMaterial.get(materialItem.get("materialTypeCode").toString());
//									totalMaterial.put(materialItem.get("materialTypeCode").toString(), needCount);
//								}
//							}
//						}
//					}
//				}
//				
//				remouldStatisticsList.add(statisticsItem);
//			}
			
//			totalItem.put("meterBoxCount", totalMeterBoxCount);
//			totalItem.put("houseHold", totalHouseHold);
//			totalItem.put("estateCount", totalEstateCount);
//			totalItem.put("unitCount", totalUnitCount);
//			totalItem.put("material", totalMaterial);
			statisticsList.add(totalItem);
		}
		
		return statisticsList;
	}

	public Map<String, Object> getProDevice(Map params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		paramsN.put("companyCode", params.get("companyCode"));
		paramsN.put("reserveProNo", params.get("reserveProNo"));
		paramsN.put("deviceObjCode", params.get("deviceObjCode"));
		paramsN.put("remouldBatchCode", params.get("remouldBatchCode"));
		Map<String, Object> device = remouldBatchMongoDao.getDevice(paramsN);
		if (device != null) {
			List<Map<String, Object>> reserveMateriaList = remouldBatchMongoDao.getReserveDeviceMaterials(params.get("companyCode").toString(), 
					params.get("reserveProNo").toString(), params.get("deviceObjCode").toString());
			device.put("reserveMateriaList", reserveMateriaList);
			List<Map<String, Object>> remouldMateriaList = remouldBatchMongoDao.getRemouldDeviceMaterials(params.get("companyCode").toString(), 
					params.get("reserveProNo").toString(), params.get("deviceObjCode").toString());
			device.put("remouldMateriaList", remouldMateriaList);
		}
		return device;
	}
	
	public Map<String, Object> getProDevice1(Map params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		paramsN.put("companyCode", params.get("companyCode"));
		paramsN.put("reserveProNo", params.get("reserveProNo"));
		paramsN.put("deviceObjCode", params.get("deviceObjCode"));
		//paramsN.put("remouldBatchCode", params.get("remouldBatchCode"));
		Map<String, Object> device = remouldBatchMongoDao.getDevice(paramsN);
		if (device != null) {
			List<Map<String, Object>> reserveMateriaList = remouldBatchMongoDao.getReserveDeviceMaterials(params.get("companyCode").toString(), 
					params.get("reserveProNo").toString(), params.get("deviceObjCode").toString());
			device.put("reserveMateriaList", reserveMateriaList);
			List<Map<String, Object>> remouldMateriaList = remouldBatchMongoDao.getRemouldDeviceMaterials(params.get("companyCode").toString(), 
					params.get("reserveProNo").toString(), params.get("deviceObjCode").toString());
			device.put("remouldMateriaList", remouldMateriaList);
		}
		return device;
	}
	
	/*验收 */
	public int reserveConfirm(Map params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		paramsN.put("companyCode", params.get("companyCode"));
		paramsN.put("deviceObjCode", params.get("deviceObjCode"));
		paramsN.put("reserveProNo", params.get("reserveProNo"));
		paramsN.put("remouldBatchCode", params.get("remouldBatchCode"));
		
		paramsN.put("auditPersonCode", params.get("auditPersonCode"));
		paramsN.put("auditPersonName", params.get("auditPersonName"));
		paramsN.put("auditSuggest", params.get("auditSuggest"));
		
		
		if(params.get("reserveConfirm")!=null && params.get("reserveConfirm").toString().equals("2")){
			paramsN.put("reserveConfirm", params.get("reserveConfirm"));
		}else{
			// 照片拷贝到设备图册
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("companyCode", params.get("companyCode"));
			params2.put("reserveProNo", params.get("reserveProNo"));
			params2.put("deviceObjCode", params.get("deviceObjCode"));
			params2.put("remouldBatchCode", params.get("remouldBatchCode"));
			
			Map<String, Object> device = remouldBatchMongoDao.getDevice(params2);
			String reserveA1Img = (String)device.get("reserveA1Img");
			String reserveA2Img = (String)device.get("reserveA2Img");
			String reserveB1Img = (String)device.get("reserveB1Img");
			String reserveB2Img = (String)device.get("reserveB2Img");
			
			DeviceBookVo v=new DeviceBookVo();
			v.setCompanyCode((String)device.get("companyCode"));
			v.setDeviceObjCode((String)device.get("deviceObjCode"));
			v.setMctCode((String)device.get("mctCode"));
			v.setPicTitle((String)device.get("deviceObjName"));
			v.setProNo((String)device.get("proNo"));
			v.setPIC_CLASSIFY_CODE("PC01002");
			v.setPIC_CLASSIFY_NAME("改造");
			v.setSysCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			v.setPicVisitid(reserveA1Img);
			v.setPicTitle("改造后1");
			deviceBookDao.addImg(v);
			v.setPicVisitid(reserveA2Img);
			v.setPicTitle("改造后2");
			deviceBookDao.addImg(v);
			v.setPicVisitid(reserveB1Img);
			v.setPicTitle("改造前1");
			deviceBookDao.addImg(v);
			v.setPicVisitid(reserveB2Img);
			v.setPicTitle("改造前2");
			deviceBookDao.addImg(v);
			
			// 立项类型
			String year = Calendar.getInstance().get(Calendar.YEAR)+"";
			String reserveProName = (String)device.get("reserveProName");
			Pattern pattern=Pattern.compile(".*?([0-9]{4}).*?");
			Matcher matcher = pattern.matcher(reserveProName);
			System.out.println(matcher.matches());
			if(matcher.matches()){
				year = (matcher.group(1));
			}
			paramsN.put("PROJECT_TYPE", year+"全年改造");
		}
			
		
		
		// 验收通过
		int rows = remouldBatchMongoDao.reserveConfirm(paramsN);
		return rows;
	}

	public int updateRemouldBatchDevice(Map<String, Object> params) {
		
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("companyCode", params.get("companyCode"));
		valueMap.put("proNo", params.get("proNo"));
		valueMap.put("mctCode", params.get("mctCode"));
		valueMap.put("deviceObjName", params.get("deviceObjName"));
		valueMap.put("qrCode", params.get("qrCode"));
		valueMap.put("problemCode", params.get("problemCode"));
		valueMap.put("problemName", params.get("problemName"));
		valueMap.put("healthCode", params.get("healthCode"));
		valueMap.put("healthName", params.get("healthName"));
		valueMap.put("remarks", params.get("remarks"));
		valueMap.put("proName", params.get("proName"));
		valueMap.put("deviceObjCode", params.get("deviceObjCode"));
		valueMap.put("deviceTypeCode", params.get("deviceTypeCode"));
		valueMap.put("mctViewCode", params.get("mctViewCode"));
		valueMap.put("reserveProNo", params.get("reserveProNo"));
		valueMap.put("remouldBatchCode", params.get("remouldBatchCode"));
		if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
			
			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {
				
				String key = it.next();
				valueMap.put(key, attrMap.get(key));
			}
		}
		
		String valueJson = JSONObject.toJSONString(valueMap);
		int icount = remouldBatchMongoDao.updateRemouldBatchDevice(valueJson);
		
		valueMap.remove("remouldBatchCode");
		// Remove materiel don't update materiel Fields 
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("companyCode", params.get("companyCode"));
		params2.put("mctCode", params.get("mctCode"));
		List<Map<String, Object>> deviceAttr = deviceService.getMctDeviceAttr(params2);
		for (Iterator iterator = deviceAttr.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			try{
				if("1".equals(map.get("dimPeBsMaterial").toString())){
					valueMap.remove(map.get("ename"));
				}
			}catch(Exception e){}
		}// TODO TEST==================
		valueJson = JSONObject.toJSONString(valueMap);
		icount = this.proDeviceMongoDao.updateRMctDevice(valueJson);
		//if (icount > 0) {
		//	icount = this.proDeviceMongoDao.updateReserveProDevice(valueJson);
		//}
		
		//更新原数据
		valueMap.remove("mctViewCode");
		valueMap.remove("reserveProNo");
		valueJson = JSONObject.toJSONString(valueMap);
		this.proDeviceMongoDao.updateDevice(valueJson);
		
		return icount;
	}

	public int updateRemouldMaterial(Map map) {
		return remouldBatchMongoDao.updateRemouldMaterial(map);
	}
}
