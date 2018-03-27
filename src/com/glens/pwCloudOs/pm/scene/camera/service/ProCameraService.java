package com.glens.pwCloudOs.pm.scene.camera.service;

import java.util.Map;

import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.scene.camera.dao.ProCameraDao;
import com.glens.pwCloudOs.pm.scene.camera.vo.ProCamera;

public class ProCameraService extends EAPAbstractService {

	public Map getCamera(ValueObject vo) {
		// TODO Auto-generated method stub
		ProCameraDao dao = (ProCameraDao) getDao();
		ProCamera camera = (ProCamera) vo;
		return dao.getCamera(camera.getRowid());
	}

}
