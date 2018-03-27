package com.glens.pwCloudOs.login.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.eapAuth.core.authentication.handlers.PasswordEncoder;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.sys.orgEmployee.employee.dao.MdEmployeeDao;
import com.glens.pwCloudOs.login.dao.LoginDao;

public class LoginService extends EAPAbstractService {

	private MdEmployeeDao mdEmployeeDao;

	public UserToken getAccountByAccountName(String accountName) {

		LoginDao loginDao = (LoginDao) this.dao;

		return loginDao.getAccountByAccountName(accountName);
	}

	public String getPswByAccountName(String accountName) {

		LoginDao loginDao = (LoginDao) this.dao;

		return loginDao.getPswByAccountName(accountName);
	}

	public List<Map<String, Object>> queryModuleList(String accountName) {
		LoginDao loginDao = (LoginDao) this.dao;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list = loginDao.queryModuleList(accountName);
		calcModule(resultList, list);
		return resultList;
	}

	private void calcModule(List<Map<String, Object>> resultList,
			List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		// 一级菜单
		for (Map<String, Object> m : list) {
			String parentModuleCode = (String) m.get("parentModuleCode");
			if (parentModuleCode.equals("0")) {
				resultList.add(m);
			}
		}

		// 二级菜单
		for (Map<String, Object> m : resultList) {
			String moduleCode = (String) m.get("moduleCode");
			List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> m1 : list) {
				String parentModuleCode = (String) m1.get("parentModuleCode");
				if (moduleCode.equals(parentModuleCode)) {
					children.add(m1);
				}
			}
			m.put("children", children);
		}

		// 三级菜单
		for (Map<String, Object> m : resultList) {
			List<Map<String, Object>> childrenList = (List<Map<String, Object>>) m
					.get("children");
			for (Map<String, Object> m1 : childrenList) {
				String moduleCode = (String) m1.get("moduleCode");
				List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
				for (Map<String, Object> m2 : list) {
					String parentModuleCode = (String) m2
							.get("parentModuleCode");
					if (moduleCode.equals(parentModuleCode)) {
						children.add(m2);
					}
				}
				m1.put("children", children);
			}
		}
	}

	public int updateUserPsw(String accountCode, String psw) {

		LoginDao loginDao = (LoginDao) this.dao;
		Map<String, String> params = new HashMap<String, String>();
		params.put("accountCode", accountCode);
		params.put("psw", psw);
		return loginDao.updateUserPsw(params);
	}

	public int updateUserPswByMobile(String mobilePhone, String accountName,
			String psw) {

		LoginDao loginDao = (LoginDao) this.dao;

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// paramsMap.put("mobilePhone", mobilePhone);
		paramsMap.put("mobilePhone", mobilePhone);
		paramsMap.put("accountName", accountName);
		Map m = mdEmployeeDao.queryEmployeeInfo(paramsMap);
		if (null == m) {
			return 0;
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("accountName", accountName);
		params.put("psw", psw);
		return loginDao.updateUserPswByMobile(params);
	}

	public void setMdEmployeeDao(MdEmployeeDao mdEmployeeDao) {
		this.mdEmployeeDao = mdEmployeeDao;
	}

}
