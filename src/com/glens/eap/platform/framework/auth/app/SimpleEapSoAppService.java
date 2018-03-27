package com.glens.eap.platform.framework.auth.app;

import com.glens.eap.eapAuth.core.app.App;
import com.glens.eap.eapAuth.core.app.AppService;

public class SimpleEapSoAppService implements AppService {

	@Override
	public App findAppByHost(String host) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public App findAppById(String appId) {
		// TODO Auto-generated method stub
		App app = new App();
		app.setAppId("01");
		app.setAppName("");
		app.setEapsoServer(1);
		
		return app;
	}

	@Override
	public App findEapsoServerApp() {
		// TODO Auto-generated method stub
		App app = new App();
		app.setAppId("01");
		app.setAppName("");
		app.setEapsoServer(1);
		
		return app;
	}

}
