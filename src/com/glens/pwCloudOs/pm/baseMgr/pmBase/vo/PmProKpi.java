package com.glens.pwCloudOs.pm.baseMgr.pmBase.vo;

import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class PmProKpi implements ValueObject {

	private Long rowid;

	private String proNo;

	private Integer kpiType;

	private String kpiCode;

	private String kpiName;

	private String kpiSymbol;

	private Float kpiWeight;

	private String kpiUnit;

	private String kpiDesc;

	private Integer kpiSort;

	private Integer uesAttached;

	private String attachedUnit;

	private Integer mergeFlag;

	private Integer dailyInvisibleFlag;

	private Date sysCreateTime;

	private Date sysUpdateTime;

	private Date sysDeleteTime;

	private String sysProcessFlag;

	private String remarks;

	private Float kpiTotal;

	private Float standard;

	private Integer isPersonCnt;

	public Float getKpiTotal() {
		return kpiTotal;
	}

	public void setKpiTotal(Float kpiTotal) {
		this.kpiTotal = kpiTotal;
	}

	public Float getStandard() {
		return standard;
	}

	public void setStandard(Float standard) {
		this.standard = standard;
	}

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo == null ? null : proNo.trim();
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

	public String getKpiSymbol() {
		return kpiSymbol;
	}

	public void setKpiSymbol(String kpiSymbol) {
		this.kpiSymbol = kpiSymbol == null ? null : kpiSymbol.trim();
	}

	public Float getKpiWeight() {
		return kpiWeight;
	}

	public void setKpiWeight(Float kpiWeight) {
		this.kpiWeight = kpiWeight;
	}

	public String getKpiUnit() {
		return kpiUnit;
	}

	public void setKpiUnit(String kpiUnit) {
		this.kpiUnit = kpiUnit == null ? null : kpiUnit.trim();
	}

	public String getKpiDesc() {
		return kpiDesc;
	}

	public void setKpiDesc(String kpiDesc) {
		this.kpiDesc = kpiDesc == null ? null : kpiDesc.trim();
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

	public Integer getDailyInvisibleFlag() {
		return dailyInvisibleFlag;
	}

	public void setDailyInvisibleFlag(Integer dailyInvisibleFlag) {
		this.dailyInvisibleFlag = dailyInvisibleFlag;
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

	public Integer getIsPersonCnt() {
		return isPersonCnt;
	}

	public void setIsPersonCnt(Integer isPersonCnt) {
		this.isPersonCnt = isPersonCnt;
	}
}