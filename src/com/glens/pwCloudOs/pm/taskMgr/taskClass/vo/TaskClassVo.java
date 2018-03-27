package com.glens.pwCloudOs.pm.taskMgr.taskClass.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class TaskClassVo implements ValueObject {
	
	private Long rowid;
	private String companyCode;
	private String proNo;
	private String proName;
	private String categoryCode;
	private String categoryName;
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	private String taskClassCode;
	private String taskClassName;
	private int taskType;//任务性质 1 外业 2 内业
	private float taskWordload; //任务工作量
	private String kpiCode;//指标
	private String kpiName;
	private String kpiUnit;
	public String getKpiUnit() {
		return kpiUnit;
	}
	public void setKpiUnit(String kpiUnit) {
		this.kpiUnit = kpiUnit;
	}
	private float  kpiWordload;//指标工作量
	private String remarks;
	public Long getRowid() {
		return rowid;
	}
	public void setRowid(Long rowid) {
		this.rowid = rowid;
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
	public String getTaskClassCode() {
		return taskClassCode;
	}
	public void setTaskClassCode(String taskClassCode) {
		this.taskClassCode = taskClassCode;
	}
	public String getTaskClassName() {
		return taskClassName;
	}
	public void setTaskClassName(String taskClassName) {
		this.taskClassName = taskClassName;
	}
	public int getTaskType() {
		return taskType;
	}
	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}
	public float getTaskWordload() {
		return taskWordload;
	}
	public void setTaskWordload(float taskWordload) {
		this.taskWordload = taskWordload;
	}
	public String getKpiCode() {
		return kpiCode;
	}
	public void setKpiCode(String kpiCode) {
		this.kpiCode = kpiCode;
	}
	public String getKpiName() {
		return kpiName;
	}
	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}
	public float getKpiWordload() {
		return kpiWordload;
	}
	public void setKpiWordload(float kpiWordload) {
		this.kpiWordload = kpiWordload;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
