package com.glens.pwCloudOs.materielMg.assetMg.materialBase.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.materielMg.assetMg.materialBase.dao.MaterialBaseVoMapper")
public class MaterialBaseDao extends EAPAbstractDao {

	
	public List<Map<String, Object>> ishave(Object params) {
		
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("ishave"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("ishave"), e);
		}
	}
	
	public boolean addMaterial(Object parameters) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.getSqlSession().insert(getSqlStatement("addMaterial"), parameters) > 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("addMaterial"), e);
			
		}
	}
	
	public List<Map<String, Object>> getProList(Object params) {
		
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getProList"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getProList"), e);
		}
	}
	
	public List<Map<String, Object>> getProMembers(Object params) {
		
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getProMembers"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getProMembers"), e);
		}
	}
	
	public List<Map<String, Object>> getDetail(Object params) {
		
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getDetail"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDetail"), e);
		}
	}
	
	
	
	public List<Map<String, Object>> getLinyongNum(Object params) {
		
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getLinyongNum"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getLinyongNum"), e);
		}
	}
	
	public List<Map<String, Object>> getJieyongNum(Object params) {
		
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getJieyongNum"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getJieyongNum"), e);
		}
	}
	
	public List<Map<String, Object>> getRentList(Object params) {
		
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getRentList"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getRentList"), e);
		}
	}

	public List<Map<String, Object>> getTotalMaterialNum(Object params) {
		
		try {
			List<Map<String, Object>> resultList = this.getSqlSession().selectList(getSqlStatement("getTotalMaterialNum"), params);
			return resultList;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getTotalMaterialNum"), e);
		}
	}
	
	public int returnMaterial(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().update(getSqlStatement("returnMaterial"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("returnMaterial"), e);
		}
	}
	
	public int selectApplyMaterialCount(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(getSqlStatement("selectApplyMaterialCount"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectApplyMaterialCount"), e);
		}
	}
	
	public PageBean selectApplyMaterialPage(Map<String, Object> params) {
		
		try {
			int totalCount = this.selectApplyMaterialCount(params);
			PageBean page = null;
			if (totalCount < 1) {
				
				page = new PageBean();
				page.setList(new ArrayList<Map<String, Object>>());
			}
			else {
				
				int currentPage = (Integer) params.get("currentPage");
				int perpage = (Integer) params.get("perpage");
				page = new PageBean(totalCount, currentPage, perpage);
				params.put("firstResult", page.getPerpage() * (page.getCurrentPage() - 1));
				params.put("maxResult", page.getPerpage());
				List<Map<String, Object>> employeeList = this.getSqlSession().
						selectList(this.getSqlStatement("selectApplyMaterialPage"), params);
				page.setList(employeeList);
			}
			
			return page;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectApplyMaterialPage"), e);
		}
	}
	
	public int selectProcessedMaterialCount(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().selectOne(getSqlStatement("selectProcessedMaterialCount"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProcessedMaterialCount"), e);
		}
	}
	
	public PageBean selectProcessedMaterialPage(Map<String, Object> params) {
		
		try {
			int totalCount = this.selectProcessedMaterialCount(params);
			PageBean page = null;
			if (totalCount < 1) {
				
				page = new PageBean();
				page.setList(new ArrayList<Map<String, Object>>());
			}
			else {
				
				int currentPage = (Integer) params.get("currentPage");
				int perpage = (Integer) params.get("perpage");
				page = new PageBean(totalCount, currentPage, perpage);
				params.put("firstResult", page.getPerpage() * (page.getCurrentPage() - 1));
				params.put("maxResult", page.getPerpage());
				List<Map<String, Object>> employeeList = this.getSqlSession().
						selectList(this.getSqlStatement("selectProcessedMaterialPage"), params);
				page.setList(employeeList);
			}
			
			return page;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectProcessedMaterialPage"), e);
		}
	}
	
	public Map<String, Object> selectMaterialFlow(String flowCode) {
		
		try {
			
			return this.getSqlSession().selectOne(getSqlStatement("selectMaterialFlow"), flowCode);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectMaterialFlow"), e);
		}
	}
	
	public List<Map<String, Object>> selectApplyedMaterial(String flowCode) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("selectApplyedMaterial"), flowCode);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("selectApplyedMaterial"), e);
		}
	}
	
	public int insertMaterialUseRecord(List<Map<String, Object>> list) {
		
		try {
			
			return this.getSqlSession().insert(getSqlStatement("insertMaterialUseRecord"), list);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertMaterialUseRecord"), e);
		}
	}
	
	public int updateMaterialCurCount(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().update(getSqlStatement("updateMaterialCurCount"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateMaterialCurCount"), e);
		}
	}
	
	public int auditBorrowMaterial(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().update(getSqlStatement("auditBorrowMaterial"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("updateMaterialCurCount"), e);
		}
	}
	
	public int auditReturnMaterial(Map<String, Object> params) {
		
		try {
			
			return this.getSqlSession().update(getSqlStatement("auditReturnMaterial"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("auditReturnMaterial"), e);
		}
	}
	
	public List<Map<String, Object>> exportTableList(Object params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("exportTableList"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("exportTableList"), e);
		}
	}
	public List<Map<String, Object>> exportMaterialUseBase(Object params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("exportMaterialUseBase"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("exportMaterialUseBase"), e);
		}
	}
	
	
	
	public PageBean getStaticsMaterial(Map parameters, int currentPage, int perpage) {
		
		PageBean page = null;
		
		try {
			
			int totalCount = this.getStaticsMaterialCount(parameters);
			if (totalCount < 1) {
				
				page = new PageBean();
				page.setList(new ArrayList());
			}
			else {
				
				page = new PageBean(totalCount, currentPage, perpage);
				page = this.getStaticsMaterial(parameters, page);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getStaticsMaterial"), e);
		}
		
		return page;
	}

	private PageBean getStaticsMaterial(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {
			
			if (parameters == null) {
				parameters = new HashMap();
			}
			
			parameters.put("firstResult", page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());
			
			List list = this.getSqlSession().selectList(getSqlStatement("getStaticsMaterial"), parameters);
			page.setList(list);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getStaticsMaterial"), e);
		}
		
		return page;
	}
	
	
	public int getStaticsMaterialCount(Map parameters) {
		// TODO Auto-generated method stub
		try {
			
			return (Integer) this.getSqlSession().selectOne(getSqlStatement("getStaticsMaterialCount"), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getStaticsMaterialCount"), e);
		}
	}
	
	
	public List<Map<String, Object>> getDetailMaterial(Object params) {
		
		try {
			
			return this.getSqlSession().selectList(getSqlStatement("getDetailMaterial"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDetailMaterial"), e);
		}
	}
	
	
	
}
