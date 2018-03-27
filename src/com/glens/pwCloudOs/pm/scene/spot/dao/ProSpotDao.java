package com.glens.pwCloudOs.pm.scene.spot.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pm.scene.spot.vo.ProSpot;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.pm.scene.spot.dao.ProSpotMapper")
public class ProSpotDao extends EAPAbstractDao {

	public void insertSelective(ProSpot parameter) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().insert(getSqlStatement("insertSelective"),
					parameter);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);
		}
	}

	public void updateSelective(ProSpot parameter) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().insert(
					getSqlStatement("updateByPrimaryKeySelective"), parameter);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateByPrimaryKeySelective"), e);
		}
	}

	public Map<String, Object> get(String spotCode) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectOne(getSqlStatement("getSpot"),
					spotCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getSpot"), e);
		}
	}

	public List queryProSpotInfo(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryProSpotInfo"), paramsMap);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryProSpotInfo"), e);
		}
	}

	public Map queryProspotCode(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryProspotCode"), paramsMap);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryProspotCode"), e);
		}
	}

}
