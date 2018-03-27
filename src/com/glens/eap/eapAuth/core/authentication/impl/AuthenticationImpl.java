package com.glens.eap.eapAuth.core.authentication.impl;

import java.util.Date;
import java.util.Map;

import com.glens.eap.eapAuth.core.authentication.Authentication;
import com.glens.eap.eapAuth.core.authentication.Principal;

public class AuthenticationImpl implements Authentication {

	private Date authenticatedDate;
	
	private Map<String, Object> attributes;

	private Principal principal;
	
	private String ticket;
	
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public Date getAuthenticatedDate() {
		// TODO Auto-generated method stub
		return authenticatedDate;
	}

	@Override
	public Principal getPrincipal() {
		// TODO Auto-generated method stub
		return principal;
	}

	public void setAuthenticatedDate(Date authenticatedDate) {
		this.authenticatedDate = authenticatedDate;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
