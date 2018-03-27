package com.glens.eap.eapAuth.core.app;

import java.io.Serializable;
/**
 * 应用描述信息。
 * @author Administrator
 *
 */
public class App implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8909901916281726150L;
	
	/**
	 * 应用ID
	 */
	private String appId;
	
	/**
	 * 应用名称。
	 */
	private String appName;
	
	/**
	 * 应用主机地址。
	 */
	private String host;
	
	/**
	 * 应用登出地址。
	 */
	private String logoutUrl;
	
	/**
	 * 是否是eapso服务器应用本身。
	 */
	private int eapsoServer = 0;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public final int isEapsoServer() {
		return eapsoServer;
	}

	public final void setEapsoServer(int eapsoServer) {
		this.eapsoServer = eapsoServer;
	}

	@Override
	public String toString() {
		return "App [appId=" + appId + ", appName=" + appName + ", host="
				+ host + ", logoutUrl=" + logoutUrl + ", eapsoServer="
				+ eapsoServer + "]";
	}
	
}
