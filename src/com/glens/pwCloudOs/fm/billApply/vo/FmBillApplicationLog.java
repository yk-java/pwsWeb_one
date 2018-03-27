package com.glens.pwCloudOs.fm.billApply.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class FmBillApplicationLog implements ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6916076242779478112L;

	private Long rowid;

	private String applyCode;

	private String invoiceNo;

	private Float totalMoney;

	private Float taxMoney;

	private Float pureMoney;

	private String createTime;

	private String updateTime;

	private String deleteTime;

	private String sysProcessFlag;

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

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public Float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Float getTaxMoney() {
		return taxMoney;
	}

	public void setTaxMoney(Float taxMoney) {
		this.taxMoney = taxMoney;
	}

	public Float getPureMoney() {
		return pureMoney;
	}

	public void setPureMoney(Float pureMoney) {
		this.pureMoney = pureMoney;
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
}