package com.glens.pwCloudOs.dailylog.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.dailylog.dao.DailylogVoMapper")
public class DailylogDao extends EAPAbstractDao {

	public List getLogList(Object parameters) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("getLogList"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getLogList"));
		}
	}

	public List getAllList(Object parameters) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("getAllList"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getAllList"));
		}
	}

	public List getAllLogList(Object parameters) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("getAllLogList"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getAllLogList"));
		}
	}

	public List getLogList1(Object parameters) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("getLogList1"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getLogList1"));
		}
	}

	public boolean insertLog(Object parameters) {
		// TODO Auto-generated method stub
		try {

			this.getSqlSession().insert(getSqlStatement("insertLog"),
					parameters);

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertLog"), e);

		}
	}

}
