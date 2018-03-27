package com.glens.pwCloudOs.materielMg.goods.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.materielMg.goods.vo.Goods;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.materielMg.goods.dao.GoodsMapper")
public class GoodsDao extends EAPAbstractDao {

	public Map getGoodsCount(Map paramsMap) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("getGoodsCount"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getGoodsCount"), e);
		}
	}

	public void insertSelective(Goods goods) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().insert(getSqlStatement("insertSelective"),
					goods);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);
		}
	}

	public List queryGoodsList(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryGoodsList"), paramsMap);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryGoodsList"), e);
		}
	}

	public void updateSelective(Goods goods) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().update(
					getSqlStatement("updateByPrimaryKeySelective"), goods);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateByPrimaryKeySelective"), e);
		}
	}

}
