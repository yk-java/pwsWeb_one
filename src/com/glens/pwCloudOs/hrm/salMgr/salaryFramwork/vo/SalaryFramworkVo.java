/**
 * @Title: SalaryFramworkVo.java
 * @Package com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:江苏量为石科技股份有些公司
 * @author 
 * @date 2017-2-16 下午4:38:32
 * @version V1.0
 */


package com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.vo;

import com.glens.eap.platform.core.beans.ValueObject;

/**
 * 薪酬体系实体
 * 
 * @author 
 * @version V1.0
 */

public class SalaryFramworkVo implements ValueObject {

	private static final long serialVersionUID = 1L;

	private String companyCode;
	
	private String unitCode;
	
	private String unitName;
	
	private String title;
	
	private String validStartTime;
	
	private String validEndTime;
	
	private int status;
	
	private int salaryGrade;
	
	private int salaryLevel;
	
	private String salaryGradeNamePrefix;
	
	private int salaryGradeStart;
	
	private String remarks;
	
	private String frameworkCode;

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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValidStartTime() {
		return validStartTime;
	}

	public void setValidStartTime(String validStartTime) {
		this.validStartTime = validStartTime;
	}

	public String getValidEndTime() {
		return validEndTime;
	}

	public void setValidEndTime(String validEndTime) {
		this.validEndTime = validEndTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSalaryGrade() {
		return salaryGrade;
	}

	public void setSalaryGrade(int salaryGrade) {
		this.salaryGrade = salaryGrade;
	}

	public int getSalaryLevel() {
		return salaryLevel;
	}

	public void setSalaryLevel(int salaryLevel) {
		this.salaryLevel = salaryLevel;
	}

	public String getSalaryGradeNamePrefix() {
		return salaryGradeNamePrefix;
	}

	public void setSalaryGradeNamePrefix(String salaryGradeNamePrefix) {
		this.salaryGradeNamePrefix = salaryGradeNamePrefix;
	}

	public int getSalaryGradeStart() {
		return salaryGradeStart;
	}

	public void setSalaryGradeStart(int salaryGradeStart) {
		this.salaryGradeStart = salaryGradeStart;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFrameworkCode() {
		return frameworkCode;
	}

	public void setFrameworkCode(String frameworkCode) {
		this.frameworkCode = frameworkCode;
	}

}
