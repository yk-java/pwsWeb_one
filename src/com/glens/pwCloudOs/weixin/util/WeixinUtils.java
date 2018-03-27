package com.glens.pwCloudOs.weixin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.utils.HttpUtil;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.sys.orgEmployee.employee.vo.MdEmployee;
import com.glens.eap.sys.orgEmployee.orgunit.vo.OrgUnit;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.weixin.bean.AccessToken;
import com.glens.pwCloudOs.weixin.bean.Attrs;
import com.glens.pwCloudOs.weixin.bean.Depart;
import com.glens.pwCloudOs.weixin.bean.DepartListResult;
import com.glens.pwCloudOs.weixin.bean.Extattr;
import com.glens.pwCloudOs.weixin.bean.MPRoot;
import com.glens.pwCloudOs.weixin.bean.Mpnews;
import com.glens.pwCloudOs.weixin.bean.User;
import com.glens.pwCloudOs.weixin.bean.UserListResult;
import com.glens.pwCloudOs.weixin.bean.WeiXinToken;
import com.glens.pwCloudOs.weixin.constant.Constants;

public class WeixinUtils {

	/**
	 * 获取accessToken
	 * 
	 * @return
	 */
	public static WeiXinToken getAccessToken() {
		String result = HttpUtil.doGet(
				String.format(Constants.ACCESS_TOKEN_URL, Constants.CorpID,
						Constants.Secret), "UTF-8");
		JSONObject obj = JSON.parseObject(result);
		System.out.println(obj);
		WeiXinToken token = new WeiXinToken();
		token.setAccessToken(obj.getString("access_token"));
		token.setExpiresIn(obj.getInteger("expires_in"));
		return token;
	}

	/**
	 * 查询所有部门
	 */
	public static DepartListResult getDepartList(WeiXinToken token) {
		String result = HttpUtil.doGet(
				String.format(Constants.QUERY_DEPART_URL,
						token.getAccessToken()), "UTF-8");
		DepartListResult list = JSON
				.parseObject(result, DepartListResult.class);
		// 去掉根部门
		List<Depart> dList = new ArrayList<Depart>();
		for (Depart d : list.getDepartment()) {
			if (d.getId() != 1) {
				dList.add(d);
			}
		}

		list.getDepartment().clear();
		list.getDepartment().addAll(dList);
		return list;
	}

	/**
	 * 获取部门人员
	 */
	public static UserListResult getDepartUserList(int departId,
			WeiXinToken token) {
		String result = HttpUtil.doGet(
				String.format(Constants.QUERY_DEPART_USER_URL,
						token.getAccessToken(), departId, 1, 0), "UTF-8");

		UserListResult list = JSON.parseObject(result, UserListResult.class);

		return list;
	}

	/**
	 * 获取部门人员详情
	 */
	public static UserListResult getDepartUserDetailList(int departId,
			WeiXinToken token) {
		String result = HttpUtil.doGet(
				String.format(Constants.QUERY_DEPART_USER_DETAIL_URL,
						token.getAccessToken(), departId, 1, 0), "UTF-8");

		UserListResult list = JSON.parseObject(result, UserListResult.class);

		return list;
	}

	/**
	 * 批量删除人员
	 * 
	 * @param userList
	 * @param token
	 * @param resultMap
	 * @param taskCode
	 */
	public static void deleteBatchUser(List<User> userList, WeiXinToken token,
			Map<String, String> resultMap, String taskCode) {
		List<String> userIdList = new ArrayList<String>();
		for (int i = 0; i < userList.size(); i++) {
			User u = userList.get(i);
			// 删除
			// deleteBatch(userIdList, resultMap, token, taskCode);
			deleteUser(u, token, resultMap, taskCode);
		}

	}

	private static void deleteBatch(List<String> userIdList,
			Map<String, String> resultMap, WeiXinToken token, String taskCode) {
		JSONArray arr = new JSONArray();
		for (String id : userIdList) {
			arr.add(id);
		}
		JSONObject obj = new JSONObject();
		obj.put("useridlist", arr);
		String result = HttpUtil.doPost(
				String.format(Constants.BATCH_DELETE_USER_URL,
						token.getAccessToken()), obj.toString(), "UTF-8");
		resultMap.put(taskCode, "正在删除离职人员");
		System.out.println("批量删除用户：" + result);
	}

	/**
	 * 删除部门
	 * 
	 * @param d
	 */
	public static void deleteDepart(Depart d, WeiXinToken token,
			Map<String, String> resultMap, String TaskCode) {
		// TODO Auto-generated method stub
		String result = HttpUtil.doGet(
				String.format(Constants.DELETE_DEPART_URL,
						token.getAccessToken(), d.getId()), "UTF-8");
		resultMap.put(TaskCode, "正在删除部门：" + d.getName());
		System.out.println("删除企业号部门：" + result);

	}

	/**
	 * 创建部门
	 * 
	 * @param ou
	 */
	public static void createDepart(OrgUnit ou, WeiXinToken token,
			Map<String, String> resultMap, String TaskCode) {
		Depart d = new Depart();
		d.setId(Integer.parseInt(ou.getUnitCode()));
		d.setName(ou.getUnitName());
		int pId = Integer.parseInt(ou.getParentUnit());
		if (pId == 0) {
			pId = 1;
		}
		d.setParentid(pId);
		// 排序，小的排在前面
		d.setOrder(ou.getRowid().intValue());
		String params = JSON.toJSONString(d);
		String result = HttpUtil.doPost(
				String.format(Constants.CREATE_DEPART_URL,
						token.getAccessToken()), params, "UTF-8");
		resultMap.put(TaskCode, "同步部门：" + ou.getUnitName());
		System.out.println("创建部门：" + result);
	}

	/**
	 * 创建用户
	 * 
	 * @param em
	 */
	public static void createUser(MdEmployee em, WeiXinToken token,
			Map<String, String> resultMap, String TaskCode) {
		if (StringUtil.isNotNull(em.getMobilephone1())
				|| StringUtil.isNotNull(em.getMail())) {
			User u = new User();
			// 用户帐号唯一
			u.setUserid(em.getJobNo());

			u.setName(em.getEmployeename());
			// 部门
			u.setDepartment(em.getUnitCode());
			// 手机号
			u.setMobile(em.getMobilephone1());
			// 性别
			u.setGender(Integer.parseInt(em.getSex()) == 0 ? 1 : 2);
			// 邮箱
			u.setEmail(em.getMail());
			// 职位
			u.setPosition(em.getJobName());

			// 微信号
			u.setWeixinid(em.getWeixinid());

			// 启用
			u.setEnable(1);

			Attrs attr = new Attrs();
			attr.setName("出生日期");
			attr.setValue(em.getBirthday());
			Extattr exAttr = new Extattr();
			exAttr.getAttrs().add(attr);
			u.setExtattr(exAttr);

			// extattr
			String params = JSON.toJSONString(u);
			String result = HttpUtil.doPost(
					String.format(Constants.CREATE_USER_URL,
							token.getAccessToken()), params, "UTF-8");
			resultMap.put(TaskCode, "新增人员：" + em.getEmployeename());
			System.out.println("新增用户：" + em.getEmployeename() + "," + result);
		}
	}

	/**
	 * 创建用户
	 * 
	 * @param em
	 */
	public static void createUser(MdEmployee em, WeiXinToken token) {
		if (StringUtil.isNotNull(em.getMobilephone1())
				|| StringUtil.isNotNull(em.getMail())) {
			User u = new User();
			// 用户帐号唯一
			u.setUserid(em.getJobNo());

			u.setName(em.getEmployeename());
			// 部门
			u.setDepartment(em.getUnitCode());
			// 手机号
			u.setMobile(em.getMobilephone1());
			// 性别
			u.setGender(Integer.parseInt(em.getSex()) == 0 ? 1 : 2);
			// 邮箱
			u.setEmail(em.getMail());
			// 职位
			u.setPosition(em.getJobName());

			// 微信号
			if (StringUtil.isNotNull(em.getWeixinid())) {
				u.setWeixinid(em.getWeixinid());
			}

			u.setEnable(1);

			Attrs attr = new Attrs();
			attr.setName("出生日期");
			attr.setValue(em.getBirthday());
			Extattr exAttr = new Extattr();
			exAttr.getAttrs().add(attr);
			u.setExtattr(exAttr);

			// extattr
			String params = JSON.toJSONString(u);
			String result = HttpUtil.doPost(
					String.format(Constants.CREATE_USER_URL,
							token.getAccessToken()), params, "UTF-8");
			System.out.println("新增用户：" + result);
		}
	}

	/**
	 * 更新用户
	 * 
	 * @param em
	 */
	public static void updateUser(MdEmployee em, WeiXinToken token,
			Map<String, String> resultMap, String TaskCode) {

		User u = new User();
		// 用户帐号唯一
		u.setUserid(em.getJobNo());

		u.setName(em.getEmployeename());
		// 部门
		u.setDepartment(em.getUnitCode());
		// 手机号
		u.setMobile(em.getMobilephone1());
		// 性别
		u.setGender(Integer.parseInt(em.getSex()) == 0 ? 1 : 2);
		// 邮箱
		u.setEmail(StringUtil.isNotNull(em.getMail()) ? em.getMail() : "");
		// 职位
		u.setPosition(em.getJobName());
		// 启用
		u.setEnable(1);

		// 微信号
		if (StringUtil.isNotNull(em.getWeixinid()))
			u.setWeixinid(em.getWeixinid());

		Attrs attr = new Attrs();
		attr.setName("出生日期");
		attr.setValue(em.getBirthday());
		Extattr exAttr = new Extattr();
		exAttr.getAttrs().add(attr);
		u.setExtattr(exAttr);

		// extattr
		String params = JSON.toJSONString(u);
		String result = HttpUtil.doPost(String.format(
				Constants.UPDATE_USER_URL, token.getAccessToken()), params,
				"UTF-8");
		resultMap.put(TaskCode, "更新人员信息：" + em.getEmployeename());
		System.out.println("更新用户：" + result);
	}

	/**
	 * 更新用户
	 * 
	 * @param em
	 */
	public static void updateUser(MdEmployee em, WeiXinToken token) {

		User u = new User();
		// 用户帐号唯一
		u.setUserid(em.getJobNo());

		u.setName(em.getEmployeename());
		// 部门
		u.setDepartment(em.getUnitCode());
		// 手机号
		u.setMobile(em.getMobilephone1());
		// 性别
		u.setGender(Integer.parseInt(em.getSex()) == 0 ? 1 : 2);
		// 邮箱
		u.setEmail(StringUtil.isNotNull(em.getMail()) ? em.getMail() : "");
		// 职位
		u.setPosition(em.getJobName());
		// 启用
		u.setEnable(1);

		// 微信号
		if (StringUtil.isNotNull(em.getWeixinid()))
			u.setWeixinid(em.getWeixinid());

		Attrs attr = new Attrs();
		attr.setName("出生日期");
		attr.setValue(em.getBirthday());
		Extattr exAttr = new Extattr();
		exAttr.getAttrs().add(attr);
		u.setExtattr(exAttr);

		// extattr
		String params = JSON.toJSONString(u);
		String result = HttpUtil.doPost(String.format(
				Constants.UPDATE_USER_URL, token.getAccessToken()), params,
				"UTF-8");
		System.out.println("更新用户：" + result);
	}

	/**
	 * 删除人员
	 * 
	 * @param user
	 */
	public static void deleteUser(User user, WeiXinToken token,
			Map<String, String> resultMap, String TaskCode) {
		// TODO Auto-generated method stub
		String result = HttpUtil.doGet(String.format(Constants.DELETE_USER_URL,
				token.getAccessToken(), user.getUserid()), "UTF-8");
		resultMap.put(TaskCode, "删除离职人员：" + user.getName());
		System.out.println("删除企业号人员：" + user.getName());
	}

	/**
	 * 删除人员
	 * 
	 * @param user
	 */
	public static void deleteUser1(MdEmployee em, WeiXinToken token) {
		// TODO Auto-generated method stub
		String result = HttpUtil.doGet(String.format(Constants.DELETE_USER_URL,
				token.getAccessToken(), em.getJobNo()), "UTF-8");
		System.out.println("删除企业号人员：" + result);
	}

	/**
	 * 根据应用ID获取accessToken
	 * 
	 * @return
	 */
	public static synchronized AccessToken getWXAccessToken(int agentId) {
		AccessToken token = null;
		token = PwCloudOsConstant.TOKENMAP.get(agentId);
		switch (agentId) {
		case PwCloudOsConstant.COMPANY_HELP_AGENTID:
			if (token == null) {
				token = getCompanyHelpToken();
			} else {
				long currentTime = (System.currentTimeMillis() - token
						.getCreateTime()) / 1000;
				if (currentTime >= token.getExpires_in()) {
					// 重新获取
					token = getCompanyHelpToken();
				}
			}
			PwCloudOsConstant.TOKENMAP.put(agentId, token);
			break;
		}

		return PwCloudOsConstant.TOKENMAP.get(agentId);
	}

	private static AccessToken getCompanyHelpToken() {
		// TODO Auto-generated method stub
		AccessToken token;
		String result = HttpUtil.doGet(String.format(
				PwCloudOsConstant.ACCESS_TOKEN_URL, PwCloudOsConstant.CorpID,
				PwCloudOsConstant.COMPANY_HELP_SECRET), "UTF-8");
		token = JSONObject.parseObject(result, AccessToken.class);
		token.setCreateTime(System.currentTimeMillis());
		return token;
	}

	/**
	 * 发送图文消息到
	 * 
	 * @param access_token
	 * @param mediaId
	 * @param weinxinId
	 * @param imageAlarm
	 * @param mm
	 */
	public static void pushMpNews(String access_token, String mediaId,
			String weinxinId, Mpnews mpnews, int agentId) {
		MPRoot root = new MPRoot();
		root.setMpnews(mpnews);
		root.setTouser(weinxinId);
		root.setMsgtype("mpnews");
		root.setAgentid(agentId);
		String params = JSON.toJSON(root).toString();
		System.out.println(JSON.toJSON(root));
		String result = HttpUtil
				.doPost(String.format(PwCloudOsConstant.SEND_MESSAGE_URL,
						access_token), params, "UTF-8");
		System.out.println(result);

	}
}
