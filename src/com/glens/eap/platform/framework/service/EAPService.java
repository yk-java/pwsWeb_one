package com.glens.eap.platform.framework.service;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.framework.beans.PageBean;

@SuppressWarnings("rawtypes")
public interface EAPService {

	public int queryForCount(Map parameters);

	public PageBean queryForPage(Map parameters, int currentPage);

	public PageBean queryForPage(Map parameters, int currentPage, int perpage);

	public List queryForList(Object parameters);

	public ValueObject findById(Object parameters);

	public boolean insert(Object parameters);

	public int update(Object parameters);

	public int delete(Object parameters);
}
