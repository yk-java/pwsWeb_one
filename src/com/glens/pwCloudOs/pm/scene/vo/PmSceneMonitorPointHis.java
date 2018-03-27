package com.glens.pwCloudOs.pm.scene.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class PmSceneMonitorPointHis implements ValueObject {
	private Long rowid;
	private String monitorPointCode;
	private String monitorValue;
	private String submitTime;
	public Long getRowid() {
		return rowid;
	}
	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}
	public String getMonitorPointCode() {
		return monitorPointCode;
	}
	public void setMonitorPointCode(String monitorPointCode) {
		this.monitorPointCode = monitorPointCode;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getMonitorValue() {
		return monitorValue;
	}
	public void setMonitorValue(String monitorValue) {
		this.monitorValue = monitorValue;
	}
	
}
