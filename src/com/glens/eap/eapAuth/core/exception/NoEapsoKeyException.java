package com.glens.eap.eapAuth.core.exception;

public class NoEapsoKeyException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 115935182258831210L;
	
	public static final NoEapsoKeyException INSTANCE = new NoEapsoKeyException();
	
	/**
	 * 异常代码值。
	 */
	public static final String CODE = "NO.EAPSO.KEY.CODE";
	
	/**
	 * 异常信息键值，要转换为具体的语言值。
	 */
	public static final String MSG_KEY = "NO.EAPSO.KEY.MSG";
	
	public NoEapsoKeyException(String code, String msgKey) {
		super(code, msgKey);
		// TODO Auto-generated constructor stub
	}
	
	public NoEapsoKeyException() {
		
		super(CODE, MSG_KEY);
	}
	
}
