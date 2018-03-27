package com.glens.eap.sys.funcConfig.role.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.sys.funcConfig.role.dao.SmRoleDao;
import com.glens.eap.sys.funcConfig.role.vo.SmRole;
import com.glens.eap.sys.funcConfig.role.vo.SmRoleModule;
import com.glens.eap.sys.funcConfig.role.web.SmRoleForm;

public class SmRoleService extends EAPAbstractService {
	
	
	public List getRoleList(Object params) {
		
		SmRoleDao dao = (SmRoleDao) this.dao;
		return dao.getRoleList(params);
	}

	public boolean saveSmRole(Object smrole) {
		SmRoleDao dao = (SmRoleDao) this.dao;
		SmRole role=(SmRole)smrole;
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		String roleCode = codeWorker.createCode("R");
		role.setRoleCode(roleCode);
		dao.insert(smrole);
		return true;
	}

	

	public Map deleteSmRole(SmRole smrole) {
		// TODO Auto-generated method stub
		SmRoleDao dao = (SmRoleDao) this.dao;
		dao.delete(smrole.getRoleCode());
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}
	
	

	

	public Map queryModuleList(SmRoleForm form) {
		Map result = new HashMap();
		SmRoleDao dao = (SmRoleDao) this.dao;
		List<Map<String, Object>> list = dao
				.queryModuleList(form.getRoleCode());
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("data", list);
		return result;
	}

	public Map linkModule(SmRole smrole) {
		Map result = new HashMap();
		SmRoleDao dao = (SmRoleDao) this.dao;
		dao.deleteRoleModule(smrole.getRoleCode());
		String[] arr = smrole.getModuleCodes().split(",");
		Map<String, Object> m = new HashMap<String, Object>();
		List<SmRoleModule> roleModuleList = new ArrayList<SmRoleModule>();
		for (String s : arr) {
			SmRoleModule rm = new SmRoleModule();
			rm.setModuleCode(s);
			rm.setRoleCode(smrole.getRoleCode());
			roleModuleList.add(rm);
		}
		m.put("list", roleModuleList);
		dao.insertRoleModule(m);
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	
}