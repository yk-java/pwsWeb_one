
package com.glens.eap.platform.framework.auth.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.glens.eap.eapAuth.client.handler.AppClientLoginHandler;
import com.glens.eap.eapAuth.core.model.EncryCredentialInfo;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPCommonService;

public class DBAppClientLoginHandler implements AppClientLoginHandler {

	private static final String MYBATIS_NAMESAPCE = "clientLoginHandler";
	
	@Override
	public void loginClient(EncryCredentialInfo encryCredentialInfo,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		EAPCommonService service = (EAPCommonService) EAPContext.getContext().getBean("commomService");
		UserToken token = (UserToken) service.selectOne(MYBATIS_NAMESAPCE 
				+ ".[getAccountByAccountName]", encryCredentialInfo.getUserId());
		if (token != null) {
			
			request.getSession().setAttribute(UserToken.KEY_TOKEN, token);
		}
	}

}
