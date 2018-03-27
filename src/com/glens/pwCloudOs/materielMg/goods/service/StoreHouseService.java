package com.glens.pwCloudOs.materielMg.goods.service;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.materielMg.goods.dao.StoreHouseDao;
import com.glens.pwCloudOs.materielMg.goods.vo.StoreHouse;

public class StoreHouseService extends EAPAbstractService {

	public List<Map<String, Object>> getStoreHouseList() {
		// TODO Auto-generated method stub
		StoreHouseDao dao = (StoreHouseDao) getDao();
		return dao.getStoreHouseList();
	}

}
