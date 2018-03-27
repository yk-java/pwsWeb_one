package com.glens.pwCloudOs.fm.expense.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.fm.expense.vo.FmMoneyReturnDetail;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.fm.expense.dao.FmMoneyReturnDetailMapper")
public class FmMoneyReturnDetailDao extends EAPAbstractDao {

	public boolean insertSelective(FmMoneyReturnDetail d) {
		// TODO Auto-generated method stub
		try {

			this.getSqlSession().insert(getSqlStatement("insertSelective"), d);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);

		}
	}

	public void deleteFmReturnDetail(Long feeId) {
		// TODO Auto-generated method stub
		try {

			this.getSqlSession().delete(
					getSqlStatement("deleteFmReturnDetail"), feeId);

		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("deleteFmReturnDetail"), e);

		}
	}

	public List<FmMoneyReturnDetail> queryDetail(Long feedId) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryDetail"), feedId);

		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryDetail"), e);

		}
	}

}
