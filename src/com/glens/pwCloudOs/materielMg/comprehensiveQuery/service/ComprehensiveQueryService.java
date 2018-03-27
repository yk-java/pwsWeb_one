/**
 * @Title: ComprehensiveQueryService.java
 * @Package com.glens.pwCloudOs.materielMg.comprehensiveQuery.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-29 下午4:43:37
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.comprehensiveQuery.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.materielMg.comprehensiveQuery.dao.ComprehensiveQueryDao;
import com.glens.pwCloudOs.materielMg.comprehensiveQuery.vo.DormProVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class ComprehensiveQueryService extends EAPAbstractService {

	private DecimalFormat decimalFormat=new DecimalFormat(".00");
	
	public List<Map<String, Object>> vehicleOilConsumptionStatistics(Map<String, String> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.vehicleOilConsumptionStatistics(params);
	}
	
	public List<Map<String, Object>> selectVehicleFuelCharge(Map<String, String> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		List<Map<String, Object>> vehicleFuelChargeList = queryDao.selectVehicleFuelCharge(params);
		if (vehicleFuelChargeList != null && vehicleFuelChargeList.size() > 0) {
			
			for (int i = 0;i < vehicleFuelChargeList.size();i++) {
				
				if (i == 0) {
					
					vehicleFuelChargeList.get(i).put("avgFuel", "");
					vehicleFuelChargeList.get(i).put("avgMiles", "");
				}
				else {
					
					Map<String, Object> preFuelCharge = vehicleFuelChargeList.get(i - 1);
					Map<String, Object> fuelCharge = vehicleFuelChargeList.get(i);
					float avgFuel = Float.parseFloat(decimalFormat.format(((Integer) preFuelCharge.get("fcAmount") * 1.0f) 
							/ (Integer) fuelCharge.get("driverMileage")));
					float avgMiles = Float.parseFloat(decimalFormat.format(((Float) preFuelCharge.get("fcVolume")) 
							/ ((Integer) fuelCharge.get("driverMileage") * 1.0f / 100)));
					
					fuelCharge.put("avgFuel", avgFuel);
					fuelCharge.put("avgMiles", avgMiles);
				}
			}
			
			Collections.reverse(vehicleFuelChargeList);
		}
		
		return vehicleFuelChargeList;
	}
	
	public List<Map<String, Object>> selectAssetRepair(Map<String, String> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.selectAssetRepair(params);
	}
	
	public List<Map<String, Object>> selectAssetStorage(Map<String, String> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.selectAssetStorage(params);
	}
	
	public List<Map<String, Object>> materialStorage(Map<String, String> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.materialStorage(params);
	}
	
	
	public List<Map<String, Object>> selectAssetRent(Map<String, String> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.selectAssetRent(params);
	}
	
	public List<Map<String, Object>> selectAssetRentReturn(Map<String, String> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.selectAssetRentReturn(params);
	}
	
	public List<Map<String, Object>> selectAssetScrap(Map<String, String> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.selectAssetScrap(params);
	}
	
	public PageBean selectAssetRentalCost(Map<String, String> params,int currentPage,int perpage) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.queryForPage(params, currentPage, perpage);
	}
	
	public List<Map<String, Object>> exportAssetRentalCost(Map<String, String> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.exportAssetRentalCost(params);
	}
	
	
	public List<Map<String, Object>> selectRentDorm(Map<String, String> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		//获取在租的宿舍列表
		List<Map<String, Object>> dormList = queryDao.selectRentDorm(params);
		List<Map<String, Object>> dormResultList = new ArrayList<Map<String,Object>>();
		if (dormList != null && dormList.size() > 0) {
			
			//获取在租的床位列表
			List<Map<String, Object>> dormBedList = queryDao.selectRentDormBed(params);
			if (dormBedList != null && dormBedList.size() > 0) {
				
				//遍历宿舍
				for (Map<String, Object> dorm : dormList) {
					
					double dormRentAmount = 0.0;
					//获取宿舍下面的在租床位
					List<Map<String, Object>> bedList = getRentDormBedByDormCode((String) dorm.get("dormCode"), dormBedList);
					List<DormProVo> dormProList = new ArrayList<DormProVo>();
					dorm.put("dormProList", dormProList);
					if (bedList != null && bedList.size() > 0) {
						
						//有在租床位，该宿舍在租
						dorm.put("status", 2);
						Map<String, DormProVo> cache = new HashMap<String, DormProVo>();
						for (Map<String, Object> bed : bedList) {
							
							if (cache.containsKey(bed.get("proNo"))) {
								
								DormProVo dormProVo = cache.get(bed.get("proNo"));
								dormProVo.addMember((String) bed.get("employeeName"));
								dormProVo.addRentAmount((Double) bed.get("rentalCost"));
							}
							else {
								
								DormProVo dormProVo = new DormProVo();
								dormProVo.setProName((String) bed.get("proName"));
								dormProVo.setProNo((String) bed.get("proNo"));
								dormProVo.addMember((String) bed.get("employeeName"));
								dormProVo.addRentAmount((Double) bed.get("rentalCost"));
								cache.put((String) bed.get("proNo"), dormProVo);
								dormProList.add(dormProVo);
							}
							
							dormRentAmount += (Double) bed.get("rentalCost");
						}
						
						dorm.put("dormRentAmount", dormRentAmount);
						
						dormResultList.add(dorm);
					}
				}
				
				//排序
//				Collections.sort(dormList, new Comparator<Map<String, Object>>() {
//					
//					@Override
//					public int compare(Map<String, Object> o1,
//							Map<String, Object> o2) {
//						// TODO Auto-generated method stub
//						int status=o1.get("status");
//						
//						int status2=
//								
//								1>2  return -1;
//								==  0
//										>1
//						
//						return 0;
//					}
//				});
			}
		}
		
		return dormResultList;
	}
	
	public List<Map<String, Object>> queryAssetDistributing(Map<String, String> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.queryAssetDistributing(params);
	}
	
	public List<Map<String, Object>> selectDistributeAsset(Map<String, Object> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.selectDistributeAsset(params);
	}
	
	public PageBean selectProAssetCostForPage(Map<String, Object> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.selectProAssetCostForPage(params);
	}
	
	public List<Map<String, Object>> selectProAssetCostForList(Map<String, Object> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.selectProAssetCostForList(params);
	}
	
	public List<Map<String, Object>> selectProAssetList(Map<String, Object> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.selectProAssetList(params);
	}
	
	public PageBean selectProDormCostForPage(Map<String, Object> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.selectProDormCostForPage(params);
	}
	
	public List<Map<String, Object>> selectProDormList(Map<String, Object> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.selectProDormList(params);
	}
	
	public List<Map<String, Object>> selectProDormCostForList(Map<String, Object> params) {
		
		ComprehensiveQueryDao queryDao = (ComprehensiveQueryDao) getDao();
		
		return queryDao.selectProDormCostForList(params);
	}
	
	private List<Map<String, Object>> getRentDormBedByDormCode(String dormCode, List<Map<String, Object>> dormBedList) {
		
		List<Map<String, Object>> bedList = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> bed : dormBedList) {
			
			if (bed.get("dormCode").equals(dormCode)) {
				
				bedList.add(bed);
			}
		}
		
		return bedList;
	}
	
}
