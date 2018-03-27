/**
 * @Title: PmChangeDao.java
 * @Package com.glens.pwCloudOs.pm.baseMgr.pmChange.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 上午11:53:38
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.baseMgr.pmChange.dao;

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

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.baseMgr.pmChange.dao.PmModifyRecordVoMapper")
public class PmChangeDao extends EAPAbstractDao {

	public int updatePmBase(Map<String, String> pmBaseMap) {
		
		try {
			
			return this.getSqlSession().update(getSqlStatement("updatePmBase"), pmBaseMap);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updatePmBase"), e);
		}
	} 
	
}
