package com.glens.eap.platform.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EAPController {

	public Object list(HttpServletRequest request, HttpServletResponse response);
	
	public Object insert(HttpServletRequest request, HttpServletResponse response);
	
	public Object update(HttpServletRequest request, HttpServletResponse response);
	
	public Object get(HttpServletRequest request, HttpServletResponse response);
	
	public Object delete(HttpServletRequest request, HttpServletResponse response);
}
