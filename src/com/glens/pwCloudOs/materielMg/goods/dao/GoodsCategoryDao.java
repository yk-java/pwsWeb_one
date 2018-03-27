package com.glens.pwCloudOs.materielMg.goods.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.materielMg.goods.vo.GoodsCategory;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.materielMg.goods.dao.GoodsCategoryMapper")
public class GoodsCategoryDao extends EAPAbstractDao {

	public List<GoodsCategory> getGoodsList() {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("getGoodsList"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getGoodsList"), e);
		}
	}

}
