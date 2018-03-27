package com.glens.eap.platform.framework.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

public class EAPCommonDao extends EAPAbstractDao {

	public EAPCommonDao() {
		// TODO Auto-generated constructor stub
	}
	
	public EAPCommonDao(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated constructor stub
		this.setSqlSessionFactory(sqlSessionFactory);
	}
	
	/**
	 * <p>初始化方法.</p>
	 * @author:[创建者中文名字]
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		//覆盖父类初始化方法，目的是不需要实现。
	}
	
	public List queryForList(String statement, Object parameter) {
		
		List list = this.getSqlSession().selectList(statement, parameter);
		
		return list;
	}
	
	public Object selectOne(String statement, Object parameter) {
		
		Object result = this.getSqlSession().selectOne(statement, parameter);
		
		return result;
	}
	
}
