package com.glens.pwCloudOs.addrLib.vo;

import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class AddIndustryCategoryVo implements ValueObject {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column add_industry_category.ROWID
     *
     * @mbggenerated
     */
    private Long rowid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column add_industry_category.COMPANY_CODE
     *
     * @mbggenerated
     */
    private String companyCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column add_industry_category.INDUSTRY_CODE
     *
     * @mbggenerated
     */
    private String industryCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column add_industry_category.INDUSTRY_NAME
     *
     * @mbggenerated
     */
    private String industryName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column add_industry_category.SYS_CREATE_TIME
     *
     * @mbggenerated
     */
    private Date sysCreateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column add_industry_category.SYS_UPDATE_TIME
     *
     * @mbggenerated
     */
    private Date sysUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column add_industry_category.SYS_DELETE_TIME
     *
     * @mbggenerated
     */
    private Date sysDeleteTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column add_industry_category.SYS_PROCESS_FLAG
     *
     * @mbggenerated
     */
    private String sysProcessFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column add_industry_category.REMARKS
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column add_industry_category.ROWID
     *
     * @return the value of add_industry_category.ROWID
     *
     * @mbggenerated
     */
    public Long getRowid() {
        return rowid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column add_industry_category.ROWID
     *
     * @param rowid the value for add_industry_category.ROWID
     *
     * @mbggenerated
     */
    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column add_industry_category.COMPANY_CODE
     *
     * @return the value of add_industry_category.COMPANY_CODE
     *
     * @mbggenerated
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column add_industry_category.COMPANY_CODE
     *
     * @param companyCode the value for add_industry_category.COMPANY_CODE
     *
     * @mbggenerated
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column add_industry_category.INDUSTRY_CODE
     *
     * @return the value of add_industry_category.INDUSTRY_CODE
     *
     * @mbggenerated
     */
    public String getIndustryCode() {
        return industryCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column add_industry_category.INDUSTRY_CODE
     *
     * @param industryCode the value for add_industry_category.INDUSTRY_CODE
     *
     * @mbggenerated
     */
    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode == null ? null : industryCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column add_industry_category.INDUSTRY_NAME
     *
     * @return the value of add_industry_category.INDUSTRY_NAME
     *
     * @mbggenerated
     */
    public String getIndustryName() {
        return industryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column add_industry_category.INDUSTRY_NAME
     *
     * @param industryName the value for add_industry_category.INDUSTRY_NAME
     *
     * @mbggenerated
     */
    public void setIndustryName(String industryName) {
        this.industryName = industryName == null ? null : industryName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column add_industry_category.SYS_CREATE_TIME
     *
     * @return the value of add_industry_category.SYS_CREATE_TIME
     *
     * @mbggenerated
     */
    public Date getSysCreateTime() {
        return sysCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column add_industry_category.SYS_CREATE_TIME
     *
     * @param sysCreateTime the value for add_industry_category.SYS_CREATE_TIME
     *
     * @mbggenerated
     */
    public void setSysCreateTime(Date sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column add_industry_category.SYS_UPDATE_TIME
     *
     * @return the value of add_industry_category.SYS_UPDATE_TIME
     *
     * @mbggenerated
     */
    public Date getSysUpdateTime() {
        return sysUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column add_industry_category.SYS_UPDATE_TIME
     *
     * @param sysUpdateTime the value for add_industry_category.SYS_UPDATE_TIME
     *
     * @mbggenerated
     */
    public void setSysUpdateTime(Date sysUpdateTime) {
        this.sysUpdateTime = sysUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column add_industry_category.SYS_DELETE_TIME
     *
     * @return the value of add_industry_category.SYS_DELETE_TIME
     *
     * @mbggenerated
     */
    public Date getSysDeleteTime() {
        return sysDeleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column add_industry_category.SYS_DELETE_TIME
     *
     * @param sysDeleteTime the value for add_industry_category.SYS_DELETE_TIME
     *
     * @mbggenerated
     */
    public void setSysDeleteTime(Date sysDeleteTime) {
        this.sysDeleteTime = sysDeleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column add_industry_category.SYS_PROCESS_FLAG
     *
     * @return the value of add_industry_category.SYS_PROCESS_FLAG
     *
     * @mbggenerated
     */
    public String getSysProcessFlag() {
        return sysProcessFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column add_industry_category.SYS_PROCESS_FLAG
     *
     * @param sysProcessFlag the value for add_industry_category.SYS_PROCESS_FLAG
     *
     * @mbggenerated
     */
    public void setSysProcessFlag(String sysProcessFlag) {
        this.sysProcessFlag = sysProcessFlag == null ? null : sysProcessFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column add_industry_category.REMARKS
     *
     * @return the value of add_industry_category.REMARKS
     *
     * @mbggenerated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column add_industry_category.REMARKS
     *
     * @param remarks the value for add_industry_category.REMARKS
     *
     * @mbggenerated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}