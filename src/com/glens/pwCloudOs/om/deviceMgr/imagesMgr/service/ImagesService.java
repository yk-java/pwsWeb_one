package com.glens.pwCloudOs.om.deviceMgr.imagesMgr.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.dao.DeviceBookDao;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.dao.DeviceDao;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.dao.ImagesDao;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceBookVo;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceVo;



public class ImagesService extends EAPAbstractService {
	private FinalHttp httpClient = new FinalHttp();
	private DeviceBookDao deviceBookDao;
	private DeviceDao deviceDao;

	public DeviceDao getDeviceDao() {
		return deviceDao;
	}



	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}



	public DeviceBookDao getDeviceBookDao() {
		return deviceBookDao;
	}



	public void setDeviceBookDao(DeviceBookDao deviceBookDao) {
		this.deviceBookDao = deviceBookDao;
	}


	public boolean deleteImg(String picVisitid){

		boolean isdelete=deviceBookDao.deleteImg(picVisitid);
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		if (picVisitid != null && !picVisitid.equals("")) {
			AjaxParams params = new AjaxParams();

			params.put("fileCode", picVisitid);
			Object content = httpClient.postSync(config.getPrefix()+config.getDeleteFileUrl(), params);	
		}


		return isdelete;
	}



//	public List<Map<String, Object>> getImageslist(String addr,String estaeName) {
//
//		ImagesDao queryDao = (ImagesDao) getDao();
//		Map<String, Object> map=new HashMap<String,Object>();
//		map.put("addr", addr);
//		map.put("estaeName", estaeName);
//		return queryDao.getImageslist(map);
//	}
//
//	public List<Map<String, Object>> getAllImageslist() {
//
//		ImagesDao queryDao = (ImagesDao) getDao();
//		Map<String, Object> map=new HashMap<String,Object>();
//		//map.put("addr", addr);
//		//map.put("estaeName", estaeName);
//		return queryDao.getImageslist(map);
//	}
	
	//手机端图片新增
	public boolean phoneinsert(Map<String, Object> params,String deviceObjecode){
		
		
		
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
		
	
		
		if(deviceimg1!=null && deviceimg1 !="" && !deviceimg1.equals("")){
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", deviceimg1);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						DeviceBookVo v=new DeviceBookVo();
						v.setCompanyCode(params.get("companyCode").toString());
						v.setDeviceObjCode(deviceObjecode);
						v.setMctCode(params.get("mctCode").toString());
						v.setPicTitle(params.get("deviceObjName").toString());
						v.setProNo(params.get("proNo").toString());
						String fileCode = fileJsonObj.getString("fileCode");
						v.setPicVisitid(fileCode);
						v.setPIC_CLASSIFY_CODE("PC01001");
						v.setPIC_CLASSIFY_NAME("勘测");
						deviceBookDao.addImg(v);
						
						updatePic(deviceObjecode,"1");
						
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
						DeviceBookVo v=new DeviceBookVo();
						v.setCompanyCode(params.get("companyCode").toString());
						v.setDeviceObjCode(deviceObjecode);
						v.setMctCode(params.get("mctCode").toString());
						v.setPicTitle(params.get("deviceObjName").toString());
						v.setProNo(params.get("proNo").toString());
						String fileCode = fileJsonObj.getString("fileCode");
						v.setPicVisitid(fileCode);
						v.setPIC_CLASSIFY_CODE("PC01001");
						v.setPIC_CLASSIFY_NAME("勘测");
						deviceBookDao.addImg(v);
						updatePic(deviceObjecode,"1");
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
						DeviceBookVo v=new DeviceBookVo();
						v.setCompanyCode(params.get("companyCode").toString());
						v.setDeviceObjCode(deviceObjecode);
						v.setMctCode(params.get("mctCode").toString());
						v.setPicTitle(params.get("deviceObjName").toString());
						v.setProNo(params.get("proNo").toString());
						String fileCode = fileJsonObj.getString("fileCode");
						v.setPicVisitid(fileCode);
						v.setPIC_CLASSIFY_CODE("PC01001");
						v.setPIC_CLASSIFY_NAME("勘测");
						deviceBookDao.addImg(v);
						updatePic(deviceObjecode,"1");
					}
				}
			}
		}
		
		
	
		return true;
		
	}
	
	//表箱抢修图片新增
	public boolean boxRepairPhoneinsert(Map<String, Object> params,String deviceObjecode){
		
		
		
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
		String deviceimg4="";
		if(params.get("deviceimg4")!=null){
			deviceimg4=params.get("deviceimg4").toString();
		}
		
	
		
		if(deviceimg1!=null && deviceimg1 !="" && !deviceimg1.equals("")){
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", deviceimg1);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						DeviceBookVo v=new DeviceBookVo();
						v.setCompanyCode(params.get("companyCode").toString());
						v.setDeviceObjCode(deviceObjecode);
						v.setMctCode(params.get("mctCode").toString());
						v.setPicTitle(params.get("deviceObjName").toString()+"表箱远景");
						v.setProNo(params.get("proNo").toString());
						String fileCode = fileJsonObj.getString("fileCode");
						v.setPicVisitid(fileCode);
						v.setPIC_CLASSIFY_CODE("PC01001");
						v.setPIC_CLASSIFY_NAME("勘测");
						deviceBookDao.addImg(v);
						
						updatePic(deviceObjecode,"1");
						
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
						DeviceBookVo v=new DeviceBookVo();
						v.setCompanyCode(params.get("companyCode").toString());
						v.setDeviceObjCode(deviceObjecode);
						v.setMctCode(params.get("mctCode").toString());
						v.setPicTitle(params.get("deviceObjName").toString()+"故障点一");
						v.setProNo(params.get("proNo").toString());
						String fileCode = fileJsonObj.getString("fileCode");
						v.setPicVisitid(fileCode);
						v.setPIC_CLASSIFY_CODE("PC01001");
						v.setPIC_CLASSIFY_NAME("勘测");
						deviceBookDao.addImg(v);
						updatePic(deviceObjecode,"1");
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
						DeviceBookVo v=new DeviceBookVo();
						v.setCompanyCode(params.get("companyCode").toString());
						v.setDeviceObjCode(deviceObjecode);
						v.setMctCode(params.get("mctCode").toString());
						v.setPicTitle(params.get("deviceObjName").toString()+"故障点二");
						v.setProNo(params.get("proNo").toString());
						String fileCode = fileJsonObj.getString("fileCode");
						v.setPicVisitid(fileCode);
						v.setPIC_CLASSIFY_CODE("PC01001");
						v.setPIC_CLASSIFY_NAME("勘测");
						deviceBookDao.addImg(v);
						updatePic(deviceObjecode,"1");
					}
				}
			}
		}
		
		
		if(deviceimg4!=null && deviceimg4 !="" && !deviceimg4.equals("")){
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", deviceimg4);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						DeviceBookVo v=new DeviceBookVo();
						v.setCompanyCode(params.get("companyCode").toString());
						v.setDeviceObjCode(deviceObjecode);
						v.setMctCode(params.get("mctCode").toString());
						v.setPicTitle(params.get("deviceObjName").toString()+"表箱内部全景");
						v.setProNo(params.get("proNo").toString());
						String fileCode = fileJsonObj.getString("fileCode");
						v.setPicVisitid(fileCode);
						v.setPIC_CLASSIFY_CODE("PC01001");
						v.setPIC_CLASSIFY_NAME("勘测");
						deviceBookDao.addImg(v);
						
						updatePic(deviceObjecode,"1");
						
					}
				}
			}
		}
		
	
		return true;
		
	}
	

	public boolean insert(Object parameters, String root) throws IOException, ParseException {
		DeviceBookVo vo=(DeviceBookVo)parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		boolean _ok =false;

		DeviceBookVo v=new DeviceBookVo();
		v.setCompanyCode(vo.getCompanyCode());
		v.setDeviceObjCode(vo.getDeviceObjCode());
		v.setMctCode(vo.getMctCode());
		v.setPicTitle(vo.getPicTitle());
		v.setProNo(vo.getProNo());
		
		v.setPIC_CLASSIFY_CODE(vo.getPIC_CLASSIFY_CODE());
		v.setPIC_CLASSIFY_NAME(vo.getPIC_CLASSIFY_NAME());

		if (vo != null && vo.getProjDoc() != null) {
			String tmpFileHomeUrl = root + File.separator + config.getTmpfileHome();
			String fileName = vo.getProjDoc().getOriginalFilename();
			File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
			boolean writeResult = FileUtil.writeFile(vo.getProjDoc().getInputStream(), tmpFile);
			if (writeResult) {
				AjaxParams params = new AjaxParams();
				params.put("file", tmpFile);
				Object content = httpClient.postSync(config.getPrefix()+ config.getFileServerUploadUrl(), params);
				if (content != null) {
					JSONObject jsonObj = JSONObject.parseObject(content.toString());
					if ("200".equals(jsonObj.get("statusCode"))) {
						JSONObject fileJsonObj = jsonObj.getJSONObject("data");
						if (fileJsonObj != null) {

							String fileCode = fileJsonObj.getString("fileCode");
							//String fileSuffix = fileJsonObj.getString("fileSuffix");
							//Long fileSize = fileJsonObj.getLong("fileSize");
							v.setPicVisitid(fileCode);

							SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
							String date=sd.format(new Date());
							v.setSysCreateTime(date);
							_ok = deviceBookDao.addImg(v);
							if(tmpFile.exists()){
								tmpFile.delete();
							}
						}
					}
				}
			}
		}

		return _ok;
	}
	
	//通用接口 上传图片 返回成功之后的filecode
	public String uploadImg(Object parameters, String root) throws IOException, ParseException {
		DeviceBookVo vo=(DeviceBookVo)parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		
		if (vo != null && vo.getProjDoc() != null) {
			String tmpFileHomeUrl = root + File.separator + config.getTmpfileHome();
			String fileName = vo.getProjDoc().getOriginalFilename();
			File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
			boolean writeResult = FileUtil.writeFile(vo.getProjDoc().getInputStream(), tmpFile);
			if (writeResult) {
				AjaxParams params = new AjaxParams();
				params.put("file", tmpFile);
				Object content = httpClient.postSync(config.getPrefix()+ config.getFileServerUploadUrl(), params);
				if (content != null) {
					JSONObject jsonObj = JSONObject.parseObject(content.toString());
					if ("200".equals(jsonObj.get("statusCode"))) {
						JSONObject fileJsonObj = jsonObj.getJSONObject("data");
						if (fileJsonObj != null) {

							String fileCode = fileJsonObj.getString("fileCode");
							if(tmpFile.exists()){
								tmpFile.delete();
							}
							
							return fileCode;
						}
					}
				}
			}
		}

		return null;
	}


	public boolean importImg(Object parameters,String fileSuffix,String fileName,File file,String  fileSize,String fileCode) throws IOException, ParseException {
		DeviceBookVo vo=(DeviceBookVo)parameters;
		//PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		boolean _ok =false;
		//vo.setProjDoc(projDoc);
		DeviceBookVo v=new DeviceBookVo();
		v.setCompanyCode(vo.getCompanyCode());
		v.setDeviceObjCode(vo.getDeviceObjCode());
		v.setMctCode(vo.getMctCode());
		v.setPicTitle(vo.getPicTitle());
		v.setProNo(vo.getProNo());
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		String date=sd.format(new Date());
		v.setSysCreateTime(date);
		/*CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);*/
		//String fileCode = codeWorker.createCode("F");


		v.setPicVisitid(fileCode);
		_ok = deviceBookDao.addImg(v);

		if(_ok){
			Map m=new HashMap();

			m.put("fileName", fileCode);
			m.put("fileCode", fileCode);
			m.put("fileSuffix", fileSuffix);
			m.put("fileSize",fileSize);
			m.put("fileUrl", fileCode+"."+fileSuffix);
			_ok=deviceDao.insertFs(m);
		}
		return _ok;
		/*if (path != null && file!=null) {
			String tmpFileHomeUrl = root + File.separator + config.getTmpfileHome();
			String fileName = file.getName();
			File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
			org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();  
		    GetMethod get = new GetMethod(path);  
		    client.executeMethod(get);  
			boolean writeResult = FileUtil.writeFile(get.getResponseBodyAsStream(), tmpFile);
			if (writeResult) {
				AjaxParams params = new AjaxParams();
				params.put("file", tmpFile);
				Object content = httpClient.postSync(config.getFileServerUploadUrl(), params);
				if (content != null) {
					JSONObject jsonObj = JSONObject.parseObject(content.toString());
					if ("200".equals(jsonObj.get("statusCode"))) {
						JSONObject fileJsonObj = jsonObj.getJSONObject("data");
						if (fileJsonObj != null) {

							String fileCode = fileJsonObj.getString("fileCode");
							//String fileSuffix = fileJsonObj.getString("fileSuffix");
							//Long fileSize = fileJsonObj.getLong("fileSize");
							v.setPicVisitid(fileCode);

							SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
							String date=sd.format(new Date());
							v.setSysCreateTime(date);
							_ok = deviceBookDao.addImg(v);
							if(tmpFile.exists()){
								tmpFile.delete();
							}
						}
					}
				}
			}
		}*/


	}


	//得到图片list
	public List<DeviceBookVo> getList(String companyCode,String proNo,String mctCode,String deviceObjCode) {
		//Map result = new HashMap();
		List<DeviceBookVo> res= new ArrayList<DeviceBookVo>();
		try {
			res=deviceBookDao.getList(companyCode, proNo, mctCode, deviceObjCode);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return res;
	}


	//得到量为石设备列表 
	public List<DeviceVo> 	getDeviceList(String proNo){
		List<DeviceVo> list=deviceBookDao.getDeviceList(proNo);
		return list;
	}
	
	//得到所有设备列表 
	public List<DeviceVo> 	getAllDeviceList(){
		List<DeviceVo> list=deviceBookDao.getAllDeviceList();
		return list;
	}
	//得到单个设备 
	public List<DeviceVo> 	getObje(String objcode){
			List<DeviceVo> list=deviceBookDao.getObje(objcode);
			return list;
	}
		
	
	//得到其他的设备列表 
	public List<DeviceVo> 	getOtherDeviceList(String proNo){
		List<DeviceVo> list=deviceBookDao.getDeviceList1(proNo);
		return list;
	}
	
	
	public int updatePic(String objCode,String status){
		int result=deviceBookDao.updatePic(objCode, status);
		return result;
	}
	



}
