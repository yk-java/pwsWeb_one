package com.glens.eap.eapAuth.core.authentication.impl;

import com.glens.eap.eapAuth.core.authentication.Credential;
import com.glens.eap.eapAuth.core.model.EncryCredentialInfo;

public class EncryCredential extends AbstractParameter implements Credential {

	/**
	 * 加密后的用户凭据串。
	 */
	private String credential;
	
	/**
	 * 加密凭据对应的加密凭据信息对象。
	 */
	private EncryCredentialInfo encryCredentialInfo;

	public EncryCredentialInfo getEncryCredentialInfo() {
		return encryCredentialInfo;
	}

	public void setEncryCredentialInfo(EncryCredentialInfo encryCredentialInfo) {
		this.encryCredentialInfo = encryCredentialInfo;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public EncryCredential(String credential) {
		super();
		this.credential = credential;
	}
	
	@Override
	public boolean isOriginal() {
		// TODO Auto-generated method stub
		return false;
	}

}
