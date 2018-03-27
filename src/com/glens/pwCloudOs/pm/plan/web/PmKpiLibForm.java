package com.glens.pwCloudOs.pm.plan.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.pm.plan.vo.PmKpiLib")
public class PmKpiLibForm extends ControllerForm {

	private Long rowid;

	private String proNo;

	private String categoryCode;

	private Integer kpiType;

	private String kpiCode;

	private String kpiName;

	private Integer kpiSort;

	private Integer uesAttached;

	private String attachedUnit;

	private Integer mergeFlag;

	private String sysCreateTime;

	private String sysUpdateTime;

	private String sysDeleteTime;

	private String sysProcessFlag;

	private String remarks;

	private Float kpiWeight;

	private Float standard;

	private String kpiUnit;

	private String kpiDesc;

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
		this.categoryCode = categoryCode;
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

	public String getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public String getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(String sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public String getSysDeleteTime() {
		return sysDeleteTime;
	}

	public void setSysDeleteTime(String sysDeleteTime) {
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
		Map map = new HashMap();
		map.put("categoryCode", categoryCode);
		return map;
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

	public Float getKpiWeight() {
		return kpiWeight;
	}

	public void setKpiWeight(Float kpiWeight) {
		this.kpiWeight = kpiWeight;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public Float getStandard() {
		return standard;
	}

	public void setStandard(Float standard) {
		this.standard = standard;
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

}
