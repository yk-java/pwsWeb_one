package com.glens.pwCloudOs.login.handler;

import com.glens.eap.eapAuth.core.authentication.handlers.AbstractUsernamePasswordAuthenticationHandler;
import com.glens.eap.eapAuth.core.authentication.impl.UsernamePasswordCredential;
import com.glens.eap.eapAuth.core.exception.AuthenticationException;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.pwCloudOs.login.service.LoginService;

public class DBUsernamePasswordAuthenticationHandler extends
		AbstractUsernamePasswordAuthenticationHandler {
	
	public DBUsernamePasswordAuthenticationHandler() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	protected boolean authenticateUsernamePasswordInternal(
			UsernamePasswordCredential credential)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
		LoginService service = (LoginService) EAPContext.getContext().getBean("loginService");
		
		String psw = service.getPswByAccountName(credential.getUsername());
		if (psw != null && !"".equals(psw)) {
			
			String encodePsw = this.getPasswordEncoder().encode(credential.getPassword());
			if (encodePsw.equals(psw)) {
				
				return true;
			}
		}
		
		return false;
	}

}
