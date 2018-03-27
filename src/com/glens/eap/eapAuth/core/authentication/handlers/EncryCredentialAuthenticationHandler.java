package com.glens.eap.eapAuth.core.authentication.handlers;

import com.glens.eap.eapAuth.core.authentication.Credential;
import com.glens.eap.eapAuth.core.authentication.EncryCredentialManager;
import com.glens.eap.eapAuth.core.authentication.impl.EncryCredential;
import com.glens.eap.eapAuth.core.exception.AuthenticationException;
import com.glens.eap.eapAuth.core.exception.InvalidEncryCredentialException;
import com.glens.eap.eapAuth.core.model.EncryCredentialInfo;

public class EncryCredentialAuthenticationHandler extends
		AbstractPreAndPostProcessingAuthenticationHandler {

	private EncryCredentialManager encryCredentialManager;
	
	public void setEncryCredentialManager(
			EncryCredentialManager encryCredentialManager) {
		this.encryCredentialManager = encryCredentialManager;
	}

	/** Default class to support if one is not supplied. */
	private static final Class<EncryCredential> DEFAULT_CLASS = EncryCredential.class;
	
	@Override
	protected boolean doAuthentication(Credential credential)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		//不支持的凭据直接返回false.
		if(!this.supports(credential)){
			return false;
		}
		if(credential!=null && credential instanceof EncryCredential){
			EncryCredential encryCredential = (EncryCredential)credential;
			try{
				//解密凭据信息。
				EncryCredentialInfo encryCredentialInfo = this.encryCredentialManager.decrypt(encryCredential);
				//设置凭据信息的关联性。
				if(encryCredentialInfo!=null){
					encryCredential.setEncryCredentialInfo(encryCredentialInfo);
					//检查加密凭据的合法性。
					return this.encryCredentialManager.checkEncryCredentialInfo(encryCredentialInfo);
				}
			}catch (InvalidEncryCredentialException e) {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean supports(Credential credential) {
		// TODO Auto-generated method stub
		return credential != null
		&& (DEFAULT_CLASS.equals(credential.getClass()) || (DEFAULT_CLASS
				.isAssignableFrom(credential.getClass())));
	}

}
