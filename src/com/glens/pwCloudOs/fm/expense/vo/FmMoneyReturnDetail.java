package com.glens.pwCloudOs.fm.expense.vo;

import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class FmMoneyReturnDetail implements ValueObject {

	private Long rowid;

	private String feeDate;

	private String feeDesc;

	private Float money;

	private Integer invoiceCnt;

	private Long feeId;

	private Date sysCreateTime;

	private Date sysUpdateTime;

	private Date sysDeleteTime;

	private String sysProcessFlag;

	private String remarks;

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getFeeDate() {
		return feeDate;
	}

	public void setFeeDate(String feeDate) {
		this.feeDate = feeDate == null ? null : feeDate.trim();
	}

	public String getFeeDesc() {
		return feeDesc;
	}

	public void setFeeDesc(String feeDesc) {
		this.feeDesc = feeDesc == null ? null : feeDesc.trim();
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Integer getInvoiceCnt() {
		return invoiceCnt;
	}

	public void setInvoiceCnt(Integer invoiceCnt) {
		this.invoiceCnt = invoiceCnt;
	}

	public Long getFeeId() {
		return feeId;
	}

	public void setFeeId(Long feeId) {
		this.feeId = feeId;
	}

	public Date getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(Date sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public Date getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public Date getSysDeleteTime() {
		return sysDeleteTime;
	}

	public void setSysDeleteTime(Date sysDeleteTime) {
		this.sysDeleteTime = sysDeleteTime;
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

	@Override
	public String toString() {
		return "FmMoneyReturnDetail [rowid=" + rowid + ", feeDate=" + feeDate
				+ ", feeDesc=" + feeDesc + ", money=" + money + ", invoiceCnt="
				+ invoiceCnt + ", feeId=" + feeId + ", sysCreateTime="
				+ sysCreateTime + ", sysUpdateTime=" + sysUpdateTime
				+ ", sysDeleteTime=" + sysDeleteTime + ", sysProcessFlag="
				+ sysProcessFlag + ", remarks=" + remarks + "]";
	}

}