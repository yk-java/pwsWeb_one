package com.glens.pwCloudOs.fm.contractBase.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class ContractBaseVo implements ValueObject {

	private Long rowid;
	private String proNo;
	private String proName;
	private String district;
	private String contractNo;
	private String contractName;
	private String contractDate;
	private String fpUnit;
	private String contractor;
	private float amount;
	private float invoiceAmount;
	private String remarks;
	private String searchName;
	
	
	private String province;
	
	private String city;
	
	private String businessUnit;
	
	private int contractProjType;
	
    private int contractType;
    
    private float writtenPrice;
    
    private String deviceUnivalent;
    
    private float  actualValue;
    
    private int  priceIsTax;
    
    private int  taxPoint;
    
    private String  spUnit;
    
    private String  spUnitCharger;
    
    private int billingStatus;
    
    private String expectBillingTime;
    
    private float  nonInvoiceAmount;
    
    private int paymentStatus;
    
    private String expectPaymentTime;
	

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public int getContractProjType() {
		return contractProjType;
	}

	public void setContractProjType(int contractProjType) {
		this.contractProjType = contractProjType;
	}

	public int getContractType() {
		return contractType;
	}

	public void setContractType(int contractType) {
		this.contractType = contractType;
	}

	public float getWrittenPrice() {
		return writtenPrice;
	}

	public void setWrittenPrice(float writtenPrice) {
		this.writtenPrice = writtenPrice;
	}

	public String getDeviceUnivalent() {
		return deviceUnivalent;
	}

	public void setDeviceUnivalent(String deviceUnivalent) {
		this.deviceUnivalent = deviceUnivalent;
	}

	public float getActualValue() {
		return actualValue;
	}

	public void setActualValue(float actualValue) {
		this.actualValue = actualValue;
	}

	public int getPriceIsTax() {
		return priceIsTax;
	}

	public void setPriceIsTax(int priceIsTax) {
		this.priceIsTax = priceIsTax;
	}

	public int getTaxPoint() {
		return taxPoint;
	}

	public void setTaxPoint(int taxPoint) {
		this.taxPoint = taxPoint;
	}

	public String getSpUnit() {
		return spUnit;
	}

	public void setSpUnit(String spUnit) {
		this.spUnit = spUnit;
	}

	public String getSpUnitCharger() {
		return spUnitCharger;
	}

	public void setSpUnitCharger(String spUnitCharger) {
		this.spUnitCharger = spUnitCharger;
	}

	public int getBillingStatus() {
		return billingStatus;
	}

	public void setBillingStatus(int billingStatus) {
		this.billingStatus = billingStatus;
	}

	public String getExpectBillingTime() {
		return expectBillingTime;
	}

	public void setExpectBillingTime(String expectBillingTime) {
		this.expectBillingTime = expectBillingTime;
	}

	public float getNonInvoiceAmount() {
		return nonInvoiceAmount;
	}

	public void setNonInvoiceAmount(float nonInvoiceAmount) {
		this.nonInvoiceAmount = nonInvoiceAmount;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getExpectPaymentTime() {
		return expectPaymentTime;
	}

	public void setExpectPaymentTime(String expectPaymentTime) {
		this.expectPaymentTime = expectPaymentTime;
	}

	private String categoryCode;

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractDate() {
		return contractDate;
	}

	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}

	public String getFpUnit() {
		return fpUnit;
	}

	public void setFpUnit(String fpUnit) {
		this.fpUnit = fpUnit;
	}

	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(float invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

}
