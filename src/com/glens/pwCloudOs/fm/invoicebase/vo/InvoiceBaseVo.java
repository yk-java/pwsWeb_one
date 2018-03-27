package com.glens.pwCloudOs.fm.invoicebase.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class InvoiceBaseVo implements ValueObject {

	
	private Long rowid;
	private String proNo;
	private String proCode;
	private String proName;
	private String invoiceDate;
	private String invoiceClassCode;
	private String invoiceNo;//发票号
	private String cgUnit;//往来单位
	private String contractNo; //合同编号
	private String invoiceContent;
	
	private String remarks;
	private String  invoiceClassName;
	private double mount1;
	private double taxRate;
	private double taxAmount;
	private double mount2;
	
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public double getMount1() {
		return mount1;
	}
	public void setMount1(double mount1) {
		this.mount1 = mount1;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public double getMount2() {
		return mount2;
	}
	public void setMount2(double mount2) {
		this.mount2 = mount2;
	}
	public String getInvoiceClassName() {
		return invoiceClassName;
	}
	public void setInvoiceClassName(String invoiceClassName) {
		this.invoiceClassName = invoiceClassName;
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
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getInvoiceClassCode() {
		return invoiceClassCode;
	}
	public void setInvoiceClassCode(String invoiceClassCode) {
		this.invoiceClassCode = invoiceClassCode;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getCgUnit() {
		return cgUnit;
	}
	public void setCgUnit(String cgUnit) {
		this.cgUnit = cgUnit;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getInvoiceContent() {
		return invoiceContent;
	}
	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	
}
