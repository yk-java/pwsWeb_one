package com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.vo;

import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class PmTmVehicleFuelchargeVo implements ValueObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.rowid
	 * @mbggenerated
	 */
	private Long rowid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.COMPANY_CODE
	 * @mbggenerated
	 */
	private String companyCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.PRO_NO
	 * @mbggenerated
	 */
	private String proNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.PRO_NAME
	 * @mbggenerated
	 */
	private String proName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.ASSET_CODE
	 * @mbggenerated
	 */
	private String assetCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.VEHICLE_NO
	 * @mbggenerated
	 */
	private String vehicleNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.FC_PLACE
	 * @mbggenerated
	 */
	private String fcPlace;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.FC_AMOUNT
	 * @mbggenerated
	 */
	private Integer fcAmount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.FC_DATE
	 * @mbggenerated
	 */
	private String fcDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.FC_VOLUME
	 * @mbggenerated
	 */
	private Float fcVolume;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.CUR_PRICE
	 * @mbggenerated
	 */
	private Float curPrice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.CUR_MILEAGE
	 * @mbggenerated
	 */
	private Integer curMileage;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.SYS_CREATE_TIME
	 * @mbggenerated
	 */
	private String sysCreateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.SYS_UPDATE_TIME
	 * @mbggenerated
	 */
	private String sysUpdateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.SYS_DELETE_TIME
	 * @mbggenerated
	 */
	private String sysDeleteTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.SYS_PROCESS_FLAG
	 * @mbggenerated
	 */
	private String sysProcessFlag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pm_tm_vehicle_fuelcharge.REMARKS
	 * @mbggenerated
	 */
	private String remarks;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.rowid
	 * @return  the value of pm_tm_vehicle_fuelcharge.rowid
	 * @mbggenerated
	 */
	public Long getRowid() {
		return rowid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.rowid
	 * @param rowid  the value for pm_tm_vehicle_fuelcharge.rowid
	 * @mbggenerated
	 */
	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.COMPANY_CODE
	 * @return  the value of pm_tm_vehicle_fuelcharge.COMPANY_CODE
	 * @mbggenerated
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.COMPANY_CODE
	 * @param companyCode  the value for pm_tm_vehicle_fuelcharge.COMPANY_CODE
	 * @mbggenerated
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode == null ? null : companyCode.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.PRO_NO
	 * @return  the value of pm_tm_vehicle_fuelcharge.PRO_NO
	 * @mbggenerated
	 */
	public String getProNo() {
		return proNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.PRO_NO
	 * @param proNo  the value for pm_tm_vehicle_fuelcharge.PRO_NO
	 * @mbggenerated
	 */
	public void setProNo(String proNo) {
		this.proNo = proNo == null ? null : proNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.PRO_NAME
	 * @return  the value of pm_tm_vehicle_fuelcharge.PRO_NAME
	 * @mbggenerated
	 */
	public String getProName() {
		return proName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.PRO_NAME
	 * @param proName  the value for pm_tm_vehicle_fuelcharge.PRO_NAME
	 * @mbggenerated
	 */
	public void setProName(String proName) {
		this.proName = proName == null ? null : proName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.ASSET_CODE
	 * @return  the value of pm_tm_vehicle_fuelcharge.ASSET_CODE
	 * @mbggenerated
	 */
	public String getAssetCode() {
		return assetCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.ASSET_CODE
	 * @param assetCode  the value for pm_tm_vehicle_fuelcharge.ASSET_CODE
	 * @mbggenerated
	 */
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode == null ? null : assetCode.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.VEHICLE_NO
	 * @return  the value of pm_tm_vehicle_fuelcharge.VEHICLE_NO
	 * @mbggenerated
	 */
	public String getVehicleNo() {
		return vehicleNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.VEHICLE_NO
	 * @param vehicleNo  the value for pm_tm_vehicle_fuelcharge.VEHICLE_NO
	 * @mbggenerated
	 */
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo == null ? null : vehicleNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.FC_PLACE
	 * @return  the value of pm_tm_vehicle_fuelcharge.FC_PLACE
	 * @mbggenerated
	 */
	public String getFcPlace() {
		return fcPlace;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.FC_PLACE
	 * @param fcPlace  the value for pm_tm_vehicle_fuelcharge.FC_PLACE
	 * @mbggenerated
	 */
	public void setFcPlace(String fcPlace) {
		this.fcPlace = fcPlace == null ? null : fcPlace.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.FC_AMOUNT
	 * @return  the value of pm_tm_vehicle_fuelcharge.FC_AMOUNT
	 * @mbggenerated
	 */
	public Integer getFcAmount() {
		return fcAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.FC_AMOUNT
	 * @param fcAmount  the value for pm_tm_vehicle_fuelcharge.FC_AMOUNT
	 * @mbggenerated
	 */
	public void setFcAmount(Integer fcAmount) {
		this.fcAmount = fcAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.FC_DATE
	 * @return  the value of pm_tm_vehicle_fuelcharge.FC_DATE
	 * @mbggenerated
	 */
	public String getFcDate() {
		return fcDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.FC_DATE
	 * @param fcDate  the value for pm_tm_vehicle_fuelcharge.FC_DATE
	 * @mbggenerated
	 */
	public void setFcDate(String fcDate) {
		this.fcDate = fcDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.FC_VOLUME
	 * @return  the value of pm_tm_vehicle_fuelcharge.FC_VOLUME
	 * @mbggenerated
	 */
	public Float getFcVolume() {
		return fcVolume;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.FC_VOLUME
	 * @param fcVolume  the value for pm_tm_vehicle_fuelcharge.FC_VOLUME
	 * @mbggenerated
	 */
	public void setFcVolume(Float fcVolume) {
		this.fcVolume = fcVolume;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.CUR_PRICE
	 * @return  the value of pm_tm_vehicle_fuelcharge.CUR_PRICE
	 * @mbggenerated
	 */
	public Float getCurPrice() {
		return curPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.CUR_PRICE
	 * @param curPrice  the value for pm_tm_vehicle_fuelcharge.CUR_PRICE
	 * @mbggenerated
	 */
	public void setCurPrice(Float curPrice) {
		this.curPrice = curPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.CUR_MILEAGE
	 * @return  the value of pm_tm_vehicle_fuelcharge.CUR_MILEAGE
	 * @mbggenerated
	 */
	public Integer getCurMileage() {
		return curMileage;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.CUR_MILEAGE
	 * @param curMileage  the value for pm_tm_vehicle_fuelcharge.CUR_MILEAGE
	 * @mbggenerated
	 */
	public void setCurMileage(Integer curMileage) {
		this.curMileage = curMileage;
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

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.SYS_PROCESS_FLAG
	 * @return  the value of pm_tm_vehicle_fuelcharge.SYS_PROCESS_FLAG
	 * @mbggenerated
	 */
	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.SYS_PROCESS_FLAG
	 * @param sysProcessFlag  the value for pm_tm_vehicle_fuelcharge.SYS_PROCESS_FLAG
	 * @mbggenerated
	 */
	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag == null ? null : sysProcessFlag
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pm_tm_vehicle_fuelcharge.REMARKS
	 * @return  the value of pm_tm_vehicle_fuelcharge.REMARKS
	 * @mbggenerated
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pm_tm_vehicle_fuelcharge.REMARKS
	 * @param remarks  the value for pm_tm_vehicle_fuelcharge.REMARKS
	 * @mbggenerated
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}
}