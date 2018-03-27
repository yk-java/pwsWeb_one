package com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.plan.dao.PmKpiLibDao;
import com.glens.pwCloudOs.pm.plan.vo.PmKpiLib;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao.PmDayKpiDao;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao.PmDayWordloadDao;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.vo.PmDayWordloadVo;

public class PmDayKpiService extends EAPAbstractService {
	PmKpiLibDao pmKpiLibDao;
	PmDayWordloadDao pmDayWordloadDao;
	
	public PmDayWordloadDao getPmDayWordloadDao() {
		return pmDayWordloadDao;
	}
	public void setPmDayWordloadDao(PmDayWordloadDao pmDayWordloadDao) {
		this.pmDayWordloadDao = pmDayWordloadDao;
	}
	public PmKpiLibDao getPmKpiLibDao() {
		return pmKpiLibDao;
	}
	public void setPmKpiLibDao(PmKpiLibDao pmKpiLibDao) {
		this.pmKpiLibDao = pmKpiLibDao;
	}

	public Map queryProKpiProgress(Map params){
		Map res = new HashMap();
		
		Map params1=new HashMap();
		params1.put("categoryCode", params.get("categoryCode"));
		List<PmKpiLib> kpiList = pmKpiLibDao.queryForList(params1);
		res.put("kpiList", kpiList);
		String endDate = (String)params.get("endDate");
		PmDayKpiDao pmDayKpiDao = (PmDayKpiDao)this.dao;
		List<Map> kpiProgress =  pmDayKpiDao.queryProKpiProgress(params);
		
		List<Map> proProgressList=new ArrayList<Map>();// 进度数据，按项目分
		res.put("proProgressList", proProgressList);
		if(kpiProgress==null)
			return res;
		for (Iterator iterator = kpiProgress.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			String proNo = (String)map.get("proNo");
			Map progress = getProgressByProNo(proProgressList, proNo);
			if(progress==null){
				progress=new HashMap();
				progress.put("proNo", proNo);
				progress.put("proName", map.get("proName"));
				progress.put("proManager", map.get("proManager"));
				PmDayWordloadVo vo =new PmDayWordloadVo();
				vo.setProNo(proNo);
				if(endDate!=null && endDate.length()>0){
					vo.setReportDate(endDate);
				}else{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(new Date());
					vo.setReportDate(date);
				}
				PmDayWordloadVo wordloadVo = (PmDayWordloadVo)pmDayWordloadDao.selectDayWordloadByNo(vo);
				if(wordloadVo!=null){
					progress.put("totalWordload", wordloadVo.getTotalWordload());
					progress.put("allAccumulativeWordload", wordloadVo.getAllAccumulativeWordload());
					progress.put("allAccumulativeProgress", wordloadVo.getAllAccumulativeProgress()*100);
				}
				progress.put("kpiProgressList", new ArrayList<Map>());
				proProgressList.add(progress);
			}
			List<Map> kpiProgressList = (List<Map>)progress.get("kpiProgressList");
			Map kpi= new HashMap();
			kpi.put("kpiCode", map.get("kpiCode"));
			kpi.put("kpiName", map.get("kpiName"));
			kpi.put("kpiType", map.get("kpiType"));
			kpi.put("totalVal", map.get("totalVal"));
			kpi.put("completedVal", map.get("completedVal"));
			kpi.put("percentVal", map.get("percentVal"));
			kpiProgressList.add(kpi);
		}
		return res;
	}
	private Map getProgressByProNo(List<Map> progressList, String proNo) {
		for (Iterator iterator = progressList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			String proNo_t = (String)map.get("proNo");
			if(proNo.equals(proNo_t)){
				return map;
			}
		}
		return null;
	}
	
	public List<Map> queryByProNoAndReportDate(Map params){
		PmDayKpiDao pmDayKpiDao = (PmDayKpiDao)this.dao;
		List<Map> dayKpiList =  pmDayKpiDao.queryByProNoAndReportDate(params);
		return dayKpiList;
	}
}
