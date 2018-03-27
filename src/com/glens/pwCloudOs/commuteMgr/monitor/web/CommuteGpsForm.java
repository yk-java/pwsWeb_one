package com.glens.pwCloudOs.commuteMgr.monitor.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.commuteMgr.monitor.vo.CpCommuteGpsVo")
public class CommuteGpsForm extends ControllerForm {

	private String companyCode;
	private String unitCode;
	private String employeeCode;
	private String employeeName;
	private String accountCode;
	private String mobile;
	private String x;
	private String y;
	private String z;
	private String rpTime;
	private String timeDimYear;
	private String timeDimMonth;
	private Integer distance;
	private String beginTime;
	private String endTime;
	private String proNo;
	private String date;
	
	private String curDate;
	
	private String jobCode;
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getZ() {
		return z;
	}
	public void setZ(String z) {
		this.z = z;
	}
	public String getRpTime() {
		return rpTime;
	}
	public void setRpTime(String rpTime) {
		this.rpTime = rpTime;
	}
	public String getTimeDimYear() {
		return timeDimYear;
	}
	public void setTimeDimYear(String timeDimYear) {
		this.timeDimYear = timeDimYear;
	}
	public String getTimeDimMonth() {
		return timeDimMonth;
	}
	public void setTimeDimMonth(String timeDimMonth) {
		this.timeDimMonth = timeDimMonth;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Override
	protected Map doPreToMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountCode", this.getAccountCode());
		map.put("proNo", proNo);
		map.put("companyCode", companyCode);
		map.put("date", "date");
		map.put("employeeName", employeeName);
		map.put("curDate", curDate);
		map.put("jobCode", jobCode);
		
		return map;
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
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCurDate() {
		return curDate;
	}
	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

}
