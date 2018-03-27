/**
 * @Title: WeekJobScheduleForm.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.weekJobSchedule.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-1-19 上午11:09:20
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.schedulePlan.weekJobSchedule.web;

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

public class WeekJobScheduleForm extends ControllerForm {

	private String proNo;
	
	private String categoryCode;
	
	private String companyCode;
	
	private double totalWorkload;
	
	private double iwWeekAccumulativeWorkload;
	
	private double owWeekAccumulativeWorkload;
	
	private double weekAccumulativeWorkload;
	
	private String trendCode;
	
	private String startDate;
	
	private String endDate;
	
	private String workloadDesc;
	
	private String remarks;
	
	private long rowId;
	
	private int curYear;
	
	private String week;
	
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
		params.put("proNo", proNo);
		params.put("categoryCode", categoryCode);
		params.put("companyCode", companyCode);
		params.put("totalWorkload", totalWorkload);
		params.put("iwWeekAccumulativeWorkload", iwWeekAccumulativeWorkload);
		params.put("owWeekAccumulativeWorkload", owWeekAccumulativeWorkload);
		params.put("weekAccumulativeWorkload", weekAccumulativeWorkload);
		params.put("trendCode", trendCode);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("workloadDesc", workloadDesc);
		params.put("remarks", remarks);
		params.put("curYear", curYear);
		params.put("week", week);

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

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public double getTotalWorkload() {
		return totalWorkload;
	}

	public void setTotalWorkload(double totalWorkload) {
		this.totalWorkload = totalWorkload;
	}

	public double getIwWeekAccumulativeWorkload() {
		return iwWeekAccumulativeWorkload;
	}

	public void setIwWeekAccumulativeWorkload(double iwWeekAccumulativeWorkload) {
		this.iwWeekAccumulativeWorkload = iwWeekAccumulativeWorkload;
	}

	public double getOwWeekAccumulativeWorkload() {
		return owWeekAccumulativeWorkload;
	}

	public void setOwWeekAccumulativeWorkload(double owWeekAccumulativeWorkload) {
		this.owWeekAccumulativeWorkload = owWeekAccumulativeWorkload;
	}

	public double getWeekAccumulativeWorkload() {
		return weekAccumulativeWorkload;
	}

	public void setWeekAccumulativeWorkload(double weekAccumulativeWorkload) {
		this.weekAccumulativeWorkload = weekAccumulativeWorkload;
	}

	public String getTrendCode() {
		return trendCode;
	}

	public void setTrendCode(String trendCode) {
		this.trendCode = trendCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getWorkloadDesc() {
		return workloadDesc;
	}

	public void setWorkloadDesc(String workloadDesc) {
		this.workloadDesc = workloadDesc;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getRowId() {
		return rowId;
	}

	public void setRowId(long rowId) {
		this.rowId = rowId;
	}

	public int getCurYear() {
		return curYear;
	}

	public void setCurYear(int curYear) {
		this.curYear = curYear;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

}
