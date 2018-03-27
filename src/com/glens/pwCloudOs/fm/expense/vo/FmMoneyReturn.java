package com.glens.pwCloudOs.fm.expense.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.glens.eap.platform.core.beans.ValueObject;

public class FmMoneyReturn implements ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4739536312584302572L;

	private Long rowid;

	private String title;

	private String applyDate;

	private String unitCode;

	private String unitName;

	private String employeeName;

	private String proNo;

	private String jobNo;

	private String bankAccount;

	private String applyContent;

	private Float totalMoney;

	private Float returnMoney;

	private String feeType;

	private Float realMoney;

	private String workflowCode;

	private Date sysCreateTime;

	private Date sysUpdateTime;

	private Date sysDeleteTime;

	private String sysProcessFlag;

	private String remarks;

	private String feeName;

	private String status;

	private String claimUser;

	private String claimDate;

	private List<FmMoneyReturnDetail> detailList = new ArrayList<FmMoneyReturnDetail>();

	private List<FmMoneyReturnDetail> data;

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate == null ? null : applyDate.trim();
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo == null ? null : jobNo.trim();
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount == null ? null : bankAccount.trim();
	}

	public String getApplyContent() {
		return applyContent;
	}

	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent == null ? null : applyContent.trim();
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType == null ? null : feeType.trim();
	}

	public String getWorkflowCode() {
		return workflowCode;
	}

	public Float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Float getReturnMoney() {
		return returnMoney;
	}

	public void setReturnMoney(Float returnMoney) {
		this.returnMoney = returnMoney;
	}

	public Float getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(Float realMoney) {
		this.realMoney = realMoney;
	}

	public void setWorkflowCode(String workflowCode) {
		this.workflowCode = workflowCode == null ? null : workflowCode.trim();
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

	public List<FmMoneyReturnDetail> getData() {
		return data;
	}

	public void setData(List<FmMoneyReturnDetail> data) {
		this.data = data;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public List<FmMoneyReturnDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<FmMoneyReturnDetail> detailList) {
		this.detailList = detailList;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getClaimUser() {
		return claimUser;
	}

	public void setClaimUser(String claimUser) {
		this.claimUser = claimUser;
	}

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	@Override
	public String toString() {
		return "FmMoneyReturn [rowid=" + rowid + ", title=" + title
				+ ", applyDate=" + applyDate + ", unitCode=" + unitCode
				+ ", unitName=" + unitName + ", employeeName=" + employeeName
				+ ", proNo=" + proNo + ", jobNo=" + jobNo + ", bankAccount="
				+ bankAccount + ", applyContent=" + applyContent
				+ ", totalMoney=" + totalMoney + ", returnMoney=" + returnMoney
				+ ", feeType=" + feeType + ", realMoney=" + realMoney
				+ ", workflowCode=" + workflowCode + ", sysCreateTime="
				+ sysCreateTime + ", sysUpdateTime=" + sysUpdateTime
				+ ", sysDeleteTime=" + sysDeleteTime + ", sysProcessFlag="
				+ sysProcessFlag + ", remarks=" + remarks + ", feeName="
				+ feeName + ", status=" + status + ", claimUser=" + claimUser
				+ ", claimDate=" + claimDate + ", detailList=" + detailList
				+ ", data=" + data + "]";
	}

}