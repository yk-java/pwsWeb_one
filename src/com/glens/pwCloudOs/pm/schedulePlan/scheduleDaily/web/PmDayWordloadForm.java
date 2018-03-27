/**
 * @Title: PmDayWordloadForm.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 下午5:31:24
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.vo.PmDayWordloadVo")
public class PmDayWordloadForm extends ControllerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int actualIwPns;

	private Float actualIwWordload;

	private String actualIwWorkDesc;

	private Float actualOwCable;// ACTUAL_OW_CABLE

	private Float actualOwCard;// ACTUAL_OW_CARD

	private Float actualOwLocate;// ACTUAL_OW_LOCATE

	private int actualOwPns;

	private Float actualOwWordload;

	private String actualOwWorkDesc;

	private Float allAccumulativeProgress;// ALL_ACCUMULATIVE_PROGRESS

	private Float allAccumulativeWordload;// ALL_ACCUMULATIVE_WORDLOAD

	private Float allWorkHours;

	private String categoryCode;

	private String companyCode;

	private String fromDate;

	private String kpiData;

	private Float planIwWordload;
	private Float planOwWordload;
	private String proName;
	private String proNo;

	private String proStatus;
	private String remarks;
	private String reportDate;
	private String searchName;

	private String toDate;
	private Float workHours;

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
		params.put("proNo", proNo);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("reportDate", reportDate);
		params.put("categoryCode", categoryCode);
		params.put("proStatus", proStatus);
		params.put("searchName", searchName);

		// 判断是否部门监管角色
		PmDayWordloadController dayWorkloadController = (PmDayWordloadController) getController();
		UserToken token = dayWorkloadController.getToken(request);
		if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE
				.equals(token.getRoleCode())) {

			params.put("deptCode", token.getUnitCode());
		}

		return params;
	}

	public int getActualIwPns() {
		return actualIwPns;
	}

	public Float getActualIwWordload() {
		return actualIwWordload;
	}

	public String getActualIwWorkDesc() {
		return actualIwWorkDesc;
	}

	public Float getActualOwCable() {
		return actualOwCable;
	}

	public Float getActualOwCard() {
		return actualOwCard;
	}

	public Float getActualOwLocate() {
		return actualOwLocate;
	}

	public int getActualOwPns() {
		return actualOwPns;
	}

	public Float getActualOwWordload() {
		return actualOwWordload;
	}

	public String getActualOwWorkDesc() {
		return actualOwWorkDesc;
	}

	public Float getAllAccumulativeProgress() {
		return allAccumulativeProgress;
	}

	public Float getAllAccumulativeWordload() {
		return allAccumulativeWordload;
	}

	public Float getAllWorkHours() {
		return allWorkHours;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getFromDate() {
		return fromDate;
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

	public String getKpiData() {
		return kpiData;
	}

	public Float getPlanIwWordload() {
		return planIwWordload;
	}

	public Float getPlanOwWordload() {
		return planOwWordload;
	}

	public String getProName() {
		return proName;
	}

	public String getProNo() {
		return proNo;
	}

	public String getProStatus() {
		return proStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getReportDate() {
		return reportDate;
	}

	public String getSearchName() {
		return searchName;
	}

	public String getToDate() {
		return toDate;
	}

	public Float getWorkHours() {
		return workHours;
	}

	public void setActualIwPns(int actualIwPns) {
		this.actualIwPns = actualIwPns;
	}

	public void setActualIwWordload(Float actualIwWordload) {
		this.actualIwWordload = actualIwWordload;
	}

	public void setActualIwWorkDesc(String actualIwWorkDesc) {
		this.actualIwWorkDesc = actualIwWorkDesc;
	}

	public void setActualOwCable(Float actualOwCable) {
		this.actualOwCable = actualOwCable;
	}

	public void setActualOwCard(Float actualOwCard) {
		this.actualOwCard = actualOwCard;
	}

	public void setActualOwLocate(Float actualOwLocate) {
		this.actualOwLocate = actualOwLocate;
	}

	public void setActualOwPns(int actualOwPns) {
		this.actualOwPns = actualOwPns;
	}

	public void setActualOwWordload(Float actualOwWordload) {
		this.actualOwWordload = actualOwWordload;
	}

	public void setActualOwWorkDesc(String actualOwWorkDesc) {
		this.actualOwWorkDesc = actualOwWorkDesc;
	}

	public void setAllAccumulativeProgress(Float allAccumulativeProgress) {
		this.allAccumulativeProgress = allAccumulativeProgress;
	}

	public void setAllAccumulativeWordload(Float allAccumulativeWordload) {
		this.allAccumulativeWordload = allAccumulativeWordload;
	}

	public void setAllWorkHours(Float allWorkHours) {
		this.allWorkHours = allWorkHours;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setKpiData(String kpiData) {
		this.kpiData = kpiData;
	}

	public void setPlanIwWordload(Float planIwWordload) {
		this.planIwWordload = planIwWordload;
	}

	public void setPlanOwWordload(Float planOwWordload) {
		this.planOwWordload = planOwWordload;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public void setWorkHours(Float workHours) {
		this.workHours = workHours;
	}

}
