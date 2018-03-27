package com.glens.eap.platform.framework.auth.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.glens.eap.eapAuth.client.handler.AppClientLoginHandler;
import com.glens.eap.eapAuth.client.web.AuthUrlInterceptFilter;
import com.glens.eap.eapAuth.core.authentication.EncryCredentialManager;
import com.glens.eap.eapAuth.core.authentication.impl.EncryCredential;
import com.glens.eap.eapAuth.core.key.EAPSoKey;
import com.glens.eap.eapAuth.core.key.KeyService;
import com.glens.eap.eapAuth.core.model.EncryCredentialInfo;
import com.glens.eap.eapAuth.web.WebConstants;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;

public class RestUrlInterceptFilter extends AuthUrlInterceptFilter {

	private static final String EMPTY_CREDENTIAL = "凭据为空,请先登录!";

	private static final String UN_SUPPORTED_CREDENTIAL = "不支持的用户凭据";

	/**
	 * 本应用在eapso服务器上的应用ID值。
	 */
	protected String eapsoClientAppId = "1001";

	/**
	 * 登录本应用处理器类，由此类进行构造一个对象。
	 */
	protected String appClientLoginHandlerClass = "com.glens.eap.eapAuth.client.handler.impl.DefaultAppClientLoginHandler";

	/**
	 * 本应用对应的加密key.
	 */
	protected EAPSoKey eapsoKey;

	/**
	 * 秘钥获取服务。
	 */
	protected KeyService keyService = null;

	/**
	 * 凭据管理器。
	 */
	protected EncryCredentialManager encryCredentialManager;

	/**
	 * 登录本应用的处理器。
	 */
	protected AppClientLoginHandler appClientLoginHandler;

	@Override
	protected void doInit(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		super.doInit(filterConfig);

		eapsoClientAppId = getInitParameterWithDefalutValue(filterConfig,
				"eapsoClientAppId", eapsoClientAppId);
		appClientLoginHandlerClass = getInitParameterWithDefalutValue(
				filterConfig, "appClientLoginHandlerClass",
				appClientLoginHandlerClass);

		// 构造登录本应用的处理器对象。
		if (!StringUtils.isEmpty(appClientLoginHandlerClass)) {

			try {
				this.appClientLoginHandler = (AppClientLoginHandler) Class
						.forName(appClientLoginHandlerClass).newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		keyService = (KeyService) EAPContext.getContext().getBean("keyService");
		encryCredentialManager = (EncryCredentialManager) EAPContext
				.getContext().getBean("encryCredentialManager");
	}

	@Override
	protected void _doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpSession session = servletRequest.getSession();

		try {
			if (session.getAttribute(USER_STATE_IN_SESSION_KEY) == null) {

				// 查找参数中是否存在eapso_client_ec值，若没有则返回没有凭据的信息。
				String clientEc = this.getClientEC(servletRequest);
				if (StringUtils.isEmpty(clientEc)) {

					doResponse(request, response, EMPTY_CREDENTIAL);

					return;
				}

				// 如果没有key，则重试获取一次。
				if (eapsoKey == null) {
					try {
						eapsoKey = keyService.findKeyByAppId(eapsoClientAppId);

					} catch (Exception e) {

					}
				}

				// 解密凭据信息。
				EncryCredentialInfo encryCredentialInfo = this.encryCredentialManager
						.decrypt(new EncryCredential(clientEc));
				if (encryCredentialInfo != null) {

					// 检查凭据合法性。
					boolean valid = this.encryCredentialManager
							.checkEncryCredentialInfo(encryCredentialInfo);
					if (valid) {

						// 凭据合法
						session.setAttribute(USER_STATE_IN_SESSION_KEY,
								encryCredentialInfo);
						// 触发登录本应用的处理。
						if (appClientLoginHandler != null) {
							// 登录本应用。
							appClientLoginHandler.loginClient(
									encryCredentialInfo, servletRequest,
									servletResponse);
						}

						// 重新定位到原请求，去除EC参数。
						String url = servletRequest.getRequestURL().toString();

						// 登录成功后，写入EC到cookie中。
						writeEC(clientEc, servletResponse);

						// 重新定位请求，避免尾部出现长参数。
						chain.doFilter(request, response);
						return;
					}
				}

				// 否则凭据信息不合法
				doResponse(request, response, UN_SUPPORTED_CREDENTIAL);
				
				return;
			}

			// 若已经登录过，则直接返回，继续其它过滤器。
			// 触发登录本应用的处理。
			if (appClientLoginHandler != null) {
				// 登录本应用。
				EncryCredentialInfo encryCredentialInfo = (EncryCredentialInfo) session.getAttribute(USER_STATE_IN_SESSION_KEY);
				appClientLoginHandler.loginClient(
						encryCredentialInfo, servletRequest,
						servletResponse);
			}
			chain.doFilter(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			removeCookeEC(servletRequest, servletResponse);

			// 否则凭据信息不合法。
			doResponse(request, response, UN_SUPPORTED_CREDENTIAL);
			return;
		}
	}

	private void doResponse(ServletRequest request, ServletResponse response,
			String msg) {

		HttpServletResponse servletResponse = (HttpServletResponse) response;
		ResponseResult result = new ResponseResult(ResponseConstants.UNAUTHORIZED, msg);

		servletResponse.setContentType("application/json;charset=UTF-8");
		servletResponse.setHeader("Cache-Control", "no-cache");
		OutputStream out = null;
		String resposeContent = JSON.toJSONString(result);
		try {
			out = response.getOutputStream();
			out.write(resposeContent.getBytes("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (out != null) {

				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	
	private String removeEc(String url) {
		
		String newUrl = url;
		
		if (newUrl
				.contains(WebConstants.EAPSO_CLIENT_ENCRYPTED_CREDENTIAL_COOKIE_KEY)) {
			newUrl = newUrl.substring(0,
							newUrl.indexOf(WebConstants.EAPSO_CLIENT_ENCRYPTED_CREDENTIAL_COOKIE_KEY));
			// 去除末尾的问号。
			if (newUrl.endsWith("?")) {
				newUrl = newUrl.substring(0, newUrl.length() - 1);
			}

			// 去除末尾的&符号。
			if (newUrl.endsWith("&")) {
				newUrl = newUrl.substring(0, newUrl.length() - 1);
			}
		}
		
		return newUrl;
	}

}
