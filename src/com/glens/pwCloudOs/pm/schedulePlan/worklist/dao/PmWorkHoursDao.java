package com.glens.pwCloudOs.pm.schedulePlan.worklist.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.schedulePlan.worklist.dao.PmWorkHoursMapper")
public class PmWorkHoursDao extends EAPAbstractDao {
	
	public float statisticsDayWorkloadHours(Object parameters) {
		try {
			return this.getSqlSession().selectOne(getSqlStatement("statisticsDayWorkloadHours"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("statisticsDayWorkloadHours"), e);
		}
	}
	
	public float statisticsAllWorkloadHours(Object parameters) {
		try {
			return this.getSqlSession().selectOne(getSqlStatement("statisticsAllWorkloadHours"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("statisticsAllWorkloadHours"), e);
		}
	}
	
	public boolean calculateWorkHours(Object parameters) {
		try {
			return this.getSqlSession().update(getSqlStatement("calculateWorkHours"), parameters)>0;
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("calculateWorkHours"), e);
		}
	}
	
	
	public ValueObject findByWorkerAndWorkDate(Object parameter) {
		try {
			return (ValueObject) this.getSqlSession().selectOne(getSqlStatement("findByWorkerAndWorkDate"), parameter);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findByWorkerAndWorkDate"), e);
		}
	}
	
	public List<Map<String, Object>> workHoursStatistics(Map<String, Object> parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("workHoursStatistics"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("workHoursStatistics"), e);
		}
	}
	
	public List<Map<String, Object>> workHoursStatisticsExport(Map<String, Object> parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("workHoursStatisticsExport"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("workHoursStatisticsExport"), e);
		}
	}
	
	public Map<String, Object> selectProByProCode(Map<String, Object> parameters) {
		try {
			return this.getSqlSession().selectOne(getSqlStatement("selectProByProCode"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProByProCode"), e);
		}
	}
	
	public Map<String, Object> selectKpiByNameAndCategoryCode2(Map<String, Object> parameters) {
		try {
			return this.getSqlSession().selectOne(getSqlStatement("selectKpiByNameAndCategoryCode"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectKpiByNameAndCategoryCode"), e);
		}
	}
	
	public List<Map<String, Object>> selectEmployeeByName(Map<String, Object> parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectEmployeeByName"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectEmployeeByName"), e);
		}
	}
	
	public List<Map<String, Object>> proCostStatistics(Map<String, Object> parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("proCostStatistics"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("proCostStatistics"), e);
		}
	}
	
	public int checkWorkHours(Map parameters){
		try {
			return this.getSqlSession().update(getSqlStatement("checkWorkHours"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("checkWorkHours"), e);
		}
	}
	
	public int backWorkHours(Map parameters){
		try {
			return this.getSqlSession().update(getSqlStatement("backWorkHours"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("backWorkHours"), e);
		}
	}


	public List<String> dateWillBeCreate(String proNo) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("dateWillBeCreate"), proNo);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("dateWillBeCreate"), e);
		}
	}
	
	public List<String> dateWillBeUpdate(String proNo, String date) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("proNo", proNo);
			params.put("date", date);
			return this.getSqlSession().selectList(getSqlStatement("dateWillBeUpdate"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("dateWillBeUpdate"), e);
		}
	}

	public boolean workHoursIsChecked(Map<String, Object> params2) {
		try {
			 Map<String, Object> res = this.getSqlSession().selectOne(getSqlStatement("workHoursIsChecked"), params2);
			 if(res!=null){
				 Long cnt = (Long)res.get("cnt");
				 Integer checkedVal = ((BigDecimal)res.get("checkedVal")).intValue();
				 if(cnt.intValue()==checkedVal){
					 return true;
				 }
			 }
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("workHoursIsChecked"), e);
		}
		return false;
	}
}
