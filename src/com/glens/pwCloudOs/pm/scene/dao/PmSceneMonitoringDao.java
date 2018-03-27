package com.glens.pwCloudOs.pm.scene.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pm.scene.vo.PmSceneMonitoring;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.pm.scene.dao.PmSceneMonitoringMapper")
public class PmSceneMonitoringDao extends EAPAbstractDao {

	public List<PmSceneMonitoring> statistics(Map<String, Object> parameters) {

		try {
			return this.getSqlSession().selectList(
					getSqlStatement("statistics"), parameters);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("statistics"), e);
		}
	}

	public List querySceneList(Map parameters) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("querySceneList"), parameters);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("querySceneList"), e);
		}
	}

	public List queryHisList(Map parameters) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryHisList"), parameters);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryHisList"), e);
		}
	}

	public List queryHeatHisList(Map parameters) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryHeatHisList"), parameters);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryHeatHisList"), e);
		}
	}

	public List queryReport(Map parameters) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryReport"), parameters);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryReport"), e);
		}
	}
}
