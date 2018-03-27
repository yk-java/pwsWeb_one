package com.glens.pwCloudOs.fm.contractBase.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.fm.contractBase.vo.ContractPageBean;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.fm.contractBase.dao.ContractBaseVoMapper")
public class ContractBaseDao extends EAPAbstractDao {

	public int queryForCount1(Map parameters) {
		// TODO Auto-generated method stub
		try {

			return (Integer) this.getSqlSession().selectOne(
					getSqlStatement("queryForCount1"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryForCount1"), e);
		}
	}

	public ContractPageBean queryForPage1(Map parameters, int currentPage, int perpage) {

		ContractPageBean page = null;

		try {

			int totalCount = this.queryForCount1(parameters);
			if (totalCount < 1) {

				page = new ContractPageBean();
				page.setList(new ArrayList());
			} else {

				page = new ContractPageBean(totalCount, currentPage, perpage);
				page = this.queryForPage1(parameters, page);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryForPage1"), e);
		}

		return page;
	}

	private ContractPageBean queryForPage1(Map parameters, ContractPageBean page) {
		// TODO Auto-generated method stub
		try {

			if (parameters == null) {
				parameters = new HashMap();
			}

			parameters.put("firstResult",
					page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());

			List list = this.getSqlSession().selectList(
					getSqlStatement("queryForPage1"), parameters);
			page.setList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryForPage1"), e);
		}

		return page;
	}

	public List queryList(String proNo) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryList"), proNo);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryList"), e);
		}
	}
	
	public List<Map<String, Object>> selectProOutputYears() {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectProOutputYears"));
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + this.getSqlStatement("selectProOutputYears"), e);
		}
	}
	
	public List<Map<String, Object>> selectProOutput(String proNos) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("selectProOutput"), proNos);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + this.getSqlStatement("selectProOutput"), e);
		}
	}
	
	public boolean insertProjLink(Object params){

		try {
			
			return this.getSqlSession().insert(getSqlStatement("insertProjLink"), params) > 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("insertProjLink"), e);
			
		}
	}
	
	public List getDetail(Object parameter) {
		// TODO Auto-generated method stub
		
		try {
			return this.getSqlSession().selectList(this.getSqlStatement("getDetail"), parameter);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDetail"), e);
		}
	}
	
	public List getLinksByContractNo(Map params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("getLinksByContractNo"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + this.getSqlStatement("getLinksByContractNo"), e);
		}
	}
	
	public List getProContractLinks(Map params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("getProContractLinks"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + this.getSqlStatement("getProContractLinks"), e);
		}
	}
	public List getProContractLinks1(Map params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("getProContractLinks1"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + this.getSqlStatement("getProContractLinks1"), e);
		}
	}
	
	public List getContractProLinks(Map params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("getContractProLinks"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + this.getSqlStatement("getContractProLinks"), e);
		}
	}
	
	public int deleteProContractLinks(Object parameter) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.getSqlSession().delete(getSqlStatement("deleteProContractLinks"), parameter);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("deleteProContractLinks"), e);
		}
	}
	
	public List getStatistics1(Map params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("getStatistics1"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + this.getSqlStatement("getStatistics1"), e);
		}
	}
	
	public List getStatistics2(Map params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("getStatistics2"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + this.getSqlStatement("getStatistics2"), e);
		}
	}
	
	public List getContractPlan(Map params) {
		
		try {
			
			return this.getSqlSession().selectList(this.getSqlStatement("getContractPlan"), params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + this.getSqlStatement("getContractPlan"), e);
		}
	}
	
	
	
}
