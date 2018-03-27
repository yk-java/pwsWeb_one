package com.glens.pwCloudOs.pe.baseMgr.rpMaterialBase.service;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pe.baseMgr.rpMaterialBase.dao.RpMaterialBaseDao;

public class RpMaterialBaseService extends EAPAbstractService {
	
	
	
public List<Map<String, Object>> getMaterialtype(Object params) {
		
		RpMaterialBaseDao queryDao = (RpMaterialBaseDao) getDao();
		
		return queryDao.getMaterialtype(params);
	}

}
