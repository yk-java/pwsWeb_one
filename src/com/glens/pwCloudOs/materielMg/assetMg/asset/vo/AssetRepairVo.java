package com.glens.pwCloudOs.materielMg.assetMg.asset.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class AssetRepairVo implements ValueObject {

	private Long rowid;
	private String assetCode;
	private String assetName;
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
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
	
	
}
