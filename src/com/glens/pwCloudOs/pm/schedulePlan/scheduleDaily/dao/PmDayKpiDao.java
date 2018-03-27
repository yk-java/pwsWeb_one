/**
 * @Title: PmDayWordloadDao.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 下午5:29:30
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.vo.PmDayKpi;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao.PmDayKpiMapper")
public class PmDayKpiDao extends EAPAbstractDao {
	public int batchInsert(List<PmDayKpi> kpiList) {
		try {
			int rows = this.getSqlSession().insert(getSqlStatement("batchInsert"), kpiList);
			return rows;
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("batchInsert"), e);
		}
	}
	
	public int deleteByProNoAndDate(Map params){
		try {
			int rows = this.getSqlSession().insert(getSqlStatement("deleteByProNoAndDate"), params);
			return rows;
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteByProNoAndDate"), e);
		}
	}
	
	public List<Map> queryProKpiProgress(Map params){
		try {
			return this.getSqlSession().selectList(getSqlStatement("queryProKpiProgress"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryProKpiProgress"), e);
		}
	}
	
	public List<Map> queryByProNoAndReportDate(Map params){
		try {
			return this.getSqlSession().selectList(getSqlStatement("queryByProNoAndReportDate"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryByProNoAndReportDate"), e);
		}
	}
	
	public List<Map> findKpiByDate(Map params){
		try {
			return this.getSqlSession().selectList(getSqlStatement("findKpiByDate"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findKpiByDate"), e);
		}
	}
	
	public List<Map<String, Object>> queryKpiSum(Map params){
		try {
			return this.getSqlSession().selectList(getSqlStatement("queryKpiSum"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryKpiSum"), e);
		}
	}
}
