package com.glens.eap.eapAuth.client.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public abstract class AuthUrlInterceptFilter extends AuthClientFilter {

	private String allowUrl;
	
	@Override
	protected void doInit(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.allowUrl = getInitParameterWithDefalutValue(filterConfig, "allowUrl", "");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.allowUrl = null;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String pathInfo="";
		if(req.getPathInfo()!=null){
			pathInfo=req.getPathInfo();
		}
		String reqUrl = req.getServletPath() + pathInfo;
//		System.out.println(reqUrl);
//		System.out.println(request.getParameter("userName") + "," + request.getParameter("ticket"));
		
		if (!isAllowUrl(reqUrl)) {
			
			_doFilter(request, response, chain);
		}
		else {
			
			chain.doFilter(request, response);
		}
	}
	
	protected abstract void _doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException;
	
	private boolean isAllowUrl(String reqUrl) {

		boolean isAllowUrl = true;

		try {
			JSONObject jsonUrl = JSON.parseObject(this.allowUrl);
			JSONArray jsonAllowUrls = (JSONArray) jsonUrl.get("allow");
			JSONArray jsonFilterUrls = (JSONArray) jsonUrl.get("filter");
			if (jsonAllowUrls != null && jsonAllowUrls.size() > 0) {

				boolean flag = false;

				for (int i = 0; i < jsonAllowUrls.size(); i++) {
					if (isMatchUrl(reqUrl, jsonAllowUrls.get(i).toString())) {
						return true;
					}
				}

				if (jsonFilterUrls != null && jsonFilterUrls.size() > 0) {

					for (int i = 0; i < jsonFilterUrls.size(); i++) {

						if (isMatchUrl(reqUrl, jsonFilterUrls.get(i).toString())) {
							flag = true;

							break;
						}
					}

					return !flag;
				}

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isAllowUrl;
	}

	private boolean isMatchUrl(String reqUrl, String matchUrl) {

		// "/method.do"
		if (matchUrl.charAt(0) == '/' && matchUrl.indexOf("*") == -1
				&& reqUrl.equals(matchUrl)) {
			return true;
		}
		// "/*"
		if (matchUrl.charAt(0) == '/'
				&& matchUrl.indexOf("*") != -1
				&& reqUrl.indexOf(matchUrl.substring(0, matchUrl.indexOf("*"))) != -1) {
			return true;
		}
		// "*.do"
		if (matchUrl.charAt(0) == '*'
				&& reqUrl
						.indexOf(matchUrl.substring(matchUrl.indexOf("*") + 1)) != -1) {
			return true;
		}
		
		// "/platform/*"
		if (matchUrl.charAt(0) == '/' && matchUrl.indexOf("*") != -1 
				&& reqUrl.indexOf(matchUrl.substring(0, matchUrl.indexOf("*"))) != -1) {
			
			return true;
		}
		return false;
	}

}
