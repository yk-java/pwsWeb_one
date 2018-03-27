/**
 * @Title: ScheduleSummaryForm.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.scheduleSummary.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-12 下午2:18:14
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.schedulePlan.scheduleSummary.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

public class ScheduleSummaryForm extends ControllerForm {

	private String companyCode;

	private String proNo;
	private String proStatus;

	private String fromDate;

	private String toDate;

	private String searchName;

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

		Map<String, String> params = new HashMap<String, String>();
		params.put("companyCode", companyCode);
		params.put("proNo", proNo);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("proStatus", proStatus);
		params.put("searchName", searchName);

		// 判断是否部门监管角色
		ScheduleSummaryController scheduleSummaryController = (ScheduleSummaryController) getController();
		UserToken token = scheduleSummaryController.getToken(request);
		if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE
				.equals(token.getRoleCode())) {

			params.put("deptCode", token.getUnitCode());
		}

		return params;
	}

	public String getProStatus() {
		return proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
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

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

}
