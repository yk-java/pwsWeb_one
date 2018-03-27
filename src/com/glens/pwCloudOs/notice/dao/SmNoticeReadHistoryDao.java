package com.glens.pwCloudOs.notice.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.notice.vo.SmNoticeReadHistory;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.notice.dao.SmNoticeReadHistoryMapper")
public class SmNoticeReadHistoryDao extends EAPAbstractDao {

	public List<SmNoticeReadHistory> queryList(Map<String, Object> m) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryList"), m);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryList"), e);
		}
	}

	public void insertSelective(SmNoticeReadHistory sr) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().insert(getSqlStatement("insertSelective"), sr);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);
		}
	}

	public void updateSelective(SmNoticeReadHistory sr) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().update(
					getSqlStatement("updateByPrimaryKeySelective"), sr);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("updateByPrimaryKeySelective"), e);
		}
	}

}
