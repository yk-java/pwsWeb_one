package com.glens.pwCloudOs.pm.memberMove.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class AppointmentVo implements ValueObject {
	private Long rowid;
	private String companyCode;
	private String employeeCode;
	private String appointmentCode;
	private String fileNo;
	private String proNo;
	private String proName;
	private int appoinmentType;
	private String appoinmentDate;
	private String validDate;
	private int appoinmentYear;
	private int appoinmentMonth;
	private String appointmentEvidence;
	private String remarks;
	private String jobCode;
	private String jobName;
	private String employeeName;
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
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
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
	public String getAppointmentCode() {
		return appointmentCode;
	}
	public void setAppointmentCode(String appointmentCode) {
		this.appointmentCode = appointmentCode;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
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
	public int getAppoinmentType() {
		return appoinmentType;
	}
	public void setAppoinmentType(int appoinmentType) {
		this.appoinmentType = appoinmentType;
	}
	public String getAppoinmentDate() {
		return appoinmentDate;
	}
	public void setAppoinmentDate(String appoinmentDate) {
		this.appoinmentDate = appoinmentDate;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public int getAppoinmentYear() {
		return appoinmentYear;
	}
	public void setAppoinmentYear(int appoinmentYear) {
		this.appoinmentYear = appoinmentYear;
	}
	public int getAppoinmentMonth() {
		return appoinmentMonth;
	}
	public void setAppoinmentMonth(int appoinmentMonth) {
		this.appoinmentMonth = appoinmentMonth;
	}
	public String getAppointmentEvidence() {
		return appointmentEvidence;
	}
	public void setAppointmentEvidence(String appointmentEvidence) {
		this.appointmentEvidence = appointmentEvidence;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	
}
