package com.glens.pwCloudOs.notice.dao;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.notice.vo.SmNotice;
import com.glens.pwCloudOs.notice.vo.SmNoticeReceive;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.notice.dao.SmNoticeMapper")
public class SmNoticeDao extends EAPAbstractDao {

	public List<Map<String, Object>> queryAllNoticeList(String accountCode) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryAllNoticeList"), accountCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryAllNoticeList"), e);
		}
	}

	public List<Map<String, Object>> queryUnReadNoticeList(String accountCode) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryUnReadNoticeList"), accountCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryUnReadNoticeList"), e);
		}
	}

	public List<Map<String, Object>> queryReadNoticeList(String accountCode) {
		try {
			return this.getSqlSession().selectList(
					getSqlStatement("queryReadNoticeList"), accountCode);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryReadNoticeList"), e);
		}
	}

	public void insertSelective(SmNotice sm) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().insert(getSqlStatement("insertSelective"), sm);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);
		}
	}

}
