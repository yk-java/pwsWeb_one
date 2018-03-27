package com.glens.pwCloudOs.materielMg.assetMg.materialBase.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;


@ValueObjectProcessor(clazz="com.glens.pwCloudOs.materielMg.assetMg.materialBase.vo.MaterialBaseVo")
public class MaterialBaseForm extends ControllerForm {
	
	private String  materialBatchno;//耗材批次编号
	private String  materialName;
	private String assetTypeCode;
	private String brand;
    private String modelNo;
    private String forms;
    private Float price1;
    private Float price2;
    private Float total1;
    private Float total2;
    private String bill;
    private String datePurchase;
    private String dateStorage;
    private double  countStorage;
    private double curcountStorage;
    private String remarks;
    private String assetClassCode;
    private int invoiceType; //发票类型
    
    public int getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(int invoiceType) {
		this.invoiceType = invoiceType;
	}

	//app端
    private String flowCode;
    
    private String loanEmployeeCode;
    
    private String loanEmployeeName;
    
    private String loanUnitCode;
    
    private String loanUnitName;
    
    private String loanProNo;
    
    private String loanProName;
    
    private int useCount;
    
    private String useDate;
    
    private String useDesc;
    
    private String flowStatus;
    
    private String estimateReturnDate;
    
    private String returnDate;
    
    private String rentStatus;
    
    private String returnMan;
    
    private String  receiveMan;
    
    private String grantMan;
    
    private int recordType = 0;
    
    private String deptCode;
	
	private String districtManager;
	
	private String proManager;
    
    private String proNo;
    
    private String returnPic;
    
    private String fromDate;
    
    private String toDate;
    
    private String proCategory;
    
    public double getCountStorage() {
		return countStorage;
	}

	public void setCountStorage(double countStorage) {
		this.countStorage = countStorage;
	}

	public double getCurcountStorage() {
		return curcountStorage;
	}

	public void setCurcountStorage(double curcountStorage) {
		this.curcountStorage = curcountStorage;
	}

	/**
     * 审核内容，格式如：批次号,数量;批次号，数量...
     */
    private String auditContent;
    
    private String returnAuditMan;
    
    public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	
    
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDistrictManager() {
		return districtManager;
	}

	public void setDistrictManager(String districtManager) {
		this.districtManager = districtManager;
	}

	public String getProManager() {
		return proManager;
	}

	public void setProManager(String proManager) {
		this.proManager = proManager;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getLoanEmployeeCode() {
		return loanEmployeeCode;
	}

	public void setLoanEmployeeCode(String loanEmployeeCode) {
		this.loanEmployeeCode = loanEmployeeCode;
	}

	public String getLoanEmployeeName() {
		return loanEmployeeName;
	}

	public void setLoanEmployeeName(String loanEmployeeName) {
		this.loanEmployeeName = loanEmployeeName;
	}

	public String getLoanUnitCode() {
		return loanUnitCode;
	}

	public void setLoanUnitCode(String loanUnitCode) {
		this.loanUnitCode = loanUnitCode;
	}

	public String getLoanUnitName() {
		return loanUnitName;
	}

	public void setLoanUnitName(String loanUnitName) {
		this.loanUnitName = loanUnitName;
	}

	public String getLoanProNo() {
		return loanProNo;
	}

	public void setLoanProNo(String loanProNo) {
		this.loanProNo = loanProNo;
	}

	public String getLoanProName() {
		return loanProName;
	}

	public void setLoanProName(String loanProName) {
		this.loanProName = loanProName;
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

	
	public String getEstimateReturnDate() {
		return estimateReturnDate;
	}

	public void setEstimateReturnDate(String estimateReturnDate) {
		this.estimateReturnDate = estimateReturnDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}

	public String getRentStatus() {
		return rentStatus;
	}

	public void setRentStatus(String rentStatus) {
		this.rentStatus = rentStatus;
	}

	public String getReturnMan() {
		return returnMan;
	}

	public void setReturnMan(String returnMan) {
		this.returnMan = returnMan;
	}

	public String getReceiveMan() {
		return receiveMan;
	}

	public void setReceiveMan(String receiveMan) {
		this.receiveMan = receiveMan;
	}

	public String getGrantMan() {
		return grantMan;
	}

	public void setGrantMan(String grantMan) {
		this.grantMan = grantMan;
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	public String getAssetClassCode() {
		return assetClassCode;
	}

	public void setAssetClassCode(String assetClassCode) {
		this.assetClassCode = assetClassCode;
	}

	public String getMaterialBatchno() {
		return materialBatchno;
	}

	public void setMaterialBatchno(String materialBatchno) {
		this.materialBatchno = materialBatchno;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getAssetTypeCode() {
		return assetTypeCode;
	}

	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getForms() {
		return forms;
	}

	public void setForms(String forms) {
		this.forms = forms;
	}

	public Float getPrice1() {
		return price1;
	}

	public void setPrice1(Float price1) {
		this.price1 = price1;
	}

	public Float getPrice2() {
		return price2;
	}

	public void setPrice2(Float price2) {
		this.price2 = price2;
	}

	public Float getTotal1() {
		return total1;
	}

	public void setTotal1(Float total1) {
		this.total1 = total1;
	}

	public Float getTotal2() {
		return total2;
	}

	public void setTotal2(Float total2) {
		this.total2 = total2;
	}

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}

	public String getDatePurchase() {
		return datePurchase;
	}

	public void setDatePurchase(String datePurchase) {
		this.datePurchase = datePurchase;
	}

	public String getDateStorage() {
		return dateStorage;
	}

	public void setDateStorage(String dateStorage) {
		this.dateStorage = dateStorage;
	}

	

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getReturnPic() {
		return returnPic;
	}

	public void setReturnPic(String returnPic) {
		this.returnPic = returnPic;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getProCategory() {
		return proCategory;
	}

	public void setProCategory(String proCategory) {
		this.proCategory = proCategory;
	}

	public String getAuditContent() {
		return auditContent;
	}

	public void setAuditContent(String auditContent) {
		this.auditContent = auditContent;
	}

	public String getReturnAuditMan() {
		return returnAuditMan;
	}

	public void setReturnAuditMan(String returnAuditMan) {
		this.returnAuditMan = returnAuditMan;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		//params.put("assetClassCode", assetClassCode);
		params.put("assetTypeCode", assetTypeCode);
		params.put("assetClassCode", assetClassCode);
		params.put("materialName", materialName);
		params.put("flowCode", flowCode);
		params.put("loanEmployeeCode", loanEmployeeCode);
		params.put("loanEmployeeName", loanEmployeeName);
		params.put("loanUnitCode", loanUnitCode);
		params.put("loanUnitName", loanUnitName);
		params.put("loanProNo", loanProNo);
		params.put("loanProName", loanProName);
		params.put("useCount", useCount);
		params.put("useDate", useDate);
		params.put("useDesc", useDesc);
		params.put("flowStatus", flowStatus);
		params.put("estimateReturnDate", estimateReturnDate);
		params.put("returnDate", returnDate);
		params.put("rentStatus", rentStatus);
		params.put("returnMan", returnMan);
		params.put("receiveMan", receiveMan);
		params.put("grantMan", grantMan);
		params.put("recordType", recordType);
		params.put("deptCode", deptCode);
		params.put("districtManager", districtManager);
		params.put("proManager", proManager);
		params.put("remarks", remarks);
		params.put("proNo", proNo);
		params.put("returnPic", returnPic);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("proCategory", proCategory);
		params.put("auditContent", auditContent);
		params.put("materialBatchno", materialBatchno);
		params.put("returnAuditMan", returnAuditMan);
		params.put("invoiceType", invoiceType);
		
		return params;
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

}
