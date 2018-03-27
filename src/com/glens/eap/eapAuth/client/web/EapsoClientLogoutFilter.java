package com.glens.eap.eapAuth.client.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.glens.eap.eapAuth.client.handler.AppClientLogoutHandler;

public class EapsoClientLogoutFilter extends AuthClientFilter {

	/**
	 * 登录本应用处理器类，由此类进行构造一个对象。
	 */
	protected String appClientLogoutHandlerClass = "com.yaao.eap.eapSo.client.handler.impl.DefaultAppClientLogoutHandler";

	/**
	 * 登录本应用的处理器。
	 */
	protected AppClientLogoutHandler appClientLogoutHandler;
	
	private static Logger logger = Logger.getLogger(EapsoClientLogoutFilter.class.getName());
	
	@Override
	protected void doInit(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		appClientLogoutHandlerClass = getInitParameterWithDefalutValue(filterConfig, "appClientLoginHandlerClass", appClientLogoutHandlerClass);
		//构造登录本应用的处理器对象。
		if(!StringUtils.isEmpty(appClientLogoutHandlerClass)){
			try{
				this.appClientLogoutHandler = (AppClientLogoutHandler)Class.forName(appClientLogoutHandlerClass).newInstance();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse servletResponse = (HttpServletResponse)response;
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		HttpSession session = servletRequest.getSession();
		try{
			//本地应用已经登录，则进行登出处理。
			if(session.getAttribute(EapsoClientFilter.USER_STATE_IN_SESSION_KEY)!=null){
				//清除session中的值。
				session.setAttribute(EapsoClientFilter.USER_STATE_IN_SESSION_KEY, null);
				//若本定应用处理器不为空。
				if(appClientLogoutHandler!=null){
					//登出本应用。
					appClientLogoutHandler.logoutClient(servletRequest, servletResponse);
				}
				//清除cookie值。
				removeCookeEC(servletRequest, servletResponse);
			}
			//响应登录结果。
			sendResponse(servletResponse);
		}
		catch (Exception e) {
			//响应登录结果。
			sendError(servletResponse);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	private void sendResponse(HttpServletResponse response){
		response.setContentType("text/javascript;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter outhtml;
		try {
			outhtml = response.getWriter();
			outhtml.print("{result:true}");
			outhtml.close();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "send response error", e);
		} 
	}
	
	private void sendError(HttpServletResponse response){
		try {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "send response error", e);
		} 
	}

}
