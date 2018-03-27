package com.glens.eap.eapAuth.core.service;

import java.util.List;

import com.glens.eap.eapAuth.core.app.App;
import com.glens.eap.eapAuth.core.authentication.Credential;

/** 
 * <p>核心服务接口，定义了所有的核心方法。
 * 该接口是一个门面类，定义了ki4so核心的对外
 * 提供的方法。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public interface EapsoService {

	/**
	 * 使用一个用户凭据登录ki4so中心认证服务。
	 * @param credential 用户凭据。
	 * @return 登录结果。
	 */
	public LoginResult login(Credential credential);
	
	/**
	 * 为某个用户凭据实现登出操作。
	 * @param credential 用户凭据。
	 */
	public void logout(Credential credential);
	
	/**
	 * 获得某个用户凭据对应的登录的应用列表。
	 * @param credential 用户凭据。
	 * @return 该用户登录的应用列表。
	 */
	public List<App> getAppList(Credential credential);
	
}
