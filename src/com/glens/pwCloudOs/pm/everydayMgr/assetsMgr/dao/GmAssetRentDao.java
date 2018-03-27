package com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.dao.GmAssetRentVoMapper")
public class GmAssetRentDao extends EAPAbstractDao {
	
	public int updateRentStatus(Object parameters) {
		try {
			return this.getSqlSession().update(getSqlStatement("updateRentStatus"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateRentStatus"), e);
		}
	}
	public int updateAssetCode(Object parameters) {
		try {
			return this.getSqlSession().update(getSqlStatement("updateAssetCode"), parameters);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateAssetCode"), e);
		}
	}
}
