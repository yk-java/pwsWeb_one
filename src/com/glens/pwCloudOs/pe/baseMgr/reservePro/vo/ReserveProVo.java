package com.glens.pwCloudOs.pe.baseMgr.reservePro.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class ReserveProVo implements ValueObject {
	private Long rowid;
	private String companyCode;
	private String mctViewCode;
	private String mctViewName;
	public String getMctViewName() {
		return mctViewName;
	}
	public void setMctViewName(String mctViewName) {
		this.mctViewName = mctViewName;
	}
	private String reserveProNo;
	private String reserveProName;
	private int reserveProGrade;
	private String rpAuditStateCode;
	private String auditPerson;
	private String auditDate;
	private String auditSuggest;
	private String remarks;
	private String deviceTypeCode;
	
	private int meterBoxCount;
	
	private int houseHold;
	
	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}
	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}
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
	public String getMctViewCode() {
		return mctViewCode;
	}
	public void setMctViewCode(String mctViewCode) {
		this.mctViewCode = mctViewCode;
	}
	public String getReserveProNo() {
		return reserveProNo;
	}
	public void setReserveProNo(String reserveProNo) {
		this.reserveProNo = reserveProNo;
	}
	public String getReserveProName() {
		return reserveProName;
	}
	public void setReserveProName(String reserveProName) {
		this.reserveProName = reserveProName;
	}
	public int getReserveProGrade() {
		return reserveProGrade;
	}
	public void setReserveProGrade(int reserveProGrade) {
		this.reserveProGrade = reserveProGrade;
	}
	public String getRpAuditStateCode() {
		return rpAuditStateCode;
	}
	public void setRpAuditStateCode(String rpAuditStateCode) {
		this.rpAuditStateCode = rpAuditStateCode;
	}
	public String getAuditPerson() {
		return auditPerson;
	}
	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getAuditSuggest() {
		return auditSuggest;
	}
	public void setAuditSuggest(String auditSuggest) {
		this.auditSuggest = auditSuggest;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getMeterBoxCount() {
		return meterBoxCount;
	}
	public void setMeterBoxCount(int meterBoxCount) {
		this.meterBoxCount = meterBoxCount;
	}
	public int getHouseHold() {
		return houseHold;
	}
	public void setHouseHold(int houseHold) {
		this.houseHold = houseHold;
	}
	
	
}
