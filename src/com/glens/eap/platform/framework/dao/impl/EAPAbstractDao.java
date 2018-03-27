package com.glens.eap.platform.framework.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.beans.InitializingBean;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.core.orm.mybatis.session.SqlSessionDaoSupport;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.EAPDao;

@SuppressWarnings({ "unchecked" })
public abstract class EAPAbstractDao extends SqlSessionDaoSupport implements EAPDao, InitializingBean {
	
	public static final String DELETE_STAEMENT = "delete";
	public static final String FIND_BY_ID_STATEMENT = "findById";
	public static final String INSERT_STATEMENT = "insert";
	public static final String QUERY_FOR_COUNT_STATEMENT = "queryForCount";
	public static final String QUERY_FOR_LIST_STATEMENT = "queryForList";
	public static final String QUERY_FOR_PAGE_STATEMENT = "queryForPage";
	public static final String UPDATE_STATEMENT = "update";
	
	protected String namespace;

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	public EAPAbstractDao() {
		// TODO Auto-generated constructor stub
		this.initialize();
	}
	
	private void initializeNamespace() {
		
		MybatisNamespaceProcessor ns = this.getClass().getAnnotation(MybatisNamespaceProcessor.class);
		if (ns != null && ns.value() != null && !"".equals(ns.value())) {
			
			this.namespace = ns.value();
		}
		else {
			
			throw new MyBatisAccessException("Annotation Namespace's value is required");
		}
	}
	
	protected String getSqlStatement(String sqlstatement) {
		return this.namespace + "." + sqlstatement;
	}

	@Override
	public int delete(Object parameter) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.getSqlSession().delete(getSqlStatement(DELETE_STAEMENT), parameter);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement(DELETE_STAEMENT), e);
		}
	}

	@Override
	public ValueObject findById(Object parameter) {
		// TODO Auto-generated method stub
		
		try {
			
			return (ValueObject) this.getSqlSession().selectOne(getSqlStatement(FIND_BY_ID_STATEMENT), parameter);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement(FIND_BY_ID_STATEMENT), e);
		}
	}

	@Override
	public boolean insert(Object parameters) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.getSqlSession().insert(getSqlStatement(INSERT_STATEMENT), parameters) > 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement(INSERT_STATEMENT), e);
			
		}
	}
	
	@Override
	public List queryForList(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().selectList(getSqlStatement(QUERY_FOR_LIST_STATEMENT), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement(QUERY_FOR_LIST_STATEMENT), e);
		}
	}

	@Override
	public int queryForCount(Map parameters) {
		// TODO Auto-generated method stub
		try {
			
			return (Integer) this.getSqlSession().selectOne(getSqlStatement(QUERY_FOR_COUNT_STATEMENT), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement(QUERY_FOR_COUNT_STATEMENT), e);
		}
	}

	@Override
	public PageBean queryForPage(Map parameters, int currentPage) {
		// TODO Auto-generated method stub
		PageBean page = this.queryForPage(parameters, currentPage, PageBean.DEFAULT_PER_PAGE);
		return page;
	}
	
	@Override
	public PageBean queryForPage(Map parameters, int currentPage, int perpage) {
		
		PageBean page = null;
		
		try {
			
			int totalCount = this.queryForCount(parameters);
			if (totalCount < 1) {
				
				page = new PageBean();
				page.setList(new ArrayList());
			}
			else {
				
				page = new PageBean(totalCount, currentPage, perpage);
				page = this.queryForPage(parameters, page);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement(QUERY_FOR_PAGE_STATEMENT), e);
		}
		
		return page;
	}

	private PageBean queryForPage(Map parameters, PageBean page) {
		// TODO Auto-generated method stub
		try {
			
			if (parameters == null) {
				parameters = new HashMap();
			}
			
			parameters.put("firstResult", page.getPerpage() * (page.getCurrentPage() - 1));
			parameters.put("maxResult", page.getPerpage());
			
			List list = this.getSqlSession().selectList(getSqlStatement(QUERY_FOR_PAGE_STATEMENT), parameters);
			page.setList(list);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement(QUERY_FOR_PAGE_STATEMENT), e);
		}
		
		return page;
	}

	@Override
	public int update(Object parameters) {
		// TODO Auto-generated method stub
		try {
			
			return this.getSqlSession().update(getSqlStatement(UPDATE_STATEMENT), parameters);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql=" + getSqlStatement(UPDATE_STATEMENT), e);
		}
	}
	
	@Override
	public void initialize() {
		
		initializeNamespace();
	}
	
	/**
	 * Edit by MaDx
	 * @param params
	 * @return
	 */
	@Override
	public PageBean queryForPage(Map<String, Object> params, String selectRecCountId, String selectRecId){
		PageBean page = null;
		
		try {
			int currentPage = 1;
			int pageSize = 10;
			try {
				currentPage=Integer.parseInt((String)params.get("currentPage"));
				pageSize = Integer.parseInt((String)params.get("perpage"));
			} catch (Exception e) {
			}
			int totalCount = 0;
			Object resObj =  this.getSqlSession().selectOne(getSqlStatement(selectRecCountId), params);
			if(resObj!=null){
				totalCount = (Integer)resObj;
			}	
			if (totalCount < 1) {
				page = new PageBean();
				page.setList(new ArrayList());
			}
			else {
				
				page = new PageBean(totalCount, currentPage, pageSize);
				 
				if (params == null) {
					params = new HashMap();
				}
				
				params.put("firstResult", page.getPerpage() * (page.getCurrentPage() - 1));
				params.put("maxResult", page.getPerpage());
				
				List list = this.getSqlSession().selectList(getSqlStatement(selectRecId), params);
				page.setList(list);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement(selectRecCountId) + ", " + getSqlStatement(selectRecId), e);
		}
		
		return page;
	}
}
