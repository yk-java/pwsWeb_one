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
 * 
 * @ClassName: ListResult
 * 
 * @Description: TODO
 * 
 * @author Comsys-Administrator
 * 
 * @date 2016-5-6 下午5:13:14
 * 
 * 
 */

public class ListsResult extends ResponseResult {

	private List firlist;

	private List seclist;

	public List getFirlist() {
		return firlist;
	}

	public void setFirlist(List firlist) {
		this.firlist = firlist;
	}

	public List getSeclist() {
		return seclist;
	}

	public void setSeclist(List seclist) {
		this.seclist = seclist;
	}

	public static ListsResult success(String msg, List firList, List secList) {
		ListsResult res = new ListsResult();
		res.setStatusCode(ResponseConstants.OK);
		res.setResultMsg(msg);
		res.setFirlist(firList);
		res.setSeclist(secList);
		return res;
	}

	public static ListsResult fail(String msg) {
		ListsResult res = new ListsResult();
		res.setStatusCode(ResponseConstants.SERVER_ERROR);
		res.setResultMsg(msg);
		return res;

	}

}
