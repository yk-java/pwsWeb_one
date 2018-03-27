package com.glens.pwCloudOs.km.base.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.km.base.vo.KmBaseVo")
public class KmBaseForm extends ControllerForm {
	
	private Long rowid;
	private String companyCode;
	private String catalogCode;
	private String fileCode;
	private String fileTitle;
	private String keywords;
	private String employeeName;
	private String employeeCode;
	private int publishAuth;
	private String publishRange;
	private int publishStatus;
	private int readCnt;
	private int downloadCnt;
	private String btext;
	private String attachment1;
	private String attachment2;
	private String attachment3;
	private String sysProcessFlag;
	private String remarks;
	private String sysCreateTime;
	
	private MultipartFile projDoc1;
	private MultipartFile projDoc2;
	private MultipartFile projDoc3;
	
	private String fromDate;
	private String toDate;
	
	private String btextContent;
	private String catalogName;
	
	private String fileUrl;
	
	
	private String fullTextSearch;
	
	
	

	public String getFullTextSearch() {
		return fullTextSearch;
	}

	public void setFullTextSearch(String fullTextSearch) {
		this.fullTextSearch = fullTextSearch;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getBtextContent() {
		return btextContent;
	}

	public void setBtextContent(String btextContent) {
		this.btextContent = btextContent;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
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

	public MultipartFile getProjDoc1() {
		return projDoc1;
	}

	public void setProjDoc1(MultipartFile projDoc1) {
		this.projDoc1 = projDoc1;
	}

	public MultipartFile getProjDoc2() {
		return projDoc2;
	}

	public void setProjDoc2(MultipartFile projDoc2) {
		this.projDoc2 = projDoc2;
	}

	public MultipartFile getProjDoc3() {
		return projDoc3;
	}

	public void setProjDoc3(MultipartFile projDoc3) {
		this.projDoc3 = projDoc3;
	}

	public String getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
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

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public int getPublishAuth() {
		return publishAuth;
	}

	public void setPublishAuth(int publishAuth) {
		this.publishAuth = publishAuth;
	}

	public String getPublishRange() {
		return publishRange;
	}

	public void setPublishRange(String publishRange) {
		this.publishRange = publishRange;
	}

	public int getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(int publishStatus) {
		this.publishStatus = publishStatus;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public int getDownloadCnt() {
		return downloadCnt;
	}

	public void setDownloadCnt(int downloadCnt) {
		this.downloadCnt = downloadCnt;
	}

	public String getBtext() {
		return btext;
	}

	public void setBtext(String btext) {
		this.btext = btext;
	}

	public String getAttachment1() {
		return attachment1;
	}

	public void setAttachment1(String attachment1) {
		this.attachment1 = attachment1;
	}

	public String getAttachment2() {
		return attachment2;
	}

	public void setAttachment2(String attachment2) {
		this.attachment2 = attachment2;
	}

	public String getAttachment3() {
		return attachment3;
	}

	public void setAttachment3(String attachment3) {
		this.attachment3 = attachment3;
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
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyCode", companyCode);
		params.put("catalogCode", catalogCode);
		params.put("fileCode", fileCode);
		params.put("fileTitle", fileTitle);
		params.put("keywords", keywords);
		params.put("employeeName", employeeName);
		params.put("employeeCode", employeeCode);
		params.put("publishAuth", publishAuth);
		
		params.put("publishRange", publishRange);
		params.put("publishStatus", publishStatus);
		params.put("readCnt", readCnt);
		params.put("downloadCnt", downloadCnt);
		params.put("btext", btext);
		params.put("attachment1", attachment1);
		params.put("attachment2", attachment2);
		params.put("attachment3", attachment3);
		params.put("remarks", remarks);
		params.put("fileUrl", fileUrl);
		
		params.put("rowid", rowid);
		params.put("fullTextSearch", fullTextSearch);
		
		
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
