package com.glens.pwCloudOs.pm.everydayMgr.expInfoColle.service;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.materielMg.comprehensiveQuery.dao.ComprehensiveQueryDao;
import com.glens.pwCloudOs.pm.everydayMgr.expInfoColle.dao.ExpInfoColleDao;

public class ExpInfoColleService extends EAPAbstractService {
	
	
	
	public List<Map<String, Object>> getProManger() {
		
		ExpInfoColleDao queryDao = (ExpInfoColleDao) getDao();
		
		return queryDao.getProManger();
	}
	

}
