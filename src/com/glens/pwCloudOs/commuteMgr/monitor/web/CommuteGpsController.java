package com.glens.pwCloudOs.commuteMgr.monitor.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.commuteMgr.monitor.service.CommuteGpsService;

@FormProcessor(clazz = "com.glens.pwCloudOs.commuteMgr.monitor.web.CommuteGpsForm")
@RequestMapping("/pmsServices/commuteMgr/monitor")
public class CommuteGpsController extends EAPJsonAbstractController {

	@RequestMapping(value = "rtMonitor", method = RequestMethod.GET)
	public Object rtMonitor(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CommuteGpsService commuteGpsService = (CommuteGpsService) getService();
				Map<String, Object> params = actionForm.toMap();
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				if ("004".equals(token.getRoleCode())
						|| "002002".equals(token.getRoleCode())
						|| "R20161107150600707427".equals(token.getRoleCode())) {// 如果是项目负责人角色则传入筛选条件，只能查询所负责的项目下的信息
					params.put("isProManager", 1);
					params.put("proManagerEmployeecode",
							token.getEmployeeCode());
				}
				
				if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
					
					params.put("districtManager", token.getEmployeeCode());
				}
				else if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
					
					params.put("deptCode", token.getUnitCode());
				}
				
				PageBean page = commuteGpsService.getRealTimePosition(params);
				PageBeanResult result = new PageBeanResult();
				if (page != null && page.getList() != null
						&& page.getList().size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取实时通勤信息成功!");
					result.setPageBean(page);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取实时通勤信息失败!");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "track", method = RequestMethod.GET)
	public Object track(HttpServletRequest request, HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CommuteGpsForm myCommuteForm = (CommuteGpsForm) actionForm;
				CommuteGpsService commuteGpsService = (CommuteGpsService) getService();
				Map result = commuteGpsService.getHisPosition(
						myCommuteForm.getAccountCode(), myCommuteForm.getDate());
				return result;
			}
		});
	}

	@RequestMapping(value = "outdoorWorkers", method = RequestMethod.GET)
	public Object selectOutdoorWorkers(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				CommuteGpsService commuteGpsService = (CommuteGpsService) getService();
				CommuteGpsForm form = (CommuteGpsForm) actionForm;
				String curDate = request.getParameter("curDate");
				if (curDate == null || curDate.length() == 0) {
					curDate = new SimpleDateFormat("yyyy-MM-dd")
							.format(new Date());
					form.setCurDate(curDate);
				}
				Map<String, Object> params = form.toMap();
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				if ("004".equals(token.getRoleCode())
						|| "002002".equals(token.getRoleCode())
						|| "R20161107150600707427".equals(token.getRoleCode())) {// 如果是项目负责人角色则传入筛选条件，只能查询所负责的项目下的信息
					params.put("isProManager", 1);
					params.put("proManagerEmployeecode",
							token.getEmployeeCode());
				}
				
				if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
					
					params.put("districtManager", token.getEmployeeCode());
				}
				else if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
					
					params.put("deptCode", token.getUnitCode());
				}
				
				PageBean page = commuteGpsService.selectOutdoorWorkers(params);
				PageBeanResult result = new PageBeanResult();
				if (page != null && page.getList() != null
						&& page.getList().size() > 0) {
					//
					List res = page.getList();
					for (Iterator iterator = res.iterator(); iterator.hasNext();) {
						Map<String, Object> map = (Map<String, Object>) iterator
								.next();
						String accountCode = (String) map.get("accountCode");

						Map hisResult = commuteGpsService.getHisPosition(
								accountCode, curDate);
						String statusCode = (String) hisResult
								.get("statusCode");
						if (ResponseConstants.OK.equals(statusCode)) {
							List lst = (List) hisResult.get("list");
							if (lst != null && lst.size() > 0) {
								map.put("hasHis", 1);
							}
						}
					}
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取外业人员信息成功!");
					result.setPageBean(page);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取外业人员信息失败!");
				}

				return result;
			}
		});
	}

}
