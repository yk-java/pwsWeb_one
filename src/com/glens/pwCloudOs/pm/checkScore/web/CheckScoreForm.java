package com.glens.pwCloudOs.pm.checkScore.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.web.ControllerForm;

public class CheckScoreForm extends ControllerForm {
	
	private Long rowid;
	private String companyCode;
	private String kpiCode;
	private String kpiName;
	private float checkScore;
	private String checkedPro;
	private String checkMan;
	private String checkTime;
	private String checkDesc;
	private String checkObj;
	private String remarks;
	private String docName;
	
	

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
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

	public String getKpiCode() {
		return kpiCode;
	}

	public void setKpiCode(String kpiCode) {
		this.kpiCode = kpiCode;
	}

	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}

	public float getCheckScore() {
		return checkScore;
	}

	public void setCheckScore(float checkScore) {
		this.checkScore = checkScore;
	}

	public String getCheckedPro() {
		return checkedPro;
	}

	public void setCheckedPro(String checkedPro) {
		this.checkedPro = checkedPro;
	}

	public String getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckDesc() {
		return checkDesc;
	}

	public void setCheckDesc(String checkDesc) {
		this.checkDesc = checkDesc;
	}

	public String getCheckObj() {
		return checkObj;
	}

	public void setCheckObj(String checkObj) {
		this.checkObj = checkObj;
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
		params.put("kpiCode", kpiCode);
		params.put("checkScore", checkScore);
		params.put("checkedPro", checkedPro);
		params.put("checkMan", checkMan);
		params.put("checkTime", checkTime);
		params.put("checkDesc", checkDesc);
		params.put("checkObj", checkObj);
		params.put("remarks", remarks);
		params.put("docName", docName);
		
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
