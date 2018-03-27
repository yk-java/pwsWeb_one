package com.glens.eap.platform.framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.glens.eap.platform.core.beans.InitializingBean;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.core.web.ProcessRequestHandler;
import com.glens.eap.platform.core.web.RequestProcessor;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.service.EAPService;

public abstract class EAPAbstractController implements EAPController, InitializingBean {

	protected EAPService service;
	
	protected RequestProcessor processor;
	
	public EAPService getService() {
		return service;
	}

	public void setService(EAPService service) {
		this.service = service;
	}

	public EAPAbstractController() {
		// TODO Auto-generated constructor stub
		this.initialize();
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		processor = new RequestProcessor(this);
	}
	
	public UserToken getToken(HttpServletRequest request) {
		
		HttpSession session = null;
        if(request != null)
        {
            session = request.getSession();
        }
        
        return getToken(session);
	}
	
	public UserToken getToken(HttpSession session) {
		
		UserToken token = null;
		
		 if(session != null) {
			 token = (UserToken) session.getAttribute(UserToken.KEY_TOKEN);
		 }
		
		return token;
	}
	
	protected Object process(String viewType, HttpServletRequest request,
			HttpServletResponse response, ProcessRequestHandler handler) {
		
		Object result = null;
		
		try {
			
			result = processor.process(viewType, request, response, handler);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = handler.doWithException(request, response, e);
		}
		
		return result;
	}
	
	protected String getRootPath(HttpServletRequest request) {
		
		String rootPath = request.getSession().getServletContext()
		.getRealPath("/");
		
		return rootPath;
	}

}
