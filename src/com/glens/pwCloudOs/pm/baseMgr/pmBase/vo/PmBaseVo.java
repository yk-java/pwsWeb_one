package com.glens.pwCloudOs.pm.baseMgr.pmBase.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class PmBaseVo implements ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyCode;
	private String kpiData;

	public String getKpiData() {
		return kpiData;
	}

	public void setKpiData(String kpiData) {
		this.kpiData = kpiData;
	}

	private String proNo;
	private String proCode;
	private String manageDept;
	private String districtManager;
	private String manageDeptName;
	private String districtManagerName;

	private String proName;

	private String province;

	private String city;

	private String district;

	private Integer proYear;

	private String categoryCode;

	private String categoryName;

	private Integer proStatus;

	private Integer proPhase;

	private String startMetting;

	private String testRun;

	private String change;

	private String applyContent;

	private String applyDate;

	private Integer applyId;

	private String rangeDetailBook;

	private String sampleConfirmFile;

	private String planBook;

	private String estimate;

	private String overSpend;

	private String overSpendMoney;

	public String getPlanBook() {
		return planBook;
	}

	public String getEstimate() {
		return estimate;
	}

	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}

	public String getOverSpend() {
		return overSpend;
	}

	public void setOverSpend(String overSpend) {
		this.overSpend = overSpend;
	}

	public String getOverSpendMoney() {
		return overSpendMoney;
	}

	public void setOverSpendMoney(String overSpendMoney) {
		this.overSpendMoney = overSpendMoney;
	}

	public void setPlanBook(String planBook) {
		this.planBook = planBook;
	}

	public String getSampleConfirmFile() {
		return sampleConfirmFile;
	}

	public void setSampleConfirmFile(String sampleConfirmFile) {
		this.sampleConfirmFile = sampleConfirmFile;
	}

	public String getRangeDetailBook() {
		return rangeDetailBook;
	}

	public void setRangeDetailBook(String rangeDetailBook) {
		this.rangeDetailBook = rangeDetailBook;
	}

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public String getApplyContent() {
		return applyContent;
	}

	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getStartMetting() {
		return startMetting;
	}

	public void setStartMetting(String startMetting) {
		this.startMetting = startMetting;
	}

	public String getTestRun() {
		return testRun;
	}

	public void setTestRun(String testRun) {
		this.testRun = testRun;
	}

	public String getProStatusName() {
		if (proStatus == 1) {
			return "未激活";
		} else if (proStatus == 2) {
			return "已激活";
		} else if (proStatus == 3) {
			return "已关闭";
		}
		return "";
	}

	public String getProPhaseName() {
		if (proPhase == 1) {
			return "前期";
		} else if (proPhase == 2) {
			return "中期";
		} else if (proPhase == 3) {
			return "后期";
		} else if (proPhase == 4) {
			return "更新类";
		} else if (proPhase == 5) {
			return "维护类";
		} else if (proPhase == 6) {
			return "已竣工";
		} else if (proPhase == 7) {
			return "项目暂停";
		}
		return "";
	}

	private String owSumType;

	public String getOwSumType() {
		return owSumType;
	}

	public void setOwSumType(String owSumType) {
		this.owSumType = owSumType;
	}

	private Integer initWorkload;

	private Integer totalWorkload;

	private String unitWorkload;

	private Float iwProportion;

	private Float owProportion;

	private String sdate;

	private String edate;

	private Float ppm;

	private Float ppw;

	private Integer demandStaff;

	private Float qcMin;

	private String proManager;

	private String employeecode;

	private String accountCode;

	private Float tprice;

	private String uprice;

	private Float income;

	private Float factor;

	private String clearform;

	private String remarks;
	private String contractDate;
	private String contractNo;
	private Integer progressMode;
	private String progressModeName;

	public String getProgressModeName() {
		return progressModeName;
	}

	public void setProgressModeName(String progressModeName) {
		this.progressModeName = progressModeName;
	}

	public Integer getProgressMode() {
		return progressMode;
	}

	public void setProgressMode(Integer progressMode) {
		this.progressMode = progressMode;
	}

	private int memberCount;

	private float forecastWorkload;

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getManageDept() {
		return manageDept;
	}

	public void setManageDept(String manageDept) {
		this.manageDept = manageDept;
	}

	public String getDistrictManager() {
		return districtManager;
	}

	public void setDistrictManager(String districtManager) {
		this.districtManager = districtManager;
	}

	public String getManageDeptName() {
		return manageDeptName;
	}

	public void setManageDeptName(String manageDeptName) {
		this.manageDeptName = manageDeptName;
	}

	public String getDistrictManagerName() {
		return districtManagerName;
	}

	public void setDistrictManagerName(String districtManagerName) {
		this.districtManagerName = districtManagerName;
	}

	public String getContractDate() {
		return contractDate;
	}

	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode == null ? null : companyCode.trim();
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo == null ? null : proNo.trim();
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName == null ? null : proName.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district == null ? null : district.trim();
	}

	public Integer getProYear() {
		return proYear;
	}

	public void setProYear(Integer proYear) {
		this.proYear = proYear;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode == null ? null : categoryCode.trim();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getProStatus() {
		return proStatus;
	}

	public void setProStatus(Integer proStatus) {
		this.proStatus = proStatus;
	}

	public Integer getProPhase() {
		return proPhase;
	}

	public void setProPhase(Integer proPhase) {
		this.proPhase = proPhase;
	}

	public Integer getInitWorkload() {
		return initWorkload;
	}

	public void setInitWorkload(Integer initWorkload) {
		this.initWorkload = initWorkload;
	}

	public Integer getTotalWorkload() {
		return totalWorkload;
	}

	public void setTotalWorkload(Integer totalWorkload) {
		this.totalWorkload = totalWorkload;
	}

	public String getUnitWorkload() {
		return unitWorkload;
	}

	public void setUnitWorkload(String unitWorkload) {
		this.unitWorkload = unitWorkload == null ? null : unitWorkload.trim();
	}

	public Float getIwProportion() {
		return iwProportion;
	}

	public void setIwProportion(Float iwProportion) {
		this.iwProportion = iwProportion;
	}

	public Float getOwProportion() {
		return owProportion;
	}

	public void setOwProportion(Float owProportion) {
		this.owProportion = owProportion;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public Float getPpm() {
		return ppm;
	}

	public void setPpm(Float ppm) {
		this.ppm = ppm;
	}

	public Float getPpw() {
		return ppw;
	}

	public void setPpw(Float ppw) {
		this.ppw = ppw;
	}

	public Integer getDemandStaff() {
		return demandStaff;
	}

	public void setDemandStaff(Integer demandStaff) {
		this.demandStaff = demandStaff;
	}

	public Float getQcMin() {
		return qcMin;
	}

	public void setQcMin(Float qcMin) {
		this.qcMin = qcMin;
	}

	public String getProManager() {
		return proManager;
	}

	public void setProManager(String proManager) {
		this.proManager = proManager == null ? null : proManager.trim();
	}

	public String getEmployeecode() {
		return employeecode;
	}

	public void setEmployeecode(String employeecode) {
		this.employeecode = employeecode == null ? null : employeecode.trim();
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode == null ? null : accountCode.trim();
	}

	public Float getTprice() {
		return tprice;
	}

	public void setTprice(Float tprice) {
		this.tprice = tprice;
	}

	public String getUprice() {
		return uprice;
	}

	public void setUprice(String uprice) {
		this.uprice = uprice == null ? null : uprice.trim();
	}

	public Float getIncome() {
		return income;
	}

	public void setIncome(Float income) {
		this.income = income;
	}

	public Float getFactor() {
		return factor;
	}

	public void setFactor(Float factor) {
		this.factor = factor;
	}

	public String getClearform() {
		return clearform;
	}

	public void setClearform(String clearform) {
		this.clearform = clearform == null ? null : clearform.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public float getForecastWorkload() {
		return forecastWorkload;
	}

	public void setForecastWorkload(float forecastWorkload) {
		this.forecastWorkload = forecastWorkload;
	}
}