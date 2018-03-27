package com.glens.pwCloudOs.pm.scene.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.pm.scene.dao.PmSceneMonitorPointMapper")
public class PmSceneMonitorPointDao extends EAPAbstractDao {
	
	public ValueObject findByMonitorPointCode(Object parameter) {
		try {
			return (ValueObject) this.getSqlSession().selectOne(
					getSqlStatement("findByMonitorPointCode"), parameter);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("findByMonitorPointCode"), e);
		}
	}
	
}
