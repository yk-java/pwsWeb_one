package com.glens.pwCloudOs.weixin.bean;

public class AccessToken {

	private String errcode;

	private String errmsg;

	private String access_token;

	private int expires_in;

	private Long createTime;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AccessToken [errcode=" + errcode + ", errmsg=" + errmsg
				+ ", access_token=" + access_token + ", expires_in="
				+ expires_in + ", createTime=" + createTime + "]";
	}

}
