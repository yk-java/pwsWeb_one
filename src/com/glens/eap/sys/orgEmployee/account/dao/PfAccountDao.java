package com.glens.eap.sys.orgEmployee.account.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.eap.sys.orgEmployee.account.vo.PfAccount;

@MybatisNamespaceProcessor(value = "PfAccountMapper")
public class PfAccountDao extends EAPAbstractDao {

	public PfAccount queryPfAccount(String accountCode) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("[queryPfAccount]"), accountCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryPfAccount]"), e);
		}
	}
	
	
	public PfAccount selectAccountByEmployeecode(Map<String, Object> params) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("selectAccountByEmployeecode"), params);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectAccountByEmployeecode"), e);
		}
	}
	
	public List<String> selectAccountsByEmployeecodes(List<String> params) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("selectAccountsByEmployeecodes"), params);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("selectAccountsByEmployeecodes"), e);
		}
	}

}
