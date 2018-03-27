package com.glens.eap.sys.orgEmployee.employee.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.sys.orgEmployee.employee.dao.MdEmployeeDao;
import com.glens.eap.sys.orgEmployee.employee.vo.MdEmployee;
import com.glens.eap.sys.orgEmployee.employee.web.MdEmployeeForm;
import com.glens.eap.sys.orgEmployee.orgunit.dao.OrgUnitDao;
import com.glens.eap.sys.orgEmployee.orgunit.vo.OrgUnit;
import com.glens.pwCloudOs.weixin.util.WeixinThread;

public class MdEmployeeService extends EAPAbstractService {

	public List getUnits() {
		// TODO Auto-generated method stub

		MdEmployeeDao dao = (MdEmployeeDao) getDao();

		return dao.getUnits();
	}

	public List getJobs() {
		// TODO Auto-generated method stub

		MdEmployeeDao dao = (MdEmployeeDao) getDao();

		return dao.getJobs();
	}

	public List getProperty() {
		// TODO Auto-generated method stub

		MdEmployeeDao dao = (MdEmployeeDao) getDao();

		return dao.getProperty();
	}

	public List getContracts() {
		// TODO Auto-generated method stub

		MdEmployeeDao dao = (MdEmployeeDao) getDao();

		return dao.getContracts();
	}

	public Map saveEmployee(MdEmployee employee) {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		String employeeCode = codeWorker.createCode("E");
		employee.setEmployeecode(employeeCode);
		Map result = new HashMap();
		Map<String, Object> employeeItem = dao.queryEmployeeByJObNo(employee
				.getJobNo());
		if (employeeItem != null && employeeItem.size() > 0) {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增人员信息失败，已经存在该工号：" + employee.getJobNo());
		} else {
			if (dao.insert(employee)) {
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("employeeCode", employeeCode);
				// 同步人员到微信
				new WeixinThread(employee, "1").start();
			} else {

				result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
				result.put("resultMsg", "新增人员信息失败");
			}
		}

		return result;
	}

	public Map updateEmployee(MdEmployee employee) {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		dao.update(employee);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map memberLeave(Object emp) {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		dao.memberLeave(emp);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map deleteAccount(Object emp) {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		dao.deleteAccount(emp);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	// 判断是否有在租的宿舍
	public List<Map<String, Object>> queryDorm(Object emp) {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		List<Map<String, Object>> list = dao.queryDorm(emp);
		return list;
	}

	// 判断是否有在租的资产
	public List<Map<String, Object>> queryAsset(Object emp) {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		List<Map<String, Object>> list = dao.queryAsset(emp);
		return list;
	}

	public Map queryEmployee(MdEmployeeForm form) {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		List<MdEmployee> list = dao.queryEmployee(form.getUnitCode());
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("data", list);
		return result;
	}

	public Map queryEmployeeList(MdEmployeeForm form) {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		OrgUnitDao orgUnitDao = (OrgUnitDao) EAPContext.getContext().getBean(
				"orgUnitDao");
		PageBean page = dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());
		for (MdEmployee e : (List<MdEmployee>) page.getList()) {
			String unitCode = e.getUnitCode();
			OrgUnit ou = orgUnitDao.queryOrgUnit(unitCode);
			if (ou != null) {
				e.setUnitName(ou.getUnitName());
			}
		}
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("currentPage", page.getCurrentPage());
		result.put("perPage", page.getPerpage());
		result.put("totalPage", page.getTotalPage());
		result.put("totalRecord", page.getTotalRecord());
		result.put("list", page.getList());
		return result;
	}

	public List<MdEmployee> queryForExport(MdEmployeeForm form) {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		List<MdEmployee> list = dao.queryForExport(form.toMap());
		return list;
	}

	public Map selectJob() {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		List<Map<String, Object>> list = dao.selectJob();
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("list", list);
		return result;
	}

	public Map selectJobProperty() {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		List<Map<String, Object>> list = dao.selectJobProperty();
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("list", list);
		return result;
	}

	public Map selectContractProperty() {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		List<Map<String, Object>> list = dao.selectContractProperty();
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("list", list);
		return result;
	}

	public PageBean queryNotInProForPage(Map params) {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		return dao.queryNotInProForPage(params);
	}

	public Map queryEmployeeInfo(String employeeCode) {
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		return dao.queryEmployeeInfo(employeeCode);
	}

	public List<Map<String, String>> queryRelatedEmployeeList(
			String employeeCode) {
		// TODO Auto-generated method stub
		MdEmployeeDao dao = (MdEmployeeDao) this.dao;
		return dao.queryRelatedEmployeeList(employeeCode);
	}

}