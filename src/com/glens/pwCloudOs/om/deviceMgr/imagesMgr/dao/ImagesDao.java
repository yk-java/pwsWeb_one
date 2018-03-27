package com.glens.pwCloudOs.om.deviceMgr.imagesMgr.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;


@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.om.deviceMgr.imagesMgr.dao.ImagesVoMapper")
public class ImagesDao extends EAPAbstractDao {

	
	
	
	public List<Map<String, Object>> getImageslist(Map<String, Object> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getImageslist"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getImageslist"), e);
		}
	}
	
	public List<Map<String, Object>> getAllImageslist(Map<String, Object> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getAllImageslist"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getAllImageslist"), e);
		}
	}
	
	
	
	
	
}
