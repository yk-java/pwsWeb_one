package com.glens.pwCloudOs.pe.baseMgr.materialUse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pe.baseMgr.materialUse.dao.MaterialUseDao;
import com.glens.pwCloudOs.pe.baseMgr.projectInit.dao.ProjectInitDao;
import com.mongodb.util.Hash;

public class MaterialUserService extends EAPAbstractService {


	public int updateMaterialCount(int  num,String materialBatchno) {

		MaterialUseDao dao=(MaterialUseDao)getDao();

		Map map=new HashMap();
		map.put("curcountStorage", num);
		map.put("materialBatchno", materialBatchno);
		// TODO Auto-generated method stub
		return dao.updateMaterialCount(map);
	}


	public List<Map<String, Object>> getMaterialType(String companyCode) {

		MaterialUseDao dao=(MaterialUseDao)getDao();
		Map<String, Object> map=new HashMap<String,Object>();

		map.put("companyCode", companyCode);
		return dao.getMaterialType(map);
	}

	public List<Map<String, Object>> getMaterialBase(String materialtypeCode) {

		MaterialUseDao dao=(MaterialUseDao)getDao();
		Map<String, Object> map=new HashMap<String,Object>();

		map.put("materialtypeCode", materialtypeCode);
		return dao.getMaterialBase(map);
	}


	public List<Map<String, Object>> getPros(String companyCode) {

		MaterialUseDao dao=(MaterialUseDao)getDao();
		Map<String, Object> map=new HashMap<String,Object>();

		map.put("companyCode", companyCode);
		return dao.getPros(map);
	}

}
