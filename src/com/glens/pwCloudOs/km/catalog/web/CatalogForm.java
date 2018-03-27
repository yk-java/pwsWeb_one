package com.glens.pwCloudOs.km.catalog.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.km.catalog.vo.CatalogVo")
public class CatalogForm extends ControllerForm {

	private Long rowid;
	private String companyCode;
	private String catalogCode;
	private String catalogName;
	private String parentCatalogCode;
	private String catalogIcon;
	private int cataNo;
	private String sysProcessFlag;
	private String remarks;
	private MultipartFile icon;
	
	private String creator;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public MultipartFile getIcon() {
		return icon;
	}

	public void setIcon(MultipartFile icon) {
		this.icon = icon;
	}

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getParentCatalogCode() {
		return parentCatalogCode;
	}

	public void setParentCatalogCode(String parentCatalogCode) {
		this.parentCatalogCode = parentCatalogCode;
	}

	public int getCataNo() {
		return cataNo;
	}

	public void setCataNo(int cataNo) {
		this.cataNo = cataNo;
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyCode", companyCode);
		params.put("catalogCode", catalogCode);
		params.put("catalogName", catalogName);
		params.put("parentCatalogCode", parentCatalogCode);
		params.put("creator", creator);

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

	public String getCatalogIcon() {
		return catalogIcon;
	}

	public void setCatalogIcon(String catalogIcon) {
		this.catalogIcon = catalogIcon;
	}

}