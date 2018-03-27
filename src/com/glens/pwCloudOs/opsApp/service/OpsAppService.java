/**
 * @Title: OpsAppService.java
 * @Package com.glens.pwCloudOs.opsApp.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-10 下午2:27:58
 * @version V1.0
 */


package com.glens.pwCloudOs.opsApp.service;

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
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.opsApp.dao.LogAppDao;
import com.glens.pwCloudOs.opsApp.dao.OpsAppDao;
import com.glens.pwCloudOs.opsApp.vo.LogAppVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class OpsAppService extends EAPAbstractService {

	private FinalHttp httpClient;
	private LogAppDao logAppDao;
	
	public LogAppDao getLogAppDao() {
		return logAppDao;
	}

	public void setLogAppDao(LogAppDao logAppDao) {
		this.logAppDao = logAppDao;
	}

	//日志
	public List<LogAppVo> getList(String companyCode,String unitCode,String employeeCode,String accountCode,String logFromTime,String logToTime,int appType,int logType){
		return logAppDao.getList(companyCode, unitCode, employeeCode, accountCode, logFromTime, logToTime, appType, logType);
	}
	
	//日志分页
	public PageBean getPageben(Map<String, Object> params) {
		
		int currentPage = (Integer) params.get("currentPage");
		int perpage = (Integer) params.get("perpage");
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("companyCode", params.get("companyCode"));
		if (params.get("unitCode") != null && !"".equals(params.get("unitCode"))) {
			
			queryParams.put("unitCode", params.get("unitCode"));
		}
		if (params.get("employeeCode") != null && !"".equals(params.get("employeeCode"))) {
			
			queryParams.put("employeeCode", params.get("employeeCode"));
		}
		
		
		if (params.get("appType") != null && !"".equals(params.get("appType"))) {
			String appType=params.get("appType").toString();
			if(!appType.equals("0")){
				queryParams.put("appType", params.get("appType"));
			}
		}
		
		if (params.get("logType") != null && !"".equals(params.get("logType"))) {
			String logType=params.get("logType").toString();
			if(!logType.equals("0")){
				queryParams.put("logType", params.get("logType"));
			}
		}
		
		String startTime="";
		if(params.get("logFromTime")!=null && !params.get("logFromTime").equals("") ){
			startTime=params.get("logFromTime").toString()+" 00:00:00";
		}
		String endtime="";
		if(params.get("logToTime")!=null && !params.get("logToTime").equals("") ){
			endtime=params.get("logToTime").toString()+" 23:59:59";
		}
		Object searchName = params.get("employeeCode");
	
		
		String paramJson = JSONObject.toJSONString(queryParams);
		PageBean page = logAppDao.getPageBean(paramJson, searchName, currentPage, perpage,startTime,endtime);
		return page;
		
	}
	
	//新增日志
	public boolean addLog(LogAppVo vo){
		boolean _ok = logAppDao.addLog(vo);
		return _ok;
	}
	
	
	
	public Map<String, Object> getEmployeeInfo(String accountCode) {
		
		OpsAppDao appDao = (OpsAppDao) this.getDao();
		
		return appDao.getEmployeeInfo(accountCode);
	}
	
	public int updateEmployeeField(Map<String, String> params) {
		
		OpsAppDao appDao = (OpsAppDao) this.getDao();
		
		return appDao.updateEmployeeField(params);
	}
	
	//查询通讯录
	public List<Map<String, String>> getMailList(Object params) {
		
		OpsAppDao appDao = (OpsAppDao) this.getDao();
		
		return appDao.getMailList(params);
	}
	
public List<Map<String, String>> getProcessDocTypes() {
		
		OpsAppDao appDao = (OpsAppDao) this.getDao();
		
		return appDao.getProcessDocTypes();
	}
	
	public PageBean getProcessDoc(Map<String, Object> params, int currentPage, int perpage) {
		
		OpsAppDao appDao = (OpsAppDao) this.getDao();
		
		return appDao.getProcessDoc(params, currentPage, perpage);
	}
	
	public boolean batchInsertProcessDoc(Map<String, Object> params) {
		
		boolean _insertResult = false;
		OpsAppDao appDao = (OpsAppDao) this.getDao();
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		String companyCode = (String) params.get("companyCode");
		String proNo = (String) params.get("proNo");
		String docTypeLibCode = (String) params.get("docTypeLibCode");
		String title = (String) params.get("title");
		String uploadDate = (String) params.get("uploadDate");
		String base64Img = (String) params.get("base64Img");
		String remarks = (String) params.get("remarks");
		//上传到文件服务器
		AjaxParams ajaxParams = new AjaxParams();
		ajaxParams.put("base64Img", base64Img);
		Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
		if (content != null) {
			
			JSONObject jsonObj = JSONObject.parseObject(content.toString());
			if ("200".equals(jsonObj.get("statusCode"))) {
				JSONObject fileJsonObj = jsonObj.getJSONObject("data");
				if (fileJsonObj != null) {
					
					String fileCode = fileJsonObj.getString("fileCode");
					String convertFileCode = fileJsonObj.getString("convertFileCode");
					String fileSuffix = fileJsonObj.getString("fileSuffix");
					Long fileSize = fileJsonObj.getLong("fileSize");
					
					String[] proNos = proNo.split(",");
					List<Map<String, Object>> docList = new ArrayList<Map<String,Object>>();
					for (int i = 0;i < proNos.length;i++) {
						
						Map<String, Object> docItem = new HashMap<String, Object>();
						docItem.put("companyCode", companyCode);
						docItem.put("proNo", proNos[i]);
						docItem.put("docTypeLibCode", docTypeLibCode);
						docItem.put("title", title);
						docItem.put("fileName", fileCode + "." + fileSuffix);
						docItem.put("suffixName", fileSuffix);
						docItem.put("reVisitid", convertFileCode);
						docItem.put("dlVisitid", fileCode);
						docItem.put("uploadDate", uploadDate);
						docItem.put("fileSize", fileSize);
						docItem.put("remarks", remarks);
						docItem.put("docNo", codeWorker.createCode("DOC"));
						
						docList.add(docItem);
					}
					
					if (docList.size() > 0) {
						
						int insertCount = appDao.batchInsertProcessDoc(docList);
						_insertResult = insertCount > 0;
					}
				}
			}
		}
		
		return _insertResult;
	}
	
	public int updateUserPsw(String accountCode, String psw) {
		
		OpsAppDao appDao = (OpsAppDao) this.getDao();
		Map<String, String> params = new HashMap<String, String>();
		params.put("accountCode", accountCode);
		params.put("psw", psw);
		return appDao.updateUserPsw(params);
	}
	
	public Map<String, Object> getLastMobileVersion() {
		
		OpsAppDao appDao = (OpsAppDao) this.getDao();
		
		return appDao.getLastMobileVersion();
	}

	public FinalHttp getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(FinalHttp httpClient) {
		this.httpClient = httpClient;
	}
	
}
