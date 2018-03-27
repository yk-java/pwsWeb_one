package com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.vo;

import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class CpCommuteConfig implements ValueObject {

	private Long rowid;

	private String companyCode;
	private String proNo;
	private String proName;

	private String checkinTime;

	private String checkoutTime;
	
	private Integer checkinExMinites;
	private Integer checkoutExMinites;
	

	public Integer getCheckinExMinites() {
		return checkinExMinites;
	}

	public void setCheckinExMinites(Integer checkinExMinites) {
		this.checkinExMinites = checkinExMinites;
	}

	public Integer getCheckoutExMinites() {
		return checkoutExMinites;
	}

	public void setCheckoutExMinites(Integer checkoutExMinites) {
		this.checkoutExMinites = checkoutExMinites;
	}

	private Integer checkPointTotal;

	private String commuteSettime;

	private Date sysCreateTime;

	private Date sysUpdateTime;

	private Date sysDeleteTime;

	private String sysProcessFlag;

	private String remarks;

	public Long getRowid() {
		return rowid;
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

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode == null ? null : companyCode.trim();
	}

	public Integer getCheckPointTotal() {
		return checkPointTotal;
	}

	public void setCheckPointTotal(Integer checkPointTotal) {
		this.checkPointTotal = checkPointTotal;
	}

	public String getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(String checkinTime) {
		this.checkinTime = checkinTime;
	}

	public String getCheckoutTime() {
		return checkoutTime;
	}

	public void setCheckoutTime(String checkoutTime) {
		this.checkoutTime = checkoutTime;
	}

	public String getCommuteSettime() {
		return commuteSettime;
	}

	public void setCommuteSettime(String commuteSettime) {
		this.commuteSettime = commuteSettime;
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
}