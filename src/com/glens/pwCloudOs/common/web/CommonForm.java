/**
 * @Title: CommonForm.java
 * @Package com.glens.pwCloudOs.common.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 下午3:28:57
 * @version V1.0
 */


package com.glens.pwCloudOs.common.web;

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

public class CommonForm extends ControllerForm {

	private String iscm;
	public String getIscm() {
		return iscm;
	}

	public void setIscm(String iscm) {
		this.iscm = iscm;
	}

	private String companyCode;
	
	private String accountCode;
	
	private String categoryCode;
	
	private String assetCharacterCode;
	
	private String employeecode;
	
	private String districtManager;
	
	private String deptCode;
	
	private String deputyManager;//部门副经理
	
	private String deptAuditor;// 部门审核人
	
	private String financeAuditor;//财务审核人
	
	private String generalManager;//总经理
	
	public String getDeputyManager() {
		return deputyManager;
	}

	public void setDeputyManager(String deputyManager) {
		this.deputyManager = deputyManager;
	}

	public String getDeptAuditor() {
		return deptAuditor;
	}

	public void setDeptAuditor(String deptAuditor) {
		this.deptAuditor = deptAuditor;
	}

	public String getFinanceAuditor() {
		return financeAuditor;
	}

	public void setFinanceAuditor(String financeAuditor) {
		this.financeAuditor = financeAuditor;
	}

	public String getGeneralManager() {
		return generalManager;
	}

	public void setGeneralManager(String generalManager) {
		this.generalManager = generalManager;
	}

	private String proManager;
	
	public String getProManager() {
		return proManager;
	}

	public void setProManager(String proManager) {
		this.proManager = proManager;
	}

	public String getEmployeecode() {
		return employeecode;
	}

	public void setEmployeecode(String employeecode) {
		this.employeecode = employeecode;
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
		params.put("iscm", iscm);
		params.put("companyCode", companyCode);
		params.put("accountCode", accountCode);
		params.put("categoryCode", categoryCode);
		params.put("assetCharacterCode", assetCharacterCode);
		params.put("employeecode", employeecode);
		params.put("districtManager", districtManager);
		params.put("deptCode", deptCode);
		params.put("proManager", proManager);
		params.put("deputyManager", deputyManager);
		params.put("deptAuditor", deptAuditor);
		params.put("financeAuditor", financeAuditor);
		params.put("generalManager", generalManager);
		
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

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getAssetCharacterCode() {
		return assetCharacterCode;
	}

	public void setAssetCharacterCode(String assetCharacterCode) {
		this.assetCharacterCode = assetCharacterCode;
	}

	public String getDistrictManager() {
		return districtManager;
	}

	public void setDistrictManager(String districtManager) {
		this.districtManager = districtManager;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

}
