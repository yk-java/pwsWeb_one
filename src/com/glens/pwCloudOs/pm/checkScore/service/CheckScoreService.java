package com.glens.pwCloudOs.pm.checkScore.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.checkScore.dao.CheckScoreDao;
import com.glens.pwCloudOs.pm.checkScore.vo.DATASOURCE;
import com.glens.pwCloudOs.pm.checkScore.web.CheckScoreForm;

public class CheckScoreService extends EAPAbstractService {
	
	

	public boolean insertCheckScore(String companyCode,String name,float score,String pro,String checkMan,String checkObj,String checkDesc) {
		// TODO Auto-generated method stub
		
		
		//String name=parameters.getDocName();.
		
		int sta=0;//标示是否是周、月计划  如果是不用累加计算分值
		
		String code="";
		if(name.equals("项目晨会")){
			code=DATASOURCE.CHENBAO;
		}else if(name.equals("项目日报")){
			code=DATASOURCE.RIBAO;
		}else if(name.equals("项目日志")){
			code=DATASOURCE.RIZHI;
		}else if(name.equals("周计划评定")){
			code=DATASOURCE.ZHOUJIHUA;
			sta=1;
		}else if(name.equals("周计划反馈评定")){
			code=DATASOURCE.ZHOUJIHUAFANKUI;
			sta=1;
		}else if(name.equals("客户周报")){
			code=DATASOURCE.ZHOUBAO;
		}else if(name.equals("月计划评定")){
			code=DATASOURCE.YUEJIHUA;
			sta=1;
		}else if(name.equals("月计划反馈评定")){
			code=DATASOURCE.YUEJIHUAFANKUI;
			sta=1;
		}else if(name.equals("客户月报")){
			code=DATASOURCE.YUEBAO;
		}
		CheckScoreDao dao=(CheckScoreDao)getDao();
		Map m=new HashMap();
		m.put("sourceCode", code);
		List kpilist=dao.getKpi(m);
		Map insertMap=new HashMap();
		float kpiscore=0;
		if(kpilist!=null && kpilist.size()>0){
			Map tempmap=(Map)kpilist.get(0);
			String kpicode=tempmap.get("KPI_CODE").toString();
			String kpiname=tempmap.get("KPI_NAME").toString();
			kpiscore=Float.parseFloat(tempmap.get("KPI_SCORE").toString());
			insertMap.put("kpiCode", kpicode);
			insertMap.put("kpiName", kpiname);
		}
		float f=0;
		if(sta==1){
			f=score;
		}else{
			f=kpiscore+score;
		}
		
		
		SimpleDateFormat ds=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String checkTime=ds.format(new Date());
		insertMap.put("companyCode", companyCode);
		insertMap.put("checkScore", f);
		insertMap.put("checkedPro", pro);
		insertMap.put("checkMan", checkMan);
		insertMap.put("checkTime", checkTime);
		insertMap.put("checkDesc", checkDesc);
		insertMap.put("checkObj", checkObj);
		insertMap.put("checkDesc", checkDesc);
		
		//#{companyCode},#{kpiCode},#{kpiName},#{checkScore},#{checkedPro},#{checkMan},#{checkTime},#{checkDesc},#{checkObj}
		
		
		return dao.insertCheckScore(insertMap);
	}
	
}
