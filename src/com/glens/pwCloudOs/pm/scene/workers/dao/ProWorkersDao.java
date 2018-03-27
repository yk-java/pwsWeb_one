package com.glens.pwCloudOs.pm.scene.workers.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pm.scene.workers.vo.ProWorkers;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.pm.scene.workers.dao.ProWorkersMapper")
public class ProWorkersDao extends EAPAbstractDao {

	public void updateSelective(ProWorkers worker) {
		try {
			this.getSqlSession().update(
					getSqlStatement("updateByPrimaryKeySelective"), worker);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateByPrimaryKeySelective"), e);
		}
	}

	public void deleteWorker(String spotCode) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().delete(getSqlStatement("deleteWorker"),
					spotCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteWorker"), e);
		}
	}

	public void insertSelective(ProWorkers vo) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().delete(getSqlStatement("insertSelective"), vo);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);
		}
	}

	public List<Map<String, Object>> queryProWorkers(Map map) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryProWorkers"), map);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryProWorkers"), e);
		}
	}

	public Map get(String workerCode) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("getProWorker"), workerCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getProWorker"), e);
		}
	}

	public List queryProWorker(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryProWorker"), paramsMap);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryProWorker"), e);
		}
	}

	public Map queryProWorkerByCardNo(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryProWorkerByCardNo"), paramsMap);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryProWorkerByCardNo"), e);
		}
	}

	public List getSpotWorkers(Map paramsMap) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("getSpotWorkers"), paramsMap);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getSpotWorkers"), e);
		}
	}

}
