package com.glens.eap.platform.core.exception;

public class EAPException extends RuntimeException {

	/**
	 * <p>Discription:[字段功能描述]</p>
	 */
	private static final long serialVersionUID = 1L;

	public EAPException(String msg) {
		
		super(msg);
	}
	
	public EAPException(String msg, Throwable cause) {
		
		super(msg, cause);
	}
}
