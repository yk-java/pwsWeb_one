package com.glens.pwCloudOs.cj.base.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.cj.base.dao.CjXlghMapper")
public class CjXlghDao extends EAPAbstractDao {
	public int delByGtCollectId(String gtCollectId) {
		try {
			return this.getSqlSession().delete(
					getSqlStatement("delByGtCollectId"), gtCollectId);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[delByGtCollectId]"), e);
		}
	}
}
