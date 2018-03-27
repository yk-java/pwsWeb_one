package com.glens.pwCloudOs.commuteMgr.performance.statistic.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.commuteMgr.performance.statistic.service.CommuteStatisticService;
import com.glens.pwCloudOs.commuteMgr.performance.statistic.service.CpCommuteJudgingService;
import com.glens.pwCloudOs.commuteMgr.performance.statistic.vo.CommuteStatisticVo;

@FormProcessor(clazz = "com.glens.pwCloudOs.commuteMgr.performance.statistic.web.CommuteStatisticForm")
@RequestMapping("/pmsServices/commuteMgr/performance/statistic")
public class CommuteStatisticController extends EAPJsonAbstractController {

	@RequestMapping(method = RequestMethod.GET, value = "dailySheet")
	public Object dailySheet(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CommuteStatisticService cpcommutecheckservice = (CommuteStatisticService) EAPContext
						.getContext().getBean("commuteStatisticService");
				CommuteStatisticForm form = (CommuteStatisticForm) actionForm;
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

				if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {

					params.put("districtManager", token.getEmployeeCode());
				}
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					params.put("deptCode", token.getUnitCode());
				}

				Map result = cpcommutecheckservice.queryList(params,
						form.getCurrentPage(), form.getPerpage());

				return result;
			}

		});
	}

	@RequestMapping(method = RequestMethod.GET, value = "online")
	public Object online(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CommuteStatisticService cpcommutecheckservice = (CommuteStatisticService) EAPContext
						.getContext().getBean("commuteStatisticService");
				CommuteStatisticForm form = (CommuteStatisticForm) actionForm;
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
				Map result = cpcommutecheckservice.queryOnlineList(params,
						form.getCurrentPage(), form.getPerpage());

				return result;
			}

		});
	}

	@RequestMapping(method = RequestMethod.GET, value = "dailySheetExport")
	public Object dailySheetExport(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CommuteStatisticService cpcommutecheckservice = (CommuteStatisticService) EAPContext
						.getContext().getBean("commuteStatisticService");
				CommuteStatisticForm form = (CommuteStatisticForm) actionForm;
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

				if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {

					params.put("districtManager", token.getEmployeeCode());
				}

				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					params.put("deptCode", token.getUnitCode());
				}
				
				List<CommuteStatisticVo> result = cpcommutecheckservice
						.queryListForExport(params);

				return result;
			}

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String date = request.getParameter("date");
						String fileName = URLEncoder.encode(date + "_轨迹异常报告",
								"UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName(date + "_轨迹异常报告");
						List dataList = (List) data;
						excelHelper.writeData(response.getOutputStream(),
								CommuteStatisticVo.class, dataList);
						// response.getOutputStream().write("abc".getBytes());
					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
			}

		});

	}

	@RequestMapping(method = RequestMethod.GET, value = "findCommute")
	public Object findCommute(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CommuteStatisticService cpcommutecheckservice = (CommuteStatisticService) EAPContext
						.getContext().getBean("commuteStatisticService");
				CommuteStatisticForm form = (CommuteStatisticForm) actionForm;
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

				if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {

					params.put("districtManager", token.getEmployeeCode());
				}

				CommuteStatisticVo vo = cpcommutecheckservice
						.findCommute(params);
				Map result = new HashMap();
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("data", vo);
				return result;
			}

		});
	}

	@RequestMapping(method = RequestMethod.GET, value = "findCommuteByDate")
	public Object findCommuteByDate(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CommuteStatisticService cpcommutecheckservice = (CommuteStatisticService) EAPContext
						.getContext().getBean("commuteStatisticService");
				CommuteStatisticForm form = (CommuteStatisticForm) actionForm;
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

				if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {

					params.put("districtManager", token.getEmployeeCode());
				}

				CommuteStatisticVo vo = cpcommutecheckservice
						.findCommute(params);
				Map result = new HashMap();
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("data", vo);
				return result;
			}

		});
	}

	@RequestMapping(method = RequestMethod.GET, value = "statisticByDate")
	public Object statisticByDate(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CommuteStatisticService cpcommutecheckservice = (CommuteStatisticService) EAPContext
						.getContext().getBean("commuteStatisticService");
				CommuteStatisticForm form = (CommuteStatisticForm) actionForm;
				Map result = cpcommutecheckservice.statisticByDate(form);

				return result;
			}

		});
	}

	@RequestMapping(method = RequestMethod.GET, value = "allCommuteJudging")
	public Object allCommuteJudging(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CpCommuteJudgingService cpCommuteJudgingService = (CpCommuteJudgingService) EAPContext
						.getContext().getBean("cpCommuteJudgingService");
				List result = cpCommuteJudgingService.queryForList(null);
				return ListResult.success("ok", result);
			}

		});
	}
}
