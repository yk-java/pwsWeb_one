package com.glens.eap.sys.orgEmployee.employee.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.eap.sys.orgEmployee.employee.service.MdEmployeeService;
import com.glens.eap.sys.orgEmployee.employee.vo.MdEmployee;
import com.glens.pwCloudOs.notice.service.SmNoticeService;
import com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.service.PmDayQcService;
import com.glens.pwCloudOs.weixin.util.WeixinThread;

@FormProcessor(clazz = "com.glens.eap.sys.orgEmployee.employee.web.MdEmployeeForm")
@RequestMapping("/pmsServices/sys/orgEmployee/employee")
public class MdEmployeeController extends EAPJsonAbstractController {

	private PmDayQcService pmDayQcService;

	private SmNoticeService smNoticeService;

	public void setPmDayQcService(PmDayQcService pmDayQcService) {
		this.pmDayQcService = pmDayQcService;
	}

	public void setSmNoticeService(SmNoticeService smNoticeService) {
		this.smNoticeService = smNoticeService;
	}

	@RequestMapping(value = "importExcel", method = RequestMethod.POST)
	public Object importExcel(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				MdEmployeeForm form = (MdEmployeeForm) actionForm;

				MdEmployeeService ser = (MdEmployeeService) getService();

				List resultlist = pmDayQcService.importExcel(form
						.getExcelFile());

				List unitList = ser.getUnits();// 部门
				List jobList = ser.getJobs();// 职务
				List propertyList = ser.getProperty();// 工作性质
				List contractList = ser.getContracts();// 合同性质

				MdEmployeeService mdEmployeeService = (MdEmployeeService) EAPContext
						.getContext().getBean("mdEmployeeService");

				int num = 0;
				for (int i = 0; i < resultlist.size(); i++) {

					try {
						Map m = (Map) resultlist.get(i);

						MdEmployee employee = new MdEmployee();

						if (m.get("档案号") != null) {
							employee.setRecordno(m.get("档案号").toString());
						}
						if (m.get("姓名") != null) {
							employee.setEmployeename(m.get("姓名").toString());
						}
						if (m.get("性别") != null) {
							if (m.get("性别").toString().equals("男")) {
								employee.setSex("0");
							} else {
								employee.setSex("1");
							}
						}
						if (m.get("身份证号") != null) {
							employee.setIdcard(m.get("身份证号").toString());
						}
						if (m.get("生日") != null) {
							employee.setBirthday(m.get("生日").toString());
						}

						if (m.get("手机号码") != null) {
							System.out.println(m.get("手机号码").toString());
							employee.setMobilephone1(m.get("手机号码").toString());
						}
						if (m.get("所在部门") != null) {
							String name = m.get("所在部门").toString();
							for (int j = 0; j < unitList.size(); j++) {
								Map tempm = (Map) unitList.get(j);
								String unitName = tempm.get("unitName")
										.toString();
								String unitCode = tempm.get("unitCode")
										.toString();
								if (unitName.equals(name)) {
									employee.setUnitCode(unitCode);
									employee.setUnitName(unitName);
									break;
								}
							}
						}

						if (m.get("职务") != null) {
							String name = m.get("职务").toString();
							for (int j = 0; j < jobList.size(); j++) {
								Map tempm = (Map) jobList.get(j);
								String jobName = tempm.get("jobName")
										.toString();
								String jobCode = tempm.get("jobCode")
										.toString();
								if (jobName.equals(name)) {
									employee.setJobCode(jobCode);
									employee.setJobName(jobName);
									break;
								}
							}
						}

						if (m.get("工作性质") != null) {
							String name = m.get("工作性质").toString();
							for (int j = 0; j < propertyList.size(); j++) {
								Map tempm = (Map) propertyList.get(j);
								String propertyName = tempm.get("propertyName")
										.toString();
								String propertyCode = tempm.get("propertyCode")
										.toString();
								if (propertyName.equals(name)) {
									employee.setJobPropertyCode(propertyCode);
									employee.setJobPropertyName(propertyName);
									break;
								}
							}
						}

						if (m.get("类别") != null) {
							if (m.get("类别").toString().equals("总部")) {
								employee.setJobClass1(1);
								employee.setJobClass1Name("总部");
							} else {
								employee.setJobClass1(2);
								employee.setJobClass1Name("项目");
							}
						}

						if (m.get("计算数据") != null) {
							if (m.get("计算数据").toString().equals("内业")) {
								employee.setJobClass2(1);
								employee.setJobClass2Name("内业");
							} else {
								employee.setJobClass2(2);
								employee.setJobClass2Name("外业");
							}
						}
						if (m.get("招聘地点") != null) {
							employee.setRecruitPlace(m.get("招聘地点").toString());
						}
						if (m.get("到职日期") != null) {
							employee.setOfficeDate(m.get("到职日期").toString());
						}
						if (m.get("转正日期") != null) {
							employee.setWorkDate(m.get("转正日期").toString());
						}

						if (m.get("合同性质") != null) {
							String name = m.get("合同性质").toString();
							for (int j = 0; j < contractList.size(); j++) {
								Map tempm = (Map) contractList.get(j);
								String contractName = tempm.get("contractName")
										.toString();
								String contractCode = tempm.get("contractCode")
										.toString();
								if (contractName.equals(name)) {
									employee.setContractPropertyCode(contractCode);
									employee.setContractPropertyName(contractName);
									break;
								}
							}
						}
						if (m.get("现工作证编号") != null) {
							employee.setJobNo(m.get("现工作证编号").toString());
						}
						if (m.get("合同签订日期") != null) {
							employee.setContractDateS(m.get("合同签订日期")
									.toString());
						}

						if (m.get("合同到期日期") != null) {
							employee.setContractDateE(m.get("合同到期日期")
									.toString());
						}

						if (m.get("学历") != null) {
							employee.setDegree(m.get("学历").toString());
						}
						if (m.get("专业") != null) {
							employee.setMajorDegree(m.get("专业").toString());
						}

						if (m.get("毕业时间") != null) {
							employee.setGraduateTime(m.get("毕业时间").toString());
						}

						if (m.get("毕业院校") != null) {
							employee.setGraduateSchool(m.get("毕业院校").toString());
						}

						if (m.get("银行名称") != null) {
							employee.setBankName(m.get("银行名称").toString());
						}

						if (m.get("银行账号") != null) {
							employee.setBankAccount(m.get("银行账号").toString());
						}

						if (m.get("公积金") != null) {
							employee.setWeal1Status(m.get("公积金").toString());
						}
						if (m.get("社保卡") != null) {
							employee.setWeal2Status(m.get("社保卡").toString());
						}
						if (m.get("意外险") != null) {
							employee.setWeal3Status(m.get("意外险").toString());
						}

						if (m.get("紧急联系人姓名") != null) {
							employee.setEmergencyContactorName(m.get("紧急联系人姓名")
									.toString());
						}

						if (m.get("紧急联系人关系") != null) {
							employee.setEmergencyContactorRelation(m.get(
									"紧急联系人关系").toString());
						}
						if (m.get("紧急联系人号码") != null) {
							employee.setEmergencyContactorPhone(m
									.get("紧急联系人号码").toString());
						}

						if (m.get("家庭住址") != null) {
							employee.setAddr(m.get("家庭住址").toString());
						}

						Map result = mdEmployeeService.saveEmployee(employee);
						if (result.get("statusCode") == "200") {
							num = num + 1;
						}
					} catch (Exception e) {
						e.printStackTrace();

					}

				}

				KeyResult result = new KeyResult();
				if (true) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("共" + resultlist.size() + "条,成功导入"
							+ num + "条！");
					result.setGenerateKey(actionForm.getGenerateKey());
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("导入失败");
				}
				return result;

			}
		});

	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				MdEmployeeService mdEmployeeService = (MdEmployeeService) EAPContext
						.getContext().getBean("mdEmployeeService");
				MdEmployeeForm form = (MdEmployeeForm) actionForm;

				Map result = mdEmployeeService.queryEmployeeList(form);

				return result;
			}

		});
	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "add")
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				MdEmployeeService mdEmployeeService = (MdEmployeeService) EAPContext
						.getContext().getBean("mdEmployeeService");
				MdEmployeeForm form = (MdEmployeeForm) actionForm;
				MdEmployee employee = (MdEmployee) form.toVo();
				Map result = mdEmployeeService.saveEmployee(employee);
				return result;
			}

		});
	}

	@RequestMapping(method = RequestMethod.GET, value = "selectJob")
	public Object selectJob(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				MdEmployeeService mdEmployeeService = (MdEmployeeService) EAPContext
						.getContext().getBean("mdEmployeeService");
				Map result = mdEmployeeService.selectJob();

				return result;
			}

		});
	}

	@RequestMapping(method = RequestMethod.GET, value = "selectJobProperty")
	public Object selectJobProperty(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				MdEmployeeService mdEmployeeService = (MdEmployeeService) EAPContext
						.getContext().getBean("mdEmployeeService");
				Map result = mdEmployeeService.selectJobProperty();

				return result;
			}

		});
	}

	@RequestMapping(method = RequestMethod.GET, value = "selectContractProperty")
	public Object selectContractProperty(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				MdEmployeeService mdEmployeeService = (MdEmployeeService) EAPContext
						.getContext().getBean("mdEmployeeService");
				Map result = mdEmployeeService.selectContractProperty();

				return result;
			}

		});
	}

	@RequestMapping(method = RequestMethod.GET, value = "export")
	public Object export(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				MdEmployeeService mdEmployeeService = (MdEmployeeService) EAPContext
						.getContext().getBean("mdEmployeeService");
				MdEmployeeForm form = (MdEmployeeForm) actionForm;

				List<MdEmployee> result = mdEmployeeService
						.queryForExport(form);

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
						response.addHeader("Content-Disposition",
								"attachment;filename=EMPLOYEE.xls");
						ExcelHelper excelHelper = new ExcelHelper();
						List dataList = (List) data;
						excelHelper.writeData(response.getOutputStream(),
								MdEmployee.class, dataList);
						// response.getOutputStream().write("abc".getBytes());
					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
			}

		});
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				MdEmployeeForm form = (MdEmployeeForm) actionForm;

				MdEmployeeService mdEmployeeService = (MdEmployeeService) EAPContext
						.getContext().getBean("mdEmployeeService");

				String employeeCode = form.getEmployeecode();
				String employeeName = form.getEmployeename();

				// 查询相关人员 项目经理，部门经理，行政负责人等
				List<Map<String, String>> jobNoList = mdEmployeeService
						.queryRelatedEmployeeList(employeeCode);
				// JOB_NO
				List<String> mList = new ArrayList();
				for (Map<String, String> m : jobNoList) {
					String jobNo = m.get("JOB_NO");
					mList.add(jobNo);
				}

				ResponseResult result = new ResponseResult();
				List<Map<String, Object>> list = mdEmployeeService
						.queryDorm(form.toVo());

				if (list.size() > 0) {// 有在租的宿舍 不能离职
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					String msg = "该员工还有在租的宿舍，不能离职！";
					result.setResultMsg(msg);
					return result;
				}
				List<Map<String, Object>> list1 = mdEmployeeService
						.queryAsset(actionForm.toVo());

				if (list1.size() > 0) {// 有在租的资产 不能离职
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					String msg = "该员工还有在租的资产，不能离职！";
					result.setResultMsg(msg);
					return result;
				}

				MdEmployee employee = (MdEmployee) actionForm.toVo();
				int iCount = mdEmployeeService.update(employee);
				new WeixinThread(employee, "2").start();

				mdEmployeeService.memberLeave(actionForm.toVo());// 从项目中删除该员工
				mdEmployeeService.deleteAccount(actionForm.toVo());// 删除该人员的账号

				if (iCount > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("离职成功！");
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("离职失败");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "leave", method = RequestMethod.POST)
	public Object leave(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				MdEmployeeForm form = (MdEmployeeForm) actionForm;

				MdEmployeeService mdEmployeeService = (MdEmployeeService) EAPContext
						.getContext().getBean("mdEmployeeService");

				String employeeCode = form.getEmployeecode();
				String employeeName = form.getEmployeename();

				// 查询相关人员 项目经理，部门经理，行政负责人等
				List<Map<String, String>> jobNoList = mdEmployeeService
						.queryRelatedEmployeeList(employeeCode);
				// JOB_NO
				List<String> mList = new ArrayList();
				for (Map<String, String> m : jobNoList) {
					String jobNo = m.get("JOB_NO");
					mList.add(jobNo);
				}

				ResponseResult result = new ResponseResult();
				List<Map<String, Object>> list = mdEmployeeService
						.queryDorm(form.toVo());

				if (list.size() > 0) {// 有在租的宿舍 不能离职
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					String msg = "员工%s还有在租的宿舍，不能离职！";
					result.setResultMsg(String.format(msg, employeeName));
					smNoticeService.sendWeixinMessage("离职",
							String.format(msg, employeeName), mList);
					return result;
				}
				List<Map<String, Object>> list1 = mdEmployeeService
						.queryAsset(actionForm.toVo());

				if (list1.size() > 0) {// 有在租的资产 不能离职
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					String msg = "员工%s还有在租的资产，不能离职！";
					result.setResultMsg(String.format(msg, employeeName));
					smNoticeService.sendWeixinMessage("离职",
							String.format(msg, employeeName), mList);
					return result;
				}

				MdEmployee employee = (MdEmployee) actionForm.toVo();

				int iCount = mdEmployeeService.update(employee);
				mdEmployeeService.memberLeave(actionForm.toVo());// 从项目中删除该员工
				mdEmployeeService.deleteAccount(actionForm.toVo());// 删除该人员的账号
				new WeixinThread(employee, "3").start();

				if (iCount > 0) {
					try {
						String msg = "员工%s于%s离职！！";
						smNoticeService.sendWeixinMessage("离职", String.format(
								msg, employeeName, DateTimeUtil.formatDate(
										new Date(), DateTimeUtil.FORMAT_1)),
								mList);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("离职成功！");
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("离职失败");
				}

				return result;
			}

		});
	}

	// 获取人员在租的资产或宿舍
	@RequestMapping(value = "getAssetOrDorm", method = RequestMethod.GET)
	public Object getAssetOrDorm(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MdEmployeeService mdEmployeeService = (MdEmployeeService) EAPContext
						.getContext().getBean("mdEmployeeService");

				ResponseResult result = new ResponseResult();

				Map map = new HashMap();

				List<Map<String, Object>> list = mdEmployeeService
						.queryDorm(actionForm.toVo());
				map.put("list", list);

				List<Map<String, Object>> list1 = mdEmployeeService
						.queryAsset(actionForm.toVo());

				map.put("list1", list1);
				// result.setStatusCode(ResponseConstants.OK);
				// .setResultMsg("没有在租的资产或宿舍！");
				return map;
			}

		});
	}

	// 判断该人员是否有在租的资产或者宿舍
	@RequestMapping(value = "ishave", method = RequestMethod.POST)
	public Object ishave(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MdEmployeeService mdEmployeeService = (MdEmployeeService) EAPContext
						.getContext().getBean("mdEmployeeService");

				ResponseResult result = new ResponseResult();
				List<Map<String, Object>> list = mdEmployeeService
						.queryDorm(actionForm.toVo());

				if (list.size() > 0) {// 有在租的宿舍 不能离职
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("该员工还有在租的宿舍！");
					return result;
				}
				List<Map<String, Object>> list1 = mdEmployeeService
						.queryAsset(actionForm.toVo());

				if (list1.size() > 0) {// 有在租的资产 不能离职
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("该员工还有在租的资产！");
					return result;
				}

				result.setStatusCode(ResponseConstants.OK);
				result.setResultMsg("没有在租的资产或宿舍！");

				return result;
			}

		});
	}

	@RequestMapping(method = RequestMethod.GET, value = "listNotInPro")
	public Object listNotInPro(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				MdEmployeeService mdEmployeeService = (MdEmployeeService) EAPContext
						.getContext().getBean("mdEmployeeService");
				MdEmployeeForm form = (MdEmployeeForm) actionForm;
				Map params = new HashMap();
				params.putAll(PageBean.getPageParamsFromReq(request));
				String unitCode = request.getParameter("unitCode");
				String searchName = request.getParameter("searchName");
				String companyCode = request.getParameter("companyCode");
				params.put("unitCode", unitCode);
				params.put("searchName", searchName);
				params.put("workStatus", 1);
				params.put("companyCode", companyCode);
				PageBean page = mdEmployeeService.queryNotInProForPage(params);

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

		});
	}
}
