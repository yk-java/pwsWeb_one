package com.glens.pwCloudOs.goods.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.core.utils.FileUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.goods.dao.GoodsDao;


public class GoodsService extends EAPAbstractService {
	
	
	private FinalHttp httpClient;
	
	public FinalHttp getHttpClient() {
		return httpClient;
	}


	public void setHttpClient(FinalHttp httpClient) {
		this.httpClient = httpClient;
	}


	public PageBean getProcessList(Map params,int currentpage,int perPage){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.getProcessPage(params,currentpage,perPage);
	}
	
	
	
	public PageBean getCheckPage(Map params,int currentpage,int perPage){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.getCheckPage(params,currentpage,perPage);
	}
	
	
	public PageBean getWasteProcessPage(Map params,int currentpage,int perPage){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.getWasteProcessPage(params,currentpage,perPage);
	}
	
	public PageBean getMasteCheckPage(Map params,int currentpage,int perPage){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.getMasteCheckPage(params,currentpage,perPage);
	}
	
	
	public boolean addGoodsProcess(Map<String, Object> params,String root) throws IOException {
		GoodsDao dao=(GoodsDao)getDao();
		
		boolean isSuccess=false;
		
		
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext()
				.getBean("pwcloudosConfig");
	
		//手机端上传
		String p1="";
		if(params.get("picCode1")!=null){
			p1=params.get("picCode1").toString();
		}
		if(p1!=null && p1 !="" && !p1.equals("")){
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", p1);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						params.put("pic1", fileCode);
					}
				}
			}
		}
		//web端上传
		if(params.get("img1")!=null){
			MultipartFile file=(MultipartFile)params.get("img1");
			String fileName = file.getOriginalFilename();
			String tmpFileHomeUrl = root + File.separator
					+ config.getTmpfileHome();
			File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
			boolean writeResult = FileUtil.writeFile(file.getInputStream(), tmpFile);
			if (writeResult) {
				AjaxParams pa = new AjaxParams();
				pa.put("file", tmpFile);
				Object content = httpClient.postSync(
						config.getPrefix()+ config.getFileServerUploadUrl(), pa);
				
				if (content != null) {
					JSONObject jsonObj = JSONObject.parseObject(content
							.toString());
					if ("200".equals(jsonObj.get("statusCode"))) {

						JSONObject fileJsonObj = jsonObj.getJSONObject("data");
						if (fileJsonObj != null) {
							String fileCode = fileJsonObj.getString("fileCode");
							params.put("pic1", fileCode);
						}
					}
				}
				if (tmpFile.exists()) {
					tmpFile.delete();
				}
			}
			
		}
		
		
		
		if(params.get("proNo")!=null){
			List proManager=dao.getProManager(params);
			if(proManager!=null && proManager.size()>0){
				Map mapmanager=(Map)proManager.get(0);
				String employee="";
				if(mapmanager.get("EMPLOYEECODE")!=null){
					employee=mapmanager.get("EMPLOYEECODE").toString();
				}
				params.put("proManager", employee);
			}
			
			params.put("goodsManager", PwCloudOsConstant.GOODS_MANAGER);
			List list=dao.getGoodsManager(params);
			if(list!=null && list.size()>0){
				Map m=(Map)list.get(0);
				String employeecode=m.get("EMPLOYEECODE").toString();
				params.put("storeMan", employeecode);
			}
		}
		
		isSuccess=dao.addGoodsProcess(params)>=0;
		
		
		String attrJson = (String) params.get("attrJson");
		JSONArray attrMap = JSONArray.parseArray(attrJson);
		
		
		
		
		for (int i = 0; i <attrMap.size(); i++) {
			Map m=(Map)attrMap.getJSONObject(i);
			String goodsName=m.get("goodsName").toString();//物资名称
			String goodsCode=m.get("goodsCode").toString();//物资code
			double num=Double.parseDouble(m.get("num").toString());//数量
			String unitCode=m.get("unitCode").toString();//单位
			String storeCode=m.get("storeCode").toString();//仓库编码
			String storeNo=m.get("storeNo").toString(); //库存编号
		
			int ismainunitcode=1; //是否主单位
			if(m.get("isMainUnit")!=null){
				ismainunitcode=Integer.parseInt(m.get("isMainUnit").toString());
				if(ismainunitcode==0){
					double conversion=Double.parseDouble(m.get("conversionFormula").toString());
					params.put("num", num/conversion);
				}else{
					params.put("num", num);
				}
			}
			
			params.put("goodsName", goodsName);
			params.put("goodsCode", goodsCode);
			params.put("unitCode", unitCode);
			params.put("storeCode", storeCode);
			params.put("storeNo", storeNo);
			
			isSuccess=dao.addGoodsDetail(params)>=0;
			
		}
		return isSuccess;
	}
	
	
	public boolean checkProcess (Map<String, Object> params){
		boolean isSuccess=false;
		GoodsDao dao=(GoodsDao)getDao();
		
		
		int processStatus=Integer.parseInt(params.get("processStatus").toString());
		int gtype=Integer.parseInt(params.get("gtype").toString());
		if(processStatus==2){ //项目经理审核通过
			
			params.put("goodsManager", PwCloudOsConstant.GOODS_MANAGER);
			List list=dao.getGoodsManager(params);
			if(list!=null && list.size()>0){
				Map m=(Map)list.get(0);
				String employeecode=m.get("EMPLOYEECODE").toString();
				params.put("storeMan", employeecode);
				isSuccess=dao.checkProcess(params)>=0;
			}
		}else if(processStatus==4){//物资员审核通过
			isSuccess=dao.checkProcess(params)>=0;
			if(isSuccess){
				dao.updateProcessDetail(params); //更新流程明细  改为完成状态
				
				List checkList=dao.getCheckProcessDetail(params);
				if(checkList!=null && checkList.size()>0){
					for(int i=0;i<checkList.size();i++){
						Map m=(Map)checkList.get(i);
						String stockCode=m.get("STOCK_CODE").toString();
						String goodsCode=m.get("GOODS_CODE").toString();
						String storeCode=m.get("STORE_CODE").toString();
						double amount=Double.parseDouble(m.get("AMOUNT").toString());
						
						params.put("stockCode", stockCode);
						params.put("goodsCode", goodsCode);
						params.put("storeCode", storeCode);
						params.put("amount", amount);
						
						
						if(gtype==1 || gtype==2){ //物资入库  退料入库
							dao.updateStock(params); //更新库存  +
						}else if(gtype==3){ //物资出库 
							dao.updateStock1(params); //更新库存  -
						}
					}
				}
			}
			
			
		}else if(processStatus==3 || processStatus==5){
			isSuccess=dao.checkProcess(params)>=0; //驳回
		}
		
		
		return isSuccess;
	}
	
	
	public boolean wasteCheckProcess (Map<String, Object> params){
		boolean isSuccess=false;
		GoodsDao dao=(GoodsDao)getDao();
		
		
		int processStatus=Integer.parseInt(params.get("processStatus").toString());
		int processType=Integer.parseInt(params.get("processType").toString());
		
		if(processType==1){ //废料入库
			
			if(processStatus==2){ //项目经理审核通过
				
				params.put("goodsManager", PwCloudOsConstant.GOODS_MANAGER);
				List list=dao.getGoodsManager(params);
				if(list!=null && list.size()>0){
					Map m=(Map)list.get(0);
					String employeecode=m.get("EMPLOYEECODE").toString();
					params.put("storeMan", employeecode);
					isSuccess=dao.checkWasteProcess(params)>=0;
				}
				
			}else if(processStatus==4){//物资员审核通过
				isSuccess=dao.checkWasteProcess(params)>=0;
				if(isSuccess){
					
					
					List checkList=dao.getWasteCheckProcessDetail(params); //获取详细列表
					boolean haveStore=false;
					if(checkList!=null && checkList.size()>0){

					   
					
						for(int i=0;i<checkList.size();i++){
							Map mp=(Map)checkList.get(i);
							String stockCode="";
							if(mp.get("STOCK_CODE")!=null){
								stockCode=mp.get("STOCK_CODE").toString();
							}
									
							String code=mp.get("CATEGORY_CODE").toString();
							String storeCode=mp.get("STORE_CODE").toString();
							double amount=Double.parseDouble(mp.get("OUT_IN_COUNT").toString());
							
							params.put("stockCode", stockCode);
							params.put("categoryCode", code);
							params.put("storeCode", storeCode);
							params.put("amount", amount);
							
							List storeList=dao.isHaveStore(params);  //判断是否有库存 
							
							if(storeList!=null && storeList.size()>0){//有库存直接更新数量
								 dao.updateWasteStock(params); //更新库存  
								
								 dao.updateWasteProcessDetail(params); //更新流程明细  改为完成状态
								 
							}else{ //没有库存  新增一条库存
								
								CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
										CodeWorker.SIMPLE_CODE_WORKER);
								stockCode = codeWorker.createCode("S");
								params.put("stockCode", stockCode);
								
								dao.addWasteStock(params);//新增库存
								dao.updateWasteProcessDetail(params); //更新流程明细  改为完成状态
								
							}
	
							
							
						}
						
						
					}
					
				}
				
				
			}else if(processStatus==3 || processStatus==5){
				isSuccess=dao.checkWasteProcess(params)>=0; //驳回
			}
			
		}else if(processType==2){ //废料出库
			
			
			if(processStatus==6){ //财务经理审核通过
				isSuccess=dao.checkWasteProcess(params)>=0;
				if(isSuccess){
					
					List checkList=dao.getWasteCheckProcessDetail(params);
					if(checkList!=null && checkList.size()>0){
						for(int i=0;i<checkList.size();i++){
							Map m=(Map)checkList.get(i);
							String stockCode=m.get("STOCK_CODE").toString();
							String categoryCode=m.get("CATEGORY_CODE").toString();
							String storeCode=m.get("STORE_CODE").toString();
							double amount=Double.parseDouble(m.get("OUT_IN_COUNT").toString());
							params.put("stockCode", stockCode);
							params.put("categoryCode", categoryCode);
							params.put("storeCode", storeCode);
							params.put("amount", amount);

							dao.updateWasteStock1(params); //更新库存  
							dao.updateWasteProcessDetail(params); //更新流程明细  改为完成状态
							
						}
					}
				}
				
				
			}else if(processStatus==7){//财务经理驳回
				isSuccess=dao.checkWasteProcess(params)>=0; //驳回
			}
			
			
		}
		
		
		
		return isSuccess;
	}
	
	
	public boolean updateGoodsProcess(Map<String, Object> params,String root) throws IOException {
		GoodsDao dao=(GoodsDao)getDao();
		
		boolean isSuccess=false;
		
		
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext()
				.getBean("pwcloudosConfig");
	
		//手机端上传
		String p1="";
		if(params.get("picCode1")!=null){
			p1=params.get("picCode1").toString();
		}
		if(p1!=null && p1 !="" && !p1.equals("")){
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", p1);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						params.put("pic1", fileCode);
					}
				}
			}
		}
		//web端上传
		if(params.get("img1")!=null){
			MultipartFile file=(MultipartFile)params.get("img1");
			String fileName = file.getOriginalFilename();
			String tmpFileHomeUrl = root + File.separator
					+ config.getTmpfileHome();
			File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
			boolean writeResult = FileUtil.writeFile(file.getInputStream(), tmpFile);
			if (writeResult) {
				AjaxParams pa = new AjaxParams();
				pa.put("file", tmpFile);
				Object content = httpClient.postSync(
						config.getPrefix()+ config.getFileServerUploadUrl(), pa);
				
				if (content != null) {
					JSONObject jsonObj = JSONObject.parseObject(content
							.toString());
					if ("200".equals(jsonObj.get("statusCode"))) {

						JSONObject fileJsonObj = jsonObj.getJSONObject("data");
						if (fileJsonObj != null) {
							String fileCode = fileJsonObj.getString("fileCode");
							params.put("pic1", fileCode);
						}
					}
				}
				if (tmpFile.exists()) {
					tmpFile.delete();
				}
			}
			
		}
		
		
		
		if(params.get("proNo")!=null){
			List proManager=dao.getProManager(params);
			if(proManager!=null && proManager.size()>0){
				Map mapmanager=(Map)proManager.get(0);
				String employee="";
				if(mapmanager.get("EMPLOYEECODE")!=null){
					employee=mapmanager.get("EMPLOYEECODE").toString();
				}
				params.put("proManager", employee);
			}
		}
		
		isSuccess=dao.updateProcess(params)>=0;
		
		if(isSuccess){
			dao.deleteGoodsDetail(params); //先删除之前的流程明细  然后再新增
		}
		
		String attrJson = (String) params.get("attrJson");
		JSONArray attrMap = JSONArray.parseArray(attrJson);
		
		
		
		
		for (int i = 0; i <attrMap.size(); i++) {
			Map m=(Map)attrMap.getJSONObject(i);
			String goodsName=m.get("goodsName").toString();//物资名称
			String goodsCode=m.get("goodsCode").toString();//物资code
			double num=Double.parseDouble(m.get("num").toString());//数量
			String unitCode=m.get("unitCode").toString();//单位
			String storeCode=m.get("storeCode").toString();//仓库编码
			String storeNo=m.get("storeNo").toString(); //库存编号
		
			int ismainunitcode=1; //是否主单位
			if(m.get("isMainUnit")!=null){
				ismainunitcode=Integer.parseInt(m.get("isMainUnit").toString());
				if(ismainunitcode==0){
					double conversion=Double.parseDouble(m.get("conversionFormula").toString());
					params.put("num", num/conversion);
				}else{
					params.put("num", num);
				}
			}
			
			params.put("goodsName", goodsName);
			params.put("goodsCode", goodsCode);
			params.put("unitCode", unitCode);
			params.put("storeCode", storeCode);
			params.put("storeNo", storeNo);
			
			isSuccess=dao.addGoodsDetail(params)>=0;
			
		}
		
		
		return isSuccess;
	}
	
	
	public boolean updateWasteProcess(Map<String, Object> params,String root) throws IOException {
		GoodsDao dao=(GoodsDao)getDao();
		
		boolean isSuccess=false;
		
		
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext()
				.getBean("pwcloudosConfig");
	
		//手机端上传
		String p1="";
		if(params.get("picCode1")!=null){
			p1=params.get("picCode1").toString();
		}
		if(p1!=null && p1 !="" && !p1.equals("")){
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", p1);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						params.put("pic1", fileCode);
					}
				}
			}
		}
		//web端上传
		if(params.get("img1")!=null){
			MultipartFile file=(MultipartFile)params.get("img1");
			String fileName = file.getOriginalFilename();
			String tmpFileHomeUrl = root + File.separator
					+ config.getTmpfileHome();
			File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
			boolean writeResult = FileUtil.writeFile(file.getInputStream(), tmpFile);
			if (writeResult) {
				AjaxParams pa = new AjaxParams();
				pa.put("file", tmpFile);
				Object content = httpClient.postSync(
						config.getPrefix()+ config.getFileServerUploadUrl(), pa);
				
				if (content != null) {
					JSONObject jsonObj = JSONObject.parseObject(content
							.toString());
					if ("200".equals(jsonObj.get("statusCode"))) {

						JSONObject fileJsonObj = jsonObj.getJSONObject("data");
						if (fileJsonObj != null) {
							String fileCode = fileJsonObj.getString("fileCode");
							params.put("pic1", fileCode);
						}
					}
				}
				if (tmpFile.exists()) {
					tmpFile.delete();
				}
			}
			
		}
		
		
		int processType=Integer.parseInt(params.get("processType").toString());
		if(processType==1){  //废料入库
			
			if(params.get("proNo")!=null){
				List proManager=dao.getProManager(params);
				if(proManager!=null && proManager.size()>0){
					Map mapmanager=(Map)proManager.get(0);
					String employee="";
					if(mapmanager.get("EMPLOYEECODE")!=null){
						employee=mapmanager.get("EMPLOYEECODE").toString();
					}
					params.put("proManager", employee);
				}
			}
			
		}else if(processType==2){ //废料出库
			List list=dao.getCaiwuManager();
			if(list!=null && list.size()>0){
				Map caiwuManager=(Map)list.get(0);
				String employee="";
				if(caiwuManager.get("EMPLOYEECODE")!=null){
					employee=caiwuManager.get("EMPLOYEECODE").toString();
				}
				params.put("finance", employee);
				params.put("processStatus", 1);
			}
		}

		isSuccess=dao.updateWasteProcess(params)>=0;
		
		if(isSuccess){
			dao.deleteWasteDetail(params); //先删除之前的流程明细  然后再新增
		}
		
		String attrJson = (String) params.get("attrJson");
		JSONArray attrMap = JSONArray.parseArray(attrJson);
		
		
		for (int i = 0; i <attrMap.size(); i++) {
			Map m=(Map)attrMap.getJSONObject(i);
			String categoryCode=m.get("categoryCode").toString();//物资名称
			
			double num=Double.parseDouble(m.get("num").toString());//数量
			
			String storeCode=m.get("storeCode").toString();//仓库编码
			
			String storeNo=m.get("storeNo").toString(); //库存编号
			
			String price="";
			if(m.get("price")!=null){
				price=m.get("price").toString();
			}
					
		
			
			params.put("num", num);
			params.put("categoryCode", categoryCode);
			params.put("price", price);
			
			params.put("storeCode", storeCode);
			
			params.put("storeNo", storeNo);
			isSuccess=dao.addWasteProcessDetail(params)>=0;
		}
		
		
		return isSuccess;
	}
	
			
	public List getGoodsTypes(Map params){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.getGoodsTypes(params);
	}
	public List getGoodsUnit(Map params){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.getGoodsUnit(params);
	}
	public List getStores(Map params){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.getStores(params);
	}
	
	
	public Map getGoodsProcessDetail(Object params){
		Map returnMap=new HashMap();
		
		GoodsDao dao=(GoodsDao)getDao();
		List list1=dao.getGoodsProcessDetail(params);
		List list2=dao.getGoodsFlowDetail(params);
		if(list1!=null && list1.size()>0){
			returnMap.put("goodObj", list1.get(0));
		}else{
			returnMap.put("goodObj", null);
		}
		returnMap.put("goodslist", list2);
		return returnMap;
	}
	
	public Map getWasteProcessDetail(Object params){
		Map returnMap=new HashMap();
		
		GoodsDao dao=(GoodsDao)getDao();
		List list1=dao.getWasteProcessDetail(params);
		List list2=dao.getWasteFlowDetail(params);
		if(list1!=null && list1.size()>0){
			returnMap.put("goodObj", list1.get(0));
		}else{
			returnMap.put("goodObj", null);
		}
		returnMap.put("goodslist", list2);
		return returnMap;
	}
	
	public List getProCategory(Map params){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.getProCategory(params);
	}
	public List getPros(Map params){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.getPros(params);
	}
	
	public boolean addCategory(Map params){
		
		GoodsDao dao=(GoodsDao)getDao();
		boolean isSucess=dao.addCategory(params)>=0;
		
		return isSucess;
	}
	
	
	
	public List getWastTypes(Map params){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.getWastTypes(params);
	}
	
	public List exportGoodsStocks(Map params){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.exportGoodsStocks(params);
	}
	public List exportWasteStocks(Map params){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.exportWasteStocks(params);
	}
	
	public boolean addWasteProcess(Map<String, Object> params,String root) throws IOException {
		GoodsDao dao=(GoodsDao)getDao();
		
		boolean isSuccess=false;
		/*PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext()
				.getBean("pwcloudosConfig");
	
		//手机端上传
		String p1="";
		if(params.get("picCode1")!=null){
			p1=params.get("picCode1").toString();
		}
		if(p1!=null && p1 !="" && !p1.equals("")){
			AjaxParams ajaxParams = new AjaxParams();
			ajaxParams.put("base64Img", p1);
			Object content = httpClient.postSync(config.getPrefix()+config.getUploadMergeBase64ImgUrl(), ajaxParams);
			if (content != null) {
				JSONObject jsonObj = JSONObject.parseObject(content.toString());
				if ("200".equals(jsonObj.get("statusCode"))) {
					JSONObject fileJsonObj = jsonObj.getJSONObject("data");
					if (fileJsonObj != null) {
						String fileCode = fileJsonObj.getString("fileCode");
						params.put("pic1", fileCode);
					}
				}
			}
		}
		//web端上传
		if(params.get("img1")!=null){
			MultipartFile file=(MultipartFile)params.get("img1");
			String fileName = file.getOriginalFilename();
			String tmpFileHomeUrl = root + File.separator
					+ config.getTmpfileHome();
			File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
			boolean writeResult = FileUtil.writeFile(file.getInputStream(), tmpFile);
			if (writeResult) {
				AjaxParams pa = new AjaxParams();
				pa.put("file", tmpFile);
				Object content = httpClient.postSync(
						config.getPrefix()+ config.getFileServerUploadUrl(), pa);
				
				if (content != null) {
					JSONObject jsonObj = JSONObject.parseObject(content
							.toString());
					if ("200".equals(jsonObj.get("statusCode"))) {

						JSONObject fileJsonObj = jsonObj.getJSONObject("data");
						if (fileJsonObj != null) {
							String fileCode = fileJsonObj.getString("fileCode");
							params.put("pic1", fileCode);
						}
					}
				}
				if (tmpFile.exists()) {
					tmpFile.delete();
				}
			}
			
		}
		*/
		
		
		/*if(params.get("proNo")!=null){
			List proManager=dao.getProManager(params);
			if(proManager!=null && proManager.size()>0){
				Map mapmanager=(Map)proManager.get(0);
				String employee="";
				if(mapmanager.get("EMPLOYEECODE")!=null){
					employee=mapmanager.get("EMPLOYEECODE").toString();
				}
				params.put("proManager", employee);
			}
		}*/
		
		
		
		
		
		int processType=Integer.parseInt(params.get("processType").toString());
		if(processType==1){  //废料入库
			
			if(params.get("proNo")!=null){
				List proManager=dao.getProManager(params);
				if(proManager!=null && proManager.size()>0){
					Map mapmanager=(Map)proManager.get(0);
					String employee="";
					if(mapmanager.get("EMPLOYEECODE")!=null){
						employee=mapmanager.get("EMPLOYEECODE").toString();
					}
					params.put("proManager", employee);
					params.put("processStatus", 1);
				}
			}
			
			
			params.put("goodsManager", PwCloudOsConstant.GOODS_MANAGER);
			List list=dao.getGoodsManager(params);
			if(list!=null && list.size()>0){
				Map m=(Map)list.get(0);
				String employeecode=m.get("EMPLOYEECODE").toString();
				params.put("storeMan", employeecode);
			}
			
		}else if(processType==2){ //废料出库
			
			
			if(params.get("proNo")!=null){
				List proManager=dao.getProManager(params);
				if(proManager!=null && proManager.size()>0){
					Map mapmanager=(Map)proManager.get(0);
					String employee="";
					if(mapmanager.get("EMPLOYEECODE")!=null){
						employee=mapmanager.get("EMPLOYEECODE").toString();
					}
					params.put("proManager", employee);
					
				}
			}
			
			List list=dao.getCaiwuManager();
			if(list!=null && list.size()>0){
				Map caiwuManager=(Map)list.get(0);
				String employee="";
				if(caiwuManager.get("EMPLOYEECODE")!=null){
					employee=caiwuManager.get("EMPLOYEECODE").toString();
				}
				params.put("finance", employee);
				params.put("processStatus", 1);
			}
		}
		
		isSuccess=dao.addWasteProcess(params)>=0;
		
		
		String attrJson = (String) params.get("attrJson");
		JSONArray attrMap = JSONArray.parseArray(attrJson);
		for (int i = 0; i <attrMap.size(); i++) {
			Map m=(Map)attrMap.getJSONObject(i);
			String categoryCode=m.get("categoryCode").toString();//物资名称
			
			double num=Double.parseDouble(m.get("num").toString());//数量
			String price="";
			if(m.get("price")!=null){
				price=m.get("price").toString();
			}
					
			
			String storeCode="";
			if(m.get("storeCode")!=null){
				storeCode=m.get("storeCode").toString();//仓库编码
			}
			String storeNo="";
			if(m.get("storeNo")!=null){
				storeNo=m.get("storeNo").toString(); //库存编号
			}
			params.put("num", num);
			params.put("categoryCode", categoryCode);
			params.put("storeCode", storeCode);
			params.put("storeNo", storeNo);
			params.put("price", price);
			isSuccess=dao.addWasteProcessDetail(params)>=0;
		}
		return isSuccess;
	}
	
	
	
	public PageBean getGoodsStocks(Map params,int currentpage,int perPage){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.getGoodsStocks(params,currentpage,perPage);
	}
	
	public PageBean getWasteStocks(Map params,int currentpage,int perPage){
		GoodsDao dao=(GoodsDao)getDao();
		return dao.getWasteStocks(params,currentpage,perPage);
	}
	public PageBean searchGoodsProcess(Map params,int currentpage,int perPage){
		GoodsDao dao=(GoodsDao)getDao();
		PageBean page= dao.searchGoodsProcess(params,currentpage,perPage);
		List list=page.getList();
		List arrayList=new ArrayList();
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Map m=(Map)list.get(i);
				String processCode=m.get("PROCESS_CODE").toString();
				Map tp=new HashMap();
				tp.put("processCode", processCode);
				List detailList=dao.getGoodsFlowDetail(tp);
				m.put("detailList", detailList);
				arrayList.add(m);
			}
		}
		page.setList(arrayList);
		
		return page;
	}
	
	public List searchGoodsProcessList(Map params){
		GoodsDao dao=(GoodsDao)getDao();
		List list= dao.searchGoodsProcessList(params);
		
		List arrayList=new ArrayList();
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Map m=(Map)list.get(i);
				String processCode=m.get("PROCESS_CODE").toString();
				
				int gtype=Integer.parseInt(m.get("GTYPE").toString());
				if(gtype==1){
					m.put("GTYPENAME", "物资入库");
				}else if(gtype==2){
					m.put("GTYPENAME", "退料入库");
				}else if(gtype==3){
					m.put("GTYPENAME", "领用出库");
				}
				int status=Integer.parseInt(m.get("STATUS").toString());
				String statusName="进行中";
				if(status==1){
					m.put("STATUS_NAME", "发起");
				}else if(status==2){
					m.put("STATUS_NAME", "项目经理审核通过");
				}else if(status==3){
					m.put("STATUS_NAME", "项目经理审核驳回");
				}else if(status==4){
					statusName="已完成";
					m.put("STATUS_NAME", "物资员审核通过");
				}else if(status==5){
					m.put("STATUS_NAME", "物资员审核驳回");
				}else if(status==6){
					m.put("STATUS_NAME", "财务经理审核通过");
				}else if(status==7){
					m.put("STATUS_NAME", "财务经理审核驳回");
				}
				
				m.put("PROCESS_STATUS_NAME", statusName);
				
				
				Map tp=new HashMap();
				tp.put("processCode", processCode);
				List detailList=dao.getGoodsFlowDetail(tp);
				String content="";
				for (int j = 0; j < detailList.size(); j++) {
					Map goodmap=(Map)detailList.get(j);
					String goodsName=goodmap.get("goodsName").toString();
					String nums="";
					if(goodmap.get("num")!=null){
						nums=goodmap.get("num").toString();
					}
					content+=goodsName+":"+nums+";";
				}
				m.put("content", content);
				arrayList.add(m);
			}
		}

		return arrayList;
	}
	
	
	public PageBean searchWasteProcess(Map params,int currentpage,int perPage){
		GoodsDao dao=(GoodsDao)getDao();
		PageBean page= dao.searchWasteProcess(params,currentpage,perPage);
		List list=page.getList();
		List arrayList=new ArrayList();
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Map m=(Map)list.get(i);
				String processCode=m.get("PROCESS_CODE").toString();
				Map tp=new HashMap();
				tp.put("processCode", processCode);
				List detailList=dao.getWasteFlowDetail(tp);
				m.put("detailList", detailList);
				arrayList.add(m);
			}
		}
		page.setList(arrayList);
		
		return page;
	}
	
	public List exportWasteProcess(Map params){
		GoodsDao dao=(GoodsDao)getDao();
		List list= dao.exportWasteProcess(params);
		List arrayList=new ArrayList();
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Map m=(Map)list.get(i);
				String processCode=m.get("PROCESS_CODE").toString();
				int processType=Integer.parseInt(m.get("PROCESS_TYPE").toString());
				String processTypeName="";
				if(processType==1){
					processTypeName="废料入库";
				}else if(processType==2){
					processTypeName="废料出库";
				}
				m.put("processTypeName", processTypeName);
				
				int status=Integer.parseInt(m.get("STATUS").toString());
				String statusName="进行中";
				if(status==1){
					m.put("STATUS_NAME", "发起");
				}else if(status==2){
					m.put("STATUS_NAME", "项目经理审核通过");
				}else if(status==3){
					m.put("STATUS_NAME", "项目经理审核驳回");
				}else if(status==4){
					statusName="已完成";
					m.put("STATUS_NAME", "物资员审核通过");
				}else if(status==5){
					m.put("STATUS_NAME", "物资员审核驳回");
				}else if(status==6){
					statusName="已完成";
					m.put("STATUS_NAME", "财务经理审核通过");
				}else if(status==7){
					
					m.put("STATUS_NAME", "财务经理审核驳回");
				}
				m.put("PROCESS_STATUS_NAME", statusName);
				
				
				Map tp=new HashMap();
				tp.put("processCode", processCode);
				
				List detailList=dao.getWasteFlowDetail(tp);
				String content="";
				for (int j = 0; j < detailList.size(); j++) {
					Map goodmap=(Map)detailList.get(j);
					String goodsName=goodmap.get("categoryName").toString();
					String nums="";
					if(goodmap.get("num")!=null){
						nums=goodmap.get("num").toString();
					}
					content+=goodsName+":"+nums+";";
				}
				m.put("content", content);
				arrayList.add(m);
			}
		}
		
		return arrayList;
	}
	
	
}
