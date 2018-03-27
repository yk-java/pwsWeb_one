package com.glens.pwCloudOs.commuteMgr.monitor.vo;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.glens.eap.platform.core.beans.ValueObject;

@Document(collection = "CP_COMMUTE_GPS")
public class CpCommuteGpsVo implements ValueObject {
	private String companyCode;
	private String unitCode;
	private String employeeCode;
	private String employeeName;
	private String accountCode;
	private String mobile;
	private String x;
	private String y;
	private String z;
	private Date rpTime;
	private String timeDimYear;
	private String timeDimMonth;
	private Integer distance;
	private String proName;
	private String proNo;
	private String jobNo;
	private Integer checkinStatus;
	private Integer checkoutStatus;
	private String rpTimeStr;
	private float stayTime;
	// 是否上传图片
	private Integer isPciture;

	private String jobName;

	private String online;

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

	public Date getRpTime() {
		return rpTime;
	}

	public void setRpTime(Date rpTime) {
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

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public Integer getCheckinStatus() {
		return checkinStatus;
	}

	public void setCheckinStatus(Integer checkinStatus) {
		this.checkinStatus = checkinStatus;
	}

	public Integer getCheckoutStatus() {
		return checkoutStatus;
	}

	public void setCheckoutStatus(Integer checkoutStatus) {
		this.checkoutStatus = checkoutStatus;
	}

	public String getRpTimeStr() {
		return rpTimeStr;
	}

	public void setRpTimeStr(String rpTimeStr) {
		this.rpTimeStr = rpTimeStr;
	}

	public float getStayTime() {
		return stayTime;
	}

	public void setStayTime(float stayTime) {
		this.stayTime = stayTime;
	}

	public Integer getIsPciture() {
		return isPciture;
	}

	public void setIsPciture(Integer isPciture) {
		this.isPciture = isPciture;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

}
