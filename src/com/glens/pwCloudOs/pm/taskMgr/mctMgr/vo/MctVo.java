package com.glens.pwCloudOs.pm.taskMgr.mctMgr.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class MctVo implements ValueObject {
	
	private Long rowid;
	private String companyCode;
	private String proNo;
	private String proName;
	private String mctCode;
	private String mctName;
	private int  enableOmModel;//运维模型
	private String  deviceTypeCode;
	private String  deviceTypeName;
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getDeviceTypeName() {
		return deviceTypeName;
	}
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}
	private String remarks;
	public Long getRowid() {
		return rowid;
	}
	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getMctCode() {
		return mctCode;
	}
	public void setMctCode(String mctCode) {
		this.mctCode = mctCode;
	}
	public String getMctName() {
		return mctName;
	}
	public void setMctName(String mctName) {
		this.mctName = mctName;
	}
	public int getEnableOmModel() {
		return enableOmModel;
	}
	public void setEnableOmModel(int enableOmModel) {
		this.enableOmModel = enableOmModel;
	}
	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}
	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
