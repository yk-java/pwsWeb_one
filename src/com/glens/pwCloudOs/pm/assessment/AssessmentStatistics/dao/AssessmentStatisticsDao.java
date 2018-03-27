/**
 * @Title: AssessmentStatisticsDao.java
 * @Package com.glens.pwCloudOs.pm.assessment.AssessmentStatistics.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-1-10 上午10:26:12
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.assessment.AssessmentStatistics.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

/**
 * 考核测评统计数据处理类
 * 
 * @author hgf
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.assessment.AssessmentStatistics.AssessmentStatisticsMapper")
public class AssessmentStatisticsDao extends EAPAbstractDao {

	
	public List<Map<String, String>> selectCheckCategory(Map<String, Object> params) {
		
		List<Map<String, String>> categoryList = this.getSqlSession().selectList(this.getSqlStatement("selectCheckCategory"), params);
		
		return categoryList;
	}
	
	public List<Map<String, String>> selectCheckCycle() {
		
		List<Map<String, String>> cycleList = this.getSqlSession().selectList(this.getSqlStatement("selectCheckCycle"));
		
		return cycleList;
	}
	
	public List<Map<String, String>> getActiveKpi(Map<String, Object> params) {
		
		List<Map<String, String>> kpiList = this.getSqlSession().selectList(this.getSqlStatement("getActiveKpi"), params);
		
		return kpiList;
	}
	
	public List<Map<String, Object>> getAssessmentStatistics(Map<String, Object> params) {
		
		List<Map<String, Object>> assessmentList = this.getSqlSession().selectList(this.getSqlStatement("getAssessmentStatistics"), params);
		
		return assessmentList;
	}
	
	public List<Map<String, Object>> getPmAssessment(Map<String, Object> params) {
		
		List<Map<String, Object>> assessmentList = this.getSqlSession().selectList(this.getSqlStatement("getPmAssessment"), params);
		
		return assessmentList;
	}
	
	public List<Map<String, Object>> selectKpiScore(Map<String, Object> params) {
		
		List<Map<String, Object>> assessmentList = this.getSqlSession().selectList(this.getSqlStatement("selectKpiScore"), params);
		
		return assessmentList;
	}
	
}
