/**

 * @Title: ListResult.java

 * @Package com.glens.eap.platform.framework.web.support.response

 * @Description: TODO

 * Copyright: Copyright (c) 2016 

 * Company:南京量为石信息科技有限公司

 * 

 * @author Comsys-Administrator

 * @date 2016-5-6 下午5:13:14

 * @version V1.0

 */

package com.glens.eap.platform.framework.web.support.response;

import java.util.List;

import com.glens.eap.platform.framework.web.ResponseConstants;

/**

 * @ClassName: ListResult

 * @Description: TODO

 * @author Comsys-Administrator

 * @date 2016-5-6 下午5:13:14

 *
 */

public class ListResult extends ResponseResult {

	
	private List list;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
	public static ListResult success(String msg, List data){
		ListResult res = new ListResult();
		res.setStatusCode(ResponseConstants.OK);
		res.setResultMsg(msg);
		res.setList(data);
		return res;
	}
	public static ListResult fail(String msg){
		ListResult res = new ListResult();
		res.setStatusCode(ResponseConstants.SERVER_ERROR);
		res.setResultMsg(msg);
		return res;
		
	}
	
}
