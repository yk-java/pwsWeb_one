/**
 * @Title: PmDayQcDao.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-12 上午10:34:27
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.taskMgr.carDispach.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.taskMgr.carDispach.dao.PmTmVehicleDispatchVoMapper")
public class PmTmVehicleDispatchDao extends EAPAbstractDao {

	
	
	
	
	public List selectList(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("selectList"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectList"), e);
		}
	}
	
	
	public int updateState(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateState"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateState"), e);
		}
	}
	
	
	
	public Object getDispatchVo(Object parameter) {
		// TODO Auto-generated method stub
		
		try {
			
			return  this.getSqlSession().selectOne(getSqlStatement("getDispatchVo"), parameter);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDispatchVo"), e);
		}
	}

	
	
}
