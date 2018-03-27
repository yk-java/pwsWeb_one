package com.glens.pwCloudOs.fm.rpaymentbase.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.fm.rpaymentbase.vo.RpaymentBaseVo")
public class RpaymentBaseForm extends ControllerForm {

	private Long rowid;
	private String proNo;
	private String proName;
	private String contractNo; // 合同编号
	private String rpaymentDate;
	private String cgUnit;// 往来单位
	private double amount;
	private String invoiceNos;// 发票号
	private String remarks;
	private String proStatus;

	private String fromDate;
	private String toDate;
	private String isZero;

	private String isAccept;

	public String getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}

	public String getProStatus() {
		return proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
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

	public String getIsZero() {
		return isZero;
	}

	public void setIsZero(String isZero) {
		this.isZero = isZero;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("proNo", proNo);
		params.put("invoiceNos", invoiceNos);
		params.put("proStatus", proStatus);
		params.put("isAccept", isAccept);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("isZero", isZero);
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
