package com.glens.pwCloudOs.pm.schedulePlan.worklist.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class PmWorkList implements ValueObject {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.ROWID
     *
     * @mbggenerated
     */
    private Long rowid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.PRO_NO
     *
     * @mbggenerated
     */
    private String proNo;
    private String proName;
    

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.WORK_DATE
     *
     * @mbggenerated
     */
    private String workDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.WORKER
     *
     * @mbggenerated
     */
    private String worker;
    private String workerName;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.WORK_TYPE
     *
     * @mbggenerated
     */
    private String workType;
    private String workTypeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.WORKLOAD
     *
     * @mbggenerated
     */
    private Float workload;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.WORK_DESCR
     *
     * @mbggenerated
     */
    private String workDescr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.MARKER
     *
     * @mbggenerated
     */
    private String marker;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.QUALITY_CHECK_USER
     *
     * @mbggenerated
     */
    private String qualityCheckUser;
    private String qualityCheckUserName;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.QUALITY_CHECK_DATE
     *
     * @mbggenerated
     */
    private String qualityCheckDate;
    private String qualityCheckUserJobNo;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.IS_FAULT
     *
     * @mbggenerated
     */
    private Integer isFault;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.FAULT_DESCR
     *
     * @mbggenerated
     */
    private String faultDescr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.SYS_CREATE_TIME
     *
     * @mbggenerated
     */
    private String sysCreateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pm_work_list.SYS_UPDATE_TIME
     *
     * @mbggenerated
     */
    private String sysUpdateTime;

    
    private String categoryCode;
    private String categoryName;
    private String workTypeUnit;
    
    
    public String getQualityCheckUserJobNo() {
		return qualityCheckUserJobNo;
	}

	public void setQualityCheckUserJobNo(String qualityCheckUserJobNo) {
		this.qualityCheckUserJobNo = qualityCheckUserJobNo;
	}

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

	public String getWorkTypeUnit() {
		return workTypeUnit;
	}

	public void setWorkTypeUnit(String workTypeUnit) {
		this.workTypeUnit = workTypeUnit;
	}
    public String getWorkTypeName() {
		return workTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.ROWID
     *
     * @return the value of pm_work_list.ROWID
     *
     * @mbggenerated
     */
    public Long getRowid() {
        return rowid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.ROWID
     *
     * @param rowid the value for pm_work_list.ROWID
     *
     * @mbggenerated
     */
    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.PRO_NO
     *
     * @return the value of pm_work_list.PRO_NO
     *
     * @mbggenerated
     */
    public String getProNo() {
        return proNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.PRO_NO
     *
     * @param proNo the value for pm_work_list.PRO_NO
     *
     * @mbggenerated
     */
    public void setProNo(String proNo) {
        this.proNo = proNo == null ? null : proNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.WORK_DATE
     *
     * @return the value of pm_work_list.WORK_DATE
     *
     * @mbggenerated
     */
    public String getWorkDate() {
        return workDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.WORK_DATE
     *
     * @param workDate the value for pm_work_list.WORK_DATE
     *
     * @mbggenerated
     */
    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }
    
//    public void setWorkDate(String workDate) {
////    	Date date = null;
////    	if(workDate!=null && workDate.length()>0){
////    		try {
////				date = new SimpleDateFormat("yyyy-MM-dd").parse(workDate);
////			} catch (ParseException e) {
////			}
////    	}
//        this.workDate = workDate;
//    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.WORKER
     *
     * @return the value of pm_work_list.WORKER
     *
     * @mbggenerated
     */
    public String getWorker() {
        return worker;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.WORKER
     *
     * @param worker the value for pm_work_list.WORKER
     *
     * @mbggenerated
     */
    public void setWorker(String worker) {
        this.worker = worker == null ? null : worker.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.WORK_TYPE
     *
     * @return the value of pm_work_list.WORK_TYPE
     *
     * @mbggenerated
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.WORK_TYPE
     *
     * @param workType the value for pm_work_list.WORK_TYPE
     *
     * @mbggenerated
     */
    public void setWorkType(String workType) {
        this.workType = workType == null ? null : workType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.WORKLOAD
     *
     * @return the value of pm_work_list.WORKLOAD
     *
     * @mbggenerated
     */
    public Float getWorkload() {
        return workload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.WORKLOAD
     *
     * @param workload the value for pm_work_list.WORKLOAD
     *
     * @mbggenerated
     */
    public void setWorkload(Float workload) {
        this.workload = workload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.WORK_DESCR
     *
     * @return the value of pm_work_list.WORK_DESCR
     *
     * @mbggenerated
     */
    public String getWorkDescr() {
        return workDescr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.WORK_DESCR
     *
     * @param workDescr the value for pm_work_list.WORK_DESCR
     *
     * @mbggenerated
     */
    public void setWorkDescr(String workDescr) {
        this.workDescr = workDescr == null ? null : workDescr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.MARKER
     *
     * @return the value of pm_work_list.MARKER
     *
     * @mbggenerated
     */
    public String getMarker() {
        return marker;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.MARKER
     *
     * @param marker the value for pm_work_list.MARKER
     *
     * @mbggenerated
     */
    public void setMarker(String marker) {
        this.marker = marker == null ? null : marker.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.QUALITY_CHECK_USER
     *
     * @return the value of pm_work_list.QUALITY_CHECK_USER
     *
     * @mbggenerated
     */
    public String getQualityCheckUser() {
        return qualityCheckUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.QUALITY_CHECK_USER
     *
     * @param qualityCheckUser the value for pm_work_list.QUALITY_CHECK_USER
     *
     * @mbggenerated
     */
    public void setQualityCheckUser(String qualityCheckUser) {
        this.qualityCheckUser = qualityCheckUser == null ? null : qualityCheckUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.QUALITY_CHECK_DATE
     *
     * @return the value of pm_work_list.QUALITY_CHECK_DATE
     *
     * @mbggenerated
     */
    public String getQualityCheckDate() {
        return qualityCheckDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.QUALITY_CHECK_DATE
     *
     * @param qualityCheckDate the value for pm_work_list.QUALITY_CHECK_DATE
     *
     * @mbggenerated
     */
    public void setQualityCheckDate(String qualityCheckDate) {
        this.qualityCheckDate = qualityCheckDate;
    }
    
//    public void setQualityCheckDate(String qualityCheckDate) {
//    	Date date = null;
//    	if(qualityCheckDate!=null && qualityCheckDate.length()>0){
//    		try {
//    			date = new SimpleDateFormat("yyyy-MM-dd").parse(qualityCheckDate);
//    		} catch (ParseException e) {
//    		}
//    	}
//    	this.qualityCheckDate = date;
//    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.IS_FAULT
     *
     * @return the value of pm_work_list.IS_FAULT
     *
     * @mbggenerated
     */
    public Integer getIsFault() {
        return isFault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.IS_FAULT
     *
     * @param isFault the value for pm_work_list.IS_FAULT
     *
     * @mbggenerated
     */
    public void setIsFault(Integer isFault) {
        this.isFault = isFault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.FAULT_DESCR
     *
     * @return the value of pm_work_list.FAULT_DESCR
     *
     * @mbggenerated
     */
    public String getFaultDescr() {
        return faultDescr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.FAULT_DESCR
     *
     * @param faultDescr the value for pm_work_list.FAULT_DESCR
     *
     * @mbggenerated
     */
    public void setFaultDescr(String faultDescr) {
        this.faultDescr = faultDescr == null ? null : faultDescr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.SYS_CREATE_TIME
     *
     * @return the value of pm_work_list.SYS_CREATE_TIME
     *
     * @mbggenerated
     */
    public String getSysCreateTime() {
        return sysCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.SYS_CREATE_TIME
     *
     * @param sysCreateTime the value for pm_work_list.SYS_CREATE_TIME
     *
     * @mbggenerated
     */
    public void setSysCreateTime(String sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_work_list.SYS_UPDATE_TIME
     *
     * @return the value of pm_work_list.SYS_UPDATE_TIME
     *
     * @mbggenerated
     */
    public String getSysUpdateTime() {
        return sysUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_work_list.SYS_UPDATE_TIME
     *
     * @param sysUpdateTime the value for pm_work_list.SYS_UPDATE_TIME
     *
     * @mbggenerated
     */
    public void setSysUpdateTime(String sysUpdateTime) {
        this.sysUpdateTime = sysUpdateTime;
    }

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getQualityCheckUserName() {
		return qualityCheckUserName;
	}

	public void setQualityCheckUserName(String qualityCheckUserName) {
		this.qualityCheckUserName = qualityCheckUserName;
	}
    
}