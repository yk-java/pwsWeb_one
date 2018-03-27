package com.glens.pwCloudOs.materielMg.assetMg.materialBase.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.materielMg.assetMg.materialBase.dao.MaterialBaseDao;
import com.glens.pwCloudOs.materielMg.comprehensiveQuery.dao.ComprehensiveQueryDao;

public class MaterialBaseService extends EAPAbstractService {

	
	/**
	 * 流程状态枚举
	 * 1-申请，2-批准，3-驳回，4-归还申请，5-归还确认
	 * 
	 * @author 
	 * @version V1.0
	 */
	
	enum FlowStatusEnum {
		
		FLOW_APPLY(1), FLOW_AUDIT(2), FLOW_REBUT(3), FLOW_RETURN_APPLY(4), FLOW_RETURN_AUDIT(5);
		
		private int status;
		
		private FlowStatusEnum(int status) {
			
			this.status = status;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}
	}
	
	private FinalHttp httpClient;
	
	public List<Map<String, Object>> ishave(Object params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.ishave(params);
	}
	
	
	public Map<String, Object> getMaterialSummary(Map<String, Object> params){
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		Map<String, Object> returnMap=new HashMap<String, Object>();
		
		List<Map<String, Object>> linyongList=queryDao.getLinyongNum(params);//领用列表
		
		
		List<Map<String, Object>> jieyongList=queryDao.getJieyongNum(params);//借用列表
		
	
		params.put("rentStatus", "1");
		List<Map<String, Object>>  weihuanlist=queryDao.getJieyongNum(params);//租用中 （未归还）
		params.put("rentStatus", "2");
		List<Map<String, Object>>  guihuanlist=queryDao.getJieyongNum(params);//已归还
		
		returnMap.put("lingyong", linyongList.size());
		returnMap.put("jieyong", jieyongList.size());
		returnMap.put("guihuan", guihuanlist.size());
		returnMap.put("weihuan", weihuanlist.size());

		return returnMap;
	}
	
	public boolean addMaterial(Object parameters) {
		// TODO Auto-generated method stub
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		return queryDao.addMaterial(parameters);
	}
	
	
	public List<Map<String, Object>> getLinyongList(Object params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.getLinyongNum(params);
	}
	
	public List<Map<String, Object>> getJieyongList(Object params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.getJieyongNum(params);
	}
	
	public List<Map<String, Object>> getProList(Object params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.getProList(params);
	}
	
	public List<Map<String, Object>> getProMembers(Object params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.getProMembers(params);
	}
	
	
	public List<Map<String, Object>> getDetail(Object params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.getDetail(params);
	}
	
	public List<Map<String, Object>> getRentList(Object params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.getRentList(params);
	}
	
	public List<Map<String, Object>> getTotalMaterialNum(Object params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.getTotalMaterialNum(params);
	}
	
	public int returnMaterial(Map<String, Object> params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		String returnPic = (String) params.get("returnPic");
		if (returnPic != null && !"".equals(returnPic)) {
			
			PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
			//上传到文件服务器
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", returnPic);
			Object content = httpClient.postSync(config.getPrefix() + config.getBatchUploadBase64ImgUrl(), ajaxParams);
			if (content != null) {
				
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					String fileCode = jsonObj.getString("generateKey");
					if (fileCode != null && !"".equals(fileCode)) {
						
						params.put("returnPic", fileCode);
					}
				}
				else {
					
					params.put("returnPic", "");
				}
			}
			else {
				
				params.put("returnPic", "");
			}
		}
		
		return queryDao.returnMaterial(params);
	}
	
	public PageBean selectApplyMaterialPage(Map<String, Object> params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.selectApplyMaterialPage(params);
	}
	
	
	public PageBean getStaticsMaterial(Map<String, Object> params,int currentPage,int perpage){
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.getStaticsMaterial(params,currentPage,perpage);
		
	}
	
	public PageBean selectProcessedMaterialPage(Map<String, Object> params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.selectProcessedMaterialPage(params);
	}

	public Map<String, Object> selectMaterialFlow(String flowCode) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.selectMaterialFlow(flowCode);
	}
	
	public List<Map<String, Object>> selectApplyedMaterial(String flowCode) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.selectApplyedMaterial(flowCode);
	}
	
	public List<Map<String, Object>> getDetailMaterial(Map params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.getDetailMaterial(params);
	}
	
	
	
	public int auditBorrowMaterial(Map<String, Object> params) {
		
		int flowStatus = Integer.parseInt(params.get("flowStatus").toString());
		String flowCode = params.get("flowCode").toString();
		int affectedCount = 0;
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		if (flowStatus == FlowStatusEnum.FLOW_AUDIT.getStatus()) {
			
			String auditContent = (String) params.get("auditContent");
			if (auditContent != null && !"".equals(auditContent)) {
				
				List<Map<String, Object>> auditMaterialList = new ArrayList<Map<String,Object>>();
				String[] auditContents = auditContent.split(";");
				int totalUseCount = 0;
				for (String auditStr : auditContents) {
					
					String[] _materialContents = auditStr.split(",");
					Map<String, Object> materialItem = new HashMap<String, Object>();
					int _count = Integer.parseInt(_materialContents[1]);
					if (_count > 0) {
						
						materialItem.put("flowCode", flowCode);
						materialItem.put("materialBatchno", _materialContents[0]);
						materialItem.put("useCount", _count);
						auditMaterialList.add(materialItem);
						totalUseCount += _count;
					}
					
				}
				
				if (queryDao.insertMaterialUseRecord(auditMaterialList) > 0) {
					params.put("tolUseCount", totalUseCount);
					affectedCount = queryDao.auditBorrowMaterial(params);
					if (affectedCount > 0) {
						
						for (Map<String, Object> materialItem : auditMaterialList) {
							
							materialItem.put("tolUseCount", -Integer.parseInt(materialItem.get("useCount").toString()));
							queryDao.updateMaterialCurCount(materialItem);
						}
						
					}
				}
			}
		}
		else if (flowStatus == FlowStatusEnum.FLOW_REBUT.getStatus()) {
			
			affectedCount = queryDao.auditBorrowMaterial(params);
		}
		
		return affectedCount;
	}
	
	public int auditReturnMaterial(Map<String, Object> params) {
		
		String flowCode = params.get("flowCode").toString();
		int affectedCount = 0;
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		List<Map<String, Object>> materialList = queryDao.selectApplyedMaterial(flowCode);
		if (materialList != null && materialList.size() > 0) {
			
			affectedCount = queryDao.auditReturnMaterial(params);
			for (Map<String, Object> materialItem : materialList) {
				
				materialItem.put("tolUseCount", materialItem.get("useCount"));
				queryDao.updateMaterialCurCount(materialItem);
			}
		}
		
		return affectedCount;
	}

	public FinalHttp getHttpClient() {
		return httpClient;
	}


	public void setHttpClient(FinalHttp httpClient) {
		this.httpClient = httpClient;
	}
	
	
	public List<Map<String, Object>> exportTableList(Object params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.exportTableList(params);
	}
	public List<Map<String, Object>> exportMaterialUseBase(Object params) {
		
		MaterialBaseDao queryDao = (MaterialBaseDao) getDao();
		
		return queryDao.exportMaterialUseBase(params);
	}
	
}
