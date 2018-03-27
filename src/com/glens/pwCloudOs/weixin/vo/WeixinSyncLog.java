package com.glens.pwCloudOs.weixin.vo;

import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class WeixinSyncLog implements ValueObject {

	private Long rowid;

	private Date syncDate;

	private String syncType;

	private String remarks;

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public String getSyncType() {
		return syncType;
	}

	public void setSyncType(String syncType) {
		this.syncType = syncType == null ? null : syncType.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}
}