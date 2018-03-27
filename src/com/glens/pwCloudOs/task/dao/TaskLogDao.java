package com.glens.pwCloudOs.task.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.task.dao.TaskLogMapper")
public class TaskLogDao extends EAPAbstractDao {

	public boolean insertSelective(Object parameters) {
		try {

			this.getSqlSession().insert(getSqlStatement("insertSelective"),
					parameters);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);

		}
	}

	public boolean updateSelective(Object parameters) {
		try {

			this.getSqlSession().update(
					getSqlStatement("updateByPrimaryKeySelective"), parameters);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateByPrimaryKeySelective"), e);

		}
	}
}
