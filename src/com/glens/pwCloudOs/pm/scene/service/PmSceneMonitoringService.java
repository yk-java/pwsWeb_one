package com.glens.pwCloudOs.pm.scene.service;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.scene.dao.PmSceneMonitoringDao;
import com.glens.pwCloudOs.pm.scene.vo.PmSceneMonitoring;

public class PmSceneMonitoringService extends EAPAbstractService {

	public List<PmSceneMonitoring> statistics(Map<String, Object> params) {
		PmSceneMonitoringDao monitoringDao = (PmSceneMonitoringDao) this.dao;
		return monitoringDao.statistics(params);
	}

	public List querySceneList(Map map) {
		// TODO Auto-generated method stub
		PmSceneMonitoringDao monitoringDao = (PmSceneMonitoringDao) this.dao;
		return monitoringDao.querySceneList(map);
	}

	public List queryHisList(Map map) {
		// TODO Auto-generated method stub
		PmSceneMonitoringDao monitoringDao = (PmSceneMonitoringDao) this.dao;
		return monitoringDao.queryHisList(map);
	}

	public List queryHeatHisList(Map map) {
		// TODO Auto-generated method stub
		PmSceneMonitoringDao monitoringDao = (PmSceneMonitoringDao) this.dao;
		return monitoringDao.queryHeatHisList(map);
	}

	public List queryReport(Map map) {
		// TODO Auto-generated method stub
		PmSceneMonitoringDao monitoringDao = (PmSceneMonitoringDao) this.dao;
		return monitoringDao.queryReport(map);
	}
}
