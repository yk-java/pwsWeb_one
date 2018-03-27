/**
 * @Title: ComprehensiveQueryForm.java
 * @Package com.glens.pwCloudOs.materielMg.comprehensiveQuery.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-29 下午4:45:11
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.comprehensiveQuery.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.web.ControllerForm;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class ComprehensiveQueryForm extends ControllerForm {

	private String fromDate;
	
	private String toDate;
	
	private String companyCode;
	
	private String assetCode;
	
	private String assetClassCode;
	
	private String assetTypeCode;
	
	private String areaName;
	
	private String assetCodes;//出库单用 checkbox
	
	private String assetFlag;
	
	private String categoryCode;
	
	public String getAssetCodes() {
		return assetCodes;
	}

	public void setAssetCodes(String assetCodes) {
		this.assetCodes = assetCodes;
	}

	private String proNo;
	private String assetProno;//资产租赁费  项目代码
	private String bedroomProno;//宿舍租赁 项目代码
	private String searchName;
	private String loanUnitCode;
	
	public String getLoanUnitCode() {
		return loanUnitCode;
	}

	public void setLoanUnitCode(String loanUnitCode) {
		this.loanUnitCode = loanUnitCode;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getAssetProno() {
		return assetProno;
	}

	public void setAssetProno(String assetProno) {
		this.assetProno = assetProno;
	}

	

	public String getBedroomProno() {
		return bedroomProno;
	}

	public void setBedroomProno(String bedroomProno) {
		this.bedroomProno = bedroomProno;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	private String bill;
	
	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
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
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("companyCode", companyCode);
		params.put("assetCode", assetCode);
		params.put("assetClassCode", assetClassCode);
		params.put("assetTypeCode", assetTypeCode);
		params.put("areaName", areaName);
		params.put("bill", bill);
		params.put("proNo", proNo);
		params.put("assetProno", assetProno);
		params.put("bedroomProno", bedroomProno);
		params.put("searchName", searchName);
		params.put("assetCodes", assetCodes);
		params.put("loanUnitCode", loanUnitCode);
		params.put("assetFlag", assetFlag);
		params.put("categoryCode", categoryCode);
		
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

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetClassCode() {
		return assetClassCode;
	}

	public void setAssetClassCode(String assetClassCode) {
		this.assetClassCode = assetClassCode;
	}

	public String getAssetTypeCode() {
		return assetTypeCode;
	}

	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAssetFlag() {
		return assetFlag;
	}

	public void setAssetFlag(String assetFlag) {
		this.assetFlag = assetFlag;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

}
