package com.glens.pwCloudOs.om.deviceMgr.device.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
@ValueObjectProcessor(clazz="com.glens.pwCloudOs.om.deviceMgr.device.vo.QualityCheckRecordVo")
public class QualityCheckRecordForm extends ControllerForm {
	private Long rowid;
	private String companyCode;
	private String deviceObjCode;
	private Integer checkType;
	private String checkTime;
	private String checkUser;
	private String checkUserName;
	private Integer checkResult;
	private String faultType;
	private String faultDescr;
	private String pics;
	private String imgs;
	private Integer isRectify;
	
	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
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

	public String getDeviceObjCode() {
		return deviceObjCode;
	}

	public void setDeviceObjCode(String deviceObjCode) {
		this.deviceObjCode = deviceObjCode;
	}

	public Integer getCheckType() {
		return checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

	public Integer getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(Integer checkResult) {
		this.checkResult = checkResult;
	}

	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public String getFaultDescr() {
		return faultDescr;
	}

	public void setFaultDescr(String faultDescr) {
		this.faultDescr = faultDescr;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public Integer getIsRectify() {
		return isRectify;
	}

	public void setIsRectify(Integer isRectify) {
		this.isRectify = isRectify;
	}

	@Override
	protected Map doPreToMap() {
		Map map = new HashMap();
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
