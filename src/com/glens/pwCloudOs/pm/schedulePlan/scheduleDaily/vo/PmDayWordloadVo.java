package com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.vo;

import java.util.Map;

import com.glens.eap.platform.core.beans.ValueObject;

public class PmDayWordloadVo implements ValueObject {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.COMPANY_CODE
     *
     * @mbggenerated
     */
	private Long rowid;
	private Map check;
	
	
    public Map getCheck() {
		return check;
	}

	public void setCheck(Map check) {
		this.check = check;
	}

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	private String companyCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.PRO_NO
     *
     * @mbggenerated
     */
    private String proNo;
    private String proCode;
    private String owSumType;
    private String kpiData;
    private String timeProgress;
    
    

    public String getTimeProgress() {
		return timeProgress;
	}

	public void setTimeProgress(String timeProgress) {
		this.timeProgress = timeProgress;
	}

	public String getKpiData() {
		return kpiData;
	}

	public void setKpiData(String kpiData) {
		this.kpiData = kpiData;
	}

    public String getOwSumType() {
		return owSumType;
	}

	public void setOwSumType(String owSumType) {
		this.owSumType = owSumType;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.PRO_NAME
     *
     * @mbggenerated
     */
    private String proName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.PLAN_PROGRESS
     *
     * @mbggenerated
     */
    private Float planProgress;
    
    private Float planWordload;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.PLAN_IW_WORDLOAD
     *
     * @mbggenerated
     */
    private Float planIwWordload;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.PLAN_OW_WORDLOAD
     *
     * @mbggenerated
     */
    private Float planOwWordload;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.CLONE_PLAN_PROGRESS
     *
     * @mbggenerated
     */
    private Float clonePlanProgress;
    
    private Float clonePlanWordload;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.CLONE_PLAN_IW_WORDLOAD
     *
     * @mbggenerated
     */
    private Float clonePlanIwWordload;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.CLONE_PLAN_OW_WORDLOAD
     *
     * @mbggenerated
     */
    private Float clonePlanOwWordload;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.ACTUAL_IW_WORDLOAD
     *
     * @mbggenerated
     */
    private Float actualIwWordload;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.ACTUAL_OW_WORDLOAD
     *
     * @mbggenerated
     */
    private Float actualOwWordload;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.REMAIN_IW_WORDLOAD
     *
     * @mbggenerated
     */
    private Float remainIwWordload;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.REMAIN_OW_WORDLOAD
     *
     * @mbggenerated
     */
    private Float remainOwWordload;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.DAY_ACCUMULATIVE_PROGRESS
     *
     * @mbggenerated
     */
    private Float dayAccumulativeProgress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.DAY_ACCUMULATIVE_WORDLOAD
     *
     * @mbggenerated
     */
    private Float dayAccumulativeWordload;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.REPORT_DATE
     *
     * @mbggenerated
     */
    private String reportDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_day_wordload.REMARKS
     *
     * @mbggenerated
     */
    private String remarks;
    
    private int totalWordload;
    
    private String proManager;
    
    private String reportStatus;
    
    private float iwProportion;
    
    private float owProportion;
    
    private int actualIwPns;
    
    private int actualOwPns;
    
    private String actualIwWorkDesc;
    
    private String actualOwWorkDesc;
    
    private Float actualOwCable;//ACTUAL_OW_CABLE
    private Float actualOwCard;//ACTUAL_OW_CARD
    private Float actualOwLocate;//ACTUAL_OW_LOCATE
    
    private Float allAccumulativeProgress;//ALL_ACCUMULATIVE_PROGRESS
    private Float allAccumulativeWordload;//ALL_ACCUMULATIVE_WORDLOAD
    
    private Integer proPhase;
    private String sysCreateTime;
    private Float workHours;
    private Float allWorkHours;
    
    
    
    
    public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public Float getAllWorkHours() {
		return allWorkHours;
	}

	public void setAllWorkHours(Float allWorkHours) {
		this.allWorkHours = allWorkHours;
	}

	public Float getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Float workHours) {
		this.workHours = workHours;
	}

	public String getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public Integer getProPhase() {
		return proPhase;
	}

	public void setProPhase(Integer proPhase) {
		this.proPhase = proPhase;
	}

	public Float getActualOwCable() {
		return actualOwCable;
	}

	public void setActualOwCable(Float actualOwCable) {
		this.actualOwCable = actualOwCable;
	}

	public Float getActualOwCard() {
		return actualOwCard;
	}

	public void setActualOwCard(Float actualOwCard) {
		this.actualOwCard = actualOwCard;
	}

	public Float getActualOwLocate() {
		return actualOwLocate;
	}

	public void setActualOwLocate(Float actualOwLocate) {
		this.actualOwLocate = actualOwLocate;
	}

	public Float getAllAccumulativeProgress() {
		return allAccumulativeProgress;
	}

	public void setAllAccumulativeProgress(Float allAccumulativeProgress) {
		this.allAccumulativeProgress = allAccumulativeProgress;
	}

	public Float getAllAccumulativeWordload() {
		return allAccumulativeWordload;
	}

	public void setAllAccumulativeWordload(Float allAccumulativeWordload) {
		this.allAccumulativeWordload = allAccumulativeWordload;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.COMPANY_CODE
     *
     * @return the value of pm_day_wordload.COMPANY_CODE
     *
     * @mbggenerated
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.COMPANY_CODE
     *
     * @param companyCode the value for pm_day_wordload.COMPANY_CODE
     *
     * @mbggenerated
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.PRO_NO
     *
     * @return the value of pm_day_wordload.PRO_NO
     *
     * @mbggenerated
     */
    public String getProNo() {
        return proNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.PRO_NO
     *
     * @param proNo the value for pm_day_wordload.PRO_NO
     *
     * @mbggenerated
     */
    public void setProNo(String proNo) {
        this.proNo = proNo == null ? null : proNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.PRO_NAME
     *
     * @return the value of pm_day_wordload.PRO_NAME
     *
     * @mbggenerated
     */
    public String getProName() {
        return proName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.PRO_NAME
     *
     * @param proName the value for pm_day_wordload.PRO_NAME
     *
     * @mbggenerated
     */
    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.PLAN_PROGRESS
     *
     * @return the value of pm_day_wordload.PLAN_PROGRESS
     *
     * @mbggenerated
     */
    public Float getPlanProgress() {
        return planProgress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.PLAN_PROGRESS
     *
     * @param planProgress the value for pm_day_wordload.PLAN_PROGRESS
     *
     * @mbggenerated
     */
    public void setPlanProgress(Float planProgress) {
        this.planProgress = planProgress;
    }

    public Float getPlanWordload() {
		return planWordload;
	}

	public void setPlanWordload(Float planWordload) {
		this.planWordload = planWordload;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.PLAN_IW_WORDLOAD
     *
     * @return the value of pm_day_wordload.PLAN_IW_WORDLOAD
     *
     * @mbggenerated
     */
    public Float getPlanIwWordload() {
        return planIwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.PLAN_IW_WORDLOAD
     *
     * @param planIwWordload the value for pm_day_wordload.PLAN_IW_WORDLOAD
     *
     * @mbggenerated
     */
    public void setPlanIwWordload(Float planIwWordload) {
        this.planIwWordload = planIwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.PLAN_OW_WORDLOAD
     *
     * @return the value of pm_day_wordload.PLAN_OW_WORDLOAD
     *
     * @mbggenerated
     */
    public Float getPlanOwWordload() {
        return planOwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.PLAN_OW_WORDLOAD
     *
     * @param planOwWordload the value for pm_day_wordload.PLAN_OW_WORDLOAD
     *
     * @mbggenerated
     */
    public void setPlanOwWordload(Float planOwWordload) {
        this.planOwWordload = planOwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.CLONE_PLAN_PROGRESS
     *
     * @return the value of pm_day_wordload.CLONE_PLAN_PROGRESS
     *
     * @mbggenerated
     */
    public Float getClonePlanProgress() {
        return clonePlanProgress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.CLONE_PLAN_PROGRESS
     *
     * @param clonePlanProgress the value for pm_day_wordload.CLONE_PLAN_PROGRESS
     *
     * @mbggenerated
     */
    public void setClonePlanProgress(Float clonePlanProgress) {
        this.clonePlanProgress = clonePlanProgress;
    }

    public Float getClonePlanWordload() {
		return clonePlanWordload;
	}

	public void setClonePlanWordload(Float clonePlanWordload) {
		this.clonePlanWordload = clonePlanWordload;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.CLONE_PLAN_IW_WORDLOAD
     *
     * @return the value of pm_day_wordload.CLONE_PLAN_IW_WORDLOAD
     *
     * @mbggenerated
     */
    public Float getClonePlanIwWordload() {
        return clonePlanIwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.CLONE_PLAN_IW_WORDLOAD
     *
     * @param clonePlanIwWordload the value for pm_day_wordload.CLONE_PLAN_IW_WORDLOAD
     *
     * @mbggenerated
     */
    public void setClonePlanIwWordload(Float clonePlanIwWordload) {
        this.clonePlanIwWordload = clonePlanIwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.CLONE_PLAN_OW_WORDLOAD
     *
     * @return the value of pm_day_wordload.CLONE_PLAN_OW_WORDLOAD
     *
     * @mbggenerated
     */
    public Float getClonePlanOwWordload() {
        return clonePlanOwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.CLONE_PLAN_OW_WORDLOAD
     *
     * @param clonePlanOwWordload the value for pm_day_wordload.CLONE_PLAN_OW_WORDLOAD
     *
     * @mbggenerated
     */
    public void setClonePlanOwWordload(Float clonePlanOwWordload) {
        this.clonePlanOwWordload = clonePlanOwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.ACTUAL_IW_WORDLOAD
     *
     * @return the value of pm_day_wordload.ACTUAL_IW_WORDLOAD
     *
     * @mbggenerated
     */
    public Float getActualIwWordload() {
        return actualIwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.ACTUAL_IW_WORDLOAD
     *
     * @param actualIwWordload the value for pm_day_wordload.ACTUAL_IW_WORDLOAD
     *
     * @mbggenerated
     */
    public void setActualIwWordload(Float actualIwWordload) {
        this.actualIwWordload = actualIwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.ACTUAL_OW_WORDLOAD
     *
     * @return the value of pm_day_wordload.ACTUAL_OW_WORDLOAD
     *
     * @mbggenerated
     */
    public Float getActualOwWordload() {
        return actualOwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.ACTUAL_OW_WORDLOAD
     *
     * @param actualOwWordload the value for pm_day_wordload.ACTUAL_OW_WORDLOAD
     *
     * @mbggenerated
     */
    public void setActualOwWordload(Float actualOwWordload) {
        this.actualOwWordload = actualOwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.REMAIN_IW_WORDLOAD
     *
     * @return the value of pm_day_wordload.REMAIN_IW_WORDLOAD
     *
     * @mbggenerated
     */
    public Float getRemainIwWordload() {
        return remainIwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.REMAIN_IW_WORDLOAD
     *
     * @param remainIwWordload the value for pm_day_wordload.REMAIN_IW_WORDLOAD
     *
     * @mbggenerated
     */
    public void setRemainIwWordload(Float remainIwWordload) {
        this.remainIwWordload = remainIwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.REMAIN_OW_WORDLOAD
     *
     * @return the value of pm_day_wordload.REMAIN_OW_WORDLOAD
     *
     * @mbggenerated
     */
    public Float getRemainOwWordload() {
        return remainOwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.REMAIN_OW_WORDLOAD
     *
     * @param remainOwWordload the value for pm_day_wordload.REMAIN_OW_WORDLOAD
     *
     * @mbggenerated
     */
    public void setRemainOwWordload(Float remainOwWordload) {
        this.remainOwWordload = remainOwWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.DAY_ACCUMULATIVE_PROGRESS
     *
     * @return the value of pm_day_wordload.DAY_ACCUMULATIVE_PROGRESS
     *
     * @mbggenerated
     */
    public Float getDayAccumulativeProgress() {
        return dayAccumulativeProgress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.DAY_ACCUMULATIVE_PROGRESS
     *
     * @param dayAccumulativeProgress the value for pm_day_wordload.DAY_ACCUMULATIVE_PROGRESS
     *
     * @mbggenerated
     */
    public void setDayAccumulativeProgress(Float dayAccumulativeProgress) {
        this.dayAccumulativeProgress = dayAccumulativeProgress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.DAY_ACCUMULATIVE_WORDLOAD
     *
     * @return the value of pm_day_wordload.DAY_ACCUMULATIVE_WORDLOAD
     *
     * @mbggenerated
     */
    public Float getDayAccumulativeWordload() {
        return dayAccumulativeWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.DAY_ACCUMULATIVE_WORDLOAD
     *
     * @param dayAccumulativeWordload the value for pm_day_wordload.DAY_ACCUMULATIVE_WORDLOAD
     *
     * @mbggenerated
     */
    public void setDayAccumulativeWordload(Float dayAccumulativeWordload) {
        this.dayAccumulativeWordload = dayAccumulativeWordload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.REPORT_DATE
     *
     * @return the value of pm_day_wordload.REPORT_DATE
     *
     * @mbggenerated
     */
    public String getReportDate() {
        return reportDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.REPORT_DATE
     *
     * @param reportDate the value for pm_day_wordload.REPORT_DATE
     *
     * @mbggenerated
     */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_day_wordload.REMARKS
     *
     * @return the value of pm_day_wordload.REMARKS
     *
     * @mbggenerated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_day_wordload.REMARKS
     *
     * @param remarks the value for pm_day_wordload.REMARKS
     *
     * @mbggenerated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

	public int getTotalWordload() {
		return totalWordload;
	}

	public void setTotalWordload(int totalWordload) {
		this.totalWordload = totalWordload;
	}

	public String getProManager() {
		return proManager;
	}

	public void setProManager(String proManager) {
		this.proManager = proManager;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public float getIwProportion() {
		return iwProportion;
	}

	public void setIwProportion(float iwProportion) {
		this.iwProportion = iwProportion;
	}

	public float getOwProportion() {
		return owProportion;
	}

	public void setOwProportion(float owProportion) {
		this.owProportion = owProportion;
	}

	public int getActualIwPns() {
		return actualIwPns;
	}

	public void setActualIwPns(int actualIwPns) {
		this.actualIwPns = actualIwPns;
	}

	public int getActualOwPns() {
		return actualOwPns;
	}

	public void setActualOwPns(int actualOwPns) {
		this.actualOwPns = actualOwPns;
	}

	public String getActualIwWorkDesc() {
		return actualIwWorkDesc;
	}

	public void setActualIwWorkDesc(String actualIwWorkDesc) {
		this.actualIwWorkDesc = actualIwWorkDesc;
	}

	public String getActualOwWorkDesc() {
		return actualOwWorkDesc;
	}

	public void setActualOwWorkDesc(String actualOwWorkDesc) {
		this.actualOwWorkDesc = actualOwWorkDesc;
	}

}