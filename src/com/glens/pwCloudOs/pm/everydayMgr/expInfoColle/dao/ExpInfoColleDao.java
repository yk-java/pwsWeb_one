package com.glens.pwCloudOs.pm.everydayMgr.expInfoColle.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.everydayMgr.expInfoColle.dao.ExpInfoColleVoMapper")
public class ExpInfoColleDao extends EAPAbstractDao {




	public List<Map<String, Object>> getProManger() {

		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getProManger"), null);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getProManger"), e);
		}
	}

}
