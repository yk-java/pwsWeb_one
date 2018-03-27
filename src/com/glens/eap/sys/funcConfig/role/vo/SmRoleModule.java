package com.glens.eap.sys.funcConfig.role.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class SmRoleModule implements ValueObject {

	private Long rowid;

	private String roleCode;

	private String moduleCode;

	private String remarks;

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode == null ? null : roleCode.trim();
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode == null ? null : moduleCode.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}
}