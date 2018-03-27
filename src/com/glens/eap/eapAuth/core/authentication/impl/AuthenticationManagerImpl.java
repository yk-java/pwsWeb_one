package com.glens.eap.eapAuth.core.authentication.impl;

import java.util.List;

import com.glens.eap.eapAuth.core.authentication.Authentication;
import com.glens.eap.eapAuth.core.authentication.AuthenticationManager;
import com.glens.eap.eapAuth.core.authentication.AuthenticationPostHandler;
import com.glens.eap.eapAuth.core.authentication.Credential;
import com.glens.eap.eapAuth.core.authentication.Principal;
import com.glens.eap.eapAuth.core.authentication.handlers.AuthenticationHandler;
import com.glens.eap.eapAuth.core.authentication.resolvers.CredentialToPrincipalResolver;
import com.glens.eap.eapAuth.core.exception.AuthenticationException;
import com.glens.eap.eapAuth.core.exception.EmptyCredentialException;
import com.glens.eap.eapAuth.core.exception.InvalidCredentialException;
import com.glens.eap.eapAuth.core.exception.NoAuthenticationPostHandlerException;
import com.glens.eap.eapAuth.core.exception.UnsupportedCredentialsException;

public class AuthenticationManagerImpl implements AuthenticationManager {

	/**
	 * 认证处理器集合，使用多个认证处理器对凭证逐一校验，只要有一个通过即可。
	 */
	private List<AuthenticationHandler> authenticationHandlers;
	
	/**
	 * 用户凭据转为用户主体的解析器对象。
	 */
	private List<CredentialToPrincipalResolver> credentialToPrincipalResolvers;
	
	/**
	 * 认证成功后处理对象。
	 */
	private AuthenticationPostHandler authenticationPostHandler;
	
	public final List<AuthenticationHandler> getAuthenticationHandlers() {
		return authenticationHandlers;
	}

	public final void setAuthenticationHandlers(
			List<AuthenticationHandler> authenticationHandlers) {
		this.authenticationHandlers = authenticationHandlers;
	}

	public final List<CredentialToPrincipalResolver> getCredentialToPrincipalResolvers() {
		return credentialToPrincipalResolvers;
	}

	public final void setCredentialToPrincipalResolvers(
			List<CredentialToPrincipalResolver> credentialToPrincipalResolvers) {
		this.credentialToPrincipalResolvers = credentialToPrincipalResolvers;
	}

	public final AuthenticationPostHandler getAuthenticationPostHandler() {
		return authenticationPostHandler;
	}

	public final void setAuthenticationPostHandler(
			AuthenticationPostHandler authenticationPostHandler) {
		this.authenticationPostHandler = authenticationPostHandler;
	}

	/**
	 * <p>对用户凭据进行认证，返回认证结果。.</p>
	 * @param credential
	 * @return
	 * @throws InvalidCredentialException
	 * @author:[创建者中文名字]
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	@Override
	public Authentication authenticate(Credential credential)
			throws InvalidCredentialException {
		// TODO Auto-generated method stub
		//是否找到支持的凭据认证处理器。
		boolean foundSupported = false;
		//是否认证通过。
        boolean authenticated = false;
		//若凭据为空，则跑出异常。
		if(credential==null){
			throw new EmptyCredentialException();
		}
		
		//初始化的认证异常信息。
		AuthenticationException unAuthSupportedHandlerException = InvalidCredentialException.INSTANCE; 
		
		//循环调用所有的认证处理器。
		if(authenticationHandlers!=null && authenticationHandlers.size()>0){
			for(AuthenticationHandler handler:authenticationHandlers){
				//认证处理器是否支持该凭据。
				if(handler.supports(credential)){
					foundSupported = true;
					try {
						authenticated = handler.authenticate(credential);
						//若认证成功，则跳出循环。
						if(authenticated){
							break;
						}
					} catch (AuthenticationException e) {
						unAuthSupportedHandlerException = e;
					}
					
				}
			}
		}
		//未找到支持的认证处理器。
		if(!foundSupported){
			throw new UnsupportedCredentialsException();
		}
		
		//若未认证通过，则抛出最后一个异常信息。
		if(!authenticated){
			throw unAuthSupportedHandlerException;
		}
		
		Principal principal = null;
		//初始化是否找到合适的凭据转换器。
		foundSupported = false;
		//循环调用所有的用户凭据解析器。
		if(credentialToPrincipalResolvers!=null && credentialToPrincipalResolvers.size()>0){
			for(CredentialToPrincipalResolver resolver:credentialToPrincipalResolvers){
				//用户凭据解析器是否支持该凭据。
				if(resolver.supports(credential)){
					foundSupported = true;
					principal = resolver.resolvePrincipal(credential);
					//若解析成功，则跳出循环。
					if(principal!=null){
						break;
					}
				}
			}
		}
		//未找到支持的用户凭据解析器。
		if(!foundSupported){
			throw new UnsupportedCredentialsException();
		}
		//若认证后处理器对象为空，则抛出异常。
		if(authenticationPostHandler==null){
			throw new NoAuthenticationPostHandlerException();
		}
		
		//交由认证后处理器进行处理。
		return authenticationPostHandler.postAuthentication(credential, principal);
	}

}
