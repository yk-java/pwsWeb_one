/**
 * 
 */
package com.glens.eap.eapAuth.client.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * <p>本应用登出处理接口。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */
public interface AppClientLogoutHandler {

	/**
	 * 登出本应用。
	 * @param request http请求对象。
	 * @param response http响应对象。
	 */
	public void logoutClient(HttpServletRequest request, HttpServletResponse response);
	
}
