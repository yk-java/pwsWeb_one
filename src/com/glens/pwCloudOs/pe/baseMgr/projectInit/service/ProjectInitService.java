package com.glens.pwCloudOs.pe.baseMgr.projectInit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pe.baseMgr.projectInit.dao.ProjectInitDao;


public class ProjectInitService extends EAPAbstractService {


	public List<Map<String, Object>> getPmMct(String mctCode,String companyCode) {

		ProjectInitDao queryDao = (ProjectInitDao) getDao();
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("proNo", mctCode);
		map.put("companyCode", companyCode);
		return queryDao.getPmMct(map);
	}


	public List<Map<String, Object>> treeList(String companyCode) {

		ProjectInitDao queryDao = (ProjectInitDao) getDao();
		Map<String, Object> map=new HashMap<String,Object>();

		map.put("companyCode", companyCode);
		return queryDao.treeList(map);
	}
	public List<Map<String, Object>> treeList1(String companyCode,String categoryCode) {

		ProjectInitDao queryDao = (ProjectInitDao) getDao();
		Map<String, Object> map=new HashMap<String,Object>();

		map.put("companyCode", companyCode);
		map.put("categoryCode", categoryCode);
		return queryDao.treeList1(map);
	}
	public List<Map<String, Object>> treeList2(String companyCode,String categoryCode,String  proNo) {

		ProjectInitDao queryDao = (ProjectInitDao) getDao();
		Map<String, Object> map=new HashMap<String,Object>();

		map.put("companyCode", companyCode);
		map.put("categoryCode", categoryCode);
		map.put("proNo", proNo);
		return queryDao.treeList2(map);
	}

}
