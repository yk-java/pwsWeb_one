package com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.vo.CpCommuteConfig")
public class CpCommuteConfigForm extends ControllerForm {

	private Long rowid;

	private String companyCode;
	private String proNo;
	private String checkinTime;

	private String checkoutTime;
	private Integer checkinExMinites;
	private Integer checkoutExMinites;
	
	private String deptCode;
	
	private String districtManager;
	
	private String proManager;
	

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

	public Integer getCheckinExMinites() {
		return checkinExMinites;
	}

	public void setCheckinExMinites(Integer checkinExMinites) {
		this.checkinExMinites = checkinExMinites;
	}

	public Integer getCheckoutExMinites() {
		return checkoutExMinites;
	}

	public void setCheckoutExMinites(Integer checkoutExMinites) {
		this.checkoutExMinites = checkoutExMinites;
	}
	private Integer checkPointTotal;

	public String getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(String checkinTime) {
		this.checkinTime = checkinTime;
	}

	public String getCheckoutTime() {
		return checkoutTime;
	}

	public void setCheckoutTime(String checkoutTime) {
		this.checkoutTime = checkoutTime;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	private Date sysCreateTime;

	private Date sysUpdateTime;

	private Date sysDeleteTime;

	private String sysProcessFlag;

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
		this.companyCode = companyCode == null ? null : companyCode.trim();
	}

	public Integer getCheckPointTotal() {
		return checkPointTotal;
	}

	public void setCheckPointTotal(Integer checkPointTotal) {
		this.checkPointTotal = checkPointTotal;
	}

	public Date getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(Date sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public Date getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public Date getSysDeleteTime() {
		return sysDeleteTime;
	}

	public void setSysDeleteTime(Date sysDeleteTime) {
		this.sysDeleteTime = sysDeleteTime;
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag == null ? null : sysProcessFlag
				.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	@Override
	protected Map doPreToMap() {
		Map params=new HashMap();
		params.put("companyCode", companyCode);
		params.put("proNo", proNo);
		params.put("deptCode", deptCode);
		params.put("districtManager", districtManager);
		params.put("proManager", proManager);
		
		
		
		//判断是否部门监管角色
		CpCommuteConfigController configController = (CpCommuteConfigController) this.controller;
		UserToken token = configController.getToken(getRequest());
		if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
			
			params.put("deptCode", token.getUnitCode());
		}
		
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

	public ValueObject toVo() {

		ValueObject vo = createVo();
		try {
			PropertyUtils.copyProperties(vo, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
}