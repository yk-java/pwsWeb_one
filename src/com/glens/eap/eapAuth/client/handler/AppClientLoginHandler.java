package com.glens.eap.eapAuth.client.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.glens.eap.eapAuth.core.model.EncryCredentialInfo;

/**
 * <p>
 * 本应用登录处理器接口，请实现该处理器，实现本接口，将本应用的登录逻辑写在这里。
 * </p>
 * 
 * @author <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$
 */
public interface AppClientLoginHandler {

	public String USER_KEY = "USER_KEY_SESSON";
	
	/**
	 * 登录本应用。
	 * 
	 * @param encryCredentialInfo
	 *            用户凭据信息，表示当前登录的用户。
	 * @param request
	 *            http请求对象。
	 * @param response
	 *            http响应对象。
	 */
	public void loginClient(EncryCredentialInfo encryCredentialInfo,
			HttpServletRequest request, HttpServletResponse response);

}
