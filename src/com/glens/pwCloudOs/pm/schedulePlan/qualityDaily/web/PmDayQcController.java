/**
 * @Title: PmDayQcController.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-12 上午10:39:19
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.web;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.service.PmDayQcService;
import com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.vo.PmDayQcVo;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@RequestMapping("pmsServices/pm/schedulePlan/qualityDaily")
@FormProcessor(clazz = "com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.web.PmDayQcForm")
public class PmDayQcController extends EAPJsonAbstractController {

	@RequestMapping(method = RequestMethod.GET)
	public Object getProDayQc(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				PmDayQcService dayQcService = (PmDayQcService) getService();
				ListResult result = new ListResult();
				Map map = actionForm.toMap();

				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}
				List<PmDayQcVo> resultList = dayQcService.getProDayQc(map);
				if (resultList != null && resultList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取质量日报成功！");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取质量日报失败！");
				}

				return result;
			}
		});
	}

	// 质量日报查看
	@RequestMapping(value = "detailist", method = RequestMethod.GET)
	public Object detailist(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				PmDayQcService dayQcService = (PmDayQcService) getService();
				ListResult result = new ListResult();
				Map map = actionForm.toMap();

				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				List resultList = dayQcService.getDetailList(map);
				if (resultList != null && resultList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取质量日报成功！");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取质量日报失败！");
				}

				return result;
			}

		});
	}

	// 质量分析
	@RequestMapping(value = "qualityAnalysis", method = RequestMethod.GET)
	public Object qualityAnalysis(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				PmDayQcService pmDayQcService = (PmDayQcService) getService();
				ListResult result = new ListResult();

				Map map = actionForm.toMap();

				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}
				List<Map<String, String>> list = pmDayQcService
						.qualityAnalysis(map);
				if (list != null && list.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setList(list);
				} else {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取失败!");
				}

				return result;
			}
		});
	}

	@Override
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmDayQcForm pmDayQcForm = (PmDayQcForm) actionForm;

				// SimpleDateFormat ds=new
				// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// String reportDate=ds.format(new Date());
				System.out.println(pmDayQcForm.getReportDate());
				pmDayQcForm.setReportDate(pmDayQcForm.getReportDate());// 上报日期

				if (pmDayQcForm.getRectifyDate() == "") {
					pmDayQcForm.setRectifyDate(null);
				}
				// 删除 （项目管理部 先删除再新增）
				String isDelete = pmDayQcForm.getIsDelete();

				System.out.println(isDelete);

				if (isDelete.equals("1") || isDelete == "1") {
					PmDayQcService ser = (PmDayQcService) getService();
					ser.deleteDetalList(pmDayQcForm.toVo());
				}

				boolean ok = getService().insert(pmDayQcForm.toVo());
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

	// 导入质量日报

	@RequestMapping(value = "importExcel", method = RequestMethod.POST)
	public Object importExcel(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmDayQcForm pmDayQcForm = (PmDayQcForm) actionForm;

				SimpleDateFormat ds = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String reportDate = ds.format(new Date());
				// System.out.println(pmDayQcForm.getReportDate());
				// pmDayQcForm.setReportDate(pmDayQcForm.getReportDate());//上报日期
				String proNo = pmDayQcForm.getProNo();
				String proName = pmDayQcForm.getProName();
				String categoryName = pmDayQcForm.getCategoryName();
				String companyCode = pmDayQcForm.getCompanyCode();
				// String categoryCode=pmDayQcForm.getCategoryCode();

				PmDayQcService pmDayQcService = (PmDayQcService) getService();

				List<Map<String, String>> checkTypeList = pmDayQcService
						.checkType(); // 检查性质

				List<Map<String, String>> problemList = pmDayQcService
						.problemType(actionForm.toMap()); // 问题类型 categoryCode参数
				List<Map<String, String>> checkclassList = pmDayQcService
						.checkClass();// 检查类型

				List resultlist = pmDayQcService.importExcel(pmDayQcForm
						.getExcelFile());

				int successNum = 0;// 成功条数
				for (int i = 0; i < resultlist.size(); i++) {
					Map m = (Map) resultlist.get(i);
					PmDayQcForm form = new PmDayQcForm();
					form.setProName(proName);
					form.setProNo(proNo);
					form.setCategoryName(categoryName);
					form.setCompanyCode(companyCode);
					form.setReportDate(reportDate);
					if (categoryName.equals("中压清理")) {
						// 变电站 线路名称 核查杆塔、设备名称
						String station = m.get("变电站").toString();
						form.setStation(station);
						String line = m.get("线路名称").toString();
						form.setLine(line);
						String cablepit = m.get("核查杆塔、设备名称").toString();
						form.setCablepit(cablepit);
					} else if (categoryName.indexOf("中压电缆") > -1) {
						// 变电站 线路名称/道路名称 电缆井名称
						String station = m.get("变电站").toString();
						form.setStation(station);
						String line = m.get("线路名称/道路名称").toString();
						form.setLine(line);
						String cablepit = m.get("电缆井名称").toString();
						form.setCablepit(cablepit);
					} else if (categoryName.equals("井井通电")) {
						// 乡、镇 项目包/村庄 工序名称
						String station = m.get("乡、镇").toString();
						form.setStation(station);
						String line = m.get("项目包/村庄").toString();
						form.setLine(line);
						String cablepit = m.get("工序名称").toString();
						form.setCablepit(cablepit);
					} else {
						// 变电站 线路名称/小区名称 台区名称
						String station = m.get("变电站").toString();
						form.setStation(station);
						String line = m.get("线路名称/小区名称").toString();
						form.setLine(line);
						String cablepit = m.get("台区名称").toString();
						form.setCablepit(cablepit);
					}

					String checkTypeName = "";
					if (m.get("核查性质") != null) {
						checkTypeName = m.get("核查性质").toString();
						for (int j = 0; j < checkTypeList.size(); j++) {
							Map tempm = checkTypeList.get(j);

							String checktypecode = tempm.get("checktypecode")
									.toString();
							String checktypename = tempm.get("checktypename")
									.toString();
							if (checkTypeName.equals(checktypename)) {
								form.setCheckTypeCode(checktypecode);
								form.setCheckTypeName(checkTypeName);
								break;
							}
						}
					}

					if (m.get("责任人") != null) {
						form.setDeviceLperson(m.get("责任人").toString());
					}

					if (m.get("结论") != null) {
						String result = m.get("结论").toString();
						if (result.equals("不合格")) {
							form.setCheckResult(0);
						} else if (result.equals("合格")) {
							form.setCheckResult(1);
						}
					}

					if (m.get("是否整改") != null) {
						String result = m.get("是否整改").toString();
						if (result.equals("否")) {
							form.setRectifyStatus(0);
						} else if (result.equals("是")) {
							form.setRectifyStatus(1);
						}
					}

					String problemTypeName = "";
					if (m.get("问题类型") != null) {
						problemTypeName = m.get("问题类型").toString();
						for (int j = 0; j < problemList.size(); j++) {
							Map tempm = problemList.get(j);

							String problemtypecode = tempm.get(
									"problemtypecode").toString();
							String problemtypename = tempm.get(
									"problemtypename").toString();
							if (problemTypeName.equals(problemtypename)) {
								form.setProblemTypeCode(problemtypecode);
								form.setProblemTypeName(problemtypename);
								break;
							}
						}
					}

					if (m.get("核查情况说明") != null) {
						form.setProblemDesc(m.get("核查情况说明").toString());
					}
					if (m.get("整改措施") != null) {
						form.setRectifyDesc(m.get("整改措施").toString());
					}
					if (m.get("核查人") != null) {
						form.setCheckName(m.get("核查人").toString());
					}

					if (m.get("核查日期") != null) {
						form.setCheckDate(m.get("核查日期").toString());
					}
					if (m.get("项目跟进") != null) {
						form.setProPhase(m.get("项目跟进").toString());
					}

					String checkType = "";
					if (m.get("核查类型") != null) {
						checkType = m.get("核查类型").toString();
						for (int j = 0; j < checkclassList.size(); j++) {
							Map tempm = checkclassList.get(j);

							String checkClassCode = tempm.get("checkClassCode")
									.toString();
							String checkClassName = tempm.get("checkClassName")
									.toString();
							if (checkType.equals(checkClassName)) {

								form.setCheckClassCode(checkClassCode);

								break;
							}
						}
					}
					if (m.get("备注") != null) {
						form.setRemarks(m.get("备注").toString());
					}

					try {
						boolean ok = getService().insert(form.toVo());
						if (ok) {
							successNum = successNum + 1;
						}
					} catch (Exception e) {

					}

				}
				boolean ok = true;
				KeyResult result = new KeyResult();
				if (ok) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("共" + resultlist.size() + "条,成功导入"
							+ successNum + "条！");
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
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmDayQcForm pmDayQcForm = (PmDayQcForm) actionForm;
				if (pmDayQcForm.getRectifyDate() == "") {
					pmDayQcForm.setRectifyDate(null);
				}

				int iCount = getService().update(pmDayQcForm.toVo());

				ResponseResult result = new ResponseResult();

				if (iCount > 0) {
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

	@RequestMapping(value = "problemType", method = RequestMethod.GET)
	public Object problemType(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmDayQcForm form = (PmDayQcForm) actionForm;

				PmDayQcService pmDayQcService = (PmDayQcService) getService();
				ListResult result = new ListResult();

				List<Map<String, String>> list = pmDayQcService
						.problemType(actionForm.toMap());
				if (list != null && list.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取问题类别成功!");
					result.setList(list);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取问题类别失败!");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "checkType", method = RequestMethod.GET)
	public Object checkType(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmDayQcService pmDayQcService = (PmDayQcService) getService();
				ListResult result = new ListResult();
				List<Map<String, String>> list = pmDayQcService.checkType();
				if (list != null && list.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取核查性质成功!");
					result.setList(list);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取核查性质失败!");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "checkClass", method = RequestMethod.GET)
	public Object checkClass(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmDayQcService pmDayQcService = (PmDayQcService) getService();
				ListResult result = new ListResult();
				List<Map<String, String>> list = pmDayQcService.checkClass();
				if (list != null && list.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取核查性质成功!");
					result.setList(list);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取核查性质失败!");
				}

				return result;
			}
		});
	}

}
