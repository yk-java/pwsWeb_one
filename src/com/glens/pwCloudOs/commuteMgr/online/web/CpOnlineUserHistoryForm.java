package com.glens.pwCloudOs.commuteMgr.online.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.CpOnlineUserHistoryVo")
public class CpOnlineUserHistoryForm extends ControllerForm {

	private Long id;

	private String companycode;

	private String unitcode;

	private String employeecode;

	private String employeename;

	private String accountcode;

	private String logintime;

	private String offtime;

	private String network;

	private Integer duration;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAccountcode() {
		return accountcode;
	}

	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode == null ? null : accountcode.trim();
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime == null ? null : logintime.trim();
	}

	public String getOfftime() {
		return offtime;
	}

	public void setOfftime(String offtime) {
		this.offtime = offtime == null ? null : offtime.trim();
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network == null ? null : network.trim();
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub
		return null;
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