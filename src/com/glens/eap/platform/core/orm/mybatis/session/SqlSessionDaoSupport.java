package com.glens.eap.platform.core.orm.mybatis.session;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

public abstract class SqlSessionDaoSupport {

	private SqlSession sqlSession;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

}
