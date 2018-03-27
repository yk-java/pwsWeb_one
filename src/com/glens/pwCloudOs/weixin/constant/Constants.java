package com.glens.pwCloudOs.weixin.constant;

public class Constants {

	// 批量删除人员数量
	public static final int BATCH_DELETE_USER_COUNT = 200;

	// 公司编码
	public static final String COMPANY_CODE = "001";

	// 应用ID
	public static final int APPLICATION_ID = 0;

	// 企业号的标识
	public static final String CorpID = "wxcd3760bbd2420b8c";

	// 管理组凭证密钥
	public static final String Secret = "a3fDHxbH-Fs2UPfOa_NFLOBkzMFOtLZBkcbdn6ruDFwBUJie0MptLg73Mu9fuQjA";

	// 获取accessToken
	public static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s";

	// 创建部门
	public static final String CREATE_DEPART_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=%s";

	// 更新部门
	public static final String UPDATE_DEPART_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/update";

	// 删除部门
	public static final String DELETE_DEPART_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=%s&id=%d";

	// 获取部门列表
	public static final String QUERY_DEPART_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=%s";

	// 创建成员
	public static final String CREATE_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=%s";

	// 更新成员
	public static final String UPDATE_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=%s";

	// 删除成员
	public static final String DELETE_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=%s&userid=%s";

	// 批量删除成员
	public static final String BATCH_DELETE_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=%s";

	// 获取成员
	public static final String QUERY_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/get";

	// 获取部门成员
	public static final String QUERY_DEPART_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=%s&department_id=%d&fetch_child=%d&status=%d";

	// 获取部门成员(详情)
	public static final String QUERY_DEPART_USER_DETAIL_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=%s&department_id=%d&fetch_child=%d&status=%d";

	// 发送消息
	public static final String SEND_TEXT_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s";

}
