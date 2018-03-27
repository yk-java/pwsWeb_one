package com.glens.pwCloudOs.commuteMgr.online.dao;

import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.commuteMgr.online.vo.CpOnlineUserHistoryVo;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.commuteMgr.online.dao.CpOnlineUserHistoryMapper")
public class CpOnlineUserHistoryDao extends EAPAbstractDao {

	public void insertOnline(CpOnlineUserHistoryVo vo) {
		try {

			this.getSqlSession().insert(getSqlStatement("insertSelective"), vo);

		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);

		}
	}

	public void updateOnline(CpOnlineUserHistoryVo vo) {
		// TODO Auto-generated method stub
		try {

			this.getSqlSession().update(
					getSqlStatement("updateByPrimaryKeySelective"), vo);

		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateByPrimaryKeySelective"), e);

		}
	}

	public Map queryOnlineTime(Map m) {

		Map result = null;
		try {
			

			result = this.getSqlSession().selectOne(
					getSqlStatement("queryOnlineTime"), m);

		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryOnlineTime"), e);

		}

		return result;
	}

	public void updatebyMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {

			this.getSqlSession().update(getSqlStatement("updatebyMap"),
					paramsMap);

		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updatebyMap"), e);

		}
	}

}
