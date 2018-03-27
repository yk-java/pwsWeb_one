package com.glens.eap.platform.framework.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.beans.ValueObject;

public class PageBean implements ValueObject {

	public static final int DEFAULT_PER_PAGE = 10;

	private int currentPage = 1;
	private int totalPage = 1;
	private int totalRecord = 0;
	private int perpage = 10;
	private List list;

	private List headerList = new ArrayList<String>();

	public PageBean() {

		totalPage = totalRecord % perpage == 0 ? totalRecord / perpage
				: totalRecord / perpage + 1;

		if (totalPage == 0) {
			totalPage = 1;
		}

		if (this.currentPage <= this.totalPage)
			return;
		this.currentPage = this.totalPage;
	}

	public PageBean(int totalRecord, int currentPage) {
		this.totalRecord = totalRecord;
		if (currentPage > 0) {

			this.currentPage = currentPage;
		}

		totalPage = totalRecord % perpage == 0 ? totalRecord / perpage
				: totalRecord / perpage + 1;

		if (totalPage == 0) {
			totalPage = 1;
		}

		if (this.currentPage <= this.totalPage)
			return;
		this.currentPage = this.totalPage;
	}

	public PageBean(int totalRecord, int currentPage, int perpage) {

		if (currentPage > 0) {

			this.currentPage = currentPage;
		}

		if (perpage > 0) {

			this.perpage = perpage;
		}

		this.totalRecord = totalRecord;

		totalPage = this.totalRecord % this.perpage == 0 ? this.totalRecord
				/ this.perpage : this.totalRecord / this.perpage + 1;

		if (totalPage == 0) {
			totalPage = 1;
		}

		if (this.currentPage <= this.totalPage)
			return;
		this.currentPage = this.totalPage;
	}

	public PageBean(int totalRecord, Map properties) {

		this.totalRecord = totalRecord;

		if (properties != null) {

			if (properties.containsKey("currentPage")) {

				this.currentPage = (Integer) properties.get("currentPage");
			}

			if (properties.containsKey("perpage")) {

				this.perpage = (Integer) properties.get("perpage");
			}
		}

		totalPage = totalRecord % perpage == 0 ? totalRecord / perpage
				: totalRecord / perpage + 1;

		if (totalPage == 0) {
			totalPage = 1;
		}

		if (this.currentPage <= this.totalPage)
			return;
		this.currentPage = this.totalPage;
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

	/**
	 * Edit by MaDx
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getPageParamsFromReq(
			HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("currentPage", request.getParameter("currentPage"));
		params.put("perpage", request.getParameter("perpage"));// pageSize
		return params;
	}

	public List getHeaderList() {
		return headerList;
	}

	public void setHeaderList(List headerList) {
		this.headerList = headerList;
	}

}
