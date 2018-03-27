package com.glens.pwCloudOs.pm.plan.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.pm.plan.vo.PmPlan")
public class PmPlanForm extends ControllerForm {

	private Long rowid;

	private String companyCode;

	private String proNo;

	private String proName;

	private Integer planType;

	private String planNo;

	private String sdate;

	private String edate;

	private String planDesc;

	private String planDate;

	private String feedback;

	private String evaluate;

	private String feedbackEval;

	private float planCheckScore;

	private float feedbackCheckScore;

	private String docTypelibName;

	private String employeeCode;

	private int status;

	private Float planWordload;

	private Float allPlanWordload;

	public Float getPlanWordload() {
		return planWordload;
	}

	public void setPlanWordload(Float planWordload) {
		this.planWordload = planWordload;
	}

	public Float getAllPlanWordload() {
		return allPlanWordload;
	}

	public void setAllPlanWordload(Float allPlanWordload) {
		this.allPlanWordload = allPlanWordload;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDocTypelibName() {
		return docTypelibName;
	}

	public void setDocTypelibName(String docTypelibName) {
		this.docTypelibName = docTypelibName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public float getPlanCheckScore() {
		return planCheckScore;
	}

	public void setPlanCheckScore(float planCheckScore) {
		this.planCheckScore = planCheckScore;
	}

	public float getFeedbackCheckScore() {
		return feedbackCheckScore;
	}

	public void setFeedbackCheckScore(float feedbackCheckScore) {
		this.feedbackCheckScore = feedbackCheckScore;
	}

	public String getFeedbackEval() {
		return feedbackEval;
	}

	public void setFeedbackEval(String feedbackEval) {
		this.feedbackEval = feedbackEval;
	}

	private String sysCreateTime;

	private String sysUpdateTime;

	private String sysDeleteTime;

	private String sysProcessFlag;

	private String remarks;

	private String kpiData;

	private String year;
	private String startTime;
	private String endTime;
	private String isEval;
	private String isFeedback;
	private String isFeedbackEval;

	private String searchName;

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIsEval() {
		return isEval;
	}

	public void setIsEval(String isEval) {
		this.isEval = isEval;
	}

	public String getIsFeedback() {
		return isFeedback;
	}

	public void setIsFeedback(String isFeedback) {
		this.isFeedback = isFeedback;
	}

	public String getIsFeedbackEval() {
		return isFeedbackEval;
	}

	public void setIsFeedbackEval(String isFeedbackEval) {
		this.isFeedbackEval = isFeedbackEval;
	}

	private String accountCode;

	private String proStatus;

	public String getProStatus() {
		return proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getKpiData() {
		return kpiData;
	}

	public void setKpiData(String kpiData) {
		this.kpiData = kpiData;
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

	public Integer getPlanType() {
		return planType;
	}

	public void setPlanType(Integer planType) {
		this.planType = planType;
	}

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getPlanDesc() {
		return planDesc;
	}

	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	public String getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public String getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(String sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public String getSysDeleteTime() {
		return sysDeleteTime;
	}

	public void setSysDeleteTime(String sysDeleteTime) {
		this.sysDeleteTime = sysDeleteTime;
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Map doPreToMap() {
		Map map = new HashMap();
		map.put("proNo", proNo);
		map.put("planType", planType);
		map.put("year", year);
		map.put("accountCode", accountCode);
		map.put("proStatus", proStatus);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("isEval", isEval);
		map.put("isFeedback", isFeedback);
		map.put("isFeedbackEval", isFeedbackEval);
		map.put("planCheckScore", planCheckScore);
		map.put("feedbackCheckScore", feedbackCheckScore);
		map.put("searchName", searchName);
		// 判断是否是区域经理
		PmPlanController planController = (PmPlanController) this.controller;
		UserToken token = planController.getToken(getRequest());
		if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token
				.getRoleCode())) {

			map.put("districtManager", token.getEmployeeCode());

		}

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
