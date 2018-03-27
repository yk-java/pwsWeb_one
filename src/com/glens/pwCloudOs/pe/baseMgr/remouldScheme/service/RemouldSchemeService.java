package com.glens.pwCloudOs.pe.baseMgr.remouldScheme.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pe.baseMgr.remouldScheme.dao.RemouldSchemeDao;

public class RemouldSchemeService extends EAPAbstractService {
	
	public List<Map<String, Object>> materialList(String companyCode,String remouldSchemeCode) {
		
		RemouldSchemeDao queryDao = (RemouldSchemeDao) getDao();
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("companyCode", companyCode);
		map.put("remouldSchemeCode", remouldSchemeCode);
		return queryDao.materialList(map);
	}
	public List<Map<String, Object>> getALLType(String companyCode) {
		
		RemouldSchemeDao queryDao = (RemouldSchemeDao) getDao();
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("companyCode", companyCode);
		return queryDao.getALLType(map);
	}
	public int deleteMaterial(String remouldSchemeCode,String companyCode) {
		
		RemouldSchemeDao queryDao = (RemouldSchemeDao) getDao();
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("companyCode", companyCode);
		map.put("remouldSchemeCode", remouldSchemeCode);
		return queryDao.deleteMaterial(map);
	}
	public int addMaterial(Object params) {
		
		RemouldSchemeDao queryDao = (RemouldSchemeDao) getDao();
		
		return queryDao.addMaterial(params);
	}
	
	
	

}
