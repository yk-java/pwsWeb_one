/**

 * @Title: LoginDao.java

 * @Package com.glens.pwCloudOs.login.dao

 * @Description: TODO

 * Copyright: Copyright (c) 2016 

 * Company:南京量为石信息科技有限公司

 * 

 * @author 

 * @date 2016-5-6 下午4:47:24

 * @version V1.0

 */

package com.glens.pwCloudOs.login.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

/**
 * 
 * @ClassName: LoginDao
 * 
 * @Description: TODO
 * 
 * @author Comsys-Administrator
 * 
 * @date 2016-5-6 下午4:47:24
 * 
 * 
 */

@MybatisNamespaceProcessor(value = "login")
public class LoginDao extends EAPAbstractDao {

	public UserToken getAccountByAccountName(String accountName) {

		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("[getAccountByAccountName]"), accountName);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[getAccountByAccountName]"), e);
		}
	}

	public String getPswByAccountName(String accountName) {

		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("[getPswByAccountName]"), accountName);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[getPswByAccountName]"), e);
		}
	}

	public List<Map<String, Object>> queryModuleList(String accountName) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("[queryModuleList]"), accountName);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryModuleList]"), e);
		}
	}

	public int updateUserPsw(Map<String, String> params) {
		try {

			return this.getSqlSession().update(
					getSqlStatement("updateUserPsw"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[updateUserPsw]"), e);
		}
	}

	public int updateUserPswByMobile(Map<String, String> params) {
		try {

			return this.getSqlSession().update(
					getSqlStatement("updateUserPswByMobile"), params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateUserPswByMobile"), e);
		}
	}

}
