package com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.vo.CpCommuteConfig;

@MybatisNamespaceProcessor(value = "CpCommuteConfigMapper")
public class CpCommuteConfigDao extends EAPAbstractDao {

	public void deleteCommuteSetting(String companyCode) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().delete(
					getSqlStatement("[deleteCommuteSetting]"), companyCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[deleteCommuteSetting]"), e);
		}
	}

}
