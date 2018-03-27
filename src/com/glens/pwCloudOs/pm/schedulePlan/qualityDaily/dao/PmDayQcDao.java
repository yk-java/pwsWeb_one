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


package com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.vo.PmDayQcVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.dao.PmDayQcVoMapper")
public class PmDayQcDao extends EAPAbstractDao {

	public PmDayQcVo selectProDayQc(Map<String, String> params) {
		
		try {
			
			return this.getSqlSession().selectOne(getSqlStatement("selectProDayQc"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProDayQc"), e);
		}
	}
	
	
	
	public List getDetailList(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getDetailList"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDetailList"), e);
		}
	}
	
	public int deleteDetalList(Object parameter) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.getSqlSession().delete(getSqlStatement("deleteDetalList"), parameter);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteDetalList"), e);
		}
	}
	
	public List<Map<String, String>> problemType(Object parameter) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(getSqlStatement("problemType"), parameter);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("problemType"));
		}
	}
	
	public List<Map<String, String>> checkType() {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(getSqlStatement("checkType"), null);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("checkType"));
		}
	}
	public List<Map<String, String>> checkClass() {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(getSqlStatement("checkClass"), null);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("checkClass"));
		}
	}
	
	
	
	public List<Map<String, String>> qualityAnalysis(Object parameters) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(getSqlStatement("qualityAnalysis"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("qualityAnalysis"));
		}
	}
	
	
}
