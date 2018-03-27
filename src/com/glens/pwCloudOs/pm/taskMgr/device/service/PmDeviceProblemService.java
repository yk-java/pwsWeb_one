package com.glens.pwCloudOs.pm.taskMgr.device.service;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.taskMgr.device.dao.PmDeviceProblemDao;
import com.glens.pwCloudOs.pm.taskMgr.device.vo.PmDeviceProblem;

public class PmDeviceProblemService extends EAPAbstractService {
	
	@Override
	public boolean insert(Object parameters) {
		PmDeviceProblemDao pmDeviceProblemDao = (PmDeviceProblemDao)this.dao;
		PmDeviceProblem vo = (PmDeviceProblem)pmDeviceProblemDao.findByProblemCode(parameters);
		if(vo==null){
			this.dao.insert(parameters);
			return true;
		}else{
			return false;
		}
	}
}
