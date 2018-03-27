package com.glens.pwCloudOs.materielMg.assetMg.asset.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;


@ValueObjectProcessor(clazz="com.glens.pwCloudOs.materielMg.assetMg.asset.vo.AssetRepairVo")
public class AssetRepairForm extends ControllerForm {
	
	private Long rowid;
	private String assetCode;
	private String loanEmployeeCode;
	private String loanEmployeeName;
	private String loanUnitCode;
	private String loanUnitName;
	private String loanProNo;
	private String loanProName;
	private String reportDate;
	private String repairDate;
	private String finishDate;
	private String repairReason;
	private float repairCost;
	private String repairReceipt;
	private String repairVender;
	private int repairStatus;
	private int flowStatus;
	private String remarks;
	private String fromDate;
	private String toDate;

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
		this.assetCode = assetCode;
	}

	public String getLoanEmployeeCode() {
		return loanEmployeeCode;
	}

	public void setLoanEmployeeCode(String loanEmployeeCode) {
		this.loanEmployeeCode = loanEmployeeCode;
	}

	public String getLoanEmployeeName() {
		return loanEmployeeName;
	}

	public void setLoanEmployeeName(String loanEmployeeName) {
		this.loanEmployeeName = loanEmployeeName;
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

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getRepairReason() {
		return repairReason;
	}

	public void setRepairReason(String repairReason) {
		this.repairReason = repairReason;
	}

	public float getRepairCost() {
		return repairCost;
	}

	public void setRepairCost(float repairCost) {
		this.repairCost = repairCost;
	}

	public String getRepairReceipt() {
		return repairReceipt;
	}

	public void setRepairReceipt(String repairReceipt) {
		this.repairReceipt = repairReceipt;
	}

	public String getRepairVender() {
		return repairVender;
	}

	public void setRepairVender(String repairVender) {
		this.repairVender = repairVender;
	}

	public int getRepairStatus() {
		return repairStatus;
	}

	public void setRepairStatus(int repairStatus) {
		this.repairStatus = repairStatus;
	}

	public int getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(int flowStatus) {
		this.flowStatus = flowStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("assetCode", assetCode);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		return params;
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
