package com.glens.eap.platform.core.view;

import org.springframework.web.servlet.View;

public abstract class EAPView implements View {

	public static final String JSON_VIEW = "json";
	public static final String JSP_VIEW = "jsp";
	public static final String XML_VIEW = "xml";
	
	protected Object data;
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
