/**

 * @Title: PageBeanResult.java

 * @Package com.glens.eap.platform.framework.web.support.response

 * @Description: TODO

 * Copyright: Copyright (c) 2016 

 * Company:南京量为石信息科技有限公司

 * 

 * @author Comsys-Administrator

 * @date 2016-5-6 下午5:17:38

 * @version V1.0

 */

package com.glens.eap.platform.framework.web.support.response;

import java.util.List;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.web.ResponseConstants;

/**
 * 
 * @ClassName: PageBeanResult
 * 
 * @Description: TODO
 * 
 * @author Comsys-Administrator
 * 
 * @date 2016-5-6 下午5:17:38
 * 
 * 
 */

public class PageBeanResult extends ResponseResult {

	private int currentPage;

	private int totalPage;

	private int totalRecord;

	private int perpage;

	private List list;

	private List headerList;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPerpage() {
		return perpage;
	}

	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getHeaderList() {
		return headerList;
	}

	public void setHeaderList(List headerList) {
		this.headerList = headerList;
	}

	public void setPageBean(PageBean page) {

		this.currentPage = page.getCurrentPage();
		this.list = page.getList();
		this.perpage = page.getPerpage();
		this.totalPage = page.getTotalPage();
		this.totalRecord = page.getTotalRecord();
		this.headerList = page.getHeaderList();
	}

	public static PageBeanResult success(String msg, PageBean pageBean) {
		PageBeanResult res = new PageBeanResult();
		res.setStatusCode(ResponseConstants.OK);
		res.setResultMsg(msg);
		res.setPageBean(pageBean);
		return res;
	}

	public static PageBeanResult fail(String msg) {
		PageBeanResult res = new PageBeanResult();
		res.setStatusCode(ResponseConstants.SERVER_ERROR);
		res.setResultMsg(msg);
		return res;

	}
}
