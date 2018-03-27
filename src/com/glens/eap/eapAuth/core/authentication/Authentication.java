package com.glens.eap.eapAuth.core.authentication;

import java.util.Date;
import java.util.Map;

public interface Authentication {

	public Map<String, Object> getAttributes();

	public Date getAuthenticatedDate();

	public Principal getPrincipal();
	
	public String getTicket();
}
