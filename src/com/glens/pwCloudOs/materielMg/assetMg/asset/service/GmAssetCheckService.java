package com.glens.pwCloudOs.materielMg.assetMg.asset.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.materielMg.assetMg.asset.dao.GmAssetCheckDao;
import com.glens.pwCloudOs.materielMg.assetMg.asset.vo.AssetVo;
import com.glens.pwCloudOs.materielMg.assetMg.asset.web.GmAssetCheckForm;

public class GmAssetCheckService extends EAPAbstractService {

	public List<Map<String, Object>> queryForExport(Map params) {
		GmAssetCheckDao dao = (GmAssetCheckDao) this.dao;
		List<Map<String, Object>> list = dao.queryForExport(params);
		return list;
	}

	public Map get(Map map) {
		GmAssetCheckDao dao = (GmAssetCheckDao) this.dao;
		Map m = dao.get(map);
		return m;
	}

	public List queryImages(Map map) {
		GmAssetCheckDao dao = (GmAssetCheckDao) this.dao;
		List<Map<String, Object>> list = dao.queryImages(map);
		return list;
	}

	public PageBean queryPageImages(Map map, int currentPage, int perpage) {
		// TODO Auto-generated method stub
		GmAssetCheckDao gmAssetCheckDao = (GmAssetCheckDao) this.dao;
		return gmAssetCheckDao.queryPageImages(map, currentPage, perpage);
	}

	public String queryTodayStatus(Map m) {
		GmAssetCheckDao dao = (GmAssetCheckDao) this.dao;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("queryDate", m.get("QUERY_DATE"));
		params.put("assetCode", m.get("ASSET_CODE"));
		List list = dao.queryTodayStatus(params);
		// [{cnt=2, CLIENT=1}, {cnt=1, CLIENT=2}]
		Map resultMap = new HashMap<String, Object>();
		String result = "";

		for (int i = 0; i < list.size(); i++) {
			Map mm = (Map) list.get(i);
			String client = (String) mm.get("CLIENT");
			resultMap.put(client, "");
		}

		if (resultMap.size() == 0) {
			result = "未扫码";
		}

		return result;
	}

	public List assetUserList(Map map) {
		// TODO Auto-generated method stub
		GmAssetCheckDao gmAssetCheckDao = (GmAssetCheckDao) this.dao;
		return gmAssetCheckDao.assetUserList(map);
	}
}
