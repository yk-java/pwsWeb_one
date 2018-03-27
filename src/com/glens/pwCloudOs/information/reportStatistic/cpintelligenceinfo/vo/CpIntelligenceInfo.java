package com.glens.pwCloudOs.information.reportStatistic.cpintelligenceinfo.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class CpIntelligenceInfo implements ValueObject {

	private Long rowid;

	private String companyCode;

	private String unitCode;

	private String unitName;

	private String employeeName;

	private String employeecode;

	private String accountCode;

	private String intelligenceTypeCode;

	private String intelligenceTypeName;

	private String intelligenceDesc;

	private String intelligenceUrgencyTypeCode;

	private String intelligenceUrgencyTypeName;

	private String img1;

	private String img2;

	private String img3;

	private BigDecimal x;

	private BigDecimal y;

	private String rpTime;

	private Date sysCreateTime;

	private Date sysUpdateTime;

	private Date sysDeleteTime;

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
		this.companyCode = companyCode == null ? null : companyCode.trim();
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode == null ? null : unitCode.trim();
	}

	public String getEmployeecode() {
		return employeecode;
	}

	public void setEmployeecode(String employeecode) {
		this.employeecode = employeecode == null ? null : employeecode.trim();
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode == null ? null : accountCode.trim();
	}

	public String getIntelligenceTypeCode() {
		return intelligenceTypeCode;
	}

	public void setIntelligenceTypeCode(String intelligenceTypeCode) {
		this.intelligenceTypeCode = intelligenceTypeCode == null ? null
				: intelligenceTypeCode.trim();
	}

	public String getIntelligenceDesc() {
		return intelligenceDesc;
	}

	public void setIntelligenceDesc(String intelligenceDesc) {
		this.intelligenceDesc = intelligenceDesc == null ? null
				: intelligenceDesc.trim();
	}

	public String getIntelligenceUrgencyTypeCode() {
		return intelligenceUrgencyTypeCode;
	}

	public void setIntelligenceUrgencyTypeCode(
			String intelligenceUrgencyTypeCode) {
		this.intelligenceUrgencyTypeCode = intelligenceUrgencyTypeCode == null ? null
				: intelligenceUrgencyTypeCode.trim();
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1 == null ? null : img1.trim();
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2 == null ? null : img2.trim();
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3 == null ? null : img3.trim();
	}

	public BigDecimal getX() {
		return x;
	}

	public void setX(BigDecimal x) {
		this.x = x;
	}

	public BigDecimal getY() {
		return y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}

	

	public String getRpTime() {
		return rpTime;
	}

	public void setRpTime(String rpTime) {
		this.rpTime = rpTime;
	}

	public Date getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(Date sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public Date getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public Date getSysDeleteTime() {
		return sysDeleteTime;
	}

	public void setSysDeleteTime(Date sysDeleteTime) {
		this.sysDeleteTime = sysDeleteTime;
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag == null ? null : sysProcessFlag
				.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getIntelligenceUrgencyTypeName() {
		return intelligenceUrgencyTypeName;
	}

	public void setIntelligenceUrgencyTypeName(
			String intelligenceUrgencyTypeName) {
		this.intelligenceUrgencyTypeName = intelligenceUrgencyTypeName;
	}

	public String getIntelligenceTypeName() {
		return intelligenceTypeName;
	}

	public void setIntelligenceTypeName(String intelligenceTypeName) {
		this.intelligenceTypeName = intelligenceTypeName;
	}
}