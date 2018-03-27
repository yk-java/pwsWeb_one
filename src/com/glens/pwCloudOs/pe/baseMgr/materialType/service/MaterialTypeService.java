package com.glens.pwCloudOs.pe.baseMgr.materialType.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pe.baseMgr.materialType.dao.MaterialTypeDao;
import com.glens.pwCloudOs.pe.baseMgr.projectInit.dao.ProjectInitDao;

public class MaterialTypeService extends EAPAbstractService {
	
public List<Map<String, Object>> selectCount(String materialTypeName,String companyCode) {
		
		MaterialTypeDao queryDao = (MaterialTypeDao) getDao();
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("materialTypeName", materialTypeName);
		map.put("companyCode", companyCode);
		
		return queryDao.selectCount(map);
	}
	
}
