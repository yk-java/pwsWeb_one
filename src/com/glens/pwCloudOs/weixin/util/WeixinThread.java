package com.glens.pwCloudOs.weixin.util;

import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.sys.orgEmployee.employee.vo.MdEmployee;
import com.glens.pwCloudOs.weixin.bean.WeiXinToken;
import com.glens.pwCloudOs.weixin.dao.WeixinDao;

public class WeixinThread extends Thread {

	private MdEmployee employee;

	/**
	 * 1 新增 2更新 3删除
	 */
	private String type;

	public WeixinThread(MdEmployee employee, String type) {
		super();
		this.employee = employee;
		this.type = type;
	}

	@Override
	public void run() {
		WeixinDao weixinDao = (WeixinDao) EAPContext.getContext().getBean(
				"weixinDao");
		WeiXinToken token = weixinDao.getWeixinToken();
		if (type.equals("1")) {
			WeixinUtils.createUser(employee, token);
		} else if (type.equals("2")) {
			WeixinUtils.updateUser(employee, token);
		} else if (type.equals("3")) {
			WeixinUtils.deleteUser1(employee, token);
		}
	}
}
