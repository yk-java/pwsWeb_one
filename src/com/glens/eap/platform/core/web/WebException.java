package com.glens.eap.platform.core.web;

import com.glens.eap.platform.core.exception.EAPException;

public class WebException extends EAPException {

	/**
	 * <p>Discription:[字段功能描述]</p>
	 */
	private static final long serialVersionUID = 1L;

	public WebException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	public WebException(String msg, Throwable cause) {
		
		super(msg, cause);
	}

}
