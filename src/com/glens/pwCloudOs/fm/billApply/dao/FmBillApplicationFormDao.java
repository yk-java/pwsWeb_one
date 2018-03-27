package com.glens.pwCloudOs.fm.billApply.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.fm.billApply.vo.FmBillApplicationForm;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.fm.billApply.dao.FmBillApplicationFormMapper")
public class FmBillApplicationFormDao extends EAPAbstractDao {

	public int insertSelective(FmBillApplicationForm ftype) {
		try {
			return this.getSqlSession().insert(
					getSqlStatement("insertSelective"), ftype);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);
		}
	}

	public int updateSelective(FmBillApplicationForm ftype) {
		try {
			return this.getSqlSession().update(
					getSqlStatement("updateByPrimaryKeySelective"), ftype);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateByPrimaryKeySelective"), e);
		}
	}

	public List queryCompanyList() {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryCompanyList"), null);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryCompanyList"));
		}
	}

	public FmBillApplicationForm queryBillApplication(String applyCode) {
		try {

			return this.getSqlSession().selectOne(
					getSqlStatement("queryBillApplication"), applyCode);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryBillApplication"));
		}
	}

}
