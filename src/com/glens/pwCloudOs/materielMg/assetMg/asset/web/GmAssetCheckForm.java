package com.glens.pwCloudOs.materielMg.assetMg.asset.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.core.web.ControllerForm;

/**
 * 资 产盘点
 * 
 * @author Administrator
 * 
 */
@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.materielMg.assetMg.asset.vo.GmAssetCheck")
public class GmAssetCheckForm extends ControllerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1119732074850939033L;

	private Long rowid;

	private String assetCode;

	private String checkEmployeeCode;

	private String checkDealDate;

	private String checkEndDate;

	private String loanProNo;

	private String loanProName;

	private String loanUnitCode;

	private String loanUnitName;

	private String loanEmployeecode;

	private String loanEmployeename;

	private String status;

	private String pic1;

	private String pic2;

	private String pic3;

	private Date sysCreateTime;

	private Date sysUpdateTime;

	private Date sysDeleteTime;

	private String sysProcessFlag;

	private String remarks;

	private String searchName;

	private String assetTypeCode;

	private String assetClassCode;

	private String proNo;

	private String fromDate;

	private String toDate;

	private String client;

	private Integer isUse;

	private Integer isPeriod;

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getAssetClassCode() {
		return assetClassCode;
	}

	public void setAssetClassCode(String assetClassCode) {
		this.assetClassCode = assetClassCode;
	}

	public String getAssetTypeCode() {
		return assetTypeCode;
	}

	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode == null ? null : assetCode.trim();
	}

	public String getCheckEmployeeCode() {
		return checkEmployeeCode;
	}

	public void setCheckEmployeeCode(String checkEmployeeCode) {
		this.checkEmployeeCode = checkEmployeeCode == null ? null
				: checkEmployeeCode.trim();
	}

	public String getCheckDealDate() {
		return checkDealDate;
	}

	public void setCheckDealDate(String checkDealDate) {
		this.checkDealDate = checkDealDate == null ? null : checkDealDate
				.trim();
	}

	public String getLoanProNo() {
		return loanProNo;
	}

	public void setLoanProNo(String loanProNo) {
		this.loanProNo = loanProNo == null ? null : loanProNo.trim();
	}

	public String getLoanProName() {
		return loanProName;
	}

	public void setLoanProName(String loanProName) {
		this.loanProName = loanProName == null ? null : loanProName.trim();
	}

	public String getLoanUnitCode() {
		return loanUnitCode;
	}

	public void setLoanUnitCode(String loanUnitCode) {
		this.loanUnitCode = loanUnitCode == null ? null : loanUnitCode.trim();
	}

	public String getLoanUnitName() {
		return loanUnitName;
	}

	public void setLoanUnitName(String loanUnitName) {
		this.loanUnitName = loanUnitName == null ? null : loanUnitName.trim();
	}

	public String getLoanEmployeecode() {
		return loanEmployeecode;
	}

	public void setLoanEmployeecode(String loanEmployeecode) {
		this.loanEmployeecode = loanEmployeecode == null ? null
				: loanEmployeecode.trim();
	}

	public String getLoanEmployeename() {
		return loanEmployeename;
	}

	public void setLoanEmployeename(String loanEmployeename) {
		this.loanEmployeename = loanEmployeename == null ? null
				: loanEmployeename.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1 == null ? null : pic1.trim();
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2 == null ? null : pic2.trim();
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3 == null ? null : pic3.trim();
	}

	public Date getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(Date sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public Date getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public Date getSysDeleteTime() {
		return sysDeleteTime;
	}

	public void setSysDeleteTime(Date sysDeleteTime) {
		this.sysDeleteTime = sysDeleteTime;
	}

	public String getCheckEndDate() {
		return checkEndDate;
	}

	public void setCheckEndDate(String checkEndDate) {
		this.checkEndDate = checkEndDate;
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag == null ? null : sysProcessFlag
				.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("assetClassCode", assetClassCode);
		params.put("assetTypeCode", assetTypeCode);
		params.put("proNo", proNo);
		params.put("searchName", searchName);
		params.put("loanProNo", loanProNo);
		params.put("loanProName", loanProName);

		params.put("assetCode", assetCode);
		params.put("loanEmployeecode", loanEmployeecode);
		params.put("loanEmployeename", loanEmployeename);
		params.put("loanUnitCode", loanUnitCode);
		params.put("loanUnitName", loanUnitName);
		params.put("rowid", rowid);

		params.put("fromDate", StringUtil.isNotNull(fromDate) ? fromDate : "");

		params.put("isUse", isUse);
		params.put("isPeriod", isPeriod);

		return params;
	}

	public Integer getIsPeriod() {
		return isPeriod;
	}

	public void setIsPeriod(Integer isPeriod) {
		this.isPeriod = isPeriod;
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