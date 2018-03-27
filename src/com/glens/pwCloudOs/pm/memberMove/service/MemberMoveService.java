package com.glens.pwCloudOs.pm.memberMove.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.materielMg.assetMg.asset.dao.AssetDao;
import com.glens.pwCloudOs.pm.memberMove.dao.MemberMoveDao;
import com.glens.pwCloudOs.pm.memberMove.vo.PmMemberMoveVo;


public class MemberMoveService extends EAPAbstractService {
	
	
	
	
	
	public boolean insertProc(Object parameters) {
		// TODO Auto-generated method stub
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		return memberMoveDao.insertProc(parameters);
	}
	
	public List<Map<String, Object>> getJobs(){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		return memberMoveDao.getJobs();
	}
	
	public List<Map<String, Object>> memberMoveIn(Map<String, String> params){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		return memberMoveDao.memberMoveIn(params);
	}
	
	public List<Map<String, Object>> memberMoveOut(Map<String, String> params){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		return memberMoveDao.memberMoveOut(params);
	}

	public List<Map<String, Object>> memberMoveChar(Map<String, String> params){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		return memberMoveDao.memberMoveChar(params);
	}
	
	public List<Map<String, Object>> memberMoves(Map<String, String> params){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		return memberMoveDao.memberMoves(params);
	}
	
	public List<Map<String, Object>> getMovelist(){
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		List<Map<String, Object>> list=memberMoveDao.getMovelist();
		for(int i=0;i<list.size();i++){
			Map m=list.get(i);
			//{employeecode=414, valid_date=2016-07-13, prono2=NTRD-DLBS-0001-1603, prono1=NTRD-DLBS-0001-1603, companycode=001}
			String companyCode=m.get("companycode").toString();
			String proNo1=m.get("prono1").toString();
			String proNo2=m.get("prono2").toString();
			String employeeCode=m.get("employeecode").toString();
			Long rowid=Long.parseLong(m.get("rowid").toString());
			
			String job1=m.get("job1").toString();
			String job2=m.get("job2").toString();
			String moveType=m.get("moveType").toString();
			
			
			PmMemberMoveVo v=new PmMemberMoveVo();
			v.setCompanyCode(companyCode);
			v.setProNo1(proNo1);
			v.setProNo2(proNo2);
			v.setEmployeeCode(employeeCode);
			v.setRowid(rowid);
			v.setJobCode1(job1);
			v.setJobCode2(job2);
			v.setMoveType(moveType);
			
			
			if(moveType.equals("3")){//项目调部门 remarks存的是部门编码
				String remarks=m.get("remarks").toString();
				v.setRemarks(remarks);
			}
			
			memberMoveDao.updateMoves(v);
			
			
		}
		return memberMoveDao.getMovelist();
		
	}
	
	//人员调动处理  最终确认
	public boolean updateMessage(Object params){
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		List<Map<String, Object>> list=memberMoveDao.getMovelist1(params);
		if(list!=null && list.size()>0){
			Map m=list.get(0);
			//{employeecode=414, valid_date=2016-07-13, prono2=NTRD-DLBS-0001-1603, prono1=NTRD-DLBS-0001-1603, companycode=001}
			String companyCode=m.get("companycode").toString();
			String proNo1=m.get("prono1").toString();
			String proNo2=m.get("prono2").toString();
			String employeeCode=m.get("employeecode").toString();
			Long rowid=Long.parseLong(m.get("rowid").toString());
			
			String job1=m.get("job1").toString();
			String job2=m.get("job2").toString();
			String moveType=m.get("moveType").toString();
			
			SimpleDateFormat ds=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			
			String validDate=ds.format(new Date());
			if(m.get("validDate")!=null){
				validDate=m.get("validDate").toString();
			}
					
			
			PmMemberMoveVo v=new PmMemberMoveVo();
			v.setCompanyCode(companyCode);
			v.setProNo1(proNo1);
			v.setProNo2(proNo2);
			v.setEmployeeCode(employeeCode);
			v.setRowid(rowid);
			v.setJobCode1(job1);
			v.setJobCode2(job2);
			v.setMoveType(moveType);
			v.setValidDate(validDate);
			
			
			if(moveType.equals("3")){//项目调部门 remarks存的是部门编码
				String remarks=m.get("remarks").toString();
				v.setRemarks(remarks);
			}
			
			memberMoveDao.updateMoves(v);
			
			return true;
		}else{
			return false;
		}
		
		
	}
	
	
	public List getmanagerinpro(Object params){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		return memberMoveDao.getmanagerinpro(params);
	}
	
	public List getmanageroutpro(Object params){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		return memberMoveDao.getmanageroutpro(params);
	}
	
	public int updateProc(Object parameters) {
		// TODO Auto-generated method stub
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		return memberMoveDao.updateProc(parameters);
	}
	public int updateProcStatus(Object parameters) {
		// TODO Auto-generated method stub
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		return memberMoveDao.updateProcStatus(parameters);
	}
	
	public List getListByCode(Object params){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		return memberMoveDao.getListByCode(params);
	}
	public List getDetailProc(Object params){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		return memberMoveDao.getDetailProc(params);
	}
	public List employeeAges(Object params){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		
		List list=memberMoveDao.employeeAges(params);
		
		
		List li=new ArrayList();
		for(int i=1;i<=8;i++){
			Map m=new HashMap();
			m.put("status", i);
			m.put("number", 0);
			m.put("rate", 0);
			li.add(m);
		}
		
		int totalnum=0;
		double totalrate=0;
		for(int i=0;i<list.size();i++){
			Map m=(Map)list.get(i);
			String status1="";
			if(m.get("status1")!=null){
				status1=m.get("status1").toString();
			}
			String total="";
			if(m.get("total")!=null){
				total=m.get("total").toString();
			}
			
			totalnum=totalnum+Integer.parseInt(total);
			
			String rate="";
			if(m.get("rate")!=null){
				rate=m.get("rate").toString();
			}
			totalrate=totalrate+Double.parseDouble(rate);
			for(int j=0;j<li.size();j++){
				Map mp=(Map)li.get(j);
				String status=mp.get("status").toString();
				
				if(status.equals(status1)){
					mp.put("status", status1);
					mp.put("rate", rate);
					mp.put("number", total);
				}
			}
			
		}
		Map m=(Map)li.get(0);
		m.put("totalnum", totalnum);
		m.put("totalrate", totalrate);
		
		
		return li;
	}
	
	
	public List degreeStatics(Object params){
			
			MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
			
			List list=memberMoveDao.degreeStatics(params);
			int totalnum=0;
			for(int i=0;i<list.size();i++){
				Map m=(Map)list.get(i);
				totalnum=totalnum+Integer.parseInt(m.get("icount").toString());
				
				String degree="";
				if(m.get("degree")!=null){
					degree=m.get("degree").toString();
				}
				
				if(degree.equals("")){
					m.put("degree", "其他");
				}
						
				
			}
			Map m=(Map)list.get(0);
			m.put("totalnum", totalnum);
			return list;
	}
	
	public List jobsStatics(Object params){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		
		List list=memberMoveDao.jobsStatics(params);
		
		List resultList=new ArrayList();
		
		int totalnum=0;
		int other=0;
		double rates=0;
		for(int i=0;i<list.size();i++){
			Map m=(Map)list.get(i);
			totalnum=totalnum+Integer.parseInt(m.get("icount").toString());
			
			String JOB_NAME="";
			if(m.get("JOB_NAME")!=null && !m.get("JOB_NAME").toString().equals("")){
				JOB_NAME=m.get("JOB_NAME").toString();
				
				resultList.add(m);
				//System.out.println(m);
			}else{
				//System.out.println(m);
			}
			
			if(JOB_NAME.equals("")){
				//m.put("JOB_NAME", "其他");
				
				int count=Integer.parseInt(m.get("icount").toString());
				double rate=Double.parseDouble(m.get("zb").toString());
				rates=rates+rate;
				other=other+count;
				//list.remove(i);
			}
					
			
		}
		Map temp=new HashMap();
		temp.put("zb", rates);
		temp.put("icount", other);
		temp.put("JOB_NAME", "其他");
		
		
		resultList.add(temp);
		
		
		Map m=(Map)resultList.get(0);
		m.put("totalnum", totalnum);
		
		
		return resultList;
	}
	
	public List majorDegreeStatics(Object params){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		
		
		List list=memberMoveDao.majorDegreeStatics(params);
		int totalnum=0;
		for(int i=0;i<list.size();i++){
			Map m=(Map)list.get(i);
			totalnum=totalnum+Integer.parseInt(m.get("icount").toString());
			
			String MAJOR_DEGREE="";
			if(m.get("MAJOR_DEGREE")!=null){
				MAJOR_DEGREE=m.get("MAJOR_DEGREE").toString();
			}
			
			if(MAJOR_DEGREE.equals("")){
				m.put("MAJOR_DEGREE", "其他");
			}
					
			
		}
		Map m=(Map)list.get(0);
		m.put("totalnum", totalnum);
		return list;
	}
	
	//判断是否是项目管理人
	public List isManager(Object params){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		List list=memberMoveDao.isManager(params);
		return list;
	}
	
	//根据项目编码  获取acctount
	public List getAcccount(Object params){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		List list=memberMoveDao.getAcccount(params);
		return list;
	}
	
	
	//非项目负责人
	public List noManager(Object params) throws ParseException{
		
			MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
			
			List list=memberMoveDao.getMoveMessage(params);//人员调动信息
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			String today=sdf.format(new Date());
			List memberList=memberMoveDao.getEmployeeMessage(params);//得到人员信息
			List retunlist=new ArrayList();
			
			if(list.size()>0){
				Map m=(Map)memberList.get(0);
				String officeDate=m.get("officeDate").toString();
				if(list.size()==1){
					
					Map temp=(Map)list.get(0);
					String proName1=temp.get("proName1").toString();
					String proName2=temp.get("proName2").toString();
					String validDate=temp.get("validDate").toString();
					String jobName1="";
					if(temp.get("jobName1")!=null){
						jobName1=temp.get("jobName1").toString();
					}
					String jobName2="";
					if(temp.get("jobName2")!=null){
						jobName2=temp.get("jobName2").toString();
					}
					
					Map smap=new HashMap();
					smap.put("proName", proName1);
					smap.put("sdate", officeDate);
					smap.put("edate", validDate);
					smap.put("jobName", jobName1);
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");  
			        Date d1=df.parse(officeDate);  
			        Date d2=df.parse(validDate); 
			        int days=daysBetween(d1,d2);
					smap.put("days", days);
				    retunlist.add(smap);
				    
				    
				    Map emap=new HashMap();
					emap.put("proName", proName2);
					emap.put("sdate", validDate);
					emap.put("edate", today);
					emap.put("jobName", jobName2);
					Date d3=df.parse(validDate);  
				    Date d4=df.parse(today); 
				    int days1=daysBetween(d3,d4);
					emap.put("days", days1);
					retunlist.add(emap);
					
				}else if(list.size()>1){
					for(int i=0;i<list.size();i++){
						Map temp=(Map)list.get(i);
						String proName1=temp.get("proName1").toString();
						String proName2=temp.get("proName2").toString();
						String validDate=temp.get("validDate").toString();
						String jobName1="";
						if(temp.get("jobName1")!=null){
							jobName1=temp.get("jobName1").toString();
						}
						String jobName2="";
						if(temp.get("jobName2")!=null){
							jobName2=temp.get("jobName2").toString();
						}
						if(i==0){//第一个
							Map nextmap=(Map)list.get(1);
							String nexttime=nextmap.get("validDate").toString();
							
							Map smap=new HashMap();
							smap.put("proName", proName1);
							smap.put("sdate", officeDate);
							smap.put("edate", validDate);
							smap.put("jobName", jobName1);
							SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");  
					        Date d1=df.parse(officeDate);  
					        Date d2=df.parse(validDate); 
					        int days=daysBetween(d1,d2);
							smap.put("days", days);
						    retunlist.add(smap);
						    
						    Map emap=new HashMap();
							emap.put("proName", proName2);
							emap.put("sdate", validDate);
							emap.put("edate", nexttime);
							emap.put("jobName", jobName2);
							Date d3=df.parse(validDate);  
						    Date d4=df.parse(nexttime); 
						    int days1=daysBetween(d3,d4);
							emap.put("days", days1);
							retunlist.add(emap);
						    
						}else if(i==list.size()-1){//最后一个
							
							SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");  
							Map emap=new HashMap();
							emap.put("proName", proName2);
							emap.put("sdate", validDate);
							emap.put("edate", today);
							emap.put("jobName", jobName2);
							Date d3=df.parse(validDate);  
						    Date d4=df.parse(today); 
						    int days1=daysBetween(d3,d4);
							emap.put("days", days1);
							retunlist.add(emap);
						}else{
							Map nextmap=(Map)list.get(i+1);
							String nexttime=nextmap.get("validDate").toString();
							
							SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");  
							Map emap=new HashMap();
							emap.put("proName", proName2);
							emap.put("sdate", validDate);
							emap.put("edate", nexttime);
							emap.put("jobName", jobName2);
							Date d3=df.parse(validDate);  
						    Date d4=df.parse(nexttime); 
						    int days1=daysBetween(d3,d4);
							emap.put("days", days1);
							retunlist.add(emap);
						}
					}
				}
			}else{
				
				Map m=(Map)memberList.get(0);
				String proName=m.get("proName").toString();
				String officeDate=m.get("officeDate").toString();
				String addr=m.get("addr").toString();
				String jobName="";
				if(m.get("jobName")!=null){
					jobName=m.get("jobName").toString();
				}
				Map map=new HashMap();
				map.put("proName", proName);
				//map.put("addr", addr);
				map.put("sdate", officeDate);
				map.put("edate", today);
				map.put("jobName", jobName);
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");  
		        Date d1=df.parse(officeDate);  
		        Date d2=df.parse(today); 
		        int days=daysBetween(d1,d2);
		        map.put("days", days);
		        retunlist.add(map);
			}
			return retunlist;
	}
	
	

	public  int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }  
	
	public List getHumanResources(int year,String unitCode) throws ParseException{
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		Map params=new HashMap();
		params.put("unitCode", unitCode);
		List resultList=new ArrayList();
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
		
		int totalavgnum=0;//年度平均在职人数
		int totalinmum=0;//年度平均入职人数
		float totalinrate=0;//年度平均入职率
		int totaloutnum=0;//年度平均离职人数
		float totaloutrate=0;//年度平均离职率
		int totalworkmonth=0;//年度平均工龄
		int monthfirst=0;
		int monthlast=0;
		int curmonth=1;
		for(int i=1;i<=12;i++){
			String firstdate=getFirstDayOfMonth(year,i);//月初
			Date fdate=formatter.parse(firstdate);
			params.put("date", firstdate);		
			List list=memberMoveDao.getHumanResources(params);
			
			//monthfirst=monthfirst+list.size();
			
			String lastdate=getLastDayOfMonth(year,i);//月末
			Date edate=formatter.parse(lastdate);
			params.put("date", lastdate);
			List list1=memberMoveDao.getHumanResources(params);
			
			//int avgnum=(list.size()+list1.size())/2; //月初与月末平均值
			int innum=0;//入职人数
			int totalworkday=0;//工龄
			for(int j=0;j<list1.size();j++){   //入职  月末               
				Map tempmap=(Map)list1.get(j);
				String officedate="";
				if(tempmap.get("officeDate")!=null){
					officedate=tempmap.get("officeDate").toString();  //入职日期
				}
						
				
				String today=formatter.format(new Date());//今天
				
				if(officedate.equals("")){
					officedate=today;
				}
				
				int month=getMonthSpace(today,officedate);
				
				totalworkday=totalworkday+month;
				
				Date date =  formatter.parse(officedate);
				if((date.after(fdate) && date.before(edate))||(officedate.equals(firstdate))||(officedate.equals(lastdate))){
					innum++;
				}
			}
			
			int avgworkday=0;
			if(list1.size()>0){
				avgworkday=totalworkday/list1.size();
			}
					
			
			int outnum=0;//离职人数
			for(int j=0;j<list.size();j++){   //   离职  月初
				Map tempmap=(Map)list.get(j);
				String leaveDate="";
				if(tempmap.get("leaveDate")!=null){
					leaveDate=tempmap.get("leaveDate").toString();  //离职日期
				}else{
					continue;
				}		
				Date date =  formatter.parse(leaveDate);
				if(date.after(fdate) && date.before(edate)){
					outnum++;
				}
			}
			
			int avgnum=(list.size()+list.size()+innum-outnum)/2; //月初与月末平均值
			
			float num= 0;
			if(avgnum!=0){
				num=(float)innum/avgnum; 
			}
					 
			num=num*100;
			DecimalFormat df = new DecimalFormat("0.000");//格式化小数  
			String inrate = df.format(num);//返回的是String类型   入职率
			
			
			float num1= 0;
			if(avgnum!=0){
				num1=(float)outnum/avgnum;  
			}
					
			num1=num1*100;
			String outrate = df.format(num1);//返回的是String类型   入职率
			
			
			//monthlast=monthlast+(list.size()+innum-outnum);
			
			SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd");
			String tday=dd.format(new Date());
			Date ddd=dd.parse(tday);
			
			//ddd.getMonth()
			
			if(ddd.getYear()==edate.getYear() && ddd.getMonth()==edate.getMonth()){
				curmonth=i;
				Map m=new HashMap();
				m.put("avgnum", avgnum);
				m.put("innum", innum);
				m.put("outnum", outnum);
				m.put("inrate", inrate);
				m.put("outrate", outrate);
				m.put("avgworkday", avgworkday);
				m.put("monthf",list.size());
				m.put("monthe",list.size()+innum-outnum);
				resultList.add(m);
				totalavgnum=totalavgnum+avgnum;
				totalinmum=totalinmum+innum;
				totalinrate=totalinrate+Float.parseFloat(inrate);
				totaloutnum=totaloutnum+outnum;
				totaloutrate=totaloutrate+Float.parseFloat(outrate);
				totalworkmonth=totalworkmonth+avgworkday;
				monthfirst=monthfirst+list.size();
				monthlast=monthlast+(list.size()+innum-outnum);
			}else if(edate.after(ddd)){
				Map m=new HashMap();
				m.put("avgnum", "-");
				m.put("innum", "-");
				m.put("outnum", "-");
				m.put("inrate", "-");
				m.put("outrate", "-");
				m.put("avgworkday", "-");
				m.put("monthf","-");
				m.put("monthe","-");
				resultList.add(m);
				totalavgnum=totalavgnum+0;
				totalinmum=totalinmum+0;
				totalinrate=totalinrate+0;
				totaloutnum=totaloutnum+0;
				totaloutrate=totaloutrate+0;
				totalworkmonth=totalworkmonth+0;
				monthfirst=monthfirst+0;
				monthlast=monthlast+0;
			}else{
				curmonth=i;
				Map m=new HashMap();
				m.put("avgnum", avgnum);
				m.put("innum", innum);
				m.put("outnum", outnum);
				m.put("inrate", inrate);
				m.put("outrate", outrate);
				m.put("avgworkday", avgworkday);
				m.put("monthf",list.size());
				m.put("monthe",list.size()+innum-outnum);
				resultList.add(m);
				totalavgnum=totalavgnum+avgnum;
				totalinmum=totalinmum+innum;
				totalinrate=totalinrate+Float.parseFloat(inrate);
				totaloutnum=totaloutnum+outnum;
				totaloutrate=totaloutrate+Float.parseFloat(outrate);
				totalworkmonth=totalworkmonth+avgworkday;
				monthfirst=monthfirst+list.size();
				monthlast=monthlast+(list.size()+innum-outnum);
			}
		}
		Map m=(Map)resultList.get(0);
		m.put("totalavgnum", totalavgnum/curmonth);
		m.put("totalinmum", totalinmum/curmonth);
		m.put("totalinrate", totalinrate/curmonth);
		m.put("totaloutnum", totaloutnum/curmonth);
		m.put("totaloutrate", totaloutrate/curmonth);
		m.put("totalworkmonth", totalworkmonth/curmonth);
		m.put("monthfirst", monthfirst/curmonth);
		m.put("monthlast", monthlast/curmonth);
	
		return resultList;
		
	}
	//获取某年某月最后一天
	public  String getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
         
        return lastDayOfMonth;
    }
	
	//得到当月第一天
	public  String getFirstDayOfMonth(int year,int month){
		String temp="";
		if(month<10){
			temp="0"+month;
		}else{
			temp=month+"";
		}
		return year+"-"+temp+"-01";
    }
	
	public int getMonthSpace(String date1,String date2) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
       /* String str1 = "2012-02";
        String str2 = "2010-01";*/
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(date1));
        aft.setTime(sdf.parse(date2));
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
      //  System.out.println(Math.abs(month + result));   
        return Math.abs(month + result);
	}
	
	public static void main(String[] args) {
		SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd");
		String tday=dd.format(new Date());
		Date ddd;
		try {
			ddd = dd.parse(tday);
			Date edate=dd.parse("2017-01-31");
			if(ddd.after(edate)){
				System.out.println(1);
			}else{
				System.out.println(2);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public List getSendList(){
		
		MemberMoveDao  memberMoveDao=(MemberMoveDao)getDao();
		List list=memberMoveDao.getSendList();
		return list;
	}
	
	
}
