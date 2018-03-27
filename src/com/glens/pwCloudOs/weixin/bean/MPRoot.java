package com.glens.pwCloudOs.weixin.bean;

public class MPRoot {

	private String touser;

	private String toparty;

	private String totag;

	private String msgtype;

	private int agentid;

	private Mpnews mpnews;

	private int safe;

	private Text text;

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTouser() {
		return this.touser;
	}

	public void setToparty(String toparty) {
		this.toparty = toparty;
	}

	public String getToparty() {
		return this.toparty;
	}

	public void setTotag(String totag) {
		this.totag = totag;
	}

	public String getTotag() {
		return this.totag;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getMsgtype() {
		return this.msgtype;
	}

	public void setAgentid(int agentid) {
		this.agentid = agentid;
	}

	public int getAgentid() {
		return this.agentid;
	}

	public void setMpnews(Mpnews mpnews) {
		this.mpnews = mpnews;
	}

	public Mpnews getMpnews() {
		return this.mpnews;
	}

	public void setSafe(int safe) {
		this.safe = safe;
	}

	public int getSafe() {
		return this.safe;
	}
}
