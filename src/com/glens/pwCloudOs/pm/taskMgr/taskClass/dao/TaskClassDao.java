package com.glens.pwCloudOs.pm.taskMgr.taskClass.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;


@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.taskMgr.taskClass.dao.TaskClassVoMapper")
public class TaskClassDao extends EAPAbstractDao {

	public List<Map<String, Object>> getKpiList(Map<String, String> params) {

		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getKpiList"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getKpiList"), e);
		}
	}

}
