package com.glens.pwCloudOs.addrLib.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.addrLib.dao.AddAddrClassVoMapper")
public class AddAddrClassDao extends EAPAbstractDao {
	public List queryAll(Object parameters) {
		try {
			return this.getSqlSession().selectList(getSqlStatement("queryAll"), parameters);
		}catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("queryAll"), e);
		}
	}
	
	public List getDatasourceList() {
		try {
			return this.getSqlSession().selectList(getSqlStatement("getDatasourceList"), null);
		}catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql=" + getSqlStatement("getDatasourceList"), e);
		}
	}
	
}
