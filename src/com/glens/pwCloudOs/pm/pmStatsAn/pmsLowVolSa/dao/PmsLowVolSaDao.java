/**
 * @Title: PmsLowVolSaDao.java
 * @Package com.glens.pwCloudOs.pm.pmStatsAn.pmsLowVolSa.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-11-8 下午5:12:31
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.pmStatsAn.pmsLowVolSa.dao;

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
@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.pmStatsAn.pmsLowVolSaMapper")
public class PmsLowVolSaDao extends EAPAbstractDao {

	public List<Map<String, String>> getActivePro(Map<String, String> params) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("getActivePro"), params);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getActivePro"), e);
		}
	}
	
	public List<Map<String, Object>> getProList(Map<String, String> params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getProList"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getProList"), e);
		}
	}
	
}
