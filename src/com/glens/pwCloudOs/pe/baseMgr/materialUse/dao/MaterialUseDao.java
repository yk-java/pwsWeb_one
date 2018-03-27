package com.glens.pwCloudOs.pe.baseMgr.materialUse.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pe.baseMgr.materialUse.dao.MaterialUseVoMapper")
public class MaterialUseDao extends EAPAbstractDao {

	
	
	
	public int updateMaterialCount(Object parameters) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().update(getSqlStatement("updateMaterialCount"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateMaterialCount"), e);
		}
	}
	
	
	public List<Map<String, Object>> getMaterialType(Map<String, Object> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getMaterialType"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getMaterialType"), e);
		}
	}
	
	public List<Map<String, Object>> getMaterialBase(Map<String, Object> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getMaterialBase"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getMaterialBase"), e);
		}
	}
	
	public List<Map<String, Object>> getPros(Map<String, Object> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getPros"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getPros"), e);
		}
	}
	
}
