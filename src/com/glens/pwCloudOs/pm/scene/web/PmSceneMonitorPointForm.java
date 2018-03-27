package com.glens.pwCloudOs.pm.scene.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.pm.scene.vo.PmSceneMonitorPoint")
public class PmSceneMonitorPointForm extends ControllerForm {
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
	
	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("monitorType", monitorType);
		params.put("spotCode", spotCode);
		return params;
	}

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
