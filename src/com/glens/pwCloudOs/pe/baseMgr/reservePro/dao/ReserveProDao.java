package com.glens.pwCloudOs.pe.baseMgr.reservePro.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pe.baseMgr.reservePro.dao.ReserveProVoMapper")
public class ReserveProDao extends EAPAbstractDao {
	
	
	
	public List<Map<String, Object>> getMctView(Map<String, Object> params) {

		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getMctView"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getMctView"), e);
		}
	}
	
	public List<Map<String, Object>> getAuditstate(Map<String, Object> params) {

		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getAuditstate"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getAuditstate"), e);
		}
	}
	
	public List<Map<String, String>> getDeviceMaterialAttr(String companyCode, String mctViewCode) {
		
		try {
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("companyCode", companyCode);
			params.put("mctViewCode", mctViewCode);
			
			return this.getSqlSession().selectList(getSqlStatement("getDeviceMaterialAttr"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDeviceMaterialAttr"), e);
		}
	}

}
