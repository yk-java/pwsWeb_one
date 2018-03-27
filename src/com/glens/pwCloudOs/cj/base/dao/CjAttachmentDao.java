package com.glens.pwCloudOs.cj.base.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.cj.base.dao.CjAttachmentMapper")
public class CjAttachmentDao extends EAPAbstractDao {
	public int delByIdList(List<String> attachmentIdList) {
		try {
			return this.getSqlSession().delete(
					getSqlStatement("[delByIdList]"), attachmentIdList);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[delByIdList]"), e);
		}
	}
	
	public int delByObjId(String attachmentAttachId) {
		try {
			return this.getSqlSession().delete(
					getSqlStatement("[delByObjId]"), attachmentAttachId);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[delByObjId]"), e);
		}
	}
}
