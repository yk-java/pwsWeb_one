package com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class AssetsInProjectVo implements ValueObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9060497861353626270L;
	private Long rowid;
	private String assetCode;
	private String assetClassName;
	private String assetClassCode;
	private String assetTypeName;
	private String brand;
	private String modelNo;
	private String loanEmployeename;
	private String loanUnitName;
	private String loanProNo;
	private String loanProName;
	private String rentDate;
	private String estimateReturnDate;
	private String vaNo;
	private Integer rentStatus;
	private Integer flowStatus;
	
	
	public Integer getFlowStatus() {
		return flowStatus;
	}
	public void setFlowStatus(Integer flowStatus) {
		this.flowStatus = flowStatus;
	}
	public Long getRowid() {
		return rowid;
	}
	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}
	public Integer getRentStatus() {
		return rentStatus;
	}
	public void setRentStatus(Integer rentStatus) {
		this.rentStatus = rentStatus;
	}
	public String getVaNo() {
		return vaNo;
	}
	public void setVaNo(String vaNo) {
		this.vaNo = vaNo;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	
	public String getAssetClassName() {
		return assetClassName;
	}
	public void setAssetClassName(String assetClassName) {
		this.assetClassName = assetClassName;
	}
	public String getAssetClassCode() {
		return assetClassCode;
	}
	public void setAssetClassCode(String assetClassCode) {
		this.assetClassCode = assetClassCode;
	}
	public String getAssetTypeName() {
		return assetTypeName;
	}
	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public String getLoanEmployeename() {
		return loanEmployeename;
	}
	public void setLoanEmployeename(String loanEmployeename) {
		this.loanEmployeename = loanEmployeename;
	}
	public String getLoanUnitName() {
		return loanUnitName;
	}
	public void setLoanUnitName(String loanUnitName) {
		this.loanUnitName = loanUnitName;
	}
	
	public String getLoanProNo() {
		return loanProNo;
	}
	public void setLoanProNo(String loanProNo) {
		this.loanProNo = loanProNo;
	}
	public String getLoanProName() {
		return loanProName;
	}
	public void setLoanProName(String loanProName) {
		this.loanProName = loanProName;
	}
	public String getRentDate() {
		return rentDate;
	}
	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}
	public String getEstimateReturnDate() {
		return estimateReturnDate;
	}
	public void setEstimateReturnDate(String estimateReturnDate) {
		this.estimateReturnDate = estimateReturnDate;
	}
	
	
}