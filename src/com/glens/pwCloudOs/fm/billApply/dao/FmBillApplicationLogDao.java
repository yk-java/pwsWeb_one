package com.glens.pwCloudOs.fm.billApply.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.fm.billApply.vo.FmBillApplicationLog;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.fm.billApply.dao.FmBillApplicationLogMapper")
public class FmBillApplicationLogDao extends EAPAbstractDao {

	public void insertSelective(FmBillApplicationLog log) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession()
					.insert(getSqlStatement("insertSelective"), log);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);
		}
	}

}
