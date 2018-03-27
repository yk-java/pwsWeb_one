package com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.beans.ValueObject;


@Document(collection="PM_DEVICE_PICBOOK")
public class DeviceBookVo implements ValueObject {
	  
	private Long rowid;
	private String companyCode;
	private String proNo;
	private String mctCode;
	private String deviceObjCode;
	private String picTitle;
	private String picVisitid;
	private MultipartFile projDoc;
	private String sysCreateTime;
	private String PIC_CLASSIFY_CODE;
	private String PIC_CLASSIFY_NAME;
	
	
	
	public String getPIC_CLASSIFY_CODE() {
		return PIC_CLASSIFY_CODE;
	}
	public void setPIC_CLASSIFY_CODE(String pIC_CLASSIFY_CODE) {
		PIC_CLASSIFY_CODE = pIC_CLASSIFY_CODE;
	}
	public String getPIC_CLASSIFY_NAME() {
		return PIC_CLASSIFY_NAME;
	}
	public void setPIC_CLASSIFY_NAME(String pIC_CLASSIFY_NAME) {
		PIC_CLASSIFY_NAME = pIC_CLASSIFY_NAME;
	}
	public String getSysCreateTime() {
		return sysCreateTime;
	}
	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}
	public MultipartFile getProjDoc() {
		return projDoc;
	}
	public void setProjDoc(MultipartFile projDoc) {
		this.projDoc = projDoc;
	}
	private String remarks;
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
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getMctCode() {
		return mctCode;
	}
	public void setMctCode(String mctCode) {
		this.mctCode = mctCode;
	}
	public String getDeviceObjCode() {
		return deviceObjCode;
	}
	public void setDeviceObjCode(String deviceObjCode) {
		this.deviceObjCode = deviceObjCode;
	}
	public String getPicTitle() {
		return picTitle;
	}
	public void setPicTitle(String picTitle) {
		this.picTitle = picTitle;
	}
	public String getPicVisitid() {
		return picVisitid;
	}
	public void setPicVisitid(String picVisitid) {
		this.picVisitid = picVisitid;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
