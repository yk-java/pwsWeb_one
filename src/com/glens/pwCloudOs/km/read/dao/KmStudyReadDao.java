package com.glens.pwCloudOs.km.read.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.km.read.vo.KmStudyRead;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.km.read.dao.KmStudyReadMapper")
public class KmStudyReadDao extends EAPAbstractDao {

	public boolean insertSelective(KmStudyRead read) {
		// TODO Auto-generated method stub
		try {

			this.getSqlSession().insert(getSqlStatement("insertSelective"),
					read);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);

		}
	}

	public int updateSelective(KmStudyRead r) {
		// TODO Auto-generated method stub
		try {

			int i = this.getSqlSession().update(
					getSqlStatement("updateByPrimaryKeySelective"), r);

			return i;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateByPrimaryKeySelective"), e);

		}
	}

	public int updatereadNum(String fileCode) {
		// TODO Auto-generated method stub
		try {

			int i = this.getSqlSession().update(
					getSqlStatement("updatereadNum"), fileCode);

			return i;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updatereadNum"), e);

		}
	}

}
