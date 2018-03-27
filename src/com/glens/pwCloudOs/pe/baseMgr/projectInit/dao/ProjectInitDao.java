package com.glens.pwCloudOs.pe.baseMgr.projectInit.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pe.baseMgr.projectInit.dao.ProjectInitVoMapper")
public class ProjectInitDao extends EAPAbstractDao {
	
	public List<Map<String, Object>> getPmMct(Map<String, Object> params) {

		try {


			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getPmMct"), params);


			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception


			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getPmMct"), e);
		}
	}
	
	
	public List<Map<String, Object>> treeList(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("treeList"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("treeList"), e);
		}
	}
	
	public List<Map<String, Object>> treeList1(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("treeList1"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("treeList1"), e);
		}
	}
	
	public List<Map<String, Object>> treeList2(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("treeList2"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("treeList2"), e);
		}
	}
	
	

}
