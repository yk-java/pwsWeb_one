package com.glens.eap.eapAuth.web;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.eapAuth.core.authentication.Credential;

/** 
 * <p>凭据解析器，从http请求的cookie，参数等值中解析出各种类型的用户凭证，该接口由具体实现类负责具体凭据解析。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public interface CredentialResolver {

	/**
	 * 从http请求参数的cookie或者参数值中解析出凭据信息对象，返回解析后的凭据对象。
	 * @param request http servlet请求对象，不能空。
	 * @return 若没有合法的凭据，请返回空。
	 */
	public Credential resolveCredential(HttpServletRequest request);
	
}
