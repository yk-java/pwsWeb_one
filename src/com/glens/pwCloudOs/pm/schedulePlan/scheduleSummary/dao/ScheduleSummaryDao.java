/**
 * @Title: ScheduleSummaryDao.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.scheduleSummary.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-12 下午2:13:52
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.schedulePlan.scheduleSummary.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.schedulePlan.scheduleSummary.dao.scheduleSummaryMapper")
public class ScheduleSummaryDao extends EAPAbstractDao {

	public List<Map<String, Object>> getScheduleSummary(Map<String, String> params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("getScheduleSummary"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getScheduleSummary"), e);
		}
	}
	
}
