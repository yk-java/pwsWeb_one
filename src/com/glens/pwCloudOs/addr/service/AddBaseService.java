package com.glens.pwCloudOs.addr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.utils.common.PingYinUtil;
import com.glens.pwCloudOs.addr.dao.AddBaseDao;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceBookVo;
import com.map.coords.algorithm.Converter;
import com.map.coords.algorithm.Point;

public class AddBaseService extends EAPAbstractService {
	
	
	private AddBaseDao addBaseDao;
	private FinalHttp httpClient;
	
	public AddBaseDao getAddBaseDao() {
		return addBaseDao;
	}

	public void setAddBaseDao(AddBaseDao addBaseDao) {
		this.addBaseDao = addBaseDao;
	}

	public FinalHttp getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(FinalHttp httpClient) {
		this.httpClient = httpClient;
	}

	public PageBean getAddrList(Map<String, Object> params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		
		
		
		paramsN.put("COMPANY_CODE", params.get("COMPANY_CODE"));
		
		
		
		if(params.get("ADDR_STATUS")!=null && !params.get("ADDR_STATUS").toString().equals("0")){
			paramsN.put("ADDR_STATUS", params.get("ADDR_STATUS"));
		}
		
		if(params.get("PARENT_ADDR_CODE")!=null){
			paramsN.put("PARENT_ADDR_CODE", params.get("PARENT_ADDR_CODE"));
		}
		if(params.get("ADDR_NAME")!=null){
			paramsN.put("ADDR_NAME", params.get("ADDR_NAME"));
		}
		if(params.get("ADDR_CLASS")!=null){
			paramsN.put("ADDR_CLASS", params.get("ADDR_CLASS"));
		}
		
		
		//paramsN.put("reseverOrgcode", params.get("reseverOrgcode"));
		//paramsN.put("reserveStatus", null);//未改造的
		//paramsN.put("searchName", params.get("searchName"));
		
		PageBean pageBean = addBaseDao.getAddrList(paramsN);
		return pageBean;
	}
	public int insertAddrBase(Map<String, Object> params) {
		
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		String addrCode = codeWorker.createCode("AD");
		
		Map<String, Object> paramsN = new HashMap<String, Object>();
		
		paramsN.put("ADDR_CODE", addrCode);
		paramsN.put("COMPANY_CODE", params.get("COMPANY_CODE"));
		paramsN.put("PROVINCE","江苏省");
		paramsN.put("CITY","镇江市");
		paramsN.put("REGION", params.get("REGION")); //区县
		paramsN.put("TOWN_SHIP", params.get("TOWN_SHIP"));//街道
		paramsN.put("STREET", params.get("STREET"));//街路巷
		paramsN.put("ROOM_NO", params.get("ROOM_NO"));//门牌号
		
		//城市字段
		paramsN.put("PLOT", params.get("PLOT"));//小区
		paramsN.put("BUILDING_NO", params.get("BUILDING_NO"));//楼牌号
		paramsN.put("UNIT_NO", params.get("UNIT_NO"));//单元号
		//农村字段
		paramsN.put("GROUP", params.get("GROUP"));//村组
		paramsN.put("FARMER_ROOM_NO", params.get("FARMER_ROOM_NO"));//农户门牌号
		paramsN.put("ZAIMUTH",params.get("ZAIMUTH"));//方位
		
		
		paramsN.put("PARENT_ADDR_CODE", params.get("PARENT_ADDR_CODE"));
		paramsN.put("PARENT_ADDR_NAME", params.get("TOWN_SHIP"));
		String reg="";
		if(params.get("REGION")!=null){
			reg=params.get("REGION").toString();
		}
		String ship="";
		if(params.get("TOWN_SHIP")!=null){
			ship=params.get("TOWN_SHIP").toString();
		}
		
		String street="";
		if(params.get("STREET")!=null){
			street=params.get("STREET").toString();
		}
		
		String roomno="";
		if(params.get("ROOM_NO")!=null){
			roomno=params.get("ROOM_NO").toString();
		}
		
		String plot="";
		if(params.get("PLOT")!=null){
			plot=params.get("PLOT").toString();
		}
		
		String buildingno="";
		if(params.get("BUILDING_NO")!=null){
			buildingno=params.get("BUILDING_NO").toString();
		}
		String unitno="";
		if(params.get("UNIT_NO")!=null){
			unitno=params.get("UNIT_NO").toString();
		}
		
		String group="";
		if(params.get("GROUP")!=null){
			group=params.get("GROUP").toString();
		}
		
		String farmerroomno="";
		if(params.get("FARMER_ROOM_NO")!=null){
			farmerroomno=params.get("FARMER_ROOM_NO").toString();
		}
		
		String zaimuth="";
		if(params.get("ZAIMUTH")!=null){
			zaimuth=params.get("ZAIMUTH").toString();
		}
		
		String ADDR_NAME=reg+ship+street+roomno+plot+buildingno+unitno+group+farmerroomno+zaimuth;

		paramsN.put("ADDR_NAME", ADDR_NAME);
		paramsN.put("ADDR_CONTENT", ADDR_NAME);
		paramsN.put("SHOW_X", params.get("SHOW_X"));
		paramsN.put("SHOW_Y", params.get("SHOW_Y"));
		paramsN.put("ADDR_STATUS", 2);
		paramsN.put("IS_REGION", 0);
		paramsN.put("REMARKS", params.get("REMARKS"));
		
		
		
		String pinyin = PingYinUtil.getPingYin(ADDR_NAME);
		String firstLetter = PingYinUtil.getFirstSpell(ADDR_NAME);
		//addrItem.put("PIN_YIN", pinyin);
		//addrItem.put("FIRST_LETTER", firstLetter);
		paramsN.put("PIN_YIN", pinyin);
		paramsN.put("FIRST_LETTER", firstLetter);
		
		
		Double showx=Double.parseDouble(params.get("SHOW_X").toString());//显示x
		Double showy=Double.parseDouble(params.get("SHOW_Y").toString());//显示y
		Point point= Converter.BD09ToWGS84(showx, showy);
		paramsN.put("WGS84_X", point.getLongitude());
		paramsN.put("WGS84_Y", point.getLatitude());
		
		
		
		int rows = addBaseDao.insertAddrBase(paramsN);
		return rows;
	}
	
	
	//手机端采集
public int insertAddrBase1(Map<String, Object> params) {
		
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		String addrCode = codeWorker.createCode("AD");
		
		Map<String, Object> paramsN = new HashMap<String, Object>();
		
		paramsN.put("ADDR_CODE", addrCode);
		paramsN.put("COMPANY_CODE", params.get("companyCode"));
		
		String addrName="";
		if(params.get("deviceObjName")!=null){
			addrName=params.get("deviceObjName").toString();
		}
				
		paramsN.put("ADDR_NAME", addrName);
		
		paramsN.put("SHOW_X", params.get("x"));
		paramsN.put("SHOW_Y", params.get("y"));
		//String floor_house=params.get("attrJson").toString();
		//floor_house=floor_house.replaceAll("'\'", "");
		/////paramsN.put("FLOOR_HOUSE", floor_house);
		
		if (params.get("attrJson") != null && !"".equals(params.get("attrJson"))) {

			String attrJson = (String) params.get("attrJson");
			Map<String, Object> attrMap = JSONObject.parseObject(attrJson);
			Iterator<String> it = attrMap.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				paramsN.put(key, attrMap.get(key));
			}
		}
		
		String pinyin = PingYinUtil.getPingYin(addrName);
		String firstLetter = PingYinUtil.getFirstSpell(addrName);
		//addrItem.put("PIN_YIN", pinyin);
		//addrItem.put("FIRST_LETTER", firstLetter);
		paramsN.put("PIN_YIN", pinyin);
		paramsN.put("FIRST_LETTER", firstLetter);
		
		
		Double showx=Double.parseDouble(params.get("x").toString());//显示x
		Double showy=Double.parseDouble(params.get("y").toString());//显示y
		Point point= Converter.BD09ToWGS84(showx, showy);
		paramsN.put("WGS84_X", point.getLongitude());
		paramsN.put("WGS84_Y", point.getLatitude());
		
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		String deviceimg1="";
		if(params.get("deviceimg1")!=null){
			deviceimg1=params.get("deviceimg1").toString();
		}
		String deviceimg2="";
		if(params.get("deviceimg2")!=null){
			deviceimg2=params.get("deviceimg2").toString();
		}
		
		String deviceimg3="";
		if(params.get("deviceimg3")!=null){
			deviceimg3=params.get("deviceimg3").toString();
		}
		String fileCodes="";
		
		if(deviceimg1!=null && deviceimg1 !="" && !deviceimg1.equals("")){
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", deviceimg1);
			Object content = httpClient.postSync(config.getPrefix()+ config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						fileCodes+=fileCode+",";
					}
				}
			}
		}
		
		
		if(deviceimg2!=null && deviceimg2 !="" && !deviceimg2.equals("")){
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", deviceimg2);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						
						String fileCode = fileJsonObj.getString("fileCode");
						fileCodes+=fileCode+",";
					}
				}
			}
		}
		
		
		if(deviceimg3!=null && deviceimg3 !="" && !deviceimg3.equals("")){
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", deviceimg3);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
					
						String fileCode = fileJsonObj.getString("fileCode");
						fileCodes+=fileCode+",";
					}
				}
			}
		}
		
		fileCodes=fileCodes.substring(0,fileCodes.length()-1);
		
		paramsN.put("pic", fileCodes);
		
		
		int rows = addBaseDao.insertAddrBase(paramsN);
		return rows;
	}
	
	
	public int updateAddrBase(Map<String, Object> params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		String ADDR_CODE=params.get("ADDR_CODE").toString();
		paramsN.put("ADDR_CODE", ADDR_CODE);
		String COMPANY_CODE=params.get("COMPANY_CODE").toString();
		paramsN.put("COMPANY_CODE", COMPANY_CODE);
		
		String UNIT_NAME=params.get("UNIT_NAME").toString();
		String UP_FLOOR_NUM=params.get("UP_FLOOR_NUM").toString();
		String DOWN_FLOOR_NUM=params.get("DOWN_FLOOR_NUM").toString();
		String HOUSE_NUM=params.get("HOUSE_NUM").toString();
		
		if(!UNIT_NAME.equals("null")){
			String unitNames[]=UNIT_NAME.split(",");
			String upNum[]=UP_FLOOR_NUM.split(",");
			String downNum[]=DOWN_FLOOR_NUM.split(",");
			String houseNum[]=HOUSE_NUM.split(",");
			List<Map<String, Object>> houseList = new ArrayList<Map<String,Object>>();
			for(int i=0;i<unitNames.length;i++){
				
				Map<String, Object> houseItem = new HashMap<String, Object>();
				houseItem.put("UNIT_NAME", unitNames[i]);
				houseItem.put("UP_FLOOR_NUM", upNum[i]);
				houseItem.put("DOWN_FLOOR_NUM", downNum[i]);
				houseItem.put("HOUSE_NUM", houseNum[i]);
				houseList.add(houseItem);
			}
			
			
			paramsN.put("FLOOR_HOUSE", houseList);
		}
		
		
		
		
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		String reserveB1Img="";
		if(params.get("POLICE_PIC")!=null && !params.get("POLICE_PIC").equals("null")&& params.get("POLICE_PIC")!="null" && params.get("POLICE_PIC")!=""){
			reserveB1Img=params.get("POLICE_PIC").toString();
		}
	   
	     
	     if(reserveB1Img!=null && reserveB1Img !="" && !reserveB1Img.equals("")){
				AjaxParams ajaxParams3 = new AjaxParams();
				ajaxParams3.put("base64Img", reserveB1Img);
				Object content3 = httpClient.postSync(config.getPrefix()+ config.getBatchUploadBase64ImgUrl(), ajaxParams3);
				if (content3 != null) {
					JSONObject jsonObj = JSONObject.parseObject(content3.toString());
					if ("200".equals(jsonObj.get("statusCode"))) {
						//JSONObject fileJsonObj = jsonObj.getJSONObject("data");
						
						String fileCode=jsonObj.get("generateKey").toString();
						//if (fileJsonObj != null) {
							//String fileCode = fileJsonObj.getString("generateKey");
						
							paramsN.put("POLICE_PIC", fileCode);
						//}
					}
				}
		 }else{
			 paramsN.put("POLICE_PIC", "");
		 }
	     
	     int rows = addBaseDao.updateAddrBase(paramsN);
		 return rows;

	}
	
	public int updateElectricAddrBase(Map<String, Object> params) {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		String ADDR_CODE=params.get("ADDR_CODE").toString();
		paramsN.put("ADDR_CODE", ADDR_CODE);
		String COMPANY_CODE=params.get("COMPANY_CODE").toString();
		paramsN.put("COMPANY_CODE", COMPANY_CODE);
		
		
		String BOX_NO=params.get("BOX_NO").toString();
		String AMUL_BOX_NUM=params.get("AMUL_BOX_NUM").toString();
		String HOUSE_HOLD=params.get("HOUSE_HOLD").toString();
		String AMULBOX_CLASS=params.get("AMULBOX_CLASS").toString();
		
		String ELECTRIC_METER=params.get("ELECTRIC_METER").toString();
		
		Map<String, Object> meterItem = new HashMap<String, Object>();
		meterItem.put("BOX_NO", BOX_NO);
		meterItem.put("AMUL_BOX_NUM", AMUL_BOX_NUM);
		meterItem.put("HOUSE_HOLD", HOUSE_HOLD);
		meterItem.put("AMULBOX_CLASS", AMULBOX_CLASS);
		
		paramsN.put("ALUMINIUM_BOX", meterItem);
		if(!ELECTRIC_METER.equals("null")){
			paramsN.put("ELECTRIC_METER", ELECTRIC_METER);
		}
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		String reserveB1Img="";
		
		if(params.get("ELECTRIC_PIC")!=null && !params.get("ELECTRIC_PIC").equals("null")&& params.get("ELECTRIC_PIC")!="null" && params.get("ELECTRIC_PIC")!=""){	
			reserveB1Img=params.get("ELECTRIC_PIC").toString();
		}
	   
	     
	     if(reserveB1Img!=null && reserveB1Img !="" && !reserveB1Img.equals("")){
				AjaxParams ajaxParams3 = new AjaxParams();
				ajaxParams3.put("base64Img", reserveB1Img);
				Object content3 = httpClient.postSync(config.getPrefix()+ config.getBatchUploadBase64ImgUrl(), ajaxParams3);
				if (content3 != null) {
					JSONObject jsonObj = JSONObject.parseObject(content3.toString());
					if ("200".equals(jsonObj.get("statusCode"))) {
						//JSONObject fileJsonObj = jsonObj.getJSONObject("data");
						String fileCode=jsonObj.get("generateKey").toString();
						
						paramsN.put("ELECTRIC_PIC", fileCode);
						
					}
				}
		 }else{
			 paramsN.put("ELECTRIC_PIC", "");
		 }
	     
	     int rows = addBaseDao.updateElectricAddrBase(paramsN);
		 return rows;

	}
	
	
}
