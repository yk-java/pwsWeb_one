package com.glens.eap.eapAuth.core.authentication.handlers;

import com.glens.eap.eapAuth.core.authentication.Credential;
import com.glens.eap.eapAuth.core.authentication.impl.UsernamePasswordCredential;
import com.glens.eap.eapAuth.core.exception.AuthenticationException;

public abstract class AbstractUsernamePasswordAuthenticationHandler extends
		AbstractPreAndPostProcessingAuthenticationHandler {

	private static final Class<UsernamePasswordCredential> DEFAULT_CLASS = UsernamePasswordCredential.class;

	private Class<?> classToSupport = DEFAULT_CLASS;

	private boolean supportSubClasses = true;

	private PasswordEncoder passwordEncoder = new PlainTextPasswordEncoder();
	
	@Override
	protected boolean doAuthentication(Credential credential)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return authenticateUsernamePasswordInternal((UsernamePasswordCredential) credential);
	}
	
	protected abstract boolean authenticateUsernamePasswordInternal(
			final UsernamePasswordCredential credential)
			throws AuthenticationException;

	@Override
	public boolean supports(Credential credential) {
		// TODO Auto-generated method stub
		return credential != null
		&& (this.classToSupport.equals(credential.getClass()) || (this.classToSupport
				.isAssignableFrom(credential.getClass()))
				&& this.supportSubClasses);
	}

	public final Class<?> getClassToSupport() {
		return classToSupport;
	}

	public final void setClassToSupport(Class<?> classToSupport) {
		this.classToSupport = classToSupport;
	}

	public final boolean isSupportSubClasses() {
		return supportSubClasses;
	}

	public final void setSupportSubClasses(boolean supportSubClasses) {
		this.supportSubClasses = supportSubClasses;
	}

	protected final PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public final void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}
