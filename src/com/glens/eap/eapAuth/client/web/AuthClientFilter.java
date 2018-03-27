package com.glens.eap.eapAuth.client.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.glens.eap.eapAuth.web.WebConstants;
import com.glens.eap.platform.framework.filter.EAPFilter;

public abstract class AuthClientFilter extends EAPFilter {

	/**
	 * 在客户端的session中的用户信息，避免频繁认证，提高性能。
	 */
	public static final String USER_STATE_IN_SESSION_KEY = "eapso_client_user_info_session_key";
	
	/**
	 * <p>so服务器地址</p>
	 */
	protected String eapsoServerHost;

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		//初始化参数。
		eapsoServerHost = getInitParameterWithDefalutValue(filterConfig, "eapServerHost", eapsoServerHost);
		
		//调用子类的初始化方法。
		doInit(filterConfig);
	}
	
	/**
	 * 由子类实现的初始化方法。
	 * @param filterConfig
	 * @throws ServletException
	 */
	protected abstract void doInit(FilterConfig filterConfig) throws ServletException;
	
	/**
	 * 删除cookie中的异常信息。
	 * @param ec EC值。
	 * @param response Http响应对象。
	 */
	protected void removeCookeEC(HttpServletRequest request, HttpServletResponse response){
		Cookie cookie = getCookie(request, WebConstants.EAPSO_CLIENT_ENCRYPTED_CREDENTIAL_COOKIE_KEY);
		if(cookie!=null){
			//设置过期时间为立即。
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
	
	/**
	 * Retrieve the first cookie with the given name. Note that multiple
	 * cookies can have the same name but different paths or domains.
	 * @param request current servlet request
	 * @param name cookie name
	 * @return the first cookie with the given name, or {@code null} if none is found
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}
	
	/**
	 * 从客户端参数或者cookie中获取EC值。
	 * 
	 * @param request
	 *            http请求对象。
	 * @return EC值。
	 */
	protected String getClientEC(HttpServletRequest request) {
		String ec = null;
		if (request != null) {
			ec = request.getParameter(WebConstants.EAPSO_CLIENT_ENCRYPTED_CREDENTIAL_COOKIE_KEY);
			// 再从cookie中获取值。
			if (StringUtils.isEmpty(ec)) {
				Cookie cookie = getCookie(
						request,
						WebConstants.EAPSO_CLIENT_ENCRYPTED_CREDENTIAL_COOKIE_KEY);
				if (cookie != null) {
					ec = cookie.getValue().trim();
				}
			}
		}
		return ec;
	}

	/**
	 * 将EC的值写入到服务器的cookie中。
	 * 
	 * @param ec
	 *            EC值。
	 * @param response
	 *            Http响应对象。
	 */
	protected void writeEC(String ec, HttpServletResponse response) {
		// 使用URL进行编码，避免写入cookie错误。
		try {
			ec = URLEncoder.encode(ec, "UTF-8");
			response.addCookie(new Cookie(
					WebConstants.EAPSO_CLIENT_ENCRYPTED_CREDENTIAL_COOKIE_KEY,
					ec));
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}

	}

}
