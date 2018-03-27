/**
 * @Title: PmDeviceOpLogVo.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.device.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-11-3 下午6:00:47
 * @version V1.0
 */


package com.glens.pwCloudOs.om.deviceMgr.device.vo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.glens.eap.platform.core.beans.ValueObject;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */
@Document(collection="PM_DEVICE_OP_LOG")
public class PmDeviceOpLogVo implements ValueObject {

	private String companyCode;
	
	private String unitCode;
	
	private String unitName;
	
	private String employeeCode;
	
	private String employeeName;
	
	private String accountCode;
	
	private String accountName;
	
	private String opTime;
	
	private int opType;
	
	private int affectRecordNum;
	
	private String deviceObjCode;
	
	private String deviceObjName;
	
	private String opDesc;
	
	private String remarks;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOpTime() {
		return opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	public int getOpType() {
		return opType;
	}

	public void setOpType(int opType) {
		this.opType = opType;
	}

	public int getAffectRecordNum() {
		return affectRecordNum;
	}

	public void setAffectRecordNum(int affectRecordNum) {
		this.affectRecordNum = affectRecordNum;
	}

	public String getDeviceObjCode() {
		return deviceObjCode;
	}

	public void setDeviceObjCode(String deviceObjCode) {
		this.deviceObjCode = deviceObjCode;
	}

	public String getDeviceObjName() {
		return deviceObjName;
	}

	public void setDeviceObjName(String deviceObjName) {
		this.deviceObjName = deviceObjName;
	}

	public String getOpDesc() {
		return opDesc;
	}

	public void setOpDesc(String opDesc) {
		this.opDesc = opDesc;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
