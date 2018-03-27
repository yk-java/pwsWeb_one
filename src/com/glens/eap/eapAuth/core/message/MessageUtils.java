package com.glens.eap.eapAuth.core.message;

import java.util.HashMap;
import java.util.Map;

import com.glens.eap.eapAuth.core.exception.EmptyCredentialException;
import com.glens.eap.eapAuth.core.exception.InvalidCredentialException;
import com.glens.eap.eapAuth.core.exception.InvalidEncryCredentialException;
import com.glens.eap.eapAuth.core.exception.NoAuthenticationPostHandlerException;
import com.glens.eap.eapAuth.core.exception.NoEapsoKeyException;
import com.glens.eap.eapAuth.core.exception.PasswordInvalidException;
import com.glens.eap.eapAuth.core.exception.UnsupportedCredentialsException;
import com.glens.eap.eapAuth.core.exception.UsernameOrPasswordEmptyException;
import com.glens.eap.eapAuth.core.exception.UsernameOrPasswordInvalidException;

public abstract class MessageUtils {

	/**
	 * 信息表，其中key是消息键，value是具体对应的中文的消息内容。
	 */
	private static Map<String, String> msgMap = null;
	
	static{
		//初始化消息表。
		msgMap = new HashMap<String, String>();
		msgMap.put(InvalidCredentialException.MSG_KEY, "不合法的凭据");
		msgMap.put(InvalidEncryCredentialException.MSG_KEY, "不合法的加密凭据");
		msgMap.put(UsernameOrPasswordEmptyException.MSG_KEY, "用户名或者密码为空");
		msgMap.put(UsernameOrPasswordInvalidException.MSG_KEY, "用户名或者密码不正确");
		msgMap.put(PasswordInvalidException.MSG_KEY, "密码错误");
		msgMap.put(EmptyCredentialException.MSG_KEY, "凭据为空");
		msgMap.put(UnsupportedCredentialsException.MSG_KEY, "不支持的用户凭据");
		msgMap.put(NoAuthenticationPostHandlerException.MSG_KEY, "无合法的认证后处理器");
		msgMap.put(NoEapsoKeyException.MSG_KEY, "系统没有配eapso服务器本身的key信息，请检查配置。");
	}
	
	
	/**
	 * 查询消息键对应的消息提示内容。
	 * @param key 消息键
	 * @return 消息内容。
	 */
	public static String getMessage(String key){
		return msgMap.get(key);
	}
	
}
