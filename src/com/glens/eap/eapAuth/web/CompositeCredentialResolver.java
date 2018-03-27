package com.glens.eap.eapAuth.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.WebUtils;

import com.glens.eap.eapAuth.core.authentication.Credential;
import com.glens.eap.eapAuth.core.authentication.impl.AbstractParameter;

public class CompositeCredentialResolver implements CredentialResolver {

	/**
	 * 加密后的凭据解析器。
	 */
	private CredentialResolver encryCredentialResolver;
	
	/**
	 * 原始用户名密码凭据解析器。
	 */
	private CredentialResolver usernamePasswordCredentialResolver;
	
	public CompositeCredentialResolver() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * <p>从http请求参数的cookie或者参数值中解析出凭据信息对象。
	 * 返回解析后的凭据对象。
	 * 先解析加密后的已认证凭据，若没有则再解析出原始的用户名秘密凭据，若任何凭据都没有则返回null..</p>
	 * @param request
	 * @return
	 * @author:[创建者中文名字]
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	@Override
	public Credential resolveCredential(HttpServletRequest request) {
		// TODO Auto-generated method stub
		if(request==null){
			return null;
		}
		
		Credential credential = null;
		if(encryCredentialResolver!=null){
			//先解析加密后的凭据。
			credential = encryCredentialResolver.resolveCredential(request);
		}
		//若返回空，则用原始凭据解析器解析。
		if(credential==null){
			if(usernamePasswordCredentialResolver!=null){
				credential = usernamePasswordCredentialResolver.resolveCredential(request);
			}
		}
		
		//如果是抽象的参数凭据对象，则解析其它的参数。
		if(credential instanceof AbstractParameter){
			AbstractParameter abstractParameter = (AbstractParameter)credential;
			//将所有的参数设置到参数列表中，方便以后处理使用。
			abstractParameter.setParameters(WebUtils.getParametersStartingWith(request, null));
			//如果参数列表中无service,则从session中获取。
			if(abstractParameter.getParameterValue(WebConstants.SERVICE_PARAM_NAME)==null){
				if(request.getSession().getAttribute(WebConstants.EAPSO_SERVICE_KEY_IN_SESSION)!=null){
					abstractParameter.getParameters().put(WebConstants.SERVICE_PARAM_NAME, 
							request.getSession().getAttribute(WebConstants.EAPSO_SERVICE_KEY_IN_SESSION));
				}
			}
		}
		return credential;
	}

	public final CredentialResolver getEncryCredentialResolver() {
		return encryCredentialResolver;
	}

	public final void setEncryCredentialResolver(
			CredentialResolver encryCredentialResolver) {
		this.encryCredentialResolver = encryCredentialResolver;
	}

	public final CredentialResolver getUsernamePasswordCredentialResolver() {
		return usernamePasswordCredentialResolver;
	}

	public final void setUsernamePasswordCredentialResolver(
			CredentialResolver usernamePasswordCredentialResolver) {
		this.usernamePasswordCredentialResolver = usernamePasswordCredentialResolver;
	}

}
