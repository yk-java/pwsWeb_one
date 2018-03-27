package com.glens.eap.sys.funcConfig.module.service;

import java.util.ArrayList;
import java.util.List;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.sys.funcConfig.module.dao.AppModuleDao;
import com.glens.eap.sys.funcConfig.module.vo.AppModuleVo;
import com.glens.pwCloudOs.materielMg.assetMg.asset.vo.AssetVo;

public class AppModuleService extends EAPAbstractService {
	
	public List queryAll(Object parameters) {
		AppModuleDao dao=(AppModuleDao)getDao();
		return dao.queryAll(parameters);
	}
	
	public int deleteRoleModule(Object parameters) {
		// TODO Auto-generated method stub
		AppModuleDao dao=(AppModuleDao)getDao();
		return dao.deleteRoleModule(parameters);
	}
	
	public int updateRoleModule(String roleCode,String modules) {
		// TODO Auto-generated method stub
		AppModuleDao dao=(AppModuleDao)getDao();
		List<AppModuleVo> moduleList = new ArrayList<AppModuleVo>();
		
		
		
		String[] str=modules.split(",");
		for(int i=0;i<str.length;i++){
			AppModuleVo vo=new AppModuleVo();
			vo.setRoleCode(roleCode);
			vo.setModuleCode(str[i]);
			moduleList.add(vo);
		}
		
		return dao.updateRoleModule(moduleList);
	}
	
}