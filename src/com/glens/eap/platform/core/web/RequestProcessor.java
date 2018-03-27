package com.glens.eap.platform.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.glens.eap.platform.core.utils.RequestUtils;

public class RequestProcessor {

	private EAPController controller;

	public EAPController getController() {
		return controller;
	}

	public void setController(EAPController controller) {
		this.controller = controller;
	}

	public RequestProcessor(EAPController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
	}

	public Object process(String viewType, HttpServletRequest request,
			HttpServletResponse response, ProcessRequestHandler handler) {

		ControllerForm bean = this.processForm(viewType, request);
		Object data = handler.doWithRequest(request, response, bean);
		Object returnObj = handler.doWithFinish(request, response, 
				data, viewType, controller);
		if (bean != null) {
			
			bean.doPostRequest(request);
		}
		
		return returnObj;
	}

	private ControllerForm processForm(String viewType, HttpServletRequest request) {
		
		ControllerForm instance = RequestUtils.createControllerForm(viewType, request, controller);
		if (instance != null) {
			
			instance.setController(this.controller);
			
			if ("request".equals(instance.getScope())) {
	            request.setAttribute(instance.getClass().getName(), instance);
	        } else {
	            HttpSession session = request.getSession();

	            session.setAttribute(instance.getClass().getName(), instance);
	        }
			
			//RequestUtils.processPathVariable(methodName, request, instance);
			RequestUtils.processParameter(request, instance);
			RequestUtils.processMutilpart(request, instance);
			instance.setRequest(request);
		}
		
		return instance;
	}
	
}
