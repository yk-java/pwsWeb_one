package com.glens.pwCloudOs.notice.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class SmNotice implements ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6242803563272979222L;

	private Long rowid;

	private String type;

	private String towho;

	private String title;

	private String publishDate;

	private String validDate;

	private String sysCreateTime;

	private String sysUpdateTime;

	private String sysDeleteTime;

	private String sysProcessFlag;

	private String source;

	private String remarks;

	private String btext;

	private String status;

	private String accountCode;

	private String startDate;

	private String endDate;

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getTowho() {
		return towho;
	}

	public void setTowho(String towho) {
		this.towho = towho == null ? null : towho.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate == null ? null : publishDate.trim();
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate == null ? null : validDate.trim();
	}

	public String getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime == null ? null : sysCreateTime
				.trim();
	}

	public String getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(String sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime == null ? null : sysUpdateTime
				.trim();
	}

	public String getSysDeleteTime() {
		return sysDeleteTime;
	}

	public void setSysDeleteTime(String sysDeleteTime) {
		this.sysDeleteTime = sysDeleteTime == null ? null : sysDeleteTime
				.trim();
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag == null ? null : sysProcessFlag
				.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public String getBtext() {
		return btext;
	}

	public void setBtext(String btext) {
		this.btext = btext == null ? null : btext.trim();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
}