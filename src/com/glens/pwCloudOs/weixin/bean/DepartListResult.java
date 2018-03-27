package com.glens.pwCloudOs.weixin.bean;

import java.util.List;

public class DepartListResult extends WeixinResult {

	private List<Depart> department;

	public List<Depart> getDepartment() {
		return department;
	}

	public void setDepartment(List<Depart> department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "DepartListResult [department=" + department + "]";
	}

}
