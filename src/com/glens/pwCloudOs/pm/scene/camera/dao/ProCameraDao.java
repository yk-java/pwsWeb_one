package com.glens.pwCloudOs.pm.scene.camera.dao;

import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value = "com.glens.pwCloudOs.pm.scene.camera.dao.ProCameraMapper")
public class ProCameraDao extends EAPAbstractDao {

	public Map getCamera(Long rowid) {
		// TODO Auto-generated method stub
		try {
			return this.getSqlSession().selectOne(getSqlStatement("findById"),
					rowid);
		} catch (Exception e) {
			throw new MyBatisAccessException("exe sql="
					+ getSqlStatement("findById"), e);
		}
	}

}
