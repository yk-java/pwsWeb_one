package com.glens.pwCloudOs.pm.projDoc.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class DocumentCheckVo implements ValueObject {
	   
	   
	   private String companyCode;
	   private String proNo;
	   private String docNo;
	   private String checkFlag;
	   private String checkDesc;
	   private float checkVal;
	   private String remarks;
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
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getCheckDesc() {
		return checkDesc;
	}
	public void setCheckDesc(String checkDesc) {
		this.checkDesc = checkDesc;
	}
	public float getCheckVal() {
		return checkVal;
	}
	public void setCheckVal(float checkVal) {
		this.checkVal = checkVal;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
