/**
 * @Title: ProjDocService.java
 * @Package com.glens.pwCloudOs.pm.projDoc.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-7-19 上午11:13:16
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.projDoc.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.utils.FileUtil;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.materielMg.comprehensiveQuery.dao.ComprehensiveQueryDao;
import com.glens.pwCloudOs.pm.projDoc.dao.ProjDocDao;
import com.glens.pwCloudOs.pm.projDoc.vo.ProjDocVo;
import com.google.gson.JsonObject;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class ProjDocService extends EAPAbstractService {

	private static final float KB = 1024.0f;
	
	private FinalHttp httpClient = new FinalHttp();
	
	/**
	
	  * <p>Title: insert</p>
	
	  * <p>Description: </p>
	
	  * @param parameters
	  * @return
	 * @throws IOException 
	 * @throws ParseException 
	
	  **/
	public boolean insert(Object parameters, String root) throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		ProjDocVo vo = (ProjDocVo) parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		boolean _ok =false;
		
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		ProjDocDao dao = (ProjDocDao) getDao();
		if (vo != null && vo.getProjDoc() != null) {
			
			String tmpFileHomeUrl = root + File.separator + config.getTmpfileHome();
			String fileName = vo.getProjDoc().getOriginalFilename();
			File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
			boolean writeResult = FileUtil.writeFile(vo.getProjDoc().getInputStream(), tmpFile);
			if (writeResult) {
				
				//postMethod.addRequestHeader("Content-Type","text/html;charset=UTF-8");
				AjaxParams params = new AjaxParams();
				params.put("file", tmpFile);
				Object content = httpClient.postSync(config.getPrefix()+ config.getFileServerUploadUrl(), params);
				if (content != null) {
					
					JSONObject jsonObj = JSONObject.parseObject(content.toString());
					if ("200".equals(jsonObj.get("statusCode"))) {
						
						JSONObject fileJsonObj = jsonObj.getJSONObject("data");
						if (fileJsonObj != null) {
							
							String fileCode = fileJsonObj.getString("fileCode");
							String convertFileCode = fileJsonObj.getString("convertFileCode");
							String fileSuffix = fileJsonObj.getString("fileSuffix");
							Long fileSize = fileJsonObj.getLong("fileSize");
							//String uploadDate=fileJsonObj.get
							
							vo.setFileName(fileName);
							vo.setFileSize(fileSize / KB);
							//vo.setUploadDate(DateTimeUtil.formatDate(new Date(), DateTimeUtil.FORMAT_2));
							
							vo.setSuffixName(fileSuffix);
							vo.setDlVisitid(fileCode);
							vo.setReVisitid(convertFileCode);
							//String docno = codeWorker.createCode("DOC");
							//vo.setDocNo(docno);
							String proNo=vo.getProNo();
							String title=vo.getTitle();
							List<ProjDocVo> list=new ArrayList<ProjDocVo>();
							String str[]=proNo.split(",");
							String str1[]=title.split(",");
							for(int i=0;i<str.length;i++){
								ProjDocVo v=new ProjDocVo();
								v.setFileName(fileName);
								v.setFileSize(fileSize / KB);
								v.setSuffixName(fileSuffix);
								v.setDlVisitid(fileCode);
								v.setReVisitid(convertFileCode);
								v.setCompanyCode(vo.getCompanyCode());
								v.setDocTypelibCode(vo.getDocTypelibCode());
								v.setTitle(str1[i]);
								v.setUploadDate(vo.getUploadDate());
								String docno = codeWorker.createCode("DOC");
								String pro=str[i];
								//System.out.println(pro);
								v.setDocNo(docno);
								v.setProNo(pro);
								v.setRemarks(vo.getRemarks());
								list.add(v);
							}
							
							_ok = dao.insertProj(list);
						}
					}
				}
				
				if(tmpFile.exists()){
					tmpFile.delete();
				}
				
			}
		}
		
		return _ok;
	}
	
	public List<Map<String, Object>> listall(Map<String, String> params) {
		
		ProjDocDao dao = (ProjDocDao) getDao();
		
		return dao.listall(params);
	}
	public List<Map<String, Object>> getAllFileByProNo(Map<String, String> params) {
		
		ProjDocDao dao = (ProjDocDao) getDao();
		
		return dao.getAllFileByProNo(params);
	}
	
	public int delete(Object parameters) {
		// TODO Auto-generated method stub
		
		
		int result= dao.delete(parameters);
		
		ProjDocVo vo = (ProjDocVo) parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		if (vo != null) {
			AjaxParams params = new AjaxParams();
			if(vo.getReVisitid().equals(vo.getDlVisitid())){
				params.put("fileCode", vo.getDlVisitid());
				
				Object content = httpClient.postSync(config.getPrefix()+config.getDeleteFileUrl(), params);
			}else{
				params.put("fileCode", vo.getReVisitid());
				Object content = httpClient.postSync(config.getPrefix()+config.getDeleteFileUrl(), params);
				params.put("fileCode", vo.getDlVisitid());
				Object content1 = httpClient.postSync(config.getPrefix()+config.getDeleteFileUrl(), params);
				
			}
			
			
			
		}
		return result;
	}
	
	public List<Map<String, String>> selectDocType(Map<String, String> params) {
		
		ProjDocDao docDao = (ProjDocDao) getDao();
		
		return docDao.selectDocType(params);
	}
	
	public List<Map<String, Object>> queryProDoc(Map<String, String> params) {
		
		ProjDocDao docDao = (ProjDocDao) getDao();
		List<Map<String, Object>> docList = docDao.queryProDoc(params);
		
		return docList;
		
	}
	
}
