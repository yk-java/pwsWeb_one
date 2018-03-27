package com.glens.pwCloudOs.task.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class TaskList implements ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3375935474796291260L;

	public static final String STATUS_RUNNING = "1";

	public static final String STATUS_NOT_RUNNING = "0";

	public static final String CONCURRENT_IS = "1";

	public static final String CONCURRENT_NOT = "0";

	private Integer rowid;

	private String jobId;

	private String jobName;

	private String jobGroup;

	private String jobStatus;

	private String isConcurrent;

	private String cronExpression;

	private String description;

	private String springId;

	private String jobClass;

	private String methodName;

	private String startDate;

	private String previousTime;

	private String nextTime;

	public Integer getRowid() {
		return rowid;
	}

	public void setRowid(Integer rowid) {
		this.rowid = rowid;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId == null ? null : jobId.trim();
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName == null ? null : jobName.trim();
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup == null ? null : jobGroup.trim();
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus == null ? null : jobStatus.trim();
	}

	public String getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent == null ? null : isConcurrent.trim();
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression == null ? null : cronExpression
				.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getSpringId() {
		return springId;
	}

	public void setSpringId(String springId) {
		this.springId = springId == null ? null : springId.trim();
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass == null ? null : jobClass.trim();
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName == null ? null : methodName.trim();
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate == null ? null : startDate.trim();
	}

	public String getPreviousTime() {
		return previousTime;
	}

	public void setPreviousTime(String previousTime) {
		this.previousTime = previousTime == null ? null : previousTime.trim();
	}

	public String getNextTime() {
		return nextTime;
	}

	public void setNextTime(String nextTime) {
		this.nextTime = nextTime == null ? null : nextTime.trim();
	}

}