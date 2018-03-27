package com.glens.pwCloudOs.dailylog.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class DailylogVo implements ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountCode;

	private String companyCode;

	private String employeeCode;

	private String employeeName;

	private String fromDate;

	private String logDate;

	private String proNo;

	private String remarks;

	private Long rowid;

	private String toDate;

	private String unitCode;

	private int wiNo;// 工作项目(序号)
	private String wiPlan;// 工作项目（计划执行情况）
	private String wiPro;// 工作项目
	private String wiRecord;// 工作项目（记录）
	private String wiResult;// 工作项目(成果)

	private String sysCreateTime;

	private String sysProcessFlag;

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag;
	}

	public String getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getLogDate() {
		return logDate;
	}

	public String getProNo() {
		return proNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public Long getRowid() {
		return rowid;
	}

	public String getToDate() {
		return toDate;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public int getWiNo() {
		return wiNo;
	}

	public String getWiPlan() {
		return wiPlan;
	}

	public String getWiPro() {
		return wiPro;
	}

	public String getWiRecord() {
		return wiRecord;
	}

	public String getWiResult() {
		return wiResult;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public void setWiNo(int wiNo) {
		this.wiNo = wiNo;
	}

	public void setWiPlan(String wiPlan) {
		this.wiPlan = wiPlan;
	}

	public void setWiPro(String wiPro) {
		this.wiPro = wiPro;
	}

	public void setWiRecord(String wiRecord) {
		this.wiRecord = wiRecord;
	}

	public void setWiResult(String wiResult) {
		this.wiResult = wiResult;
	}

}
