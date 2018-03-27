package com.glens.pwCloudOs.pm.scene.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.pm.scene.vo.PmSceneMonitoring")
public class PmSceneMonitoringForm extends ControllerForm {
	private Long rowid;

	private String companyCode;

	private String proNo;

	private String spotCode;

	private String reportEmployeecode;

	private String reportDate;

	private String catchTime;

	private String cardNo;

	private String startTime;

	private String endTime;

	private float distance;

	private String longitude;

	private String latitude;

	public String getReportEmployeecode() {
		return reportEmployeecode;
	}

	public void setReportEmployeecode(String reportEmployeecode) {
		this.reportEmployeecode = reportEmployeecode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column pm_scene_monitoring.ROWID
	 * 
	 * @return the value of pm_scene_monitoring.ROWID
	 * 
	 * @mbggenerated
	 */
	public Long getRowid() {
		return rowid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column pm_scene_monitoring.ROWID
	 * 
	 * @param rowid
	 *            the value for pm_scene_monitoring.ROWID
	 * 
	 * @mbggenerated
	 */
	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column pm_scene_monitoring.COMPANY_CODE
	 * 
	 * @return the value of pm_scene_monitoring.COMPANY_CODE
	 * 
	 * @mbggenerated
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column pm_scene_monitoring.COMPANY_CODE
	 * 
	 * @param companyCode
	 *            the value for pm_scene_monitoring.COMPANY_CODE
	 * 
	 * @mbggenerated
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode == null ? null : companyCode.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column pm_scene_monitoring.PRO_NO
	 * 
	 * @return the value of pm_scene_monitoring.PRO_NO
	 * 
	 * @mbggenerated
	 */
	public String getProNo() {
		return proNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column pm_scene_monitoring.PRO_NO
	 * 
	 * @param proNo
	 *            the value for pm_scene_monitoring.PRO_NO
	 * 
	 * @mbggenerated
	 */
	public void setProNo(String proNo) {
		this.proNo = proNo == null ? null : proNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column pm_scene_monitoring.REPORT_DATE
	 * 
	 * @return the value of pm_scene_monitoring.REPORT_DATE
	 * 
	 * @mbggenerated
	 */
	public String getReportDate() {
		return reportDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column pm_scene_monitoring.REPORT_DATE
	 * 
	 * @param reportDate
	 *            the value for pm_scene_monitoring.REPORT_DATE
	 * 
	 * @mbggenerated
	 */
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column pm_scene_monitoring.CATCH_COUNT
	 * 
	 * @return the value of pm_scene_monitoring.CATCH_COUNT
	 * 
	 * @mbggenerated
	 */
	public String getCatchTime() {
		return catchTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column pm_scene_monitoring.CATCH_COUNT
	 * 
	 * @param catchCount
	 *            the value for pm_scene_monitoring.CATCH_COUNT
	 * 
	 * @mbggenerated
	 */
	public void setCatchTime(String catchTime) {
		this.catchTime = catchTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column pm_scene_monitoring.CARD_NO
	 * 
	 * @return the value of pm_scene_monitoring.CARD_NO
	 * 
	 * @mbggenerated
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column pm_scene_monitoring.CARD_NO
	 * 
	 * @param cardNo
	 *            the value for pm_scene_monitoring.CARD_NO
	 * 
	 * @mbggenerated
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo == null ? null : cardNo.trim();
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("proNo", proNo);
		params.put("spotCode", spotCode);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("cardNo", cardNo);
		return params;
	}

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getSpotCode() {
		return spotCode;
	}

	public void setSpotCode(String spotCode) {
		this.spotCode = spotCode;
	}

}