package com.glens.eap.platform.core.orm.mybatis.exception;

import com.glens.eap.platform.core.exception.EAPException;

public class MyBatisAccessException extends EAPException {

	/**
	 * <p>Discription:[字段功能描述]</p>
	 */
	private static final long serialVersionUID = 1L;

	public MyBatisAccessException(String msg) {
		
		super(msg);
	}
	
	public MyBatisAccessException(String msg, Throwable cause) {
		
		super(msg, cause);
	}
}
