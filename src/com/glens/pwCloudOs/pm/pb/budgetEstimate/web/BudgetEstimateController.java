/**
 * @Title: BudgetEstimateController.java
 * @Package com.glens.pwCloudOs.pm.pb.budgetEstimate.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-2-9 下午2:53:40
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.pb.budgetEstimate.web;

import java.io.IOException;
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
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.service.SalaryFramworkService;
import com.glens.pwCloudOs.pm.pb.budgetEstimate.service.BudgetEstimateService;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.pb.budgetEstimate.web.BudgetEstimateForm")
@RequestMapping("pmsServices/pm/pb/budgetEstimate")
public class BudgetEstimateController extends EAPJsonAbstractController {

	@RequestMapping(value = "proBudgetEstimateList", method = RequestMethod.GET)
	public Object selectProBudgetEstimateList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				BudgetEstimateForm budgetEstimateForm = (BudgetEstimateForm) actionForm;
				UserToken token = getToken(request);
				if (token != null) {

					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
							.getRoleCode())
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE
									.equals(token.getRoleCode())
							|| token.getRoleCode().equals(
									PwCloudOsConstant.DETUTY_MANAGER_ROLE_CODE)) {

						budgetEstimateForm.setDeptCode(token.getUnitCode());
					}

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {

						budgetEstimateForm.setDistrictManager(token
								.getEmployeeCode());
					}

					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token
							.getRoleCode())) {

						budgetEstimateForm.setProManager(token
								.getEmployeeCode());
					}
					if (PwCloudOsConstant.DEPT_SUP_ROLE_CODE.equals(token
							.getRoleCode())) {
						budgetEstimateForm
								.setDeptCode(PwCloudOsConstant.DEPT_SUP);
					}
				}

				List<Map<String, Object>> budgetEstimateList = budgetEstimateService
						.selectProBudgetEstimateList(budgetEstimateForm.toMap());
				ListResult result = new ListResult();
				if (budgetEstimateList != null && budgetEstimateList.size() > 0) {

					result.setResultMsg("获取项目决算数据成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(budgetEstimateList);
				} else if (budgetEstimateList.size() == 0) {

					result.setResultMsg("获取项目决算数据为0");
					result.setStatusCode(ResponseConstants.NO_DATA);
				} else {
					result.setResultMsg("获取项目决算数据失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 项目概算审核
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "proBudgetEstimateListCheck", method = RequestMethod.GET)
	public Object proBudgetEstimateListCheck(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				BudgetEstimateForm budgetEstimateForm = (BudgetEstimateForm) actionForm;
				UserToken token = getToken(request);
				if (token != null) {

					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
							.getRoleCode())
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE
									.equals(token.getRoleCode())
							|| token.getRoleCode().equals(
									PwCloudOsConstant.DETUTY_MANAGER_ROLE_CODE)) {

						budgetEstimateForm.setDeptCode(token.getUnitCode());
					}

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {

						budgetEstimateForm.setDistrictManager(token
								.getEmployeeCode());
					}

					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token
							.getRoleCode())) {

						budgetEstimateForm.setProManager(token
								.getEmployeeCode());
					}
					if (PwCloudOsConstant.DEPT_SUP_ROLE_CODE.equals(token
							.getRoleCode())) {
						budgetEstimateForm
								.setDeptCode(PwCloudOsConstant.DEPT_SUP);
					}
				}

				if (token.getRoleCode().equals("R20171011104517924566")) {// 部门副经理
					budgetEstimateForm.setDeputyManager(token.getEmployeeCode());
				} else if (token.getRoleCode().equals("016")) {// 部门经理
					budgetEstimateForm.setDeptAuditor(token.getEmployeeCode());
				} else if (token.getRoleCode().equals("R20170908155947597240")) {// 财务经理
					budgetEstimateForm.setFinanceAuditor(token
							.getEmployeeCode());
				} else if (token.getRoleCode().equals("012")) {// 总经理
					budgetEstimateForm.setGeneralManager(token
							.getEmployeeCode());
				}

				List<Map<String, Object>> budgetEstimateList = budgetEstimateService
						.selectProBudgetEstimateList(budgetEstimateForm.toMap());
				
				ListResult result = new ListResult();
				if (budgetEstimateList != null && budgetEstimateList.size() > 0) {

					result.setResultMsg("获取项目决算数据成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(budgetEstimateList);
				} else if (budgetEstimateList.size() == 0) {

					result.setResultMsg("获取项目决算数据为0");
					result.setStatusCode(ResponseConstants.NO_DATA);
				} else {
					result.setResultMsg("获取项目决算数据失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "getDistrictManager", method = RequestMethod.GET)
	public Object getDistrictManager(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				List<Map<String, Object>> proPhaseList = budgetEstimateService
						.getDistrictManager(actionForm.toMap());
				ListResult result = new ListResult();
				if (proPhaseList != null && proPhaseList.size() > 0) {

					result.setResultMsg("获取区域经理成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(proPhaseList);
				} else {

					result.setResultMsg("获取区域经理失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "proPhaseList", method = RequestMethod.GET)
	public Object selectProPhaseList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				List<Map<String, String>> proPhaseList = budgetEstimateService
						.selectProPhaseList(actionForm.toMap());
				ListResult result = new ListResult();
				if (proPhaseList != null && proPhaseList.size() > 0) {

					result.setResultMsg("获取项目阶段数据成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(proPhaseList);
				} else {

					result.setResultMsg("获取项目阶段数据失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "getContractProperty", method = RequestMethod.GET)
	public Object getContractProperty(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				List<Map<String, Object>> proPhaseList = budgetEstimateService
						.getContractProperty(actionForm.toMap());
				ListResult result = new ListResult();
				if (proPhaseList != null && proPhaseList.size() > 0) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(proPhaseList);
				} else {

					result.setResultMsg("获取失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "getJobs", method = RequestMethod.GET)
	public Object getJobs(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				List<Map<String, Object>> proPhaseList = budgetEstimateService
						.getJobs(actionForm.toMap());
				ListResult result = new ListResult();
				if (proPhaseList != null && proPhaseList.size() > 0) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(proPhaseList);
				} else {

					result.setResultMsg("获取失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "insertProPhase", method = RequestMethod.POST)
	public Object insertProPhase(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				int icount = budgetEstimateService.insertProPhase(actionForm
						.toMap());
				ResponseResult result = new ResponseResult();
				if (icount > 0) {

					result.setResultMsg("新增项目阶段成功");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("新增项目阶段失败");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}
	
	
	/**
	 * 新增概算日志
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addPmpbLog", method = RequestMethod.POST)
	public Object addPmpbLog(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				int icount = budgetEstimateService.addPmpbLog(actionForm
						.toMap());
				ResponseResult result = new ResponseResult();
				if (icount > 0) {

					result.setResultMsg("新增成功");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("新增失败");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}
	

	@RequestMapping(value = "updateProPhase", method = RequestMethod.POST)
	public Object updateProPhase(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				int icount = budgetEstimateService.updateProPhase(actionForm
						.toMap());
				ResponseResult result = new ResponseResult();
				if (icount > 0) {

					result.setResultMsg("修改项目阶段成功");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("修改项目阶段失败");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "deleteProPhase", method = RequestMethod.POST)
	public Object deleteProPhase(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				int icount = budgetEstimateService.deleteProPhase(actionForm
						.toMap());
				ResponseResult result = new ResponseResult();
				if (icount > 0) {

					result.setResultMsg("删除项目阶段成功");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("删除项目阶段失败");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "proBudgetEstimate", method = RequestMethod.GET)
	public Object getProBudgetEstimate(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				Map<String, Object> proBudgetEstimate = budgetEstimateService
						.getProBudgetEstimate(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (proBudgetEstimate != null) {

					result.setResultMsg("获取项目概算成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(proBudgetEstimate);
				} else {

					result.setResultMsg("获取项目概算失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	// 汇总
	@RequestMapping(value = "proBudgetTotal", method = RequestMethod.GET)
	public Object proBudgetTotal(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				Map<String, Object> proBudgetEstimate = budgetEstimateService
						.proBudgetTotal(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (proBudgetEstimate != null) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(proBudgetEstimate);
				} else {

					result.setResultMsg("获取失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}
	
	/**
	 * 项目概算版本对比
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "proBudgetContrast", method = RequestMethod.GET)
	public Object proBudgetContrast(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				String versions=request.getParameter("versions");
				String versionstr[]=versions.split(",");
				Map returnMap=new HashMap();
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				BudgetEstimateForm form=(BudgetEstimateForm)actionForm;
				for (int i = 0; i < versionstr.length; i++) {
					form.setVersionCode(versionstr[i]);
					Map<String, Object> proBudgetEstimate = budgetEstimateService
							.getProBudgetEstimate(form.toMap());
					
					String data="data"+(i+1);
					
					returnMap.put(data, proBudgetEstimate);
				}
				
				
				
				BeanResult result = new BeanResult();
				if (versionstr.length>0) {

					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(returnMap);
				} else {

					result.setResultMsg("获取失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}
	
	

	@RequestMapping(value = "labourCostDetailList", method = RequestMethod.GET)
	public Object getProLabourCostDetailList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				Map<String, Object> proBudgetEstimate = budgetEstimateService
						.getProLabourCostDetailList(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (proBudgetEstimate != null) {

					result.setResultMsg("获取项目人力资源成本概算成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(proBudgetEstimate);
				} else {

					result.setResultMsg("获取项目人力资源成本概算失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "proCostDetail", method = RequestMethod.GET)
	public Object getProCostDetail(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				Map<String, Object> proCostResut = budgetEstimateService
						.getProCostDetailList(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (proCostResut != null && proCostResut.size() > 0) {

					result.setResultMsg("获取项目成本概算详情成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(proCostResut);
				} else if (proCostResut.size() == 0) {

					result.setResultMsg("未获取到数据");
					result.setStatusCode(ResponseConstants.NO_DATA);
				} else {
					result.setResultMsg("获取项目成本概算详情失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "insertProBudgetEstimate", method = RequestMethod.POST)
	public Object insertProBudgetEstimate(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				int icount = budgetEstimateService
						.insertProBudgetEstimate(actionForm.toMap());
				ResponseResult result = new ResponseResult();
				if (icount > 0) {

					result.setResultMsg("新增项目概算成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("新增项目概算失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "insertProPhaseLabourCost", method = RequestMethod.POST)
	public Object insertProPhaseLabourCost(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				int icount = budgetEstimateService
						.insertProPhaseLabourCost(actionForm.toMap());
				ResponseResult result = new ResponseResult();
				if (icount > 0) {

					result.setResultMsg("新增项目阶段人力成本成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("新增项目阶段人力成本失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "deleteProPhaseLabourCost", method = RequestMethod.POST)
	public Object deleteProPhaseLabourCost(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				int icount = budgetEstimateService
						.deleteProPhaseLabourCost(actionForm.toMap());
				ResponseResult result = new ResponseResult();
				if (icount > 0) {

					result.setResultMsg("删除项目阶段人力成本成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("删除项目阶段人力成本失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "updateProPhaseLabourCost", method = RequestMethod.POST)
	public Object updateProPhaseLabourCost(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				int icount = budgetEstimateService
						.updateProPhaseLabourCost(actionForm.toMap());
				ResponseResult result = new ResponseResult();
				if (icount > 0) {

					result.setResultMsg("修改项目阶段人力成本成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("修改项目阶段人力成本失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "insertProPhaseCost", method = RequestMethod.POST)
	public Object insertProPhaseCost(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				int icount = budgetEstimateService
						.insertProPhaseCost(actionForm.toMap());
				ResponseResult result = new ResponseResult();
				if (icount > 0) {

					result.setResultMsg("提交项目费用成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("提交项目费用失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "publishProBudgetEstimate", method = RequestMethod.POST)
	public Object publishProBudgetEstimate(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				UserToken token = getToken(request);
				String userCode = token.getEmployeeCode();

				BudgetEstimateForm form = (BudgetEstimateForm) actionForm;
				form.setUserCode(userCode);
				int icount = budgetEstimateService
						.publishProBudgetEstimate(form.toMap());
				ResponseResult result = new ResponseResult();
				if (icount > 0) {

					result.setResultMsg("发布项目概算成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("发布项目概算失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	/**
	 * 得到项目概算汇总
	 */
	@RequestMapping(value = "gaisuanHuizong", method = RequestMethod.GET)
	public Object gaisuanHuizong(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();

				BudgetEstimateForm budgetEstimateForm = (BudgetEstimateForm) actionForm;
				UserToken token = getToken(request);
				if (token != null) {

					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
							.getRoleCode())
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE
									.equals(token.getRoleCode())
							|| token.getRoleCode().equals(
									PwCloudOsConstant.DETUTY_MANAGER_ROLE_CODE)) {

						budgetEstimateForm.setDeptCode(token.getUnitCode());
					}

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {

						budgetEstimateForm.setDistrictManager(token
								.getEmployeeCode());
					}

					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token
							.getRoleCode())) {

						budgetEstimateForm.setProManager(token
								.getEmployeeCode());
					}
					if (PwCloudOsConstant.DEPT_SUP_ROLE_CODE.equals(token
							.getRoleCode())) {
						budgetEstimateForm
								.setDeptCode(PwCloudOsConstant.DEPT_SUP);
					}
				}

				PageBean page = budgetEstimateService.queryBudgetForPage(
						budgetEstimateForm.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());

				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(page);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "proEstimateLabour", method = RequestMethod.GET)
	public Object selectProEstimateLabour(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> labourList = budgetEstimateService
						.selectProEstimateLabour(actionForm.toMap());
				if (labourList != null && labourList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取项目人力资源成本概算数据成功");
					result.setList(labourList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("当前项目没有人力资源成本概算数据");
				}

				return result;
			}
		});
	}

	/**
	 * 导出项目概算汇总
	 */
	@RequestMapping(value = "exportgaisuanHuizong", method = RequestMethod.GET)
	public Object exportgaisuanHuizong(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				BudgetEstimateForm budgetEstimateForm = (BudgetEstimateForm) actionForm;
				UserToken token = getToken(request);
				if (token != null) {

					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
							.getRoleCode())
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE
									.equals(token.getRoleCode())
							|| token.getRoleCode().equals(
									PwCloudOsConstant.DETUTY_MANAGER_ROLE_CODE)) {

						budgetEstimateForm.setDeptCode(token.getUnitCode());
					}

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {

						budgetEstimateForm.setDistrictManager(token
								.getEmployeeCode());
					}

					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token
							.getRoleCode())) {

						budgetEstimateForm.setProManager(token
								.getEmployeeCode());
					}
					if (PwCloudOsConstant.DEPT_SUP_ROLE_CODE.equals(token
							.getRoleCode())) {
						budgetEstimateForm
								.setDeptCode(PwCloudOsConstant.DEPT_SUP);
					}
				}

				List<Map<String, Object>> salaryList = budgetEstimateService
						.queryForAllList(budgetEstimateForm.toMap());

				return salaryList;
			}

			/**
			 * 
			 * <p>
			 * Title: doWithFinish
			 * </p>
			 * 
			 * <p>
			 * Description:
			 * </p>
			 * 
			 * @param request
			 * @param response
			 * @param data
			 * @param viewType
			 * @param controller
			 * @return
			 * 
			 * @see com.glens.eap.platform.framework.web.support.AbstractProcessRequestHandler#doWithFinish(javax.servlet.http.HttpServletRequest,
			 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
			 *      java.lang.String,
			 *      com.glens.eap.platform.core.web.EAPController)
			 **/

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("项目概算汇总", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("项目概算汇总");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("budgetList",
								response.getOutputStream(), Map.class, dataList);

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

	/**
	 * 把项目组成员导入概算某个人力成本阶段，提高概算人力成本维护效率
	 * 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return 一个Map类型记录
	 * @throws
	 * @author:
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */

	@RequestMapping(value = "importProMemberBudgetSection", method = RequestMethod.POST)
	public Object importProMemberBudgetSection(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				int icount = budgetEstimateService
						.importProMemberBudgetSection(actionForm.toMap());
				ResponseResult result = new ResponseResult();
				if (icount > 0) {

					result.setResultMsg("导入项目成员到概算成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else if (icount < 0) {

					result.setResultMsg("当前项目没有项目组成员！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				} else {

					result.setResultMsg("导入项目成员到概算失败！");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}

				return result;

			}
		});
	}

	@RequestMapping(value = "createProBudgetEstimate", method = RequestMethod.POST)
	public Object createProBudgetEstimate(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				int icount = budgetEstimateService
						.createProBudgetEstimate(actionForm.toMap());
				ResponseResult result = new ResponseResult();
				if (icount > 0) {

					result.setResultMsg("新增项目概算成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("新增项目概算失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	// 导入实际成本
	@RequestMapping(value = "importExcel", method = RequestMethod.POST)
	public Object importExcel(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				BudgetEstimateForm form = (BudgetEstimateForm) actionForm;

				BudgetEstimateService ser = (BudgetEstimateService) getService();

				List<ImportBudgetEstimateVo> list = null;
				try {
					list = new ExcelHelper().getData(form.getExcelFile()
							.getInputStream(), form.getExcelFile()
							.getOriginalFilename(),
							ImportBudgetEstimateVo.class);
				} catch (RuntimeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				int num = 0;

				String year = form.getYear();

				String[] str = year.split("-");

				for (int i = 0; i < list.size(); i++) {
					Map insertMap = new HashMap();
					boolean result = false;
					try {
						ImportBudgetEstimateVo vo = list.get(i);
						String proNo = ser.getProNo(vo.getProNo().trim());

						insertMap.put("proNo", proNo);

						insertMap.put("proName", vo.getProName().trim());
						insertMap.put("costYear", str[0]);

						String currentMonth = str[1];

						insertMap.put("costMonth", currentMonth);
						insertMap.put("costDate", str[0] + "-" + currentMonth);

						ser.deleteFmProCost(insertMap); // 删除该项目本月的记录

						if (vo.getHumanResourceCost() != null) { // 人力资源成本
							insertMap.put("itemCode", "C001");
							insertMap.put("costValue",
									vo.getHumanResourceCost());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getEquipmentResourceCost() != null) { // 设备资源成本
							insertMap.put("itemCode", "C002");
							insertMap.put("costValue",
									vo.getEquipmentResourceCost());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getOutsourcingExpenses() != null) { // 外包费用
							insertMap.put("itemCode", "C003");
							insertMap.put("costValue",
									vo.getOutsourcingExpenses());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getHouseRentalFee() != null) { // 房屋租赁费
							insertMap.put("itemCode", "C004");
							insertMap.put("costValue", vo.getHouseRentalFee());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getEntertainmentExpenses() != null) { // 招待费
							insertMap.put("itemCode", "C005");
							insertMap.put("costValue",
									vo.getEntertainmentExpenses());
							result = ser.insertFmProCost(insertMap) >= 0;
						}

						if (vo.getMotorFare() != null) { // 租车/汽车费
							insertMap.put("itemCode", "C006");
							insertMap.put("costValue", vo.getMotorFare());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getTravelExpense() != null) { // 差旅费
							insertMap.put("itemCode", "C007");
							insertMap.put("costValue", vo.getTravelExpense());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getTrafficExpense() != null) { // 交通费
							insertMap.put("itemCode", "C008");
							insertMap.put("costValue", vo.getTrafficExpense());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getConsultingServiceFee() != null) { // 咨询服务费
							insertMap.put("itemCode", "C009");
							insertMap.put("costValue",
									vo.getConsultingServiceFee());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getDormitoryFee() != null) { // 生活宿舍费
							insertMap.put("itemCode", "C010");
							insertMap.put("costValue", vo.getDormitoryFee());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getBoardExpenses() != null) { // 伙食费
							insertMap.put("itemCode", "C011");
							insertMap.put("costValue", vo.getBoardExpenses());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getServiceFee() != null) { // 劳务费
							insertMap.put("itemCode", "C012");
							insertMap.put("costValue", vo.getServiceFee());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getOfficeExpenses() != null) { // 办公费
							insertMap.put("itemCode", "C013");
							insertMap.put("costValue", vo.getOfficeExpenses());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getNetworkCommunicationFee() != null) { // 网络通讯费
							insertMap.put("itemCode", "C014");
							insertMap.put("costValue",
									vo.getNetworkCommunicationFee());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getInstrumentOfficeEquipment() != null) { // 仪器办公设备
							insertMap.put("itemCode", "C015");
							insertMap.put("costValue",
									vo.getInstrumentOfficeEquipment());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getEquipmentRepairFee() != null) { // 设备修理费
							insertMap.put("itemCode", "C016");
							insertMap.put("costValue",
									vo.getEquipmentRepairFee());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getToolMaterialFee() != null) { // 工具材料费
							insertMap.put("itemCode", "C017");
							insertMap.put("costValue", vo.getToolMaterialFee());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getFreight() != null) { // 运费
							insertMap.put("itemCode", "C018");
							insertMap.put("costValue", vo.getFreight());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getLaborProtectionFee() != null) { // 劳动保护费
							insertMap.put("itemCode", "C019");
							insertMap.put("costValue",
									vo.getLaborProtectionFee());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getTenderFee() != null) { // 投标费
							insertMap.put("itemCode", "C020");
							insertMap.put("costValue", vo.getTenderFee());
							result = ser.insertFmProCost(insertMap) >= 0;
						}
						if (vo.getOtherCharges() != null) { // 其他费
							insertMap.put("itemCode", "C021");
							insertMap.put("costValue", vo.getOtherCharges());
							result = ser.insertFmProCost(insertMap) >= 0;
						}

						if (result) {
							num = num + 1;
						}
					} catch (Exception e) {
						e.printStackTrace();

					}

				}

				KeyResult result = new KeyResult();
				if (true) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("共" + list.size() + "条,成功导入" + num
							+ "条！");
					result.setGenerateKey(actionForm.getGenerateKey());
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("导入失败");
				}
				return result;

			}
		});

	}
	
	/**
	 * 项目计划书
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getProPlanBooks", method = RequestMethod.GET)
	public Object getProPlanBooks(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> labourList = budgetEstimateService
						.getProPlanBooks(actionForm.toMap());
				if (labourList != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取计划书成功");
					result.setList(labourList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取计划书失败");
				}

				return result;
			}
		});
	}
	
	/**
	 * 项目合同
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getProContract", method = RequestMethod.GET)
	public Object getProContract(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> labourList = budgetEstimateService
						.getProContract(actionForm.toMap());
				if (labourList != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(labourList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取失败");
				}

				return result;
			}
		});
	}
	
	/**
	 * 获取概算日志
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getPmPblogs", method = RequestMethod.GET)
	public Object getPmPblogs(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> labourList = budgetEstimateService
						.getPmPblogs(actionForm.toMap());
				if (labourList != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(labourList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取失败");
				}

				return result;
			}
		});
	}
	
	/**
	 * 获取项目概算版本列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getPmVersion", method = RequestMethod.GET)
	public Object getPmVersion(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				BudgetEstimateService budgetEstimateService = (BudgetEstimateService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> labourList = budgetEstimateService
						.getPmVersion(actionForm.toMap());
				if (labourList != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(labourList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取失败");
				}

				return result;
			}
		});
	}
	
	
	

}
