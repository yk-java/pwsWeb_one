package com.glens.pwCloudOs.notice.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class SmNoticeReadHistory implements ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1465465150018006991L;

	private Long rowid;

	private Long noticeId;

	private String companycode;

	private String unitcode;

	private String accountcode;

	private String employeecode;

	private String employeename;

	private String readTime;

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

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode == null ? null : companycode.trim();
	}

	public String getUnitcode() {
		return unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode == null ? null : unitcode.trim();
	}

	public String getAccountcode() {
		return accountcode;
	}

	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode == null ? null : accountcode.trim();
	}

	public String getEmployeecode() {
		return employeecode;
	}

	public void setEmployeecode(String employeecode) {
		this.employeecode = employeecode == null ? null : employeecode.trim();
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename == null ? null : employeename.trim();
	}

	public String getReadTime() {
		return readTime;
	}

	public void setReadTime(String readTime) {
		this.readTime = readTime == null ? null : readTime.trim();
	}
}