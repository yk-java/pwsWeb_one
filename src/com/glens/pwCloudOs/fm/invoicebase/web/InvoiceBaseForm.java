package com.glens.pwCloudOs.fm.invoicebase.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.fm.invoicebase.vo.InvoiceBaseVo")
public class InvoiceBaseForm extends ControllerForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8310620840040352810L;
	private Long rowid;
	private String proNo;
	private String proCode;
	private String proName;
	private String invoiceDate;
	private String invoiceClassCode;
	private String invoiceNo;
	private String cgUnit;// 来往单号
	private String contractNo; // 合同编号
	private String invoiceContent;
	private double taxRate;
	private double mount1;
	private double taxAmount;
	private double mount2;
	private String remarks;
	private String fromDate;
	private String toDate;
	private String proStatus;

	private String _mount1;
	private String _taxAmount;
	private String _mount2;

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	// 申请编号
	private String applyCode;

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

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub

		Map<String, String> params = new HashMap<String, String>();
		params.put("proNo", proNo);
		params.put("invoiceNo", invoiceNo);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("proStatus", proStatus);
		params.put("proCode", proCode);
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

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public String get_mount1() {
		return _mount1;
	}

	public void set_mount1(String _mount1) {
		this._mount1 = _mount1;
	}

	public String get_taxAmount() {
		return _taxAmount;
	}

	public void set_taxAmount(String _taxAmount) {
		this._taxAmount = _taxAmount;
	}

	public String get_mount2() {
		return _mount2;
	}

	public void set_mount2(String _mount2) {
		this._mount2 = _mount2;
	}

}
