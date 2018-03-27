/**
 * @Title: PwBaoAppForm.java
 * @Package com.glens.pwCloudOs.pwBaoApp.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-13 上午11:28:23
 * @version V1.0
 */

package com.glens.pwCloudOs.pwBaoApp.web;

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

public class PwBaoAppForm extends ControllerForm {

	private String accountCode;

	private String psw;

	private String employeeCode;

	private String field;

	private String fieldValue;

	private String companyCode;

	private String unitCode;

	private String intelligenceTypeCode;

	private String intelligenceDesc;

	private String intelligenceUrgencyTypeCode;

	private String img1;

	private String img2;

	private String img3;

	private double x;

	private double y;

	private String rpTime;

	private String accountName;

	private String proNo;

	private String date;

	private String checkinImg1;

	private String checkinImg2;

	private String checkinTime;

	private int checkinType;

	private String usedPower;

	private double usedFlow;

	private String roleCode;

	private String distance;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
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
		params.put("accountCode", accountCode);
		params.put("psw", psw);
		params.put("employeeCode", employeeCode);
		params.put("field", field);
		params.put("fieldValue", fieldValue);
		params.put("companyCode", companyCode);
		params.put("unitCode", unitCode);
		params.put("intelligenceTypeCode", intelligenceTypeCode);
		params.put("intelligenceDesc", intelligenceDesc);
		params.put("intelligenceUrgencyTypeCode", intelligenceUrgencyTypeCode);
		params.put("img1", img1);
		params.put("img2", img2);
		params.put("img3", img3);
		params.put("x", x);
		params.put("y", y);
		params.put("rpTime", rpTime);
		params.put("accountName", accountName);
		params.put("proNo", proNo);
		params.put("date", date);
		params.put("checkinImg1", checkinImg1);
		params.put("checkinImg2", checkinImg2);
		params.put("checkinTime", checkinTime);
		params.put("checkinType", checkinType);
		params.put("usedPower", usedPower);
		params.put("usedFlow", usedFlow);
		params.put("roleCode", roleCode);
		params.put("distance", distance);
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

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
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

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getIntelligenceTypeCode() {
		return intelligenceTypeCode;
	}

	public void setIntelligenceTypeCode(String intelligenceTypeCode) {
		this.intelligenceTypeCode = intelligenceTypeCode;
	}

	public String getIntelligenceDesc() {
		return intelligenceDesc;
	}

	public void setIntelligenceDesc(String intelligenceDesc) {
		this.intelligenceDesc = intelligenceDesc;
	}

	public String getIntelligenceUrgencyTypeCode() {
		return intelligenceUrgencyTypeCode;
	}

	public void setIntelligenceUrgencyTypeCode(
			String intelligenceUrgencyTypeCode) {
		this.intelligenceUrgencyTypeCode = intelligenceUrgencyTypeCode;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getRpTime() {
		return rpTime;
	}

	public void setRpTime(String rpTime) {
		this.rpTime = rpTime;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCheckinImg1() {
		return checkinImg1;
	}

	public void setCheckinImg1(String checkinImg1) {
		this.checkinImg1 = checkinImg1;
	}

	public String getCheckinImg2() {
		return checkinImg2;
	}

	public void setCheckinImg2(String checkinImg2) {
		this.checkinImg2 = checkinImg2;
	}

	public String getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(String checkinTime) {
		this.checkinTime = checkinTime;
	}

	public int getCheckinType() {
		return checkinType;
	}

	public void setCheckinType(int checkinType) {
		this.checkinType = checkinType;
	}

	public String getUsedPower() {
		return usedPower;
	}

	public void setUsedPower(String usedPower) {
		this.usedPower = usedPower;
	}

	public double getUsedFlow() {
		return usedFlow;
	}

	public void setUsedFlow(double usedFlow) {
		this.usedFlow = usedFlow;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "PwBaoAppForm [accountCode=" + accountCode + ", psw=" + psw
				+ ", employeeCode=" + employeeCode + ", field=" + field
				+ ", fieldValue=" + fieldValue + ", companyCode=" + companyCode
				+ ", unitCode=" + unitCode + ", intelligenceTypeCode="
				+ intelligenceTypeCode + ", intelligenceDesc="
				+ intelligenceDesc + ", intelligenceUrgencyTypeCode="
				+ intelligenceUrgencyTypeCode + ", img1=" + img1 + ", img2="
				+ img2 + ", img3=" + img3 + ", x=" + x + ", y=" + y
				+ ", rpTime=" + rpTime + ", accountName=" + accountName
				+ ", proNo=" + proNo + ", date=" + date + ", checkinImg1="
				+ checkinImg1 + ", checkinImg2=" + checkinImg2
				+ ", checkinTime=" + checkinTime + ", checkinType="
				+ checkinType + ", usedPower=" + usedPower + ", usedFlow="
				+ usedFlow + ", roleCode=" + roleCode + ", distance="
				+ distance + "]";
	}

}
