package com.glens.pwCloudOs.pe.baseMgr.materialUse.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class MaterialUseVo implements ValueObject {

	private Long rowid;
	private String materialBatchno;
	private String materialName;
	private String reserveProno;
	private String remouldBatchcode;//改造批次
	private String reserveOrgcode;//改造单位code
	private String usePerson;
	private int useCount;
	private String useDate;
	private String useDesc;
	private int flowStatus;
	private String remarks;
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getRemouldBatchcode() {
		return remouldBatchcode;
	}
	public void setRemouldBatchcode(String remouldBatchcode) {
		this.remouldBatchcode = remouldBatchcode;
	}
	public String getUsePerson() {
		return usePerson;
	}
	public void setUsePerson(String usePerson) {
		this.usePerson = usePerson;
	}
	public Long getRowid() {
		return rowid;
	}
	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}
	public String getMaterialBatchno() {
		return materialBatchno;
	}
	public void setMaterialBatchno(String materialBatchno) {
		this.materialBatchno = materialBatchno;
	}
	public String getReserveProno() {
		return reserveProno;
	}
	public void setReserveProno(String reserveProno) {
		this.reserveProno = reserveProno;
	}
	public String getReserveOrgcode() {
		return reserveOrgcode;
	}
	public void setReserveOrgcode(String reserveOrgcode) {
		this.reserveOrgcode = reserveOrgcode;
	}
	public int getUseCount() {
		return useCount;
	}
	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}
	public String getUseDate() {
		return useDate;
	}
	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}
	public String getUseDesc() {
		return useDesc;
	}
	public void setUseDesc(String useDesc) {
		this.useDesc = useDesc;
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
