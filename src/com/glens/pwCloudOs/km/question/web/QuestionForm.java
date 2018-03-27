package com.glens.pwCloudOs.km.question.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.utils.BeanUtilsEx;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.km.question.vo.EsQuestionVo")
public class QuestionForm extends ControllerForm {
	
	private Long rowid;
	
	private String companyCode;
	
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
	
	private int publicStatus;
	
	private int readCnt;
	
	private int downloadCnt;
	
	private String remarks;
	
	private String fromDate;
	
	private String toDate;
	
	private String typeName;
	
	private String employeeName;
	
	private String employeeCode;
	
	private String catalogCode;
	
	private String catalogName;
	
	private String fullTextSearch;
	
	private String proName;
	
	private String proNo;
	
	private String status;
	
	private String createTime;
	
	private String alertTime;
	
	private String sovlerCode;
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(String alertTime) {
		this.alertTime = alertTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String checker;
	
	private String checkerCodes;
	
	private String checkOption;
	

	private String deptCode;
	
	private String districtManager;
	
	private String proManager;
	
	private  String dealCode;
	
	
	

	public String getDealCode() {
		return dealCode;
	}

	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
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


	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getFullTextSearch() {
		return fullTextSearch;
	}

	public void setFullTextSearch(String fullTextSearch) {
		this.fullTextSearch = fullTextSearch;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
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

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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

	public int getPublicStatus() {
		return publicStatus;
	}

	public void setPublicStatus(int publicStatus) {
		this.publicStatus = publicStatus;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public int getDownloadCnt() {
		return downloadCnt;
	}

	public void setDownloadCnt(int downloadCnt) {
		this.downloadCnt = downloadCnt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSovlerCode() {
		return sovlerCode;
	}

	public void setSovlerCode(String sovlerCode) {
		this.sovlerCode = sovlerCode;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rowid", rowid);
		params.put("companyCode", companyCode);
		params.put("typeCode", typeCode);
		params.put("questionCode", questionCode);
		params.put("questionTitle", questionTitle);
		params.put("keywords", keywords);
		params.put("questionContent", questionContent);
		params.put("answer", answer);
		params.put("collator", collator);
		params.put("publicStatus", publicStatus);
		params.put("readCnt", readCnt);
		params.put("downloadCnt", downloadCnt);
		params.put("remarks", remarks);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("sovler", sovler);
		params.put("sovleDate", sovleDate);
		params.put("planSoveDate", planSoveDate);
		params.put("employeeName", employeeName);
		params.put("typeName", typeName);
		params.put("employeeCode", employeeCode);
		params.put("catalogCode", catalogCode);
		params.put("catalogName", catalogName);
		params.put("fullTextSearch", fullTextSearch);
		params.put("proNo", proNo);
		params.put("proName", proName);
		params.put("status", status);
		params.put("checker", checker);
		params.put("checkOption", checkOption);
		params.put("checkerCodes", checkerCodes);
		
		params.put("deptCode", deptCode);
		params.put("districtManager", districtManager);
		params.put("proManager", proManager);
		params.put("dealCode", dealCode);
		params.put("sovlerCode", sovlerCode);
		
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
	
	@Override
	public ValueObject toVo() {
		// TODO Auto-generated method stub
		ValueObject vo = createVo();
		try {
			BeanUtils.copyProperties(vo, this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return vo;
	}

}
