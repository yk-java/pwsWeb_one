package com.glens.pwCloudOs.pe.baseMgr.remouldBatch.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class RemouldBatchVo implements ValueObject {
	
	private Long rowid;
	private String companyCode;
	private String mctViewCode;
	private String deviceTypeCode;
	private String reserveProNo;
	private String reserveProName;
	private String remouldBatchCode;
	private String remouldBatchName;
	private String remouldSdate;
	private String remouldEdate;
	private int remouldCount;
	private String remouldExtent;
	private String remarks;
	public Long getRowid() {
		return rowid;
	}
	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}
	
	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}
	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}
	public String getMctViewCode() {
		return mctViewCode;
	}
	public void setMctViewCode(String mctViewCode) {
		this.mctViewCode = mctViewCode;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getReserveProNo() {
		return reserveProNo;
	}
	public void setReserveProNo(String reserveProNo) {
		this.reserveProNo = reserveProNo;
	}
	public String getReserveProName() {
		return reserveProName;
	}
	public void setReserveProName(String reserveProName) {
		this.reserveProName = reserveProName;
	}
	public String getRemouldBatchCode() {
		return remouldBatchCode;
	}
	public void setRemouldBatchCode(String remouldBatchCode) {
		this.remouldBatchCode = remouldBatchCode;
	}
	public String getRemouldBatchName() {
		return remouldBatchName;
	}
	public void setRemouldBatchName(String remouldBatchName) {
		this.remouldBatchName = remouldBatchName;
	}
	public String getRemouldSdate() {
		return remouldSdate;
	}
	public void setRemouldSdate(String remouldSdate) {
		this.remouldSdate = remouldSdate;
	}
	public String getRemouldEdate() {
		return remouldEdate;
	}
	public void setRemouldEdate(String remouldEdate) {
		this.remouldEdate = remouldEdate;
	}
	public int getRemouldCount() {
		return remouldCount;
	}
	public void setRemouldCount(int remouldCount) {
		this.remouldCount = remouldCount;
	}
	public String getRemouldExtent() {
		return remouldExtent;
	}
	public void setRemouldExtent(String remouldExtent) {
		this.remouldExtent = remouldExtent;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	   

}
