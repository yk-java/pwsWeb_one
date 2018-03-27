/**
 * @Title: WeekJobScheduleDao.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.weekJobSchedule.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-1-19 上午9:12:17
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.schedulePlan.weekJobSchedule.dao;

import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.jam.mutable.MPackage;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.schedulePlan.weekJobSchedule.dao.weekJobScheduleMapper")
public class WeekJobScheduleDao extends EAPAbstractDao {

	public Map<String, Object> selectProWeekWorkload(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(getSqlStatement("selectProWeekWorkload"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProWeekWorkload"), e);
		}
	}
	
	public String getFormulaByProNo(String proNo) {
		
		try {
			
			return this.getSqlSession().selectOne(getSqlStatement("getFormulaByProNo"), proNo);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getFormulaByProNo"), e);
		}
	}
	
	public List<Map<String, String>> selectWorkloadTrend() {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("selectWorkloadTrend"));
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectWorkloadTrend"), e);
		}
	}
	
}
