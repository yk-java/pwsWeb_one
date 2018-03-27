/**
 * @Title: OpsAppForm.java
 * @Package com.glens.pwCloudOs.opsApp.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-10 下午2:30:26
 * @version V1.0
 */

package com.glens.pwCloudOs.opsApp.web;

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

public class OpsAppForm extends ControllerForm {

	private String accountCode;

	private String field;

	private String fieldValue;

	private String employeeCode;

	private String employeeName;

	private String logTime;

	private String mobilePhone;

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	private String proNo;

	private String companyCode;

	private String docTypeLibCode;

	private String title;

	private String uploadDate;

	private String base64Img;

	private String psw;

	private String logContent;

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	private String remarks;

	private String orderField;

	private int logType;

	private int appType;

	private String logFromTime;

	private String logToTime;

	private String unitCode;

	private String phoneType;
	private String networkStatus;

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getNetworkStatus() {
		return networkStatus;
	}

	public void setNetworkStatus(String networkStatus) {
		this.networkStatus = networkStatus;
	}

	public int getLogType() {
		return logType;
	}

	public void setLogType(int logType) {
		this.logType = logType;
	}

	public int getAppType() {
		return appType;
	}

	public void setAppType(int appType) {
		this.appType = appType;
	}

	public String getLogFromTime() {
		return logFromTime;
	}

	public void setLogFromTime(String logFromTime) {
		this.logFromTime = logFromTime;
	}

	public String getLogToTime() {
		return logToTime;
	}

	public void setLogToTime(String logToTime) {
		this.logToTime = logToTime;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	/**
	 * 
	 * <p>
	 * Title: doPreToMap
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * 
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPreToMap()
	 **/

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("field", field);
		params.put("fieldValue", fieldValue);
		params.put("employeeCode", employeeCode);
		params.put("proNo", proNo);
		params.put("companyCode", companyCode);
		params.put("docTypeLibCode", docTypeLibCode);
		params.put("title", title);
		params.put("uploadDate", uploadDate);
		params.put("base64Img", base64Img);
		params.put("remarks", remarks);
		params.put("orderField", orderField);
		params.put("accountCode", accountCode);
		params.put("logTime", logTime);
		params.put("unitCode", unitCode);
		params.put("appType", appType);
		params.put("logFromTime", logFromTime);
		params.put("logToTime", logToTime);
		params.put("employeeName", employeeName);
		params.put("logType", logType);

		return params;
	}

	/**
	 * 
	 * <p>
	 * Title: doPostRequest
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param request
	 * 
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPostRequest(javax.servlet.http.HttpServletRequest)
	 **/

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * <p>
	 * Title: getGenerateKey
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * 
	 * @see com.glens.eap.platform.core.web.ControllerForm#getGenerateKey()
	 **/

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub

		return null;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getDocTypeLibCode() {
		return docTypeLibCode;
	}

	public void setDocTypeLibCode(String docTypeLibCode) {
		this.docTypeLibCode = docTypeLibCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getBase64Img() {
		return base64Img;
	}

	public void setBase64Img(String base64Img) {
		this.base64Img = base64Img;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

}
