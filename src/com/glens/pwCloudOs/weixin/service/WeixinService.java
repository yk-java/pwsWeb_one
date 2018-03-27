package com.glens.pwCloudOs.weixin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.utils.HttpUtil;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.sys.orgEmployee.employee.dao.MdEmployeeDao;
import com.glens.eap.sys.orgEmployee.employee.vo.MdEmployee;
import com.glens.eap.sys.orgEmployee.orgunit.dao.OrgUnitDao;
import com.glens.pwCloudOs.weixin.bean.User;
import com.glens.pwCloudOs.weixin.bean.UserListResult;
import com.glens.pwCloudOs.weixin.bean.WeiXinToken;
import com.glens.pwCloudOs.weixin.bean.WeixinResult;
import com.glens.pwCloudOs.weixin.constant.Constants;
import com.glens.pwCloudOs.weixin.dao.WeixinDao;
import com.glens.pwCloudOs.weixin.util.WeixinUtils;

public class WeixinService extends EAPAbstractService {

	// 全量
	private static final String SYNC_ALL = "1";

	// 增量
	private static final String SYNC_ADD = "2";

	private static final int ROOT_DEPARTID = 19;

	private OrgUnitDao orgUnitDao;

	private MdEmployeeDao mdEmployeeDao;

	public String syncAllDepartAndUser(String flag, String TaskCode,
			Map<String, String> resultMap) throws Exception {

		String returnResult = "";

		WeixinDao weixinDao = (WeixinDao) getDao();
		WeiXinToken token = weixinDao.getWeixinToken();
		/*
		 * if (StringUtil.isNotNull(flag) && flag.equals(SYNC_ALL)) {
		 * 
		 * 
		 * // 删除所有人员 UserListResult result = WeixinUtils.getDepartUserList(
		 * ROOT_DEPARTID, token);
		 * 
		 * // 删除所有人员
		 * 
		 * for (int i = 0; i < result.getUserlist().size(); i++) { User user =
		 * result.getUserlist().get(i); WeixinUtils.deleteUser(user, token,
		 * resultMap, TaskCode); }
		 * 
		 * 
		 * // 批量删除人员 List<User> userList = result.getUserlist();
		 * WeixinUtils.deleteBatchUser(userList, token, resultMap, TaskCode);
		 * 
		 * // 删除所有部门 DepartListResult depart = WeixinUtils.getDepartList(token);
		 * while (depart.getDepartment().size() > 0) { for (int i = 0; i <
		 * depart.getDepartment().size(); i++) { Depart d =
		 * depart.getDepartment().get(i); WeixinUtils.deleteDepart(d, token,
		 * resultMap, TaskCode); } depart = WeixinUtils.getDepartList(token); }
		 * 
		 * // 删除部门历史表 orgUnitDao.deleteHistory();
		 * 
		 * // 删除人员历史表 mdEmployeeDao.deleteHistory();
		 * 
		 * // 同步所有部门 List<OrgUnit> orgUnitList = orgUnitDao
		 * .queryOrgUnitList(Constants.COMPANY_CODE); for (OrgUnit ou :
		 * orgUnitList) { WeixinUtils.createDepart(ou, token, resultMap,
		 * TaskCode); // ou.setSysCreateTime(sysDate);
		 * orgUnitDao.insertHistory(ou); }
		 * 
		 * // 同步所有在职人员 List<MdEmployee> employeeList =
		 * mdEmployeeDao.queryAllEmployee(); for (MdEmployee em : employeeList)
		 * { // 暂时注释，全部推送有次数限制 WeixinUtils.createUser(em, token, resultMap,
		 * TaskCode); mdEmployeeDao.insertHistory(em); } returnResult =
		 * "共同步部门数据：" + orgUnitList.size() + "条,同步人员数据：" + employeeList.size() +
		 * "条";
		 * 
		 * // 不记录日志 WeixinSyncLog log = new WeixinSyncLog();
		 * log.setRemarks("同步部门数据：" + orgUnitList.size() + "条");
		 * log.setSyncDate(new Date()); log.setSyncType("1"); dao.insert(log);
		 * 
		 * log = new WeixinSyncLog(); log.setRemarks("同步人员数据：" +
		 * employeeList.size() + "条"); log.setSyncDate(new Date());
		 * log.setSyncType("2"); dao.insert(log);
		 * 
		 * 
		 * } else if (flag.equals(SYNC_ADD)) {
		 * 
		 * // 增量同步
		 * 
		 * }
		 */

		// 增量同步
		// 清理离职人员
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		List<MdEmployee> list = mdEmployeeDao.queryLeaveEmployee(paramsMap);
		List<User> deletedUserList = new ArrayList<User>();
		for (MdEmployee m : list) {
			User u = new User();
			u.setUserid(m.getJobNo());
			u.setName(m.getEmployeename());
			deletedUserList.add(u);
		}
		WeixinUtils
				.deleteBatchUser(deletedUserList, token, resultMap, TaskCode);

		// 新增人员（微信端不存在的人员）
		UserListResult result = WeixinUtils.getDepartUserList(ROOT_DEPARTID,
				token);
		List<User> userList = result.getUserlist();

		Map<String, String> wxServerMap = new HashMap<String, String>();
		for (User u : userList) {
			wxServerMap.put(u.getUserid(), "");
		}

		// 云平台所有在职人员
		List<MdEmployee> localEmployeeList = mdEmployeeDao.queryAllEmployee();

		for (MdEmployee m : localEmployeeList) {
			// 如果微信服务端不存在，就添加进去
			if (!wxServerMap.containsKey(m.getJobNo())) {
				WeixinUtils.createUser(m, token, resultMap, TaskCode);
			}
		}

		returnResult = "同步完成！";

		return returnResult;
	}

	public void setOrgUnitDao(OrgUnitDao orgUnitDao) {
		this.orgUnitDao = orgUnitDao;
	}

	public void setMdEmployeeDao(MdEmployeeDao mdEmployeeDao) {
		this.mdEmployeeDao = mdEmployeeDao;
	}

	public WeixinResult sendValidateCode(String code, String mobilePhone,
			String accountName) {
		String result = "";
		WeixinDao weixinDao = (WeixinDao) getDao();
		WeiXinToken token = weixinDao.getWeixinToken();
		WeixinResult r = new WeixinResult();

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// paramsMap.put("mobilePhone", mobilePhone);
		paramsMap.put("accountName", accountName);
		Map<String, Object> m = mdEmployeeDao.queryEmployeeInfo(paramsMap);
		if (null == m) {
			r.setErrcode("1");
			r.setErrmsg("帐号不存在！");
			return r;
		}
		m = mdEmployeeDao.queryEmployeeByMobile(mobilePhone);
		if (null == m) {
			r.setErrcode("1");
			r.setErrmsg("手机号不存在！");
			return r;
		}
		paramsMap.clear();
		paramsMap.put("mobilePhone", mobilePhone);
		paramsMap.put("accountName", accountName);
		m = mdEmployeeDao.queryEmployeeInfo(paramsMap);
		if (null == m) {
			r.setErrcode("1");
			r.setErrmsg("帐号与手机不匹配！");
			return r;
		}
		String _url = String.format(Constants.SEND_TEXT_MESSAGE_URL,
				token.getAccessToken());
		if (m != null && m.get("JOB_NO") != null) {
			JSONObject obj = new JSONObject();
			obj.put("touser", m.get("JOB_NO") + "");
			obj.put("msgtype", "text");
			obj.put("agentid", 0);
			JSONObject textJson = new JSONObject();
			textJson.put("content", "验证码为:" + code);
			obj.put("text", textJson);
			System.out.println(obj);
			result = HttpUtil.doPost(_url, obj.toString(), "UTF-8");
			r = JSON.parseObject(result, WeixinResult.class);
		}
		return r;
	}

}
