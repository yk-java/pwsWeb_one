package com.glens.pwCloudOs.km.attach.dao;

import java.util.List;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.km.attach.vo.KmAttachment;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.km.attach.dao.kmAttachmentMapper")
public class KmAttachmentDao extends EAPAbstractDao {

	public List<KmAttachment> queryAttachList(String fileCode) {
		try {

			return this.getSqlSession().selectList(
					getSqlStatement("queryAttachList"), fileCode);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("queryAttachList"), e);
		}
	}

}
