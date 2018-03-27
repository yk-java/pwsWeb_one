package com.glens.eap.platform.framework.service.impl;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.EAPDao;
import com.glens.eap.platform.framework.service.EAPService;

@SuppressWarnings("rawtypes")
public abstract class EAPAbstractService implements EAPService {

	protected EAPDao dao;
	
	public EAPDao getDao() {
		return dao;
	}

	public void setDao(EAPDao dao) {
		this.dao = dao;
	}

	@Override
	public int delete(Object parameters) {
		// TODO Auto-generated method stub
		return this.dao.delete(parameters);
	}

	@Override
	public ValueObject findById(Object parameters) {
		// TODO Auto-generated method stub
		return this.dao.findById(parameters);
	}

	@Override
	public boolean insert(Object parameters) {
		// TODO Auto-generated method stub
		return this.dao.insert(parameters);
	}

	@Override
	public int queryForCount(Map parameters) {
		// TODO Auto-generated method stub
		return this.dao.queryForCount(parameters);
	}

	@Override
	public List queryForList(Object parameters) {
		// TODO Auto-generated method stub
		return this.dao.queryForList(parameters);
	}

	@Override
	public PageBean queryForPage(Map parameters, int currentPage) {
		// TODO Auto-generated method stub
		return this.dao.queryForPage(parameters, currentPage);
	}

	@Override
	public PageBean queryForPage(Map parameters, int currentPage, int perpage) {
		// TODO Auto-generated method stub
		return this.dao.queryForPage(parameters, currentPage, perpage);
	}

	@Override
	public int update(Object parameters) {
		// TODO Auto-generated method stub
		return this.dao.update(parameters);
	}

}
