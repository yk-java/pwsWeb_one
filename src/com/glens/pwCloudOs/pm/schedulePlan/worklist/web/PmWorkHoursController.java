package com.glens.pwCloudOs.pm.schedulePlan.worklist.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
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
import com.glens.eap.sys.orgEmployee.account.service.PfAccountService;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.notice.service.SmNoticeService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.service.PmBaseService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmBaseVo;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.service.PmWorkHoursService;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.vo.PmWorkHours;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.vo.PmWorkList;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.vo.PmWorkListExcelVo;

@RequestMapping(value = "pmsServices/pm/schedulePlan/workHours")
@FormProcessor(clazz = "com.glens.pwCloudOs.pm.schedulePlan.worklist.web.PmWorkHoursForm")
public class PmWorkHoursController extends EAPJsonAbstractController {
	private SmNoticeService smNoticeService;
	private PmBaseService pmBaseService;
	private PfAccountService pfAccountService;

	public PfAccountService getPfAccountService() {
		return pfAccountService;
	}

	public void setPfAccountService(PfAccountService pfAccountService) {
		this.pfAccountService = pfAccountService;
	}

	public PmBaseService getPmBaseService() {
		return pmBaseService;
	}

	public void setPmBaseService(PmBaseService pmBaseService) {
		this.pmBaseService = pmBaseService;
	}

	public SmNoticeService getSmNoticeService() {
		return smNoticeService;
	}

	public void setSmNoticeService(SmNoticeService smNoticeService) {
		this.smNoticeService = smNoticeService;
	}

	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmWorkHoursForm form = (PmWorkHoursForm) actionForm;
				UserToken token = getToken(request);
				if (token != null) {

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {
						form.setEmployeecode(token.getEmployeeCode());
					} else if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {
						form.setEmployeecode(token.getEmployeeCode());
					}
				}
				PageBean page = getService().queryForPage(form.toMap(),
						form.getCurrentPage(), form.getPerpage());
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

	@RequestMapping(value = "checkWorkHours", method = RequestMethod.GET)
	public Object checkWorkHours(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmWorkHoursForm form = (PmWorkHoursForm) actionForm;
				UserToken token = getToken(request);
				if (token != null) {

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {
						form.setEmployeecode(token.getEmployeeCode());
					} else if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {
						form.setEmployeecode(token.getEmployeeCode());
					}
				}
				PmWorkHoursService pmHoursService = (PmWorkHoursService) getService();
				int res = pmHoursService.checkWorkHours(form.toMap());
				if (res > 0) {
					return BeanResult.success("审核成功", res);
				} else {
					return BeanResult.fail("审核失败");
				}
			}

		});
	}

	@RequestMapping(value = "backWorkHours", method = RequestMethod.GET)
	public Object backWorkHours(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmWorkHoursForm form = (PmWorkHoursForm) actionForm;
				PmWorkHoursService pmHoursService = (PmWorkHoursService) getService();
				int res = pmHoursService.backWorkHours(form.toMap());
				if (res > 0) {
					PmWorkHours pmWorkHours = (PmWorkHours) pmHoursService
							.findByWorkerAndWorkDate(form.toMap());
					List<String> empCodes = new ArrayList<String>();
					empCodes.add(pmWorkHours.getWorker());// 当事人

					PmBaseVo pmBase = (PmBaseVo) pmBaseService
							.findById(pmWorkHours.getProNo());
					empCodes.add(pmBase.getEmployeecode());// 项目负责人
					empCodes.add(pmBase.getDistrictManager());// 区域经理
					// employeecode 转 account_code
					List<String> accountCodes = pfAccountService
							.selectAccountsByEmployeecodes(empCodes);
					// 消息通知
					smNoticeService.sendMessage(
							"系统通知",
							pmWorkHours.getWorkDate()
									+ pmWorkHours.getWorkerName() + "的工作明细被退回",
							pmWorkHours.getProName() + " ["
									+ pmWorkHours.getProNo() + "] "
									+ pmWorkHours.getWorkDate() + " "
									+ pmWorkHours.getWorkerName()
									+ "的工作明细被退回，原因："
									+ pmWorkHours.getCheckDescr(), accountCodes);
					return BeanResult.success("退回成功", res);
				} else {
					return BeanResult.fail("退回失败");
				}
			}

		});
	}

	@RequestMapping(value = "checkBackWorkHours", method = RequestMethod.GET)
	public Object backCheckWorkHours(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmWorkHoursForm form = (PmWorkHoursForm) actionForm;
				PmWorkHoursService pmHoursService = (PmWorkHoursService) getService();
				int res = pmHoursService.backWorkHours(form.toMap());
				if (res > 0) {
					return BeanResult.success("退回成功", res);
				} else {
					return BeanResult.fail("退回失败");
				}
			}

		});
	}

	@RequestMapping(value = "importWorkList", method = RequestMethod.POST)
	public Object importWorkList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				PmWorkHoursForm form = (PmWorkHoursForm) actionForm;
				MultipartFile excelFile = form.getExcelFile();
				ExcelHelper excelHelper = new ExcelHelper();
				List<PmWorkListExcelVo> data;
				try {
					String fileName = ((CommonsMultipartFile) excelFile)
							.getFileItem().getName();
					data = excelHelper.getData("pmWorkList",
							excelFile.getInputStream(), fileName,
							PmWorkListExcelVo.class);
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail("解析Excel失败");
				}
				PmWorkHoursService service = (PmWorkHoursService) getService();

				int res = service.importWorkList(data);
				
				return BeanResult.success("SUCCESS", res);
				
			}
		});
	}

	@RequestMapping(value = "export", method = RequestMethod.GET)
	public Object export(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmWorkHoursForm form = (PmWorkHoursForm) actionForm;
				UserToken token = getToken(request);
				if (token != null) {

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {
						form.setEmployeecode(token.getEmployeeCode());
					} else if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {
						form.setEmployeecode(token.getEmployeeCode());
					}
				}
				PmWorkHoursService service = (PmWorkHoursService) getService();
				List<PmWorkListExcelVo> data = service.export(form.toMap());
				return data;
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
						String fileName = URLEncoder.encode("工作明细", "UTF-8");
						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("Sheet1");
						List<PmWorkListExcelVo> dataList = (List<PmWorkListExcelVo>) data;
						excelHelper.writeData("pmWorkList",
								response.getOutputStream(),
								PmWorkListExcelVo.class, dataList);
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

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmWorkHoursService pmWorkHoursService = (PmWorkHoursService) getService();
				PmWorkHours pmWorkHours = (PmWorkHours) actionForm.toVo();
				String jsonStr = request.getParameter("jsonStr");
				List<PmWorkList> workList = JSONObject.parseArray(jsonStr,
						PmWorkList.class);
				if (workList != null) {
					for (Iterator iterator = workList.iterator(); iterator
							.hasNext();) {
						PmWorkList pmWorkList = (PmWorkList) iterator.next();
						pmWorkList.setProNo(pmWorkHours.getProNo());
						pmWorkList.setWorkDate(pmWorkHours.getWorkDate());
						pmWorkList.setWorker(pmWorkHours.getWorker());
					}
					pmWorkHours.setWorkList(workList);
				}
				boolean ok = pmWorkHoursService.save(pmWorkHours);
				KeyResult result = new KeyResult();
				if (ok) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				} else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败");
				}
				return result;
			}
		});
	}

	@RequestMapping(value = "updateSave", method = RequestMethod.POST)
	public Object updateSave(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmWorkHoursService pmWorkHoursService = (PmWorkHoursService) getService();
				PmWorkHours pmWorkHours = (PmWorkHours) actionForm.toVo();
				String jsonStr = request.getParameter("jsonStr");
				List<PmWorkList> workList = JSONObject.parseArray(jsonStr,
						PmWorkList.class);
				for (Iterator iterator = workList.iterator(); iterator
						.hasNext();) {
					PmWorkList pmWorkList = (PmWorkList) iterator.next();
					pmWorkList.setProNo(pmWorkHours.getProNo());
					pmWorkList.setWorkDate(pmWorkHours.getWorkDate());
					pmWorkList.setWorker(pmWorkHours.getWorker());
				}
				pmWorkHours.setWorkList(workList);
				boolean ok = pmWorkHoursService.updateSave(pmWorkHours);
				KeyResult result = new KeyResult();
				if (ok) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				} else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}
				return result;
			}
		});
	}

	@RequestMapping(value = "findByWorkerAndWorkDate", method = RequestMethod.GET)
	public Object findByWorkerAndWorkDate(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmWorkHoursForm form = (PmWorkHoursForm) actionForm;
				PmWorkHoursService pmWorkHoursService = (PmWorkHoursService) getService();
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("proNo", form.getProNo());
				params.put("workDate", form.getWorkDate());
				params.put("worker", form.getWorker());
				ValueObject vo = pmWorkHoursService
						.findByWorkerAndWorkDate(params);
				BeanResult result = new BeanResult();
				if (vo != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(vo);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}

		});
	}

	@RequestMapping(value = "workHoursStatistics", method = RequestMethod.GET)
	public Object workHoursStatistics(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmWorkHoursService pmWorkHoursService = (PmWorkHoursService) getService();
				Map<String, Object> params = new HashMap<String, Object>();
				PmWorkHoursForm form = (PmWorkHoursForm) actionForm;
				params.put("proNo", form.getProNo());
				params.put("startTime", form.getStartTime());
				params.put("endTime", form.getEndTime());
				params.put("worker", form.getWorker());

				UserToken token = getToken(request);
				if (token != null) {

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {
						params.put("employeecode", token.getEmployeeCode());
					} else if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {
						params.put("employeecode", token.getEmployeeCode());
					}
				}

				List<Map<String, Object>> res = pmWorkHoursService
						.workHoursStatistics(params);
				ListResult result = new ListResult();
				if (res != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(res);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}
		});
	}

	@RequestMapping(value = "workHoursStatisticsExport", method = RequestMethod.GET)
	public Object workHoursStatisticsExport(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmWorkHoursService pmWorkHoursService = (PmWorkHoursService) getService();
				Map<String, Object> params = new HashMap<String, Object>();
				PmWorkHoursForm form = (PmWorkHoursForm) actionForm;
				params.put("proNo", form.getProNo());
				params.put("startTime", form.getStartTime());
				params.put("endTime", form.getEndTime());
				params.put("worker", form.getWorker());

				UserToken token = getToken(request);
				if (token != null) {

					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {
						params.put("employeecode", token.getEmployeeCode());
					} else if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {
						params.put("employeecode", token.getEmployeeCode());
					}
				}

				List<Map<String, Object>> res = pmWorkHoursService
						.workHoursStatisticsExport(params);
				for (int i = 0; i < res.size(); i++) {
					Map<String, Object> item = res.get(i);
					item.put("listNum", i + 1);
				}
				return res;
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
						String fileName = URLEncoder.encode("人员工时统计", "UTF-8");
						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("Sheet1");
						List<PmWorkListExcelVo> dataList = (List<PmWorkListExcelVo>) data;
						excelHelper.writeData("workHoursStatistics",
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
}
