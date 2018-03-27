package com.glens.eap.eapAuth.core.authentication.handlers;

import org.springframework.util.StringUtils;

import com.glens.eap.eapAuth.core.authentication.impl.UsernamePasswordCredential;
import com.glens.eap.eapAuth.core.exception.AuthenticationException;
import com.glens.eap.eapAuth.core.exception.UsernameOrPasswordInvalidException;

public class SimpleUsernamePasswordAuthenticationHandler extends
		AbstractUsernamePasswordAuthenticationHandler {

	public SimpleUsernamePasswordAuthenticationHandler() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean authenticateUsernamePasswordInternal(
			final UsernamePasswordCredential credential)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		final String username = credential.getUsername();
		final String password = credential.getPassword();

		if (StringUtils.hasText(username) && StringUtils.hasText(password)
				&& username.equals(getPasswordEncoder().encode(password))) {
			return true;
		}
		throw UsernameOrPasswordInvalidException.INSTANCE;
	}

}
