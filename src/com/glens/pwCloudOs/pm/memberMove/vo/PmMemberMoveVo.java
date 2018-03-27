package com.glens.pwCloudOs.pm.memberMove.vo;

import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class PmMemberMoveVo implements ValueObject {
	
	private Long rowid;
	
	
	private String moveCode;
	private String procStatus;
	
	public String getMoveCode() {
		return moveCode;
	}

	public void setMoveCode(String moveCode) {
		this.moveCode = moveCode;
	}

	public String getProcStatus() {
		return procStatus;
	}

	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}
	private String companyCode;
	private String employeeCode;
	private String employeeName;
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	private String proNo1;
	private String proName1;
	private String jobCode1;
	private String jobName1;
	private String proNo2;
	private String proName2;
	private String jobCode2;
	private String jobName2;
	private String moveDate;
	private String validDate;
	private int moveYear;
	private int moveMonth;
	private String moveEvidence;
	private String sysCreateTime;
	private String sysUpdateTime;
	private String sysDeleteTime;
	private String remarks;
	private String fileNo;
	private String moveChar;
	private String areaName;
	private int isFinish;
	private String moveType;
	

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public int getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(int isFinish) {
		this.isFinish = isFinish;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getMoveChar() {
		return moveChar;
	}
	public void setMoveChar(String moveChar) {
		this.moveChar = moveChar;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
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
	public String getProNo1() {
		return proNo1;
	}
	public void setProNo1(String proNo1) {
		this.proNo1 = proNo1;
	}
	public String getProName1() {
		return proName1;
	}
	public void setProName1(String proName1) {
		this.proName1 = proName1;
	}
	public String getJobCode1() {
		return jobCode1;
	}
	public void setJobCode1(String jobCode1) {
		this.jobCode1 = jobCode1;
	}
	public String getJobName1() {
		return jobName1;
	}
	public void setJobName1(String jobName1) {
		this.jobName1 = jobName1;
	}
	public String getProNo2() {
		return proNo2;
	}
	public void setProNo2(String proNo2) {
		this.proNo2 = proNo2;
	}
	public String getProName2() {
		return proName2;
	}
	public void setProName2(String proName2) {
		this.proName2 = proName2;
	}
	public String getJobCode2() {
		return jobCode2;
	}
	public void setJobCode2(String jobCode2) {
		this.jobCode2 = jobCode2;
	}
	public String getJobName2() {
		return jobName2;
	}
	public void setJobName2(String jobName2) {
		this.jobName2 = jobName2;
	}
	public String getMoveDate() {
		return moveDate;
	}
	public void setMoveDate(String moveDate) {
		this.moveDate = moveDate;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public int getMoveYear() {
		return moveYear;
	}
	public void setMoveYear(int moveYear) {
		this.moveYear = moveYear;
	}
	public int getMoveMonth() {
		return moveMonth;
	}
	public void setMoveMonth(int moveMonth) {
		this.moveMonth = moveMonth;
	}
	public String getMoveEvidence() {
		return moveEvidence;
	}
	public void setMoveEvidence(String moveEvidence) {
		this.moveEvidence = moveEvidence;
	}
	public String getSysCreateTime() {
		return sysCreateTime;
	}
	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}
	public String getSysUpdateTime() {
		return sysUpdateTime;
	}
	public void setSysUpdateTime(String sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}
	public String getSysDeleteTime() {
		return sysDeleteTime;
	}
	public void setSysDeleteTime(String sysDeleteTime) {
		this.sysDeleteTime = sysDeleteTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
