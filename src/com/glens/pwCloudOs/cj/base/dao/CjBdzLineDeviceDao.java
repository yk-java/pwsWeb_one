package com.glens.pwCloudOs.cj.base.dao;

import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.cj.base.vo.CjBdzLineDevice;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.cj.base.dao.CjBdzLineDeviceMapper")
public class CjBdzLineDeviceDao extends EAPAbstractDao {
	public CjBdzLineDevice queryCjDeviceByCollectIdAndTypeNumber(Map parameter) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("[queryCjDeviceByCollectIdAndTypeNumber]"), parameter);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryCjDeviceByCollectIdAndTypeNumber]"), e);
		}
	}
}
