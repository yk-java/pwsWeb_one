package com.glens.eap.sys.funcConfig.module.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value = "AppModuleVoMapper")
public class AppModuleDao extends EAPAbstractDao {

	
	public List queryAll(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("queryAll"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryAll"));
		}
	}
	
	public int deleteRoleModule(Object parameter) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.getSqlSession().delete(getSqlStatement("deleteRoleModule"), parameter);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteRoleModule"), e);
		}
	}
	
	
	
	public int updateRoleModule(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("insertRoleModule"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertRoleModule"), e);
		}
	}

}
