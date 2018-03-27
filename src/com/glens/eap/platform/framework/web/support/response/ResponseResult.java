/**

 * @Title: ResponseResult.java

 * @Package com.glens.eap.platform.framework.web.support.response

 * @Description: TODO

 * Copyright: Copyright (c) 2016 

 * Company:南京量为石信息科技有限公司

 * 

 * @author Comsys-Administrator

 * @date 2016-5-6 下午5:08:14

 * @version V1.0

 */

package com.glens.eap.platform.framework.web.support.response;

import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.framework.web.ResponseConstants;

/**

 * @ClassName: ResponseResult

 * @Description: TODO

 * @author Comsys-Administrator

 * @date 2016-5-6 下午5:08:14

 *

 */

@SuppressWarnings("serial")
public class ResponseResult implements ValueObject {

	private String statusCode;
	
	private String resultMsg;
	
	/**



	 * 创建一个新的实例 ResponseResult. 

	 * <p>Title: </p>

	 * <p>Description: </p>


	 */

	public ResponseResult() {

		// TODO Auto-generated constructor stub
	}
	
	public ResponseResult(String statusCode, String resultMsg) {

		// TODO Auto-generated constructor stub
		this.statusCode = statusCode;
		this.resultMsg = resultMsg;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	public static ResponseResult success(String msg){
		ResponseResult res = new ResponseResult();
		res.setStatusCode(ResponseConstants.OK);
		res.setResultMsg(msg);
		return res;
	}
	public static ResponseResult fail(String msg){
		ResponseResult res = new ResponseResult();
		res.setStatusCode(ResponseConstants.SERVER_ERROR);
		res.setResultMsg(msg);
		return res;
		
	}
	
	public boolean isSuccess(){
		if(ResponseConstants.OK.equals(this.statusCode)){
			return true;
		}
		return false;
	}
}
