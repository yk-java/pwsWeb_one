package com.glens.eap.eapAuth.client.handler.impl;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.glens.eap.eapAuth.client.handler.AppClientLoginHandler;
import com.glens.eap.eapAuth.client.handler.AppClientLogoutHandler;
import com.glens.eap.eapAuth.core.model.EncryCredentialInfo;

public class DefaultAppClientLogoutHandler implements AppClientLogoutHandler {

	private static Logger logger = Logger.getLogger(DefaultAppClientLogoutHandler.class.getName());
	
	@Override
	public void logoutClient(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		//若已经登录，则作相关处理。
		if(request.getSession().getAttribute(AppClientLoginHandler.USER_KEY) != null){
			EncryCredentialInfo encryCredentialInfo = (EncryCredentialInfo)request.getSession().getAttribute(AppClientLoginHandler.USER_KEY);
			//remove the exception
			request.getSession().setAttribute(AppClientLoginHandler.USER_KEY, null);
			logger.info("the user id is "+encryCredentialInfo.getUserId() +" has logined out the app");
		}
	}

}
