package com.glens.pwCloudOs.cj.base.dao;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;
import com.glens.pwCloudOs.cj.base.vo.CjPole;
import com.glens.pwCloudOs.cj.base.web.CjPoleForm;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.cj.base.dao.CjPoleMapper")
public class CjPoleDao extends EAPAbstractDao {
	public CjPole queryByXlGtCollectId(CjPoleForm form) {
		try {
			return this.getSqlSession().selectOne(
					getSqlStatement("queryByXlGtCollectId"), form.toMap());
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("[queryByXlGtCollectId]"), e);
		}
	}
}
