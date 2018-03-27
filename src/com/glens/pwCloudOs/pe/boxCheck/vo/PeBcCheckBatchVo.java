package com.glens.pwCloudOs.pe.boxCheck.vo;

import java.util.Date;

public class PeBcCheckBatchVo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pe_bc_check_batch.ROWID
     *
     * @mbggenerated
     */
    private Long rowid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pe_bc_check_batch.BATCH_CODE
     *
     * @mbggenerated
     */
    private String batchCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pe_bc_check_batch.BATCH_NAME
     *
     * @mbggenerated
     */
    private String batchName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pe_bc_check_batch.CREATE_TIME
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pe_bc_check_batch.ROWID
     *
     * @return the value of pe_bc_check_batch.ROWID
     *
     * @mbggenerated
     */
    public Long getRowid() {
        return rowid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pe_bc_check_batch.ROWID
     *
     * @param rowid the value for pe_bc_check_batch.ROWID
     *
     * @mbggenerated
     */
    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pe_bc_check_batch.BATCH_CODE
     *
     * @return the value of pe_bc_check_batch.BATCH_CODE
     *
     * @mbggenerated
     */
    public String getBatchCode() {
        return batchCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pe_bc_check_batch.BATCH_CODE
     *
     * @param batchCode the value for pe_bc_check_batch.BATCH_CODE
     *
     * @mbggenerated
     */
    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode == null ? null : batchCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pe_bc_check_batch.BATCH_NAME
     *
     * @return the value of pe_bc_check_batch.BATCH_NAME
     *
     * @mbggenerated
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pe_bc_check_batch.BATCH_NAME
     *
     * @param batchName the value for pe_bc_check_batch.BATCH_NAME
     *
     * @mbggenerated
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName == null ? null : batchName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pe_bc_check_batch.CREATE_TIME
     *
     * @return the value of pe_bc_check_batch.CREATE_TIME
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pe_bc_check_batch.CREATE_TIME
     *
     * @param createTime the value for pe_bc_check_batch.CREATE_TIME
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}