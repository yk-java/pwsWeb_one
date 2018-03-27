/**
 * @Title: PmBaseController.java
 * @Package com.glens.pwCloudOs.pm.baseMgr.pmBase.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 上午10:55:26
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.baseMgr.pmBase.web;

import java.math.BigDecimal;
import java.net.URLEncoder;
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
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.eap.sys.orgEmployee.account.service.PfAccountService;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.notice.service.SmNoticeService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.service.PmBaseService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmBaseVo;
import com.glens.pwCloudOs.pm.pb.budgetEstimate.service.BudgetEstimateService;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.baseMgr.pmBase.web.PmBaseForm")
@RequestMapping("pmsServices/pm/baseMgr/pmBase")
public class PmBaseController extends EAPJsonAbstractController {

	private SmNoticeService smNoticeService;

	public SmNoticeService getSmNoticeService() {
		return smNoticeService;
	}

	public PfAccountService pfAccountService;

	public void setPfAccountService(PfAccountService pfAccountService) {
		this.pfAccountService = pfAccountService;
	}

	public void setSmNoticeService(SmNoticeService smNoticeService) {
		this.smNoticeService = smNoticeService;
	}

	@RequestMapping(value = "getByProCode", method = RequestMethod.GET)
	public Object getByProCode(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmBaseService pmBaseService = (PmBaseService) getService();
				ValueObject vo = pmBaseService.findByProCode(actionForm.toVo());
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

	@RequestMapping(value = "getProTimeProgress", method = RequestMethod.GET)
	public Object getProTimeProgress(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmBaseService pmBaseService = (PmBaseService) getService();
				String proNo = request.getParameter("proNo");
				String date = request.getParameter("date");
				Float progress = pmBaseService.getProTimeProgress(proNo, date);
				BeanResult result = new BeanResult();
				if (progress != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(progress);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}
		});
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmBaseService ser = (PmBaseService) getService();
				PmBaseForm form = (PmBaseForm) actionForm;
				int tempstatus = form.getTempstatus();

				String proStepName1 = form.getProStepName1();
				String proStepName2 = form.getProPhaseName();

				int iCount = getService().update(actionForm.toVo());

				ResponseResult result = new ResponseResult();

				if (iCount > 0) {
					if (tempstatus == 1) {
						List list = new ArrayList();

						// list.add(form.getAccountCode()); //
						// 项目不维护accountcode所以不用

						// 项目负责人（empcode转accountcode）
						List<String> employeecodes = new ArrayList<String>();
						employeecodes.add(form.getEmployeecode());
						List<String> accountList = pfAccountService
								.selectAccountsByEmployeecodes(employeecodes);
						if (accountList.size() > 0) {
							if (accountList.get(0) != null) {
								list.add(accountList.get(0));
							}
						}
						list.add("028"); // 陈美双
						list.add("A20161205170442304884"); // 张诗梦
						list.add("sgt235sagasdgadshdsa532732253253"); // 唐总
						list.add("030"); // 吴英
						list.add("029"); // 侍瑶
						list.add("c7c9247ba57f4006b54ce05793259328"); // 金戋
						list.add("sdh3252dsy223taewsshdshdshdhdhd"); // 徐金金

						smNoticeService.sendMessage("项目管理部", "项目阶段变更",
								form.getProNo() + form.getProName() + ",项目阶段由"
										+ proStepName1 + "变更为" + proStepName2,
								list);
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

	@RequestMapping(value = "queryEmployeeProList", method = RequestMethod.GET)
	public Object queryEmployeeProList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				String jobNo = request.getParameter("jobNo");
				PmBaseService ser = (PmBaseService) getService();
				List list = ser.queryEmployeeProList(jobNo);
				if (list.size() == 0) {
					list = ser.queryAllProList();
				}
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

	@RequestMapping(value = "getpro", method = RequestMethod.GET)
	public Object getpro(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				PmBaseService ser = (PmBaseService) getService();
				String rowid = request.getParameter("rowid");
				Map pm = ser.getpro(rowid);

				BeanResult result = new BeanResult();
				if (pm != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setData(pm);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});

	}

	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				Map map = actionForm.toMap();
				map.put("companyCode", token.getCompanyCode());

				// 部门经理
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				// 如果是项目经理
				if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("employeeCode", token.getEmployeeCode());
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

	@RequestMapping(value = "getDialogPros", method = RequestMethod.GET)
	public Object getDialogPros(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				Map map = actionForm.toMap();
				map.put("companyCode", token.getCompanyCode());

				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {

					map.put("deptCode", token.getUnitCode());
				}
				PmBaseService ser = (PmBaseService) getService();

				PageBean page = ser.getDialogPros(map,
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
				map.put("companyCode", token.getCompanyCode());
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}
				List data = getService().queryForList(map);
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
						String fileName = URLEncoder.encode("项目列表", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("项目列表");
						List dataList = (List) data;
						excelHelper.writeData(response.getOutputStream(),
								PmBaseVo.class, dataList);
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

	@RequestMapping(value = "queryList", method = RequestMethod.GET)
	public Object queryList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				// Map map = actionForm.toMap();
				// map.put("companyCode", token.getCompanyCode());
				PmBaseService service = (PmBaseService) getService();
				List list = service.queryList();
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

	@RequestMapping(value = "active", method = RequestMethod.POST)
	public Object active(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				ResponseResult result = new ResponseResult();
				PmBaseService pmService = (PmBaseService) getService();
				PmBaseVo vo = (PmBaseVo) actionForm.toVo();
				vo.setCompanyCode(token.getCompanyCode());
				int activeCount = pmService.active(vo);
				if (activeCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("激活项目成功！");
				} else {

					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("激活项目失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "close", method = RequestMethod.POST)
	public Object close(HttpServletRequest request, HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				ResponseResult result = new ResponseResult();
				PmBaseService pmService = (PmBaseService) getService();
				int activeCount = pmService.close(actionForm.toVo());
				if (activeCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("关闭项目成功！");
				} else {

					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("关闭项目失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "addProMember", method = RequestMethod.POST)
	public Object addProMember(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				ResponseResult result = new ResponseResult();
				PmBaseService pmService = (PmBaseService) getService();
				Map<String, String> params = new HashMap<String, String>();
				PmBaseForm pmForm = (PmBaseForm) actionForm;
				params.put("companyCode", pmForm.getCompanyCode());
				params.put("proNo", pmForm.getProNo());
				params.put("employeeCode", pmForm.getEmployeecode());
				params.put("accountCode", pmForm.getAccountCode());
				params.put("inDate", pmForm.getInDate());
				int insertCount = pmService.insertProMember(params);
				if (insertCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增项目成员成功！");
				} else {

					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
					result.setResultMsg("新增项目成员失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "batchAddProMember", method = RequestMethod.POST)
	public Object batchAddProMember(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				ResponseResult result = new ResponseResult();
				PmBaseService pmService = (PmBaseService) getService();
				Map<String, String> params = new HashMap<String, String>();
				PmBaseForm pmForm = (PmBaseForm) actionForm;
				params.put("companyCode", pmForm.getCompanyCode());
				params.put("proNo", pmForm.getProNo());
				params.put("employeeCodes", pmForm.getEmployeecode());
				params.put("accountCodes", pmForm.getAccountCode());
				params.put("inDate", pmForm.getInDate());
				int insertCount = pmService.batchAddProMember(params);
				if (insertCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增项目成员成功！");
				} else {

					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
					result.setResultMsg("新增项目成员失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "deleteProMember", method = RequestMethod.POST)
	public Object deleteProMember(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				ResponseResult result = new ResponseResult();
				PmBaseService pmService = (PmBaseService) getService();
				PmBaseForm pmForm = (PmBaseForm) actionForm;
				int deleteCount = pmService.deleteProMember(
						pmForm.getCompanyCode(), pmForm.getProNo(),
						pmForm.getEmployeecode());
				if (deleteCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("删除项目成员成功！");
				} else {

					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
					result.setResultMsg("删除项目成员失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "logoffProMember", method = RequestMethod.POST)
	public Object logoffProMember(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				ResponseResult result = new ResponseResult();
				PmBaseService pmService = (PmBaseService) getService();
				PmBaseForm pmForm = (PmBaseForm) actionForm;
				int deleteCount = pmService.logoffProMember(
						pmForm.getCompanyCode(), pmForm.getProNo(),
						pmForm.getEmployeecode());
				if (deleteCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("注销项目成员成功！");
				} else {

					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
					result.setResultMsg("注销项目成员失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "proMember", method = RequestMethod.GET)
	public Object selectProMember(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				ListResult result = new ListResult();
				PmBaseService pmService = (PmBaseService) getService();
				PmBaseForm pmForm = (PmBaseForm) actionForm;
				List<Map<String, Object>> memberList = pmService
						.selectProMember(pmForm.getCompanyCode(),
								pmForm.getProNo(), pmForm.getWorkStatue());
				if (memberList != null && memberList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取项目成员成功！");
					result.setList(memberList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取项目成员失败！");
				}

				return result;
			}
		});
	}

	/**
	 * 查询文档类型
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getFileType", method = RequestMethod.GET)
	public Object getFileType(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				ListResult result = new ListResult();
				PmBaseService pmService = (PmBaseService) getService();
				PmBaseForm pmForm = (PmBaseForm) actionForm;
				List<Map<String, String>> memberList = pmService
						.getFileType(actionForm.toMap());
				if (memberList != null && memberList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功！");
					result.setList(memberList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "getFileTypeA", method = RequestMethod.GET)
	public Object getFileTypeA(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				ListResult result = new ListResult();
				PmBaseService pmService = (PmBaseService) getService();
				PmBaseForm pmForm = (PmBaseForm) actionForm;
				List<Map<String, String>> memberList = pmService
						.getFileType(actionForm.toMap());
				if (memberList != null && memberList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功！");
					result.setList(memberList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "queryWaitingList", method = RequestMethod.GET)
	public Object queryWaitingList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmBaseService service = (PmBaseService) getService();
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				String currentPage = request.getParameter("currentPage");
				String perpage = request.getParameter("perpage");

				Map map = actionForm.toMap();

				map.put("currentPage", currentPage);

				map.put("perpage", perpage);

				map.put("companyCode", token.getCompanyCode());

				// 部门经理
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				// 如果是项目经理
				if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("employeeCode", token.getEmployeeCode());
				}

				PageBean page = service.queryWaitingList(map);

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

	/**
	 * 项目预警功能
	 * "1.根据项目最新完工进度、时间进度，项目属性“是否召开启动会”、“是否需要试运行”，判断《项目范围说明书》、《样板确认文件》是否在相应进度前上传
	 * 。 2.判断项目是否具备《计划书》《概算》。 3.判断项目成本是否超支。"
	 * 
	 * @param request
	 * @param response
	 * @return3
	 */
	@RequestMapping(value = "queryProPreWarningList", method = RequestMethod.GET)
	public Object queryProPreWarningList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmBaseService service = (PmBaseService) getService();
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				String currentPage = request.getParameter("currentPage");
				String perpage = request.getParameter("perpage");

				Map map = actionForm.toMap();

				map.put("currentPage", currentPage);

				map.put("perpage", perpage);

				map.put("companyCode", token.getCompanyCode());

				// 部门经理
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				// 如果是项目经理
				if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("employeeCode", token.getEmployeeCode());
				}

				PageBean page = service.queryProPreWarningList(map);

				PageBeanResult result = new PageBeanResult();

				String endtime = "";
				try {
					endtime = DateTimeUtil.formatDate(new Date(),
							DateTimeUtil.FORMAT_2);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (page != null) {
					for (int i = 0; i < page.getList().size(); i++) {
						PmBaseVo pb = (PmBaseVo) page.getList().get(i);
						double val = queryProcostDiff(pb.getProNo(),
								pb.getSdate(), endtime);
						if (val < 0) {
							// 成本差异小于0就表示超支
							pb.setOverSpend("1");
						} else {
							pb.setOverSpend("0");
						}

					}
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

	// 查询项目成本是否超支
	private double queryProcostDiff(String proNo, String starttime,
			String endtime) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("proNo", proNo);
		paramsMap.put("starttime", starttime);
		paramsMap.put("endtime", endtime);
		PmBaseService pmBaseService = (PmBaseService) getService();
		BudgetEstimateService budgetEstimateService = (BudgetEstimateService) EAPContext
				.getContext().getBean("budgetEstimateService");
		Map m = pmBaseService.getProCost(paramsMap);
		BeanResult result = new BeanResult();
		Map<String, Object> pm = new HashMap<String, Object>();
		double resultVal = 0;
		if (m != null) {
			String budgetRate = "0";
			pm.put("proNo", m.get("PRO_NO"));
			pm.put("companyCode", "001");
			pm.put("queryDate", endtime);

			List budgetList = budgetEstimateService.getBUdgetByProNo(pm);
			String budgetEstimateCode = "";
			String versionCode = "";
			double contractValue = 0;
			if (budgetList != null && budgetList.size() > 0) {
				Map mm = (Map) budgetList.get(0);
				budgetEstimateCode = mm.get("BUDGETESTIMATE_CODE").toString();
				versionCode = mm.get("VERSION_CODE").toString();
				if (mm.get("CONTRACT_VALUE") != null) {
					contractValue = Double.parseDouble(mm.get("CONTRACT_VALUE")
							.toString());
					m.put("contractValue", contractValue);
				}
			}

			pm.put("budgetEstimateCode", budgetEstimateCode);
			pm.put("versionCode", versionCode);

			List resultMap1 = null;
			if (!versionCode.equals("")) {
				resultMap1 = budgetEstimateService.getPhaseListByPro(pm); // /////////////////////////////////////////////
			}

			// 概算利润率
			m.put("BUDGET_RATE", budgetRate);

			Map queryMap = new HashMap();
			queryMap.put("proNo", m.get("PRO_NO"));
			queryMap.put("starttime", starttime);
			queryMap.put("endtime", endtime);

			List getProTotalCost = budgetEstimateService.getProCost(queryMap);

			double totalCost = 0;
			String humanCost = "";

			for (int i = 0; i < getProTotalCost.size(); i++) {
				Map tp = (Map) getProTotalCost.get(i);
				if (tp.get("ITEM_CODE").toString().equals("C001")) {
					humanCost = tp.get("COST_VALUE").toString();
				}
				double costValue = Double.parseDouble(tp.get("COST_VALUE")
						.toString());
				totalCost += costValue;
			}

			// 累计预算成本
			double bugetCost = 0;
			try {

				bugetCost = calTotalProphaseCost(resultMap1, starttime, endtime);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			BigDecimal b2 = new BigDecimal(Float.parseFloat(bugetCost + "")
					- Float.parseFloat(totalCost + ""));

			resultVal = b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		}

		return resultVal;
	}

	public double calTotalProphaseCost(List budgetestimateList,
			String startMonth, String endMonth) throws ParseException {
		double totalCost = 0.0;
		if (budgetestimateList == null) {
			return 0;
		}
		if (budgetestimateList.size() <= 0) {
			return 0;
		}
		Map m1 = (Map) budgetestimateList.get(0);
		Map m2 = (Map) budgetestimateList.get(budgetestimateList.size() - 1);
		Date firstDate = DateTimeUtil.getDateFormat(m1.get("PHASE_START_DATE")
				.toString(), DateTimeUtil.FORMAT_2);
		Date lastDate = DateTimeUtil.getDateFormat(m2.get("PHASE_END_DATE")
				.toString(), DateTimeUtil.FORMAT_2);
		String emonthLastDay = DateTimeUtil.lastDayOfDate(endMonth); // 取得查询月份最后一天

		Date smonth = DateTimeUtil.getDateFormat(startMonth + "-01",
				DateTimeUtil.FORMAT_2);
		Date emonth = DateTimeUtil.getDateFormat(emonthLastDay,
				DateTimeUtil.FORMAT_2);
		long dayOfFirst = DateTimeUtil.getTwoTimeDiff(firstDate, smonth);
		long dayOfEnd = DateTimeUtil.getTwoTimeDiff(lastDate, emonth);

		List budgetList = new ArrayList();
		if (dayOfFirst <= 0 && dayOfEnd >= 0) {

			budgetList = budgetestimateList;
			totalCost = getBudgetestimateCost(budgetList);
			//

		} else if (dayOfFirst <= 0 && dayOfEnd < 0) {

			for (int i = 0; i < budgetestimateList.size(); i++) {
				Map m = (Map) budgetestimateList.get(i);
				Date sdate = DateTimeUtil.getDateFormat(
						m.get("PHASE_START_DATE").toString(),
						DateTimeUtil.FORMAT_2);
				Date edate = DateTimeUtil.getDateFormat(m.get("PHASE_END_DATE")
						.toString(), DateTimeUtil.FORMAT_2);
				long dayOfStart = DateTimeUtil.getTwoTimeDiff(sdate, emonth);
				dayOfEnd = DateTimeUtil.getTwoTimeDiff(edate, emonth);
				if (dayOfStart < 0) {

					continue;
				} else if (dayOfStart >= 0 && dayOfEnd <= 0) {

					int monthDiffCount1 = DateTimeUtil.getMonthDiffer(sdate,
							edate);
					int monthDiffCount2 = DateTimeUtil.getMonthDiffer(sdate,
							emonth);

					if (monthDiffCount1 == 0) {

						totalCost += Double.parseDouble(m.get("cost")
								.toString());
					} else {

						totalCost += ((monthDiffCount2 + 1) * Double
								.parseDouble(m.get("cost").toString()))
								/ (monthDiffCount1 + 1);
					}
				} else {
					totalCost += Double.parseDouble(m.get("cost").toString());
				}
			}

		} else if (dayOfFirst > 0 && dayOfEnd >= 0) {
			for (int i = 0; i < budgetestimateList.size(); i++) {
				Map m = (Map) budgetestimateList.get(i);
				Date sdate = DateTimeUtil.getDateFormat(
						m.get("PHASE_START_DATE").toString(),
						DateTimeUtil.FORMAT_2);
				Date edate = DateTimeUtil.getDateFormat(m.get("PHASE_END_DATE")
						.toString(), DateTimeUtil.FORMAT_2);
				long dayOfStart = DateTimeUtil.getTwoTimeDiff(sdate, smonth);
				dayOfEnd = DateTimeUtil.getTwoTimeDiff(edate, smonth);
				if (dayOfEnd > 0) {

					continue;
				} else if (dayOfStart > 0 && dayOfEnd <= 0) {

					int monthDiffCount1 = DateTimeUtil.getMonthDiffer(sdate,
							edate);
					int monthDiffCount2 = DateTimeUtil.getMonthDiffer(smonth,
							edate);

					if (monthDiffCount1 == 0) {

						totalCost += Double.parseDouble(m.get("cost")
								.toString());
					} else {

						totalCost += ((monthDiffCount2 + 1) * Double
								.parseDouble(m.get("cost").toString()))
								/ (monthDiffCount1 + 1);
					}

				} else {
					totalCost += Double.parseDouble(m.get("cost").toString());
				}
			}
		} else if (dayOfFirst > 0 && dayOfEnd < 0) {

			for (int i = 0; i < budgetestimateList.size(); i++) {

				Map m = (Map) budgetestimateList.get(i);
				Date sdate = DateTimeUtil.getDateFormat(
						m.get("PHASE_START_DATE").toString(),
						DateTimeUtil.FORMAT_2);
				Date edate = DateTimeUtil.getDateFormat(m.get("PHASE_END_DATE")
						.toString(), DateTimeUtil.FORMAT_2);
				long dayOfStart1 = DateTimeUtil.getTwoTimeDiff(sdate, smonth);
				long dayOfEnd1 = DateTimeUtil.getTwoTimeDiff(edate, smonth);
				long dayOfStart2 = DateTimeUtil.getTwoTimeDiff(sdate, emonth);
				long dayOfEnd2 = DateTimeUtil.getTwoTimeDiff(edate, emonth);

				if ((dayOfStart1 < 0 && dayOfStart2 < 0) || (dayOfEnd1 > 0)) {

					continue;
				} else if (dayOfStart1 >= 0 && dayOfEnd2 <= 0) {

					int monthDiffCount1 = DateTimeUtil.getMonthDiffer(sdate,
							edate);
					int monthDiffCount2 = DateTimeUtil.getMonthDiffer(smonth,
							emonth);

					if (monthDiffCount1 == 0) {

						totalCost += Double.parseDouble(m.get("cost")
								.toString());
					} else {

						totalCost += ((monthDiffCount2 + 1) * Double
								.parseDouble(m.get("cost").toString()))
								/ (monthDiffCount1 + 1);
					}
				} else if (dayOfStart1 >= 0 && dayOfEnd2 >= 0) {

					int monthDiffCount1 = DateTimeUtil.getMonthDiffer(sdate,
							edate);
					int monthDiffCount2 = DateTimeUtil.getMonthDiffer(smonth,
							edate);

					if (monthDiffCount1 == 0) {

						totalCost += Double.parseDouble(m.get("cost")
								.toString());
					} else {

						totalCost += ((monthDiffCount2 + 1) * Double
								.parseDouble(m.get("cost").toString()))
								/ (monthDiffCount1 + 1);
					}
				} else if (dayOfStart1 <= 0 && dayOfEnd2 <= 0) {

					int monthDiffCount1 = DateTimeUtil.getMonthDiffer(sdate,
							edate);
					int monthDiffCount2 = DateTimeUtil.getMonthDiffer(sdate,
							emonth);

					if (monthDiffCount1 == 0) {

						totalCost += Double.parseDouble(m.get("cost")
								.toString());
					} else {

						totalCost += ((monthDiffCount2 + 1) * Double
								.parseDouble(m.get("cost").toString()))
								/ (monthDiffCount1 + 1);
					}
				} else if (dayOfStart1 <= 0 && dayOfEnd2 >= 0) {

					totalCost += Double.parseDouble(m.get("cost").toString());
				}
			}
		}

		return totalCost;
	}

	public double getBudgetestimateCost(List budgetestimateList) {

		double totalCost = 0.0;
		for (int i = 0; i < budgetestimateList.size(); i++) {
			Map m = (Map) budgetestimateList.get(i);
			totalCost += Double.parseDouble(m.get("cost").toString());
		}

		return totalCost;
	}
}
