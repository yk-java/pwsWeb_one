package com.glens.pwCloudOs.pm.memberMove.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;


@ValueObjectProcessor(clazz="com.glens.pwCloudOs.pm.memberMove.vo.PmMemberMoveVo")
public class PmMemberMoveForm extends ControllerForm {
	
	private Long rowid;
	private String moveCode;
	private String procStatus;
	private String employeeName1;
	private String employeeCode1;
	
	private String status;
	private String unitCode;
	
	private int date;
	
	
	

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmployeeName1() {
		return employeeName1;
	}

	public void setEmployeeName1(String employeeName1) {
		this.employeeName1 = employeeName1;
	}

	public String getEmployeeCode1() {
		return employeeCode1;
	}

	public void setEmployeeCode1(String employeeCode1) {
		this.employeeCode1 = employeeCode1;
	}

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
	private String fromDate;
	private String toDate;
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

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		//params.put("companyCode", companyCode);
		params.put("proNo1", proNo1);
		params.put("proName1", proName1);
		params.put("proNo2", proNo2);
		params.put("proName2", proName2);
		params.put("jobCode1", jobCode1);
		if(moveChar!=null && moveChar.equals("1")){//平调
			params.put("jobCode2", jobCode1);
		}else{
			params.put("jobCode2", jobCode2);
		}
		params.put("jobCode2", jobCode1);
		params.put("moveDate", moveDate);
		params.put("validDate", validDate);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("fileNo", fileNo);
		params.put("companyCode", companyCode);
		params.put("moveChar", moveChar);
		params.put("employeeCode", employeeCode);
		params.put("moveYear", moveYear);
		params.put("moveMonth", moveMonth);
		params.put("employeeName", employeeName);
		params.put("areaName", areaName);
		
		params.put("moveType",moveType);
		params.put("procStatus",procStatus);
		params.put("moveCode",moveCode);
		params.put("unitCode",unitCode);
		params.put("status",status);
		
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
