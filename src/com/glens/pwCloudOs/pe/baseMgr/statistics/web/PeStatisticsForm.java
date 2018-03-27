package com.glens.pwCloudOs.pe.baseMgr.statistics.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.web.ControllerForm;

public class PeStatisticsForm extends ControllerForm {
	
	private String companyCode;
	private String reserveProNo;
	private String remouldBatchCode;
	private String xField;
	private String yField;
	private String xFieldVal;
	private String yFieldVal;
	private String searchName;
	private String attrJson;
	private String mctCode;
	private String deviceTypeCode;
	
	public String getxFieldVal() {
		return xFieldVal;
	}

	public void setxFieldVal(String xFieldVal) {
		this.xFieldVal = xFieldVal;
	}

	public String getxField() {
		return xField;
	}

	public void setxField(String xField) {
		this.xField = xField;
	}

	public String getyField() {
		return yField;
	}

	public void setyField(String yField) {
		this.yField = yField;
	}

	public String getyFieldVal() {
		return yFieldVal;
	}

	public void setyFieldVal(String yFieldVal) {
		this.yFieldVal = yFieldVal;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getAttrJson() {
		return attrJson;
	}

	public void setAttrJson(String attrJson) {
		this.attrJson = attrJson;
	}

	public String getMctCode() {
		return mctCode;
	}

	public void setMctCode(String mctCode) {
		this.mctCode = mctCode;
	}

	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}

	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getReserveProNo() {
		return reserveProNo;
	}

	public void setReserveProNo(String reserveProNo) {
		this.reserveProNo = reserveProNo;
	}

	public String getRemouldBatchCode() {
		return remouldBatchCode;
	}

	public void setRemouldBatchCode(String remouldBatchCode) {
		this.remouldBatchCode = remouldBatchCode;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyCode", companyCode);
		params.put("reserveProNo", reserveProNo);
		params.put("remouldBatchCode", remouldBatchCode);
		params.put("xField", xField);
		params.put("yField", yField);
		params.put("xFieldVal", xFieldVal);
		params.put("yFieldVal", yFieldVal);
		params.put("searchName", searchName);
		params.put("attrJson", attrJson);
		params.put("mctCode", mctCode);
		params.put("deviceTypeCode", deviceTypeCode);
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
