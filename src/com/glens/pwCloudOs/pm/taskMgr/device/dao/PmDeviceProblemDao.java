package com.glens.pwCloudOs.pm.taskMgr.device.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.taskMgr.device.dao.PmDeviceProblemMapper")
public class PmDeviceProblemDao extends EAPAbstractDao {
	
	public ValueObject findByProblemCode(Object parameter) {
		try {
			return (ValueObject) this.getSqlSession().selectOne(getSqlStatement("findByProblemCode"), parameter);
		}
		catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("findByProblemCode"), e);
		}
	}
}
