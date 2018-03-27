package com.glens.pwCloudOs.fm.rpaymentbase.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class RpaymentBaseVo implements ValueObject {

	private Long rowid;
	private String proNo;
	private String proCode;
	private String proName;
	private String contractNo; // 合同编号
	private String rpaymentDate;
	private String cgUnit;// 往来单位
	private double amount;
	private String invoiceNos;// 发票号
	private String remarks;

	private String amount1;

	private String isAccept;

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
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

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getRpaymentDate() {
		return rpaymentDate;
	}

	public void setRpaymentDate(String rpaymentDate) {
		this.rpaymentDate = rpaymentDate;
	}

	public String getCgUnit() {
		return cgUnit;
	}

	public void setCgUnit(String cgUnit) {
		this.cgUnit = cgUnit;
	}

	

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getInvoiceNos() {
		return invoiceNos;
	}

	public void setInvoiceNos(String invoiceNos) {
		this.invoiceNos = invoiceNos;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAmount1() {
		return amount1;
	}

	public void setAmount1(String amount1) {
		this.amount1 = amount1;
	}

}
