package com.glens.pwCloudOs.pm.scene.spot.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.pm.scene.spot.vo.ProSpot")
public class ProSpotForm extends ControllerForm {

	private Long rowid;

	private String companyCode;

	private String spotCode;

	private String spotName;

	private String proNo;

	private String proName;

	private String buildEmployeeCode;

	private String manageEmployeeCode;

	private String ownerEmployeeCode;

	private String spotAddr;

	private String lng;

	private String lat;

	private String pic1;

	private String pic2;

	private String pic3;

	private String workerCodes;

	private String sysCreateTime;

	private String sysUpdateTime;

	private String sysDeleteTime;

	private String sysProcessFlag;

	private String startTime;

	private MultipartFile file1;
	private MultipartFile file2;
	private MultipartFile file3;

	public MultipartFile getFile1() {
		return file1;
	}

	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}

	public MultipartFile getFile2() {
		return file2;
	}

	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}

	public MultipartFile getFile3() {
		return file3;
	}

	public void setFile3(MultipartFile file3) {
		this.file3 = file3;
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
		this.companyCode = companyCode == null ? null : companyCode.trim();
	}

	public String getSpotCode() {
		return spotCode;
	}

	public void setSpotCode(String spotCode) {
		this.spotCode = spotCode == null ? null : spotCode.trim();
	}

	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName == null ? null : spotName.trim();
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

	public String getSpotAddr() {
		return spotAddr;
	}

	public void setSpotAddr(String spotAddr) {
		this.spotAddr = spotAddr == null ? null : spotAddr.trim();
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng == null ? null : lng.trim();
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat == null ? null : lat.trim();
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1 == null ? null : pic1.trim();
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2 == null ? null : pic2.trim();
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3 == null ? null : pic3.trim();
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag == null ? null : sysProcessFlag
				.trim();
	}

	public String getBuildEmployeeCode() {
		return buildEmployeeCode;
	}

	public void setBuildEmployeeCode(String buildEmployeeCode) {
		this.buildEmployeeCode = buildEmployeeCode;
	}

	public String getManageEmployeeCode() {
		return manageEmployeeCode;
	}

	public void setManageEmployeeCode(String manageEmployeeCode) {
		this.manageEmployeeCode = manageEmployeeCode;
	}

	public String getOwnerEmployeeCode() {
		return ownerEmployeeCode;
	}

	public void setOwnerEmployeeCode(String ownerEmployeeCode) {
		this.ownerEmployeeCode = ownerEmployeeCode;
	}

	public String getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public String getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(String sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public String getSysDeleteTime() {
		return sysDeleteTime;
	}

	public void setSysDeleteTime(String sysDeleteTime) {
		this.sysDeleteTime = sysDeleteTime;
	}

	public String getWorkerCodes() {
		return workerCodes;
	}

	public void setWorkerCodes(String workerCodes) {
		this.workerCodes = workerCodes;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("proNo", proNo);
		params.put("spotCode", spotCode);
		params.put("spotName", spotName);
		params.put("startTime", startTime);
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
