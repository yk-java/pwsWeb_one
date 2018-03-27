package com.glens.eap.eapAuth.core.key;

public interface KeyService {
	
	/**
	 * 根据密钥ID查找对应的密钥信息。
	 * @param keyId 密钥ID.
	 * @return 密钥信息。
	 */
	public EAPSoKey findKeyByKeyId(String keyId);
	
	/**
	 * 根据应用ID查找对应的密钥信息。
	 * @param appId 应用ID.
	 * @return 密钥信息。
	 */
	public EAPSoKey findKeyByAppId(String appId);
	
}
