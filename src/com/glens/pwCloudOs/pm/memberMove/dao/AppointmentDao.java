package com.glens.pwCloudOs.pm.memberMove.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;


@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.memberMove.dao.AppointmentVoMapper")
public class AppointmentDao extends EAPAbstractDao {

	
	public boolean insert(Object parameters) {
		// TODO Auto-generated method stub
		
		try {
			
			this.getSqlSession().insert(getSqlStatement("insert"), parameters);
			
			
			
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insert"), e);
			
		}
	}
	
	
	public int updateManager(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateManager"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateManager"), e);
		}
	}
	
	
}
