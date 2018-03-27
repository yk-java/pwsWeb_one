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


package com.glens.pwCloudOs.pm.everydayMgr.bedMgr.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.pm.everydayMgr.bedMgr.vo.GmBmBedrentVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.everydayMgr.bedMgr.dao.GmBmBedrentVoMapper")
public class GmBmBedrentDao extends EAPAbstractDao {

	public int updateRentStatus(Object parameters) {
		try {
			return this.getSqlSession().update(getSqlStatement("updateRentStatus"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateRentStatus"), e);
		}
	}
	
	public int updateDormCode(Object parameters) {
		try {
			return this.getSqlSession().update(getSqlStatement("updateDormCode"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateDormCode"), e);
		}
	}
	
	public GmBmBedrentVo selectProNosByDormCode(Object parameter) {
		try {
			return (GmBmBedrentVo) this.getSqlSession().selectOne(getSqlStatement("selectProNosByDormCode"), parameter);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProNosByDormCode"), e);
		}
	}
	
}
