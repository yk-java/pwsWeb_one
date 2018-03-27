package com.glens.pwCloudOs.dailylog.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.dailylog.vo.DailylogVo")
public class DailylogForm extends ControllerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String accountCode;

	private String companyCode;

	private String employeeCode;

	private String employeeName;

	private String fromDate;

	private String logDate;

	private String proNo;

	private String remarks;

	private Long rowid;

	private String searchName;

	private String sysCreateTime;
	private String sysProcessFlag;
	private String toDate;
	private String unitCode;
	private int wiNo;// 工作项目(序号)
	private String wiPlan;// 工作项目（计划执行情况）
	private String wiPro;// 工作项目
	private String wiRecord;// 工作项目（记录）
	private String wiResult;// 工作项目(成果)

	private String saveJSON;

	public String getSaveJSON() {
		return saveJSON;
	}

	public void setSaveJSON(String saveJSON) {
		this.saveJSON = saveJSON;
	}

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Map doPreToMap() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyCode", companyCode);
		params.put("unitCode", unitCode);
		params.put("employeeCode", employeeCode);
		params.put("logDate", logDate);
		params.put("employeeName", employeeName);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);

		params.put("accountCode", accountCode);
		params.put("wiRecord", wiRecord);
		params.put("wiPlan", wiPlan);
		params.put("wiResult", wiResult);
		params.put("wiPro", wiPro);

		params.put("proNo", proNo);
		params.put("searchName", searchName);

		/*
		 * private int wiNo;//工作项目(序号) private String wiPro;//工作项目 private
		 * String wiRecord;//工作项目（记录） private String wiPlan;//工作项目（计划执行情况）
		 * private String wiResult;//工作项目(成果) private String remarks; private
		 * String fromDate; private String toDate;
		 */

		return params;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getFromDate() {
		return fromDate;
	}

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLogDate() {
		return logDate;
	}

	public String getProNo() {
		return proNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public Long getRowid() {
		return rowid;
	}

	public String getSearchName() {
		return searchName;
	}

	public String getSysCreateTime() {
		return sysCreateTime;
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public String getToDate() {
		return toDate;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public int getWiNo() {
		return wiNo;
	}

	public String getWiPlan() {
		return wiPlan;
	}

	public String getWiPro() {
		return wiPro;
	}

	public String getWiRecord() {
		return wiRecord;
	}

	public String getWiResult() {
		return wiResult;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public void setWiNo(int wiNo) {
		this.wiNo = wiNo;
	}

	public void setWiPlan(String wiPlan) {
		this.wiPlan = wiPlan;
	}

	public void setWiPro(String wiPro) {
		this.wiPro = wiPro;
	}

	public void setWiRecord(String wiRecord) {
		this.wiRecord = wiRecord;
	}

	public void setWiResult(String wiResult) {
		this.wiResult = wiResult;
	}

}
