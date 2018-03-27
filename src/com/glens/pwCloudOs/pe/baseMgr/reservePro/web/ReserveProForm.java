package com.glens.pwCloudOs.pe.baseMgr.reservePro.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.pe.baseMgr.reservePro.vo.ReserveProVo")
public class ReserveProForm extends ControllerForm {

	
	private Long rowid;
	private String companyCode;
	private String mctViewCode;
	private String reserveProNo;
	private String reserveProName;
	private int reserveProGrade;
	private String rpAuditStateCode;
	private String auditPerson;
	private String auditDate;
	private String auditSuggest;
	private String remarks;
	
	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getMctViewCode() {
		return mctViewCode;
	}

	public void setMctViewCode(String mctViewCode) {
		this.mctViewCode = mctViewCode;
	}

	public String getReserveProNo() {
		return reserveProNo;
	}

	public void setReserveProNo(String reserveProNo) {
		this.reserveProNo = reserveProNo;
	}

	public String getReserveProName() {
		return reserveProName;
	}

	public void setReserveProName(String reserveProName) {
		this.reserveProName = reserveProName;
	}

	public int getReserveProGrade() {
		return reserveProGrade;
	}

	public void setReserveProGrade(int reserveProGrade) {
		this.reserveProGrade = reserveProGrade;
	}

	public String getRpAuditStateCode() {
		return rpAuditStateCode;
	}

	public void setRpAuditStateCode(String rpAuditStateCode) {
		this.rpAuditStateCode = rpAuditStateCode;
	}

	public String getAuditPerson() {
		return auditPerson;
	}

	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditSuggest() {
		return auditSuggest;
	}

	public void setAuditSuggest(String auditSuggest) {
		this.auditSuggest = auditSuggest;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("reserveProName", reserveProName);
		params.put("companyCode", companyCode);
		params.put("mctViewCode", mctViewCode);
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
