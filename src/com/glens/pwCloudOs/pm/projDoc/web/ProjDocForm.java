/**
 * @Title: ProjDocForm.java
 * @Package com.glens.pwCloudOs.pm.projDoc.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-7-19 下午1:48:30
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.projDoc.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.pm.projDoc.vo.ProjDocVo")
public class ProjDocForm extends ControllerForm {
	
	private Long rowid;

	private String searchName;
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	private String companyCode;
	
	private String proNo;
	private String proStatus;
	
	
	public String getProStatus() {
		return proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}

	private String docNo;
	
	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	private String docTypelibCode;
	
	private Integer docClass = 0;
	
	private String title;
	private String uploadDate;
	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	private MultipartFile projDoc;
	
	private String fromDate;
	private String toDate;
	private float price1;
	private float price2;
	private String contractNo;
	
	private String reVisitid;
   
    private String dlVisitid;
	
    private String accountCode;
	
	private String remarks;
	
	private String isChecked;
	
	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getReVisitid() {
		return reVisitid;
	}

	public void setReVisitid(String reVisitid) {
		this.reVisitid = reVisitid;
	}

	public String getDlVisitid() {
		return dlVisitid;
	}

	public void setDlVisitid(String dlVisitid) {
		this.dlVisitid = dlVisitid;
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

	public float getPrice1() {
		return price1;
	}

	public void setPrice1(float price1) {
		this.price1 = price1;
	}

	public float getPrice2() {
		return price2;
	}

	public void setPrice2(float price2) {
		this.price2 = price2;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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
		params.put("proNo", proNo);
		params.put("docTypelibCode", docTypelibCode);
		params.put("docClass", docClass);
		
		params.put("contractNo", contractNo);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("price1", price1);
		params.put("price2", price2);
		params.put("uploadDate",uploadDate);
		params.put("accountCode",accountCode);
		params.put("searchName", searchName);
		params.put("proStatus", proStatus);
		params.put("isChecked", isChecked);
		
		//判断是否是区域经理
		ProjDocController docController = (ProjDocController) getController();
		UserToken token = docController.getToken(getRequest());
		if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
			
			params.put("districtManager", token.getEmployeeCode());
			
		}
		
		//判断是否部门监管角色
		if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
			
			params.put("deptCode", token.getUnitCode());
		}
		
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

	public String getDocTypelibCode() {
		return docTypelibCode;
	}

	public void setDocTypelibCode(String docTypelibCode) {
		this.docTypelibCode = docTypelibCode;
	}

	public Integer getDocClass() {
		return docClass;
	}

	public void setDocClass(Integer docClass) {
		this.docClass = docClass;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MultipartFile getProjDoc() {
		return projDoc;
	}

	public void setProjDoc(MultipartFile projDoc) {
		this.projDoc = projDoc;
	}

}
