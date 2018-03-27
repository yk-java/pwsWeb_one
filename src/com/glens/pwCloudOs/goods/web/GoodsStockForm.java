package com.glens.pwCloudOs.goods.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.web.ControllerForm;

public class GoodsStockForm extends ControllerForm {
	
	//物资库存
	
	private String  stockCode;  //库存表号
	
	private String  goodsCode;  //物资编号
	
	private String  goodsName;  // 物资名称
	
	private float   stock;   //库存
	
	private String  storeCode;  //仓库code
	
	//物资流程表
	private String processCode;
	
	private String processTitle;
	
	private String gtype;
	
	private int processStatus;
	
	private String startTime;
	
	private String sponsor;
	
	private String startDesc;
	
	private String proNo;
	
	private String proManager;
	
	private String managerAuditTime;
	
	private String managerAuditDesc;
	
	private String storeman;
	
	private String storemanAuditTime;
	
	private String storemanAuditDesc;
	
	private String finance;
	
	private String financeAuditTime;
	
	private String financeAuditDesc;
	
	private String pic1;
	
	private String pic2;
	
	private String picCode1;
	
	private MultipartFile picCode2;
	
	//物资出入库流水表
	
	private double price;
	
	private double amount;
	
	private String goodsTime;
	
	private  int  isComplete;
	
	//单位
	private String unitCode;
	
	private String unitName;
	
	private String isMainUnit;
	
	private String conversionFormula;
	
	private String attrJson;
	
	private MultipartFile img1;
	
	private String userCode;
	
	private String deptCode;
	
	private String districtManager;
	
	private String pmanager;
	
	
	
	//废料
	
	private String categoryCode;
	
	private String categoryName;
	
	private String unit;
	
	
	private String processType;
	
	
	private String fromDate;
	
	private String toDate;
	
	
	private String outAmount; //金额
	
	
	
	
	
	
	

	public String getOutAmount() {
		return outAmount;
	}

	public void setOutAmount(String outAmount) {
		this.outAmount = outAmount;
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

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	public String getPmanager() {
		return pmanager;
	}

	public void setPmanager(String pmanager) {
		this.pmanager = pmanager;
	}

	public MultipartFile getImg1() {
		return img1;
	}

	public void setImg1(MultipartFile img1) {
		this.img1 = img1;
	}

	public String getAttrJson() {
		return attrJson;
	}

	public void setAttrJson(String attrJson) {
		this.attrJson = attrJson;
	}

	

	public String getPicCode1() {
		return picCode1;
	}

	public void setPicCode1(String picCode1) {
		this.picCode1 = picCode1;
	}

	public MultipartFile getPicCode2() {
		return picCode2;
	}

	public void setPicCode2(MultipartFile picCode2) {
		this.picCode2 = picCode2;
	}

	public String getProcessTitle() {
		return processTitle;
	}

	public void setProcessTitle(String processTitle) {
		this.processTitle = processTitle;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getGoodsTime() {
		return goodsTime;
	}

	public void setGoodsTime(String goodsTime) {
		this.goodsTime = goodsTime;
	}

	public int getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(int isComplete) {
		this.isComplete = isComplete;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getIsMainUnit() {
		return isMainUnit;
	}

	public void setIsMainUnit(String isMainUnit) {
		this.isMainUnit = isMainUnit;
	}

	public String getConversionFormula() {
		return conversionFormula;
	}

	public void setConversionFormula(String conversionFormula) {
		this.conversionFormula = conversionFormula;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getGtype() {
		return gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
	}

	public int getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(int processStatus) {
		this.processStatus = processStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getStartDesc() {
		return startDesc;
	}

	public void setStartDesc(String startDesc) {
		this.startDesc = startDesc;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getProManager() {
		return proManager;
	}

	public void setProManager(String proManager) {
		this.proManager = proManager;
	}

	public String getManagerAuditTime() {
		return managerAuditTime;
	}

	public void setManagerAuditTime(String managerAuditTime) {
		this.managerAuditTime = managerAuditTime;
	}

	public String getManagerAuditDesc() {
		return managerAuditDesc;
	}

	public void setManagerAuditDesc(String managerAuditDesc) {
		this.managerAuditDesc = managerAuditDesc;
	}

	public String getStoreman() {
		return storeman;
	}

	public void setStoreman(String storeman) {
		this.storeman = storeman;
	}

	public String getStoremanAuditTime() {
		return storemanAuditTime;
	}

	public void setStoremanAuditTime(String storemanAuditTime) {
		this.storemanAuditTime = storemanAuditTime;
	}

	public String getStoremanAuditDesc() {
		return storemanAuditDesc;
	}

	public void setStoremanAuditDesc(String storemanAuditDesc) {
		this.storemanAuditDesc = storemanAuditDesc;
	}

	public String getFinance() {
		return finance;
	}

	public void setFinance(String finance) {
		this.finance = finance;
	}

	public String getFinanceAuditTime() {
		return financeAuditTime;
	}

	public void setFinanceAuditTime(String financeAuditTime) {
		this.financeAuditTime = financeAuditTime;
	}

	public String getFinanceAuditDesc() {
		return financeAuditDesc;
	}

	public void setFinanceAuditDesc(String financeAuditDesc) {
		this.financeAuditDesc = financeAuditDesc;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public float getStock() {
		return stock;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("stockCode", stockCode);
		params.put("goodsCode", goodsCode);
		params.put("goodsName", goodsName);
		params.put("stock", stock);
		params.put("storeCode", storeCode);

		params.put("processCode", processCode);
		params.put("gtype", gtype);
		params.put("processStatus", processStatus);
		params.put("startTime", startTime);
		params.put("sponsor", sponsor);
		
		params.put("startDesc", startDesc);
		params.put("proNo", proNo);
		params.put("proManager", proManager);
		params.put("managerAuditTime", managerAuditTime);
		params.put("managerAuditDesc", managerAuditDesc);
		
		params.put("storeman", storeman);
		params.put("storemanAuditTime", storemanAuditTime);
		params.put("storemanAuditDesc", storemanAuditDesc);
		params.put("financeAuditTime", financeAuditTime);
		params.put("financeAuditDesc", financeAuditDesc);
		params.put("pic1", pic1);
		params.put("pic2", pic2);
		
		
		params.put("price", price);
		params.put("amount", amount);
		params.put("goodsTime", goodsTime);
		params.put("isComplete", isComplete);
		params.put("unitCode", unitCode);
		params.put("unitName", unitName);
		params.put("isMainUnit", isMainUnit);
		params.put("conversionFormula", conversionFormula);
		params.put("processTitle", processTitle);
		params.put("picCode1", picCode1);
		params.put("picCode2", picCode2);
		params.put("attrJson", attrJson);
		params.put("img1", img1);
		params.put("userCode", userCode);
		params.put("deptCode", deptCode);
		params.put("districtManager", districtManager);
		params.put("pmanager", pmanager);
		
		params.put("categoryCode", categoryCode);
		params.put("categoryName", categoryName);
		params.put("unit", unit);
		params.put("processType", processType);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("outAmount", outAmount);
		
		
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
