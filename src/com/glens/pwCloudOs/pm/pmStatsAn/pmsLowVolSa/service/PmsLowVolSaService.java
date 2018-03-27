/**
 * @Title: PmsLowVolSaService.java
 * @Package com.glens.pwCloudOs.pm.pmStatsAn.pmsLowVolSa.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-11-8 下午5:24:28
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.pmStatsAn.pmsLowVolSa.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.pmStatsAn.pmsLowVolSa.dao.PmsLowVolSaDao;
import com.glens.pwCloudOs.pm.pmStatsAn.pmsLowVolSa.dao.PmsLowVolSaMongoDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class PmsLowVolSaService extends EAPAbstractService {

	private static DecimalFormat G_NUMBER_FORMAT = new DecimalFormat("#.00");
	
	private static DecimalFormat G_NUMBER_FORMAT_THREE = new DecimalFormat("#.000");
	
	private PmsLowVolSaMongoDao pmsLowVolSaMongoDao;
	
	public List<Map<String, Object>> pmMemberWorkloadStatsAn(Map<String, String> params) {
		
		String companyCode = params.get("companyCode");
		String proNo = params.get("proNo");
		String fromDate = params.get("fromDate");
		String toDate = params.get("toDate");
		String[] proNos = null;
		if (proNo == null || "".equals(proNo)) {
			
			PmsLowVolSaDao pmsLowVolSaDao = (PmsLowVolSaDao) getDao();
			List<Map<String, String>> proList = pmsLowVolSaDao.getActivePro(params);
			if (proList != null && proList.size() > 0) {
				
				proNos = new String[proList.size()];
				for (int i = 0;i < proList.size();i++) {
					
					Map<String, String> proItem = proList.get(i);
					proNos[i] = proItem.get("proNo");
				}
			}
		}
		else {
			
			proNos = proNo.split(",");
		}
		
		List<Map<String, Object>> resultList = pmsLowVolSaMongoDao.pmMemberWorkloadStatsAn(companyCode, fromDate, toDate, proNos);
		if (resultList != null && resultList.size() > 0) {
			
			List<Map<String, Object>> memberAccumulateWorkloadList = pmsLowVolSaMongoDao.getMemberAccumulateWorkload(companyCode, toDate, proNos);
			for (Map<String, Object> item : resultList) {
				
				getMemberAccWorkload(item, memberAccumulateWorkloadList);
			}
		}
		
		return resultList;
	}
	
	public List<Map<String, Object>> getPmProWorkloadStatsAn(Map<String, String> params) {
		
		List<Map<String, Object>> proWorkloadStatsAnList = null;
		String companyCode = params.get("companyCode");
		String proNos = params.get("proNo");
		String fromDate = params.get("fromDate");
		String toDate = params.get("toDate");
		if (proNos == null || "".equals(proNos)) {
			
			List<String> proNoList = this.pmsLowVolSaMongoDao.getPmsLowVolPros(companyCode);
			proNos = StringUtil.join(proNoList.toArray(), ",");
		}
		
		proWorkloadStatsAnList = getProWorkloadStatsAn(companyCode, fromDate, toDate, proNos);
		if (proWorkloadStatsAnList != null && proWorkloadStatsAnList.size() > 0) {
			
			if (fromDate.equals(toDate)) {
				
				try {
					
					String preDate = DateTimeUtil.getPreDate(DateTimeUtil.getDateFormat(fromDate, DateTimeUtil.FORMAT_2), -1);
					List<Map<String, Object>> preWorkloadStatsAnList = getProWorkloadStatsAn(companyCode, preDate, preDate, proNos);
					for (Map<String, Object> proItem : proWorkloadStatsAnList) {
						
						String proNo = (String) proItem.get("proNo");
						float avgeFficiency = Float.parseFloat(proItem.get("avgeFficiency").toString());
						float riseFficiency = getRiseFficiency(proNo, avgeFficiency, preWorkloadStatsAnList);
						proItem.put("riseFficiency", riseFficiency);
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
			}
			else {
				
				for (Map<String, Object> proItem : proWorkloadStatsAnList) {
					
					proItem.put("riseFficiency", 0);
				}
			}
			
		}
		
		return proWorkloadStatsAnList;
	}
	
	public List<Map<String, Object>> getProWorkloadStatsAn(String companyCode, String fromDate, String toDate, String proNos) {
		
		PmsLowVolSaDao pmsLowVolSaDao = (PmsLowVolSaDao) getDao();
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyCode", companyCode);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("proNo", proNos);
		List<Map<String, Object>> proList = pmsLowVolSaDao.getProList(params);
		if (proList != null && proList.size() > 0) {
			
			//总工作量
			List<Map<String, Object>> proTotalWorkloadList = this.pmsLowVolSaMongoDao.getProDmrdCount(companyCode, proNos.split(","));
			
			//累积完成量
			List<Map<String, Object>> proAccumulateWorkloadList = this.pmsLowVolSaMongoDao.getTaskDmrdCount(companyCode, proNos.split(","), toDate);
			
			//人员投入
			List<Map<String, Object>> proStuffCountList = this.pmsLowVolSaMongoDao.pmProStuffCount(companyCode, fromDate, toDate, proNos.split(","));
			
			//当期情况
			List<Map<String, Object>> workloadList = this.pmsLowVolSaMongoDao.pmProWorkloadStatsAn(companyCode, fromDate, 
					toDate, proNos.split(","));
			
			for (Map<String, Object> proItem : proList) {
				
				//总工作量设置
				proTotalWorkload(proItem, proTotalWorkloadList);
				
				//累积完成量设置
				proAccumulateWorkload(proItem, proAccumulateWorkloadList);
				
				//人员投入设置
				proStuffCount(proItem, proStuffCountList);
				
				proWorkload(proItem, workloadList);
			}
		}
		
		return proList;
	}
	
	private void proAccumulateWorkload(Map<String, Object> proItem, List<Map<String, Object>> proAccumulateWorkloadList) {
		
		int icount = 0;
		if (proAccumulateWorkloadList != null && proAccumulateWorkloadList.size() > 0) {
			
			for (Map<String, Object> workloadItem : proAccumulateWorkloadList) {
				
				if (proItem.get("proNo").equals(workloadItem.get("proNo"))) {
					
					icount++;
					
				}
			}
		}
		
		proItem.put("allAccumulativeWordload", icount);
		
		//完成率
		float allAccumulativeProgress = 0;
		float total = Float.parseFloat(proItem.get("totalWorkload").toString());
		if (total > 0) {
			
			allAccumulativeProgress = Float.parseFloat(G_NUMBER_FORMAT_THREE.format(icount / total));
		}
		
		proItem.put("allAccumulativeProgress", allAccumulativeProgress);
		
	}
	
	private void proTotalWorkload(Map<String, Object> proItem, List<Map<String, Object>> proTotalWorkloadList) {
		
		if (proTotalWorkloadList != null && proTotalWorkloadList.size() > 0) {
			
			for (Map<String, Object> workloadItem : proTotalWorkloadList) {
				
				if (proItem.get("proNo").equals(workloadItem.get("proNo"))) {
					
					proItem.put("totalWorkload", workloadItem.get("dmrdCount"));
					
					return;
				}
			}
		}
		
		proItem.put("totalWorkload", 0);
	}
	
	private void proStuffCount(Map<String, Object> proItem, List<Map<String, Object>> proStuffCountList) {
		
		int icount = 0;
		if (proStuffCountList != null && proStuffCountList.size() > 0) {
			
			for (Map<String, Object> stuffItem : proStuffCountList) {
				
				if (proItem.get("proNo").equals(stuffItem.get("proNo"))) {
					
					icount++;
					
				}
			}
		}
		
		proItem.put("personCount", icount);
	}
	
	private void proWorkload(Map<String, Object> proItem, List<Map<String, Object>> workloadList) {
		
		if (workloadList != null && workloadList.size() > 0) {
			
			proItem.put("dmrdCount", 0);
			proItem.put("avgeFficiency", 0);
			
			for (Map<String, Object> workloadItem : workloadList) {
				
				if (proItem.get("proNo").equals(workloadItem.get("proNo"))) {
					
					long personCount = Long.parseLong(proItem.get("personCount").toString());
					double dmrdCount = (Double) workloadItem.get("dmrdCount");
					float avgeFficiency = 0;
					if (personCount > 0) {
						
						avgeFficiency = Float.parseFloat(G_NUMBER_FORMAT.format(dmrdCount / personCount));
					}
					
					proItem.put("avgeFficiency", avgeFficiency);
					proItem.put("dmrdCount", dmrdCount);
					proItem.put("dmrdNewCount", workloadItem.get("dmrdNewCount"));
					proItem.put("dmrdMrCount", workloadItem.get("dmrdMrCount"));
				}
			}
		}
		else {
			
			proItem.put("dmrdCount", 0);
			proItem.put("avgeFficiency", 0);
		}
	}
	
	private float getRiseFficiency(String proNo, float avgeFficiency, List<Map<String, Object>> preWorkloadList) {
		
		float riseFficiency = 0;
		if (preWorkloadList != null && preWorkloadList.size() > 0) {
			
			for (Map<String, Object> workloadItem : preWorkloadList) {
				
				if (proNo.equals(workloadItem.get("proNo"))) {
					
					float preAvgeFficiency = Float.parseFloat(workloadItem.get("avgeFficiency").toString());
					riseFficiency = avgeFficiency - preAvgeFficiency;
					
					break;
				}
			}
		}
		
		return riseFficiency;
	}
	
	private void getMemberAccWorkload(Map<String, Object> memberWorkItem, List<Map<String, Object>> memberAccumulateWorkloadList) {
		
		if (memberAccumulateWorkloadList != null && memberAccumulateWorkloadList.size() > 0) {
			
			for (Map<String, Object> accmulateWorkloadItem : memberAccumulateWorkloadList) {
				
				if (memberWorkItem.get("proNo").equals(accmulateWorkloadItem.get("proNo")) 
						&& memberWorkItem.get("WORKER").equals(accmulateWorkloadItem.get("WORKER"))) {
					
					memberWorkItem.put("total", accmulateWorkloadItem.get("total"));
					
					return;
				}
			}
		}
		
		memberWorkItem.put("total", 0);
	}

	public PmsLowVolSaMongoDao getPmsLowVolSaMongoDao() {
		return pmsLowVolSaMongoDao;
	}

	public void setPmsLowVolSaMongoDao(PmsLowVolSaMongoDao pmsLowVolSaMongoDao) {
		this.pmsLowVolSaMongoDao = pmsLowVolSaMongoDao;
	}
	
}
