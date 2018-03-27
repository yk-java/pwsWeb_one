/**
 * @Title: DormService.java
 * @Package com.glens.pwCloudOs.materielMg.dormMg.dorm.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-24 下午5:29:58
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.dormMg.dorm.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.core.utils.FileUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.materielMg.assetMg.asset.dao.AssetDao;
import com.glens.pwCloudOs.materielMg.dormMg.dorm.dao.DormDao;
import com.glens.pwCloudOs.materielMg.dormMg.dorm.vo.DormVo;
import com.glens.pwCloudOs.om.deviceMgr.imagesMgr.vo.DeviceBookVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class DormService extends EAPAbstractService {
	
	private FinalHttp httpClient = new FinalHttp();

	public List<Map<String, String>> selectBedByDormCode(Map<String, String> params) {
		
		DormDao dormDao = (DormDao) getDao();
		
		return dormDao.selectBedByDormCode(params);
	}
	
	public List<Map<String, Object>> selectRentDorm(Map<String, Object> params) {
		
		DormDao dormDao = (DormDao) getDao();
		List<Map<String, Object>> rentDormList = dormDao.selectRentDorm(params);
		if (rentDormList != null && rentDormList.size() > 0) {
			
			for (Map<String, Object> rentDorm : rentDormList) {
				
				String curProNos = (String) rentDorm.get("curProNos");
				if (curProNos != null && !"".equals(curProNos)) {
					
					int curProCount = curProNos.split(",").length;
					rentDorm.put("curProCount", curProCount);
				}
				else {
					
					rentDorm.put("curProCount", 0);
				}
			}
		}
		
		return rentDormList;
	}
	
	//获取合同列表
	public List<Map<String, Object>> getContract(String dormCode){
		Map<String, Object> map=new HashMap();
		map.put("dormCode", dormCode);
		
		DormDao dormDao = (DormDao) getDao();
		return dormDao.getContract(map);
	}
	
	
	
	//获取开票列表
	public List<Map<String, Object>> getBilling(String dormCode){
		Map<String, Object> map=new HashMap();
		map.put("dormCode", dormCode);
		
		DormDao dormDao = (DormDao) getDao();
		return dormDao.getBilling(map);
	}
	
	
	public boolean checkDorm(Map<String, Object> params,String employeeCode,String employeeName){
		
		String dormCode=params.get("dormCode").toString();
		String proNo=params.get("proNo").toString();
		String proName=params.get("proName").toString();
		Map<String, Object> map=new HashMap();
		map.put("dormCode", dormCode);
		DormDao dormDao = (DormDao) getDao();
		
		List list=dormDao.checkDorm(map);
		
		if(list!=null && list.size()>0){
			Map m=(Map)list.get(0);
			int maxbeds=0;
			if(m.get("maxbeds")!=null){
				maxbeds=Integer.parseInt(m.get("maxbeds").toString());
			}
			int curbeds=0;
			if(m.get("curbeds")!=null){
				curbeds=Integer.parseInt(m.get("curbeds").toString());
			}
			String proNos="";
			if(m.get("proNos")!=null){
				proNos=m.get("proNos").toString();
			}
			String proNames="";
			if(m.get("proNames")!=null){
				proNames=m.get("proNames").toString();
			}
			
			if(curbeds>=maxbeds){ //床位已满 无法添加
				return false;
			}else{
				if(proNos!="" && !proNos.equals("")){
					if(proNos.indexOf(proNo)>-1){//已经存在该项目
						
					}else{
						proNos=proNos+","+proNo;
						proNames=proNames+","+proName;
					}
				}else{
					proNos=proNo;
					proNames=proName;
				}
			}
			map.put("proNos", proNos);
			map.put("proNames", proNames);
		}
		
		
		
		
		dormDao.updateDormMess(map); //更新数量及项目名称
		params.put("employeeName", employeeName);
		params.put("employeeCode", employeeCode);
		dormDao.insertDormPerson(params);
		return true;
	}
	
	
public boolean checkDorm1(Map<String, Object> params,String proNo,String proName,String rentDate,String returnDate,String employeeCode,String employeeName){
		
		String dormCode=params.get("dormCode").toString();
		//String proNo=params.get("proNo").toString();
		//String proName=params.get("proName").toString();
		Map<String, Object> map=new HashMap();
		map.put("dormCode", dormCode);
		DormDao dormDao = (DormDao) getDao();
		
		List list=dormDao.checkDorm(map);
		
		if(list!=null && list.size()>0){
			Map m=(Map)list.get(0);
			int maxbeds=0;
			if(m.get("maxbeds")!=null){
				maxbeds=Integer.parseInt(m.get("maxbeds").toString());
			}
			int curbeds=0;
			if(m.get("curbeds")!=null){
				curbeds=Integer.parseInt(m.get("curbeds").toString());
			}
			String proNos="";
			if(m.get("proNos")!=null){
				proNos=m.get("proNos").toString();
			}
			String proNames="";
			if(m.get("proNames")!=null){
				proNames=m.get("proNames").toString();
			}
			
			if(curbeds>=maxbeds){ //床位已满 无法添加
				return false;
			}else{
				if(proNos!="" && !proNos.equals("")){
					if(proNos.indexOf(proNo)>-1){//已经存在该项目
						
					}else{
						proNos=proNos+","+proNo;
						proNames=proNames+","+proName;
					}
				}else{
					proNos=proNo;
					proNames=proName;
				}
			}
			map.put("proNos", proNos);
			map.put("proNames", proNames);
		}
		
		
		
		
		dormDao.updateDormMess(map); //更新数量及项目名称
		//params.put("employeeName", employeeName);
		//params.put("employeeCode", employeeCode);
		
		Map insertMap=new HashMap();
		insertMap.put("dormCode", dormCode);
		insertMap.put("proNo", proNo);
		insertMap.put("employeeName", employeeName);
		insertMap.put("employeeCode", employeeCode);
		insertMap.put("rentDate", rentDate);
		insertMap.put("returnDate", returnDate);
		
		dormDao.insertDormPerson(insertMap);
		return true;
	}
	
	public boolean addContract(Object parameters, String root) throws IOException, ParseException {
		
		DormVo vo=(DormVo)parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		boolean _ok =false;
		DormDao dormDao = (DormDao) getDao();

		Map map=new HashMap();
		map.put("dormCode", vo.getDormCode());
		map.put("contractName", vo.getPicTitle());
		map.put("contractDate", vo.getContractDate());
		

		if (vo != null && vo.getContractDoc() != null) {
			String tmpFileHomeUrl = root + File.separator + config.getTmpfileHome();
			String fileName = vo.getContractDoc().getOriginalFilename();
			File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
			boolean writeResult = FileUtil.writeFile(vo.getContractDoc().getInputStream(), tmpFile);
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
							map.put("contractScanimg", fileCode);

							
							_ok = dormDao.insertContract(map);
							
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
	
	public boolean insertInvoice(Object parameters, String root) throws IOException, ParseException {
		
		DormVo vo=(DormVo)parameters;
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		boolean _ok =false;
		DormDao dormDao = (DormDao) getDao();

		Map map=new HashMap();
		map.put("dormCode", vo.getDormCode());
		map.put("invoiceName", vo.getPicTitle());
		map.put("invoiceDate", vo.getInvoiceDate());
		

		if (vo != null && vo.getContractDoc() != null) {
			String tmpFileHomeUrl = root + File.separator + config.getTmpfileHome();
			String fileName = vo.getContractDoc().getOriginalFilename();
			File tmpFile = new File(tmpFileHomeUrl + File.separator + fileName);
			boolean writeResult = FileUtil.writeFile(vo.getContractDoc().getInputStream(), tmpFile);
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
							map.put("invoiceScanimg", fileCode);

							
							_ok = dormDao.insertInvoice(map);
							
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
	
	
	public boolean deleteContract(String contractScanimg){
		DormDao dormDao = (DormDao) getDao();
		
		Map m=new HashMap();
		m.put("contractScanimg", contractScanimg);
		
		boolean isdelete=dormDao.deleteContract(m);
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		if (contractScanimg != null && !contractScanimg.equals("")) {
			AjaxParams params = new AjaxParams();

			params.put("fileCode", contractScanimg);
			Object content = httpClient.postSync(config.getPrefix()+ config.getDeleteFileUrl(), params);	
		}


		return isdelete;
	}
	
	
	public boolean deleteInvoice(String invoiceScanimg){
		DormDao dormDao = (DormDao) getDao();
		
		Map m=new HashMap();
		m.put("invoiceScanimg", invoiceScanimg);
		
		boolean isdelete=dormDao.deleteInvoice(m);
		PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
		if (invoiceScanimg != null && !invoiceScanimg.equals("")) {
			AjaxParams params = new AjaxParams();

			params.put("fileCode", invoiceScanimg);
			Object content = httpClient.postSync(config.getPrefix()+config.getDeleteFileUrl(), params);	
		}


		return isdelete;
	}
	
	//把人员从宿舍移出
	public boolean deletePerson(String employeeCode,String returnDate){
		DormDao dormDao = (DormDao) getDao();
		
		Map m=new HashMap();
		m.put("employeeCode", employeeCode);
		m.put("returnDate", returnDate);
		
		boolean isdelete=dormDao.deletePerson(m);
		


		return isdelete;
	}
	
	
	public boolean removePro(String dormCode,String proNo,String proName){
		DormDao dormDao = (DormDao) getDao();
		
		Map map=new HashMap();
		map.put("dormCode", dormCode);
		

		
		List list=dormDao.checkDorm(map);
		
		if(list!=null && list.size()>0){
			Map m=(Map)list.get(0);
			
			String proNos="";
			if(m.get("proNos")!=null){
				proNos=m.get("proNos").toString();
			}
			String proNames="";
			if(m.get("proNames")!=null){
				proNames=m.get("proNames").toString();
			}
			
			String pnos[]=proNos.split(",");
			String pnames[]=proNames.split(",");
			String savePro="";
			String savePname="";
			for(int i=0;i<pnos.length;i++){
				String tempPno=pnos[i];
				String tempPname=pnames[i];
				if(tempPno.equals(proNo)){
					
				}else{
					savePro+=tempPno+",";
					savePname+=tempPname+",";
				}
			}
			if(!savePro.equals("")){
				savePro=savePro.substring(0,savePro.length()-1);
				savePname=savePname.substring(0,savePname.length()-1);
				map.put("proNos", savePro);
				map.put("proNames", savePname);
				dormDao.updatePro(map);
			}else{
				Map params=new HashMap();
				
				params.put("proNos", "");
				params.put("proNames", "");
				params.put("dormCode", dormCode);
				dormDao.updateStatus(params);
			}
			
			//Map params=new HashMap();
			
			
		}
		return true;
	}
	
	
	
	
	public boolean updateNum(String dormCode,int status){
		DormDao dormDao = (DormDao) getDao();
		
		Map m=new HashMap();
		m.put("dormCode", dormCode);
		m.put("status", status);
		boolean isdelete=dormDao.updateNum(m);
		


		return isdelete;
	}
	
	
	public List getDormRentNums(String dormCode){
		DormDao dormDao = (DormDao) getDao();
		Map m=new HashMap();
		m.put("dormCode", dormCode);
		
		return dormDao.getDormRentNums(m);
	}
	
	//判断该人员是否有在租的宿舍
	public boolean getPersonDorm(String employeeCode){
		
		DormDao dormDao = (DormDao) getDao();
		Map m=new HashMap();
		m.put("employeeCode", employeeCode);
		
		List list= dormDao.getPersonDorm(m);
		if(list.size()>0){
			return false;
		}else{
			return true;
		}
		
	}
	
	
	
	
	
	
	public PageBean queryHasbedForPage(Map parameters) {
		DormDao dormDao = (DormDao) getDao();
		return dormDao.queryHasbedForPage(parameters);
	}
	
	public Map<String, Object> dormScrap(String dormCode,String  refundaMount,String refundDate,String refundDesc) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dormCode", dormCode);
		
		
		params.put("refundaMount", refundaMount);
		params.put("refundDate", refundDate);
		params.put("refundDesc", refundDesc);
		DormDao dormDao = (DormDao) getDao();
		Map<String, Object> proDetail = dormDao.dormScrap(params);
		
		return proDetail;
	}
	
	public PageBean getVehicleList(Map parameters, int currentPage, int perpage){
		
		DormDao dormDao = (DormDao) getDao();
		return dormDao.queryForPage1(parameters, currentPage, perpage);
		
	}
	
}
