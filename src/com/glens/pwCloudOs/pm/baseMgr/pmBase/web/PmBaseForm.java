/**
 * @Title: PmBaseForm.java
 * @Package com.glens.pwCloudOs.pm.baseMgr.pmBase.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 上午10:49:15
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.baseMgr.pmBase.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmBaseVo")
public class PmBaseForm extends ControllerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String type;

	private String kpiData;
	private String companyCode;

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

	private Integer proStatus;

	private Integer proPhase;

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

	private String inDate;
	private String workStatue;

	private String docClass;
	private String contractDate;
	private String contractNo;

	private int tempstatus;

	private String proStepName1;
	private String proPhaseName;
	private String deptCode;
	private Integer progressMode;

	private String searchName;

	private String startMetting;

	private String testRun;

	private String change;

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

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public Integer getProgressMode() {
		return progressMode;
	}

	public void setProgressMode(Integer progressMode) {
		this.progressMode = progressMode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getProPhaseName() {
		return proPhaseName;
	}

	public void setProPhaseName(String proPhaseName) {
		this.proPhaseName = proPhaseName;
	}

	public String getProStepName1() {
		return proStepName1;
	}

	public void setProStepName1(String proStepName1) {
		this.proStepName1 = proStepName1;
	}

	public int getTempstatus() {
		return tempstatus;
	}

	public void setTempstatus(int tempstatus) {
		this.tempstatus = tempstatus;
	}

	public String getKpiData() {
		return kpiData;
	}

	public void setKpiData(String kpiData) {
		this.kpiData = kpiData;
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

	public String getDocClass() {
		return docClass;
	}

	public void setDocClass(String docClass) {
		this.docClass = docClass;
	}

	public String getWorkStatue() {
		return workStatue;
	}

	public void setWorkStatue(String workStatue) {
		this.workStatue = workStatue;
	}

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

	/**
	 * 
	 * <p>
	 * Title: doPreToMap
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * 
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPreToMap()
	 **/

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("areaName", city);
		params.put("categoryCode", categoryCode);
		params.put("proName", proName);
		params.put("proNo", proNo);
		params.put("proCode", proCode);
		params.put("proStatus", proStatus);
		params.put("proPhase", proPhase);
		params.put("docClass", docClass);
		params.put("type", type);
		params.put("deptCode", deptCode);
		params.put("searchName", searchName);

		return params;
	}

	/**
	 * 
	 * <p>
	 * Title: doPostRequest
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param request
	 * 
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPostRequest(javax.servlet.http.HttpServletRequest)
	 **/

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * <p>
	 * Title: getGenerateKey
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * 
	 * @see com.glens.eap.platform.core.web.ControllerForm#getGenerateKey()
	 **/

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub

		return null;
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
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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
		this.categoryCode = categoryCode;
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
		this.unitWorkload = unitWorkload;
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
		this.proManager = proManager;
	}

	public String getEmployeecode() {
		return employeecode;
	}

	public void setEmployeecode(String employeecode) {
		this.employeecode = employeecode;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
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
		this.uprice = uprice;
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
		this.clearform = clearform;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
