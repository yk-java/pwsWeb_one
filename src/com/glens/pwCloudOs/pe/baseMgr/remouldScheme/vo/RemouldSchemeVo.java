package com.glens.pwCloudOs.pe.baseMgr.remouldScheme.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class RemouldSchemeVo implements ValueObject {
	
	
	private Long rowid;
	private String companyCode;
	private String remouldSchemeCode;
	private String remouldSchemeName;
	private String remouldSchemeDesc;
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
	public String getRemouldSchemeCode() {
		return remouldSchemeCode;
	}
	public void setRemouldSchemeCode(String remouldSchemeCode) {
		this.remouldSchemeCode = remouldSchemeCode;
	}
	public String getRemouldSchemeName() {
		return remouldSchemeName;
	}
	public void setRemouldSchemeName(String remouldSchemeName) {
		this.remouldSchemeName = remouldSchemeName;
	}
	public String getRemouldSchemeDesc() {
		return remouldSchemeDesc;
	}
	public void setRemouldSchemeDesc(String remouldSchemeDesc) {
		this.remouldSchemeDesc = remouldSchemeDesc;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
