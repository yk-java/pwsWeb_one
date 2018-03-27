/**
 * @Title: LoginForm.java
 * @Package com.glens.pwCloudOs.login.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-16 下午1:51:43
 * @version V1.0
 */

package com.glens.pwCloudOs.login.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.web.ControllerForm;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

public class LoginForm extends ControllerForm {

	private String accountCode;

	private String psw;
	
	private String oldPsw;

	private String userName;

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

		return null;
	}

	public String getOldPsw() {
		return oldPsw;
	}

	public void setOldPsw(String oldPsw) {
		this.oldPsw = oldPsw;
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

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
