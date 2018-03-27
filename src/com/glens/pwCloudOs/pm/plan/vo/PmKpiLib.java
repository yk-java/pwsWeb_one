package com.glens.pwCloudOs.pm.plan.vo;

import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class PmKpiLib implements ValueObject {

	private Long rowid;

	private String categoryCode;

	private Integer kpiType;

	private String kpiCode;

	private String kpiName;

	private Integer kpiSort;

	private Integer uesAttached;

	private String attachedUnit;

	private Integer mergeFlag;

	private Date sysCreateTime;

	private Date sysUpdateTime;

	private Date sysDeleteTime;

	private String sysProcessFlag;

	private String remarks;
	private String kpiUnit;
	private String kpiDesc;
	private String itemLabel;
	private int isPerCnt;
	private Float planKpiValue;
	private Float realKpiValue;

	private Float kpiWeight;

	private Float standard;

	public Float getPlanKpiValue() {
		return planKpiValue;
	}

	public void setPlanKpiValue(Float planKpiValue) {
		this.planKpiValue = planKpiValue;
	}

	public Float getRealKpiValue() {
		return realKpiValue;
	}

	public void setRealKpiValue(Float realKpiValue) {
		this.realKpiValue = realKpiValue;
	}

	public String getItemLabel() {
		return itemLabel;
	}

	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}

	public int getIsPerCnt() {
		return isPerCnt;
	}

	public void setIsPerCnt(int isPerCnt) {
		this.isPerCnt = isPerCnt;
	}

	public String getKpiUnit() {
		return kpiUnit;
	}

	public void setKpiUnit(String kpiUnit) {
		this.kpiUnit = kpiUnit;
	}

	public String getKpiDesc() {
		return kpiDesc;
	}

	public void setKpiDesc(String kpiDesc) {
		this.kpiDesc = kpiDesc;
	}

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode == null ? null : categoryCode.trim();
	}

	public Integer getKpiType() {
		return kpiType;
	}

	public void setKpiType(Integer kpiType) {
		this.kpiType = kpiType;
	}

	public String getKpiCode() {
		return kpiCode;
	}

	public void setKpiCode(String kpiCode) {
		this.kpiCode = kpiCode == null ? null : kpiCode.trim();
	}

	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName == null ? null : kpiName.trim();
	}

	public Integer getKpiSort() {
		return kpiSort;
	}

	public void setKpiSort(Integer kpiSort) {
		this.kpiSort = kpiSort;
	}

	public Integer getUesAttached() {
		return uesAttached;
	}

	public void setUesAttached(Integer uesAttached) {
		this.uesAttached = uesAttached;
	}

	public String getAttachedUnit() {
		return attachedUnit;
	}

	public void setAttachedUnit(String attachedUnit) {
		this.attachedUnit = attachedUnit == null ? null : attachedUnit.trim();
	}

	public Integer getMergeFlag() {
		return mergeFlag;
	}

	public void setMergeFlag(Integer mergeFlag) {
		this.mergeFlag = mergeFlag;
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
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

	public Float getKpiWeight() {
		return kpiWeight;
	}

	public void setKpiWeight(Float kpiWeight) {
		this.kpiWeight = kpiWeight;
	}

	public Float getStandard() {
		return standard;
	}

	public void setStandard(Float standard) {
		this.standard = standard;
	}

}