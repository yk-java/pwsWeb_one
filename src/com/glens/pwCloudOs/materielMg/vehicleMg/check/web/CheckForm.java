package com.glens.pwCloudOs.materielMg.vehicleMg.check.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.materielMg.vehicleMg.check.vo.CheckVo")
public class CheckForm extends ControllerForm {
	
	private Long rowid;
	private String assetCode;
	private String checkDate;
	private String checkDate2;
	private float checkCost;
	private String checkAddress;
	private String sysCreateTime;
	private String sysUpdateTime;
	private String sysDeleteTime;
	private char sysProcessFlag;
	private String remarks;
	private String fromDate;
	private String endDate;
	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckDate2() {
		return checkDate2;
	}

	public void setCheckDate2(String checkDate2) {
		this.checkDate2 = checkDate2;
	}

	public float getCheckCost() {
		return checkCost;
	}

	public void setCheckCost(float checkCost) {
		this.checkCost = checkCost;
	}

	public String getCheckAddress() {
		return checkAddress;
	}

	public void setCheckAddress(String checkAddress) {
		this.checkAddress = checkAddress;
	}

	public String getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public String getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(String sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public String getSysDeleteTime() {
		return sysDeleteTime;
	}

	public void setSysDeleteTime(String sysDeleteTime) {
		this.sysDeleteTime = sysDeleteTime;
	}

	public char getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(char sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, String> params = new HashMap<String, String>();
		//params.put("assetC", assetName);
		params.put("checkDate", checkDate);
		params.put("checkDate2", checkDate2);
		params.put("assetCode", assetCode);
		params.put("fromDate", fromDate);
		params.put("endDate", endDate);
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
