package com.glens.pwCloudOs.OA.vo;

import java.util.List;

import com.glens.pwCloudOs.fm.expense.vo.FmMoneyReturn;

public class ExpensesResult {

	private String statusCode;

	private String resultMsg;

	private List<FmMoneyReturn> data;

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

	public List<FmMoneyReturn> getData() {
		return data;
	}

	public void setData(List<FmMoneyReturn> data) {
		this.data = data;
	}

}
