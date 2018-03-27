package com.glens.pwCloudOs.pe.baseMgr.projectInit.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.pe.baseMgr.projectInit.vo.ProjectInitVo")
public class ProjectInitForm extends ControllerForm {

	 private Long rowid;
	 private String companyCode;
	 private String mctViewName;
	 private String mctViewCode;
	 private String proNos;
	 private String proNames;
	 private String remarks;
	 private String proMctCodes;
	 private String proMctNames;
	 private String deviceTypeCode;
	
	public String getProMctCodes() {
		return proMctCodes;
	}

	public void setProMctCodes(String proMctCodes) {
		this.proMctCodes = proMctCodes;
	}

	public String getProMctNames() {
		return proMctNames;
	}

	public void setProMctNames(String proMctNames) {
		this.proMctNames = proMctNames;
	}

	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}

	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}

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

	public String getMctViewName() {
		return mctViewName;
	}

	public void setMctViewName(String mctViewName) {
		this.mctViewName = mctViewName;
	}

	public String getMctViewCode() {
		return mctViewCode;
	}

	public void setMctViewCode(String mctViewCode) {
		this.mctViewCode = mctViewCode;
	}

	public String getProNos() {
		return proNos;
	}

	public void setProNos(String proNos) {
		this.proNos = proNos;
	}

	public String getProNames() {
		return proNames;
	}

	public void setProNames(String proNames) {
		this.proNames = proNames;
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
		params.put("mctViewName", mctViewName);
		params.put("companyCode", companyCode);
		params.put("mctViewCode", mctViewCode);
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
