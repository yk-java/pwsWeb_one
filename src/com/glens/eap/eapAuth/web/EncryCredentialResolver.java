package com.glens.eap.eapAuth.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.glens.eap.eapAuth.core.authentication.Credential;
import com.glens.eap.eapAuth.core.authentication.impl.EncryCredential;

/** 
 * <p>经过认证加密后的凭据信息解析器，从http请求的cookie中解析出对应的加密后的凭据信息。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public class EncryCredentialResolver implements CredentialResolver {

	@Override
	public Credential resolveCredential(HttpServletRequest request) {
		// TODO Auto-generated method stub
		if(request!=null){
			//查找请求中的cookie。
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				String value = null;
				for(Cookie cookie:cookies){
					//若查找到EAPSO写入的cookie值。
					if(cookie!=null && cookie.getName().equalsIgnoreCase(WebConstants.EAPSO_SERVER_ENCRYPTED_CREDENTIAL_COOKIE_KEY)){
						value = cookie.getValue();
						break;
					}
				}
				//如果cookie中没有凭据值，则从请求参数中获取凭据值。
				if(StringUtils.isEmpty(value)){
					value = request.getParameter(WebConstants.EAPSO_SERVER_ENCRYPTED_CREDENTIAL_COOKIE_KEY);
				}
				//最终如果加密凭据有值，则直接返回凭据对象。
				if(!StringUtils.isEmpty(value)){
					//去除空串后返回。
					return new EncryCredential(value.trim());
				}
			}
		}
		
		return null;
	}

}
