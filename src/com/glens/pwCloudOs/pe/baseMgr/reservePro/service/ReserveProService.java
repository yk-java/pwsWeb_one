package com.glens.pwCloudOs.pe.baseMgr.reservePro.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pe.baseMgr.reservePro.dao.ReserveProDao;
import com.glens.pwCloudOs.pe.baseMgr.reservePro.vo.ReserveProVo;
import com.glens.pwCloudOs.pe.proReserve.proDevice.dao.ProDeviceMongoDao;
import com.mongodb.BasicDBList;

public class ReserveProService extends EAPAbstractService {
	
	private static Log logger = LogFactory.getLog(ReserveProService.class);
	
	private ProDeviceMongoDao proDeviceMongoDao;
	
	public List<Map<String, Object>> getMctView(String companyCode) {
		
		ReserveProDao queryDao = (ReserveProDao) getDao();
		Map<String, Object> map=new HashMap<String,Object>();
		//map.put("proNo", mctCode);
		map.put("companyCode", companyCode);
		return queryDao.getMctView(map);
	}
	
	public List<Map<String, Object>> getAuditstate(String companyCode) {
		
		ReserveProDao queryDao = (ReserveProDao) getDao();
		Map<String, Object> map=new HashMap<String,Object>();
		//map.put("proNo", mctCode);
		map.put("companyCode", companyCode);
		return queryDao.getAuditstate(map);
	}
	
	/**
	
	  * <p>Title: queryForPage</p>
	
	  * <p>Description: </p>
	
	  * @param parameters
	  * @param currentPage
	  * @param perpage
	  * @return
	
	  * @see com.glens.eap.platform.framework.service.impl.EAPAbstractService#queryForPage(java.util.Map, int, int)
	
	  **/
	
	
	@Override
	public PageBean queryForPage(Map parameters, int currentPage, int perpage) {
		// TODO Auto-generated method stub
		
		PageBean page = super.queryForPage(parameters, currentPage, perpage);
		if (page != null && page.getList() != null && page.getList().size() > 0) {
			
			Map<String, String> params = new HashMap<String, String>();
			
			for (int i = 0;i < page.getList().size();i++) {
				
				ReserveProVo vo = (ReserveProVo) page.getList().get(i);
				params.put("reserveProNo", vo.getReserveProNo());
				params.put("companyCode", vo.getCompanyCode());
				
				List<Map<String, Object>> proDeviceList = this.proDeviceMongoDao.getProDeviceList(JSONObject.toJSONString(params), "");
				int meterBoxCount = 0;
				int houseHold = 0;
				if (proDeviceList != null && proDeviceList.size() > 0) {
					
					meterBoxCount = proDeviceList.size();
					for (Map<String, Object> proDeviceMap : proDeviceList) {
						
						if (proDeviceMap.get("HOUSE_HOLD") != null 
								&& !"".equals(proDeviceMap.get("HOUSE_HOLD"))
								&& StringUtil.isNum(proDeviceMap.get("HOUSE_HOLD").toString())) {
							
							houseHold += Integer.parseInt(proDeviceMap.get("HOUSE_HOLD").toString());
						}
					}
				}
				
				vo.setMeterBoxCount(meterBoxCount);
				vo.setHouseHold(houseHold);
			}
		}
		
		return page;
	}
	
	public List<Map<String, Object>> getReserveStatistics(ReserveProVo params) {
		
		ReserveProDao queryDao = (ReserveProDao) getDao();
		List<Map<String, String>> deviceMaterialAttrList = queryDao.getDeviceMaterialAttr(params.getCompanyCode(), params.getMctViewCode());
		List<Map<String, Object>> proDeviceStatisticsList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> proDeviceList = this.proDeviceMongoDao.censusProDevice(params.getCompanyCode(), 
				params.getReserveProNo(), params.getRpAuditStateCode(), deviceMaterialAttrList);
		if (proDeviceList != null && proDeviceList.size() > 0) {
			
			Map<String, Object> totalItem = new HashMap<String, Object>();
			totalItem.put("REFORM_PROGRAM", "总计");
//			int totalAuditMeterBoxCount = 0;
//			int totalUnAuditMeterBoxCount = 0;
//			int totalReturnMeterBoxCount = 0;
			int totalHouseHold = 0;
			int totalEstateCount = 0;
			int totalUnitCount = 0;
			List<Map<String, Object>> totalMaterialCountList = new ArrayList<Map<String,Object>>();
			
//			List<Map<String, Object>> auditDeviceMaterialStatisticsList = this.proDeviceMongoDao.proDeviceAuditMaterialCountStatistics(params.getCompanyCode(), 
//				params.getReserveProNo());
			
			for (Map<String, Object> proDevice : proDeviceList) {
				
				Map<String, Object> statisticsItem = new HashMap<String, Object>();
				statisticsItem.put("REFORM_PROGRAM", proDevice.get("REFORM_PROGRAM"));
				statisticsItem.put("houseHold", proDevice.get("houseHold"));
				statisticsItem.put("estateCount", proDevice.get("estateCount"));
				statisticsItem.put("unitCount", proDevice.get("unitCount"));
				
				//总计
				totalHouseHold += Float.parseFloat(proDevice.get("houseHold").toString());
				totalEstateCount += Float.parseFloat(proDevice.get("estateCount").toString());
				totalUnitCount += Float.parseFloat(proDevice.get("unitCount").toString());
				
				List<Map<String, Object>> materialCountList = new ArrayList<Map<String,Object>>();
				statisticsItem.put("materialList", materialCountList);
				for (int i = 0;i < deviceMaterialAttrList.size();i++) {
					
					Map<String, String> deviceMaterialAttr = deviceMaterialAttrList.get(i);
					
					Map<String, Object> materialCountItem = new HashMap<String, Object>();
					materialCountItem.put("name", deviceMaterialAttr.get("cname"));
					materialCountItem.put("v1", proDevice.get(deviceMaterialAttr.get("ename")));
					//materialCountItem.put("v2", getDeviceMaterialCount(deviceMaterialAttr.get("cname"), auditDeviceMaterialStatisticsList));
					materialCountList.add(materialCountItem);
					
					if (totalMaterialCountList.size() <= i) {
						
						Map<String, Object> totalMaterialCountItem = new HashMap<String, Object>();
						totalMaterialCountItem.put("name", deviceMaterialAttr.get("cname"));
						totalMaterialCountItem.put("v1", materialCountItem.get("v1"));
						//totalMaterialCountItem.put("v2", materialCountItem.get("v2"));
						
						totalMaterialCountList.add(totalMaterialCountItem);
					}
					else {
						
						Map<String, Object> totalMaterialCountItem = totalMaterialCountList.get(i);
						if (totalMaterialCountItem != null) {
							
							Float v1 = Float.parseFloat(totalMaterialCountItem.get("v1").toString()) 
									+ Float.parseFloat(materialCountItem.get("v1").toString());
//							Float v2 = Float.parseFloat(totalMaterialCountItem.get("v2").toString()) 
//									+ Float.parseFloat(materialCountItem.get("v2").toString());
							totalMaterialCountItem.put("v1", v1);
							//totalMaterialCountItem.put("v2", v2);
						}
					}
				}
				
				proDeviceStatisticsList.add(statisticsItem);
			}
			
//			totalItem.put("auditMeterBoxCount", totalAuditMeterBoxCount);
//			totalItem.put("unAuditMeterBoxCount", totalUnAuditMeterBoxCount);
//			totalItem.put("returnMeterBoxCount", totalReturnMeterBoxCount);
			totalItem.put("houseHold", totalHouseHold);
			totalItem.put("estateCount", totalEstateCount);
			totalItem.put("unitCount", totalUnitCount);
			totalItem.put("materialList", totalMaterialCountList);
			proDeviceStatisticsList.add(totalItem);
		}
		
		return proDeviceStatisticsList;
	}
	
	/**
	
	  * <p>Title: delete</p>
	
	  * <p>Description: </p>
	
	  * @param parameters
	  * @return
	
	  * @see com.glens.eap.platform.framework.service.impl.EAPAbstractService#delete(java.lang.Object)
	
	  **/
	
	
	@Override
	public int delete(Object parameters) {
		// TODO Auto-generated method stub
		
		Map<String, Object> params = new HashMap<String, Object>();
		ReserveProVo vo = (ReserveProVo) parameters;
		params.put("companyCode", vo.getCompanyCode());
		params.put("reserveProNo", vo.getReserveProNo());
		int icount = super.delete(parameters);
		if (icount > 0) {
			
			logger.info("删除了储备项目：" + params.get("companyCode") + "，" + params.get("reserveProNo"));
			
			List<Map<String, Object>> proDeviceList = this.proDeviceMongoDao.getProDeviceList(JSONObject.toJSONString(params), "");
			
			int ic = this.proDeviceMongoDao.deleteReserveProDevice(params);
			if (ic > 0) {
				
				logger.info("删除了储备项目：" + params.get("companyCode") + "，" + params.get("reserveProNo") + "下面的设备");
				
				ic = this.proDeviceMongoDao.deleteReserveProDeviceMaterial(params);
				if (ic > 0) {
					
					logger.info("删除了储备项目：" + params.get("companyCode") + "，" + params.get("reserveProNo") + "下面的材料");
				}
				else {
					
					logger.info("删除储备项目：" + params.get("companyCode") + "，" + params.get("reserveProNo") + "下面的材料失败");
				}
				
				//修改储备总表的状态，释放设备
				if (proDeviceList != null && proDeviceList.size() > 0) {
					
					BasicDBList deviceObjCodes =  new BasicDBList();
					for (Map<String, Object> device : proDeviceList) {
						
						deviceObjCodes.add(device.get("deviceObjCode"));
					}
					
					ic = this.proDeviceMongoDao.batchReleaseRMctDevices(vo.getMctViewCode(), deviceObjCodes);
					if (ic > 0) {
						
						logger.info("释放了设备：" + deviceObjCodes);
					}
					else {
						
						logger.info("释放设备：" + deviceObjCodes + "失败");
					}
				}
			}
			else {
				
				logger.info("删除储备项目：" + params.get("companyCode") + "，" + params.get("reserveProNo") + "下面的设备失败");
			}
		}
		else {
			
			logger.info("删除储备项目：" + params.get("companyCode") + "，" + params.get("reserveProNo") + "失败");
		}
		
		return icount;
	}
	
	private Float getDeviceMaterialCount(String material, List<Map<String, Object>> deviceMaterialStatisticsList) {
		
		Float icount = 0.0f;
		if (deviceMaterialStatisticsList != null && deviceMaterialStatisticsList.size() > 0) {
			
			for (Map<String, Object> deviceMaterialStatisticsItem : deviceMaterialStatisticsList) {
				
				if (material.equals(deviceMaterialStatisticsItem.get("materialTypeCode"))) {
					
					icount = Float.parseFloat(deviceMaterialStatisticsItem.get("needCount").toString());
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
	
}
