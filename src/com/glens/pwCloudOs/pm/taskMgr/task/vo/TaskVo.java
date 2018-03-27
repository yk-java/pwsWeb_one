package com.glens.pwCloudOs.pm.taskMgr.task.vo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.glens.eap.platform.core.beans.ValueObject;


@Document(collection="PM_TASK")
public class TaskVo implements ValueObject {
	private Long rowid;
	private String companyCode;
	private String proNo;
	private String proName;
	private String unitCode;
	private String unitName;
	private String employeeCode;
	private String employeeName;
	private String accountCode;
	private String deviceObjType;//设备类型
	private String deviceCode;
	private String deviceName;
	private String taskName;
	private String taskCode;
	
	private String mainTaskName;
	private int taskStatus;
	private String taskClassCode;//任务类型
	private int overdueStatus;
	private String startTime1;
	private String endTime1;
	private String startTime2;
	private String endTime2;
	private int taskResultFlag;//任务处理状态
	private String faultDesc;//故障现象
	private String sceneDesc;//现场状况
	private String img1;
	private String img2;
	private String img3;
	private String sysCreateTime;
	private String sysUpdateTime;
	private String sysDeleteTime;
	private String sysProcessFlag;
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
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
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
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getDeviceObjType() {
		return deviceObjType;
	}
	public void setDeviceObjType(String deviceObjType) {
		this.deviceObjType = deviceObjType;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	
	public String getMainTaskName() {
		return mainTaskName;
	}
	public void setMainTaskName(String mainTaskName) {
		this.mainTaskName = mainTaskName;
	}
	public int getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getTaskClassCode() {
		return taskClassCode;
	}
	public void setTaskClassCode(String taskClassCode) {
		this.taskClassCode = taskClassCode;
	}
	public int getOverdueStatus() {
		return overdueStatus;
	}
	public void setOverdueStatus(int overdueStatus) {
		this.overdueStatus = overdueStatus;
	}
	public String getStartTime1() {
		return startTime1;
	}
	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
	}
	public String getEndTime1() {
		return endTime1;
	}
	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}
	public String getStartTime2() {
		return startTime2;
	}
	public void setStartTime2(String startTime2) {
		this.startTime2 = startTime2;
	}
	public String getEndTime2() {
		return endTime2;
	}
	public void setEndTime2(String endTime2) {
		this.endTime2 = endTime2;
	}
	public int getTaskResultFlag() {
		return taskResultFlag;
	}
	public void setTaskResultFlag(int taskResultFlag) {
		this.taskResultFlag = taskResultFlag;
	}
	public String getFaultDesc() {
		return faultDesc;
	}
	public void setFaultDesc(String faultDesc) {
		this.faultDesc = faultDesc;
	}
	public String getSceneDesc() {
		return sceneDesc;
	}
	public void setSceneDesc(String sceneDesc) {
		this.sceneDesc = sceneDesc;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getImg3() {
		return img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
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
	public String getSysProcessFlag() {
		return sysProcessFlag;
	}
	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
}
