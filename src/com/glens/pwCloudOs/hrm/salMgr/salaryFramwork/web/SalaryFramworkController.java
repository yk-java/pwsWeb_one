/**
 * @Title: SalaryFramworkController.java
 * @Package com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-2-16 下午5:59:52
 * @version V1.0
 */


package com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.web;

import java.net.URLEncoder;
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
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.service.SalaryFramworkService;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionnireSend;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@FormProcessor(clazz="com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.web.SalaryFramworkForm")
@RequestMapping("/pmsServices/hrm/salMgr/salaryFramwork")
public class SalaryFramworkController extends EAPJsonAbstractController {

	@RequestMapping(value="salaryFramworkDetail", method=RequestMethod.GET)
	public Object getSalaryFramworkDetail(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BeanResult result = new BeanResult();
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				Map<String, Object> salaryFramwork = salaryFramworkService.getSalaryFramworkDetail(actionForm.toMap());
				if (salaryFramwork != null && salaryFramwork.size() > 0) {
					
					result.setResultMsg("获取薪酬体系信息成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(salaryFramwork);
				}
				else {
					
					result.setResultMsg("获取薪酬体系信息失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="insertFrameworkSetting", method=RequestMethod.POST)
	public Object insertFrameworkSetting(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				ResponseResult result = new ResponseResult();
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				int affectedCount = salaryFramworkService.insertFrameworkSetting(actionForm.toMap());
				if (affectedCount > 0) {
					
					result.setResultMsg("提交薪酬体系设置成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("提交薪酬体系设置失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="unitEnableSalaryFramwork", method=RequestMethod.GET)
	public Object getEnableUnitSalaryFramework(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BeanResult result = new BeanResult();
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				Map<String, Object> salaryFramwork = salaryFramworkService.getUnitSalaryFramework(actionForm.toMap());
				if (salaryFramwork != null && salaryFramwork.size() > 0) {
					
					result.setResultMsg("获取部门薪酬体系信息成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(salaryFramwork);
				}
				else {
					
					result.setResultMsg("获取部门薪酬体系信息失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="getStandSalary", method=RequestMethod.GET)
	public Object getStandSalary(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BeanResult result = new BeanResult();
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				Map<String, Object> salaryFramwork = salaryFramworkService.getStandSalary(actionForm.toMap());
				if (salaryFramwork != null && salaryFramwork.size() > 0) {
					
					result.setResultMsg("获取岗位薪酬成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(salaryFramwork);
				}
				else {
					
					result.setResultMsg("获取岗位薪酬失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="updateEmployeeSalaryGrade", method=RequestMethod.POST)
	public Object updateEmployeeSalaryGrade(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				ResponseResult result = new ResponseResult();
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				int affectedCount = salaryFramworkService.updateEmployeeSalaryGrade(actionForm.toMap());
				if (affectedCount > 0) {
					
					result.setResultMsg("人员薪酬等级设置成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("人员薪酬等级设置失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="unitEmloyeeList", method=RequestMethod.GET)
	public Object getUnitEmployeeList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> employeeList = salaryFramworkService.getUnitEmployeeList(actionForm.toMap());
				if (employeeList != null && employeeList.size() > 0) {
					
					result.setResultMsg("获取人员信息成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(employeeList);
				}
				else {
					
					result.setResultMsg("没有获取到人员信息！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="getJobNums", method=RequestMethod.GET)
	public Object getJobNums(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> employeeList = salaryFramworkService.getJobNums(actionForm.toMap());
				if (employeeList != null && employeeList.size() > 0) {
					
					result.setResultMsg("获取人职位数量成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(employeeList);
				}
				else {
					
					result.setResultMsg("没有获取到职位信息！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	
	@RequestMapping(value="unitSalarySetting", method=RequestMethod.GET)
	public Object getUnitSalarySetting(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BeanResult result = new BeanResult();
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				Map<String, Map<String, List<Map<String, Object>>>> salarySetting = salaryFramworkService.getUnitGradeSetting(actionForm.toMap());
				if (salarySetting != null && salarySetting.size() > 0) {
					
					result.setResultMsg("获取部门薪酬体系信息成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(salarySetting);
				}
				else {
					
					result.setResultMsg("获取部门薪酬体系信息失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="resetEmployeeSalaryGrade", method=RequestMethod.POST)
	public Object resetEmployeeSalaryGrade(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ResponseResult result = new ResponseResult();
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				int affectedCount = salaryFramworkService.resetEmployeeSalaryGrade(actionForm.toMap());
				if (affectedCount > 0) {
					
					result.setResultMsg("人员薪酬重置成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("人员薪酬重置失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="employeeSalaryForPage", method=RequestMethod.GET)
	public Object selectEmployeeSalaryForPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PageBeanResult result = new PageBeanResult();
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				PageBean page = salaryFramworkService.selectEmployeeSalaryForPage(actionForm.toMap());
				if (page != null && page.getList() != null && page.getList().size() > 0) {
					
					result.setResultMsg("获取人员薪酬信息成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				}
				else {
					
					result.setResultMsg("获取人员薪酬信息失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	
	//判断薪酬体系是否启用
	@RequestMapping(value="isStart", method=RequestMethod.GET)
	public Object isStart(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				BeanResult result = new BeanResult();
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				List<Map<String, Object>> salaryFramwork = salaryFramworkService.isStart(actionForm.toMap());
				
				
				
				if (salaryFramwork != null) {
					//已有启用的体系
					if(salaryFramwork.size() > 0){
						result.setResultMsg("已有启用的体系");
						result.setStatusCode(ResponseConstants.OK);//200
						result.setData(salaryFramwork);
					}else{
						//启用或者关闭体系
						int res=salaryFramworkService.updateStatus(actionForm.toMap());
						if(res>0){
							result.setResultMsg("修改成功");
							result.setStatusCode("100");//成功
							//result.setData();
						}else{
							result.setResultMsg("修改失败！");
							result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);//502
							//result.setData();
						}
					}
				}
				else {
					
					result.setResultMsg("查询是否启用出错！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="employeeSalary", method=RequestMethod.GET)
	public Object selectEmployeeSalary(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				BeanResult result = new BeanResult();
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				Map<String, Object> employeeSalary = salaryFramworkService.selectEmployeeSalary(actionForm.toMap());
				if (employeeSalary != null && employeeSalary.size() > 0) {
					
					result.setResultMsg("获取人员薪酬信息成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(employeeSalary);
				}
				else {
					
					result.setResultMsg("获取人员薪酬信息失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	//得到资源等级单价
	@RequestMapping(value="unitSalary", method=RequestMethod.GET)
	public Object unitSalary(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				BeanResult result = new BeanResult();
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				Map<String, Object> employeeSalary = salaryFramworkService.selectUnitSalary(actionForm.toMap());
				if (employeeSalary != null && employeeSalary.size() > 0) {
					
					result.setResultMsg("获取成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(employeeSalary);
				}
				else {
					
					result.setResultMsg("获取失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	
	@RequestMapping(value="copySalaryFramework", method=RequestMethod.POST)
	public Object copySalaryFramework(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ResponseResult result = new ResponseResult();
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				boolean affectedResult = salaryFramworkService.copySalaryFramework(actionForm.toMap());
				if (affectedResult) {
					
					result.setResultMsg("人员薪酬体系复制成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("人员薪酬体系复制失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="exportEmployeeSalary", method=RequestMethod.GET)
	public Object selectEmployeeSalaryForList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				List<Map<String, Object>> salaryList = salaryFramworkService.selectEmployeeSalaryForList(actionForm.toMap());
				
				return salaryList;
			}
			
			/**
			
			  * <p>Title: doWithFinish</p>
			
			  * <p>Description: </p>
			
			  * @param request
			  * @param response
			  * @param data
			  * @param viewType
			  * @param controller
			  * @return
			
			  * @see com.glens.eap.platform.framework.web.support.AbstractProcessRequestHandler#doWithFinish(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.String, com.glens.eap.platform.core.web.EAPController)
			
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
						String fileName = URLEncoder.encode("人员薪酬信息",
								"UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("人员薪酬信息");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("employeeSalary", response.getOutputStream(),
								Map.class, dataList);

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
	
	//修改人力资源成本
	@RequestMapping(value="updateLabourcostByProNo", method=RequestMethod.POST)
	public Object updateLabourcostByProNo(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ResponseResult result = new ResponseResult();
				SalaryFramworkService salaryFramworkService = (SalaryFramworkService) getService();
				boolean affectedResult = salaryFramworkService.getLabourcostByProNo(actionForm.toMap());
				
				if (affectedResult) {
					result.setResultMsg("人力资源成本修改成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("人力资源成本修改成功！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
}
