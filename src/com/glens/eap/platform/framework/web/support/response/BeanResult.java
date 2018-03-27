/**

 * @Title: BeanResult.java

 * @Package com.glens.eap.platform.framework.web.support.response

 * @Description: TODO

 * Copyright: Copyright (c) 2016 

 * Company:南京量为石信息科技有限公司

 * 

 * @author Comsys-Administrator

 * @date 2016-5-6 下午5:16:42

 * @version V1.0

 */

package com.glens.eap.platform.framework.web.support.response;

import com.glens.eap.platform.framework.web.ResponseConstants;

/**

 * @ClassName: BeanResult

 * @Description: TODO

 * @author Comsys-Administrator

 * @date 2016-5-6 下午5:16:42

 *
 */

public class BeanResult extends ResponseResult {

	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public static BeanResult success(String msg, Object data){
		BeanResult res = new BeanResult();
		res.setStatusCode(ResponseConstants.OK);
		res.setResultMsg(msg);
		res.setData(data);
		return res;
	}
	
	public static BeanResult fail(String msg){
		BeanResult res = new BeanResult();
		res.setStatusCode(ResponseConstants.SERVER_ERROR);
		res.setResultMsg(msg);
		return res;
		
	}
}
