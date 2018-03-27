package com.glens.eap.platform.framework.context;

import org.springframework.context.ApplicationContext;

public abstract class EAPContext {

	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		EAPContext.context = context;
	}
}
