package com.glens.pwCloudOs.materielMg.goods.service;

import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.materielMg.goods.dao.GoodsDao;

public class GoodsService extends EAPAbstractService {

	public Map getGoodsCount(Map paramsMap) {
		// TODO Auto-generated method stub
		GoodsDao dao = (GoodsDao) getDao();
		return dao.getGoodsCount(paramsMap);
	}

}
