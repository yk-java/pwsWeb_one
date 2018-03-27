package com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.glens.eap.platform.core.beans.ValueObject;
@Document(collection="PM_MCT_LIST")
public class DeviceVo implements ValueObject {
	
	private Long rowid;
	private String AMUL_BOX_ADDR;//表箱地址
	private String  ESTAE_NAME;//小区名称
	private String proNo;
	private String companyCode;
	private String deviceObjCode;
	private String deviceObjName;
	private String mctCode;
	private String proName;
	private String REFORM_PROGRAM;
	private String status;

	
	
	public String getDeviceObjName() {
		return deviceObjName;
	}
	public void setDeviceObjName(String deviceObjName) {
		this.deviceObjName = deviceObjName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getREFORM_PROGRAM() {
		return REFORM_PROGRAM;
	}
	public void setREFORM_PROGRAM(String rEFORM_PROGRAM) {
		REFORM_PROGRAM = rEFORM_PROGRAM;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getDeviceObjCode() {
		return deviceObjCode;
	}
	public void setDeviceObjCode(String deviceObjCode) {
		this.deviceObjCode = deviceObjCode;
	}
	public String getMctCode() {
		return mctCode;
	}
	public void setMctCode(String mctCode) {
		this.mctCode = mctCode;
	}
	public Long getRowid() {
		return rowid;
	}
	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getAMUL_BOX_ADDR() {
		return AMUL_BOX_ADDR;
	}
	public void setAMUL_BOX_ADDR(String aMUL_BOX_ADDR) {
		AMUL_BOX_ADDR = aMUL_BOX_ADDR;
	}
	public String getESTAE_NAME() {
		return ESTAE_NAME;
	}
	public void setESTAE_NAME(String eSTAE_NAME) {
		ESTAE_NAME = eSTAE_NAME;
	}
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

}
