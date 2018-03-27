package com.glens.eap.sys.orgEmployee.orgunit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.sys.orgEmployee.employee.dao.MdEmployeeDao;
import com.glens.eap.sys.orgEmployee.employee.vo.MdEmployee;
import com.glens.eap.sys.orgEmployee.orgunit.dao.OrgUnitDao;
import com.glens.eap.sys.orgEmployee.orgunit.vo.OrgUnit;

public class OrgUnitService extends EAPAbstractService {

	public int updateArea(Object parameters) {
		// TODO Auto-generated method stub

		OrgUnitDao dao = (OrgUnitDao) getDao();

		return dao.updateArea(parameters);
	}

	public List<OrgUnit> queryOrgUnitList(String companyCode) {

		OrgUnitDao orgUnitDao = (OrgUnitDao) this.dao;

		MdEmployeeDao mdEmployeeDao = (MdEmployeeDao) EAPContext.getContext()
				.getBean("mdEmployeeDao");

		// List<OrgUnit> resultList = new ArrayList<OrgUnit>();
		List<OrgUnit> list = orgUnitDao.queryOrgUnitList(companyCode);
		// 主管姓名
		for (OrgUnit ou : list) {
			String employeeCode = ou.getEmployeeCode();
			if (StringUtil.isNotNull(employeeCode)) {
				MdEmployee me = mdEmployeeDao.queryEmployeeByCode(employeeCode);
				if (me != null)
					ou.setEmployeeName(me.getEmployeename());
			}
		}
		// calcOrgUnit(resultList, list);

		return list;
	}

	public List<OrgUnit> getPartOrgUnitList(String companyCode) {

		OrgUnitDao orgUnitDao = (OrgUnitDao) this.dao;

		MdEmployeeDao mdEmployeeDao = (MdEmployeeDao) EAPContext.getContext()
				.getBean("mdEmployeeDao");

		// List<OrgUnit> resultList = new ArrayList<OrgUnit>();
		List<OrgUnit> list = orgUnitDao.getPartOrgUnitList(companyCode);
		// 主管姓名
		for (OrgUnit ou : list) {
			String employeeCode = ou.getEmployeeCode();
			if (StringUtil.isNotNull(employeeCode)) {
				MdEmployee me = mdEmployeeDao.queryEmployeeByCode(employeeCode);
				if (me != null)
					ou.setEmployeeName(me.getEmployeename());
			}
		}
		// calcOrgUnit(resultList, list);

		return list;
	}

	private void calcOrgUnit(List<OrgUnit> resultList, List<OrgUnit> list) {
		// 一级部门
		for (OrgUnit m : list) {
			String parentUnit = m.getParentUnit();
			if (StringUtil.isNull(parentUnit) || "0".equals(parentUnit)) {
				resultList.add(m);
			}
		}

		// 二级菜单
		for (OrgUnit m : resultList) {
			String unitCode = m.getUnitCode();
			List<OrgUnit> children = new ArrayList<OrgUnit>();
			for (OrgUnit m1 : list) {
				String parentUnit = m1.getParentUnit();
				if (unitCode.equals(parentUnit)) {
					children.add(m1);
				}
			}
			m.setChildren(children);
		}

		// 三级菜单
		for (OrgUnit m : resultList) {
			List<OrgUnit> childrenList = (List<OrgUnit>) m.getChildren();
			for (OrgUnit m1 : childrenList) {
				String unitCode = m1.getUnitCode();
				List<OrgUnit> children = new ArrayList<OrgUnit>();
				for (OrgUnit m2 : list) {
					String parentUnit = m2.getParentUnit();
					if (unitCode.equals(parentUnit)) {
						children.add(m2);
					}
				}
				m1.setChildren(children);
			}
		}
	}

	public Map<String, String> saveOrgUnit(OrgUnit orgUnit) {
		// TODO Auto-generated method stub
		OrgUnitDao orgUnitDao = (OrgUnitDao) this.dao;

		String parentUnitCode = orgUnit.getParentUnit();
		String unitName = orgUnit.getUnitName();
		Map<String, String> result = new HashMap<String, String>();
		if (hasOrgUnit(parentUnitCode, unitName, null)) {
			result.put("statusCode", ResponseConstants.NO_DATA);
			result.put("resultMsg", "组织机构名称不能重复！");
		} else {
			// 这里需要判断名称是否重复
			orgUnit.setSysProcessFlag("1");
			orgUnit.setSysCreateTime(new Date());

			CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
					.getBean(CodeWorker.SIMPLE_CODE_WORKER);
			orgUnit.setUnitCode(codeWorker.createCode("O"));
			orgUnitDao.saveOrgUnit(orgUnit);

			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
			result.put("unitCode", orgUnit.getUnitCode());
		}

		return result;
	}

	private boolean hasOrgUnit(String parentUnitCode, String unitName,
			String unitCode) {
		OrgUnitDao orgUnitDao = (OrgUnitDao) this.dao;

		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("parentUnitCode", parentUnitCode);
		paramsMap.put("unitName", unitName);
		if (StringUtil.isNotNull(unitCode)) {
			paramsMap.put("unitCode", unitCode);
		}
		List<OrgUnit> list = orgUnitDao.queryOrgList(paramsMap);
		if (list.size() > 0)
			return true;
		else
			return false;
	}

	public Map<String, String> UpdateOrgUnit(OrgUnit orgUnit) {
		OrgUnitDao orgUnitDao = (OrgUnitDao) this.dao;
		String parentUnitCode = orgUnit.getParentUnit();
		String unitName = orgUnit.getUnitName();
		String unitCode = orgUnit.getUnitCode();
		Map<String, String> result = new HashMap<String, String>();
		if (hasOrgUnit(parentUnitCode, unitName, unitCode)) {
			result.put("statusCode", ResponseConstants.NO_DATA);
			result.put("resultMsg", "组织机构名称不能重复！");
		} else {
			// 这里需要判断名称是否重复
			orgUnit.setSysUpdateTime(new Date());
			orgUnitDao.updateOrgUnit(orgUnit);

			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
		}

		return result;
	}

	public Map<String, Object> queryOrgUnit(String unitCode) {
		// TODO Auto-generated method stub
		OrgUnitDao orgUnitDao = (OrgUnitDao) this.dao;
		Map<String, Object> result = new HashMap<String, Object>();
		OrgUnit ou = orgUnitDao.queryOrgUnit(unitCode);
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("data", ou);
		return result;
	}

	public Map queryUnitLeaders(String unitCode) {
		// TODO Auto-generated method stub
		OrgUnitDao orgUnitDao = (OrgUnitDao) this.dao;
		return orgUnitDao.queryUnitLeaders(unitCode);
	}

}
