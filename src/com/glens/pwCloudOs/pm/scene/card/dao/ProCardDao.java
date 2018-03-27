package com.glens.pwCloudOs.pm.scene.card.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.pm.scene.card.dao.ProCardMapper")
public class ProCardDao extends EAPAbstractDao {

	public List chooseCardsList(Map map) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("chooseCardsList"), map);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("chooseCardsList"), e);
		}
	}

	public void insertCard(ValueObject vo) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().insert(getSqlStatement("insertSelective"), vo);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);
		}
	}

	public void updateCard(ValueObject vo) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().update(
					getSqlStatement("updateByPrimaryKeySelective"), vo);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateByPrimaryKeySelective"), e);
		}
	}

	public Map get(Long rowid) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectOne(getSqlStatement("getCard"),
					rowid);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getCard"), e);
		}
	}

}
