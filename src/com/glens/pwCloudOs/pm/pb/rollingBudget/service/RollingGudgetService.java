package com.glens.pwCloudOs.pm.pb.rollingBudget.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.pb.budgetEstimate.dao.BudgetEstimateDao;
import com.glens.pwCloudOs.pm.pb.rollingBudget.dao.RollingGudgetDao;
import com.google.gson.JsonArray;

public class RollingGudgetService extends EAPAbstractService {


	/**
	 * 项目概算未填状态
	 */
	private static final int PRO_BUDGET_ESTIMATE_UN_WRITE = 0;
	
	/**
	 * 项目概算已填状态
	 */
	private static final int PRO_BUDGET_ESTIMATE_WRITED = 1;
	
	/**
	 * 半个月的分割点
	 */
	private static final int HALF_MONTH_DAY = 15;
	
	private static final int SECTION_TYPE_HALFMONTH = 1;
	
	private static final int SECTION_TYPE_MONTH = 2;
	
	private static final int SECTION_TYPE_QUARTER = 3;
	
	private static final int SECTION_READONLY = 1;
	
	private static final int SECTION_WRITED = 0;

	public List selectProRollingBudgetList(Object parameters) {
		RollingGudgetDao dao=(RollingGudgetDao)getDao();
		return dao.selectProRollingBudgetList(parameters);
	}

	public Map<String, Object> getProCostDetailList(Map<String, Object> params) {

		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		RollingGudgetDao rollingGudgetDao=(RollingGudgetDao)getDao();
		
		
		List<Map<String, String>> newVersionList=rollingGudgetDao.getNewVersion(params);
		
		if (newVersionList!=null && newVersionList.size()>0) {
			Map m=(Map)newVersionList.get(0);
			resultMap.put("versionCode", m.get("versionCode").toString());
			resultMap.put("budgetCode", m.get("budgetCode").toString());
			resultMap.put("versionOrder", m.get("versionOrder").toString());
			resultMap.put("budgetNo", m.get("budgetNo").toString());
			params.put("budgetCode", m.get("budgetCode").toString());
			params.put("versionCode", m.get("versionCode").toString());
		}
		
		//得到区间
		List<Map<String, String>> sectionList = selectSectionList(params);
		
		if (sectionList != null && sectionList.size() > 0) {

			StringBuilder sqlSeqBuilder = new StringBuilder();
			for (Map<String, String> sectionItem : sectionList) {

				sqlSeqBuilder.append("SUM(CASE WHEN b.SECTION_CODE='");
				sqlSeqBuilder.append(sectionItem.get("sectionCode"));
				sqlSeqBuilder.append("' THEN b.COST ELSE 0 END) ");
				sqlSeqBuilder.append(sectionItem.get("sectionCode"));
				sqlSeqBuilder.append(",");
			}
			params.put("sqlSeq", sqlSeqBuilder.toString());
			//	params.put("itemCode", OTHER_COST_ITEM_CODE);

			List<Map<String, Object>> proCostList = rollingGudgetDao.selectProItemCostDetail(params);
			
			
			resultMap.put("proCostList", proCostList);
			resultMap.put("sectionList", sectionList);
			
			
			
			
		}

		return resultMap;
	}

	public List<Map<String, String>> selectSectionList(Map<String, Object> params) {

		RollingGudgetDao rollingGudgetDao = (RollingGudgetDao) getDao();

		return rollingGudgetDao.selectProSection(params);
	}

	public int insertProSectionCost(Map<String, Object> params,int isSubmit) {

		String companyCode = params.get("companyCode").toString();
		String proNo = params.get("proNo").toString();
		String itemCosts = params.get("itemCosts").toString();
		//String itemDescs =  params.get("itemDescs").toString();
		String versionCode=params.get("versionCode").toString();
		String budgetCode=params.get("budgetCode").toString();
		int icount = 0;
		RollingGudgetDao rollingGudgetDao = (RollingGudgetDao) getDao();

		if (itemCosts != null && !"".equals(itemCosts)) {

			String[] itemSectionCosts = itemCosts.split(";");
			List<Map<String, Object>> itemPhaseCostParamList = new ArrayList<Map<String,Object>>();
			for (String itemSectionCost : itemSectionCosts) {

				Map<String, Object> itemPhaseCostMap = new HashMap<String, Object>();
				String[] itemSectionCostParams = itemSectionCost.split(",");
				itemPhaseCostMap.put("companyCode", companyCode);
				itemPhaseCostMap.put("proNo", proNo);
				itemPhaseCostMap.put("itemCode", itemSectionCostParams[0]);
				itemPhaseCostMap.put("sectionCode", itemSectionCostParams[1]);
				itemPhaseCostMap.put("cost", itemSectionCostParams[2]);

				itemPhaseCostMap.put("budgetCode", budgetCode);
				itemPhaseCostMap.put("versionCode", versionCode);
				
				itemPhaseCostParamList.add(itemPhaseCostMap);
			}


			
			rollingGudgetDao.deleteProCost(params);
			icount = rollingGudgetDao.insertProSectionCost(itemPhaseCostParamList);
			insertSectionLabourCost(params);//批量新增人力资源成本
			
			if(isSubmit==2){//提交
				try {
					params.put("versionStatus", "2");
					rollingGudgetDao.updateVersion(params);
					createNewBudgetVersion(params);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(isSubmit==1){
				params.put("versionStatus", "1");
				rollingGudgetDao.updateVersion(params);
			}
			
		}

		return icount;
	}

	public Map<String, Object> getTravalList(Map<String, Object> params) {
		
		RollingGudgetDao rollingGudgetDao = (RollingGudgetDao) getDao();
		//得到项目区间
		List<Map<String, String>> sectionList = selectSectionList(params);
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		
		if (sectionList != null && sectionList.size() > 0) {

			StringBuilder sqlSeqBuilder = new StringBuilder();
			for (Map<String, String> sectionItem : sectionList) {

				sqlSeqBuilder.append("SUM(CASE WHEN a.SECTION_CODE='");
				sqlSeqBuilder.append(sectionItem.get("sectionCode"));
				sqlSeqBuilder.append("' THEN a.TRACE_SUBSIDY ELSE 0 END) ");
				sqlSeqBuilder.append(sectionItem.get("sectionCode"));
				sqlSeqBuilder.append(",");
			}
			params.put("sqlSeq", sqlSeqBuilder.toString());
			//	params.put("itemCode", OTHER_COST_ITEM_CODE);

			List<Map<String, Object>> datalist = rollingGudgetDao.getEmpCostDetail(params);
			resultMap.put("dataList", datalist);
			resultMap.put("sectionList", sectionList);
		}
		return resultMap;
	}
	
	
	//得到人力资源成本
	
	public Map<String, Object> getSectionLabourCost(Map<String, Object> params) {
		
		RollingGudgetDao rollingGudgetDao = (RollingGudgetDao) getDao();
		//得到项目区间
		List<Map<String, Object>> resultSectionList = new ArrayList<Map<String,Object>>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", resultSectionList);
		List<Map<String, String>> sectionList = selectSectionList(params);
		List<Map<String, Object>> labourCostDetailList = rollingGudgetDao.getSectionLabourCost(params);
		List<Map<String, Object>> labourCostList = rollingGudgetDao.selectSectionLabourSumCost(params);
		
		if (sectionList != null && sectionList.size() > 0) {

			for (Map<String, String> sectionItem : sectionList) {

				Map<String, Object> section = new HashMap<String, Object>();
				section.put("sectionCode", sectionItem.get("sectionCode"));
				section.put("startTime", sectionItem.get("startTime"));
				section.put("endTime", sectionItem.get("endTime"));
				section.put("proNo", sectionItem.get("proNo"));
				section.put("proName", sectionItem.get("proName"));
				section.put("readOnly", sectionItem.get("readOnly"));
				
				writeLabourCostToSection(section, labourCostDetailList, labourCostList);
				resultSectionList.add(section);
			}
		}
		return resultMap;
	}
	
	private void writeLabourCostToSection(Map<String, Object> sectionItem, 
			List<Map<String, Object>> labourCostDetailList, List<Map<String, Object>> labourCostList) {
		
		List<Map<String, Object>> sectionLabourCostList = new ArrayList<Map<String,Object>>();
		sectionItem.put("list", sectionLabourCostList);
		if (labourCostDetailList != null && labourCostDetailList.size() > 0) {
			
			for (Map<String, Object> labourCostDetailItem : labourCostDetailList) {
				
				if (sectionItem.get("sectionCode").equals(labourCostDetailItem.get("sectionCode"))) {
					
					sectionLabourCostList.add(labourCostDetailItem);
				}
			}
		}
		
		if (labourCostList != null && labourCostList.size() > 0) {
			
			for (Map<String, Object> labourCostItem : labourCostList) {
				
				if (sectionItem.get("sectionCode").equals(labourCostItem.get("sectionCode"))) {
					
					Map<String, Object> totalItem = new HashMap<String, Object>();
					totalItem.put("employeeName", "总计");
					totalItem.put("employeeCode", "总计");
					totalItem.put("univalent", "");
					totalItem.put("workTime", labourCostItem.get("workTime"));
					totalItem.put("labourCost", labourCostItem.get("cost"));
					sectionLabourCostList.add(totalItem);
				}
			}
		}
	}
	
	//删除差旅补贴
	public int deleteTravelSubsidy(Map<String, Object> params) {
		// TODO Auto-generated method stub
		RollingGudgetDao rollingGudgetDao = (RollingGudgetDao) getDao();
		int afterDelete= rollingGudgetDao.deleteTravelSubsidy(params);
		if(afterDelete>0){
			List<Map<String, Object>> travlList  = rollingGudgetDao.selectProSectionEmployeeLabourCost(params);//得到项目区间的总额
			int result=rollingGudgetDao.selectProSectionLabourCostCountInItem(params);//判断 区间内预算项的总价
			if(travlList!=null && travlList.size()>0){
				Map<String, Object> item = travlList.get(0);
				params.put("traceSubsidy", item.get("traceSubsidy"));
				rollingGudgetDao.updatePlusProSectionItemLabourCost(params);
			}
			
			
		}
		return afterDelete;
	}
	
	//删除人力资源成本
	public int deleteSectionLabourCost(Map<String, Object> params) {
		// TODO Auto-generated method stub
		RollingGudgetDao rollingGudgetDao = (RollingGudgetDao) getDao();
		int afterDelete= rollingGudgetDao.deleteSectionLabourCost(params);
		if(afterDelete>0){
			List<Map<String, Object>> list  = rollingGudgetDao.selectSectionLabourCost(params);//得到项目区间的人力资源成本
			int result=rollingGudgetDao.selectSectionLabourCostCountInItem(params);//判断 区间内预算项的总价
			if(list!=null && list.size()>0){
				Map<String, Object> item = list.get(0);
				params.put("labourCost", item.get("labourCost"));
				rollingGudgetDao.updateProSectionItemLabourCost(params);
			}
		}
		return afterDelete;
	}
	
	
	//新增差旅补贴
	public int insertTravelSubsidy(Map<String, Object> params) {
		RollingGudgetDao rollingGudgetDao = (RollingGudgetDao) getDao();
		int count= rollingGudgetDao.insertTravelSubsidy(params);
		if(count>0){
			
			List<Map<String, Object>> travlList  = rollingGudgetDao.selectProSectionEmployeeLabourCost(params);//得到项目区间的总额
			int result=rollingGudgetDao.selectProSectionLabourCostCountInItem(params);//判断 区间内预算项的总价
			Map<String, Object> item = travlList.get(0);
			params.put("traceSubsidy", item.get("traceSubsidy"));
			if(result>0){
				rollingGudgetDao.updatePlusProSectionItemLabourCost(params);
			}else{
				rollingGudgetDao.insertProSectionItemLabourCost(params);
			}
			
		}
		
		
		return count;
		
	}
	
	
	public int insertSectionLabourCost(Map<String, Object> params) {
		RollingGudgetDao rollingGudgetDao = (RollingGudgetDao) getDao();
		
		String attrJson=params.get("attrJson").toString();
		String versionCode=params.get("versionCode").toString();
		String budgetCode=params.get("budgetCode").toString();
		String companyCode=params.get("companyCode").toString();
		String proNo=params.get("proNo").toString();
		List<Map<String, Object>> paramslist=new ArrayList<Map<String,Object>>();
		JSONArray arry=JSONArray.parseArray(attrJson);
		for(int i=0;i<arry.size();i++){
			Map<String, Object> queryParams = new HashMap<String, Object>();
			
			Map m=(Map)arry.get(i);
			String employeeCode="";
			if(m.get("employeeCode")!=null){
				if(m.get("employeeCode").toString().equals("总计")){
					continue;
				}else{
					employeeCode=m.get("employeeCode").toString();
				}
				
			}
			queryParams.put("employeeCode", employeeCode);
			
			
			if(m.get("companyCode")!=null){
				companyCode=m.get("companyCode").toString();
			}
			queryParams.put("companyCode", companyCode);
			
			queryParams.put("proNo", proNo);
			
			String sectionCode="";
			if(m.get("sectionCode")!=null){
				sectionCode=m.get("sectionCode").toString();
			}
			queryParams.put("sectionCode", sectionCode);
			
			String univalent="";
			if(m.get("univalent")!=null){
				univalent=m.get("univalent").toString();
			}
			queryParams.put("univalent", univalent);
			
			String workTime="";
			if(m.get("workTime")!=null){
				workTime=m.get("workTime").toString();
			}
			queryParams.put("workTime", workTime);
			
			String labourCost="";
			if(m.get("labourCost")!=null){
				labourCost=m.get("labourCost").toString();
			}
			queryParams.put("labourCost", labourCost);
			
			String contractPropertyCode="";
			if(m.get("contractPropertyCode")!=null){
				contractPropertyCode=m.get("contractPropertyCode").toString();
			}
			queryParams.put("contractPropertyCode", contractPropertyCode);
			
			String jobCode="";
			if(m.get("jobCode")!=null){
				jobCode=m.get("jobCode").toString();
			}
			queryParams.put("jobCode", jobCode);
			
			String unitCode="";
			if(m.get("unitCode")!=null){
				unitCode=m.get("unitCode").toString();
			}
			queryParams.put("unitCode", unitCode);
			
			String salaryGrade="";
			if(m.get("salaryGrade")!=null){
				salaryGrade=m.get("salaryGrade").toString();
			}
			queryParams.put("salaryGrade", salaryGrade);
			
			String salaryLevel="";
			if(m.get("salaryLevel")!=null){
				salaryLevel=m.get("salaryLevel").toString();
			}
			queryParams.put("salaryLevel", salaryLevel);
			
			queryParams.put("versionCode", versionCode);
			queryParams.put("budgetCode", budgetCode);
			paramslist.add(queryParams);
		}
		
		rollingGudgetDao.deleteSectionLabourCost(params); //先清空  再插入
		
		int afterInsert= 0;
		if(paramslist.size()>0){
			afterInsert=rollingGudgetDao.insertSectionLabourCost(paramslist);
		}
		if(afterInsert>0){
			//得到区间
			List<Map<String, String>> sectionList = selectSectionList(params);
			for(int i=0;i<sectionList.size();i++){
				Map m=sectionList.get(i);
				
				String sectionCode=m.get("sectionCode").toString();
				params.put("sectionCode", sectionCode);
				
				List<Map<String, Object>> list  = rollingGudgetDao.selectSectionLabourCost(params);//得到项目区间的人力资源成本
				int result=rollingGudgetDao.selectSectionLabourCostCountInItem(params);//判断 区间内预算项的总价
				Map<String, Object> item = list.get(0);
				params.put("labourCost", item.get("labourCost"));
				if(result>0){
					rollingGudgetDao.updateProSectionItemLabourCost(params);
				}else{
					rollingGudgetDao.insertSectionItemLabourCost(params);
				}
			}
			
			
		}
		return afterInsert;
	}
	
	public String createBudgetTemplate(Map<String, Object> params) {
		
		String companyCode = params.get("companyCode").toString();
		RollingGudgetDao rollingGudgetDao = (RollingGudgetDao) getDao();
		//boolean createResult = false;
		try {
			
			//产生滚动预算以及预算区间
			List<Map<String, String>> proList = rollingGudgetDao.selectProByNo(params);
			if (proList != null && proList.size() > 0) {
				
				Map<String, String> proItem = proList.get(0);
				String sdate = proItem.get("sdate");
				
				if(sdate==null || sdate.equals("")){
					return "1";//没有项目开始时间
				}
				
				
				String edate = proItem.get("edate");
				
				if(edate==null || edate.equals("")){
					return "2"; //没有结束时间
				}
				params.put("proName", proItem.get("proName"));
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				String budgetCode = codeWorker.createCode("RB");
				params.put("budgetCode", budgetCode);
				
				int icount = rollingGudgetDao.insertProRollingBudget(params);
				if (icount > 0) {
					
					//创建预算初始版本
					Map<String, Object> budgetVersionItem = new HashMap<String, Object>();
					String versionCode = codeWorker.createCode("BV");
					budgetVersionItem.put("companyCode", params.get("companyCode"));
					budgetVersionItem.put("proNo", params.get("proNo"));
					budgetVersionItem.put("budgetCode", budgetCode);
					budgetVersionItem.put("versionCode", versionCode);
					budgetVersionItem.put("versionNo", "V1");
					budgetVersionItem.put("versionOrder", 1);
					budgetVersionItem.put("versionStatus", 0);
					icount = rollingGudgetDao.insertBudgetVersion(budgetVersionItem);
					if (icount > 0) {
						
						List<Map<String, Object>> sectionList = new ArrayList<Map<String,Object>>();
						Date startDate = null;
						if (params != null 
								&& params.get("budgetStartTime") != null 
								&& !"".equals(params.get("budgetStartTime"))) {
							
							startDate = DateTimeUtil.getDateFormat(params.get("budgetStartTime").toString(), DateTimeUtil.FORMAT_2);
							sdate = params.get("budgetStartTime").toString();
						}
						else {
							
							startDate = DateTimeUtil.getDateFormat(sdate, DateTimeUtil.FORMAT_2);
						}
						Date endDate = DateTimeUtil.getDateFormat(edate, DateTimeUtil.FORMAT_2);
						int startDay = DateTimeUtil.getDay(startDate);
						int endDay = DateTimeUtil.getDay(endDate);
						int monthDifferCount = DateTimeUtil.getMonthDiffer(startDate, endDate);
						String startMonth = DateTimeUtil.formatDate(startDate, DateTimeUtil.FORMAT_3);
						String startMonthLastDay = DateTimeUtil.lastDayOfDate(startMonth);
						String proNo = proItem.get("proNo").toString();
						String proName = proItem.get("proName").toString();
						if (monthDifferCount > 0) {
							
							int order = 0;
							if (startDay < HALF_MONTH_DAY) {
								
								sectionList.add(createSection(companyCode, budgetCode, versionCode, 
										SECTION_TYPE_HALFMONTH, SECTION_WRITED, proNo, proName, sdate, startMonth + "-15", 1));
								sectionList.add(createSection(companyCode, budgetCode, versionCode, 
										SECTION_TYPE_HALFMONTH, SECTION_WRITED, proNo, proName, startMonth + "-16", startMonthLastDay, 2));
								order = 2;
							}
							else {
								
								sectionList.add(createSection(companyCode, budgetCode, versionCode, 
										SECTION_TYPE_HALFMONTH, SECTION_WRITED, proNo, proName, sdate, startMonthLastDay, 1));
								order = 1;
							}
							
							for (int i = 1;i <= monthDifferCount;) {
								
								Date _date = DateTimeUtil.getMonth(startDate, i);
								String _monthStr = DateTimeUtil.formatDate(_date, DateTimeUtil.FORMAT_3);
								String _lastDay = DateTimeUtil.lastDayOfDate(_monthStr);
								if (i < 2) {
									
									int _day = DateTimeUtil.daysOfTwo(DateTimeUtil.
											getDateFromDateString(_monthStr + "-01", DateTimeUtil.FORMAT_2), endDate);
									
									if (_day <= 0) {
										
										sectionList.add(createSection(companyCode, budgetCode, versionCode, 
												SECTION_TYPE_HALFMONTH, SECTION_WRITED, proNo, proName, _monthStr + "-01", edate, order + i));
										
										break;
									}
									else {
										
										_day = DateTimeUtil.daysOfTwo(DateTimeUtil.
												getDateFromDateString(_lastDay, DateTimeUtil.FORMAT_2), endDate);
										sectionList.add(createSection(companyCode, budgetCode, versionCode, 
												SECTION_TYPE_HALFMONTH, SECTION_WRITED, proNo, proName, _monthStr + "-01", 
												_monthStr + "-15", order + i));
										if (_day <= 0) {
											
											sectionList.add(createSection(companyCode, budgetCode, versionCode, 
													SECTION_TYPE_HALFMONTH, SECTION_WRITED, proNo, proName, _monthStr + "-16", 
													edate, order + i + 1));
											break;
										}
										else {
											
											sectionList.add(createSection(companyCode, budgetCode, versionCode, 
													SECTION_TYPE_HALFMONTH, SECTION_WRITED, proNo, proName, _monthStr + "-16", 
													_lastDay, order + i + 1));
											
											order = order + 2;
										}
										
									}
									
									i++;
								}
								else {
									
									if (DateTimeUtil.checkIsQuarterStartMonth(_monthStr)) {
										
										Date quarterStartDate = DateTimeUtil.getQuarterStartTime(_monthStr);
										Date querterEndDate = DateTimeUtil.getQuarterEndTime(_monthStr);
										int _day = DateTimeUtil.daysOfTwo(querterEndDate, endDate);
										if (_day <= 0) {
											
											sectionList.add(createSection(companyCode, budgetCode, versionCode, 
													SECTION_TYPE_QUARTER, SECTION_WRITED, proNo, proName, 
													DateTimeUtil.formatDate(quarterStartDate, DateTimeUtil.FORMAT_2), 
													edate, order + 1));
											
											break;
										}
										else {
											
											sectionList.add(createSection(companyCode, budgetCode, versionCode, 
													SECTION_TYPE_QUARTER, SECTION_WRITED, proNo, proName, 
													DateTimeUtil.formatDate(quarterStartDate, DateTimeUtil.FORMAT_2), 
													DateTimeUtil.formatDate(querterEndDate, DateTimeUtil.FORMAT_2), order + 1));
											
											order = order + 1;
										}
										
										i += 3;
									}
									else {
										
										int _day = DateTimeUtil.daysOfTwo(DateTimeUtil.
												getDateFromDateString(_lastDay, DateTimeUtil.FORMAT_2), endDate);
										if (_day <= 0) {
											
											sectionList.add(createSection(companyCode, budgetCode, versionCode, SECTION_TYPE_MONTH,
													SECTION_WRITED, proNo, proName, _monthStr + "-01", edate, order + 1));
											
											order = order + 1;
											break;
										}
										else {
											
											sectionList.add(createSection(companyCode, budgetCode, versionCode, SECTION_TYPE_MONTH,
													SECTION_WRITED, proNo, proName, _monthStr + "-01", _lastDay, order + 1));
											
											order = order + 1;
										}
										
										i++;
									}
								}
								
							}
						}
						else {
							
							if (endDay <= HALF_MONTH_DAY) {
								
								sectionList.add(createSection(companyCode, budgetCode, versionCode, SECTION_TYPE_HALFMONTH, 
										SECTION_WRITED, proNo, proName, sdate, edate, 1));

							}
							else {
								
								if (startDay < HALF_MONTH_DAY) {
									
									sectionList.add(createSection(companyCode, budgetCode, versionCode, SECTION_TYPE_HALFMONTH, 
											SECTION_WRITED, proNo, proName, sdate, startMonth + "-15", 1));
									sectionList.add(createSection(companyCode, budgetCode, versionCode, SECTION_TYPE_HALFMONTH, 
											SECTION_WRITED, proNo, proName, startMonth + "-16", edate, 2));
								}
								else {
									
									sectionList.add(createSection(companyCode, budgetCode, versionCode, SECTION_TYPE_HALFMONTH, 
											SECTION_WRITED, proNo, proName, sdate, edate, 1));
								}
							}
						}
						
						if (sectionList.size() > 0) {
							
							rollingGudgetDao.insertProBudgetSections(sectionList);
							return "3";
						}else{
							return "4";
						}
					}
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "3";
	}
	
	public List<Map<String, Object>> selectProBudgetVersion(Map<String, Object> params) {
		
		RollingGudgetDao rollingGudgetDao = (RollingGudgetDao) getDao();
		
		return rollingGudgetDao.selectProBudgetVersion(params);
	}
	
	public Map<String, Object> getProBudgetVersion(Map<String, Object> params) {

		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		RollingGudgetDao rollingGudgetDao=(RollingGudgetDao)getDao();
		
		
		Map<String, Object> budgetVersion= rollingGudgetDao.selectProBudgetVersionByCode(params);
		
		if (budgetVersion!=null && budgetVersion.size()>0) {

			resultMap.put("versionCode", budgetVersion.get("versionCode").toString());
			resultMap.put("budgetCode", budgetVersion.get("budgetCode").toString());
			resultMap.put("versionOrder", budgetVersion.get("versionOrder").toString());
			resultMap.put("budgetNo", budgetVersion.get("budgetNo").toString());
			params.put("budgetCode", budgetVersion.get("budgetCode").toString());
			params.put("versionCode", budgetVersion.get("versionCode").toString());
		}
		
		//得到区间
		List<Map<String, String>> sectionList = selectSectionList(params);
		
		if (sectionList != null && sectionList.size() > 0) {

			StringBuilder sqlSeqBuilder = new StringBuilder();
			for (Map<String, String> sectionItem : sectionList) {

				sqlSeqBuilder.append("SUM(CASE WHEN b.SECTION_CODE='");
				sqlSeqBuilder.append(sectionItem.get("sectionCode"));
				sqlSeqBuilder.append("' THEN b.COST ELSE 0 END) ");
				sqlSeqBuilder.append(sectionItem.get("sectionCode"));
				sqlSeqBuilder.append(",");
			}
			params.put("sqlSeq", sqlSeqBuilder.toString());
			//	params.put("itemCode", OTHER_COST_ITEM_CODE);

			List<Map<String, Object>> proCostList = rollingGudgetDao.selectProItemCostDetail(params);
			
			
			resultMap.put("proCostList", proCostList);
			resultMap.put("sectionList", sectionList);
			
			
			
			
		}

		return resultMap;
	}
	
	/**
	  * 创建新的预算版本
	  * 
	  * @param @param params
	  * @param @return
	  * @param @throws ParseException
	  * @return 一个Map类型记录
	  * @throws
	  * @author:
	  * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	  */
	  
	private boolean createNewBudgetVersion(Map<String, Object> params) throws ParseException {
		
		boolean result = false;
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean("simpleCodeWorker");
		RollingGudgetDao rollingGudgetDao = (RollingGudgetDao) getDao();
		String companyCode = params.get("companyCode").toString();
		String proNo = params.get("proNo").toString();
		String budgetCode = params.get("budgetCode").toString();
		String curVersionCode = params.get("versionCode").toString();
		params.put("sectionType", SECTION_TYPE_HALFMONTH);
		//获取可写的半个月类型的区间
		List<Map<String, Object>> writableHalfMonthSectionList = rollingGudgetDao.selectWritableSection(params);
		//获取可写的非半个月类型区间
		params.put("sectionType", 2);
		List<Map<String, Object>> writableUnHalfMonthSectionList = rollingGudgetDao.selectWritableSection(params);
		if (writableHalfMonthSectionList != null) {
			
			Map<String, Object> writableHalfMonthSectionItem = writableHalfMonthSectionList.get(0);
			writableHalfMonthSectionItem.put("companyCode", companyCode);
			writableHalfMonthSectionItem.put("proNo", proNo);
			writableHalfMonthSectionItem.put("budgetCode", budgetCode);
			writableHalfMonthSectionItem.put("curVersionCode", curVersionCode);
			writableHalfMonthSectionItem.put("readonly", 1);
			//更新当前版本的第一个可写的半月区间为只读
			rollingGudgetDao.updateSectionReadonly(writableHalfMonthSectionItem);
			
			//当前版本所有区间
			List<Map<String, Object>> sectionList = rollingGudgetDao.selectVersionSection(params);
			Map<String, Object> curVersionItem = rollingGudgetDao.selectVersionByCode(params);
			
			//有需要拆分的区间
			if (writableUnHalfMonthSectionList != null && writableUnHalfMonthSectionList.size() > 0) {
				
				//新版本的区间
				String versionCode = codeWorker.createCode("BV");
				int versionStatus = 0;
				int versionOrder = Integer.parseInt(curVersionItem.get("versionOrder").toString()) + 1;
				String versionNo = "V" + versionOrder;
				Map<String, Object> newVersionItem = new HashMap<String, Object>();
				newVersionItem.put("companyCode", companyCode);
				newVersionItem.put("proNo", proNo);
				newVersionItem.put("budgetCode", budgetCode);
				newVersionItem.put("versionCode", versionCode);
				newVersionItem.put("versionNo", versionNo);
				newVersionItem.put("versionOrder", versionOrder);
				newVersionItem.put("versionStatus", versionStatus);
				int icount = rollingGudgetDao.insertBudgetVersion(newVersionItem);
				if (icount > 0) {
					
					List<Map<String, Object>> newSectionList = new ArrayList<Map<String,Object>>();
					List<Map<String, Object>> newLabourCostList = new ArrayList<Map<String,Object>>();
					List<Map<String, Object>> newItemCostList = new ArrayList<Map<String,Object>>();
					List<Map<String, Object>> labourCostList = rollingGudgetDao.selectVersionLabourCost(params);
					List<Map<String, Object>> itemCostList = rollingGudgetDao.selectVersionItemCost(params);
					if (writableHalfMonthSectionList.size() > 3) {
						
						for (Map<String, Object> sectionItem : sectionList) {
							
							String sectionCode = codeWorker.createCode("S");
							setLabourCostToNewSection(sectionItem, labourCostList, versionCode, sectionCode, newLabourCostList);
							setItemCostToNewSection(sectionItem, itemCostList, versionCode, sectionCode, newItemCostList);
							sectionItem.put("versionCode", versionCode);
							sectionItem.put("sectionCode", sectionCode);
							newSectionList.add(sectionItem);
						}
					}
					else {
						
						Map<String, Object> writableUnHalfMonthSectionItem = writableUnHalfMonthSectionList.get(0);
						int sectionOrder = Integer.parseInt(writableUnHalfMonthSectionItem.get("sectionOrder").toString());
						List<Map<String, Object>> spliteSectionList = spliteSection(versionCode, writableUnHalfMonthSectionItem);
						for (int i = 0;i < sectionList.size();i++) {
							
							Map<String, Object> sectionItem = sectionList.get(i);
							int _order = Integer.parseInt(sectionItem.get("sectionOrder").toString());
							if (sectionOrder > _order) {
								
								String sectionCode = codeWorker.createCode("S");
								setLabourCostToNewSection(sectionItem, labourCostList, versionCode, sectionCode, newLabourCostList);
								setItemCostToNewSection(sectionItem, itemCostList, versionCode, sectionCode, newItemCostList);
								sectionItem.put("versionCode", versionCode);
								sectionItem.put("sectionCode", sectionCode);
								newSectionList.add(sectionItem);
							}
							else if (sectionOrder < _order) {
								
								String sectionCode = codeWorker.createCode("S");
								setLabourCostToNewSection(sectionItem, labourCostList, versionCode, sectionCode, newLabourCostList);
								setItemCostToNewSection(sectionItem, itemCostList, versionCode, sectionCode, newItemCostList);
								sectionItem.put("versionCode", versionCode);
								sectionItem.put("sectionCode", sectionCode);
								sectionItem.put("sectionOrder", _order + spliteSectionList.size() - 1);
								newSectionList.add(sectionItem);
							}
							else {
								
								newSectionList.addAll(i, spliteSectionList);
							}
						}
					}
					
					//保存新区间、区间下面的人力资源成本、各项成本
					if (newSectionList != null && newSectionList.size() > 0) {
						
						rollingGudgetDao.insertProBudgetSections(newSectionList);
					}
					
					if (newLabourCostList != null && newLabourCostList.size() > 0) {
						
						rollingGudgetDao.batchInsertSectionLabourCost(newLabourCostList);
					}
					
					if (newItemCostList != null && newItemCostList.size() > 0) {
						
						rollingGudgetDao.insertProSectionCost(newItemCostList);
					}
					
					result = true;
				}
			}
			else if (writableHalfMonthSectionList.size() > 1) {
				
				String versionCode = codeWorker.createCode("BV");
				int versionStatus = 0;
				int versionOrder = Integer.parseInt(curVersionItem.get("versionOrder").toString()) + 1;
				String versionNo = "V" + versionOrder;
				Map<String, Object> newVersionItem = new HashMap<String, Object>();
				newVersionItem.put("companyCode", companyCode);
				newVersionItem.put("proNo", proNo);
				newVersionItem.put("budgetCode", budgetCode);
				newVersionItem.put("versionCode", versionCode);
				newVersionItem.put("versionNo", versionNo);
				newVersionItem.put("versionOrder", versionOrder);
				newVersionItem.put("versionStatus", versionStatus);
				int icount = rollingGudgetDao.insertBudgetVersion(newVersionItem);
				if (icount > 0) {
					
					List<Map<String, Object>> newSectionList = new ArrayList<Map<String,Object>>();
					List<Map<String, Object>> newLabourCostList = new ArrayList<Map<String,Object>>();
					List<Map<String, Object>> newItemCostList = new ArrayList<Map<String,Object>>();
					List<Map<String, Object>> labourCostList = rollingGudgetDao.selectVersionLabourCost(params);
					List<Map<String, Object>> itemCostList = rollingGudgetDao.selectVersionItemCost(params);
					for (Map<String, Object> sectionItem : sectionList) {
						
						String sectionCode = codeWorker.createCode("S");
						setLabourCostToNewSection(sectionItem, labourCostList, versionCode, sectionCode, newLabourCostList);
						setItemCostToNewSection(sectionItem, itemCostList, versionCode, sectionCode, newItemCostList);
						sectionItem.put("versionCode", versionCode);
						sectionItem.put("sectionCode", sectionCode);
						newSectionList.add(sectionItem);
					}
					
					//保存新区间、区间下面的人力资源成本、各项成本
					if (newSectionList != null && newSectionList.size() > 0) {
						
						rollingGudgetDao.insertProBudgetSections(newSectionList);
					}
					
					if (newLabourCostList != null && newLabourCostList.size() > 0) {
						
						rollingGudgetDao.batchInsertSectionLabourCost(newLabourCostList);
					}
					
					if (newItemCostList != null && newItemCostList.size() > 0) {
						
						rollingGudgetDao.insertProSectionCost(newItemCostList);
					}
					
					result = true;
				}
			}
			
		}
		
		return result;
	}
	
	private List<Map<String, Object>> spliteSection(String versionCode,
			Map<String, Object> sectionItem) throws ParseException {
		
		List<Map<String, Object>> spliteSectionList = new ArrayList<Map<String,Object>>();
		String startDate = sectionItem.get("sectionStartTime").toString();
		String endDate = sectionItem.get("sectionEndTime").toString();
		String companyCode = sectionItem.get("companyCode").toString();
		String proNo = sectionItem.get("proNo").toString();
		String proName = sectionItem.get("proName").toString();
		String budgetCode = sectionItem.get("budgetCode").toString();
		int sectionOrder = Integer.parseInt(sectionItem.get("sectionOrder").toString());
		int sectionType = Integer.parseInt(sectionItem.get("sectionType").toString());
		Date _date = DateTimeUtil.getMonth( DateTimeUtil.getDateFormat(startDate, DateTimeUtil.FORMAT_2), 0);
		String _monthStr = DateTimeUtil.formatDate(_date, DateTimeUtil.FORMAT_3);
		if (sectionType == SECTION_TYPE_MONTH) {
			
			spliteSectionList.add(createSection(companyCode, budgetCode, versionCode, SECTION_TYPE_HALFMONTH, 
					SECTION_WRITED, proNo, proName, startDate, _monthStr + "-15", sectionOrder));
			spliteSectionList.add(createSection(companyCode, budgetCode, versionCode, SECTION_TYPE_HALFMONTH, 
					SECTION_WRITED, proNo, proName, _monthStr + "-16", endDate, sectionOrder + 1));
		}
		else if (sectionType == SECTION_TYPE_QUARTER) {
			
			//季度的一个月
			String startMonthLastDay = DateTimeUtil.lastDayOfDate(_monthStr);
			spliteSectionList.add(createSection(companyCode, budgetCode, versionCode, SECTION_TYPE_HALFMONTH, 
					SECTION_WRITED, proNo, proName, startDate, _monthStr + "-15", sectionOrder));
			spliteSectionList.add(createSection(companyCode, budgetCode, versionCode, SECTION_TYPE_HALFMONTH, 
					SECTION_WRITED, proNo, proName, _monthStr + "-16", startMonthLastDay, sectionOrder + 1));
			
			//季度的中间月
			Date middleMonth = DateTimeUtil.getMonth( DateTimeUtil.getDateFormat(startDate, DateTimeUtil.FORMAT_2), 1);
			String middleMonthStr = DateTimeUtil.formatDate(middleMonth, DateTimeUtil.FORMAT_3);
			String middleMonthLastDay = DateTimeUtil.lastDayOfDate(middleMonthStr);
			spliteSectionList.add(createSection(companyCode, budgetCode, versionCode, SECTION_TYPE_MONTH, 
					SECTION_WRITED, proNo, proName, middleMonthStr + "-01", middleMonthLastDay, sectionOrder + 2));
			
			//季度的最后一个月
			Date lastMonth = DateTimeUtil.getMonth( DateTimeUtil.getDateFormat(startDate, DateTimeUtil.FORMAT_2), 2);
			String lastMonthStr = DateTimeUtil.formatDate(lastMonth, DateTimeUtil.FORMAT_3);
			spliteSectionList.add(createSection(companyCode, budgetCode, versionCode, SECTION_TYPE_MONTH, 
					SECTION_WRITED, proNo, proName, lastMonthStr + "-01", endDate, sectionOrder + 3));
		}
		
		return spliteSectionList;
	}
	
	private Map<String, Object> createSection(String companyCode, String budgetCode, 
			String versionCode, int sectionType, int readonly, String proNo, String proName, 
			String sdate, String edate, int order) {
		
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean("simpleCodeWorker");
		Map<String, Object> sectionItem = new HashMap<String, Object>();
		sectionItem.put("companyCode", companyCode);
		sectionItem.put("proNo", proNo);
		sectionItem.put("proName", proName);
		sectionItem.put("budgetCode", budgetCode);
		sectionItem.put("versionCode", versionCode);
		sectionItem.put("sectionCode", codeWorker.createCode("S"));
		sectionItem.put("sectionStartTime", sdate);
		sectionItem.put("sectionEndTime", edate);
		sectionItem.put("sectionOrder", order);
		sectionItem.put("sectionType", sectionType);
		sectionItem.put("readonly", readonly);
		
		return sectionItem;
	}
	
	private void setLabourCostToNewSection(Map<String, Object> sectionItem, List<Map<String, Object>> labourCostList, 
			String versionCode, String newSectionCode, List<Map<String, Object>> newLabourCostList) {
		
		if (labourCostList != null && labourCostList.size() > 0) {
			
			for (Map<String, Object> labourCostItem : labourCostList) {
				
				if (sectionItem.get("versionCode").equals(labourCostItem.get("versionCode")) 
						&& sectionItem.get("sectionCode").equals(labourCostItem.get("sectionCode"))) {
					
					labourCostItem.put("versionCode", versionCode);
					labourCostItem.put("sectionCode", newSectionCode);
					newLabourCostList.add(labourCostItem);
				}
			}
		}
	}
	
	private void setItemCostToNewSection(Map<String, Object> sectionItem, List<Map<String, Object>> itemCostList, 
			String versionCode, String newSectionCode, List<Map<String, Object>> newItemCostList) {
		
		if (itemCostList != null && itemCostList.size() > 0) {
			
			for (Map<String, Object> itemCost : itemCostList) {
				
				if (sectionItem.get("versionCode").equals(itemCost.get("versionCode")) 
						&& sectionItem.get("sectionCode").equals(itemCost.get("sectionCode"))) {
					
					itemCost.put("versionCode", versionCode);
					itemCost.put("sectionCode", newSectionCode);
					newItemCostList.add(itemCost);
				}
			}
		}
	}
	
}
