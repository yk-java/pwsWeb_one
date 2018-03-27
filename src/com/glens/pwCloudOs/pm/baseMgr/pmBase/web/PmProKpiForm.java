package com.glens.pwCloudOs.pm.baseMgr.pmBase.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmProKpi")
public class PmProKpiForm extends ControllerForm {

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

	private String categoryCode;

	// 是否同步，1为同步
	private String sync;

	private Integer isPersonCnt;

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
		this.proNo = proNo;
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
		this.kpiCode = kpiCode;
	}

	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}

	public String getKpiSymbol() {
		return kpiSymbol;
	}

	public void setKpiSymbol(String kpiSymbol) {
		this.kpiSymbol = kpiSymbol;
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
		this.kpiUnit = kpiUnit;
	}

	public String getKpiDesc() {
		return kpiDesc;
	}

	public void setKpiDesc(String kpiDesc) {
		this.kpiDesc = kpiDesc;
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
		this.attachedUnit = attachedUnit;
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
		this.sysProcessFlag = sysProcessFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Map doPreToMap() {
		Map parasMap = new HashMap<String, Object>();
		parasMap.put("proNo", proNo);
		return parasMap;
	}

	@Override
	protected void doPostRequest(HttpServletRequest request) {

	}

	@Override
	public Object getGenerateKey() {
		return null;
	}

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

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getSync() {
		return sync;
	}

	public void setSync(String sync) {
		this.sync = sync;
	}

	public Integer getIsPersonCnt() {
		return isPersonCnt;
	}

	public void setIsPersonCnt(Integer isPersonCnt) {
		this.isPersonCnt = isPersonCnt;
	}

}
