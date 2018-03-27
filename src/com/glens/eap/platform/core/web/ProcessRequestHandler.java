package com.glens.eap.platform.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProcessRequestHandler {

	public Object doWithRequest(HttpServletRequest request,
			HttpServletResponse response, ControllerForm actionForm);
	
	public Object doWithFinish(HttpServletRequest request,
			HttpServletResponse response, Object data, 
			String methodName, EAPController controller);

	public Object doWithException(HttpServletRequest request,
			HttpServletResponse response, Exception e);
}
