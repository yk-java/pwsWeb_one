package com.glens.pwCloudOs.pm.plan.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.pm.plan.vo.PmPlanKpi")
public class PmPlanKpiForm extends ControllerForm {
	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.rowid
     *
     * @mbggenerated
     */
    private Long rowid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.COMPANY_CODE
     *
     * @mbggenerated
     */
    private String companyCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.PRO_NO
     *
     * @mbggenerated
     */
    private String proNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.PRO_NAME
     *
     * @mbggenerated
     */
    private String proName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.PLAN_NO
     *
     * @mbggenerated
     */
    private String planNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.KPI_CODE
     *
     * @mbggenerated
     */
    private String kpiCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.KPI_VALUE
     *
     * @mbggenerated
     */
    private Float kpiValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.ATTACHED_VALUE
     *
     * @mbggenerated
     */
    private Float attachedValue;
    
    private Float standardHours;
    

    public Float getStandardHours() {
		return standardHours;
	}

	public void setStandardHours(Float standardHours) {
		this.standardHours = standardHours;
	}

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.REPORT_DATE
     *
     * @mbggenerated
     */
    private String reportDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.SYS_CREATE_TIME
     *
     * @mbggenerated
     */
    private String sysCreateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.SYS_UPDATE_TIME
     *
     * @mbggenerated
     */
    private String sysUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.SYS_DELETE_TIME
     *
     * @mbggenerated
     */
    private String sysDeleteTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.SYS_PROCESS_FLAG
     *
     * @mbggenerated
     */
    private String sysProcessFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_plan_kpi.REMARKS
     *
     * @mbggenerated
     */
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

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public String getKpiCode() {
		return kpiCode;
	}

	public void setKpiCode(String kpiCode) {
		this.kpiCode = kpiCode;
	}

	public Float getKpiValue() {
		return kpiValue;
	}

	public void setKpiValue(Float kpiValue) {
		this.kpiValue = kpiValue;
	}

	public Float getAttachedValue() {
		return attachedValue;
	}

	public void setAttachedValue(Float attachedValue) {
		this.attachedValue = attachedValue;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public String getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(String sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public String getSysDeleteTime() {
		return sysDeleteTime;
	}

	public void setSysDeleteTime(String sysDeleteTime) {
		this.sysDeleteTime = sysDeleteTime;
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Map doPreToMap() {
		Map map=new HashMap();
		map.put("proNo", proNo);
		map.put("planNo", planNo);
		map.put("reportDate", reportDate);
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

}