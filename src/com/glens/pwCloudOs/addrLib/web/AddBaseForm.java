package com.glens.pwCloudOs.addrLib.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.web.ControllerForm;

public class AddBaseForm extends ControllerForm {
	private String companyCode;
	private String parentAddrCode;
	private String parentAddrName;
	private String addrCode;
	private String addrName;
	private String addrClass;
	private double wgs84x;
	private double wgs84y;
	private double showx;
	private double showy;
	private int addrStatus;
	private int qualityAudit;
	private String auditSuggest;
	private String searchName;
	private String jsonStr;
	private String datasourceName;
	private String datasourceCode;
	private String groupField;
	private String plateTypes;
	private String arrayJson;
	private String pic;
	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getArrayJson() {
		return arrayJson;
	}

	public void setArrayJson(String arrayJson) {
		this.arrayJson = arrayJson;
	}

	public String getDatasourceName() {
		return datasourceName;
	}

	public void setDatasourceName(String datasourceName) {
		this.datasourceName = datasourceName;
	}

	public String getDatasourceCode() {
		return datasourceCode;
	}

	public void setDatasourceCode(String datasourceCode) {
		this.datasourceCode = datasourceCode;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("COMPANY_CODE", companyCode);
		data.put("PARENT_ADDR_CODE", parentAddrCode);
		data.put("PARENT_ADDR_NAME", parentAddrName);
		data.put("ADDR_CODE", addrCode);
		data.put("ADDR_NAME", addrName);
		data.put("ADDR_CLASS", addrClass);
		data.put("WGS84_X", wgs84x);
		data.put("WGS84_Y", wgs84y);
		data.put("SHOW_X", showx);
		data.put("SHOW_Y", showy);
		data.put("ADDR_STATUS", addrStatus);
		data.put("QUALITY_AUDIT", qualityAudit);
		data.put("AUDIT_SUGGEST", auditSuggest);
		
		data.put("searchName", searchName);
		data.put("jsonStr", jsonStr);
		
		data.put("datasourceName", datasourceName);
		data.put("datasourceCode", datasourceCode);
		
		data.put("groupField", groupField);
		data.put("plateTypes", plateTypes);
		
		data.put("arrayJson",arrayJson);
		data.put("pic",pic);
		
		
		return data;
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

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getParentAddrCode() {
		return parentAddrCode;
	}

	public void setParentAddrCode(String parentAddrCode) {
		this.parentAddrCode = parentAddrCode;
	}

	public String getParentAddrName() {
		return parentAddrName;
	}

	public void setParentAddrName(String parentAddrName) {
		this.parentAddrName = parentAddrName;
	}

	public String getAddrCode() {
		return addrCode;
	}

	public void setAddrCode(String addrCode) {
		this.addrCode = addrCode;
	}

	public String getAddrName() {
		return addrName;
	}

	public void setAddrName(String addrName) {
		this.addrName = addrName;
	}

	public String getAddrClass() {
		return addrClass;
	}

	public void setAddrClass(String addrClass) {
		this.addrClass = addrClass;
	}

	public double getWgs84x() {
		return wgs84x;
	}

	public void setWgs84x(double wgs84x) {
		this.wgs84x = wgs84x;
	}

	public double getWgs84y() {
		return wgs84y;
	}

	public void setWgs84y(double wgs84y) {
		this.wgs84y = wgs84y;
	}

	public double getShowx() {
		return showx;
	}

	public void setShowx(double showx) {
		this.showx = showx;
	}

	public double getShowy() {
		return showy;
	}

	public void setShowy(double showy) {
		this.showy = showy;
	}

	public int getAddrStatus() {
		return addrStatus;
	}

	public void setAddrStatus(int addrStatus) {
		this.addrStatus = addrStatus;
	}

	public int getQualityAudit() {
		return qualityAudit;
	}

	public void setQualityAudit(int qualityAudit) {
		this.qualityAudit = qualityAudit;
	}

	public String getAuditSuggest() {
		return auditSuggest;
	}

	public void setAuditSuggest(String auditSuggest) {
		this.auditSuggest = auditSuggest;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public String getGroupField() {
		return groupField;
	}

	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}

	public String getPlateTypes() {
		return plateTypes;
	}

	public void setPlateTypes(String plateTypes) {
		this.plateTypes = plateTypes;
	}

	
	
}
