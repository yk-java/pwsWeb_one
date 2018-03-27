package com.glens.pwCloudOs.notice.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class SmNoticeReceive implements ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2056889402762728670L;

	private Long rowid;

	private Long noticeId;

	private String accountCode;

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode == null ? null : accountCode.trim();
	}
}