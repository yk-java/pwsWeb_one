/**
 * @Title: DeviceDao.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.device.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-22 下午3:53:28
 * @version V1.0
 */


package com.glens.pwCloudOs.om.deviceMgr.device.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.om.deviceMgr.deviceMapper")
public class DeviceDao extends EAPAbstractDao {

	private static Log logger = LogFactory.getLog(DeviceDao.class);
	
	
	public List<Map<String, Object>> getMctDeviceAttr(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getMctDeviceAttr"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("exe sql=" + getSqlStatement("getMctDeviceAttr"), e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getMctDeviceAttr"), e);
		}
	}
	
	public List<Map<String, Object>> getMobileMctDeviceAttr(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getMobileMctDeviceAttr"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("exe sql=" + getSqlStatement("getMobileMctDeviceAttr"), e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getMobileMctDeviceAttr"), e);
		}
	}
	
	//电表运维
	public List<Map<String, Object>> getMctDeviceAttr1(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getMctDeviceAttr1"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("exe sql=" + getSqlStatement("getMctDeviceAttr"), e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getMctDeviceAttr1"), e);
		}
	}
	
	public List<Map<String, Object>> getMctView(String companyCode) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getMctView"), companyCode);
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("exe sql=" + getSqlStatement("getMctView"), e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getMctView"), e);
		}
	}
	
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
	
	public int getPmMemberCount(Map parameters) {
		// TODO Auto-generated method stub
		try {
			
			return (Integer) this.getSqlSession().selectOne(getSqlStatement("selectPmMemberCount"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectPmMemberCount"), e);
		}
	}
	
	public PageBean selectPmMemberPage(Map parameters, int currentPage, int perpage) {
		
		PageBean page = null;
		
		try {
			
			int totalCount = this.getPmMemberCount(parameters);
			if (totalCount < 1) {
				
				page = new PageBean();
				page.setList(new ArrayList());
			}
			else {
				
				page = new PageBean(totalCount, currentPage, perpage);
				if (parameters == null) {
					parameters = new HashMap();
				}
				
				parameters.put("firstResult", page.getPerpage() * (page.getCurrentPage() - 1));
				parameters.put("maxResult", page.getPerpage());
				
				List list = this.getSqlSession().selectList(getSqlStatement("selectPmMemberPage"), parameters);
				page.setList(list);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectPmMemberPage"), e);
		}
		
		return page;
	}
	
	public List<Map<String, Object>> getPicClass(Object params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getPicClass"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("exe sql=" + getSqlStatement("getPicClass"), e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getPicClass"), e);
		}
	}
	
	public List<String> getFeatureField(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getFeatureField"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("exe sql=" + getSqlStatement("getFeatureField"), e);
			
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getFeatureField"), e);
		}
	}
	
}
