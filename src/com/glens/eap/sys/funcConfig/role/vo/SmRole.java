package com.glens.eap.sys.funcConfig.role.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class SmRole implements ValueObject {

	private Long rowid;

	private String companyCode;

	private String roleCode;

	private String roleName;

	private String remarks;

	private String moduleCodes;

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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode == null ? null : roleCode.trim();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public String getModuleCodes() {
		return moduleCodes;
	}

	public void setModuleCodes(String moduleCodes) {
		this.moduleCodes = moduleCodes;
	}

}