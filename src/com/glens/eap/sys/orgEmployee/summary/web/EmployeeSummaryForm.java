/**
 * @Title: EmployeeSummaryForm.java
 * @Package com.glens.eap.sys.orgEmployee.summary.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-7-15 上午10:46:53
 * @version V1.0
 */


package com.glens.eap.sys.orgEmployee.summary.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.web.ControllerForm;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class EmployeeSummaryForm extends ControllerForm {

	private String province;
	
	private String city;
	
	private String district;
	
	private String regionCode;
	
	private Integer flag;
	
	private String proNo;
	
	private Integer jobClass2;
	
	private String contractPropertyCode; 
	
	/**
	
	 * <p>Title: doPreToMap</p>
	
	 * <p>Description: </p>
	
	 * @return
	
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPreToMap()
	
	 **/

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("province", province);
		params.put("city", city);
		params.put("district", district);
		params.put("regionCode", regionCode);
		params.put("flag", flag);
		params.put("proNo", proNo);
		params.put("jobClass2", jobClass2);
		params.put("contractPropertyCode", contractPropertyCode);

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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public Integer getJobClass2() {
		return jobClass2;
	}

	public void setJobClass2(Integer jobClass2) {
		this.jobClass2 = jobClass2;
	}

	public String getContractPropertyCode() {
		return contractPropertyCode;
	}

	public void setContractPropertyCode(String contractPropertyCode) {
		this.contractPropertyCode = contractPropertyCode;
	}

}
