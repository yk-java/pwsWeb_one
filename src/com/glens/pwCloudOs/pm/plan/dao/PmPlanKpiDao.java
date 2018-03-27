package com.glens.pwCloudOs.pm.plan.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pm.plan.vo.PmPlanKpi;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.plan.dao.PmPlanKpiMapper")
public class PmPlanKpiDao extends EAPAbstractDao {

	public int batchInsert(List<PmPlanKpi> kpiList) {
		try {
			int rows = this.getSqlSession().insert(getSqlStatement("batchInsert"), kpiList);
			return rows;
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("batchInsert"), e);
		}
	}
	
	public int batchUpdate(List<PmPlanKpi> kpiList) {
		try {
			int rows = this.getSqlSession().update(getSqlStatement("batchUpdate"), kpiList);
			return rows;
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("batchUpdate"), e);
		}
	}
	
	public int deleteByProNoAndPlanNo(Map params){
		try {
			int rows = this.getSqlSession().insert(getSqlStatement("deleteByProNoAndPlanNo"), params);
			return rows;
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteByProNoAndPlanNo"), e);
		}
	}
	
	public List<PmPlanKpi> findByProNoAndPlanNo(Map params){
		try {
			return this.getSqlSession().selectList(getSqlStatement("findByProNoAndPlanNo"), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findByProNoAndPlanNo"), e);
		}
	}

}
