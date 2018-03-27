package com.glens.pwCloudOs.pm.taskMgr.mctMgr.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class MctCdevVo implements ValueObject {
	
	private Long rowid;
	private String companyCode;
	private String proNo;
	private String proName;
	private String mctCode;
	private String mctName;
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getMctName() {
		return mctName;
	}
	public void setMctName(String mctName) {
		this.mctName = mctName;
	}
	private String moduleName;
	private String moduleIco;
	private String pageUrl;
	private String servicesUrl;
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
	public String getMctCode() {
		return mctCode;
	}
	public void setMctCode(String mctCode) {
		this.mctCode = mctCode;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleIco() {
		return moduleIco;
	}
	public void setModuleIco(String moduleIco) {
		this.moduleIco = moduleIco;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getServicesUrl() {
		return servicesUrl;
	}
	public void setServicesUrl(String servicesUrl) {
		this.servicesUrl = servicesUrl;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
