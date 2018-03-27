package com.glens.pwCloudOs.pm.pb.rollingBudget.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.pb.budgetEstimate.service.BudgetEstimateService;
import com.glens.pwCloudOs.pm.pb.rollingBudget.service.RollingGudgetService;


@FormProcessor(clazz="com.glens.pwCloudOs.pm.pb.rollingBudget.web.RollingGudgetForm")
@RequestMapping("pmsServices/pm/pb/rollingGudget")
public class RollingGudgetController extends EAPJsonAbstractController {



	@RequestMapping(value="selectProRollingBudgetList", method=RequestMethod.GET)
	public Object selectProRollingBudgetList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				//RollingGudgetForm form=(RollingGudgetForm)actionForm;
				RollingGudgetService service=(RollingGudgetService)getService();
				
				RollingGudgetForm form=(RollingGudgetForm)actionForm;
				UserToken token = getToken(request);
				if (token != null) {
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode())
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE.equals(token.getRoleCode())) {
											
						form.setDeptCode(token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						form.setDistrictManager(token.getEmployeeCode());
					}
					
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						form.setProManager(token.getEmployeeCode());
					}
				}

				List list = service.selectProRollingBudgetList(form.toMap());

				ListResult result=new ListResult();


				if (list != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(list);
				}
				else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}


	@RequestMapping(value="proCostDetail", method=RequestMethod.GET)
	public Object getProCostDetail(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				RollingGudgetService service=(RollingGudgetService)getService();
				Map<String, Object> proCostResut = service.getProCostDetailList(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (proCostResut != null && proCostResut.size() > 0) {

					result.setResultMsg("获取项目成本概算详情成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(proCostResut);
				}
				else {

					result.setResultMsg("获取项目成本概算详情失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value="insertProSectionCost", method=RequestMethod.POST)
	public Object insertProSectionCost(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				RollingGudgetService rollingGudgetService = (RollingGudgetService) getService();
				int isSubmit=Integer.parseInt(request.getParameter("isSubmit").toString());
				System.out.println(isSubmit);
				int icount = rollingGudgetService.insertProSectionCost(actionForm.toMap(),isSubmit);
				ResponseResult result = new ResponseResult();
				if (icount > 0) {

					result.setResultMsg("提交项目费用成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {

					result.setResultMsg("提交项目费用失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	//得到差旅补贴
	@RequestMapping(value="getTravalList", method=RequestMethod.GET)
	public Object getTravalList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				RollingGudgetService rollingGudgetService = (RollingGudgetService) getService();
				Map<String, Object> proBudgetEstimate = rollingGudgetService.getTravalList(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (proBudgetEstimate != null) {

					result.setResultMsg("获取项目人力资源成本概算成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(proBudgetEstimate);
				}
				else {

					result.setResultMsg("获取项目人力资源成本概算失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	//得到人力成本
	@RequestMapping(value="getSectionLabourCost", method=RequestMethod.GET)
	public Object getSectionLabourCost(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				RollingGudgetService rollingGudgetService = (RollingGudgetService) getService();
				Map<String, Object> proBudgetEstimate = rollingGudgetService.getSectionLabourCost(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (proBudgetEstimate != null) {

					result.setResultMsg("获取项目人力资源成本概算成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(proBudgetEstimate);
				}
				else {

					result.setResultMsg("获取项目人力资源成本概算失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

   //删除差旅补贴
	@RequestMapping(value="deleteTravelSubsidy", method=RequestMethod.POST)
	public Object deleteTravelSubsidy(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				RollingGudgetService rollingGudgetService = (RollingGudgetService) getService();

				int iCount = rollingGudgetService.deleteTravelSubsidy(actionForm.toMap());
				ResponseResult result = new ResponseResult();

				if (iCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("删除成功");
				}
				else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("删除出错");
				}

				return result;
			}

		});
	}

	//删除 项目滚动预算  人力资源单价
	@RequestMapping(value="deleteSectionLabourCost", method=RequestMethod.POST)
	public Object deleteSectionLabourCost(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				RollingGudgetService rollingGudgetService = (RollingGudgetService) getService();

				int iCount = rollingGudgetService.deleteSectionLabourCost(actionForm.toMap());
				ResponseResult result = new ResponseResult();

				if (iCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("删除成功");
				}
				else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("删除出错");
				}

				return result;
			}

		});
	}

	//新增 人力资源成本 差旅补贴
	@RequestMapping(value="insertTravelSubsidy", method=RequestMethod.POST)
	public Object insertTravelSubsidy(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				RollingGudgetService rollingGudgetService = (RollingGudgetService) getService();

				int iCount = rollingGudgetService.insertTravelSubsidy(actionForm.toMap());
				ResponseResult result = new ResponseResult();
				if (iCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
				}
				else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增出错");
				}

				return result;
			}

		});
	}


	//新增 项目滚动预算 人力资源成本
	@RequestMapping(value="insertSectionLabourCost", method=RequestMethod.POST)
	public Object insertSectionLabourCost(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				RollingGudgetService rollingGudgetService = (RollingGudgetService) getService();

				int iCount = rollingGudgetService.insertSectionLabourCost(actionForm.toMap());
				ResponseResult result = new ResponseResult();
				if (iCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
				}
				else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增出错");
				}

				return result;
			}

		});
	}

	//创建 区间
	@RequestMapping(value="budgetTemplate", method=RequestMethod.POST)
	public Object createBudgetTemplate(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				RollingGudgetService rollingGudgetService = (RollingGudgetService) getService();
				String createResult = rollingGudgetService.createBudgetTemplate(actionForm.toMap());
				ResponseResult result = new ResponseResult();
				if (createResult!=null && createResult.equals("")) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg(createResult);
				}
				else {
					
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
					result.setResultMsg(createResult);
				}
				
				return result;
			}
		});
	}

	@RequestMapping(value="proBudgetVersionList", method=RequestMethod.GET)
	public Object selectProBudgetVersion(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				RollingGudgetService rollingGudgetService = (RollingGudgetService) getService();
				List<Map<String, Object>> versionList = rollingGudgetService.selectProBudgetVersion(actionForm.toMap());
				ListResult result = new ListResult();
				if (versionList != null && versionList.size() > 0) {
					
					result.setResultMsg("获取项目预算版本信息成本！");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(versionList);
				}
				else {
					
					result.setResultMsg("获取项目预算版本信息成本！");
					result.setStatusCode(ResponseConstants.OK);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="proBudgetVersion", method=RequestMethod.GET)
	public Object getProBudgetVersion(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				RollingGudgetService service=(RollingGudgetService)getService();
				Map<String, Object> proCostResut = service.getProBudgetVersion(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (proCostResut != null && proCostResut.size() > 0) {

					result.setResultMsg("获取项目成本概算详情成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(proCostResut);
				}
				else {

					result.setResultMsg("获取项目成本概算详情失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

}
