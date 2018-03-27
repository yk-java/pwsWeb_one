/**
 * @Title: SalaryFramworkForm.java
 * @Package com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-2-16 下午5:50:05
 * @version V1.0
 */


package com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.vo.SalaryFramworkVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.vo.SalaryFramworkVo")
public class SalaryFramworkForm extends ControllerForm {

	/**
	 * 
	 */
	
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
	
	private String salarys;
	
	private String employeeCode;
	
	private int salaryGradeConfigStatus;  //1--未设置  2--已设置
	
	private String employeeName;
	
	private String proNo;
	
	private String newUnitCode;
	
	private String jobCode;
	
	private int  isYearlySalary;
	
	private double realSalary;
	
	private double gradeSalary;
	
	private String contractPropertyCode;
	
	private String budgetEstimateCode;
	
	private String versionCode;
	
	

	public String getBudgetEstimateCode() {
		return budgetEstimateCode;
	}

	public void setBudgetEstimateCode(String budgetEstimateCode) {
		this.budgetEstimateCode = budgetEstimateCode;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public int getIsYearlySalary() {
		return isYearlySalary;
	}

	public void setIsYearlySalary(int isYearlySalary) {
		this.isYearlySalary = isYearlySalary;
	}

	

	public double getRealSalary() {
		return realSalary;
	}

	public void setRealSalary(double realSalary) {
		this.realSalary = realSalary;
	}

	public double getGradeSalary() {
		return gradeSalary;
	}

	public void setGradeSalary(double gradeSalary) {
		this.gradeSalary = gradeSalary;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
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
		params.put("unitCode", unitCode);
		params.put("validStartTime", validStartTime);
		params.put("validEndTime", validEndTime);
		params.put("status", status);
		params.put("salarys", salarys);
		params.put("frameworkCode", frameworkCode);
		params.put("employeeCode", employeeCode);
		params.put("salaryGradeConfigStatus", salaryGradeConfigStatus);
		params.put("salaryGrade", salaryGrade);
		params.put("salaryLevel", salaryLevel);
		params.put("salaryGradeNamePrefix", salaryGradeNamePrefix);
		params.put("employeeName", employeeName);
		params.put("proNo", proNo);
		params.put("newUnitCode", newUnitCode);
		params.put("jobCode",jobCode);
		params.put("isYearlySalary",isYearlySalary);
		params.put("realSalary",realSalary);
		params.put("gradeSalary",gradeSalary);
		params.put("contractPropertyCode", contractPropertyCode);
		
		params.put("budgetEstimateCode", budgetEstimateCode);
		params.put("versionCode", versionCode);
		
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
	
	/**
	
	  * <p>Title: toVo</p>
	
	  * <p>Description: </p>
	
	  * @return
	
	  * @see com.glens.eap.platform.core.web.ControllerForm#toVo()
	
	  **/
	
	
	@Override
	public ValueObject toVo() {
		// TODO Auto-generated method stub
		
		SalaryFramworkVo vo = (SalaryFramworkVo) super.toVo();
		if (vo.getFrameworkCode() == null 
				|| "".equals(vo.getFrameworkCode())) {
			
			CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
					CodeWorker.SIMPLE_CODE_WORKER);
			vo.setFrameworkCode(codeWorker.createCode("S"));
		}
		
		return vo;
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

	public String getSalarys() {
		return salarys;
	}

	public void setSalarys(String salarys) {
		this.salarys = salarys;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public int getSalaryGradeConfigStatus() {
		return salaryGradeConfigStatus;
	}

	public void setSalaryGradeConfigStatus(int salaryGradeConfigStatus) {
		this.salaryGradeConfigStatus = salaryGradeConfigStatus;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getNewUnitCode() {
		return newUnitCode;
	}

	public void setNewUnitCode(String newUnitCode) {
		this.newUnitCode = newUnitCode;
	}

	public String getContractPropertyCode() {
		return contractPropertyCode;
	}

	public void setContractPropertyCode(String contractPropertyCode) {
		this.contractPropertyCode = contractPropertyCode;
	}

}
