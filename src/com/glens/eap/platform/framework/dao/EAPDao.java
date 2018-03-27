package com.glens.eap.platform.framework.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.framework.beans.PageBean;

@SuppressWarnings("rawtypes")
public interface EAPDao {

	public int queryForCount(Map parameters);
	
	public PageBean queryForPage(Map parameters, int currentPage);
	
	public PageBean queryForPage(Map parameters, int currentPage, int perpage);
	
	public List queryForList(Object parameters);
	
	public ValueObject findById(Object parameters);
	
	public boolean insert(Object parameters);
	
	public int update(Object parameters);
	
	public int delete(Object parameters);
	/**
	 * Edit by Madx
	 * @param params
	 * @param selectRecCntId
	 * @param selectRecId
	 * @return
	 */
	public PageBean queryForPage(Map<String, Object> params, String selectRecCntId, String selectRecId);
	
}
