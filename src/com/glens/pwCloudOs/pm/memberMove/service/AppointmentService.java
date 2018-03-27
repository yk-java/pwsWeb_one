package com.glens.pwCloudOs.pm.memberMove.service;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.memberMove.dao.AppointmentDao;

public class AppointmentService extends EAPAbstractService {

	public boolean insert(Object parameters) {
		// TODO Auto-generated method stub
		AppointmentDao dao=(AppointmentDao)getDao();
		
		return dao.insert(parameters);
	}
	
	public int updateManager(Object parameters) {
		AppointmentDao dao=(AppointmentDao)getDao();
		
		return dao.updateManager(parameters);
	}
	
}
