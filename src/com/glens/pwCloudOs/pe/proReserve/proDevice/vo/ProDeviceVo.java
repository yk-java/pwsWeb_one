/**
 * @Title: ProDeviceVo.java
 * @Package com.glens.pwCloudOs.pe.proReserve.proDevice.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-26 上午11:27:58
 * @version V1.0
 */


package com.glens.pwCloudOs.pe.proReserve.proDevice.vo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.glens.eap.platform.core.beans.ValueObject;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@Document(collection="PE_BS_RP_DEVICES")
public class ProDeviceVo implements ValueObject {

	private String companyCode;
	
	private String reserveProNo;
	
	private String reserveProName;
	
	private String deviceObjCode;
	
	private String deviceObjName;
	
	private String remouldSchemeCode;
	
	private String rpAuditState;
	
	private String auditPersonCode;
	
	private String auditPersonName;
	
	private String auditDate;
	
	private String auditSuggest;
	
	private String sysProcessFlag;
	
	private String remarks;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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

	public String getRemouldSchemeCode() {
		return remouldSchemeCode;
	}

	public void setRemouldSchemeCode(String remouldSchemeCode) {
		this.remouldSchemeCode = remouldSchemeCode;
	}

	public String getRpAuditState() {
		return rpAuditState;
	}

	public void setRpAuditState(String rpAuditState) {
		this.rpAuditState = rpAuditState;
	}

	public String getAuditPersonCode() {
		return auditPersonCode;
	}

	public void setAuditPersonCode(String auditPersonCode) {
		this.auditPersonCode = auditPersonCode;
	}

	public String getAuditPersonName() {
		return auditPersonName;
	}

	public void setAuditPersonName(String auditPersonName) {
		this.auditPersonName = auditPersonName;
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
