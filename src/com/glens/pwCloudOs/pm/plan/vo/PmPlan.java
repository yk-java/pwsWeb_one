package com.glens.pwCloudOs.pm.plan.vo;

import java.util.HashMap;
import java.util.Map;

import com.glens.eap.platform.core.beans.ValueObject;

public class PmPlan implements ValueObject {

	private static final long serialVersionUID = 1L;

	private Long rowid;
	private Long listNo;

	private String companyCode;

	private String proNo;
	private String proCode;

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

	private Float planWordload;

	private Float allPlanWordload;

	private Float proTotalWordload;

	private Float proPlanProgress;

	private String proManager;
	

	public String getProManager() {
		return proManager;
	}

	public void setProManager(String proManager) {
		this.proManager = proManager;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public Long getListNo() {
		return listNo;
	}

	public void setListNo(Long listNo) {
		this.listNo = listNo;
	}

	public Float getProTotalWordload() {
		return proTotalWordload;
	}

	public void setProTotalWordload(Float proTotalWordload) {
		this.proTotalWordload = proTotalWordload;
	}

	public Float getProPlanProgress() {
		return proPlanProgress;
	}

	public void setProPlanProgress(Float proPlanProgress) {
		this.proPlanProgress = proPlanProgress;
	}

	public Float getAllPlanWordload() {
		return allPlanWordload;
	}

	public void setAllPlanWordload(Float allPlanWordload) {
		this.allPlanWordload = allPlanWordload;
	}

	public Float getPlanWordload() {
		return planWordload;
	}

	public void setPlanWordload(Float planWordload) {
		this.planWordload = planWordload;
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
	private String kpiPlanDesc;
	private Float planComplatePer;
	private Float sumWordload;
	private Integer iwPercnt;
	private Integer owPercnt;

	public Integer getIwPercnt() {
		return iwPercnt;
	}

	public void setIwPercnt(Integer iwPercnt) {
		this.iwPercnt = iwPercnt;
	}

	public Integer getOwPercnt() {
		return owPercnt;
	}

	public void setOwPercnt(Integer owPercnt) {
		this.owPercnt = owPercnt;
	}

	public Float getSumWordload() {
		return sumWordload;
	}

	public void setSumWordload(Float sumWordload) {
		this.sumWordload = sumWordload;
	}

	public Float getPlanComplatePer() {
		return planComplatePer;
	}

	public void setPlanComplatePer(Float planComplatePer) {
		this.planComplatePer = planComplatePer;
	}

	public Map toMap() {
		Map map = new HashMap();
		map.put("rowid", rowid);
		map.put("companyCode", companyCode);
		map.put("proNo", proNo);
		map.put("proName", proName);
		map.put("planType", planType);
		map.put("planNo", planNo);
		map.put("sdate", sdate);
		map.put("edate", edate);
		map.put("planDesc", planDesc);
		map.put("planDate", planDate);
		map.put("feedback", feedback);
		map.put("evaluate", evaluate);
		map.put("sysCreateTime", sysCreateTime);
		map.put("sysUpdateTime", sysUpdateTime);
		map.put("sysDeleteTime", sysDeleteTime);
		map.put("sysProcessFlag", sysProcessFlag);
		map.put("remarks", remarks);
		map.put("kpiData", kpiData);
		map.put("kpiPlanDesc", kpiPlanDesc);
		map.put("feedbackEval", feedbackEval);
		map.put("planCheckScore", planCheckScore);
		map.put("feedbackCheckScore", feedbackCheckScore);
		map.put("planWordload", planWordload);
		map.put("allPlanWordload", allPlanWordload);
		return map;
	}

	public String getKpiPlanDesc() {
		return kpiPlanDesc;
	}

	public void setKpiPlanDesc(String kpiPlanDesc) {
		this.kpiPlanDesc = kpiPlanDesc;
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
		this.companyCode = companyCode == null ? null : companyCode.trim();
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo == null ? null : proNo.trim();
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName == null ? null : proName.trim();
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
		this.planNo = planNo == null ? null : planNo.trim();
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
		this.planDesc = planDesc == null ? null : planDesc.trim();
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
		this.feedback = feedback == null ? null : feedback.trim();
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate == null ? null : evaluate.trim();
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
		this.sysProcessFlag = sysProcessFlag == null ? null : sysProcessFlag
				.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}
}