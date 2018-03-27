package com.glens.pwCloudOs.materielMg.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.materielMg.goods.dao.GoodsCategoryDao;
import com.glens.pwCloudOs.materielMg.goods.vo.GoodsCategory;

public class GoodsCategoryService extends EAPAbstractService {

	public List<Map<String, Object>> getGoodsList() {
		GoodsCategoryDao dao = (GoodsCategoryDao) getDao();
		List<GoodsCategory> list = dao.getGoodsList();
		List<Map<String, Object>> resutList = new ArrayList<Map<String, Object>>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			GoodsCategory gc = list.get(i);
			paramsMap.put(gc.getGoodsCode(), gc.getGoodsName());
		}

		for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("code", entry.getKey());
			mm.put("name", entry.getValue());
			List<Map<String, Object>> secondList = new ArrayList<Map<String, Object>>();
			for (GoodsCategory g : list) {
				if (entry.getKey().equals(g.getGoodsCode())) {
					Map<String, Object> m2 = new HashMap<String, Object>();
					m2.put("code", g.getSpecCode());
					m2.put("name", g.getSpecName());
					//m2.put("price", g.getPrice() + "");
					secondList.add(m2);
				}
			}
			mm.put("list", secondList);
			resutList.add(mm);
		}

		return resutList;
	}

}
