/**
 * @Title: CommonController.java
 * @Package com.glens.pwCloudOs.common.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 下午3:29:23
 * @version V1.0
 */

package com.glens.pwCloudOs.common.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.TreeNode;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.common.service.CommonService;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@FormProcessor(clazz = "com.glens.pwCloudOs.common.web.CommonForm")
@RequestMapping("/pmsServices/pwsCommon")
public class CommonController extends EAPJsonAbstractController {

	private static Log logger = LogFactory.getLog(CommonController.class);

	@RequestMapping(value = "orgTree", method = RequestMethod.GET)
	public Object getOrgTree(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				CommonService commonService = (CommonService) getService();
				BeanResult result = new BeanResult();
				TreeNode tree = commonService.getOrgTree();
				if (tree != null) {

					result.setData(tree);
					result.setResultMsg("获取区域树成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("获取区域树失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "activePro", method = RequestMethod.GET)
	public Object getActivePro(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				CommonForm commonForm = (CommonForm) actionForm;
				CommonService commonService = (CommonService) getService();
				UserToken token = getToken(request);
				if (token != null) {

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {

						commonForm.setDistrictManager(token.getEmployeeCode());
					} else if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode()) || token.getRoleCode().equals(PwCloudOsConstant.DETUTY_MANAGER_ROLE_CODE)) {

						commonForm.setDeptCode(token.getUnitCode());
					}
					if(PwCloudOsConstant.DEPT_SUP_ROLE_CODE.equals(token.getRoleCode())){
						commonForm.setDeptCode(PwCloudOsConstant.DEPT_SUP);
					}
					
					
//					else if(PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())){
//						commonForm.setEmployeecode(token.getEmployeeCode());
//					}else if(PwCloudOsConstant.JL_INNERWORKER_ROLE_CODE.equals(token.getRoleCode())){
//						commonForm.setEmployeecode(token.getEmployeeCode());
//					}else if(PwCloudOsConstant.JL_PRO_UNIT_ROLE_CODE.equals(token.getRoleCode())){
//						commonForm.setEmployeecode(token.getEmployeeCode());
//					}
				}

				ListResult result = new ListResult();

				List<Map<String, String>> list = null;
				String proStatus = request.getParameter("proStatus");
				if ("3".equals(proStatus)) {// 查询关闭的项目
					list = commonService.getClosedPro(commonForm.toMap());
				} else {
					list = commonService.getActivePro(commonForm.toMap());
				}
				if (list != null) {

					result.setList(list);
					result.setResultMsg("获取计划的项目成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("获取激活的项目失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "getAllPro", method = RequestMethod.GET)
	public Object getAllPro(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				CommonForm commonForm = (CommonForm) actionForm;
				CommonService commonService = (CommonService) getService();
				UserToken token = getToken(request);
				if (token != null) {

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {

						commonForm.setDistrictManager(token.getEmployeeCode());
					} else if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode()) || token.getRoleCode().equals(PwCloudOsConstant.DETUTY_MANAGER_ROLE_CODE)) {

						commonForm.setDeptCode(token.getUnitCode());
					}
					if(PwCloudOsConstant.DEPT_SUP_ROLE_CODE.equals(token.getRoleCode())){
						commonForm.setDeptCode(PwCloudOsConstant.DEPT_SUP);
					}
//					else if(PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())){
//						commonForm.setEmployeecode(token.getEmployeeCode());
//					}else if(PwCloudOsConstant.JL_INNERWORKER_ROLE_CODE.equals(token.getRoleCode())){
//						commonForm.setEmployeecode(token.getEmployeeCode());
//					}else if(PwCloudOsConstant.JL_PRO_UNIT_ROLE_CODE.equals(token.getRoleCode())){
//						commonForm.setEmployeecode(token.getEmployeeCode());
//					}
				}

				ListResult result = new ListResult();

				List<Map<String, String>> list = null;

				list = commonService.getAllPro(commonForm.toMap());

				if (list != null) {

					result.setList(list);
					result.setResultMsg("获取成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("获取失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}
	
	@RequestMapping(value = "getGaisuanPros", method = RequestMethod.GET)
	public Object getGaisuanPros(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				CommonForm commonForm = (CommonForm) actionForm;
				CommonService commonService = (CommonService) getService();
				UserToken token = getToken(request);
				if (token != null) {

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {

						commonForm.setDistrictManager(token.getEmployeeCode());
					} else if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE.equals(token.getRoleCode())||token.getRoleCode().equals(PwCloudOsConstant.DETUTY_MANAGER_ROLE_CODE)) {

						commonForm.setDeptCode(token.getUnitCode());
					}
					if(PwCloudOsConstant.DEPT_SUP_ROLE_CODE.equals(token.getRoleCode())){
						commonForm.setDeptCode(PwCloudOsConstant.DEPT_SUP);
					}
					
//					else if(PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())){
//						commonForm.setEmployeecode(token.getEmployeeCode());
//					}else if(PwCloudOsConstant.JL_INNERWORKER_ROLE_CODE.equals(token.getRoleCode())){
//						commonForm.setEmployeecode(token.getEmployeeCode());
//					}else if(PwCloudOsConstant.JL_PRO_UNIT_ROLE_CODE.equals(token.getRoleCode())){
//						commonForm.setEmployeecode(token.getEmployeeCode());
//					}
				}

				ListResult result = new ListResult();

				List<Map<String, String>> list = null;

				list = commonService.getGaisuanPros(commonForm.toMap());

				if (list != null) {

					result.setList(list);
					result.setResultMsg("获取成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("获取失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}
	@RequestMapping(value = "getCheckPros", method = RequestMethod.GET)
	public Object getCheckPros(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				CommonForm commonForm = (CommonForm) actionForm;
				CommonService commonService = (CommonService) getService();
				UserToken token = getToken(request);
			
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode()) 
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE.equals(token.getRoleCode()) || token.getRoleCode().equals(PwCloudOsConstant.DETUTY_MANAGER_ROLE_CODE)) {
						
						commonForm.setDeptCode(token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						commonForm.setDistrictManager(token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						commonForm.setProManager(token.getEmployeeCode());
					}
					if(PwCloudOsConstant.DEPT_SUP_ROLE_CODE.equals(token.getRoleCode())){
						commonForm.setDeptCode(PwCloudOsConstant.DEPT_SUP);
					}
				}
				
				

				if(token.getRoleCode().equals("R20171011104517924566")){//部门副经理
					commonForm.setDeputyManager(token.getEmployeeCode());
				}else if(token.getRoleCode().equals("016")){//部门经理
					commonForm.setDeptAuditor(token.getEmployeeCode());
				}else if(token.getRoleCode().equals("R20170908155947597240")){//财务经理
					commonForm.setFinanceAuditor(token.getEmployeeCode());
				}else if(token.getRoleCode().equals("012")){//总经理
					commonForm.setGeneralManager(token.getEmployeeCode());
				}
				


				List<Map<String, String>> budgetEstimateList = commonService.getCheckPros(commonForm.toMap());
				ListResult result = new ListResult();
				if (budgetEstimateList != null && budgetEstimateList.size() > 0) {
					
					
					result.setStatusCode(ResponseConstants.OK);
					result.setList(budgetEstimateList);
				}
				else if(budgetEstimateList.size()==0) {
					
					
					result.setStatusCode(ResponseConstants.NO_DATA);
				}else{
					
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	

	@RequestMapping(value = "categorys", method = RequestMethod.GET)
	public Object getProCategory(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				CommonForm commonForm = (CommonForm) actionForm;
				CommonService commonService = (CommonService) getService();
				UserToken token = getToken(request);
				if (token != null) {

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {

						commonForm.setDistrictManager(token.getEmployeeCode());
					} else if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode()) || token.getRoleCode().equals(PwCloudOsConstant.DETUTY_MANAGER_ROLE_CODE)) {

						commonForm.setDeptCode(token.getUnitCode());
					}
					if(PwCloudOsConstant.DEPT_SUP_ROLE_CODE.equals(token.getRoleCode())){
						commonForm.setDeptCode(PwCloudOsConstant.DEPT_SUP);
					}
//					else if(PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())){
//						commonForm.setEmployeecode(token.getEmployeeCode());
//					}else if(PwCloudOsConstant.JL_INNERWORKER_ROLE_CODE.equals(token.getRoleCode())){
//						commonForm.setEmployeecode(token.getEmployeeCode());
//					}else if(PwCloudOsConstant.JL_PRO_UNIT_ROLE_CODE.equals(token.getRoleCode())){
//						commonForm.setEmployeecode(token.getEmployeeCode());
//					}
				}
				ListResult result = new ListResult();
				List<Map<String, String>> list = commonService
						.getProCategory(commonForm.toMap());
				if (list != null) {

					result.setList(list);
					result.setResultMsg("获取项目类别成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("获取项目类别失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "assetClass", method = RequestMethod.GET)
	public Object assetClass(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CommonService commonService = (CommonService) getService();
				CommonForm commonForm = (CommonForm) actionForm;
				ListResult result = new ListResult();
				String iscm = commonForm.getIscm();
				if (iscm.equals("3")) {// 显示全部
					iscm = "";
				}
				List<Map<String, String>> res = commonService.selectAssetClass(
						commonForm.getAssetCharacterCode(), iscm);
				if (res != null) {
					result.setList(res);
					result.setResultMsg("成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {
					result.setResultMsg("失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				return result;
			}
		});
	}

	@RequestMapping(value = "assetCharacter", method = RequestMethod.GET)
	public Object assetCharacter(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				CommonService commonService = (CommonService) getService();
				ListResult result = new ListResult();
				List<Map<String, String>> list = commonService
						.selectAssetCharacter();
				if (list != null) {
					result.setList(list);
					result.setResultMsg("成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {
					result.setResultMsg("失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "assetVehicle", method = RequestMethod.GET)
	public Object assetVehicle(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				CommonService commonService = (CommonService) getService();
				ListResult result = new ListResult();
				List<Map<String, String>> list = commonService.selectVehicle();
				if (list != null) {
					result.setList(list);
					result.setResultMsg("成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {
					result.setResultMsg("失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "vehicleInProject", method = RequestMethod.GET)
	public Object vehicleInProject(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				String proNo = request.getParameter("proNo");
				CommonService commonService = (CommonService) getService();
				ListResult result = new ListResult();
				List<Map<String, String>> list = commonService
						.vehicleInProject(proNo);
				if (list != null) {
					result.setList(list);
					result.setResultMsg("成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {
					result.setResultMsg("失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "employeeTreeByPro", method = RequestMethod.GET)
	public Object employeeTreeByPro(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CommonService commonService = (CommonService) getService();
				ListResult result = new ListResult();
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("proNo", request.getParameter("proNo"));
				params.put("employeeName", request.getParameter("employeeName"));
				List<Map<String, String>> tree = commonService
						.selectEmployeeByPro(params);
				if (tree != null) {
					result.setList(tree);
					result.setResultMsg("ok");
					result.setStatusCode(ResponseConstants.OK);
				} else {
					result.setResultMsg("fail");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				return result;
			}
		});
	}

	@RequestMapping(value = "employeeTreeByUnit", method = RequestMethod.GET)
	public Object employeeTreeByUnit(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CommonService commonService = (CommonService) getService();
				ListResult result = new ListResult();
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("unitCode", request.getParameter("unitCode"));
				params.put("employeeName", request.getParameter("employeeName"));
				List<Map<String, String>> tree = commonService
						.selectEmployeeByUnit(params);
				if (tree != null) {
					result.setList(tree);
					result.setResultMsg("ok");
					result.setStatusCode(ResponseConstants.OK);
				} else {
					result.setResultMsg("fail");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				return result;
			}
		});
	}

	@RequestMapping(value = "selectEmployeeByJob", method = RequestMethod.GET)
	public Object selectEmployeeByJob(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CommonService commonService = (CommonService) getService();
				ListResult result = new ListResult();
				List<Map<String, String>> tree = commonService
						.selectEmployeeByJob();
				if (tree != null) {
					result.setList(tree);
					result.setResultMsg("ok");
					result.setStatusCode(ResponseConstants.OK);
				} else {
					result.setResultMsg("fail");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				return result;
			}
		});
	}

	// 得到所有的员工 employeecode and employeename
	@RequestMapping(value = "getAllEmps", method = RequestMethod.GET)
	public Object getAllEmps(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CommonService commonService = (CommonService) getService();
				ListResult result = new ListResult();

				List list = commonService.getAllEmps();
				if (list != null) {
					result.setList(list);
					result.setResultMsg("ok");
					result.setStatusCode(ResponseConstants.OK);
				} else {
					result.setResultMsg("fail");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				return result;
			}
		});
	}

	// 得到项目列表
	@RequestMapping(value = "getPros", method = RequestMethod.GET)
	public Object getPros(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CommonService commonService = (CommonService) getService();
				ListResult result = new ListResult();

				CommonForm form = (CommonForm) actionForm;

				Map m = new HashMap();
				m.put("employeeCode", form.getEmployeecode());

				List list = commonService.getManagerPros(m);

				if (list.size() > 0) {// 项目负责人
					result.setList(list);
				} else {// 非项目负责人
					List li = commonService.getProList(m);
					result.setList(li);
				}
				if (list != null) {
					result.setResultMsg("ok");
					result.setStatusCode(ResponseConstants.OK);
				} else {
					result.setResultMsg("fail");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				return result;
			}
		});
	}

	/**
	 * 获取电缆井环境
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getCablePit", method = RequestMethod.GET)
	public Object getCablePit(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CommonService commonService = (CommonService) getService();
				String id = request.getParameter("id");
				String temperature = request.getParameter("temperature");
				String humidity = request.getParameter("humidity");
				BeanResult result = new BeanResult();
				JSONObject obj = new JSONObject();
				obj.put("id", id);
				obj.put("temperature", temperature);
				obj.put("humidity", humidity);
				if (obj != null) {
					logger.debug("电缆井数据：" + obj.toString());
					result.setData(obj);
					result.setResultMsg("获取数据成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {
					result.setResultMsg("获取数据失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}
}
