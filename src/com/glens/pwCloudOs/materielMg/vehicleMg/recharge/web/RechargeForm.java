/**
 * @Title: RechargeForm.java
 * @Package com.glens.pwCloudOs.materielMg.vehicleMg.recharge.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-24 上午11:15:01
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.vehicleMg.recharge.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@ValueObjectProcessor(clazz="com.glens.pwCloudOs.materielMg.vehicleMg.recharge.vo.RechargeVo")
public class RechargeForm extends ControllerForm {

	private Long rowid;
	
	private String fuelcardNo;

    private String rechargeDate;

    private Integer rechargeAmount;

    private String rechargeShop;

    private String remarks;
    
    private String fromDate;
    
    private String toDate;
	
	/**
	
	 * <p>Title: doPreToMap</p>
	
	 * <p>Description: </p>
	
	 * @return
	
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPreToMap()
	
	 **/

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub

		Map<String, String> params = new HashMap<String, String>();
		params.put("fuelcardNo", fuelcardNo);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		
		return params;
	}

	/**
	
	 * <p>Title: doPostRequest</p>
	
	 * <p>Description: </p>
	
	 * @param request
	
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPostRequest(javax.servlet.http.HttpServletRequest)
	
	 **/

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	/**
	
	 * <p>Title: getGenerateKey</p>
	
	 * <p>Description: </p>
	
	 * @return
	
	 * @see com.glens.eap.platform.core.web.ControllerForm#getGenerateKey()
	
	 **/

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub

		return null;
	}

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getFuelcardNo() {
		return fuelcardNo;
	}

	public void setFuelcardNo(String fuelcardNo) {
		this.fuelcardNo = fuelcardNo;
	}

	public String getRechargeDate() {
		return rechargeDate;
	}

	public void setRechargeDate(String rechargeDate) {
		this.rechargeDate = rechargeDate;
	}

	public Integer getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(Integer rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public String getRechargeShop() {
		return rechargeShop;
	}

	public void setRechargeShop(String rechargeShop) {
		this.rechargeShop = rechargeShop;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

}
