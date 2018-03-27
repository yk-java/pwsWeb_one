package com.glens.eap.platform.framework.auth.key;

import com.glens.eap.eapAuth.core.key.EAPSoKey;
import com.glens.eap.eapAuth.core.key.KeyService;

public class SimpleEapSoKeyService implements KeyService {

	@Override
	public EAPSoKey findKeyByAppId(String appId) {
		// TODO Auto-generated method stub
		EAPSoKey key = new EAPSoKey();
		key.setAppId("01");
		key.setKeyId("01008");
		key.setValue("123abc");
		
		return key;
	}

	@Override
	public EAPSoKey findKeyByKeyId(String keyId) {
		// TODO Auto-generated method stub
		EAPSoKey key = new EAPSoKey();
		key.setAppId("01");
		key.setKeyId("01008");
		key.setValue("123abc");
		
		return key;
	}

}
