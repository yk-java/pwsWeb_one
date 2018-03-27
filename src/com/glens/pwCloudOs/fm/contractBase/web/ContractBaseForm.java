package com.glens.pwCloudOs.fm.contractBase.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.fm.contractBase.vo.ContractBaseVo")
public class ContractBaseForm extends ControllerForm {

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
	private String fromDate;
	private String toDate;
	private String proStatus;
	private String searchName;
	private String queryDate;
	
	
	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

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
    
    private String billingStatus;
    
    private String expectBillingTime;
    
    private float  nonInvoiceAmount;
    
    private String paymentStatus;
    
    private String expectPaymentTime;
    
    
    private String attrJson;
    
    
    
    
	
	

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getAttrJson() {
		return attrJson;
	}

	public void setAttrJson(String attrJson) {
		this.attrJson = attrJson;
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

	

	public String getBillingStatus() {
		return billingStatus;
	}

	public void setBillingStatus(String billingStatus) {
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

	

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getExpectPaymentTime() {
		return expectPaymentTime;
	}

	public void setExpectPaymentTime(String expectPaymentTime) {
		this.expectPaymentTime = expectPaymentTime;
	}

	private String categoryCode;

	public String getProStatus() {
		return proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

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

	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("proNo", proNo);

		params.put("proName", proName);
		params.put("district", district);
		params.put("contractNo", contractNo);
		params.put("contractName", contractName);
		params.put("contractDate", contractDate);
		params.put("fpUnit", fpUnit);
		params.put("contractor", contractor);
		params.put("remarks", remarks);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("proStatus", proStatus);
		params.put("categoryCode", categoryCode);
		
		params.put("province", province);
		params.put("city", city);
		params.put("businessUnit", businessUnit);
		params.put("contractProjType", contractProjType);
		params.put("contractType", contractType);
		params.put("writtenPrice", writtenPrice);
		params.put("deviceUnivalent", deviceUnivalent);
		params.put("actualValue", actualValue);
		params.put("priceIsTax", priceIsTax);
		params.put("taxPoint", taxPoint);
		params.put("spUnit", spUnit);
		params.put("spUnitCharger", spUnitCharger);
		params.put("billingStatus", billingStatus);
		params.put("expectBillingTime", expectBillingTime);
		params.put("nonInvoiceAmount", nonInvoiceAmount);
		params.put("paymentStatus", paymentStatus);
		params.put("expectPaymentTime", expectPaymentTime);
		params.put("searchName", searchName);
		params.put("attrJson", attrJson);
		params.put("queryDate", queryDate);
		
		
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
