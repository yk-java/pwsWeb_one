package com.glens.eap.eapAuth.web;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.eapAuth.core.authentication.Credential;
import com.glens.eap.eapAuth.core.authentication.Parameter;

import java.util.*;

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
			Map<String, Object> parameters = new HashMap<String, Object>();
			Map<String, String[]> parameterMap = request.getParameterMap();
			List<String> mlist = new ArrayList<String>();
			for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
				// String数组转List集合
				mlist.addAll(Arrays.asList(entry.getValue()));
				parameters.put(entry.getKey(),mlist);
			}
			parameter.setParameters(parameters);
		}
		
		return super.postResolveCredential(request, credential);
	}

}
