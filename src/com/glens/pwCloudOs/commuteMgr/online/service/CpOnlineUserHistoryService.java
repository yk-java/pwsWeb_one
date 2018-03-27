package com.glens.pwCloudOs.commuteMgr.online.service;

import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.commuteMgr.online.dao.CpOnlineUserHistoryDao;
import com.glens.pwCloudOs.commuteMgr.online.vo.CpOnlineUserHistoryVo;

public class CpOnlineUserHistoryService extends EAPAbstractService {

	public void insertOnline(CpOnlineUserHistoryVo vo) {
		CpOnlineUserHistoryDao dao = (CpOnlineUserHistoryDao) getDao();
		dao.insertOnline(vo);
	}

	public void updateOnline(CpOnlineUserHistoryVo vo) {
		// TODO Auto-generated method stub
		CpOnlineUserHistoryDao dao = (CpOnlineUserHistoryDao) getDao();
		dao.updateOnline(vo);
	}

	public void updatebyMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		CpOnlineUserHistoryDao dao = (CpOnlineUserHistoryDao) getDao();
		dao.updatebyMap(paramsMap);
	}
}
