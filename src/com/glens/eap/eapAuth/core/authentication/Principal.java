package com.glens.eap.eapAuth.core.authentication;

import java.util.Map;

public interface Principal {

	public Map<String, Object> getAttributes();
	
	public String getId();
}
