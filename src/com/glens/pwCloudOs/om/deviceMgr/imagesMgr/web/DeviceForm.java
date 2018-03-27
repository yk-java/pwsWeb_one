package com.glens.pwCloudOs.om.deviceMgr.imagesMgr.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;


@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceVo")
public class DeviceForm extends ControllerForm {

	
	private Long rowid;
	private String amulBoxAddr;//表箱地址
	private String  estaeName;//小区名称
	private String proNo;
	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getAmulBoxAddr() {
		return amulBoxAddr;
	}

	public void setAmulBoxAddr(String amulBoxAddr) {
		this.amulBoxAddr = amulBoxAddr;
	}

	public String getEstaeName() {
		return estaeName;
	}

	public void setEstaeName(String estaeName) {
		this.estaeName = estaeName;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("proNo", proNo);
		
	
		return map;
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
