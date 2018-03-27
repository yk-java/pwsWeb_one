/**
 * @Title: AssessmentStatisticsService.java
 * @Package com.glens.pwCloudOs.pm.assessment.AssessmentStatistics.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-1-10 上午11:10:35
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.assessment.AssessmentStatistics.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.assessment.AssessmentStatistics.dao.AssessmentStatisticsDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class AssessmentStatisticsService extends EAPAbstractService {

	public List<Map<String, String>> selectCheckCategory(Map<String, Object> params) {
		
		AssessmentStatisticsDao assessmentDao = (AssessmentStatisticsDao) getDao();
		
		return assessmentDao.selectCheckCategory(params);
	}
	
	public List<Map<String, String>> selectCheckCycle() {
		
		AssessmentStatisticsDao assessmentDao = (AssessmentStatisticsDao) getDao();
		
		return assessmentDao.selectCheckCycle();
	}
	
	public List<Map<String, String>> getActiveKpi(Map<String, Object> params) {
		
		AssessmentStatisticsDao assessmentDao = (AssessmentStatisticsDao) getDao();
		
		return assessmentDao.getActiveKpi(params);
	}
	
	public Map<String, Object> getAssessmentStatistics(Map<String, Object> params) {
		
		Map<String, Object> statisticsResult = new HashMap<String, Object>();
		AssessmentStatisticsDao assessmentDao = (AssessmentStatisticsDao) getDao();
		//获取指标
		List<Map<String, String>> kpiList = getActiveKpi(params);
		if (kpiList != null && kpiList.size() > 0) {
			
			StringBuilder sqlSeqBuilder = new StringBuilder();
			for (Map<String, String> kpiItem : kpiList) {
				
				sqlSeqBuilder.append("SUM(CASE WHEN b.KPI_CODE='");
				sqlSeqBuilder.append(kpiItem.get("kpiCode"));
				sqlSeqBuilder.append("' THEN ROUND(b.CHECK_SCORE,2) ELSE 0 END) ");
				sqlSeqBuilder.append(kpiItem.get("kpiCode"));
				sqlSeqBuilder.append(",");
			}
			
			params.put("sqlSeg", sqlSeqBuilder.toString());
			List<Map<String, Object>> assessmentList = assessmentDao.getAssessmentStatistics(params);
			statisticsResult.put("kpiList", kpiList);
			statisticsResult.put("assessmentList", assessmentList);
		}
		
		return statisticsResult;
	}
	
	public List<Map<String, Object>> getPmAssessment(Map<String, Object> params) {
		
		AssessmentStatisticsDao assessmentDao = (AssessmentStatisticsDao) getDao();
		
		return assessmentDao.getPmAssessment(params);
	}
	
	public List<Map<String, Object>> selectKpiScore(Map<String, Object> params) {
		
		AssessmentStatisticsDao assessmentDao = (AssessmentStatisticsDao) getDao();
		
		return assessmentDao.selectKpiScore(params);
	}
	
}
