package com.glens.eap.eapAuth.core.exception;


/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public abstract class AuthenticationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1783421378934140252L;
	
	/**
	 * 异常代号。
	 */
	private String code;
	
	/**
	 * 异常详细信息键值，根据该键值查找某种语言下的具体值。
	 */
	private String msgKey;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

	public AuthenticationException(String code, String msgKey) {
		super();
		this.code = code;
		this.msgKey = msgKey;
	}
	
}
