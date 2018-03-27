/**
 * @Title: EmployeeSummaryDao.java
 * @Package com.glens.eap.sys.orgEmployee.summary.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-7-15 上午10:42:18
 * @version V1.0
 */


package com.glens.eap.sys.orgEmployee.summary.dao;

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

@MybatisNamespaceProcessor(value="com.glens.eap.sys.orgEmployee.summary.summaryMapper")
public class EmployeeSummaryDao extends EAPAbstractDao {

	private static Log logger = LogFactory.getLog(EmployeeSummaryDao.class);
	
	public List<Map<String, Object>> selectEmployeeSummary(Map<String, String> params) {
		
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectEmployeeSummary"), params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("exe sql=" + getSqlStatement("selectEmployeeSummary"), e);
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectEmployeeSummary"), e);
		}
	}
	
	public List<Map<String, Object>> selectProEmployee(Map<String, Object> params) {
		
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectProEmployee"), params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("exe sql=" + getSqlStatement("selectProEmployee"), e);
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProEmployee"), e);
		}
	}
	
	public List<Map<String, Object>> selectUnitEmployee(Map<String, Object> params) {
		
		try {
			return this.getSqlSession().selectList(getSqlStatement("selectUnitEmployee"), params);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("exe sql=" + getSqlStatement("selectUnitEmployee"), e);
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectUnitEmployee"), e);
		}
	}
	
}
