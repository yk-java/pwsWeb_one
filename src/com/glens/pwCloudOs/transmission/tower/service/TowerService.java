package com.glens.pwCloudOs.transmission.tower.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceBookVo;
import com.glens.pwCloudOs.transmission.tower.dao.TowerDao;
import com.map.coords.algorithm.Converter;
import com.map.coords.algorithm.Point;

public class TowerService extends EAPAbstractService {

	private FinalHttp httpClient = new FinalHttp();
	
	public List<Map<String, String>> getVoltageLevel(Map<String, Object> params) {
		TowerDao towerDao=(TowerDao)getDao();
		List<Map<String, String>> list =towerDao.getVoltageLevel(params);
		List<Map<String, String>> returnList=new ArrayList<Map<String,String>>();
		for(int i=0;i<list.size();i++){
			Map m=list.get(i);
			String voltageLevel=m.get("VOLTAGELEVEL").toString();
			params.put("voltageLevel", voltageLevel);
			List lineList=towerDao.getLines(params);
			m.put("list", lineList);
			returnList.add(m);
		}
		
		return returnList;
	}
	
	public List<Map<String, String>> getTowers(Map<String, Object> params) {
		TowerDao towerDao=(TowerDao)getDao();
		return towerDao.getTowers(params);
	}
	
	public Map<String, String> getTowerDetail(Map<String, Object> params) {
		TowerDao towerDao=(TowerDao)getDao();
		List<Map<String, String>> list=towerDao.getTowerDetail(params);
		List<Map<String, String>> resultList=new ArrayList<Map<String,String>>();
		
		
		Map m=new HashMap();
		if(list!=null && list.size()>0){
			//WD_JZ  JD_JZ
			Map temp=list.get(0);
			
			if(temp.get("JD_JZ")!=null && temp.get("WD_JZ")!=null){
				Double showx=Double.parseDouble(temp.get("JD_JZ").toString());//显示x
				Double showy=Double.parseDouble(temp.get("WD_JZ").toString());//显示y
				
				Point point= Converter.WGS84ToBD09(showx, showy);
				temp.put("JD_JZ", point.getLongitude());
				temp.put("WD_JZ", point.getLatitude());
			}
			
			
			
			
			m.put("tower", temp);
		}
		List<Map<String, String>> piclist=towerDao.getPics(params);
		m.put("pics", piclist);
		
		return m;
				
	}
	
	public List<Map<String, String>> getDianYa(Map<String, Object> params) {
		TowerDao towerDao=(TowerDao)getDao();
		return towerDao.getDianYa(params);
	}
	public List<Map<String, String>> getLineByDianya(Map<String, Object> params) {
		TowerDao towerDao=(TowerDao)getDao();
		return towerDao.getLines(params);
	}
	public List<Map<String, String>> getTowerslist(Map<String, Object> params) {
		TowerDao towerDao=(TowerDao)getDao();
		return towerDao.getTowerslist(params);
	}
	
	public boolean addPic(Object parameters) {
		TowerDao towerDao=(TowerDao)getDao();
		Map m=(Map)parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		
		boolean isInsert=false;
		
		if(m.get("picCode")!=null){
			String pic=m.get("picCode").toString();
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", pic);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						m.put("picCode", fileCode);
						isInsert=towerDao.addPic(m);
					}
				}
			}else{
				m.put("picCode", "");
			}
		}
		
		
		if(m.get("picCode2")!=null){
			String pic=m.get("picCode2").toString();
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", pic);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						m.put("picCode", fileCode);
						isInsert=towerDao.addPic(m);
					}
				}
			}else{
				m.put("picCode", "");
			}
		}
		return isInsert;
		
		
	}
	
	public boolean addTower(Object parameters) {
		TowerDao towerDao=(TowerDao)getDao();
		
		Map m=(Map)parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		
		if(m.get("defectPic1")!=null){
			String pic1=m.get("defectPic1").toString();
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", pic1);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						m.put("defectPic1", fileCode);
						
					}
				}
			}else{
				m.put("defectPic1", "");
			}
		}
		
		
		if(m.get("defectPic2")!=null){
			String pic2=m.get("defectPic2").toString();
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", pic2);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						m.put("defectPic2", fileCode);
						
					}
				}
			}else{
				m.put("defectPic2", "");
			}
		}
		
		
		
		if(m.get("defectPic3")!=null){
			String pic3=m.get("defectPic3").toString();
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", pic3);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						m.put("defectPic3", fileCode);
						
					}
				}
			}else{
				m.put("defectPic3", "");
			}
		}
		
		
		
		return towerDao.addTower(m);
	}
	
	public boolean addDeviceRange(Object parameters) {
		TowerDao towerDao=(TowerDao)getDao();
		
		return towerDao.addDeviceRange(parameters);
	}
	
	public List<Map<String, String>> getDefectList(Map<String, Object> params) {
		TowerDao towerDao=(TowerDao)getDao();
		return towerDao.getDefectList(params);
	}
	
	
	public int checkDefect(Object parameters) {
		TowerDao towerDao=(TowerDao)getDao();
		
		Map m=(Map)parameters;
		int isPass=Integer.parseInt(m.get("isPass").toString());
		if(isPass==0){//不通过
			m.put("state", 3);
		}else{//通过
			m.put("state", 1);
		}
		
		return towerDao.checkDefect(m);
	}
	public List<Map<String, String>> getPassList(Map<String, Object> params) {
		TowerDao towerDao=(TowerDao)getDao();
		return towerDao.getPassList(params);
	}
	
	
	public int workOutDefect(Object parameters) {
		TowerDao towerDao=(TowerDao)getDao();
		
		Map m=(Map)parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		
		if(m.get("clearPic1")!=null){
			String pic1=m.get("clearPic1").toString();
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", pic1);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						m.put("clearPic1", fileCode);
						
					}
				}
			}else{
				m.put("clearPic1", "");
			}
		}
		
		if(m.get("clearPic2")!=null){
			String pic2=m.get("clearPic2").toString();
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", pic2);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						m.put("clearPic2", fileCode);
						
					}
				}
			}else{
				m.put("clearPic2", "");
			}
		}
		
		if(m.get("clearPic3")!=null){
			String pic3=m.get("clearPic3").toString();
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", pic3);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						m.put("clearPic3", fileCode);
						
					}
				}
			}else{
				m.put("clearPic3", "");
			}
		}
		
		return towerDao.workOutDefect(m);
		
		
	}
	
	
	public List<Map<String, String>> getAreaList(Map<String, Object> params) {
		TowerDao towerDao=(TowerDao)getDao();
		
		Map m=(Map)params;
		
		double longitude=Double.parseDouble(m.get("longitude").toString());//x
		double latitude=Double.parseDouble(m.get("latitude").toString());//y
		
		Point point= Converter.BD09ToWGS84(longitude, latitude);
		
		int mile=Integer.parseInt(m.get("mile").toString());
		//0.00001172529394396682  y
		//0.000008993220562150633  x
		double tempx=0.000008993220562150633*mile;
		double tempy=0.00001172529394396682*mile;
		
		double minx=point.getLongitude()-tempx;
		double maxx=point.getLongitude()+tempx;
		double miny=point.getLatitude()-tempy;
		double maxy=point.getLatitude()+tempy;
		
		m.put("minx", minx);
		m.put("maxx", maxx);
		m.put("miny", miny);
		m.put("maxy", maxy);
		
		List list=towerDao.getAreaList(m);
		List resultList=new ArrayList();
		for(int i=0;i<list.size();i++){
			Map mp=(Map)list.get(i);
			double jdjz=Double.parseDouble(mp.get("JD_JZ").toString());
			double wdjz=Double.parseDouble(mp.get("WD_JZ").toString());
			Point p= Converter.WGS84ToBD09(jdjz, wdjz);
			mp.put("JD_JZ", p.getLongitude());
			mp.put("WD_JZ", p.getLatitude());
			resultList.add(mp);
		}
		return resultList;
	}
	
	
	public List<Map<String, String>> getTeam1(Map<String, Object> params) {
		TowerDao towerDao=(TowerDao)getDao();
		return towerDao.getTeam1(params);
	}
	public List<Map<String, String>> getTeam2(Map<String, Object> params) {
		TowerDao towerDao=(TowerDao)getDao();
		return towerDao.getTeam2(params);
	}
	
}
