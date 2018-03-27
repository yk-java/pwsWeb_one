package com.glens.pwCloudOs.pm.pb.rollingBudget.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.web.ControllerForm;

public class RollingGudgetForm extends ControllerForm {
	private Long rowid;
	private String companyCode;
	private String proNo;
	private String proName;
	
	private String itemCosts;
	
	private String itemDescs;
	
    private int publishStatus;
    
    private String employeeCode;
    
    private String sectionCode;
    
    private String traceSubsidy;
    
    private String remarks;
    
    private String budgetNo;
    
    private String univalent;
    
    private String workTime;
    
    private String labourCost;
    
    private String proManager;
    
	private String deptCode;
	
	private String districtManager;
	
	private String attrJson;
	
	private String versionCode;
	
	private String budgetCode;
	
	private String contractPropertyCode;
	
	private String jobCode;
	
	private String unitCode;
	
	private int salaryGrade;
	
	private int salaryLevel;
	
	private String budgetStartTime;
    	
    public String getBudgetNo() {
		return budgetNo;
	}

	public void setBudgetNo(String budgetNo) {
		this.budgetNo = budgetNo;
	}
	
	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
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

	public String getContractPropertyCode() {
		return contractPropertyCode;
	}

	public void setContractPropertyCode(String contractPropertyCode) {
		this.contractPropertyCode = contractPropertyCode;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getBudgetCode() {
		return budgetCode;
	}

	public void setBudgetCode(String budgetCode) {
		this.budgetCode = budgetCode;
	}

	public String getAttrJson() {
		return attrJson;
	}

	public void setAttrJson(String attrJson) {
		this.attrJson = attrJson;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDistrictManager() {
		return districtManager;
	}

	public void setDistrictManager(String districtManager) {
		this.districtManager = districtManager;
	}

	public String getProManager() {
		return proManager;
	}

	public void setProManager(String proManager) {
		this.proManager = proManager;
	}

	public String getUnivalent() {
		return univalent;
	}

	public void setUnivalent(String univalent) {
		this.univalent = univalent;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getLabourCost() {
		return labourCost;
	}

	public void setLabourCost(String labourCost) {
		this.labourCost = labourCost;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getTraceSubsidy() {
		return traceSubsidy;
	}

	public void setTraceSubsidy(String traceSubsidy) {
		this.traceSubsidy = traceSubsidy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(int publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getItemCosts() {
		return itemCosts;
	}

	public void setItemCosts(String itemCosts) {
		this.itemCosts = itemCosts;
	}

	public String getItemDescs() {
		return itemDescs;
	}

	public void setItemDescs(String itemDescs) {
		this.itemDescs = itemDescs;
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

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getBudgetStartTime() {
		return budgetStartTime;
	}

	public void setBudgetStartTime(String budgetStartTime) {
		this.budgetStartTime = budgetStartTime;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyCode", companyCode);
		params.put("proNo", proNo);
		params.put("itemDescs", itemDescs);
		params.put("itemCosts", itemCosts);
		params.put("remarks", remarks);
		params.put("publishStatus", publishStatus);
		params.put("traceSubsidy", traceSubsidy);
		params.put("sectionCode", sectionCode);
		params.put("employeeCode", employeeCode);
		params.put("univalent", univalent);
		params.put("workTime", workTime);
		params.put("labourCost", labourCost);
		params.put("proManager", proManager);
		params.put("deptCode", deptCode);
		params.put("districtManager", districtManager);
		params.put("attrJson", attrJson);
		params.put("versionCode", versionCode);
		params.put("budgetCode", budgetCode);
		params.put("contractPropertyCode", contractPropertyCode);
		params.put("jobCode", jobCode);
		params.put("salaryGrade", salaryGrade);
		params.put("salaryLevel", salaryLevel);
		params.put("budgetNo", budgetNo);
		params.put("budgetStartTime", budgetStartTime);
		
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
