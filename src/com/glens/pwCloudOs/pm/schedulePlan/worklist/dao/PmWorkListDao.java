package com.glens.pwCloudOs.pm.schedulePlan.worklist.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.vo.PmWorkList;
@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.schedulePlan.worklist.dao.PmWorkListMapper")
public class PmWorkListDao extends EAPAbstractDao {
	public boolean batchInsert(List<PmWorkList> list) {
		try {
			return this.getSqlSession().insert(getSqlStatement("batchInsert"), list) > 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("batchInsert"), e);
		}
	}
	
	public boolean deleteByWorkerAndDate(Map<String, Object> parameter) {
		try {
			return this.getSqlSession().delete(getSqlStatement("deleteByWorkerAndDate"), parameter)>0;
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteByWorkerAndDate"), e);
		}
	}
	
	public List queryByWorkerAndDate(Object parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("queryByWorkerAndDate"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryByWorkerAndDate"), e);
		}
	}
	
	public Object projectDayWorkHours(Map<String, Object> parameters) {
		try {
			return this.getSqlSession().selectOne(getSqlStatement("projectDayWorkHours"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryByWorkerAndDate"), e);
		}
	}
	
	public List<Map<String, Object>> kpiDayWorkload(Map<String, Object> parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("kpiDayWorkload"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("kpiDayWorkload"), e);
		}
	}
	
	public List<Map<String, Object>> inOutPerCntAndDescr(Map<String, Object> parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("inOutPerCntAndDescr"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("inOutPerCntAndDescr"), e);
		}
	}
	
	
}
