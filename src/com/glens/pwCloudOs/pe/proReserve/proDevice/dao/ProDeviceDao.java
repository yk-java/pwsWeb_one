/**
 * @Title: ProDeviceDao.java
 * @Package com.glens.pwCloudOs.pe.proReserve.proDevice.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-26 下午5:08:51
 * @version V1.0
 */


package com.glens.pwCloudOs.pe.proReserve.proDevice.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pe.proReserve.proDevice.deviceMapper")
public class ProDeviceDao extends EAPAbstractDao {

	private static Log logger = LogFactory.getLog(ProDeviceDao.class);
	
	public Map<String, String> getMctViewPros(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(getSqlStatement("getMctViewPros"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("exe sql=" + getSqlStatement("getMctViewPros"), e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getMctViewPros"), e);
		}
	}
	
	public List<Map<String, Object>> getProDeviceMateriaInfo(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getProDeviceMateriaInfo"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("exe sql=" + getSqlStatement("getProDeviceMateriaInfo"), e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getProDeviceMateriaInfo"), e);
		}
	}
	
	public List<Map<String, String>> getActivePro(Map<String, Object> params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("getActivePro"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getActivePro"), e);
		}
	}
	
}
