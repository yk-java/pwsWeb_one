/**
 * @Title: EmployeeSummaryController.java
 * @Package com.glens.eap.sys.orgEmployee.summary.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-7-15 上午10:53:11
 * @version V1.0
 */


package com.glens.eap.sys.orgEmployee.summary.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.sys.orgEmployee.summary.service.EmployeeSummaryService;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@FormProcessor(clazz="com.glens.eap.sys.orgEmployee.summary.web.EmployeeSummaryForm")
@RequestMapping("pmsServices/sys/orgEmployee/summary")
public class EmployeeSummaryController extends EAPJsonAbstractController {

	@RequestMapping(value="employeeSummary", method=RequestMethod.GET)
	public Object selectEmployeeSummary(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				EmployeeSummaryService employeeSummaryService = (EmployeeSummaryService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> employeeSummaryList = employeeSummaryService.selectEmployeeSummary(actionForm.toMap());
				if (employeeSummaryList != null && employeeSummaryList.size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("人员汇总统计信息获取成功!");
					result.setList(employeeSummaryList);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("人员汇总统计信息获取失败!");
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="unitEmployee", method=RequestMethod.GET)
	public Object selectUnitEmployee(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				EmployeeSummaryService employeeSummaryService = (EmployeeSummaryService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> employeeList = employeeSummaryService.selectUnitEmployee(actionForm.toMap());
				if (employeeList != null && employeeList.size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("人员信息获取成功!");
					result.setList(employeeList);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("人员信息获取失败!");
				}
				
				return result;
			}
		});
	}
	
}
