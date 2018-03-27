package com.glens.pwCloudOs.fm.expense.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.fm.expense.vo.FmFeeType;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.fm.expense.dao.FmFeeTypeMapper")
public class FmFeeTypeDao extends EAPAbstractDao {

	public List getTreeList(Map map) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("getTreeList"), map);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("getTreeList"), e);
		}
	}

	public int insertSelective(FmFeeType ftype) {
		try {
			return this.getSqlSession().insert(
					getSqlStatement("insertSelective"), ftype);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);
		}
	}

	public Map queryFeeType(Map map) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryFeeType"), map);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryFeeType"), e);
		}
	}

	public int updateSelective(FmFeeType ftype) {
		try {
			return this.getSqlSession().update(
					getSqlStatement("updateByPrimaryKeySelective"), ftype);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateByPrimaryKeySelective"), e);
		}
	}

	public FmFeeType queryFeeTypeByCode(String code) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryFeeTypeByCode"), code);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryFeeTypeByCode"), e);
		}
	}

	public List<Map<String, Object>> queryAllFeeType() {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryAllFeeType"));
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryAllFeeType"), e);
		}
	}

}
