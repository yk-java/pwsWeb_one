package com.glens.pwCloudOs.pm.taskMgr.taskClass.service;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.materielMg.comprehensiveQuery.dao.ComprehensiveQueryDao;
import com.glens.pwCloudOs.pm.taskMgr.taskClass.dao.TaskClassDao;

public class TaskClassService extends EAPAbstractService {

	
	public List<Map<String, Object>> getKpiList(Map<String, String> params) {
		
		TaskClassDao queryDao = (TaskClassDao) getDao();
		
		return queryDao.getKpiList(params);
	}
	

}
