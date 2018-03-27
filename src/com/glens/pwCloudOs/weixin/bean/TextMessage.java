package com.glens.pwCloudOs.weixin.bean;

import com.glens.eap.platform.core.beans.ValueObject;

public class TextMessage implements ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8203909770546041835L;

	// 成员ID列表
	private String touser;

	// 部门ID列表
	private String toparty;

	// 标签ID列表
	private String totag;

	// 消息类型
	private String msgtype = "text";

	// 企业应用的id
	private Integer agentid;

	// 消息内容
	private String content;

	// 表示是否是保密消息，0表示否，1表示是，默认0
	private Integer safe;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getToparty() {
		return toparty;
	}

	public void setToparty(String toparty) {
		this.toparty = toparty;
	}

	public String getTotag() {
		return totag;
	}

	public void setTotag(String totag) {
		this.totag = totag;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSafe() {
		return safe;
	}

	public void setSafe(Integer safe) {
		this.safe = safe;
	}

}
