package com.glens.pwCloudOs.pm.scene.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class PmSceneMonitorPoint implements ValueObject {
	private Long rowid;
	private String monitorPointCode;
	private String name;
	private Integer monitorType;
	private String configParams;
	private String spotCode;
	private Integer x;
	private Integer y;
	private String alarmMaxVal;
	private String alarmMinVal;
	private String spotName;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMonitorType() {
		return monitorType;
	}
	public void setMonitorType(Integer monitorType) {
		this.monitorType = monitorType;
	}
	public String getConfigParams() {
		return configParams;
	}
	public void setConfigParams(String configParams) {
		this.configParams = configParams;
	}
	public String getSpotCode() {
		return spotCode;
	}
	public void setSpotCode(String spotCode) {
		this.spotCode = spotCode;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public String getAlarmMaxVal() {
		return alarmMaxVal;
	}
	public void setAlarmMaxVal(String alarmMaxVal) {
		this.alarmMaxVal = alarmMaxVal;
	}
	public String getAlarmMinVal() {
		return alarmMinVal;
	}
	public void setAlarmMinVal(String alarmMinVal) {
		this.alarmMinVal = alarmMinVal;
	}
	public String getMonitorValue() {
		return monitorValue;
	}
	public void setMonitorValue(String monitorValue) {
		this.monitorValue = monitorValue;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	
}
