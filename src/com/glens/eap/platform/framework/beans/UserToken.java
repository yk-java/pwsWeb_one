package com.glens.eap.platform.framework.beans;

import com.glens.eap.platform.core.beans.ValueObject;

/**
 * 
 * @ClassName: UserToken
 * 
 * @Description: 用户登录信息类
 * 
 * @author
 * 
 * @date 2016-5-6 下午2:41:36
 * 
 * 
 */

@SuppressWarnings("serial")
public class UserToken implements ValueObject {

	/**
	 * 
	 * @Fields KEY_TOKEN : 记录到session的key名称
	 */

	public static final String KEY_TOKEN = "userToken";

	private String regionCode;

	private String regionName;

	private String companyCode;

	private String companyName;

	private String unitCode;

	private String unitName;

	private String employeeCode;

	private String employeeName;

	private String roleCode;

	private String roleName;

	private String accountCode;

	private String accountName;

	private String accountPsw;

	private int accountType;
	
	private Float x;
	private Float y;

	public Float getX() {
		return x;
	}

	public void setX(Float x) {
		this.x = x;
	}

	public Float getY() {
		return y;
	}

	public void setY(Float y) {
		this.y = y;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPsw() {
		return accountPsw;
	}

	public void setAccountPsw(String accountPsw) {
		this.accountPsw = accountPsw;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

}
