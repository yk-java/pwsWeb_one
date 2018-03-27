package com.glens.pwCloudOs.pm.taskMgr.carDispach.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.pm.taskMgr.carDispach.dao.PmTmVehicleDispatchDao;

public class PmTmVehicleFuelchargeService extends EAPAbstractService {
	
	private FinalHttp httpClient;
	
	
	public FinalHttp getHttpClient() {
		return httpClient;
	}




	public void setHttpClient(FinalHttp httpClient) {
		this.httpClient = httpClient;
	}




	public List selectList(String ptjDriver) {
		// TODO Auto-generated method stub
		Map map=new HashMap();
		map.put("ptjDriver", ptjDriver);
		
		PmTmVehicleDispatchDao dao=(PmTmVehicleDispatchDao)getDao();
		return dao.selectList(map);
	}
	
	
	
	
	public int updateState(Map parameters,String smileageImg,String dmileageImg) {
		// TODO Auto-generated method stub
		PmTmVehicleDispatchDao dao=(PmTmVehicleDispatchDao)getDao();
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		
		
		AjaxParams ajaxParams = new AjaxParams();
		ajaxParams.put("base64Img", smileageImg);
		System.out.println(config.getUploadMergeBase64ImgUrl());
		Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
		if (content != null) {
			JSONObject jsonObj = JSONObject.parseObject(content.toString());
			if ("200".equals(jsonObj.get("statusCode"))) {
				JSONObject fileJsonObj = jsonObj.getJSONObject("data");
				if (fileJsonObj != null) {
					String fileCode = fileJsonObj.getString("fileCode");
					parameters.put("smileageImg", fileCode);
				}
			}
		}
		
		
		AjaxParams ajaxParams1 = new AjaxParams();
		ajaxParams1.put("base64Img", dmileageImg);
		Object content1 = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams1);
		if (content1 != null) {
			JSONObject jsonObj1 = JSONObject.parseObject(content1.toString());
			if ("200".equals(jsonObj1.get("statusCode"))) {
				JSONObject fileJsonObj1 = jsonObj1.getJSONObject("data");
				if (fileJsonObj1 != null) {
					String fileCode = fileJsonObj1.getString("fileCode");
					parameters.put("dmileageImg", fileCode);
				}
			}
		}
		
		
		
		
		//parameters.put("smileageImg", smileageImg);
		//parameters.put("dmileageImg", dmileageImg);
		return dao.updateState(parameters);
	}
	
	
	
	public Object getDispatchVo(Long rowid) {
		// TODO Auto-generated method stub
		
		
		PmTmVehicleDispatchDao dao=(PmTmVehicleDispatchDao)getDao();
		
		return dao.getDispatchVo(rowid);
	}

}
