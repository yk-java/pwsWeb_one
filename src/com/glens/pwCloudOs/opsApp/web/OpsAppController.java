/**
 * @Title: OpsAppController.java
 * @Package com.glens.pwCloudOs.opsApp.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-10 下午2:36:54
 * @version V1.0
 */

package com.glens.pwCloudOs.opsApp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.eapAuth.core.authentication.handlers.PasswordEncoder;
import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.eap.platform.framework.web.support.response.TokenResult;
import com.glens.pwCloudOs.login.service.LoginService;
import com.glens.pwCloudOs.login.web.LoginForm;
import com.glens.pwCloudOs.opsApp.service.OpsAppService;
import com.glens.pwCloudOs.opsApp.vo.LogAppVo;
import com.glens.pwCloudOs.weixin.bean.WeixinResult;
import com.glens.pwCloudOs.weixin.service.WeixinService;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@FormProcessor(clazz = "com.glens.pwCloudOs.opsApp.web.OpsAppForm")
@RequestMapping("/pmsServices/opsAppService")
public class OpsAppController extends EAPJsonAbstractController {

	private static Log logger = LogFactory.getLog(OpsAppController.class);

	private WeixinService weixinService;

	private LoginService loginService;

	private final static String PASSWORD = "123456";

	@RequestMapping(value = "employeeInfo", method = RequestMethod.GET)
	public Object getEmployeeInfo(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				OpsAppService appService = (OpsAppService) getService();
				OpsAppForm appForm = (OpsAppForm) actionForm;
				BeanResult result = new BeanResult();

				logger.info("开始获取账号代码:" + appForm.getAccountCode() + "对应人员信息！");
				Map<String, Object> employeeInfo = appService
						.getEmployeeInfo(appForm.getAccountCode());
				if (employeeInfo != null && employeeInfo.size() > 0) {

					result.setResultMsg("获取人员信息成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(employeeInfo);

					logger.info("获取账号代码:" + appForm.getAccountCode()
							+ "对应人员信息成功！");
				} else {

					result.setResultMsg("获取人员信息失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);

					logger.info("开始获取账号代码:" + appForm.getAccountCode()
							+ "对应人员失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "employeeField", method = RequestMethod.POST)
	public Object updateEmployeeField(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				OpsAppService appService = (OpsAppService) getService();
				ResponseResult result = new ResponseResult();
				logger.info("开始更新人员信息");
				int updateCount = appService.updateEmployeeField(actionForm
						.toMap());
				if (updateCount > 0) {

					result.setResultMsg("更新人员信息成功！");
					result.setStatusCode(ResponseConstants.OK);
					logger.info("更新人员成功！");
				} else {

					result.setResultMsg("更新人员信息失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					logger.info("更新人员信息失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "processDocTypes", method = RequestMethod.GET)
	public Object getProcessDocTypes(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				OpsAppService appService = (OpsAppService) getService();
				ListResult result = new ListResult();
				logger.info("开始获取过程文档类型");
				List<Map<String, String>> docTypeList = appService
						.getProcessDocTypes();
				if (docTypeList != null && docTypeList.size() > 0) {

					result.setResultMsg("获取过程文档类型成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(docTypeList);
				} else {

					result.setResultMsg("获取过程文档类型失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	// 获取通讯录

	@RequestMapping(value = "getMailList", method = RequestMethod.GET)
	public Object getMailList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				OpsAppService appService = (OpsAppService) getService();
				ListResult result = new ListResult();
				logger.info("开始获取通讯录");

				OpsAppForm form = (OpsAppForm) actionForm;
				String companyCode = form.getCompanyCode();
				Map map = new HashMap();
				map.put("companyCode", companyCode);
				List<Map<String, String>> mailList = appService
						.getMailList(map);
				if (mailList != null && mailList.size() > 0) {

					result.setResultMsg("获取通讯录成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(mailList);
				} else {

					result.setResultMsg("获取通讯录失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	// 获取日志

	@RequestMapping(value = "getAppLog", method = RequestMethod.GET)
	public Object getAppLog(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				OpsAppService appService = (OpsAppService) getService();
				// ListResult result = new ListResult();
				PageBeanResult result = new PageBeanResult();
				logger.info("开始获取日志");

				OpsAppForm form = (OpsAppForm) actionForm;
				String companyCode = form.getCompanyCode();
				String unitCode = form.getUnitCode();
				String employeeCode = form.getEmployeeCode();
				// String employeeName=form.getEmployeeCode();
				int appType = form.getAppType();
				int logType = form.getLogType();
				String accountCode = form.getAccountCode();
				String logFromTime = form.getLogFromTime();
				String logToTime = form.getLogToTime();

				// Map map=new HashMap();
				// map.put("companyCode", companyCode);
				// List<LogAppVo> mailList = appService.getList(companyCode,
				// unitCode, employeeCode, accountCode, logFromTime, logToTime,
				// appType, logType);

				PageBean page = appService.getPageben(form.toMap());
				System.out.println(page.getList().size());
				// List<LogAppVo> mailList = appService.getList("002", "", "",
				// "", "", "", 2, 1);
				if (page != null && page.getList().size() > 0) {

					result.setResultMsg("获取日志成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);

				} else {

					result.setResultMsg("获取通讯录失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "addLog", method = RequestMethod.POST)
	public Object addLog(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				OpsAppService appService = (OpsAppService) getService();
				// ListResult result = new ListResult();
				logger.info("开始新增日志");

				LogAppVo vo = new LogAppVo();
				OpsAppForm form = (OpsAppForm) actionForm;

				vo.setCompanyCode(form.getCompanyCode());
				vo.setUnitCode(form.getUnitCode());
				vo.setEmployeeCode(form.getEmployeeCode());
				vo.setAccountCode(form.getAccountCode());
				vo.setLogType(form.getLogType());
				vo.setLogTime(form.getLogTime());
				vo.setLogContent(form.getLogContent());
				vo.setAppType(form.getAppType());
				vo.setPhoneType(form.getPhoneType());
				vo.setNetworkStatus(form.getNetworkStatus());
				vo.setRemarks(form.getRemarks());

				boolean ok = appService.addLog(vo);
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

	@RequestMapping(value = "processDocs", method = RequestMethod.GET)
	public Object getProcessDoc(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				OpsAppService appService = (OpsAppService) getService();
				PageBeanResult result = new PageBeanResult();
				PageBean page = appService.getProcessDoc(actionForm.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				if (page != null && page.getTotalRecord() > 0) {

					result.setResultMsg("获取过程文档成功!");
					result.setStatusCode(ResponseConstants.OK);

					result.setPageBean(page);
				} else {

					result.setResultMsg("获取过程文档失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "uploadProcessDocs", method = RequestMethod.POST)
	public Object uploadProcessDocs(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				OpsAppService appService = (OpsAppService) getService();
				ResponseResult result = new ResponseResult();
				boolean insertResult = appService
						.batchInsertProcessDoc(actionForm.toMap());
				if (insertResult) {

					result.setResultMsg("上传过程文档成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("上传过程文档失败！");
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public Object test(HttpServletRequest request, HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				ResponseResult result = new ResponseResult();
				result.setResultMsg("连接成功！");
				result.setStatusCode(ResponseConstants.OK);

				return result;
			}
		});
	}

	@RequestMapping(value = "userPsw", method = RequestMethod.POST)
	public Object modifyUserPassword(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				OpsAppService appService = (OpsAppService) getService();
				OpsAppForm appForm = (OpsAppForm) actionForm;
				ResponseResult result = new ResponseResult();
				int icount = appService.updateUserPsw(appForm.getAccountCode(),
						appForm.getPsw());
				if (icount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改密码成功！");
				} else {

					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("修改密码失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "lastMobileVersion", method = RequestMethod.GET)
	public Object getLastMobileVersion(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				OpsAppService appService = (OpsAppService) getService();
				BeanResult result = new BeanResult();
				Map<String, Object> mobileVersonInfo = appService
						.getLastMobileVersion();
				if (mobileVersonInfo != null && mobileVersonInfo.size() > 0) {

					mobileVersonInfo.put("apkPath", "/apk/glensOps.apk");
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("运维宝最新版本获取成功！");
					result.setData(mobileVersonInfo);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到运维宝最新版本");
				}

				return result;
			}
		});
	}

	/**
	 * 发送验证码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "sendValidateCode", method = RequestMethod.POST)
	public Object sendValidateCode(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// 验证码code
				String code = request.getParameter("code");
				String mobilePhone = request.getParameter("mobilePhone");
				String accountName = request.getParameter("accountName");
				WeixinResult result = new WeixinResult();

				result = weixinService.sendValidateCode(code, mobilePhone,
						accountName);
				System.out.println(result);
				return result;

			}
		});
	}

	@RequestMapping(value = "resetPsw", method = RequestMethod.GET)
	public Object resetPsw(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				TokenResult result = new TokenResult();
				OpsAppForm loginForm = (OpsAppForm) actionForm;

				String mobilePhone = request.getParameter("mobilePhone");
				String accountName = request.getParameter("accountName");

				PasswordEncoder pswEncoder = (PasswordEncoder) EAPContext
						.getContext().getBean("passwordEncoder");
				String encodePsw = pswEncoder.encode(PASSWORD);

				int iCount = loginService.updateUserPswByMobile(mobilePhone,
						accountName, encodePsw);

				if (iCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("密码修改成功!");

				} else {
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("密码修改失败!");
				}

				return result;
			}
		});
	}

	public void setWeixinService(WeixinService weixinService) {
		this.weixinService = weixinService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
