package com.glens.eap.eapAuth.core.exception;


/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public class InvalidCredentialException extends AuthenticationException {
	
	public static final InvalidCredentialException INSTANCE = new InvalidCredentialException();
	
	/**
	 * 异常代码值。
	 */
	public static final String CODE = "INVALID.CREDENTIAL.CODE";
	
	/**
	 * 异常信息键值，要转换为具体的语言值。
	 */
	public static final String MSG_KEY = "INVALID.CREDENTIAL.MSG";
	
	public InvalidCredentialException(String code, String msgKey) {
		super(code, msgKey);
	}

	public InvalidCredentialException() {
		super(CODE, MSG_KEY);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3350784910105909683L;
	

}
