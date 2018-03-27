package com.glens.eap.eapAuth.client.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.glens.eap.eapAuth.client.handler.AppClientLoginHandler;
import com.glens.eap.eapAuth.client.key.DefaultKeyServiceImpl;
import com.glens.eap.eapAuth.core.authentication.EncryCredentialManager;
import com.glens.eap.eapAuth.core.authentication.impl.EncryCredential;
import com.glens.eap.eapAuth.core.authentication.impl.EncryCredentialManagerImpl;
import com.glens.eap.eapAuth.core.key.EAPSoKey;
import com.glens.eap.eapAuth.core.key.KeyService;
import com.glens.eap.eapAuth.core.model.EncryCredentialInfo;
import com.glens.eap.eapAuth.web.WebConstants;

public class EapsoClientFilter extends AuthClientFilter {

	private static Logger logger = Logger.getLogger(EapsoClientFilter.class
			.getName());

	/**
	 * eapso服务器登录URL地址。
	 */
	protected String eapsoServerLoginUrl = eapsoServerHost + "login.do";

	/**
	 * eapso服务器获取应用秘钥信息的URL地址。
	 */
	protected String eapsoServerFetchKeyUrl = eapsoServerHost + "fetchKey.do";

	/**
	 * 本应用在eapso服务器上的应用ID值。
	 */
	protected String eapsoClientAppId = "1001";

	/**
	 * 登录本应用处理器类，由此类进行构造一个对象。
	 */
	protected String appClientLoginHandlerClass = "com.yaao.eap.eapSo.client.handler.impl.DefaultAppClientLoginHandler";

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
	protected EncryCredentialManagerImpl encryCredentialManager;

	/**
	 * 登录本应用的处理器。
	 */
	protected AppClientLoginHandler appClientLoginHandler;

	@Override
	protected void doInit(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		eapsoClientAppId = getInitParameterWithDefalutValue(filterConfig,
				"eapsoClientAppId", eapsoClientAppId);
		eapsoServerLoginUrl = getInitParameterWithDefalutValue(filterConfig,
				"eapsoServerLoginUrl", eapsoServerLoginUrl);
		eapsoServerFetchKeyUrl = getInitParameterWithDefalutValue(filterConfig,
				"eapsoServerFetchKeyUrl", eapsoServerFetchKeyUrl);
		appClientLoginHandlerClass = getInitParameterWithDefalutValue(
				filterConfig, "appClientLoginHandlerClass",
				appClientLoginHandlerClass);

		// 构造key服务等相关对象。
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

		keyService = new DefaultKeyServiceImpl(eapsoServerFetchKeyUrl,
				eapsoClientAppId);
		this.encryCredentialManager = new EncryCredentialManagerImpl();
		this.encryCredentialManager.setKeyService(keyService);
		logger.info("the ki4so sever is :" + this.eapsoServerHost
				+ ", please check this service is ok.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpSession session = servletRequest.getSession();

		try {
			if (session.getAttribute(USER_STATE_IN_SESSION_KEY) == null) {
				
				//查找参数中是否存在eapso_client_ec值，若没有则重定向到登录页面。
				String clientEc = this.getClientEC(servletRequest);
				
				if(StringUtils.isEmpty(clientEc)){
					
					servletResponse.sendRedirect(buildRedirectToEapsoServer(servletRequest));
					
					return;
				}
				
				//如果没有key，则重试获取一次。
				if (eapsoKey == null){
					try {
						eapsoKey = keyService.findKeyByAppId(eapsoClientAppId);
						
					} catch (Exception e) {
						
						logger.log(Level.SEVERE, "fetch eapso key info error", e);
					}
				}
				
				//解密凭据信息。
				EncryCredentialInfo encryCredentialInfo = this.encryCredentialManager.decrypt(new EncryCredential(clientEc));
				if (encryCredentialInfo != null) {
					
					//检查凭据合法性。
					boolean valid = this.encryCredentialManager.checkEncryCredentialInfo(encryCredentialInfo);
					if (valid) {
						//如果合法
						//设置登录状态到session中。
						session.setAttribute(USER_STATE_IN_SESSION_KEY, encryCredentialInfo);
						//触发登录本应用的处理。
						if(appClientLoginHandler!=null){
							//登录本应用。
							appClientLoginHandler.loginClient(encryCredentialInfo, servletRequest, servletResponse);
						}
						
						//重新定位到原请求，去除EC参数。
						String url = servletRequest.getRequestURL().toString();
						if(!StringUtils.isEmpty(url)){
							//如果请求中存在EC参数，则去除这个参数，重定位。
							if(url.contains(WebConstants.EAPSO_CLIENT_ENCRYPTED_CREDENTIAL_COOKIE_KEY)){
								url = url.substring(0, url.indexOf(WebConstants.EAPSO_CLIENT_ENCRYPTED_CREDENTIAL_COOKIE_KEY));
								//去除末尾的问号。
								if(url.endsWith("?")){
									url = url.substring(0, url.length()-1);
								}
								
								//去除末尾的&符号。
								if(url.endsWith("&")){
									url = url.substring(0, url.length()-1);
								}
							}
						}
						
						//登录成功后，写入EC到cookie中。
						writeEC(clientEc, servletResponse);
						
						//重新定位请求，避免尾部出现长参数。
						servletResponse.sendRedirect(url);
						return;
					}
				}
				
				//否则凭据信息不合法，跳转到Eapso登录页面。
				servletResponse.sendRedirect(buildRedirectToEapsoServer(servletRequest));
				return;
			}
			
			//若已经登录过，则直接返回，继续其它过滤器。
			chain.doFilter(request, response);
			return;
		}
		catch (Exception e) {
			// TODO: handle exception
			removeCookeEC(servletRequest, servletResponse);
			
			//否则凭据信息不合法，跳转到Eapso登录页面。
			servletResponse.sendRedirect(buildRedirectToEapsoServer(servletRequest));
			return;
		}

	}

	protected String buildRedirectToEapsoServer(
			HttpServletRequest servletRequest) {
		StringBuffer sb = new StringBuffer(this.eapsoServerLoginUrl);
		if (this.eapsoServerLoginUrl.contains("?")) {
			sb.append("&");
		} else {
			sb.append("?");
		}
		sb.append("service=").append(servletRequest.getRequestURL().toString());
		return sb.toString();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.eapsoKey = null;
	}

}
