/**
 * @Title: AssetForm.java
 * @Package com.glens.pwCloudOs.materielMg.assetMg.asset.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-22 下午3:47:24
 * @version V1.0
 */

package com.glens.pwCloudOs.materielMg.assetMg.asset.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.materielMg.assetMg.asset.vo.AssetVo")
public class AssetForm extends ControllerForm {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.ASSET_QR_CODE
	 * 
	 * @mbggenerated
	 */
	private String assetQrCode;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.ASSET_CODE
	 * 
	 * @mbggenerated
	 */
	private String assetCode;

	private String assetName;
	private String vaEngineNo;
	private String searchName;
	private String fromDate;
	private String endDate;

	private int count;

	private String rentType;// 租用类型 项目 、部门
	private String rentDate; // 租用时间
	private String estimateReturnDate;// 预计归还时间
	private String unitCode1;
	private String unitName1;
	private String employeeName;
	private String employeeCode;

	private String isMark;

	private String markDate;

	private String markOwner;

	private String stockPeriod;
	
	private String stockPeriodName;

	public String getIsMark() {
		return isMark;
	}

	public void setIsMark(String isMark) {
		this.isMark = isMark;
	}

	public String getMarkDate() {
		return markDate;
	}

	public void setMarkDate(String markDate) {
		this.markDate = markDate;
	}

	public String getMarkOwner() {
		return markOwner;
	}

	public void setMarkOwner(String markOwner) {
		this.markOwner = markOwner;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getRentDate() {
		return rentDate;
	}

	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}

	public String getEstimateReturnDate() {
		return estimateReturnDate;
	}

	public void setEstimateReturnDate(String estimateReturnDate) {
		this.estimateReturnDate = estimateReturnDate;
	}

	public String getUnitCode1() {
		return unitCode1;
	}

	public void setUnitCode1(String unitCode1) {
		this.unitCode1 = unitCode1;
	}

	public String getUnitName1() {
		return unitName1;
	}

	public void setUnitName1(String unitName1) {
		this.unitName1 = unitName1;
	}

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getVaEngineNo() {
		return vaEngineNo;
	}

	public void setVaEngineNo(String vaEngineNo) {
		this.vaEngineNo = vaEngineNo;
	}

	public String getVaCarframeNo() {
		return vaCarframeNo;
	}

	public void setVaCarframeNo(String vaCarframeNo) {
		this.vaCarframeNo = vaCarframeNo;
	}

	private String vaCarframeNo;

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.ASSET_TYPE_CODE
	 * 
	 * @mbggenerated
	 */
	private String assetTypeCode;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.PRICE1
	 * 
	 * @mbggenerated
	 */
	private Float price1;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.PRICE2
	 * 
	 * @mbggenerated
	 */
	private Float price2;

	private String brand;
	private String modelNo;
	private String dormCode;

	public String getDormCode() {
		return dormCode;
	}

	public void setDormCode(String dormCode) {
		this.dormCode = dormCode;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getForms() {
		return forms;
	}

	public void setForms(String forms) {
		this.forms = forms;
	}

	private String forms;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.BILL
	 * 
	 * @mbggenerated
	 */
	private String bill;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.DATE_PURCHASE
	 * 
	 * @mbggenerated
	 */
	private String datePurchase;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.DATE_STORAGE
	 * 
	 * @mbggenerated
	 */
	private String dateStorage;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.DATE_DUMP
	 * 
	 * @mbggenerated
	 */
	private String dateDump;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.DUMP_AMOUNT
	 * 
	 * @mbggenerated
	 */
	private Float dumpAmount;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.STATUS
	 * 
	 * @mbggenerated
	 */
	private String status;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.LOAN_EMPLOYEECODE
	 * 
	 * @mbggenerated
	 */
	private String loanEmployeecode;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.LOAN_EMPLOYEENAME
	 * 
	 * @mbggenerated
	 */
	private String loanEmployeename;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.LOAN_UNIT_CODE
	 * 
	 * @mbggenerated
	 */
	private String loanUnitCode;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.LOAN_UNIT_NAME
	 * 
	 * @mbggenerated
	 */
	private String loanUnitName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.LOAN_PRO_NO
	 * 
	 * @mbggenerated
	 */
	private String loanProNo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.LOAN_PRO_NAME
	 * 
	 * @mbggenerated
	 */
	private String loanProName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.LOCATION
	 * 
	 * @mbggenerated
	 */
	private String location;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.VA_NO
	 * 
	 * @mbggenerated
	 */
	private String vaNo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.VA_OIL
	 * 
	 * @mbggenerated
	 */
	private Integer vaOil;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.VA_OIL_CONSUMPTION
	 * 
	 * @mbggenerated
	 */
	private Float vaOilConsumption;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.VA_INIT_MILEAGE
	 * 
	 * @mbggenerated
	 */
	private Integer vaInitMileage;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column gm_asset_base.REMARKS
	 * 
	 * @mbggenerated
	 */
	private String remarks;

	private String assetClassCode;

	private Integer assetMark;

	private String proName;

	private String proNo;

	/**
	 * 
	 * <p>
	 * Title: doPreToMap
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * 
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPreToMap()
	 **/

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("assetClassCode", assetClassCode);
		params.put("assetTypeCode", assetTypeCode);
		params.put("tstatus", status);
		params.put("proName", proName);
		params.put("proNo", proNo);
		params.put("searchName", searchName);
		params.put("fromDate", fromDate);
		params.put("endDate", endDate);
		params.put("loanProNo", loanProNo);
		params.put("loanProName", loanProName);
		params.put("dormCode", dormCode);

		params.put("assetCode", assetCode);
		params.put("loanEmployeecode", loanEmployeecode);
		params.put("loanEmployeename", loanEmployeename);
		params.put("loanUnitCode", loanUnitCode);
		params.put("loanUnitName", loanUnitName);
		params.put("rentDate", rentDate);
		params.put("estimateReturnDate", estimateReturnDate);
		params.put("remarks", remarks);
		params.put("status", status);

		params.put("price1", price1);
		params.put("price2", price2);
		params.put("vaNo", vaNo);
		params.put("isMark", isMark);

		return params;
	}

	/**
	 * 
	 * <p>
	 * Title: doPostRequest
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param request
	 * 
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPostRequest(javax.servlet.http.HttpServletRequest)
	 **/

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * <p>
	 * Title: getGenerateKey
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * 
	 * @see com.glens.eap.platform.core.web.ControllerForm#getGenerateKey()
	 **/

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub

		return null;
	}

	public String getAssetQrCode() {
		return assetQrCode;
	}

	public void setAssetQrCode(String assetQrCode) {
		this.assetQrCode = assetQrCode;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetTypeCode() {
		return assetTypeCode;
	}

	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
	}

	public Float getPrice1() {
		return price1;
	}

	public void setPrice1(Float price1) {
		this.price1 = price1;
	}

	public Float getPrice2() {
		return price2;
	}

	public void setPrice2(Float price2) {
		this.price2 = price2;
	}

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}

	public String getDatePurchase() {
		return datePurchase;
	}

	public void setDatePurchase(String datePurchase) {
		this.datePurchase = datePurchase;
	}

	public String getDateStorage() {
		return dateStorage;
	}

	public void setDateStorage(String dateStorage) {
		this.dateStorage = dateStorage;
	}

	public String getDateDump() {
		return dateDump;
	}

	public void setDateDump(String dateDump) {
		this.dateDump = dateDump;
	}

	public Float getDumpAmount() {
		return dumpAmount;
	}

	public void setDumpAmount(Float dumpAmount) {
		this.dumpAmount = dumpAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoanEmployeecode() {
		return loanEmployeecode;
	}

	public void setLoanEmployeecode(String loanEmployeecode) {
		this.loanEmployeecode = loanEmployeecode;
	}

	public String getLoanEmployeename() {
		return loanEmployeename;
	}

	public void setLoanEmployeename(String loanEmployeename) {
		this.loanEmployeename = loanEmployeename;
	}

	public String getLoanUnitCode() {
		return loanUnitCode;
	}

	public void setLoanUnitCode(String loanUnitCode) {
		this.loanUnitCode = loanUnitCode;
	}

	public String getLoanUnitName() {
		return loanUnitName;
	}

	public void setLoanUnitName(String loanUnitName) {
		this.loanUnitName = loanUnitName;
	}

	public String getLoanProNo() {
		return loanProNo;
	}

	public void setLoanProNo(String loanProNo) {
		this.loanProNo = loanProNo;
	}

	public String getLoanProName() {
		return loanProName;
	}

	public void setLoanProName(String loanProName) {
		this.loanProName = loanProName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getVaNo() {
		return vaNo;
	}

	public void setVaNo(String vaNo) {
		this.vaNo = vaNo;
	}

	public Integer getVaOil() {
		return vaOil;
	}

	public void setVaOil(Integer vaOil) {
		this.vaOil = vaOil;
	}

	public Float getVaOilConsumption() {
		return vaOilConsumption;
	}

	public void setVaOilConsumption(Float vaOilConsumption) {
		this.vaOilConsumption = vaOilConsumption;
	}

	public Integer getVaInitMileage() {
		return vaInitMileage;
	}

	public void setVaInitMileage(Integer vaInitMileage) {
		this.vaInitMileage = vaInitMileage;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAssetClassCode() {
		return assetClassCode;
	}

	public void setAssetClassCode(String assetClassCode) {
		this.assetClassCode = assetClassCode;
	}

	public Integer getAssetMark() {
		return assetMark;
	}

	public void setAssetMark(Integer assetMark) {
		this.assetMark = assetMark;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getStockPeriod() {
		return stockPeriod;
	}

	public void setStockPeriod(String stockPeriod) {
		this.stockPeriod = stockPeriod;
	}

	public String getStockPeriodName() {
		return stockPeriodName;
	}

	public void setStockPeriodName(String stockPeriodName) {
		this.stockPeriodName = stockPeriodName;
	}

}