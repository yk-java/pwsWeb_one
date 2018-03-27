package com.glens.eap.platform.framework.service.impl;

import java.util.List;

import com.glens.eap.platform.framework.dao.impl.EAPCommonDao;

@SuppressWarnings("rawtypes")
public class EAPCommonService extends EAPAbstractService {
	
	public List queryForList(String statement, Object parameter) {
		
		EAPCommonDao commonDao = (EAPCommonDao) this.dao;
		
		List list = commonDao.queryForList(statement, parameter);
		
		return list;
	}
	
	public Object selectOne(String statement, Object parameter) {
		
		EAPCommonDao commonDao = (EAPCommonDao) this.dao;
		
		Object result = commonDao.selectOne(statement, parameter);
		
		return result;
	}
	
}
