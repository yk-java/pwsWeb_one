package com.glens.pwCloudOs.pm.plan.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.eap.sys.orgEmployee.account.service.PfAccountService;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.notice.service.SmNoticeService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.service.PmBaseService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.service.PmProKpiService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmBaseVo;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmProKpi;
import com.glens.pwCloudOs.pm.checkScore.service.CheckScoreService;
import com.glens.pwCloudOs.pm.plan.service.PmKpiLibService;
import com.glens.pwCloudOs.pm.plan.service.PmPlanService;
import com.glens.pwCloudOs.pm.plan.vo.PmPlan;
import com.glens.pwCloudOs.pm.plan.vo.PmPlanKpi;

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.plan.web.PmPlanForm")
@RequestMapping("pmsServices/pm/plan")
public class PmPlanController extends EAPJsonAbstractController {
	PmBaseService pmBaseService;
	PmProKpiService pmProKpiService;
	private CheckScoreService checkScoreService;
	private PfAccountService pfAccountService;
	private SmNoticeService smNoticeService;

	public SmNoticeService getSmNoticeService() {
		return smNoticeService;
	}

	public void setSmNoticeService(SmNoticeService smNoticeService) {
		this.smNoticeService = smNoticeService;
	}

	public PfAccountService getPfAccountService() {
		return pfAccountService;
	}

	public void setPfAccountService(PfAccountService pfAccountService) {
		this.pfAccountService = pfAccountService;
	}

	public PmProKpiService getPmProKpiService() {
		return pmProKpiService;
	}

	public void setPmProKpiService(PmProKpiService pmProKpiService) {
		this.pmProKpiService = pmProKpiService;
	}

	public CheckScoreService getCheckScoreService() {
		return checkScoreService;
	}

	public void setCheckScoreService(CheckScoreService checkScoreService) {
		this.checkScoreService = checkScoreService;
	}

	public PmBaseService getPmBaseService() {
		return pmBaseService;
	}

	public void setPmBaseService(PmBaseService pmBaseService) {
		this.pmBaseService = pmBaseService;
	}

	PmKpiLibService pmKpiLibService;

	public PmKpiLibService getPmKpiLibService() {
		return pmKpiLibService;
	}

	public void setPmKpiLibService(PmKpiLibService pmKpiLibService) {
		this.pmKpiLibService = pmKpiLibService;
	}

	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				Map map = actionForm.toMap();
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				PageBean page = getService().queryForPage(map,
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

	@RequestMapping(value = "export", method = RequestMethod.GET)
	public Object export(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				Map map = actionForm.toMap();
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				List<PmPlan> data = getService().queryForList(map);
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
						String fileName = URLEncoder.encode("项目计划", "UTF-8");
						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("Sheet1");
						List<PmPlan> dataList = (List<PmPlan>) data;
						excelHelper.writeData("planList",
								response.getOutputStream(), PmPlan.class,
								dataList);
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

	@Override
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmPlanService pmPlanService = (PmPlanService) getService();
				PmPlan vo = (PmPlan) pmPlanService.findById(actionForm.toVo());
				Map voMap = vo.toMap();
				String proNo = vo.getProNo();
				PmBaseVo pmBaseVo = (PmBaseVo) pmBaseService.findById(proNo);
				voMap.put("pmBase", pmBaseVo);

				String planNo = vo.getPlanNo();
				// 2016年8月计划(N23689)
				// 2016年第32周计划(N16848)
				String year = planNo.substring(0, 4);
				voMap.put("year", year);

				Integer type = vo.getPlanType();
				String planTypeName = "";
				if (type == 1) {
					planTypeName = "周计划";
					String chooseWeekName = "";
					String chooseWeek = "";
					Pattern p = Pattern.compile("第\\d*周");
					Matcher m = p.matcher(planNo);
					if (m.find()) {
						chooseWeekName = m.group();
					}
					Pattern p2 = Pattern.compile("\\d*");
					Matcher m2 = p2.matcher(chooseWeekName);
					if (m2.find()) {
						chooseWeek = m2.group();
					}
					voMap.put("chooseWeek", chooseWeek);
					voMap.put("chooseWeekName", chooseWeekName);

				} else {
					planTypeName = "月计划";
					String month = "";
					String temp = planNo.substring(5);
					int endIdx = temp.indexOf("月");
					month = temp.substring(0, endIdx);
					voMap.put("month", month);
				}
				voMap.put("planTypeName", planTypeName);

				// kpiList
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("proNo", vo.getProNo());
				params.put("planNo", vo.getPlanNo());
				List<PmPlanKpi> pmPlanKpiList = pmPlanService
						.findByProNoAndPlanNo(params);
				String sdate = vo.getSdate();
				String edate = vo.getEdate();
				List<Map> dayKpiList = pmPlanService.findDayKpiList(proNo,
						sdate, edate);
				voMap.put("pmPlanKpiList", pmPlanKpiList);

				Map<String, Object> statisticsWordload = pmPlanService
						.statisticsWordloadByDate(proNo, sdate, edate);
				Float sumWordload = 0f;
				try {
					sumWordload = Float.parseFloat(statisticsWordload.get(
							"wordload").toString());
				} catch (Exception e) {
				}
				voMap.put("sumWordload", sumWordload);
				Float planComplatePer = 0f;
				if (vo.getPlanWordload() != null && vo.getPlanWordload() != 0) {
					planComplatePer = (sumWordload / vo.getPlanWordload()) * 100;
				}
				voMap.put("planComplatePer", planComplatePer);
				// BigDecimal val = new BigDecimal(0);
				// try {
				// val = (BigDecimal) statisticsWordload.get("owpcnt");
				// } catch (Exception e) {
				// }
				// voMap.put("", val.floatValue());
				// BigDecimal val = new BigDecimal(0);
				// try {
				// val = (BigDecimal) statisticsWordload.get("iwpcnt");
				// } catch (Exception e) {
				// }
				// voMap.put("", val.floatValue());

				// voMap.put("dayKpiList", dayKpiList);
				Map<String, Object> params2 = new HashMap<String, Object>();
				params2.put("proNo", pmBaseVo.getProNo());
				// List kpiLibList = pmKpiLibService.queryForList(params2);
				// TOTO 后期用SQL一次查出
				List<PmProKpi> kpiLibList = pmProKpiService
						.queryForList(params2);
				List<PmPlanKpi> planKpiList = new ArrayList<PmPlanKpi>();
				for (Iterator iterator = kpiLibList.iterator(); iterator
						.hasNext();) {
					PmProKpi pmKpi = (PmProKpi) iterator.next();
					PmPlanKpi pmPlanKpi = new PmPlanKpi();
					pmPlanKpi.setKpiType(pmKpi.getKpiType());
					pmPlanKpi.setKpiCode(pmKpi.getKpiCode());
					pmPlanKpi.setKpiName(pmKpi.getKpiName());
					pmPlanKpi.setKpiUnit(pmKpi.getKpiUnit());
					pmPlanKpi.setKpiValue(getPlanKpiValue(pmPlanKpiList,
							pmKpi.getKpiCode()));
					pmPlanKpi.setRealKpiValue(getRealKpiValue(dayKpiList,
							pmKpi.getKpiCode()));
					planKpiList.add(pmPlanKpi);
				}
				voMap.put("kpiLibList", planKpiList);
				BeanResult result = new BeanResult();
				if (voMap != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(voMap);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}

			private Float getPlanKpiValue(List<PmPlanKpi> pmPlanKpiList,
					String kpiCode) {
				for (Iterator iterator = pmPlanKpiList.iterator(); iterator
						.hasNext();) {
					PmPlanKpi pmPlanKpi = (PmPlanKpi) iterator.next();
					if (kpiCode.equals(pmPlanKpi.getKpiCode())) {
						return pmPlanKpi.getKpiValue();
					}
				}
				return null;
			}

			private Float getRealKpiValue(List<Map> dayKpiList, String kpiCode) {
				for (Iterator iterator = dayKpiList.iterator(); iterator
						.hasNext();) {
					Map dayKpi = (Map) iterator.next();
					if (kpiCode.equals(dayKpi.get("kpiCode"))) {
						Double val = (Double) dayKpi.get("kpiValue");
						Float val2 = val.floatValue();
						return val2;
					}
				}
				return null;
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
				// TODO Auto-generated method stub
				int iCount = getService().update(actionForm.toVo());

				PmPlanForm form = (PmPlanForm) actionForm;

				// 考核评分 TODO 如果重复修改此处是否会有问题？？？？
				int status = form.getStatus();
				if (status == 1) {// 计划考核评分
					checkScoreService.insertCheckScore(form.getCompanyCode(),
							form.getDocTypelibName(), form.getPlanCheckScore(),
							form.getProNo(), form.getEmployeeCode(),
							form.getPlanNo(), form.getEvaluate());
				} else if (status == 2) {// 反馈考核评分
					checkScoreService.insertCheckScore(form.getCompanyCode(),
							form.getDocTypelibName(),
							form.getFeedbackCheckScore(), form.getProNo(),
							form.getEmployeeCode(), form.getPlanNo(),
							form.getFeedbackEval());
				}

				ResponseResult result = new ResponseResult();
				if (iCount > 0) {
					// 通知
					if ("evaluate".equals(request.getParameter("opType"))) {
						String title = "", msg = "";
						if (status == 1) {
							title = form.getPlanNo() + "已评定";
							msg = form.getPlanNo() + "评定内容："
									+ form.getEvaluate();

						} else if (status == 2) {
							title = form.getPlanNo() + "反馈已评定";
							msg = form.getPlanNo() + "反馈评定内容："
									+ form.getFeedbackEval();
						}
						PmBaseVo pmBase = (PmBaseVo) pmBaseService
								.findById(form.getProNo());
						List<String> empCodes = new ArrayList<String>();
						empCodes.add(pmBase.getEmployeecode());// 项目负责人
						// employeecode 转 account_code
						List<String> accountCodes = pfAccountService
								.selectAccountsByEmployeecodes(empCodes);
						// 消息通知
						smNoticeService.sendMessage("系统通知", title, msg,
								accountCodes);
					}
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}

				return result;
			}

		});
	}

}
