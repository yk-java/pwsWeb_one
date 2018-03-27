package com.glens.pwCloudOs.materielMg.vehicleMg.insurance.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class InsuranceVo implements ValueObject {
	
	private String assetCode;
	private String assetName;
	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	private String name;
	private int type;
	private float cost;
	private String validTimeS;
	private String validTimeE;
	private String sysCreateTime;
	private String sysUpdateTime;
	private String sysDeleteTime;
	private char sysProcessFlag;
	private String remarks;
	private Long rowid;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getValidTimeS() {
		return validTimeS;
	}
	public void setValidTimeS(String validTimeS) {
		this.validTimeS = validTimeS;
	}
	public String getValidTimeE() {
		return validTimeE;
	}
	public void setValidTimeE(String validTimeE) {
		this.validTimeE = validTimeE;
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
	
							
}
