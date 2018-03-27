/**
 * @Title: DeviceHandleForm.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.deviceHandle.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-12-5 下午12:00:20
 * @version V1.0
 */

package com.glens.pwCloudOs.om.deviceMgr.deviceHandle.web;

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

public class DeviceHandleForm extends ControllerForm {

	private String companyCode;

	private String proNo;

	private String mctCode;

	private String deviceTypeCode;

	private String fieldEname;

	private String taskCode;

	/**
	 * 
	 * <p>
	 * Title: doPreToMap
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * 
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPreToMap()
	 **/

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyCode", companyCode);
		params.put("proNo", proNo);
		params.put("mctCode", mctCode);
		params.put("deviceTypeCode", deviceTypeCode);
		params.put("fieldEname", fieldEname);
		params.put("taskCode", taskCode);
		return params;
	}

	/**
	 * 
	 * <p>
	 * Title: doPostRequest
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param request
	 * 
	 * @see com.glens.eap.platform.core.web.ControllerForm#doPostRequest(javax.servlet.http.HttpServletRequest)
	 **/

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * <p>
	 * Title: getGenerateKey
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * 
	 * @see com.glens.eap.platform.core.web.ControllerForm#getGenerateKey()
	 **/

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

	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}

	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}

	public String getFieldEname() {
		return fieldEname;
	}

	public void setFieldEname(String fieldEname) {
		this.fieldEname = fieldEname;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

}
