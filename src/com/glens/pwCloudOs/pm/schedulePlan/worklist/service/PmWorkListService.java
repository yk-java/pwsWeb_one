package com.glens.pwCloudOs.pm.schedulePlan.worklist.service;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.dao.PmWorkListDao;

public class PmWorkListService extends EAPAbstractService {
	
	public Object projectDayWorkHours(Map<String, Object> parameters) {
		PmWorkListDao pmWorkListDao = (PmWorkListDao)this.dao;
		return pmWorkListDao.projectDayWorkHours(parameters);
	}
	
	public List<Map<String, Object>> kpiDayWorkload(Map<String, Object> parameters){
		PmWorkListDao pmWorkListDao = (PmWorkListDao)this.dao;
		return pmWorkListDao.kpiDayWorkload(parameters);
	} 
	
	public List<Map<String, Object>> inOutPerCntAndDescr(Map<String, Object> parameters){
		PmWorkListDao pmWorkListDao = (PmWorkListDao)this.dao;
		return pmWorkListDao.inOutPerCntAndDescr(parameters);
	} 
}
