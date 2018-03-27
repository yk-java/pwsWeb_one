package com.glens.pwCloudOs.materielMg.assetMg.asset.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;


@ValueObjectProcessor(clazz="com.glens.pwCloudOs.materielMg.assetMg.asset.vo.AssetMaintainVo")
public class AssetMaintainForm extends ControllerForm {
	
	private Long rowid;
	private String assetCode;
	private String assetName;
	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	private String mtDate;//保养时间
	private String mtContent;//保养项目
	private String mtProcess;
	private Float mtCost;
	private String  mtReceipt;//保养发票
	private String  mtVender;//保养厂家
	private String  nextMtDate;//下次保养时间
	private String remarks;
	
	private String fromDate;
	private String toDate;

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

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getMtDate() {
		return mtDate;
	}

	public void setMtDate(String mtDate) {
		this.mtDate = mtDate;
	}

	public String getMtContent() {
		return mtContent;
	}

	public void setMtContent(String mtContent) {
		this.mtContent = mtContent;
	}

	public String getMtProcess() {
		return mtProcess;
	}

	public void setMtProcess(String mtProcess) {
		this.mtProcess = mtProcess;
	}

	public Float getMtCost() {
		return mtCost;
	}

	public void setMtCost(Float mtCost) {
		this.mtCost = mtCost;
	}

	public String getMtReceipt() {
		return mtReceipt;
	}

	public void setMtReceipt(String mtReceipt) {
		this.mtReceipt = mtReceipt;
	}

	public String getMtVender() {
		return mtVender;
	}

	public void setMtVender(String mtVender) {
		this.mtVender = mtVender;
	}

	public String getNextMtDate() {
		return nextMtDate;
	}

	public void setNextMtDate(String nextMtDate) {
		this.nextMtDate = nextMtDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("assetCode", assetCode);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
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
