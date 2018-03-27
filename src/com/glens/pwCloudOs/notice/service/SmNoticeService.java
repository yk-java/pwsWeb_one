package com.glens.pwCloudOs.notice.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.utils.HttpUtil;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.sys.orgEmployee.employee.dao.MdEmployeeDao;
import com.glens.pwCloudOs.notice.dao.SmNoticeDao;
import com.glens.pwCloudOs.notice.dao.SmNoticeReadHistoryDao;
import com.glens.pwCloudOs.notice.dao.SmNoticeReceiveDao;
import com.glens.pwCloudOs.notice.vo.SmNotice;
import com.glens.pwCloudOs.notice.vo.SmNoticeReadHistory;
import com.glens.pwCloudOs.notice.vo.SmNoticeReceive;
import com.glens.pwCloudOs.notice.web.SmNoticeForm;
import com.glens.pwCloudOs.weixin.bean.WeiXinToken;
import com.glens.pwCloudOs.weixin.constant.Constants;
import com.glens.pwCloudOs.weixin.dao.WeixinDao;

public class SmNoticeService extends EAPAbstractService {
	private String weixinSwitch = "off";

	public String getWeixinSwitch() {
		return weixinSwitch;
	}

	public void setWeixinSwitch(String weixinSwitch) {
		this.weixinSwitch = weixinSwitch;
	}

	// 全部消息
	public static final String ALL_NOTICE = "0";

	// 未读消息
	public static final String UNREAD_NOTICE = "1";

	// 已读消息
	public static final String READ_NOTICE = "2";

	private SmNoticeReadHistoryDao smNoticeReadHistoryDao;

	private SmNoticeReceiveDao smNoticeReceiveDao;

	private WeixinDao weixinDao;

	private MdEmployeeDao mdEmployeeDao;

	/**
	 * 查询列表 （全部，已 读，未 读）
	 * 
	 * @param accountCode
	 * @param status
	 * @return
	 */
	public List<Map<String, Object>> queryNoticeList(String accountCode,
			String status) {
		List<Map<String, Object>> list = null;

		SmNoticeDao smNoticeDao = (SmNoticeDao) dao;

		if (status.equals(ALL_NOTICE)) {
			list = smNoticeDao.queryAllNoticeList(accountCode);
		} else if (status.equals(UNREAD_NOTICE)) {
			list = smNoticeDao.queryUnReadNoticeList(accountCode);
		} else if (status.equals(READ_NOTICE)) {
			list = smNoticeDao.queryReadNoticeList(accountCode);
		}

		return list;
	}

	/**
	 * 详情
	 * 
	 * @param accountCode
	 * @param rowid
	 * @return
	 */
	public SmNotice queryDetail(String accountCode, String rowid) {
		SmNoticeDao smNoticeDao = (SmNoticeDao) dao;
		SmNotice sm = (SmNotice) dao.findById(Long.parseLong(rowid));
		// 插入阅读记录
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("accountCode", accountCode);
		m.put("noticeId", rowid);
		List<SmNoticeReadHistory> list = smNoticeReadHistoryDao.queryList(m);
		if (list.size() == 0) {
			// 新增
			SmNoticeReadHistory sr = new SmNoticeReadHistory();
			sr.setNoticeId(Long.parseLong(rowid));
			sr.setAccountcode(accountCode);
			try {
				sr.setReadTime(DateTimeUtil.formatDate(new Date(),
						DateTimeUtil.FORMAT_1));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			smNoticeReadHistoryDao.insertSelective(sr);
		} else {
			// 更新
			SmNoticeReadHistory sr = list.get(0);
			try {
				sr.setReadTime(DateTimeUtil.formatDate(new Date(),
						DateTimeUtil.FORMAT_1));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			smNoticeReadHistoryDao.updateSelective(sr);
		}
		return sm;
	}

	/**
	 * @param source
	 *            来源
	 * 
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @param accountCodeList
	 *            接受人，如果为null，则发送至所有人
	 */
	public void sendMessage(String source, String title, String content,
			List<String> accountCodeList) {
		SmNoticeDao smNoticeDao = (SmNoticeDao) dao;
		try {
			SmNotice sm = new SmNotice();
			sm.setBtext(content);
			sm.setPublishDate(DateTimeUtil.formatDate(new Date(),
					DateTimeUtil.FORMAT_1));
			sm.setSysProcessFlag("1");
			sm.setTitle(title);
			sm.setSource(source);
			sm.setType("01");
			if (accountCodeList == null) {
				// 所有人
				sm.setTowho("0");
				smNoticeDao.insertSelective(sm);
			} else {
				// 部分
				sm.setTowho("1");
				smNoticeDao.insertSelective(sm);

				for (String accountCode : accountCodeList) {
					SmNoticeReceive sr = new SmNoticeReceive();
					sr.setAccountCode(accountCode);
					sr.setNoticeId(sm.getRowid());
					smNoticeReceiveDao.insertSelective(sr);
				}
			}
			if ("on".equals(weixinSwitch)) {
				// 发送到微信。。。。。。。。。。。。。。
				WeiXinToken token = weixinDao.getWeixinToken();
				JSONObject obj = new JSONObject();
				if (accountCodeList == null) {
					obj.put("touser", "@all");
				} else {
					StringBuffer sb = new StringBuffer();
					for (String accountCode : accountCodeList) {
						sb.append(getEmployeeJobNO(accountCode));
						sb.append("|");
					}
					obj.put("touser", sb.toString());
				}

				obj.put("msgtype", "text");
				obj.put("agentid", Constants.APPLICATION_ID);
				JSONObject textJson = new JSONObject();
				textJson.put("content", content);
				obj.put("text", textJson);
				String params = obj.toString();
				String _url = String.format(Constants.SEND_TEXT_MESSAGE_URL,
						token.getAccessToken());
				String result=HttpUtil.doPost(_url, params, "UTF-8");
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 发送微信消息
	 * 
	 * @param title
	 * @param content
	 * @param accountCodeList
	 */
	public void sendWeixinMessage(String title, String content,
			List<String> jobNoList) {
		// 发送到微信。。。。。。。。。。。。。。
		WeiXinToken token = weixinDao.getWeixinToken();
		JSONObject obj = new JSONObject();
		if (jobNoList == null) {
			obj.put("touser", "@all");
		} else {
			StringBuffer sb = new StringBuffer();
			for (String jobNo : jobNoList) {
				sb.append(jobNo);
				sb.append("|");
			}
			obj.put("touser", sb.toString());
		}

		obj.put("msgtype", "text");
		obj.put("agentid", Constants.APPLICATION_ID);
		JSONObject textJson = new JSONObject();
		textJson.put("content", content);
		obj.put("text", textJson);
		String params = obj.toString();
		String _url = String.format(Constants.SEND_TEXT_MESSAGE_URL,
				token.getAccessToken());
		String result = HttpUtil.doPost(_url, params, "UTF-8");
		System.out.println(result);
	}

	private String getEmployeeJobNO(String accountCode) {
		Map jMap = mdEmployeeDao.getJobNO(accountCode);
		String jobNo = "";
		if (jMap != null && jMap.get("JOB_NO") != null) {
			jobNo = (String) jMap.get("JOB_NO");
		}
		return jobNo;
	}

	public void setSmNoticeReadHistoryDao(
			SmNoticeReadHistoryDao smNoticeReadHistoryDao) {
		this.smNoticeReadHistoryDao = smNoticeReadHistoryDao;
	}

	public void setSmNoticeReceiveDao(SmNoticeReceiveDao smNoticeReceiveDao) {
		this.smNoticeReceiveDao = smNoticeReceiveDao;
	}

	public void setWeixinDao(WeixinDao weixinDao) {
		this.weixinDao = weixinDao;
	}

	public void setMdEmployeeDao(MdEmployeeDao mdEmployeeDao) {
		this.mdEmployeeDao = mdEmployeeDao;
	}

}
