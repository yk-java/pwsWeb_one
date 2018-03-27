package com.glens.pwCloudOs.weixin.bean;

public class WeixinResult {

	private String errcode;

	private String errmsg;

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

	@Override
	public String toString() {
		return "WeixinResult [errcode=" + errcode + ", errmsg=" + errmsg + "]";
	}

}
