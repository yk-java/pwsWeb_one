package com.glens.eap.eapAuth.web;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.eapAuth.core.authentication.Credential;
import com.glens.eap.eapAuth.core.authentication.impl.UsernamePasswordCredential;

/** 
 * <p>用户名和密码凭据解析器，从参数中解析出用户的用户名和密码信息。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public class UsernamePasswordCredentialResolver extends
		AbstractParameterCredentialResolver {

	/**
	 * 用户名的参数名。
	 */
	public static final String USERNAME_PARAM_NAME = "userName";
	
	/**
	 * 密码的参数名。
	 */
	public static final String PASSWORD_PARAM_NAME = "psw";
	
	@Override
	protected Credential doResolveCredential(HttpServletRequest request) {
		// TODO Auto-generated method stub
		if (request != null && request.getParameter(USERNAME_PARAM_NAME) != null && 
				request.getParameter(PASSWORD_PARAM_NAME) != null) {
			UsernamePasswordCredential credential = new UsernamePasswordCredential();
			credential.setUsername(request.getParameter(USERNAME_PARAM_NAME));
			credential.setPassword(request.getParameter(PASSWORD_PARAM_NAME));
			return credential;
		}
		
		return null;
	}

}
