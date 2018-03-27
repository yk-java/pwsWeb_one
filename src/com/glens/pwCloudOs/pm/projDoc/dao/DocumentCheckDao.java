package com.glens.pwCloudOs.pm.projDoc.dao;

import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;


@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.projDoc.dao.DocumentCheckVoMapper")
public class DocumentCheckDao extends EAPAbstractDao {
	public Map queryByDocNo(Object parameter) {
		try {
			return (Map) this.getSqlSession().selectOne(getSqlStatement("queryByDocNo"), parameter);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryByDocNo"), e);
		}
	}
}
