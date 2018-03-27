/**
 * @Title: BudgetEstimateForm.java
 * @Package com.glens.pwCloudOs.pm.pb.budgetEstimate.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-2-8 下午2:17:05
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.pb.budgetEstimate.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.web.ControllerForm;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

public class BudgetEstimateForm extends ControllerForm {

	private String areaName;

	private String budgetEstimateCode;

	private String budgetEstimateTitle;

	private String categoryCode;

	private String checker;

	private String checkOption;// 提交调整审核意见

	private String companyCode;

	private String contractPropertyCode;

	private double contractValue;

	private String deptAuditor;// 部门审核人

	private String deptAuditTime; // 部门审核时间

	private String deptCode;

	private String deptOption;// 部门经理审核意见

	private String deputyManager;// 部门副经理

	private String deputyOption;// 部门副经理审核意见

	private double dicisionMarkerBonus;

	private String districtManager;

	// 人力资源
	private String employeeCode;

	// 编制编辑


	/**
	 * 导入excel
	 */
	private MultipartFile excelFile;

	private String financeAuditor;// 财务审核人

	private String financeAuditTime; // 财务审核时间

	private String financeOption;// 财务审核意见

	private String generalManager;// 总经理

	private String generalOption;// 总经理审核意见

	// 各费用
	private String itemCosts;

	private String itemDescs;

	private String jobCode;

	private double labourCost;

	private String managerVal;// 管理费比例

	private String milestone;

	private String month;

	private String nos;

	private double partnerBonus;

	private String phaseCode;

	private String phaseEndDate;

	private String phaseName;

	private int phaseOrder;

	private String phaseStartDate;

	private String pkProDesc;

	private int plevel;

	private String proLevel;

	private String proManager;

	private double proManagerBonus;

	private String proName;

	private String proNo;

	private int proStatus;

	private double proValidorBonus;

	// 其他字段
	private int publishStatus;

	private String releventFund; // phaseCode,accumulateReceipt,incentiveBonus;

	private Long rowid;

	private int salaryGrade;

	private int salaryLevel;

	private String searchName;

	private String sectionCode;

	private String unitCode;

	private double univalent;

	private String userCode;// 当前登录人code

	// 项目概算版本
	private String versionCode;

	private String versionNo;

	private int versionOrder;

	private int versionStatus;

	private float workTime;

	private String year;
	
	
	//概算日志
	
	private String operator;
	
	private String operateTime;
	
	private String operateProNo;
	
	private String operateContent;
	
	private String operateDesc;
	
	
	

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateProNo() {
		return operateProNo;
	}

	public void setOperateProNo(String operateProNo) {
		this.operateProNo = operateProNo;
	}

	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public String getOperateDesc() {
		return operateDesc;
	}

	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
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
		params.put("companyCode", companyCode);
		params.put("proNo", proNo);
		params.put("proName", proName);
		params.put("phaseCode", phaseCode);
		params.put("phaseName", phaseName);
		params.put("milestone", milestone);
		params.put("phaseStartDate", phaseStartDate);
		params.put("phaseEndDate", phaseEndDate);
		params.put("phaseOrder", phaseOrder);
		params.put("categoryCode", categoryCode);
		params.put("proStatus", proStatus);
		params.put("deptCode", deptCode);
		params.put("districtManager", districtManager);
		params.put("proManager", proManager);
		params.put("areaName", areaName);

		// 编辑
		params.put("proManagerBonus", proManagerBonus);
		params.put("proValidorBonus", proValidorBonus);
		params.put("partnerBonus", partnerBonus);
		params.put("dicisionMarkerBonus", dicisionMarkerBonus);
		params.put("releventFund", releventFund);
		params.put("employeeCode", employeeCode);
		params.put("univalent", univalent);
		params.put("workTime", workTime);
		params.put("labourCost", labourCost);
		params.put("itemCosts", itemCosts);
		params.put("itemDescs", itemDescs);
		params.put("publishStatus", publishStatus);

		params.put("unitCode", unitCode);
		params.put("salaryGrade", salaryGrade);
		params.put("salaryLevel", salaryLevel);
		params.put("rowid", rowid);

		params.put("contractValue", contractValue);

		params.put("contractPropertyCode", contractPropertyCode);
		params.put("jobCode", jobCode);

		params.put("nos", nos);

		params.put("proLevel", proLevel);

		params.put("budgetEstimateTitle", budgetEstimateTitle);

		params.put("sectionCode", sectionCode);

		params.put("budgetEstimateCode", budgetEstimateCode);

		params.put("versionCode", versionCode);

		params.put("versionNo", versionNo);

		params.put("versionOrder", versionOrder);

		params.put("versionStatus", versionStatus);

		params.put("deptAuditTime", deptAuditTime);

		params.put("deptAuditor", deptAuditor);

		params.put("financeAuditor", financeAuditor);

		params.put("financeAuditTime", financeAuditTime);
		params.put("pkProDesc", pkProDesc);
		params.put("excelFile", excelFile);
		params.put("year", year);
		params.put("month", month);
		params.put("managerVal", managerVal);
		params.put("deputyOption", deputyOption);
		params.put("deptOption", deptOption);
		params.put("financeOption", financeOption);
		params.put("generalOption", generalOption);
		params.put("userCode", userCode);
		params.put("plevel", plevel);
		params.put("checkOption", checkOption);
		params.put("deputyManager", deputyManager);
		params.put("generalManager", generalManager);

		params.put("searchName", searchName);
		
		params.put("operator", operator);
		params.put("operateTime", operateTime);
		params.put("operateProNo", operateProNo);
		params.put("operateContent", operateContent);
		params.put("operateDesc", operateDesc);
		
		
		
		
		return params;
	}

	public String getAreaName() {
		return areaName;
	}

	public String getBudgetEstimateCode() {
		return budgetEstimateCode;
	}

	public String getBudgetEstimateTitle() {
		return budgetEstimateTitle;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public String getChecker() {
		return checker;
	}

	public String getCheckOption() {
		return checkOption;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getContractPropertyCode() {
		return contractPropertyCode;
	}

	public double getContractValue() {
		return contractValue;
	}

	public String getDeptAuditor() {
		return deptAuditor;
	}

	public String getDeptAuditTime() {
		return deptAuditTime;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public String getDeptOption() {
		return deptOption;
	}

	public String getDeputyManager() {
		return deputyManager;
	}

	public String getDeputyOption() {
		return deputyOption;
	}

	public double getDicisionMarkerBonus() {
		return dicisionMarkerBonus;
	}

	public String getDistrictManager() {
		return districtManager;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public MultipartFile getExcelFile() {
		return excelFile;
	}

	public String getFinanceAuditor() {
		return financeAuditor;
	}

	public String getFinanceAuditTime() {
		return financeAuditTime;
	}

	public String getFinanceOption() {
		return financeOption;
	}

	public String getGeneralManager() {
		return generalManager;
	}

	public String getGeneralOption() {
		return generalOption;
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

	public String getItemCosts() {
		return itemCosts;
	}

	public String getItemDescs() {
		return itemDescs;
	}

	public String getJobCode() {
		return jobCode;
	}

	public double getLabourCost() {
		return labourCost;
	}

	public String getManagerVal() {
		return managerVal;
	}

	public String getMilestone() {
		return milestone;
	}

	public String getMonth() {
		return month;
	}

	public String getNos() {
		return nos;
	}

	public double getPartnerBonus() {
		return partnerBonus;
	}

	public String getPhaseCode() {
		return phaseCode;
	}

	public String getPhaseEndDate() {
		return phaseEndDate;
	}

	public String getPhaseName() {
		return phaseName;
	}

	public int getPhaseOrder() {
		return phaseOrder;
	}

	public String getPhaseStartDate() {
		return phaseStartDate;
	}

	public String getPkProDesc() {
		return pkProDesc;
	}

	public int getPlevel() {
		return plevel;
	}

	public String getProLevel() {
		return proLevel;
	}

	public String getProManager() {
		return proManager;
	}

	public double getProManagerBonus() {
		return proManagerBonus;
	}

	public String getProName() {
		return proName;
	}

	public String getProNo() {
		return proNo;
	}

	public int getProStatus() {
		return proStatus;
	}

	public double getProValidorBonus() {
		return proValidorBonus;
	}

	public int getPublishStatus() {
		return publishStatus;
	}

	public String getReleventFund() {
		return releventFund;
	}

	public Long getRowid() {
		return rowid;
	}

	public int getSalaryGrade() {
		return salaryGrade;
	}

	public int getSalaryLevel() {
		return salaryLevel;
	}

	public String getSearchName() {
		return searchName;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public double getUnivalent() {
		return univalent;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public int getVersionOrder() {
		return versionOrder;
	}

	public int getVersionStatus() {
		return versionStatus;
	}

	public float getWorkTime() {
		return workTime;
	}

	public String getYear() {
		return year;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setBudgetEstimateCode(String budgetEstimateCode) {
		this.budgetEstimateCode = budgetEstimateCode;
	}

	public void setBudgetEstimateTitle(String budgetEstimateTitle) {
		this.budgetEstimateTitle = budgetEstimateTitle;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public void setCheckOption(String checkOption) {
		this.checkOption = checkOption;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public void setContractPropertyCode(String contractPropertyCode) {
		this.contractPropertyCode = contractPropertyCode;
	}

	public void setContractValue(double contractValue) {
		this.contractValue = contractValue;
	}

	public void setDeptAuditor(String deptAuditor) {
		this.deptAuditor = deptAuditor;
	}

	public void setDeptAuditTime(String deptAuditTime) {
		this.deptAuditTime = deptAuditTime;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public void setDeptOption(String deptOption) {
		this.deptOption = deptOption;
	}

	public void setDeputyManager(String deputyManager) {
		this.deputyManager = deputyManager;
	}

	public void setDeputyOption(String deputyOption) {
		this.deputyOption = deputyOption;
	}

	public void setDicisionMarkerBonus(double dicisionMarkerBonus) {
		this.dicisionMarkerBonus = dicisionMarkerBonus;
	}

	public void setDistrictManager(String districtManager) {
		this.districtManager = districtManager;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public void setExcelFile(MultipartFile excelFile) {
		this.excelFile = excelFile;
	}

	public void setFinanceAuditor(String financeAuditor) {
		this.financeAuditor = financeAuditor;
	}

	public void setFinanceAuditTime(String financeAuditTime) {
		this.financeAuditTime = financeAuditTime;
	}

	public void setFinanceOption(String financeOption) {
		this.financeOption = financeOption;
	}

	public void setGeneralManager(String generalManager) {
		this.generalManager = generalManager;
	}

	public void setGeneralOption(String generalOption) {
		this.generalOption = generalOption;
	}

	public void setItemCosts(String itemCosts) {
		this.itemCosts = itemCosts;
	}

	public void setItemDescs(String itemDescs) {
		this.itemDescs = itemDescs;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public void setLabourCost(double labourCost) {
		this.labourCost = labourCost;
	}

	public void setManagerVal(String managerVal) {
		this.managerVal = managerVal;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setNos(String nos) {
		this.nos = nos;
	}

	public void setPartnerBonus(double partnerBonus) {
		this.partnerBonus = partnerBonus;
	}

	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}

	public void setPhaseEndDate(String phaseEndDate) {
		this.phaseEndDate = phaseEndDate;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

	public void setPhaseOrder(int phaseOrder) {
		this.phaseOrder = phaseOrder;
	}

	public void setPhaseStartDate(String phaseStartDate) {
		this.phaseStartDate = phaseStartDate;
	}

	public void setPkProDesc(String pkProDesc) {
		this.pkProDesc = pkProDesc;
	}

	public void setPlevel(int plevel) {
		this.plevel = plevel;
	}

	public void setProLevel(String proLevel) {
		this.proLevel = proLevel;
	}

	public void setProManager(String proManager) {
		this.proManager = proManager;
	}

	public void setProManagerBonus(double proManagerBonus) {
		this.proManagerBonus = proManagerBonus;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public void setProStatus(int proStatus) {
		this.proStatus = proStatus;
	}

	public void setProValidorBonus(double proValidorBonus) {
		this.proValidorBonus = proValidorBonus;
	}

	public void setPublishStatus(int publishStatus) {
		this.publishStatus = publishStatus;
	}

	public void setReleventFund(String releventFund) {
		this.releventFund = releventFund;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public void setSalaryGrade(int salaryGrade) {
		this.salaryGrade = salaryGrade;
	}

	public void setSalaryLevel(int salaryLevel) {
		this.salaryLevel = salaryLevel;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public void setUnivalent(double univalent) {
		this.univalent = univalent;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public void setVersionOrder(int versionOrder) {
		this.versionOrder = versionOrder;
	}

	public void setVersionStatus(int versionStatus) {
		this.versionStatus = versionStatus;
	}

	public void setWorkTime(float workTime) {
		this.workTime = workTime;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
