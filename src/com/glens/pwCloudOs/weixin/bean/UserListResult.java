package com.glens.pwCloudOs.weixin.bean;

import java.util.List;

public class UserListResult extends WeixinResult {

	private List<User> userlist;

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}

}
