/**
 * @Title: AssetTypeDao.java
 * @Package com.glens.pwCloudOs.materielMg.assetMg.assetType.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-22 上午11:20:37
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.assetMg.assetType.dao;

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

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.materielMg.assetMg.assetType.dao.AssetTypeVoMapper")
public class AssetTypeDao extends EAPAbstractDao {
	
	public List getTypeList(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getTypeList"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getTypeList"));
		}
	}
	
	

}
