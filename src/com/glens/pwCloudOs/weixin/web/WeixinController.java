package com.glens.pwCloudOs.weixin.web;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.pwCloudOs.login.web.LoginController;
import com.glens.pwCloudOs.weixin.service.WeixinService;

@RequestMapping("pmsServices/weixin")
public class WeixinController extends EAPJsonAbstractController {

	private static Log logger = LogFactory.getLog(WeixinController.class);

	private static Map<String, String> resultMap = new ConcurrentHashMap<String, String>();

	/**
	 * 同步部门及人员至企业号
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "syncDepartToWeixin", method = RequestMethod.GET)
	public Object syncDepartAndUserToWeixin(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				WeixinService serivce = (WeixinService) getService();
				BeanResult result = new BeanResult();
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				if (token != null) {
					if (!token.getAccountName().equals(
							LoginController.USER_NAME)) {
						result.setStatusCode(ResponseConstants.SERVER_ERROR);
						result.setResultMsg("用户没有操作权限！");
						return result;
					}
				}

				// flag 全量同步1 其他增量同步
				String flag = request.getParameter("flag");

				// 任务CODE
				String taskCode = request.getParameter("taskCode");

				try {
					String returnResult = serivce.syncAllDepartAndUser(flag,
							taskCode, resultMap);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					result.setData(returnResult);
				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg(e.getMessage());
				}
				return result;
			}
		});
	}

	/**
	 * 同步信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getSyncInfo", method = RequestMethod.GET)
	public Object getSyncInfo(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				WeixinService serivce = (WeixinService) getService();

				// 任务CODE
				String taskCode = request.getParameter("taskCode");

				BeanResult result = new BeanResult();
				try {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					result.setData(resultMap.get(taskCode));
				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg(e.getMessage());
				}
				return result;
			}
		});
	}

}
