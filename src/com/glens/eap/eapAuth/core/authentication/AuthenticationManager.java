package com.glens.eap.eapAuth.core.authentication;

import com.glens.eap.eapAuth.core.exception.InvalidCredentialException;

/** 
 * <p>认证管理器，负责对用户凭证进行有效性认证。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public interface AuthenticationManager {

	/**
	 * 对用户凭据进行认证，若认证失败则抛出异常，若成功返回认证结果。
	 * @param credential 用户凭据。
	 * @return 当认证通过后，返回认证结果。
	 * @throws InvalidCredentialException 当输入的凭据不合法的时候会抛出该异常。
	 */
	public Authentication authenticate(Credential credential) throws InvalidCredentialException;
}
