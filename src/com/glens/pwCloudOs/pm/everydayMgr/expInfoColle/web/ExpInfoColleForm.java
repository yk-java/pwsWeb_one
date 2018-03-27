package com.glens.pwCloudOs.pm.everydayMgr.expInfoColle.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.pm.everydayMgr.expInfoColle.vo.ExpInfoColleVo")
public class ExpInfoColleForm extends ControllerForm {

	private Long rowid;
	private String companyCode;
	private String employeeCode;
	private String employeeName;
	private String phone;
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	private String fromDate;
	private String toDate;

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
	private String exproName; 
	private String exproScope;
	private String exproType;
	private float exproTprice;
	private String exproUprice;
	private String  ownerName;
	private String  ownerResponsibler;
	private String  ownerNeeds;
	private String  sdate;
	private String  edate;
	private String  bdate;
	private String   marketor;
	private String  isContract;
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

	

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getExproName() {
		return exproName;
	}

	public void setExproName(String exproName) {
		this.exproName = exproName;
	}

	public String getExproScope() {
		return exproScope;
	}

	public void setExproScope(String exproScope) {
		this.exproScope = exproScope;
	}

	public float getExproTprice() {
		return exproTprice;
	}

	public void setExproTprice(float exproTprice) {
		this.exproTprice = exproTprice;
	}

	public String getExproUprice() {
		return exproUprice;
	}

	public String getExproType() {
		return exproType;
	}

	public void setExproType(String exproType) {
		this.exproType = exproType;
	}

	public void setExproUprice(String exproUprice) {
		this.exproUprice = exproUprice;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerResponsibler() {
		return ownerResponsibler;
	}

	public void setOwnerResponsibler(String ownerResponsibler) {
		this.ownerResponsibler = ownerResponsibler;
	}

	public String getOwnerNeeds() {
		return ownerNeeds;
	}

	public void setOwnerNeeds(String ownerNeeds) {
		this.ownerNeeds = ownerNeeds;
	}

	
	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getMarketor() {
		return marketor;
	}

	public void setMarketor(String marketor) {
		this.marketor = marketor;
	}

	public String getIsContract() {
		return isContract;
	}

	public void setIsContract(String isContract) {
		this.isContract = isContract;
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("employeeCode", employeeCode);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
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
