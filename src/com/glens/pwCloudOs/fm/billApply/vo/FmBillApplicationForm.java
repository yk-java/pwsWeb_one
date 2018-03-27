package com.glens.pwCloudOs.fm.billApply.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class FmBillApplicationForm implements ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9064858722315458925L;

	private Long rowid;

	private String applyCode;

	private String proName;

	private String proCode;

	private String contractCode;

	private String contractName;

	private String appContent;

	private String appType;

	private String appTypeName;

	private Float money;

	private String money2;

	private String company;

	private String company1;

	private String taxNo;

	private String bank;

	private String bankAccount;

	private String address;

	private String mobile;

	private String applyUser;

	private String user;

	private String dept;

	private String applyDate;

	private String applyDate1;

	private String createTime;

	private String updateTime;

	private String deleteTime;

	private String sysProcessFlag;

	private String remarks;

	private String fromTime;

	private String toTime;

	private String status;

	private String searchName;

	private Float taxRate;

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode == null ? null : applyCode.trim();
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName == null ? null : proName.trim();
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode == null ? null : proCode.trim();
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode == null ? null : contractCode.trim();
	}

	public String getAppContent() {
		return appContent;
	}

	public void setAppContent(String appContent) {
		this.appContent = appContent == null ? null : appContent.trim();
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType == null ? null : appType.trim();
	}

	public String getMoney2() {
		return money2;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public void setMoney2(String money2) {
		this.money2 = money2 == null ? null : money2.trim();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company == null ? null : company.trim();
	}

	public String getCompany1() {
		return company1;
	}

	public void setCompany1(String company1) {
		this.company1 = company1 == null ? null : company1.trim();
	}

	public String getTaxNo() {
		return taxNo;
	}

	public String getAppTypeName() {
		return appTypeName;
	}

	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo == null ? null : taxNo.trim();
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank == null ? null : bank.trim();
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount == null ? null : bankAccount.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser == null ? null : applyUser.trim();
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate == null ? null : applyDate.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime == null ? null : createTime.trim();
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime == null ? null : updateTime.trim();
	}

	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime == null ? null : deleteTime.trim();
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag == null ? null : sysProcessFlag
				.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getApplyDate1() {
		return applyDate1;
	}

	public void setApplyDate1(String applyDate1) {
		this.applyDate1 = applyDate1;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Float taxRate) {
		this.taxRate = taxRate;
	}

}