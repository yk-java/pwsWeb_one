package com.glens.pwCloudOs.pe.baseMgr.remouldScheme.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;


@ValueObjectProcessor(clazz="com.glens.pwCloudOs.pe.baseMgr.remouldScheme.vo.RemouldSchemeVo")
public class RemouldSchemeForm extends ControllerForm {

	private Long rowid;
	private String companyCode;
	private String remouldSchemeCode;
	private String remouldSchemeName;
	private String remouldSchemeDesc;
	private String remarks;
	private String materialCodes;
	private String materialValues;
	
	
	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getMaterialCodes() {
		return materialCodes;
	}

	public void setMaterialCodes(String materialCodes) {
		this.materialCodes = materialCodes;
	}

	public String getMaterialValues() {
		return materialValues;
	}

	public void setMaterialValues(String materialValues) {
		this.materialValues = materialValues;
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

	@Override
	protected Map doPreToMap() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("remouldSchemeName", remouldSchemeName);
		params.put("companyCode", companyCode);
		params.put("remouldSchemeCode", remouldSchemeCode);
		params.put("materialValues", materialValues);
		params.put("materialCodes", materialCodes);
		
		
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
