package com.glens.pwCloudOs.materielMg.goods.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.materielMg.goods.vo.StoreHouse;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.materielMg.goods.dao.StoreHouseMapper")
public class StoreHouseDao extends EAPAbstractDao {

	public List<Map<String, Object>> getStoreHouseList() {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("getStoreHouseList"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getStoreHouseList"), e);
		}
	}

}
