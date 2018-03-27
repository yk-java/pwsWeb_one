package com.glens.eap.eapAuth.core.authentication.handlers;

import com.glens.eap.eapAuth.core.authentication.Credential;
import com.glens.eap.eapAuth.core.exception.AuthenticationException;

/** 
 * <p>抽象的认证处理器实现，提供了具体实现类可以在认证之前和认证之后执行一些任务。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public abstract class AbstractPreAndPostProcessingAuthenticationHandler
		implements AuthenticationHandler {

	/**
	 * 在认证发生前执行一些任务。
	 * 
	 * @param credentials
	 *            the Credentials supplied
	 * @return true if authentication should continue, false otherwise.
	 */
	protected boolean preAuthenticate(final Credential credential) {
		return true;
	}

	/**
	 * 在认证发生之后执行一些任务。
	 * 
	 * @param credentials
	 *            the supplied credentials
	 * @param authenticated
	 *            the result of the authentication attempt.
	 * @return true if the handler should return true, false otherwise.
	 */
	protected boolean postAuthenticate(final Credential credential,
			final boolean authenticated) {
		return authenticated;
	}
	
	@Override
	public boolean authenticate(Credential credential)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		if (!preAuthenticate(credential)) {
			return false;
		}

		final boolean authenticated = doAuthentication(credential);

		return postAuthenticate(credential, authenticated);
	}
	
	/**
	 * 执行真正的认证方法。
	 * @param credential 用户凭据。
	 * @return 认证结果。
	 * @throws InvalidCredentialException
	 */
	protected abstract boolean doAuthentication(final Credential credential)
			throws AuthenticationException;

}
