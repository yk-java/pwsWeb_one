/**
 * @Title: EmployeeSummaryService.java
 * @Package com.glens.eap.sys.orgEmployee.summary.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-7-15 上午10:45:44
 * @version V1.0
 */


package com.glens.eap.sys.orgEmployee.summary.service;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.sys.orgEmployee.summary.dao.EmployeeSummaryDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class EmployeeSummaryService extends EAPAbstractService {

	public List<Map<String, Object>> selectEmployeeSummary(Map<String, String> params) {
		
		EmployeeSummaryDao employeeSummaryDao = (EmployeeSummaryDao) getDao();
		
		return employeeSummaryDao.selectEmployeeSummary(params);
	}
	
	public List<Map<String, Object>> selectUnitEmployee(Map<String, Object> params) {
		
		EmployeeSummaryDao employeeSummaryDao = (EmployeeSummaryDao) getDao();
		List<Map<String, Object>> employeeList = null;
		Integer flag = (Integer) params.get("flag");
		
		if (flag == 1) {
			
			employeeList = employeeSummaryDao.selectProEmployee(params);
		}
		else if (flag == 2) {
			
			employeeList = employeeSummaryDao.selectUnitEmployee(params);
		}
		
		return employeeList;
	}
	
}
