package com.glens.pwCloudOs.weixin.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import com.glens.eap.platform.core.beans.ValueObject;

/**
 * AccessToken
 * 
 * @author Administrator
 * 
 */
@Document(collection = "WEIXIN_TOKEN")
public class WeiXinToken implements ValueObject {

	private int wid;

	private long createTime;

	private String accessToken;

	private int expiresIn;

	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

}
