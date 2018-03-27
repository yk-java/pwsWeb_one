package com.glens.eap.eapAuth.core.authentication.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.glens.eap.eapAuth.core.app.App;
import com.glens.eap.eapAuth.core.app.AppService;
import com.glens.eap.eapAuth.core.authentication.Authentication;
import com.glens.eap.eapAuth.core.authentication.AuthenticationPostHandler;
import com.glens.eap.eapAuth.core.authentication.Credential;
import com.glens.eap.eapAuth.core.authentication.EncryCredentialManager;
import com.glens.eap.eapAuth.core.authentication.Principal;
import com.glens.eap.eapAuth.core.authentication.status.UserLoggedStatus;
import com.glens.eap.eapAuth.core.authentication.status.UserLoggedStatusStore;
import com.glens.eap.eapAuth.core.exception.NoEapsoKeyException;
import com.glens.eap.eapAuth.core.key.EAPSoKey;
import com.glens.eap.eapAuth.core.key.KeyService;
import com.glens.eap.eapAuth.core.model.EncryCredentialInfo;
import com.glens.eap.eapAuth.web.WebConstants;

/** 
 * <p>默认的认证后处理器实现类，提供抽象方法由具体子类实现。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public class DefaultAuthenticationPostHandler implements
		AuthenticationPostHandler {

	private static Logger logger = Logger.getLogger(DefaultAuthenticationPostHandler.class.getName());
	
	/**
	 * 密钥持续过期时间，10分钟。
	 */
	private static final long DURATION = 10 * 60 * 1000;
	
	private EncryCredentialManager encryCredentialManager;
	
	private KeyService keyService;
	
	private AppService appService;
	
	private UserLoggedStatusStore userLoggedStatusStore;
	
	public final EncryCredentialManager getEncryCredentialManager() {
		return encryCredentialManager;
	}

	public final void setEncryCredentialManager(
			EncryCredentialManager encryCredentialManager) {
		this.encryCredentialManager = encryCredentialManager;
	}

	public final KeyService getKeyService() {
		return keyService;
	}

	public final void setKeyService(KeyService keyService) {
		this.keyService = keyService;
	}

	public final AppService getAppService() {
		return appService;
	}

	public final void setAppService(AppService appService) {
		this.appService = appService;
	}

	public final UserLoggedStatusStore getUserLoggedStatusStore() {
		return userLoggedStatusStore;
	}

	public final void setUserLoggedStatusStore(
			UserLoggedStatusStore userLoggedStatusStore) {
		this.userLoggedStatusStore = userLoggedStatusStore;
	}

	@Override
	public Authentication postAuthentication(Credential credential,
			Principal principal) {
		// TODO Auto-generated method stub
		Date createTime = new Date();
		//若认证通过，则返回认证结果对象。
		AuthenticationImpl authentication = new AuthenticationImpl();
		authentication.setAuthenticatedDate(createTime);
		authentication.setPrincipal(principal);
		encryCredentialWithEapsoKey(authentication, credential, principal);
		encryCredentialWithAppKey(authentication, credential, principal);
		return authentication;
	}
	
	/*
	 * 使用eapso服务器本身的key对凭据信息进行加密处理。
	 */
	private void encryCredentialWithEapsoKey(AuthenticationImpl authentication, Credential credential, Principal principal){
		//如果是原始凭据，则需要进行加密处理。
		if(credential!=null && credential.isOriginal()){
			//查找eapso服务对应的应用信息。
			App eapsoApp = appService.findEapsoServerApp();
			if(eapsoApp==null){
				logger.log(Level.SEVERE, "no eapso key info.");
				throw NoEapsoKeyException.INSTANCE; 
			}
			String encryCredential = encryCredentialManager.encrypt(buildEncryCredentialInfo(eapsoApp.getAppId(), authentication, principal));
			//加密后的凭据信息写入到动态属性中。
			Map<String, Object> attributes = authentication.getAttributes();
			if(attributes==null){
				attributes = new HashMap<String, Object>();
			}
			attributes.put(EAPSO_SERVER_EC_KEY, encryCredential);
			authentication.setAttributes(attributes);
			authentication.setTicket(encryCredential);
		}
	}
	
	/*
	 * 使用eapso服务器本身的key对凭据信息进行加密处理。
	 */
	private void encryCredentialWithAppKey(AuthenticationImpl authentication, Credential credential, Principal principal){
		//获得登录的应用信息。
		AbstractParameter abstractParameter = null;
		if(credential!=null && credential instanceof AbstractParameter){
			abstractParameter = (AbstractParameter)credential;
		}
		//若登录对应的服务参数service的值不为空，则使用该service对应的应用的key进行加密。
		if(authentication!=null && abstractParameter!=null && abstractParameter.getParameterValue(WebConstants.SERVICE_PARAM_NAME)!=null){
			String service = abstractParameter.getParameterValue(WebConstants.SERVICE_PARAM_NAME).toString().trim().toLowerCase();
			//service不为空，且符合Http协议URL格式，则继续加密。
			if(service.length()>0){
				//查找eapso服务对应的应用信息。
				App clientApp = appService.findAppById(service);
				if(clientApp!=null){
					String encryCredential = encryCredentialManager.encrypt(buildEncryCredentialInfo(clientApp.getAppId(), authentication, principal));
					//加密后的凭据信息写入到动态属性中。
					Map<String, Object> attributes = authentication.getAttributes();
					if(attributes==null){
						attributes = new HashMap<String, Object>();
					}
					attributes.put(EAPSO_CLIENT_EC_KEY, encryCredential);
					attributes.put(WebConstants.SERVICE_PARAM_NAME, service);
					authentication.setAttributes(attributes);
					authentication.setTicket(encryCredential);
					
					//更新用户登录状态到存储器中。
					UserLoggedStatus status = new UserLoggedStatus(principal.getId(), clientApp.getAppId(), authentication.getAuthenticatedDate());
					userLoggedStatusStore.addUserLoggedStatus(status);
				}
			}
		}
	}
	
	private EncryCredentialInfo buildEncryCredentialInfo(String appId, AuthenticationImpl authentication, Principal principal){
		EncryCredentialInfo encryCredentialInfo = new EncryCredentialInfo();
                if(authentication==null || principal==null){
                    return encryCredentialInfo;
                }
		EAPSoKey eapsoKey = keyService.findKeyByAppId(appId);
		if(eapsoKey==null){
			logger.log(Level.INFO, "no key for app id {0}", appId);
			throw new NoEapsoKeyException();
		}
		encryCredentialInfo.setAppId(appId);
		encryCredentialInfo.setCreateTime(authentication.getAuthenticatedDate());
		encryCredentialInfo.setUserId(principal.getId());
		encryCredentialInfo.setKeyId(eapsoKey.getKeyId());
		Date expiredTime = new Date((authentication.getAuthenticatedDate().getTime()+DURATION)); 
		encryCredentialInfo.setExpiredTime(expiredTime);
		return encryCredentialInfo;
	}

}
