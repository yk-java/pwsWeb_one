package com.glens.pwCloudOs.pe.baseMgr.remouldScheme.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pe.baseMgr.remouldScheme.dao.RemouldSchemeVoMapper")
public class RemouldSchemeDao extends EAPAbstractDao {

	public List<Map<String, Object>> materialList(Map<String, Object> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("queryMaterialType"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryMaterialType"), e);
		}
	}

	public List<Map<String, Object>> getALLType(Map<String, Object> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getALLType"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getALLType"), e);
		}
	}
	
	public int deleteMaterial(Map<String, Object> params) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.getSqlSession().delete(getSqlStatement("deleteMaterial"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteMaterial"), e);
		}
	}
	
	public int addMaterial(Object params) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.getSqlSession().delete(getSqlStatement("addMaterial"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("addMaterial"), e);
		}
	}
	
	
	
	
	
	
}
