package com.glens.pwCloudOs.km.catalog.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.core.utils.FileUtil;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.km.base.dao.KmBaseDao;
import com.glens.pwCloudOs.km.base.service.KmBaseService;
import com.glens.pwCloudOs.km.catalog.dao.CatalogDao;
import com.glens.pwCloudOs.km.catalog.vo.CatalogVo;

public class CatalogService extends EAPAbstractService {

	private KmBaseDao kmBaseDao;

	private FinalHttp httpClient = new FinalHttp();

	public FinalHttp getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(FinalHttp httpClient) {
		this.httpClient = httpClient;
	}

	public List getTreeList(Object parameters) {

		CatalogDao dao = (CatalogDao) getDao();
		// TODO Auto-generated method stub
		return dao.getTreeList(parameters);
	}

	public boolean insert(Object parameters){
		// TODO Auto-generated method stub

		CatalogVo vo = (CatalogVo) parameters;
		CatalogDao dao = (CatalogDao) getDao();

		return dao.insert(vo);
	}
	
	public int update(Object parameters, String root) throws IOException {
		// TODO Auto-generated method stub

		CatalogVo vo = (CatalogVo) parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext()
				.getBean("pwcloudosConfig");
		//boolean _ok = false;

		if (vo != null && vo.getIcon() != null) {
			String tmpFileHomeUrl = root + File.separator
					+ config.getTmpfileHome();
			String fileName = vo.getIcon().getOriginalFilename();
			File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
			boolean writeResult = FileUtil.writeFile(vo.getIcon()
					.getInputStream(), tmpFile);
			if (writeResult) {
				AjaxParams params = new AjaxParams();
				params.put("file", tmpFile);
				Object content = httpClient.postSync(
						config.getPrefix()+ config.getFileServerUploadUrl(), params);
				if (content != null) {
					JSONObject jsonObj = JSONObject.parseObject(content
							.toString());
					if ("200".equals(jsonObj.get("statusCode"))) {

						JSONObject fileJsonObj = jsonObj.getJSONObject("data");
						if (fileJsonObj != null) {
							String fileCode = fileJsonObj.getString("fileCode");

							vo.setCatalogIcon(fileCode);
						}
					}
				}
				if (tmpFile.exists()) {
					tmpFile.delete();
				}
			}

		}

		CatalogDao dao = (CatalogDao) getDao();

		return dao.update(vo);
	}

	public List queryKmCatalogList() {
		CatalogDao dao = (CatalogDao) getDao();
		// TODO Auto-generated method stub
		List list = dao.queryKmCatalogList();

		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> m = (Map<String, Object>) list.get(i);
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			// 目录分类
			String catalogCode = (String) m.get("catalogCode");
			List<String> codeList = new ArrayList<String>();
			codeList.add(catalogCode);
			querySublist(catalogCode, codeList);
			paramsMap.put("codeList", codeList);
			int count = kmBaseDao.queryKmBaseCount(paramsMap);
			m.put("fileCount", count);
			if (!m.containsKey("catalogIcon")) {
				m.put("catalogIcon", "");
			}
		}

		return list;
	}

	protected void querySublist(String catalogCode, List<String> codeList) {
		List<String> list = kmBaseDao.querySubCatalogList(catalogCode);
		codeList.addAll(list);
	}

	public KmBaseDao getKmBaseDao() {
		return kmBaseDao;
	}

	public void setKmBaseDao(KmBaseDao kmBaseDao) {
		this.kmBaseDao = kmBaseDao;
	}

}
