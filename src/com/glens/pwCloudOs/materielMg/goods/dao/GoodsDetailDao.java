package com.glens.pwCloudOs.materielMg.goods.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.materielMg.goods.vo.GoodsDetail;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.materielMg.goods.dao.GoodsDetailMapper")
public class GoodsDetailDao extends EAPAbstractDao {

	public void insertSelective(GoodsDetail goodsDetail) {
		// TODO Auto-generated method stub
		try {

			this.getSqlSession().insert(getSqlStatement("insertSelective"),
					goodsDetail);

		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);

		}
	}

}
