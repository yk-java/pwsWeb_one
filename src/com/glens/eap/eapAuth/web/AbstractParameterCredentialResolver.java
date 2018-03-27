package com.glens.eap.eapAuth.web;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.eapAuth.core.authentication.Credential;
import com.glens.eap.eapAuth.core.authentication.Parameter;

/** 
 * <p>该类提供了参数化的凭据类型的解析后处理方法，将请求中的所有参数全部
 * 转到参数列表中，供相关的处理。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public abstract class AbstractParameterCredentialResolver extends
		AbstractPreAndPostProcessingCredentialResolver {

	@Override
	protected Credential postResolveCredential(HttpServletRequest request,
			Credential credential) {
		if (credential == null){
			return null;
		}
		if (credential instanceof Parameter){
			Parameter parameter = (Parameter)credential;
			parameter.setParameters(request.getParameterMap());
		}
		
		return super.postResolveCredential(request, credential);
	}

}
