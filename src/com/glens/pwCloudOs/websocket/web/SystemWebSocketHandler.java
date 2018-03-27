package com.glens.pwCloudOs.websocket.web;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.pwCloudOs.km.read.dao.KmStudyReadDao;
import com.glens.pwCloudOs.km.read.vo.KmStudyRead;

public class SystemWebSocketHandler implements WebSocketHandler {

	private static final ArrayList<WebSocketSession> connectUserList;

	private static final String BEGIN_READ = "1";

	static {
		connectUserList = new ArrayList<WebSocketSession>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		connectUserList.add(session);
	}

	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		System.out.println("---handleMessage---");
		KmStudyReadDao dao = (KmStudyReadDao) EAPContext.getContext().getBean(
				"kmStudyReadDao");
		String result = message.getPayload().toString();
		JSONObject jsonResult = JSON.parseObject(result);
		String type = jsonResult.getString("type");
		if (type.equals(BEGIN_READ)) {
			// 记录阅读时间
			String employeecode = jsonResult.getString("employeecode");
			String fileCode = jsonResult.getString("fileCode");
			KmStudyRead read = new KmStudyRead();
			read.setEmployeecode(employeecode);
			read.setFileCode(fileCode);
			read.setSysProcessFlag("1");
			read.setReadDate(DateTimeUtil.formatDate(new Date(),
					DateTimeUtil.FORMAT_1));
			dao.insertSelective(read);
			session.getAttributes().put("read", read);
		}

	}

	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		System.out.println("---handleTransportError---");
		KmStudyReadDao dao = (KmStudyReadDao) EAPContext.getContext().getBean(
				"kmStudyReadDao");
		if (session.isOpen()) {
			KmStudyRead r = (KmStudyRead) session.getAttributes().get("read");
			r.setLeaveDate(DateTimeUtil.formatDate(new Date(),
					DateTimeUtil.FORMAT_1));
			dao.updateSelective(r);
			if (r != null && StringUtil.isNotNull(r.getFileCode())) {
				// 更新阅读次数
				dao.updatereadNum(r.getFileCode());
			}
			connectUserList.remove(session);
			session.close();
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		System.out.println("---afterConnectionClosed---");
		KmStudyReadDao dao = (KmStudyReadDao) EAPContext.getContext().getBean(
				"kmStudyReadDao");
		KmStudyRead r = (KmStudyRead) session.getAttributes().get("read");
		r.setLeaveDate(DateTimeUtil.formatDate(new Date(),
				DateTimeUtil.FORMAT_1));
		dao.updateSelective(r);
		if (r != null && StringUtil.isNotNull(r.getFileCode())) {
			// 更新阅读次数
			dao.updatereadNum(r.getFileCode());
		}

		connectUserList.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
