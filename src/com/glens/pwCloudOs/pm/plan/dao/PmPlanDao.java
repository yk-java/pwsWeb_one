package com.glens.pwCloudOs.pm.plan.dao;

import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.plan.dao.PmPlanMapper")
public class PmPlanDao extends EAPAbstractDao {
	public Float getAllPlanWordload(Map<String, Object> params){
		try {
			return (Float)this.getSqlSession().selectOne(getSqlStatement("getAllPlanWordload"), params);
		}catch (Exception e) {
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getAllPlanWordload"), e);
		}
	}
}
