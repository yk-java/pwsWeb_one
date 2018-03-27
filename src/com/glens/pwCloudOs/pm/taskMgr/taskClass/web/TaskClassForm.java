package com.glens.pwCloudOs.pm.taskMgr.taskClass.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;


@ValueObjectProcessor(clazz="com.glens.pwCloudOs.pm.taskMgr.taskClass.vo.TaskClassVo")
public class TaskClassForm extends ControllerForm {

	private Long rowid;
	private String companyCode;
	private String proNo;
	private String proName;
	private String taskClassCode;
	private String taskClassName;
	private int taskType;//任务性质 1 外业 2 内业
	private float taskWordload; //任务工作量
	private String kpiCode;//指标
	private String kpiName;
	private float  kpiWordload;//指标工作量
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	private String categoryCode;
	
	private String remarks;
	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
	
		params.put("taskType", taskType);
		params.put("proNo", proNo);
		params.put("categoryCode", categoryCode);
		
		return params;
	}

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

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
