package com.glens.pwCloudOs.km.question.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class EsQuestionVo implements ValueObject {

	private Long rowid;

	private String companyCode;

	private String typeName;

	private String typeCode;

	private String questionCode;

	private String questionTitle;

	private String keywords;

	private String questionContent;

	private String answer;

	private String collator;

	private String sovler;

	private String sovleDate;

	private String planSoveDate;

	private String publicStatus;

	private String remarks;

	private String catalogName;

	private String createDate;

	private String proNo;

	private String proName;

	private int status;

	private String checker;

	private String checkOption;

	private String checkerCodes;

	private String alertDate;
	
	private String deptCode;
	
	private String districtManager;
	
	private String proManager;
	
	private String sovlerCode;

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

	public String getCheckerCodes() {
		return checkerCodes;
	}

	public void setCheckerCodes(String checkerCodes) {
		this.checkerCodes = checkerCodes;
	}

	public String getCheckOption() {
		return checkOption;
	}

	public void setCheckOption(String checkOption) {
		this.checkOption = checkOption;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCollator() {
		return collator;
	}

	public void setCollator(String collator) {
		this.collator = collator;
	}

	public String getSovler() {
		return sovler;
	}

	public void setSovler(String sovler) {
		this.sovler = sovler;
	}

	public String getSovleDate() {
		return sovleDate;
	}

	public void setSovleDate(String sovleDate) {
		this.sovleDate = sovleDate;
	}

	public String getPlanSoveDate() {
		return planSoveDate;
	}

	public void setPlanSoveDate(String planSoveDate) {
		this.planSoveDate = planSoveDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getPublicStatus() {
		return publicStatus;
	}

	public void setPublicStatus(String publicStatus) {
		this.publicStatus = publicStatus;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getAlertDate() {
		return alertDate;
	}

	public void setAlertDate(String alertDate) {
		this.alertDate = alertDate;
	}

	public String getSovlerCode() {
		return sovlerCode;
	}

	public void setSovlerCode(String sovlerCode) {
		this.sovlerCode = sovlerCode;
	}

}
