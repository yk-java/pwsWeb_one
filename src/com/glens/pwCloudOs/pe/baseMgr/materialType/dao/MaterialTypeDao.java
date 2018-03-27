package com.glens.pwCloudOs.pe.baseMgr.materialType.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;


@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pe.baseMgr.materialType.dao.MaterialTypeVoMapper")
public class MaterialTypeDao extends EAPAbstractDao {
	
	
	
	public List<Map<String, Object>> selectCount(Map<String, Object> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("selectCount"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectCount"), e);
		}
	}
	
}
