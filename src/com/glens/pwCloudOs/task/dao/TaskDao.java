package com.glens.pwCloudOs.task.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.task.vo.TaskList;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.task.dao.TaskListMapper")
public class TaskDao extends EAPAbstractDao {

	public List<TaskList> findAll() {
		try {

			List<TaskList> resultList = this.getSqlSession().selectList(
					getSqlStatement("findAll"));

			return resultList;
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("findAll"), e);
		}
	}

}
