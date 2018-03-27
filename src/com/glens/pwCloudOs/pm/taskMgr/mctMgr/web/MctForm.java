package com.glens.pwCloudOs.pm.taskMgr.mctMgr.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.pm.taskMgr.mctMgr.vo.MctVo")
public class MctForm extends ControllerForm {
	
	private Long rowid;
	private String companyCode;
	private String proNo;
	private String mctCode;
	private String mctName;
	private int  enableOmModel;//运维模型
	private String  deviceTypeCode;
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

	public String getMctName() {
		return mctName;
	}

	public void setMctName(String mctName) {
		this.mctName = mctName;
	}

	public int getEnableOmModel() {
		return enableOmModel;
	}

	public void setEnableOmModel(int enableOmModel) {
		this.enableOmModel = enableOmModel;
	}

	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}

	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("enableOmModel", enableOmModel);
		params.put("mctName", mctName);
		params.put("proNo", proNo);
		params.put("companyCode", companyCode);
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
