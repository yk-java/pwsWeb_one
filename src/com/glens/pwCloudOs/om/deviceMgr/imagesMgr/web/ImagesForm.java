package com.glens.pwCloudOs.om.deviceMgr.imagesMgr.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.web.ControllerForm;

public class ImagesForm extends ControllerForm {
	
	private String addr;//地址
	private String estaeName;//
	private String companyCode;
	private String proNo;
	
	
		

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEstaeName() {
		return estaeName;
	}

	public void setEstaeName(String estaeName) {
		this.estaeName = estaeName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("addr", addr);
		params.put("companyCode", companyCode);
		params.put("estaeName", estaeName);
		params.put("proNo", proNo);
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
