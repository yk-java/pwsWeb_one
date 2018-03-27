/**
 * @Title: PmDayQcForm.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-12 上午10:37:41
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */
@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.pm.schedulePlan.qualityDaily.vo.PmDayQcVo")
public class PmDayQcForm extends ControllerForm {

	
	private String companyCode;
    private String proNo;
    private String proName;
    private String reportDate;
    private String remarks;
   
    private String proManager;
    private String reportStatus;
    private String station;
    private String line;
    private String cablepit;
    private String checkTypeCode;
    private String checkTypeName;
    private String categoryCode; //类别代码
    private String categoryName;
    private String proPhase;
    public String getProPhase() {
		return proPhase;
	}

	public void setProPhase(String proPhase) {
		this.proPhase = proPhase;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	private String employeeCode;
    
    private String checkClassCode;
    private String proStatus;
    private MultipartFile excelFile;
    
    public MultipartFile getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(MultipartFile excelFile) {
		this.excelFile = excelFile;
	}

	public String getProStatus() {
		return proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}

	public String getCheckClassCode() {
		return checkClassCode;
	}

	public void setCheckClassCode(String checkClassCode) {
		this.checkClassCode = checkClassCode;
	}

	private String isDelete;//删除标示
    
    public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getProManager() {
		return proManager;
	}

	public void setProManager(String proManager) {
		this.proManager = proManager;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getCheckTypeName() {
		return checkTypeName;
	}

	public void setCheckTypeName(String checkTypeName) {
		this.checkTypeName = checkTypeName;
	}

	public String getProblemTypeName() {
		return problemTypeName;
	}

	public void setProblemTypeName(String problemTypeName) {
		this.problemTypeName = problemTypeName;
	}

	public String getUnitWorkload() {
		return unitWorkload;
	}

	public void setUnitWorkload(String unitWorkload) {
		this.unitWorkload = unitWorkload;
	}

	private String deviceLperson;
    private String checkName;
    private String checkDate;
    private int checkResult;
	private String problemP1;
    private String problemP2;
    private String problemP3;
    private String problemTypeCode;
    private String problemTypeName;
	private String problemDesc;
    private int  rectifyStatus;
    private String rectifyDate;
    private String rectifyDesc;
    private String unitWorkload;
	private String fromDate;
	private String toDate;	
	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getCablepit() {
		return cablepit;
	}

	public void setCablepit(String cablepit) {
		this.cablepit = cablepit;
	}

	public String getCheckTypeCode() {
		return checkTypeCode;
	}

	public void setCheckTypeCode(String checkTypeCode) {
		this.checkTypeCode = checkTypeCode;
	}

	public String getDeviceLperson() {
		return deviceLperson;
	}

	public void setDeviceLperson(String deviceLperson) {
		this.deviceLperson = deviceLperson;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public int getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(int checkResult) {
		this.checkResult = checkResult;
	}

	public String getProblemP1() {
		return problemP1;
	}

	public void setProblemP1(String problemP1) {
		this.problemP1 = problemP1;
	}

	public String getProblemP2() {
		return problemP2;
	}

	public void setProblemP2(String problemP2) {
		this.problemP2 = problemP2;
	}

	public String getProblemP3() {
		return problemP3;
	}

	public void setProblemP3(String problemP3) {
		this.problemP3 = problemP3;
	}

	public String getProblemTypeCode() {
		return problemTypeCode;
	}

	public void setProblemTypeCode(String problemTypeCode) {
		this.problemTypeCode = problemTypeCode;
	}

	public String getProblemDesc() {
		return problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	public int getRectifyStatus() {
		return rectifyStatus;
	}

	public void setRectifyStatus(int rectifyStatus) {
		this.rectifyStatus = rectifyStatus;
	}

	public String getRectifyDate() {
		return rectifyDate;
	}

	public void setRectifyDate(String rectifyDate) {
		this.rectifyDate = rectifyDate;
	}

	public String getRectifyDesc() {
		return rectifyDesc;
	}

	public void setRectifyDesc(String rectifyDesc) {
		this.rectifyDesc = rectifyDesc;
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
		Map<String, String> params = new HashMap<String, String>();
		params.put("proNo", proNo);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("categoryCode", categoryCode);
		params.put("employeeCode", employeeCode);
		params.put("reportDate", reportDate);
		params.put("isDelete", isDelete);
		params.put("checkDate", checkDate);
		params.put("proStatus", proStatus);
		params.put("proPhase", proPhase);
		params.put("categoryName", categoryName);
		
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

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

}
