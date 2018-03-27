package com.glens.pwCloudOs.pe.baseMgr.materialType.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;


@ValueObjectProcessor(clazz="com.glens.pwCloudOs.pe.baseMgr.materialType.vo.MaterialTypeVo")
public class MaterialTypeForm extends ControllerForm {
	
	private Long rowid;
	private String companyCode;
	private String materialTypeCode;
	private String materialTypeName;
	private String materialTypeCost;
	private String materialTypeUnit;
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

	public String getMaterialTypeCode() {
		return materialTypeCode;
	}

	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	public String getMaterialTypeCost() {
		return materialTypeCost;
	}

	public void setMaterialTypeCost(String materialTypeCost) {
		this.materialTypeCost = materialTypeCost;
	}

	public String getMaterialTypeUnit() {
		return materialTypeUnit;
	}

	public void setMaterialTypeUnit(String materialTypeUnit) {
		this.materialTypeUnit = materialTypeUnit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("materialTypeName", materialTypeName);
		params.put("companyCode", companyCode);
		params.put("materialTypeCode", materialTypeCode);
		return params;
	}

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
