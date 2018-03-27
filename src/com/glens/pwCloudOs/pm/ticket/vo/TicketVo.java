package com.glens.pwCloudOs.pm.ticket.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class TicketVo implements ValueObject {
	
	private Long rowid;
	private String companyCode;
	private String proNo;
	private String proName;
	private String workticketDate;
	private int workticketNumber;
	private String startNo;
	private String endNo;
	private String remarks;
	public Long getRowid() {
		return rowid;
	}
	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getWorkticketDate() {
		return workticketDate;
	}
	public void setWorkticketDate(String workticketDate) {
		this.workticketDate = workticketDate;
	}
	public int getWorkticketNumber() {
		return workticketNumber;
	}
	public void setWorkticketNumber(int workticketNumber) {
		this.workticketNumber = workticketNumber;
	}
	public String getStartNo() {
		return startNo;
	}
	public void setStartNo(String startNo) {
		this.startNo = startNo;
	}
	public String getEndNo() {
		return endNo;
	}
	public void setEndNo(String endNo) {
		this.endNo = endNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
