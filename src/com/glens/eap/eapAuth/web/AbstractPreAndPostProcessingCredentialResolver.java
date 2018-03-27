package com.glens.eap.eapAuth.web;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.eapAuth.core.authentication.Credential;

public abstract class AbstractPreAndPostProcessingCredentialResolver implements
		CredentialResolver {

	@Override
	public Credential resolveCredential(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//解析前处理。
		this.preResolveCredential(request);
		//进行解析。
		Credential credential = this.doResolveCredential(request);
		//解析后的处理。
		return this.postResolveCredential(request, credential);
	}
	
	/**
	 * 凭据解析之前的处理。
	 * @param request 请求参数对象。
	 * @param credential 解析后的凭据信息，要基于该凭据上增加属性值。
	 * @return 处理后的凭据解析器。
	 */
	protected void preResolveCredential(HttpServletRequest request){
		
	}
	
	/**
	 * 抽象方法，实现真正的凭据解析处理。
	 * @param request 请求参数对象。
	 * @return 解析后的凭据信息。
	 */
	protected abstract Credential doResolveCredential(HttpServletRequest request);
	
	/**
	 * 凭据解析后处理。
	 * @param request 请求参数对象。
	 * @param credential 解析后的凭据信息，要基于该凭据上增加属性值。
	 * @return 处理后的凭据解析器。
	 */
	protected Credential postResolveCredential(HttpServletRequest request, Credential credential){
		return credential;
	}

}
