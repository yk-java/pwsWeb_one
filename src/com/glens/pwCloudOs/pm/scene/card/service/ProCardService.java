package com.glens.pwCloudOs.pm.scene.card.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.scene.card.dao.ProCardDao;
import com.glens.pwCloudOs.pm.scene.card.vo.ProCard;

public class ProCardService extends EAPAbstractService {

	public List chooseCardsList(Map map) {
		ProCardDao dao = (ProCardDao) getDao();
		return dao.chooseCardsList(map);
	}

	public void insertCard(ValueObject vo) {
		// TODO Auto-generated method stub
		ProCardDao dao = (ProCardDao) getDao();
		dao.insertCard(vo);
	}

	public void updateCard(ValueObject vo) {
		// TODO Auto-generated method stub
		ProCardDao dao = (ProCardDao) getDao();
		dao.updateCard(vo);
	}

	public void deleteCard(ValueObject vo) throws Exception {
		// TODO Auto-generated method stub
		ProCardDao dao = (ProCardDao) getDao();
		ProCard card = (ProCard) vo;
		card.setSysProcessFlag("0");
		card.setSysDeleteTime(DateTimeUtil.formatDate(new Date(),
				DateTimeUtil.FORMAT_1));
		dao.updateCard(card);
	}

	public Map get(Long rowid) {
		// TODO Auto-generated method stub
		ProCardDao dao = (ProCardDao) getDao();
		return dao.get(rowid);
	}

}
