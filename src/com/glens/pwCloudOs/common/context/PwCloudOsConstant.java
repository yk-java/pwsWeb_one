/**
 * @Title: PwCloudOsConstant.java
 * @Package com.glens.pwCloudOs.common.context
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-12-29 下午4:53:11
 * @version V1.0
 */

package com.glens.pwCloudOs.common.context;

import java.util.HashMap;
import java.util.Map;

import com.glens.pwCloudOs.weixin.bean.AccessToken;

/**
 * 平台常量类
 * 
 * @author
 * @version V1.0
 */

public class PwCloudOsConstant {

	/**
	 * 区域经理
	 */
	public static final String DISTRICT_MANAGER_ROLE_CODE = "015";

	/**
	 * 部门经理
	 */
	public static final String DEPT_MANAGER_ROLE_CODE = "016";

	/**
	 * 部门副经理
	 */
	public static final String DETUTY_MANAGER_ROLE_CODE = "R20171011104517924566";

	/**
	 * 项目经理（项目负责人）
	 */
	public static final String PRO_MANAGER_ROLE_CODE = "004";

	public static final String DEPT_SUP_ROLE_CODE = "017";// 技术总监

	public static final String DEPT_SUP = "9,16";// 部门监管管理的部门

	public static final String JL_INNERWORKER_ROLE_CODE = "R20161107150600707427";

	public static final String JL_PRO_UNIT_ROLE_CODE = "002002";

	public static final String PRO_WATCHER_ROLE_CODE = "003";

	public static final String GOODS_MANAGER = "R001";

	// 上传临时材料
	public static final String UPLOAD_URL = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";

	/**
	 * 资产专员
	 */
	public static final String ASSET_MANAGER = "008";

	// 微信token存储
	public static final Map<Integer, AccessToken> TOKENMAP = new HashMap<Integer, AccessToken>();

	// 输电通道预警应用ID
	public static final int COMPANY_HELP_AGENTID = 0;

	// 获取accessToken
	public static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s";

	// 企业号的标识
	public static final String CorpID = "wxcd3760bbd2420b8c";

	// 输电通道预警应用凭证密钥
	public static final String COMPANY_HELP_SECRET = "eJbXmTtJVqHkKEr0VnlXgqongvXw8NE9KT-zviBlIfc";

	// 发送消息
	public static final String SEND_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s";

}
