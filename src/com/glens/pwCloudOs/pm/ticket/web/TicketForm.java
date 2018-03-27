package com.glens.pwCloudOs.pm.ticket.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;


@ValueObjectProcessor(clazz="com.glens.pwCloudOs.pm.ticket.vo.TicketVo")
public class TicketForm extends ControllerForm {
	
	private Long rowid;
	private String companyCode;
	private String proNo;
	private String proName;
	private String workticketDate;
	private int workticketNumber;
	private String startNo;
	private String endNo;
	private String remarks;
	
	private String fromDate;
	private String toDate;
	

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

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
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

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getWorkticketDate() {
		return workticketDate;
	}

	public void setWorkticketDate(String workticketDate) {
		this.workticketDate = workticketDate;
	}

	public int getWorkticketNumber() {
		return workticketNumber;
	}

	public void setWorkticketNumber(int workticketNumber) {
		this.workticketNumber = workticketNumber;
	}

	public String getStartNo() {
		return startNo;
	}

	public void setStartNo(String startNo) {
		this.startNo = startNo;
	}

	public String getEndNo() {
		return endNo;
	}

	public void setEndNo(String endNo) {
		this.endNo = endNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("proNo", proNo);
		params.put("proName", proName);
		params.put("workticketDate", workticketDate);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		return params;
	}

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
