/**
 * @Title: ProDeviceService.java
 * @Package com.glens.pwCloudOs.pe.proReserve.proDevice.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-26 下午4:16:48
 * @version V1.0
 */


package com.glens.pwCloudOs.pe.proReserve.proDevice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.om.deviceMgr.device.dao.MctDeviceMongoDao;
import com.glens.pwCloudOs.pe.proReserve.proDevice.dao.ProDeviceDao;
import com.glens.pwCloudOs.pe.proReserve.proDevice.dao.ProDeviceMongoDao;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class ProDeviceService extends EAPAbstractService {

	private static Log logger = LogFactory.getLog(ProDeviceService.class);
	
	private ProDeviceMongoDao proDeviceMongoDao;
	
	private MctDeviceMongoDao mctDeviceMongoDao;
	
	public PageBean getProDevicePage(Map<String, Object> params) {
		
		int currentPage = (Integer) params.get("currentPage");
		int perpage = (Integer) params.get("perpage");
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		queryParams.put("reserveProNo", params.get("reserveProNo"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {
			
			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null && !"".equals(params.get("deviceTypeCode"))) {
			
			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		if (params.get("rpAuditState") != null && !"".equals(params.get("rpAuditState"))) {
			
			queryParams.put("rpAuditState", params.get("rpAuditState"));
		}
		if (params.get("sysProcessFlag") != null && !"".equals(params.get("sysProcessFlag"))) {
			
			queryParams.put("sysProcessFlag", params.get("sysProcessFlag"));
		}
		if (params.get("proNo") != null && !"".equals(params.get("proNo"))) {
			
			queryParams.put("proNo", params.get("proNo"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
			
			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {
				
				String key = it.next();
				if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}
		
		String paramJson = JSONObject.toJSONString(queryParams);
		PageBean page = this.proDeviceMongoDao.getProDevicePage(paramJson, searchName, currentPage, perpage);
		
		return page;
	}
	
public PageBean getProDevicePage1(Map<String, Object> params) {
		
		int currentPage = (Integer) params.get("currentPage");
		int perpage = (Integer) params.get("perpage");
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		queryParams.put("reserveProNo", params.get("reserveProNo"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {
			
			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null && !"".equals(params.get("deviceTypeCode"))) {
			
			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		if (params.get("rpAuditState") != null && !"".equals(params.get("rpAuditState"))) {
			
			queryParams.put("rpAuditState", params.get("rpAuditState"));
		}
		if (params.get("sysProcessFlag") != null && !"".equals(params.get("sysProcessFlag"))) {
			
			queryParams.put("sysProcessFlag", params.get("sysProcessFlag"));
		}
		if (params.get("proNo") != null && !"".equals(params.get("proNo"))) {
			
			queryParams.put("proNo", params.get("proNo"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
			
			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {
				
				String key = it.next();
				if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}
		
		String paramJson = JSONObject.toJSONString(queryParams);
		PageBean page = this.proDeviceMongoDao.getProDevicePage1(paramJson, searchName, currentPage, perpage);
		
		return page;
	}
	
	
	
	public List<Map<String, Object>>  getProDeviceList(Map<String, Object> params) {
		
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		queryParams.put("reserveProNo", params.get("reserveProNo"));
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {
			
			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null && !"".equals(params.get("deviceTypeCode"))) {
			
			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		if (params.get("rpAuditState") != null && !"".equals(params.get("rpAuditState"))) {
			
			queryParams.put("rpAuditState", params.get("rpAuditState"));
		}
		if (params.get("sysProcessFlag") != null && !"".equals(params.get("sysProcessFlag"))) {
			
			queryParams.put("sysProcessFlag", params.get("sysProcessFlag"));
		}
		if (params.get("proNo") != null && !"".equals(params.get("proNo"))) {
			
			queryParams.put("proNo", params.get("proNo"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
			
			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {
				
				String key = it.next();
				if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}
		
		String paramJson = JSONObject.toJSONString(queryParams);
		List<Map<String, Object>> res = this.proDeviceMongoDao.getProDeviceList(paramJson, searchName);
		
		return res;
	}
	
	public List<Map<String, String>> getMctViewPros(Map<String, Object> params) {
		
		ProDeviceDao deviceDao = (ProDeviceDao) getDao();
		Map<String, String> mctProMap = deviceDao.getMctViewPros(params);
		List<Map<String, String>> mctProList = new ArrayList<Map<String,String>>();
		if (mctProMap != null && mctProMap.size() > 0) {
			
			String proNos = mctProMap.get("proNos");
			String proNames = mctProMap.get("proNames");
			String proMctCodes = mctProMap.get("proMctCodes");
			String proMctNames = mctProMap.get("proMctNames");
			if (proNos != null && !"".equals(proNos) 
					&& proNames != null && !"".equals(proNames) 
					&& proMctCodes != null && !"".equals(proMctCodes) 
					&& proMctNames != null && !"".equals(proMctNames)) {
				
				String[] proNo = proNos.split(",");
				String[] proName = proNames.split(",");
				String[] proMctCode = proMctCodes.split(",");
				String[] proMctName = proMctNames.split(",");
				
				for (int i = 0;i < proNo.length;i++) {
					
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
	
	public int batchInsertProDevice(Map<String, Object> params) {
		
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("proNo") != null && !"".equals(params.get("proNo"))) {
			queryParams.put("proNo", params.get("proNo"));
		}
		if (params.get("mctCode") != null && !"".equals(params.get("mctCode"))) {
			
			queryParams.put("mctCode", params.get("mctCode"));
		}
		if (params.get("deviceTypeCode") != null && !"".equals(params.get("deviceTypeCode"))) {
			
			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
			
			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {
				
				String key = it.next();
				if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}
		
		String paramJson = JSONObject.toJSONString(queryParams);
		List<Map> mctDeviceList = this.proDeviceMongoDao.getUnAllocRMctDeviceList(paramJson, searchName);
		int icount = 0;
		if (mctDeviceList != null && mctDeviceList.size() > 0) {
			
			List<DBObject> objList = new ArrayList<DBObject>();
			
			for (Map mctDevice : mctDeviceList) {
				
				BasicDBObject obj = new BasicDBObject(mctDevice);
				if (obj.containsKey("_id")) {
					
					obj.remove("_id");
				}
				obj.put("reserveProNo", params.get("reserveProNo"));
				obj.put("reserveProName", params.get("reserveProName"));
				obj.put("remouldSchemeCode", obj.get("REFORM_PROGRAM"));
				obj.put("rpAuditState", "0");
				obj.put("sysProcessFlag", "1");
				obj.put("mctViewCode", params.get("mctViewCode"));
				
				objList.add(obj);
			}
			
			icount = this.proDeviceMongoDao.batchInsertProDevice(objList);
			this.proDeviceMongoDao.allocRMctDevices(paramJson, searchName);
		}
		
		return icount;
	}
	
	public Map<String, Object> getProDevice(Map<String, Object> params) {
		
		ProDeviceDao deviceDao = (ProDeviceDao) getDao();
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		queryParams.put("reserveProNo", params.get("reserveProNo"));
		queryParams.put("deviceObjCode", params.get("deviceObjCode"));
		String paramJson = JSONObject.toJSONString(queryParams);
		Map<String, Object> device = this.proDeviceMongoDao.getProDevice(paramJson);
		if (device != null) {
			
			List<Map<String, Object>> proDeviceMateriaList = deviceDao.getProDeviceMateriaInfo(device);
			device.put("materiaList", proDeviceMateriaList);
		}
		
		return device;
	}
	
	public boolean auditProDevice(Map<String, Object> params) {
		
		Map<String, Object> proDevice = new HashMap<String, Object>();
		proDevice.put("companyCode", params.get("companyCode"));
		proDevice.put("reserveProNo", params.get("reserveProNo"));
		proDevice.put("deviceObjCode", params.get("deviceObjCode"));
		proDevice.put("auditPersonCode", params.get("auditPersonCode"));
		proDevice.put("auditPersonName", params.get("auditPersonName"));
		proDevice.put("auditDate", DateTimeUtil.getSystemDateString());
		proDevice.put("auditSuggest", params.get("auditSuggest"));
		int icount = this.proDeviceMongoDao.auditProDevice(proDevice);
		if (icount > 0) {
			
//			String materialType = params.get("materialType").toString();
//			String materialTypeCount = params.get("materialTypeCount").toString();
//			String materialTypeAmount = params.get("materialTypeAmount").toString();
//			if (materialType != null && !"".equals(materialType) 
//					&& materialTypeCount != null && !"".equals(materialTypeCount) 
//					&& materialTypeAmount != null && !"".equals(materialTypeAmount)) {
//				
//				String[] materialTypes = materialType.split(",");
//				String[] materialTypeCounts = materialTypeCount.split(",");
//				String[] materialTypeAmounts = materialTypeAmount.split(",");
//				List<DBObject> materialList = new ArrayList<DBObject>();
//				for (int i = 0;i < materialTypes.length;i++) {
//					
//					DBObject materialObj = new BasicDBObject();
//					materialObj.put("companyCode", params.get("companyCode"));
//					materialObj.put("reserveProNo", params.get("reserveProNo"));
//					materialObj.put("deviceObjCode", params.get("deviceObjCode"));
//					materialObj.put("materialTypeCode", materialTypes[i]);
//					materialObj.put("needCount", materialTypeCounts[i]);
//					materialObj.put("needAmount", materialTypeAmounts[i]);
//					materialObj.put("proNo", params.get("proNo"));
//					materialObj.put("mctCode", params.get("mctCode"));
//					
//					materialList.add(materialObj);
//				}
//				
//				this.proDeviceMongoDao.batchAuditProDeviceMaterial(materialList);
//			}
			
			//修改原设备审核状态
			this.proDeviceMongoDao.updateDeviceAuditState(proDevice);
		}
		
		return icount > 0;
	}
	
	public boolean deleteProDevice(Map<String, Object> params) {
		
		Map<String, Object> proDevice = new HashMap<String, Object>();
		proDevice.put("companyCode", params.get("companyCode"));
		proDevice.put("reserveProNo", params.get("reserveProNo"));
		proDevice.put("deviceObjCode", params.get("deviceObjCode"));
		proDevice.put("auditSuggest", params.get("auditSuggest"));
		proDevice.put("auditPersonCode", params.get("auditPersonCode"));
		proDevice.put("auditPersonName", params.get("auditPersonName"));
		proDevice.put("auditDate", DateTimeUtil.getSystemDateString());
		int icount = this.proDeviceMongoDao.deleteProDevice(proDevice);
		
		return icount > 0;
	}
	
	public boolean proDeviceAllPass(Map params) {

		params.put("auditDate", DateTimeUtil.getSystemDateString());
		
		
		List<Map<String,Object>> devices = this.proDeviceMongoDao.auditProDeviceAllBefore(params);
		for (Iterator iterator = devices.iterator(); iterator.hasNext();) {
			Map<String, Object> dev = (Map<String, Object>) iterator.next();
			ProDeviceDao deviceDao = (ProDeviceDao) getDao();
			List<Map<String, Object>> proDeviceMateriaList = deviceDao.getProDeviceMateriaInfo(dev);
			if(proDeviceMateriaList!=null && proDeviceMateriaList.size()>0){
				List<DBObject> materialList = new ArrayList<DBObject>();
				for (Iterator iterator2 = proDeviceMateriaList.iterator(); iterator2
						.hasNext();) {
					DBObject materialObj = new BasicDBObject();
					Map<String, Object> material = (Map<String, Object>) iterator2
							.next();
					materialObj.put("companyCode", dev.get("companyCode"));
					materialObj.put("reserveProNo", dev.get("reserveProNo"));
					materialObj.put("deviceObjCode", dev.get("deviceObjCode"));
					materialObj.put("materialTypeCode", material.get("materialTypeName"));
					materialObj.put("needCount", material.get("needCount"));
					Float amount = Float.parseFloat(material.get("amount").toString());
					Integer needCount = Integer.parseInt(material.get("needCount").toString());
					materialObj.put("needAmount", amount*needCount);
					materialObj.put("proNo", dev.get("proNo"));
					materialObj.put("mctCode", dev.get("mctCode"));
					materialList.add(materialObj);
				}
				this.proDeviceMongoDao.batchAuditProDeviceMaterial(materialList);
			}
		}
		int icount = this.proDeviceMongoDao.auditProDeviceAll(params);
		return icount > 0;
	}
	
	public int deleteRPDevice(Map<String, Object> parameters) {
		
		int icount = this.proDeviceMongoDao.deleteRPDevice(parameters);
		if (icount > 0) {
			
			this.proDeviceMongoDao.deleteRPDeviceMaterial(parameters);
			
			String mctViewCode = (String) parameters.get("mctViewCode");
			String deviceObjCode = (String) parameters.get("deviceObjCode");
			BasicDBList deviceObjCodes =  new BasicDBList();
			deviceObjCodes.add(deviceObjCode);
			if("deep".equals(parameters.get("deleteMode"))){
				this.proDeviceMongoDao.batchDeleteRMctDevices(mctViewCode, deviceObjCodes);// 删除PM_R_MCT_LIST
			} else {
				int ic = this.proDeviceMongoDao.batchReleaseRMctDevices(mctViewCode, deviceObjCodes);
				if (ic > 0) {
					logger.info("释放了设备：" + deviceObjCodes);
				} else {
					logger.info("释放设备：" + deviceObjCodes + "失败");
				}
			}
		}
		
		return icount;
	}
	
	public int batchDeleteRPDevice(Map<String, Object> parameters) {
		
		int icount = this.proDeviceMongoDao.batchDeleteRPDevice(parameters);
		if (icount > 0) {
			
			this.proDeviceMongoDao.batchDeleteRPDeviceMaterial(parameters);
			
			String mctViewCode = (String) parameters.get("mctViewCode");
			String deviceObjCodesStr = (String) parameters.get("deviceObjCodes");
			String[] deviceObjCodesArr =deviceObjCodesStr.split(",");
			BasicDBList deviceObjCodes =  new BasicDBList();
			for (int i = 0; i < deviceObjCodesArr.length; i++) {
				deviceObjCodes.add(deviceObjCodesArr[i]);
			}
			if("deep".equals(parameters.get("deleteMode"))){
				this.proDeviceMongoDao.batchDeleteRMctDevices(mctViewCode, deviceObjCodes);// 删除PM_R_MCT_LIST
			} else {
				int ic = this.proDeviceMongoDao.batchReleaseRMctDevices(mctViewCode, deviceObjCodes);
				if (ic > 0) {
					logger.info("释放了设备：" + deviceObjCodes);
				} else {
					logger.info("释放设备：" + deviceObjCodes + "失败");
				}
			}
		}
		
		return icount;
	}
	
	public int updateReserveProDevice(Map<String, Object> params) {
		
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
		int icount = this.proDeviceMongoDao.updateRMctDevice(valueJson);
		if (icount > 0) {
			
			icount = this.proDeviceMongoDao.updateReserveProDevice(valueJson);
		}
		
		//更新原数据
		valueMap.remove("mctViewCode");
		valueMap.remove("reserveProNo");
		valueJson = JSONObject.toJSONString(valueMap);
		this.proDeviceMongoDao.updateDevice(valueJson);
		
		return icount;
	}
	
	public PageBean getUnAllocRMctDevicePage(Map<String, Object> params) {
		
		int currentPage = (Integer) params.get("currentPage");
		int perpage = (Integer) params.get("perpage");
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("proNo") != null && !"".equals(params.get("proNo"))) {
			queryParams.put("proNo", params.get("proNo"));
		}
		if (params.get("deviceTypeCode") != null && !"".equals(params.get("deviceTypeCode"))) {
			queryParams.put("deviceTypeCode", params.get("deviceTypeCode"));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
			
			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {
				
				String key = it.next();
				if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}
		
		String paramJson = JSONObject.toJSONString(queryParams);
		PageBean page = this.proDeviceMongoDao.getUnAllocRMctDevicePage(paramJson, searchName, currentPage, perpage);
		
		return page;
	}
	
	public PageBean getRejectProDevices(Map<String, Object> params) {
		
		int currentPage = (Integer) params.get("currentPage");
		int perpage = (Integer) params.get("perpage");
		ProDeviceDao deviceDao = (ProDeviceDao) getDao();
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		queryParams.put("sysProcessFlag", "0");
		BasicDBList values = new BasicDBList();
		if (params.get("proNo") != null && !"".equals(params.get("proNo"))) {
			
			values.add(params.get("proNo"));
		}
		else {
			
			if (params.get("accountCode") != null && !"".equals(params.get("accountCode"))) {
				
				List<Map<String, String>> proList = deviceDao.getActivePro(params);
				if (proList != null && proList.size() > 0) {
					
					for (Map<String, String> proItem : proList) {
						
						values.add(proItem.get("proNo"));
					}
				}
			}
		}
		
		if (values.size() > 0) {
			
			queryParams.put("proNo", new BasicDBObject("$in", values));
		}
		Object searchName = params.get("searchName");
		if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {
			
			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {
				
				String key = it.next();
				if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
					continue;
				}
				queryParams.put(key, attrMap.get(key));
			}
		}
		
		String paramJson = JSONObject.toJSONString(queryParams);
		PageBean page = this.proDeviceMongoDao.getProDevicePage(paramJson, searchName, currentPage, perpage);
		
		return page;
	}
	
	public int synRMctDevice(Map<String, Object> params) {
		
		int icount = 0;
		String companyCode = (String) params.get("companyCode");
		String mctCode = (String) params.get("mctCode");
		String mctViewCode = (String) params.get("mctViewCode");
		List<DBObject> rmctDeviceList = this.proDeviceMongoDao.getMctViewDevices(companyCode, mctViewCode);
		
		DBObject query = new BasicDBObject();
		query.put("companyCode", companyCode);
		if (mctCode != null && !"".equals(mctCode)) {
			
			String[] mctCodes = mctCode.split(",");
			BasicDBList mctIn = new BasicDBList();
			for (String mcode : mctCodes) {
				
				mctIn.add(mcode);
			}
			query.put("mctCode", new BasicDBObject("$in", mctIn));
			
			if (rmctDeviceList != null && rmctDeviceList.size() > 0) {
				
				BasicDBList deviceObjCodeIn = new BasicDBList();
				for (DBObject deviceItem : rmctDeviceList) {
					
					deviceObjCodeIn.add(deviceItem.get("deviceObjCode"));
				}
				
				query.put("deviceObjCode", new BasicDBObject("$nin", deviceObjCodeIn));
			}
			
			List<DBObject> ninDeviceList = this.proDeviceMongoDao.getNoInRmctDevices(query);
			if (ninDeviceList != null && ninDeviceList.size() > 0) {
				
				icount = ninDeviceList.size();
				for (DBObject deviceItem : ninDeviceList) {
					
					if (deviceItem.containsField("_id")) {
						
						deviceItem.removeField("_id");
					}
					
					deviceItem.put("ARCHIVE_STATUS", 0);
					deviceItem.put("PRO_STATUS", 0);
					deviceItem.put("mctViewCode", mctViewCode);
					
					this.proDeviceMongoDao.batchInRMct(deviceItem);
				}
				
			}
		}
		
		return icount;
	}

	public ProDeviceMongoDao getProDeviceMongoDao() {
		
		return proDeviceMongoDao;
	}

	public void setProDeviceMongoDao(ProDeviceMongoDao proDeviceMongoDao) {
		this.proDeviceMongoDao = proDeviceMongoDao;
	}

	public MctDeviceMongoDao getMctDeviceMongoDao() {
		return mctDeviceMongoDao;
	}

	public void setMctDeviceMongoDao(MctDeviceMongoDao mctDeviceMongoDao) {
		this.mctDeviceMongoDao = mctDeviceMongoDao;
	}
	
}
