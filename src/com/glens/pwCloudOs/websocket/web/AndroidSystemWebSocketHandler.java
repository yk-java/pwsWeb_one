package com.glens.pwCloudOs.websocket.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.pwCloudOs.commuteMgr.monitor.dao.CommuteGpsDao;
import com.glens.pwCloudOs.commuteMgr.online.service.CpOnlineUserHistoryService;
import com.glens.pwCloudOs.commuteMgr.online.vo.CpOnlineUserHistoryVo;

public class AndroidSystemWebSocketHandler implements WebSocketHandler {

	private static Log logger = LogFactory
			.getLog(AndroidSystemWebSocketHandler.class);

	public static final String ONLINE = "在线";

	public static final String OFFLINE = "离线";

	private static final ArrayList<WebSocketSession> androidOnlineUserList;

	private SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.FORMAT_1);
	static {
		androidOnlineUserList = new ArrayList<WebSocketSession>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		androidOnlineUserList.add(session);
	}

	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		logger.debug("-----android-----handleMessage------------------");
		CpOnlineUserHistoryService service = (CpOnlineUserHistoryService) EAPContext
				.getContext().getBean("onlineUserService");
		String result = message.getPayload().toString();
		CpOnlineUserHistoryVo vo = JSON.parseObject(result,
				CpOnlineUserHistoryVo.class);
		vo.setLogintime(DateTimeUtil.formatDate(new Date(),
				DateTimeUtil.FORMAT_1));
		service.insertOnline(vo);
		// 将之前断开时间为空的数据重置为当前连接的时间
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("accountcode", vo.getAccountcode());
		paramsMap.put("id", vo.getId());
		service.updatebyMap(paramsMap);
		session.getAttributes().put("user", vo);
	}

	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		logger.debug("---android----handleTransportError---");
		CpOnlineUserHistoryService service = (CpOnlineUserHistoryService) EAPContext
				.getContext().getBean("onlineUserService");
		if (session.isOpen()) {
			CpOnlineUserHistoryVo vo = (CpOnlineUserHistoryVo) session
					.getAttributes().get("user");
			String offTime = DateTimeUtil.formatDate(new Date(),
					DateTimeUtil.FORMAT_1);
			vo.setOfftime(offTime);
			Long currentTime = Long.parseLong(DateTimeUtil
					.getLongStringByDate(sdf.parse(offTime)));
			Long startTime = Long.parseLong(DateTimeUtil
					.getLongStringByDate(sdf.parse(vo.getLogintime())));
			Long duration = (currentTime - startTime) / 1000;
			vo.setDuration(duration.intValue());
			service.updateOnline(vo);
			androidOnlineUserList.remove(session);
			session.close();
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		logger.debug("-----android-----afterConnectionClosed------------------");
		CpOnlineUserHistoryService service = (CpOnlineUserHistoryService) EAPContext
				.getContext().getBean("onlineUserService");
		CpOnlineUserHistoryVo vo = (CpOnlineUserHistoryVo) session
				.getAttributes().get("user");
		String offTime = DateTimeUtil.formatDate(new Date(),
				DateTimeUtil.FORMAT_1);
		vo.setOfftime(offTime);
		Long currentTime = Long.parseLong(DateTimeUtil.getLongStringByDate(sdf
				.parse(offTime)));
		Long startTime = Long.parseLong(DateTimeUtil.getLongStringByDate(sdf
				.parse(vo.getLogintime())));
		Long duration = (currentTime - startTime) / 1000;
		vo.setDuration(duration.intValue());
		service.updateOnline(vo);
		androidOnlineUserList.remove(session);
		session.close();
	}

	/**
	 * 查看某人是否在线
	 * 
	 * @param accountCode
	 * @return
	 */
	public static boolean isOnline(String accountCode) {
		boolean result = false;
		logger.debug("查询某人是否在线：" + accountCode);
		logger.debug("所有在线：" + androidOnlineUserList);
		for (int i = 0; i < androidOnlineUserList.size(); i++) {
			WebSocketSession session = androidOnlineUserList.get(i);
			CpOnlineUserHistoryVo vo = (CpOnlineUserHistoryVo) session
					.getAttributes().get("user");
			logger.debug("session值：" + vo);
			if (vo != null && StringUtil.isNotNull(vo.getAccountcode())
					&& StringUtil.isNotNull(accountCode)) {
				if (vo.getAccountcode().equals(accountCode)) {
					result = true;
					break;
				}
			}
		}

		return result;
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
