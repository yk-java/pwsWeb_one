package com.glens.eap.eapAuth.client.handler.impl;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.glens.eap.eapAuth.client.handler.AppClientLoginHandler;
import com.glens.eap.eapAuth.core.model.EncryCredentialInfo;

public class DefaultAppClientLoginHandler implements AppClientLoginHandler {

	private static Logger logger = Logger.getLogger(DefaultAppClientLoginHandler.class.getName());
	
	@Override
	public void loginClient(EncryCredentialInfo encryCredentialInfo,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.getSession().setAttribute(AppClientLoginHandler.USER_KEY, encryCredentialInfo);
		
		logger.info("the user id is "+encryCredentialInfo.getUserId() +" has logined in the app");
	}

}
