package com.glens.pwCloudOs.pm.everydayMgr.bedMgr.vo;

import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class GmBmBedrentVo implements ValueObject {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.rowid
     *
     * @mbggenerated
     */
    private Long rowid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.DORM_CODE
     *
     * @mbggenerated
     */
    private String dormCode;
    private String dormName;
    

    public String getDormName() {
		return dormName;
	}

	public void setDormName(String dormName) {
		this.dormName = dormName;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.PRO_NO
     *
     * @mbggenerated
     */
    private String proNo;
    private String proName;
    
    public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.EMPLOYEENAME
     *
     * @mbggenerated
     */
    private String employeename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.EMPLOYEECODE
     *
     * @mbggenerated
     */
    private String employeecode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.RENT_DATE
     *
     * @mbggenerated
     */
    private String rentDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.ESTIMATE_RETURN_DATE
     *
     * @mbggenerated
     */
    private String estimateReturnDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.RETURN_DATE
     *
     * @mbggenerated
     */
    private String returnDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.RENT_STSTUS
     *
     * @mbggenerated
     */
    private Integer rentStatus;
    
    private Integer flowStatus;
    

    public Integer getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(Integer flowStatus) {
		this.flowStatus = flowStatus;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.SYS_CREATE_TIME
     *
     * @mbggenerated
     */
    private String sysCreateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.SYS_UPDATE_TIME
     *
     * @mbggenerated
     */
    private String sysUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.SYS_DELETE_TIME
     *
     * @mbggenerated
     */
    private String sysDeleteTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.SYS_PROCESS_FLAG
     *
     * @mbggenerated
     */
    private String sysProcessFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gm_bm_bedrent.REMARKS
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.rowid
     *
     * @return the value of gm_bm_bedrent.rowid
     *
     * @mbggenerated
     */
    public Long getRowid() {
        return rowid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.rowid
     *
     * @param rowid the value for gm_bm_bedrent.rowid
     *
     * @mbggenerated
     */
    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.DORM_CODE
     *
     * @return the value of gm_bm_bedrent.DORM_CODE
     *
     * @mbggenerated
     */
    public String getDormCode() {
        return dormCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.DORM_CODE
     *
     * @param dormCode the value for gm_bm_bedrent.DORM_CODE
     *
     * @mbggenerated
     */
    public void setDormCode(String dormCode) {
        this.dormCode = dormCode == null ? null : dormCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.PRO_NO
     *
     * @return the value of gm_bm_bedrent.PRO_NO
     *
     * @mbggenerated
     */
    public String getProNo() {
        return proNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.PRO_NO
     *
     * @param proNo the value for gm_bm_bedrent.PRO_NO
     *
     * @mbggenerated
     */
    public void setProNo(String proNo) {
        this.proNo = proNo == null ? null : proNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.EMPLOYEENAME
     *
     * @return the value of gm_bm_bedrent.EMPLOYEENAME
     *
     * @mbggenerated
     */
    public String getEmployeename() {
        return employeename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.EMPLOYEENAME
     *
     * @param employeename the value for gm_bm_bedrent.EMPLOYEENAME
     *
     * @mbggenerated
     */
    public void setEmployeename(String employeename) {
        this.employeename = employeename == null ? null : employeename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.EMPLOYEECODE
     *
     * @return the value of gm_bm_bedrent.EMPLOYEECODE
     *
     * @mbggenerated
     */
    public String getEmployeecode() {
        return employeecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.EMPLOYEECODE
     *
     * @param employeecode the value for gm_bm_bedrent.EMPLOYEECODE
     *
     * @mbggenerated
     */
    public void setEmployeecode(String employeecode) {
        this.employeecode = employeecode == null ? null : employeecode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.RENT_DATE
     *
     * @return the value of gm_bm_bedrent.RENT_DATE
     *
     * @mbggenerated
     */
    public String getRentDate() {
        return rentDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.RENT_DATE
     *
     * @param rentDate the value for gm_bm_bedrent.RENT_DATE
     *
     * @mbggenerated
     */
    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.ESTIMATE_RETURN_DATE
     *
     * @return the value of gm_bm_bedrent.ESTIMATE_RETURN_DATE
     *
     * @mbggenerated
     */
    public String getEstimateReturnDate() {
        return estimateReturnDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.ESTIMATE_RETURN_DATE
     *
     * @param estimateReturnDate the value for gm_bm_bedrent.ESTIMATE_RETURN_DATE
     *
     * @mbggenerated
     */
    public void setEstimateReturnDate(String estimateReturnDate) {
        this.estimateReturnDate = estimateReturnDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.RETURN_DATE
     *
     * @return the value of gm_bm_bedrent.RETURN_DATE
     *
     * @mbggenerated
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.RETURN_DATE
     *
     * @param returnDate the value for gm_bm_bedrent.RETURN_DATE
     *
     * @mbggenerated
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.RENT_STSTUS
     *
     * @return the value of gm_bm_bedrent.RENT_STSTUS
     *
     * @mbggenerated
     */
    public Integer getRentStatus() {
        return rentStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.RENT_STSTUS
     *
     * @param rentStstus the value for gm_bm_bedrent.RENT_STSTUS
     *
     * @mbggenerated
     */
    public void setRentStatus(Integer rentStatus) {
        this.rentStatus = rentStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.SYS_CREATE_TIME
     *
     * @return the value of gm_bm_bedrent.SYS_CREATE_TIME
     *
     * @mbggenerated
     */
    public String getSysCreateTime() {
        return sysCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.SYS_CREATE_TIME
     *
     * @param sysCreateTime the value for gm_bm_bedrent.SYS_CREATE_TIME
     *
     * @mbggenerated
     */
    public void setSysCreateTime(String sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.SYS_UPDATE_TIME
     *
     * @return the value of gm_bm_bedrent.SYS_UPDATE_TIME
     *
     * @mbggenerated
     */
    public String getSysUpdateTime() {
        return sysUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.SYS_UPDATE_TIME
     *
     * @param sysUpdateTime the value for gm_bm_bedrent.SYS_UPDATE_TIME
     *
     * @mbggenerated
     */
    public void setSysUpdateTime(String sysUpdateTime) {
        this.sysUpdateTime = sysUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.SYS_DELETE_TIME
     *
     * @return the value of gm_bm_bedrent.SYS_DELETE_TIME
     *
     * @mbggenerated
     */
    public String getSysDeleteTime() {
        return sysDeleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.SYS_DELETE_TIME
     *
     * @param sysDeleteTime the value for gm_bm_bedrent.SYS_DELETE_TIME
     *
     * @mbggenerated
     */
    public void setSysDeleteTime(String sysDeleteTime) {
        this.sysDeleteTime = sysDeleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.SYS_PROCESS_FLAG
     *
     * @return the value of gm_bm_bedrent.SYS_PROCESS_FLAG
     *
     * @mbggenerated
     */
    public String getSysProcessFlag() {
        return sysProcessFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.SYS_PROCESS_FLAG
     *
     * @param sysProcessFlag the value for gm_bm_bedrent.SYS_PROCESS_FLAG
     *
     * @mbggenerated
     */
    public void setSysProcessFlag(String sysProcessFlag) {
        this.sysProcessFlag = sysProcessFlag == null ? null : sysProcessFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gm_bm_bedrent.REMARKS
     *
     * @return the value of gm_bm_bedrent.REMARKS
     *
     * @mbggenerated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gm_bm_bedrent.REMARKS
     *
     * @param remarks the value for gm_bm_bedrent.REMARKS
     *
     * @mbggenerated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}