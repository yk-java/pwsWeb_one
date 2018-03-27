/**
 * @Title: ProDeviceForm.java
 * @Package com.glens.pwCloudOs.pe.proReserve.proDevice.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-26 下午3:17:23
 * @version V1.0
 */


package com.glens.pwCloudOs.pe.proReserve.proDevice.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.web.ControllerForm;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class ProDeviceForm extends ControllerForm {

	private String companyCode;
	
	private String proNo;
	
	private String mctCode;
	
	private String attrJson;
	
	private String deviceObjCode;
	private String deviceObjCodes;
	
	public String getDeviceObjCodes() {
		return deviceObjCodes;
	}

	public void setDeviceObjCodes(String deviceObjCodes) {
		this.deviceObjCodes = deviceObjCodes;
	}

	private String deviceObjName;
	
	private float x;
	
	private float y;
	
	private String xqAddressCode;
	
	private String xqAddressName;
	
	private String addressCode;
	
	private String addressName;
	
	private String qrCode;
	
	private String problemCode;
	
	private String problemName;
	
	private String healthCode;
	
	private String healthName;
	
	private String remarks;
	
	private String proName;
	
	private String searchName;
	
	private String mctViewCode;
	
	private String deviceTypeCode;
	
	private String reserveProNo;
	
	private String reserveProName;
	
	private String remouldSchemeCode;
	
	private String rpAuditState;
	
	private String auditPersonCode;
	
	private String auditPersonName;
	
	private String auditDate;
	
	private String auditSuggest;
	
	private String sysProcessFlag;
	
	private String materialType;
	
	private String materialTypeCount;
	
	private String materialTypeAmount;
	
	private String accountCode;
	
	private String deleteMode;
	
	public String getDeleteMode() {
		return deleteMode;
	}

	public void setDeleteMode(String deleteMode) {
		this.deleteMode = deleteMode;
	}

	/**
	
	 * <p>Title: doPreToMap</p>
	
	 * <p>Description: </p>
	
	 * @return
	
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPreToMap()
	
	 **/

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyCode", companyCode);
		params.put("proNo", proNo);
		params.put("mctCode", mctCode);
		params.put("attrJson", attrJson);
		params.put("deviceObjCode", deviceObjCode);
		params.put("deviceObjName", deviceObjName);
		params.put("x", x);
		params.put("y", y);
		params.put("xqAddressCode", xqAddressCode);
		params.put("xqAddressName", xqAddressName);
		params.put("addressCode", addressCode);
		params.put("addressName", addressName);
		params.put("qrCode", qrCode);
		params.put("problemCode", problemCode);
		params.put("problemName", problemName);
		params.put("healthCode", healthCode);
		params.put("healthName", healthName);
		params.put("remarks", remarks);
		params.put("proName", proName);
		params.put("searchName", searchName);
		params.put("mctViewCode", mctViewCode);
		params.put("deviceTypeCode", deviceTypeCode);
		params.put("reserveProNo", reserveProNo);
		params.put("reserveProName", reserveProName);
		params.put("remouldSchemeCode", remouldSchemeCode);
		params.put("rpAuditState", rpAuditState);
		params.put("auditPersonCode", auditPersonCode);
		params.put("auditPersonName", auditPersonName);
		params.put("auditDate", auditDate);
		params.put("auditSuggest", auditSuggest);
		params.put("sysProcessFlag", sysProcessFlag);
		params.put("materialType", materialType);
		params.put("materialTypeCount", materialTypeCount);
		params.put("materialTypeAmount", materialTypeAmount);
		params.put("accountCode", accountCode);
		params.put("deviceObjCodes", deviceObjCodes);
		params.put("deleteMode", deleteMode);
		return params;
	}

	/**
	
	 * <p>Title: doPostRequest</p>
	
	 * <p>Description: </p>
	
	 * @param request
	
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPostRequest(javax.servlet.http.HttpServletRequest)
	
	 **/

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	/**
	
	 * <p>Title: getGenerateKey</p>
	
	 * <p>Description: </p>
	
	 * @return
	
	 * @see com.glens.eap.platform.core.web.ControllerForm#getGenerateKey()
	
	 **/

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub

		return null;
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

	public String getAttrJson() {
		return attrJson;
	}

	public void setAttrJson(String attrJson) {
		this.attrJson = attrJson;
	}

	public String getDeviceObjCode() {
		return deviceObjCode;
	}

	public void setDeviceObjCode(String deviceObjCode) {
		this.deviceObjCode = deviceObjCode;
	}

	public String getDeviceObjName() {
		return deviceObjName;
	}

	public void setDeviceObjName(String deviceObjName) {
		this.deviceObjName = deviceObjName;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public String getXqAddressCode() {
		return xqAddressCode;
	}

	public void setXqAddressCode(String xqAddressCode) {
		this.xqAddressCode = xqAddressCode;
	}

	public String getXqAddressName() {
		return xqAddressName;
	}

	public void setXqAddressName(String xqAddressName) {
		this.xqAddressName = xqAddressName;
	}

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public String getHealthCode() {
		return healthCode;
	}

	public void setHealthCode(String healthCode) {
		this.healthCode = healthCode;
	}

	public String getHealthName() {
		return healthName;
	}

	public void setHealthName(String healthName) {
		this.healthName = healthName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getMctViewCode() {
		return mctViewCode;
	}

	public void setMctViewCode(String mctViewCode) {
		this.mctViewCode = mctViewCode;
	}

	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}

	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}

	public String getReserveProNo() {
		return reserveProNo;
	}

	public void setReserveProNo(String reserveProNo) {
		this.reserveProNo = reserveProNo;
	}

	public String getReserveProName() {
		return reserveProName;
	}

	public void setReserveProName(String reserveProName) {
		this.reserveProName = reserveProName;
	}

	public String getRemouldSchemeCode() {
		return remouldSchemeCode;
	}

	public void setRemouldSchemeCode(String remouldSchemeCode) {
		this.remouldSchemeCode = remouldSchemeCode;
	}

	public String getRpAuditState() {
		return rpAuditState;
	}

	public void setRpAuditState(String rpAuditState) {
		this.rpAuditState = rpAuditState;
	}

	public String getAuditPersonCode() {
		return auditPersonCode;
	}

	public void setAuditPersonCode(String auditPersonCode) {
		this.auditPersonCode = auditPersonCode;
	}

	public String getAuditPersonName() {
		return auditPersonName;
	}

	public void setAuditPersonName(String auditPersonName) {
		this.auditPersonName = auditPersonName;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditSuggest() {
		return auditSuggest;
	}

	public void setAuditSuggest(String auditSuggest) {
		this.auditSuggest = auditSuggest;
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMaterialTypeCount() {
		return materialTypeCount;
	}

	public void setMaterialTypeCount(String materialTypeCount) {
		this.materialTypeCount = materialTypeCount;
	}

	public String getMaterialTypeAmount() {
		return materialTypeAmount;
	}

	public void setMaterialTypeAmount(String materialTypeAmount) {
		this.materialTypeAmount = materialTypeAmount;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

}
