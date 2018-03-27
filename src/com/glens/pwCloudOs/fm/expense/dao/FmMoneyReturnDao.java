package com.glens.pwCloudOs.fm.expense.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.fm.expense.vo.FmMoneyReturn;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.fm.expense.dao.FmMoneyReturnMapper")
public class FmMoneyReturnDao extends EAPAbstractDao {

	
	public boolean insertSelective(FmMoneyReturn r) {
		try {

			this.getSqlSession().insert(getSqlStatement("insertSelective"), r);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);

		}
	}

	public boolean updateSelective(FmMoneyReturn r) {
		// TODO Auto-generated method stub
		try {

			this.getSqlSession().update(
					getSqlStatement("updateByPrimaryKeySelective"), r);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateByPrimaryKeySelective"), e);

		}
	}
	
	
	public boolean updateSelective1(FmMoneyReturn r) {
		// TODO Auto-generated method stub
		try {

			this.getSqlSession().update(
					getSqlStatement("updateByPrimaryKeySelective1"), r);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateByPrimaryKeySelective1"), e);

		}
	}
	

	public List<FmMoneyReturn> queryFmMoneyReturnList(String workflowCode) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryFmMoneyReturnList"), workflowCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryFmMoneyReturnList"), e);

		}
	}

	public FmMoneyReturn queryFmMoneyReturnByRowid(Long rowid) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryFmMoneyReturnByRowid"), rowid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryFmMoneyReturnByRowid"), e);

		}
	}

}
