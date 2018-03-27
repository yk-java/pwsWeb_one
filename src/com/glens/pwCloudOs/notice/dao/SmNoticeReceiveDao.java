package com.glens.pwCloudOs.notice.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.notice.vo.SmNoticeReceive;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.notice.dao.SmNoticeReceiveMapper")
public class SmNoticeReceiveDao extends EAPAbstractDao {

	public void insertSelective(SmNoticeReceive sm) {
		// TODO Auto-generated method stub
		try {
			this.getSqlSession().insert(getSqlStatement("insertSelective"), sm);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("insertSelective"), e);
		}
	}
}
