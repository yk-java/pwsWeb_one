package com.glens.pwCloudOs.fm.contractBase.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.fm.contractBase.dao.ContractBaseDao;
import com.glens.pwCloudOs.fm.contractBase.vo.ContractBaseVo;
import com.glens.pwCloudOs.fm.contractBase.vo.ContractPageBean;

public class ContractBaseService extends EAPAbstractService {

	public ContractPageBean getList(Map parameters, int currentPage, int perpage) {

		// TODO Auto-generated method stub
		ContractBaseDao contractBaseDao = (ContractBaseDao) getDao();
		ContractPageBean page = contractBaseDao.queryForPage1(parameters, currentPage, perpage);
		if (page != null && page.getList() != null && page.getList().size() > 0) {
			
			List<Map<String, Object>> yearList = contractBaseDao.selectProOutputYears();
			page.setYearList(yearList);
			if (yearList != null && yearList.size() > 0) {
				
				//获取当前页的项目编号，用来获取对应项目的各年产出
				String proNos = "";
				for (int i = 0;i < page.getList().size();i++) {
					
					Map proItem = (Map) page.getList().get(i);
					proNos += proItem.get("proNo") + ",";
				}
				
				if (proNos.length() > 0) {
					
					proNos = proNos.substring(0, proNos.length() - 1);
				}
				
				//获取项目年产出
				List<Map<String, Object>> proOutputList = contractBaseDao.selectProOutput(proNos);
				List<Map<String, Object>> proList = page.getList();
				for (Map<String, Object> proItem : proList) {
					
					configProOutput(proItem, yearList, proOutputList);
				}
			}
			
		}
		
		return page;
	}

	public List queryList(String proNo) {
		// TODO Auto-generated method stub
		ContractBaseDao dao = (ContractBaseDao) getDao();
		return dao.queryList(proNo);
	}
	
	public Map<String, Object> queryProContract(Object parameters) {
		// TODO Auto-generated method stub
		Map<String, Object> resultWrapper = new HashMap<String, Object>();
		List<Map<String, Object>> proList = super.queryForList(parameters);
		
		resultWrapper.put("proList", proList);
		ContractBaseDao contractBaseDao = (ContractBaseDao) getDao();
		if (proList != null && proList.size() > 0) {
			
			List<Map<String, Object>> yearList = contractBaseDao.selectProOutputYears();
			resultWrapper.put("yearList", yearList);
			//获取当前页的项目编号，用来获取对应项目的各年产出
			String proNos = "";
			for (int i = 0;i < proList.size();i++) {
				
				Map<String, Object> proItem = proList.get(i);
				proNos += proItem.get("proNo") + ",";
			}
			
			if (proNos.length() > 0) {
				
				proNos = proNos.substring(0, proNos.length() - 1);
			}
			
			//获取项目年产出
			List<Map<String, Object>> proOutputList = contractBaseDao.selectProOutput(proNos);
			for (Map<String, Object> proItem : proList) {
				
				configProOutput(proItem, yearList, proOutputList);
			}
		}
		return resultWrapper;
	}
	
	private void configProOutput(Map<String, Object> proItem, 
			List<Map<String, Object>> yearList,
			List<Map<String, Object>> proOutputList) {
		
		for (Map<String, Object> yearItem : yearList) {
			
			int year = Integer.parseInt(yearItem.get("years").toString());
			proItem.put(year + "_workload", "");
			proItem.put(year + "_amount", "");
			
			for (Map<String, Object> outputItem : proOutputList) {
				
				int outputYear = Integer.parseInt(outputItem.get("years").toString());
				
				if (proItem.get("proNo").equals(outputItem.get("proNo")) 
						&& year == outputYear) {
					
					proItem.put(outputItem.get("years") + "_workload", outputItem.get("workload"));
					proItem.put(outputItem.get("years") + "_amount", outputItem.get("amount"));
					
					break;
				}
			}
		}
	}
	
	
	
	
	
	public boolean insert(Object parameters,Map params) {
		ContractBaseDao contractBaseDao = (ContractBaseDao) getDao();
		
		
		
		String attrJson = (String) params.get("attrJson");
		JSONArray attrMap = JSONArray.parseArray(attrJson);
		boolean istrue=false;
		
		ContractBaseVo vo=(ContractBaseVo)parameters;
		String proName="";
		for (int i = 0; i <attrMap.size(); i++) {
			Map tempmap=new HashMap();
			Map m=(Map)attrMap.getJSONObject(i);
			String proNo=m.get("proNo").toString();
			proName+=m.get("proName").toString()+",";
			double price=Double.parseDouble(m.get("price").toString());
			tempmap.put("proNo", proNo);
			tempmap.put("price", price);
			tempmap.put("contractNo", vo.getContractNo());
			istrue=contractBaseDao.insertProjLink(tempmap);
		}
		proName=proName.substring(0,proName.length()-1);
		vo.setProName(proName);
		
		istrue= contractBaseDao.insert(parameters);
		
		return istrue;
	}
	
	
	
	public int updateLinks(Object parameters,Map params) {
		// TODO Auto-generated method stub
		
		ContractBaseDao contractBaseDao = (ContractBaseDao) getDao();
		
		
		
		String attrJson = (String) params.get("attrJson");
		JSONArray attrMap = JSONArray.parseArray(attrJson);
		ContractBaseVo vo=(ContractBaseVo)parameters;
		String proName="";
		contractBaseDao.deleteProContractLinks(params);//先删除合同与项目关系
		for (int i = 0; i <attrMap.size(); i++) {
			Map tempmap=new HashMap();
			Map m=(Map)attrMap.getJSONObject(i);
			String proNo=m.get("proNo").toString();
			proName+=m.get("proName").toString()+",";
			double price=Double.parseDouble(m.get("price").toString());
			tempmap.put("proNo", proNo);
			tempmap.put("price", price);
			tempmap.put("contractNo", vo.getContractNo());
			contractBaseDao.insertProjLink(tempmap);
		}
		vo.setProName(proName);
		return contractBaseDao.update(parameters);
	}
	
	
	public Map getDetail(Object parameters,Map params) {
		// TODO Auto-generated method stub
		Map returnMap=new HashMap();
		ContractBaseDao contractBaseDao = (ContractBaseDao) getDao();
		List list=contractBaseDao.getDetail(parameters);
		returnMap.put("obj", list.get(0));
		
		List list1=contractBaseDao.getLinksByContractNo(params);
		returnMap.put("linklist", list1);
		
		return returnMap;
	}
	
	public Map echatsData(Map params) {
		Map returnMap=new HashMap();
		ContractBaseDao contractBaseDao = (ContractBaseDao) getDao();
		List list=contractBaseDao.getProContractLinks(params);//通过合同找项目
		
		
		List nodes=new ArrayList();
		List links=new ArrayList();
		
		Map m1=new HashMap();
		m1.put("name", params.get("contractName"));
		m1.put("x", 0);
		m1.put("y", 0);
		nodes.add(m1);
		int x=0;
		int y=0;
		int y1=0;
		String pt="";
		for (int i = 0; i < list.size(); i++) {
			Map temp=(Map)list.get(i);
			String proNo=temp.get("PROJ_NO").toString();
			pt+=proNo+",";
		}
		
		
		for (int i = 0; i < list.size(); i++) {
			Map temp=(Map)list.get(i);
			String proNo=temp.get("PROJ_NO").toString();
			String proName=temp.get("PRO_NAME").toString();
			 
			Map proMap=new HashMap();
			
			proMap.put("name", proName);
			proMap.put("x", 300);
			proMap.put("y", y);
			y=y+100;
			nodes.add(proMap);
			
			Map temp1=new HashMap();
			
			temp1.put("source", proName);
			temp1.put("target", params.get("contractName"));
			temp1.put("price", temp.get("PRICE"));
			
			
			links.add(temp1);
			
			params.put("proNo", proNo);
			
			List list2=contractBaseDao.getContractProLinks(params); //通过项目找合同
			
			for (int j = 0; j < list2.size(); j++) {
				Map tp=(Map)list2.get(j);
				String contractName=tp.get("CONTRACT_NAME").toString();
				String contractNo=tp.get("CONTRACT_NO").toString();
				
				Map contractMap=new HashMap();
				contractMap.put("x", 0);
				contractMap.put("name", contractName);
				contractMap.put("y", y1+100);
				y1=y1+100;
				nodes.add(contractMap);
				
				Map tp1=new HashMap();
				tp1.put("source", proName);
				tp1.put("target", contractName);
				tp1.put("price", tp.get("PRICE"));
				links.add(tp1);
				
				Map searchMap=new HashMap();
				searchMap.put("contractNo", contractNo);
				searchMap.put("proNo", pt);
				
				List prolist=contractBaseDao.getProContractLinks1(searchMap);//通过合同找项目
				
				for (int k = 0; k < prolist.size(); k++) {
					Map temp2=(Map)prolist.get(k);
				
					String proName1=temp2.get("PRO_NAME").toString();
					 
					Map proMap1=new HashMap();
					
					proMap1.put("name", proName1);
					proMap1.put("x", 300);
					proMap1.put("y", y);
					y=y+100;
					nodes.add(proMap1);
					
					Map temp3=new HashMap();
					
					temp3.put("source", proName1);
					temp3.put("target", params.get("contractName"));
					temp3.put("price", temp.get("PRICE"));
					links.add(temp3);
				}
				
				
			}
			
		
			
		}
		returnMap.put("nodes", nodes);
		returnMap.put("links", links);
		 
		return returnMap;
	}
	
	
	public Map getStatistics1(Map params){
		Map returnMap=new HashMap();
		ContractBaseDao contractBaseDao = (ContractBaseDao) getDao();
		
		List list1=contractBaseDao.getStatistics1(params);
		List list2=contractBaseDao.getContractPlan(params);
		
		returnMap.put("list1", list1);
		returnMap.put("list2", list2);
		
		return returnMap;
	}
	public Map getStatistics2(Map params){
		Map returnMap=new HashMap();
		ContractBaseDao contractBaseDao = (ContractBaseDao) getDao();
		
		List list1=contractBaseDao.getStatistics2(params);
		List list2=contractBaseDao.getContractPlan(params);
		
		returnMap.put("list1", list1);
		returnMap.put("list2", list2);
		
		return returnMap;
	}
	
	
	
}
