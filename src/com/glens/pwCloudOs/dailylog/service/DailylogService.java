package com.glens.pwCloudOs.dailylog.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.dailylog.dao.DailylogDao;

public class DailylogService extends EAPAbstractService {
	
	public List getLogList(Object parameters) {
		
		DailylogDao dao=(DailylogDao)getDao();
		return dao.getLogList(parameters);
	}
	//得到所有的项目负责人列表
	public Map getAllList(Map<String, String> parameters) {
		
		DailylogDao dao=(DailylogDao)getDao();
		List resultlist=new ArrayList();
		List dateList=new ArrayList();
		List list= dao.getAllList(parameters);
		List list2= dao.getAllLogList(parameters);
		String fromDate = parameters.get("fromDate");
		String toDate = parameters.get("toDate");
		List<Map<String, String>> dates=null;
		Map returnMap=new HashMap();
		
		try {
			dates = DateTimeUtil.getDates(
					DateTimeUtil.getDateFormat(fromDate, DateTimeUtil.FORMAT_2),
					DateTimeUtil.getDateFormat(toDate, DateTimeUtil.FORMAT_2));
			int ind=0;
			for (Map<String, String> dateItem : dates) {
				ind++;
				String date = dateItem.get("simpleDate");
				String tempStr="date"+ind;
				Map m=new HashMap();
				m.put("date", date);
				m.put("cond", tempStr);
				dateList.add(m);
				returnMap.put("dateList", dateList);
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		if(list!=null){
			for(int i=0;i<list.size();i++){
				
				Map returnmap=new HashMap();
				
				
				Map m=(Map)list.get(i);
				String proNo=m.get("proNo").toString();
				String proName=m.get("proName").toString();
				String proManager=m.get("proManager").toString();
				returnmap.put("proNo", proNo);
				returnmap.put("proManager", proManager);
				returnmap.put("proName", proName);
				
				if (dates != null && dates.size() > 0) {
					int start=0;
					int writeNum=0;//填报数量
					int totalNum=dates.size();
					for (Map<String, String> dateItem : dates) {
						String date = dateItem.get("simpleDate");
						
						
						
						start++;
						String tempStr="date"+start;
						//returnmap.put("date", date);
						
						if(list2!=null){
							int result=0;
							for(int j=0;j<list2.size();j++){
								Map temp=(Map)list2.get(j);
								String proNo1="";
								if(temp.get("proNo")!=null){
									proNo1=temp.get("proNo").toString();
								}
								String logDate="";
								if(temp.get("logDate")!=null){
									logDate=temp.get("logDate").toString();
								}
								if(proNo1.equals(proNo) && logDate.equals(date)){
									result=1;
									break;
								}
							}
							if(result==0){
								returnmap.put(tempStr, "0");
							}else if(result==1){
								returnmap.put(tempStr, "1");
								writeNum++;
							}
							
						}
					}///日期循环结束
					returnmap.put("num", writeNum);
					returnmap.put("totalNum", totalNum);
					
					resultlist.add(returnmap);
				}
				
			}//list循环结束
		}
		returnMap.put("resultlist", resultlist);
		return returnMap;
	}
	
	//查询当天的记录
	public List getCurrentList(Map<String, String> parameters) {
		
		DailylogDao dao=(DailylogDao)getDao();
		String fromDate = parameters.get("fromDate");
		parameters.put("date", fromDate);
		List list=dao.getLogList1(parameters);
		return list;
	}
	
	
	
	
	public List getWriteList(Map<String, String> parameters) {
		DailylogDao dao=(DailylogDao)getDao();
		List resultlist=new ArrayList();
		String fromDate = parameters.get("fromDate");
		String toDate = parameters.get("toDate");
		
		List<Map<String, String>> dates;
		try {
			dates = DateTimeUtil.getDates(
					DateTimeUtil.getDateFormat(fromDate, DateTimeUtil.FORMAT_2),
					DateTimeUtil.getDateFormat(toDate, DateTimeUtil.FORMAT_2));
			if (dates != null && dates.size() > 0) {
				for (Map<String, String> dateItem : dates) {
					String date = dateItem.get("simpleDate");
					parameters.put("date", date);
					List list=dao.getLogList1(parameters);
					if(list!=null && list.size()>0){
						/*for(int i=0;i<list.size();i++){
								Map m=(Map)list.get(i);
								m.put("date", date);
								resultlist.add(m);
						}*/
						
					   String days[]=date.split("-");
					   
					   
					   System.out.println(days[0]+":"+days[1]+":"+days[2]);
					   
						
						Map m=new HashMap();
						m.put("date", date);
						m.put("isFill", "1");//已经填报
						if(days.length>0){
							m.put("year", days[0]);
							m.put("month", days[1]);
							m.put("day", days[2]);
						}
						
						resultlist.add(m);
					}else{
						Map m=new HashMap();
						m.put("date", date);
						m.put("isFill", "0");//没有填报
						String days[]=date.split("-");
						if(days.length>0){
							m.put("year", days[0]);
							m.put("month", days[1]);
							m.put("day", days[2]);
						}
						resultlist.add(m);
					}
					
					
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultlist;
	}
	
	
	public List getwebLogList(Map<String, String> parameters) {
		DailylogDao dao=(DailylogDao)getDao();
		List resultlist=new ArrayList();
		String fromDate = parameters.get("fromDate");
		String toDate = parameters.get("toDate");
		
		List<Map<String, String>> dates;
		try {
			dates = DateTimeUtil.getDates(
					DateTimeUtil.getDateFormat(fromDate, DateTimeUtil.FORMAT_2),
					DateTimeUtil.getDateFormat(toDate, DateTimeUtil.FORMAT_2));
			if (dates != null && dates.size() > 0) {
				for (Map<String, String> dateItem : dates) {
					String date = dateItem.get("simpleDate");
					parameters.put("date", date);
					List list=dao.getLogList1(parameters);
					if(list!=null && list.size()>0){
						for(int i=0;i<list.size();i++){
								Map m=(Map)list.get(i);
								m.put("date", date);
								resultlist.add(m);
						}
					}else{
						Map m=new HashMap();
						m.put("date", date);
						resultlist.add(m);
					}
					
					
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultlist;
	}
	
	
	
	
	public boolean insertLog(Object parameters) {
		// TODO Auto-generated method stub
		DailylogDao dao=(DailylogDao)getDao();
		return dao.insertLog(parameters);
	}

}
