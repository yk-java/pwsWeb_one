package com.glens.eap.eapAuth.core.authentication;


/** 
 * <p>认证成功后的处理器，该接口的职责是将
 * 用户认证主体，用户凭据转换为一个合适的
 * 认证结果对象。根据用户凭据中的信息，参数
 * 进行合适的转换。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public interface AuthenticationPostHandler {

	public static final String EAPSO_SERVER_EC_KEY = "eapso_ser_ec_key";
	
	public static final String EAPSO_CLIENT_EC_KEY = "eapso_cli_ec_key";
	
	/**
	 * 认证后的处理方法，将用户的凭据和主体转换为一个认证结果对象。
	 * @param credential 用户凭据。
	 * @param principal 用户主体。
	 * @return 认证结果对象信息。
	 */
	public Authentication postAuthentication(Credential credential, Principal principal);
	
}
