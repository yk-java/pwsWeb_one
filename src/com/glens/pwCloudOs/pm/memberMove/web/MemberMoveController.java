package com.glens.pwCloudOs.pm.memberMove.web;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.core.web.ProcessRequestHandler;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.materielMg.assetMg.asset.service.AssetService;
import com.glens.pwCloudOs.materielMg.comprehensiveQuery.service.ComprehensiveQueryService;
import com.glens.pwCloudOs.notice.service.SmNoticeService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.service.PmBaseService;
import com.glens.pwCloudOs.pm.memberMove.service.MemberMoveService;

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.memberMove.web.PmMemberMoveForm")
@RequestMapping("pmsServices/memberMove")
public class MemberMoveController extends EAPJsonAbstractController {
	private SmNoticeService smNoticeService;
	private PmBaseService pmBaseService;

	public SmNoticeService getSmNoticeService() {
		return smNoticeService;
	}

	public void setSmNoticeService(SmNoticeService smNoticeService) {
		this.smNoticeService = smNoticeService;
	}

	public PmBaseService getPmBaseService() {
		return pmBaseService;
	}

	public void setPmBaseService(PmBaseService pmBaseService) {
		this.pmBaseService = pmBaseService;
	}

	// 资产调动发起 procStauts=2
	@RequestMapping(value = "assetMove", method = RequestMethod.POST)
	public Object assetMove(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;
				MemberMoveService ser = (MemberMoveService) getService();
				String status = form.getStatus();// 1项目内已调动 2项目组已调动 3 无资产
				String moveCode = form.getMoveCode();
				Map m = new HashMap();
				m.put("moveCode", moveCode);

				ser.updateProc(m);// 更新 pm_member_move_proc sys_process_flag
									// 完成状态

				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String processTime = sdf1.format(new Date());

				// 1：项目-项目
				// 2：项目-部门
				// 3：部门-项目
				// 4：部门-部门
				String moveType = form.getMoveType();

				if (status.equals("1")) {
					// 更新状态（flag） 查询项目负责人 procstatus
					if (moveType.equals("1")) {// 项目调项目
						List list = ser.getmanagerinpro(m);
						if (list != null & list.size() > 0) {
							Map map = (Map) list.get(0);
							String managerName = map.get("promanager")
									.toString();
							String managerCode = map.get("employeecode")
									.toString();
							Map insertm = new HashMap();
							insertm.put("companyCode", form.getCompanyCode());
							insertm.put("moveCode", moveCode);
							insertm.put("nodeName", "资产调动确认");
							insertm.put("processorCode", managerCode);
							insertm.put("processorName", managerName);
							insertm.put("processTime", processTime);
							insertm.put("sysProcessFlag", "0");
							insertm.put("nodeOrder", 3);
							ser.insertProc(insertm);
							m.put("procStatus", 3);
							ser.updateProcStatus(m);// 更新prostatus 状态
						}
					} else if (moveType.equals("2")) {// 项目-部门
						List list = ser.getmanagerinpro(m);
						if (list != null & list.size() > 0) {
							Map map = (Map) list.get(0);
							String managerName = map.get("promanager")
									.toString();
							String managerCode = map.get("employeecode")
									.toString();
							Map insertm = new HashMap();
							insertm.put("companyCode", form.getCompanyCode());
							insertm.put("moveCode", moveCode);
							insertm.put("nodeName", "资产调动确认");
							insertm.put("processorCode", managerCode);
							insertm.put("processorName", managerName);
							insertm.put("processTime", processTime);
							insertm.put("sysProcessFlag", "0");
							insertm.put("nodeOrder", 3);
							ser.insertProc(insertm);
							m.put("procStatus", 3);
							ser.updateProcStatus(m);// 更新prostatus 状态
						}

					} else if (moveType.equals("3")) {// 部门-项目 不用经过项目负责人确认
						Map insertm = new HashMap();
						insertm.put("companyCode", form.getCompanyCode());
						insertm.put("moveCode", moveCode);
						insertm.put("nodeName", "资产调动确认");
						insertm.put("processorCode", "E20170911092847860242");
						insertm.put("processorName", "肖勇");
						insertm.put("processTime", processTime);
						insertm.put("sysProcessFlag", "0");
						insertm.put("nodeOrder", 4);
						ser.insertProc(insertm);
						m.put("procStatus", 4);
						ser.updateProcStatus(m);// 更新prostatus 状态
					} else if (moveType.equals("4")) {// 部门-部门
						Map insertm = new HashMap();
						insertm.put("companyCode", form.getCompanyCode());
						insertm.put("moveCode", moveCode);
						insertm.put("nodeName", "资产调动确认");
						insertm.put("processorCode", "E20170911092847860242");
						insertm.put("processorName", "肖勇");
						insertm.put("processTime", processTime);
						insertm.put("sysProcessFlag", "0");
						insertm.put("nodeOrder", 4);
						ser.insertProc(insertm);
						m.put("procStatus", 4);
						ser.updateProcStatus(m);// 更新prostatus 状态
					}

				} else if (status.equals("2")) {

					if (moveType.equals("1")) {// 项目调项目
						List list = ser.getmanageroutpro(m);
						if (list != null & list.size() > 0) {
							Map map = (Map) list.get(0);
							String managerName = map.get("promanager")
									.toString();
							String managerCode = map.get("employeecode")
									.toString();

							Map insertm = new HashMap();
							insertm.put("companyCode", form.getCompanyCode());
							insertm.put("moveCode", moveCode);
							insertm.put("nodeName", "资产调动确认");
							insertm.put("processorCode", managerCode);
							insertm.put("processorName", managerName);
							insertm.put("processTime", processTime);
							insertm.put("sysProcessFlag", "0");
							insertm.put("nodeOrder", 3);
							ser.insertProc(insertm);
							m.put("procStatus", 3);
							ser.updateProcStatus(m);// 更新prostatus 状态
						}
					} else if (moveType.equals("2")) {// 项目-部门
						List list = ser.getmanageroutpro(m);
						if (list != null & list.size() > 0) {
							// Map map=(Map)list.get(0);
							// String
							// managerName=map.get("promanager").toString();
							// String
							// managerCode=map.get("employeecode").toString();
							Map insertm = new HashMap();
							insertm.put("companyCode", form.getCompanyCode());
							insertm.put("moveCode", moveCode);
							insertm.put("nodeName", "宿舍调动发起");
							insertm.put("processorCode", "E20170911092847860242");
							insertm.put("processorName", "肖勇");
							insertm.put("processTime", processTime);
							insertm.put("sysProcessFlag", "0");
							insertm.put("nodeOrder", 4);
							ser.insertProc(insertm);
							m.put("procStatus", 4);
							ser.updateProcStatus(m);// 更新prostatus 状态
						}
					} else if (moveType.equals("3")) {// /部门-项目 不用经过项目负责人确认

						List list = ser.getmanageroutpro(m);
						if (list != null & list.size() > 0) {
							Map map = (Map) list.get(0);
							String managerName = map.get("promanager")
									.toString();
							String managerCode = map.get("employeecode")
									.toString();
							Map insertm = new HashMap();
							insertm.put("companyCode", form.getCompanyCode());
							insertm.put("moveCode", moveCode);
							insertm.put("nodeName", "资产调动确认");
							insertm.put("processorCode", managerCode);
							insertm.put("processorName", managerName);
							insertm.put("processTime", processTime);
							insertm.put("sysProcessFlag", "0");
							insertm.put("nodeOrder", 3);
							ser.insertProc(insertm);
							m.put("procStatus", 3);
							ser.updateProcStatus(m);// 更新prostatus 状态
						}

					} else if (moveType.equals("4")) {// 部门调部门
						Map insertm = new HashMap();
						insertm.put("companyCode", form.getCompanyCode());
						insertm.put("moveCode", moveCode);
						insertm.put("nodeName", "宿舍调动发起");
						insertm.put("processorCode", "E20170911092847860242");
						insertm.put("processorName", "肖勇");
						insertm.put("processTime", processTime);
						insertm.put("sysProcessFlag", "0");
						insertm.put("nodeOrder", 4);
						ser.insertProc(insertm);
						m.put("procStatus", 4);
						ser.updateProcStatus(m);// 更新prostatus 状态
					}

				} else if (status.equals("3")) {
					System.out.println(moveType);
					if (moveType.equals("1") || moveType.equals("2")) {
						Map insertm = new HashMap();
						insertm.put("companyCode", form.getCompanyCode());
						insertm.put("moveCode", moveCode);
						insertm.put("nodeName", "宿舍调动发起");
						insertm.put("processorCode", "E20170911092847860242");
						insertm.put("processorName", "肖勇");
						insertm.put("processTime", processTime);
						insertm.put("sysProcessFlag", "0");
						insertm.put("nodeOrder", 4);
						ser.insertProc(insertm);
						m.put("procStatus", 4);
						ser.updateProcStatus(m);// 更新prostatus 状态
					} else {
						Map insertm = new HashMap();
						insertm.put("companyCode", form.getCompanyCode());
						insertm.put("moveCode", moveCode);
						insertm.put("nodeName", "调动完毕");
						insertm.put("processorCode", "E20171101104100768327");
						insertm.put("processorName", "朱启娟");
						insertm.put("processTime", processTime);
						insertm.put("sysProcessFlag", "0");
						insertm.put("nodeOrder", 6);
						ser.insertProc(insertm);
						m.put("procStatus", 6);
						ser.updateProcStatus(m);// 更新prostatus 状态
					}

				}

				KeyResult result = new KeyResult();
				if (true) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					// result.setGenerateKey(actionForm.getGenerateKey());
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("失败");
				}

				return result;
			}

		});
	}

	// 项目负责人确认资产 prostatus=3

	@RequestMapping(value = "comfirmAsset", method = RequestMethod.POST)
	public Object comfirmAsset(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;
				MemberMoveService ser = (MemberMoveService) getService();
				String employeeCode = form.getEmployeeCode();

				Map m = new HashMap();
				m.put("moveCode", form.getMoveCode());
				// m.put("employeeCode", form.getEmployeeCode());
				ser.updateProc(m);// 更新 pm_member_move_proc sys_process_flag
									// 完成状态

				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String processTime = sdf1.format(new Date());

				String moveType = form.getMoveType();
				System.out.println(moveType + "000");

				if (moveType.equals("1") || moveType.equals("2")) {
					Map insertm = new HashMap();
					insertm.put("companyCode", form.getCompanyCode());
					insertm.put("moveCode", form.getMoveCode());
					insertm.put("nodeName", "宿舍调动发起");
					insertm.put("processorCode", "E20170911092847860242");
					insertm.put("processorName", "肖勇");
					insertm.put("processTime", processTime);
					insertm.put("sysProcessFlag", "0");
					insertm.put("nodeOrder", 4);
					ser.insertProc(insertm);
					m.put("procStatus", 4);
					ser.updateProcStatus(m);// 更新prostatus 状态
				} else {
					Map insertm = new HashMap();
					insertm.put("companyCode", form.getCompanyCode());
					insertm.put("moveCode", form.getMoveCode());
					insertm.put("nodeName", "调动完毕");
					insertm.put("processorCode", "E20171101104100768327");
					insertm.put("processorName", "朱启娟");
					insertm.put("processTime", processTime);
					insertm.put("sysProcessFlag", "0");
					insertm.put("nodeOrder", 6);
					ser.insertProc(insertm);
					m.put("procStatus", 6);
					ser.updateProcStatus(m);// 更新prostatus 状态
				}

				KeyResult result = new KeyResult();
				if (true) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					// result.setGenerateKey(actionForm.getGenerateKey());
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("失败");
				}

				return result;
			}

		});
	}

	// 宿舍调动发起 procStauts=4
	@RequestMapping(value = "dormMove", method = RequestMethod.POST)
	public Object dormMove(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;
				MemberMoveService ser = (MemberMoveService) getService();

				String moveCode = form.getMoveCode();
				Map m = new HashMap();
				m.put("moveCode", moveCode);

				ser.updateProc(m);// 更新 pm_member_move_proc sys_process_flag
									// 完成状态

				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String processTime = sdf1.format(new Date());

				// 更新状态（flag） 查询项目负责人 procstatus

				String moveType = form.getMoveType();

				if (moveType.equals("1") || moveType.equals("2")) {// 项目-项目
																	// 项目-部门
					List list = ser.getmanagerinpro(m);

					if (list != null & list.size() > 0) {
						Map map = (Map) list.get(0);
						String managerName = map.get("promanager").toString();
						String managerCode = map.get("employeecode").toString();

						Map insertm = new HashMap();
						insertm.put("companyCode", form.getCompanyCode());
						insertm.put("moveCode", moveCode);
						insertm.put("nodeName", "宿舍调动确认");
						insertm.put("processorCode", managerCode);
						insertm.put("processorName", managerName);
						insertm.put("processTime", processTime);
						insertm.put("sysProcessFlag", "0");
						insertm.put("nodeOrder", 5);
						ser.insertProc(insertm);
						m.put("procStatus", 5);
						ser.updateProcStatus(m);// 更新prostatus 状态
					}
				} else if (moveType.equals("3") || moveType.equals("4")) { // 部门-项目
																			// 部门-部门
					Map insertm = new HashMap();
					insertm.put("companyCode", form.getCompanyCode());
					insertm.put("moveCode", moveCode);
					insertm.put("nodeName", "调动完毕");
					insertm.put("processorCode", "E20171101104100768327");
					insertm.put("processorName", "朱启娟");
					insertm.put("processTime", processTime);
					insertm.put("sysProcessFlag", "0");
					insertm.put("nodeOrder", 6);
					ser.insertProc(insertm);
					m.put("procStatus", 6);
					ser.updateProcStatus(m);// 更新prostatus 状态
				}

				KeyResult result = new KeyResult();
				if (true) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					// result.setGenerateKey(actionForm.getGenerateKey());
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("失败");
				}

				return result;
			}

		});
	}

	// 项目负责人确认宿舍 prostatus=5

	@RequestMapping(value = "comfirmDorm", method = RequestMethod.POST)
	public Object comfirmDorm(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;
				MemberMoveService ser = (MemberMoveService) getService();
				String employeeCode = form.getEmployeeCode();

				Map m = new HashMap();
				m.put("moveCode", form.getMoveCode());
				m.put("employeeCode", form.getEmployeeCode());
				ser.updateProc(m);// 更新 pm_member_move_proc sys_process_flag
									// 完成状态

				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String processTime = sdf1.format(new Date());

				Map insertm = new HashMap();
				insertm.put("companyCode", form.getCompanyCode());
				insertm.put("moveCode", form.getMoveCode());
				insertm.put("nodeName", "人员调动完毕");
				insertm.put("processorCode", "E20171101104100768327");
				insertm.put("processorName", "朱启娟");
				insertm.put("processTime", processTime);
				insertm.put("sysProcessFlag", "0");
				insertm.put("nodeOrder", 6);
				ser.insertProc(insertm);
				m.put("procStatus", 6);
				ser.updateProcStatus(m);// 更新prostatus 状态
				KeyResult result = new KeyResult();
				if (true) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					// result.setGenerateKey(actionForm.getGenerateKey());
				} else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("失败");
				}
				return result;
			}
		});
	}

	// 最终确认调动 prostatus=6
	@RequestMapping(value = "lastConfirm", method = RequestMethod.POST)
	public Object lastConfirm(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;
				MemberMoveService ser = (MemberMoveService) getService();
				// String employeeCode=form.getEmployeeCode();

				Map m = new HashMap();
				m.put("moveCode", form.getMoveCode());
				// m.put("employeeCode", form.getEmployeeCode());
				ser.updateProc(m);

				boolean isok = ser.updateMessage(m);// 更新 pm_member_move_proc
													// sys_process_flag 完成状态

				KeyResult result = new KeyResult();
				if (isok) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					// result.setGenerateKey(actionForm.getGenerateKey());
				} else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("失败");
				}
				return result;
			}
		});
	}

	// 根据不同的角色 得到对应的流程列表
	@RequestMapping(value = "getListByCode", method = RequestMethod.GET)
	public Object getListByCode(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;
				MemberMoveService ser = (MemberMoveService) getService();

				Map m = new HashMap();
				m.put("employeeCode", form.getEmployeeCode());

				m.put("proNo1", form.getProNo1());
				m.put("employeeName", form.getEmployeeName());
				m.put("fromDate", form.getFromDate());
				m.put("toDate", form.getToDate());

				List list = ser.getListByCode(m);

				ListResult result = new ListResult();

				if (list != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(list);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	// 调动函列表
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PageBean page = getService().queryForPage(actionForm.toMap(),
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

	// 得到详细的流程

	@RequestMapping(value = "getDetail", method = RequestMethod.GET)
	public Object getDetail(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;
				MemberMoveService ser = (MemberMoveService) getService();

				Map m = new HashMap();
				m.put("moveCode", form.getMoveCode());

				List list = ser.getDetailProc(m);

				ListResult result = new ListResult();

				if (list != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(list);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	// 项目内调动
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;

				String date = form.getMoveDate();
				MemberMoveService ser = (MemberMoveService) getService();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
						.getBean(CodeWorker.SIMPLE_CODE_WORKER);
				String moveCode = codeWorker.createCode("M");
				int year = Integer.parseInt(date.substring(0, 4));
				int month = 0;
				try {
					Date d = sdf.parse(date);

					month = d.getMonth() + 1;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				form.setMoveYear(year);
				form.setMoveMonth(month);
				form.setMoveType("1");
				form.setMoveCode(moveCode);
				form.setProcStatus("2");
				boolean ok = getService().insert(form.toVo());

				String processTime = sdf1.format(new Date());
				Map m = new HashMap();
				m.put("companyCode", form.getCompanyCode());
				m.put("moveCode", moveCode);
				m.put("nodeName", "人员调动");
				m.put("processorCode", form.getEmployeeCode1());
				m.put("processorName", form.getEmployeeName1());
				m.put("processTime", processTime);
				m.put("sysProcessFlag", "1");
				m.put("nodeOrder", 1);
				ser.insertProc(m);

				Map m1 = new HashMap();
				m1.put("companyCode", form.getCompanyCode());
				// String moveCode1 = codeWorker.createCode("M");
				m1.put("moveCode", moveCode);
				m1.put("nodeName", "资产调动发起");
				m1.put("processorCode", "E20170911092847860242");
				m1.put("processorName", "肖勇");
				m1.put("processTime", processTime);
				m1.put("sysProcessFlag", "0");
				m1.put("nodeOrder", 2);
				ser.insertProc(m1);

				// 消息推送
				List maplist = new ArrayList();

				Map accountMap = new HashMap();
				accountMap.put("proNo", form.getProNo1());
				List accountList = ser.getAcccount(accountMap);
				String accountCode = "";
				if (accountList != null && accountList.size() > 0) {
					Map temp = (Map) accountList.get(0);
					accountCode = temp.get("accountCode").toString();
				}

				Map accountMap1 = new HashMap();
				accountMap1.put("proNo", form.getProNo2());
				List accountList1 = ser.getAcccount(accountMap1);
				String accountCode1 = "";
				if (accountList1 != null && accountList1.size() > 0) {
					Map temp = (Map) accountList1.get(0);
					accountCode1 = temp.get("accountCode").toString();

				}

				if (accountCode.equals(accountCode1)) {
					maplist.add(accountCode);
				} else {
					maplist.add(accountCode);
					maplist.add(accountCode1);
				}

				
				
			     /*List sendList=ser.getSendList();
			     for(int i=0;i<sendList.size();i++){
			    	 Map sendMap=(Map)sendList.get(i);
			    	 if(sendMap.get("accountCode")!=null){
			    		 maplist.add(sendMap.get("accountCode").toString());
			    	 } 
			     }*/

				// smNoticeService.sendMessage("人力资源","人员调动","原"+form.getProName1()+form.getEmployeeName()+"调动至"+form.getProName2(),maplist);

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

	// 部门调项目
	@RequestMapping(value = "moveToPro", method = RequestMethod.POST)
	public Object moveToPro(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				MemberMoveService ser = (MemberMoveService) getService();

				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;
				String date = form.getMoveDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
						.getBean(CodeWorker.SIMPLE_CODE_WORKER);
				String moveCode = codeWorker.createCode("M");
				int year = Integer.parseInt(date.substring(0, 4));
				int month = 0;
				try {
					Date d = sdf.parse(date);

					month = d.getMonth() + 1;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				form.setMoveYear(year);
				form.setMoveMonth(month);
				form.setMoveType("3");
				form.setMoveCode(moveCode);
				form.setProcStatus("2");
				boolean ok = getService().insert(form.toVo());

				String processTime = sdf1.format(new Date());

				Map m = new HashMap();
				m.put("companyCode", form.getCompanyCode());
				m.put("moveCode", moveCode);
				m.put("nodeName", "人员调动");
				m.put("processorCode", "E20171101104100768327");
				m.put("processorName", "朱启娟");
				m.put("processTime", processTime);
				m.put("sysProcessFlag", "1");
				m.put("nodeOrder", 1);
				ser.insertProc(m);

				Map m1 = new HashMap();
				m1.put("companyCode", form.getCompanyCode());
				// String moveCode1 = codeWorker.createCode("M");
				m1.put("moveCode", moveCode);
				m1.put("nodeName", "资产调动发起");
				m1.put("processorCode", "E20170911092847860242");
				m1.put("processorName", "肖勇");
				m1.put("processTime", processTime);
				m1.put("sysProcessFlag", "0");
				m1.put("nodeOrder", 2);
				ser.insertProc(m1);

				// 消息推送
				List maplist = new ArrayList();
				Map accountMap1 = new HashMap();
				accountMap1.put("proNo", form.getProNo2());
				List accountList1 = ser.getAcccount(accountMap1);
				String accountCode1 = "";
				if (accountList1 != null && accountList1.size() > 0) {
					Map temp = (Map) accountList1.get(0);
					accountCode1 = temp.get("accountCode").toString();
					maplist.add(accountCode1);
				}

				 /*List sendList=ser.getSendList();
			     for(int i=0;i<sendList.size();i++){
			    	 Map sendMap=(Map)sendList.get(i);
			    	 if(sendMap.get("accountCode")!=null){
			    		 maplist.add(sendMap.get("accountCode").toString());
			    	 } 
			     }*/

				// smNoticeService.sendMessage("人力资源","人员调动","原"+form.getProName1()+form.getEmployeeName()+"调动至"+form.getProName2(),maplist);

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

	// 部门调部门
	@RequestMapping(value = "unitMoveUnit", method = RequestMethod.POST)
	public Object unitMoveUnit(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;
				String date = form.getMoveDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
						.getBean(CodeWorker.SIMPLE_CODE_WORKER);
				String moveCode = codeWorker.createCode("M");
				int year = Integer.parseInt(date.substring(0, 4));
				int month = 0;
				try {
					Date d = sdf.parse(date);

					month = d.getMonth() + 1;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				form.setMoveYear(year);
				form.setMoveMonth(month);
				form.setMoveType("4");
				form.setMoveCode(moveCode);
				form.setProcStatus("2");
				boolean ok = getService().insert(form.toVo());
				// SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
				MemberMoveService ser = (MemberMoveService) getService();
				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				String processTime = sdf1.format(new Date());

				Map m = new HashMap();
				m.put("companyCode", form.getCompanyCode());
				m.put("moveCode", moveCode);
				m.put("nodeName", "人员调动");
				m.put("processorCode", form.getEmployeeCode());
				m.put("processorName", form.getEmployeeName());
				m.put("processTime", processTime);
				m.put("sysProcessFlag", "1");
				m.put("nodeOrder", 1);
				ser.insertProc(m);

				Map m1 = new HashMap();
				m1.put("companyCode", form.getCompanyCode());
				// String moveCode1 = codeWorker.createCode("M");
				m1.put("moveCode", moveCode);
				m1.put("nodeName", "资产调动发起");
				m1.put("processorCode", "E20170911092847860242");
				m1.put("processorName", "肖勇");
				m1.put("processTime", processTime);
				m1.put("sysProcessFlag", "0");
				m1.put("nodeOrder", 2);
				ser.insertProc(m1);

				// 消息推送
				List maplist = new ArrayList();

				/*List sendList=ser.getSendList();
			     for(int i=0;i<sendList.size();i++){
			    	 Map sendMap=(Map)sendList.get(i);
			    	 if(sendMap.get("accountCode")!=null){
			    		 maplist.add(sendMap.get("accountCode").toString());
			    	 } 
			     }*/

				// smNoticeService.sendMessage("人力资源","人员调动","原"+form.getProName1()+form.getEmployeeName()+"调动至"+form.getProName2(),maplist);

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

	// 项目调部门
	@RequestMapping(value = "moveToUnit", method = RequestMethod.POST)
	public Object moveToUnit(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;
				String date = form.getMoveDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				int year = Integer.parseInt(date.substring(0, 4));
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
						.getBean(CodeWorker.SIMPLE_CODE_WORKER);
				String moveCode = codeWorker.createCode("M");
				int month = 0;
				try {
					Date d = sdf.parse(date);

					month = d.getMonth() + 1;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				form.setMoveYear(year);
				form.setMoveMonth(month);
				form.setMoveType("2");
				form.setMoveCode(moveCode);
				form.setProcStatus("2");
				boolean ok = getService().insert(form.toVo());

				MemberMoveService ser = (MemberMoveService) getService();
				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				String processTime = sdf1.format(new Date());

				Map m = new HashMap();
				m.put("companyCode", form.getCompanyCode());
				m.put("moveCode", moveCode);
				m.put("nodeName", "人员调动");
				m.put("processorCode", form.getEmployeeCode());
				m.put("processorName", form.getEmployeeName());
				m.put("processTime", processTime);
				m.put("sysProcessFlag", "1");
				m.put("nodeOrder", 1);
				ser.insertProc(m);

				Map m1 = new HashMap();
				m1.put("companyCode", form.getCompanyCode());
				// String moveCode1 = codeWorker.createCode("M");
				m1.put("moveCode", moveCode);
				m1.put("nodeName", "资产调动发起");
				m1.put("processorCode", "E20170911092847860242");
				m1.put("processorName", "肖勇");
				m1.put("processTime", processTime);
				m1.put("sysProcessFlag", "0");
				m1.put("nodeOrder", 2);
				ser.insertProc(m1);

				// 消息推送
				List maplist = new ArrayList();

				Map accountMap1 = new HashMap();
				accountMap1.put("proNo", form.getProNo2());
				List accountList1 = ser.getAcccount(accountMap1);
				String accountCode1 = "";
				if (accountList1 != null && accountList1.size() > 0) {
					Map temp = (Map) accountList1.get(0);
					accountCode1 = temp.get("accountCode").toString();
					maplist.add(accountCode1);
				}
				/*List sendList=ser.getSendList();
			     for(int i=0;i<sendList.size();i++){
			    	 Map sendMap=(Map)sendList.get(i);
			    	 if(sendMap.get("accountCode")!=null){
			    		 maplist.add(sendMap.get("accountCode").toString());
			    	 } 
			     }*/

				// smNoticeService.sendMessage("人力资源","人员调动","原"+form.getProName1()+form.getEmployeeName()+"调动至"+form.getProName2(),maplist);

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

	@RequestMapping(value = "getJobs", method = RequestMethod.GET)
	public Object getJobs(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				MemberMoveService service = (MemberMoveService) getService();
				List<Map<String, Object>> resultList = service.getJobs();
				if (resultList != null && resultList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取车辆油耗统计结果成功！");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取车辆油耗统计结果失败！");
				}

				return result;

			}

		});
	}

	@RequestMapping(value = "memberMoveIn", method = RequestMethod.GET)
	public Object memberMoveIn(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				MemberMoveService service = (MemberMoveService) getService();
				List<Map<String, Object>> resultList = service
						.memberMoveIn(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取调入统计结果成功！");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取调入统计结果失败！");
				}

				return result;

			}

		});
	}

	@RequestMapping(value = "memberMoveOut", method = RequestMethod.GET)
	public Object memberMoveOut(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				MemberMoveService service = (MemberMoveService) getService();
				List<Map<String, Object>> resultList = service
						.memberMoveOut(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取调出统计结果成功！");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取调出统计结果失败！");
				}

				return result;

			}

		});
	}

	@RequestMapping(value = "memberMoveChar", method = RequestMethod.GET)
	public Object memberMoveChar(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				MemberMoveService service = (MemberMoveService) getService();
				List<Map<String, Object>> resultList = service
						.memberMoveChar(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取结果成功！");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取失败！");
				}

				return result;

			}

		});
	}

	@RequestMapping(value = "memberMoves", method = RequestMethod.GET)
	public Object memberMoves(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				MemberMoveService service = (MemberMoveService) getService();
				List<Map<String, Object>> resultList = service
						.memberMoves(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取结果成功！");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取失败！");
				}

				return result;

			}

		});
	}

	// 定时任务更新
	@RequestMapping(value = "getMovelist", method = RequestMethod.GET)
	public Object getMovelist(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				MemberMoveService service = (MemberMoveService) getService();
				List<Map<String, Object>> resultList = service.getMovelist();

				return result;

			}

		});
	}

	// 员工年龄情况表
	@RequestMapping(value = "employeeAges", method = RequestMethod.GET)
	public Object employeeAges(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				MemberMoveService service = (MemberMoveService) getService();
				List resultList = service.employeeAges(actionForm.toMap());
				if (resultList != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取结果成功！");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取失败！");
				}

				return result;

			}

		});
	}

	// 员工学历情况表
	@RequestMapping(value = "degreeStatics", method = RequestMethod.GET)
	public Object degreeStatics(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				MemberMoveService service = (MemberMoveService) getService();
				List resultList = service.degreeStatics(actionForm.toMap());
				if (resultList != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取结果成功！");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取失败！");
				}

				return result;

			}

		});
	}

	// 员工职位分类情况表

	@RequestMapping(value = "jobsStatics", method = RequestMethod.GET)
	public Object jobsStatics(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				MemberMoveService service = (MemberMoveService) getService();
				List resultList = service.jobsStatics(actionForm.toMap());
				if (resultList != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取结果成功！");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取失败！");
				}

				return result;

			}

		});
	}

	// 专业技术人员统计

	@RequestMapping(value = "majorDegreeStatics", method = RequestMethod.GET)
	public Object majorDegreeStatics(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				MemberMoveService service = (MemberMoveService) getService();
				List resultList = service.majorDegreeStatics(actionForm.toMap());
				if (resultList != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取结果成功！");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取失败！");
				}

				return result;

			}

		});
	}

	// 人力资源分析表
	@RequestMapping(value = "getHumanResources", method = RequestMethod.GET)
	public Object getHumanResources(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				MemberMoveService service = (MemberMoveService) getService();

				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;

				List resultList;
				try {
					resultList = service.getHumanResources(form.getDate(),
							form.getUnitCode());

					if (resultList != null) {

						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("获取结果成功！");
						result.setList(resultList);
					} else {

						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setResultMsg("获取失败！");
					}

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return result;

			}

		});
	}

	// 人员地图
	@RequestMapping(value = "isManager", method = RequestMethod.GET)
	public Object isManager(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				MemberMoveService service = (MemberMoveService) getService();

				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;

				List resultList;
				resultList = service.isManager(form.toMap());
				if (resultList != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取结果成功！");
					result.setList(resultList);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取失败！");
				}

				return result;

			}

		});
	}

	// 人员地图
	@RequestMapping(value = "noManager", method = RequestMethod.GET)
	public Object noManager(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				MemberMoveService service = (MemberMoveService) getService();

				PmMemberMoveForm form = (PmMemberMoveForm) actionForm;

				List resultList;
				try {
					resultList = service.noManager(form.toMap());
					if (resultList != null) {
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("获取结果成功！");
						result.setList(resultList);
					} else {
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setResultMsg("获取失败！");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return result;

			}

		});
	}
	
	@RequestMapping(value="exportMoveData", method=RequestMethod.GET)
	public Object exportMoveData(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new ProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				List<Map<String, Object>> personMoveList = getService().queryForList(actionForm.toMap());
				
				return personMoveList;
			}
			
			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String methodName,
					EAPController controller) {
				// TODO Auto-generated method stub
				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {
					
					@Override
					public void render(Map<String, ?> model, HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						// TODO Auto-generated method stub
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("人员调动查询",
								"UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("人员调动查询");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("personMoveSheet", response.getOutputStream(),
								Map.class, dataList);
					}
					
					@Override
					public String getContentType() {
						// TODO Auto-generated method stub
						
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};
				
				view.setData(data);
				modelAndView.setView(view);
				
				return modelAndView;
			}
			
			@Override
			public Object doWithException(HttpServletRequest request,
					HttpServletResponse response, Exception e) {
				// TODO Auto-generated method stub
				
				return null;
			}
		});
	}

}
