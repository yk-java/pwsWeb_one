package com.glens.pwCloudOs.om.deviceMgr.imagesMgr.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.eap.platform.framework.web.EAPAbstractController;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.om.deviceMgr.imagesMgr.dao.DeviceVoMapper")
public class DeviceDao extends EAPAbstractDao {


	public boolean insertFs(Object parameters) {
		// TODO Auto-generated method stub
		
		try {
			
			this.getSqlSession().insert(getSqlStatement("insertFs"), parameters);
			
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertFs"), e);
			
		}
	}

}
