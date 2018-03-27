package com.glens.eap.sys.funcConfig.role.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.eap.sys.funcConfig.role.vo.SmRole;

@MybatisNamespaceProcessor(value = "SmRoleMapper")
public class SmRoleDao extends EAPAbstractDao {


	public List<Map<String, Object>> queryModuleList(String roleCode) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("[queryModuleList]"), roleCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryModuleList]"), e);
		}
	}

	public List getRoleList(Object params) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("getRoleList"), params);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getRoleList"), e);
		}
	}
	
	
	public void deleteRoleModule(String roleCode) {
		try {
			this.getSqlSession().delete(getSqlStatement("[deleteRoleModule]"),
					roleCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[deleteRoleModule]"), e);
		}
	}

	public void insertRoleModule(Map<String, Object> m) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().insert(getSqlStatement("[insertRoleModule]"),
					m);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[insertRoleModule]"), e);
		}
	}
	
	@Override
	public boolean insert(Object parameters) {
		try {
			this.getSqlSession().insert(getSqlStatement(INSERT_STATEMENT), parameters);
			
			return true;
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement(INSERT_STATEMENT), e);
			
		}
	}
}
