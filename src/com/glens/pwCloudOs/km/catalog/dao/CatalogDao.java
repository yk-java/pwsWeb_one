package com.glens.pwCloudOs.km.catalog.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.km.catalog.dao.CatalogVoMapper")
public class CatalogDao extends EAPAbstractDao {

	public List getTreeList(Object parameters) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("getTreeList"), parameters);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getTreeList"), e);
		}
	}

	@Override
	public boolean insert(Object parameters) {
		// TODO Auto-generated method stub

		try {

			this.getSqlSession().insert(getSqlStatement(INSERT_STATEMENT),
					parameters);

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement(INSERT_STATEMENT), e);

		}
	}
	
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

	public List queryKmCatalogList() {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryKmCatalogList"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryKmCatalogList"), e);
		}
	}

}
