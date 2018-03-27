package com.glens.pwCloudOs.pe.baseMgr.rpMaterialBase.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pe.baseMgr.rpMaterialBase.dao.RpMaterialBaseVoMapper")
public class RpMaterialBaseDao extends EAPAbstractDao {
	
	
	
	
	public List<Map<String, Object>> getMaterialtype(Object params) {
		
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getMaterialtype"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getMaterialtype"), e);
		}
	}

}

