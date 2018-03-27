package com.glens.pwCloudOs.pm.plan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.dao.PmBaseDao;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.dao.PmProKpiDao;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmProKpi;
import com.glens.pwCloudOs.pm.plan.dao.PmPlanDao;
import com.glens.pwCloudOs.pm.plan.dao.PmPlanKpiDao;
import com.glens.pwCloudOs.pm.plan.vo.PmPlan;
import com.glens.pwCloudOs.pm.plan.vo.PmPlanKpi;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao.PmDayKpiDao;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao.PmDayWordloadDao;

public class PmPlanService extends EAPAbstractService {
	PmPlanKpiDao pmPlanKpiDao;
	
	public PmPlanKpiDao getPmPlanKpiDao() {
		return pmPlanKpiDao;
	}

	public void setPmPlanKpiDao(PmPlanKpiDao pmPlanKpiDao) {
		this.pmPlanKpiDao = pmPlanKpiDao;
	}
	
	PmDayKpiDao pmDayKpiDao;
	
	
	public PmDayKpiDao getPmDayKpiDao() {
		return pmDayKpiDao;
	}

	public void setPmDayKpiDao(PmDayKpiDao pmDayKpiDao) {
		this.pmDayKpiDao = pmDayKpiDao;
	}
	
	PmDayWordloadDao pmDayWordloadDao;
	

	public PmDayWordloadDao getPmDayWordloadDao() {
		return pmDayWordloadDao;
	}

	public void setPmDayWordloadDao(PmDayWordloadDao pmDayWordloadDao) {
		this.pmDayWordloadDao = pmDayWordloadDao;
	}
	
	PmBaseDao pmBaseDao;
	

	public PmBaseDao getPmBaseDao() {
		return pmBaseDao;
	}

	public void setPmBaseDao(PmBaseDao pmBaseDao) {
		this.pmBaseDao = pmBaseDao;
	}
	
	PmProKpiDao pmProKpiDao;
	
	public PmProKpiDao getPmProKpiDao() {
		return pmProKpiDao;
	}

	public void setPmProKpiDao(PmProKpiDao pmProKpiDao) {
		this.pmProKpiDao = pmProKpiDao;
	}

	public List<Map> findDayKpiList(String proNo, String sdate, String edate){
		// 实际完成指标量
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("proNo", proNo);
		params.put("sdate", sdate);
		params.put("edate", edate);
		List<Map> dayKpiList = pmDayKpiDao.findKpiByDate(params);
		return dayKpiList;
	}
	public Map<String, Object> statisticsWordloadByDate(String proNo, String sdate, String edate){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("proNo", proNo);
		params.put("sdate", sdate);
		params.put("edate", edate);
		Map<String,Object> dayWordload = pmDayWordloadDao.selectWordloadByDate(params);
		return dayWordload;
	}
	@Override
	public PageBean queryForPage(Map parameters, int currentPage, int perpage) {
		PageBean page = this.dao.queryForPage(parameters, currentPage, perpage);
		List lst = page.getList();
		for (int i = 0; i < lst.size(); i++) {
			PmPlan pmPlan = (PmPlan)lst.get(i);
			String sdate = pmPlan.getSdate();
			String edate = pmPlan.getEdate();
			String proNo = pmPlan.getProNo();
//			// 实际完成指标量
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("proNo", proNo);
//			params.put("sdate", sdate);
//			params.put("edate", edate);
//			List<Map> dayKpiList = pmDayKpiDao.findKpiByDate(params);
			// 实际完成工作量
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("proNo", proNo);
			params.put("sdate", sdate);
			params.put("edate", edate);
			Map<String,Object> dayWordload = pmDayWordloadDao.selectWordloadByDate(params);
			Float sumWordload = 0f; 
			try {
				Double sumWordload_t = (Double)dayWordload.get("wordload");
				sumWordload = sumWordload_t.floatValue();
			} catch (Exception e) {
			}
			pmPlan.setSumWordload(sumWordload);
			Integer iwpcnt = 0;
			Integer owpcnt = 0;
			try {
				iwpcnt = Integer.parseInt(dayWordload.get("iwpcnt").toString());
				owpcnt = Integer.parseInt(dayWordload.get("owpcnt").toString());
			} catch (Exception e1) {
			}
			pmPlan.setIwPercnt(iwpcnt);
			pmPlan.setOwPercnt(owpcnt);
			// 计划完成指标量
//			Map<String, Object> params2= new HashMap<String, Object>();
//			params2.put("proNo", pmPlan.getProNo());
//			params2.put("planNo", pmPlan.getPlanNo());
//			List<PmPlanKpi> planKpiList = pmPlanKpiDao.findByProNoAndPlanNo(params2);
//			List<PmPlanKpi> planKpiList_noPer=new ArrayList<PmPlanKpi>();
//			for(int j=0; j<planKpiList.size(); j++){
//				PmPlanKpi planKpi = planKpiList.get(j);
//				if(planKpi.getIsPerCnt() != 1){
//					planKpiList_noPer.add(planKpi);
//				}
//			}
			
//			// 计划完成工作量
//			PmBaseVo pmBaseVo = (PmBaseVo)pmBaseDao.findById(proNo);
//			// 项目外业类型
//			String owSumType = pmBaseVo.getOwSumType();
//			// 项目阶段
//			int proPhase = pmBaseVo.getProPhase();
//			// 
//			Float owProportion = 0.5f;
//			if(pmBaseVo.getOwProportion() != null){
//				owProportion = pmBaseVo.getOwProportion();
//			}
//			// 
//			Float iwProportion = 0.5f;
//			if(pmBaseVo.getIwProportion() != null){
//				iwProportion = pmBaseVo.getIwProportion();
//			}
//			/* 计算外业量 */
//			Float oWordload=0f;
//			if("1".equals(owSumType)){
//				Float A = sumByTypeAndLabel(planKpiList_noPer, 1, "A");
//				Float B = sumByTypeAndLabel(planKpiList_noPer, 1, "B");
//				Float C = sumByTypeAndLabel(planKpiList_noPer, 1, "C");
//				Float cKm = C/40.0f;
//				if(proPhase == 3){
//					cKm = C/25.0f;
//				}
//				//Float sum = A+B+cKm;
//				oWordload = A*0.4f+B*0.1f+cKm*0.4f;
//			}else if("2".equals(owSumType)){
//				Float A = sumByTypeAndLabel(planKpiList_noPer, 1, "A");
//				Float C = sumByTypeAndLabel(planKpiList_noPer, 1, "C");
//				Float cKm = C/40.0f;
//				if(proPhase==3){
//					cKm = C/25.0f;
//				}
//				//Float sum=A+cKm;
//				oWordload = A*0.5f + cKm*0.5f;
//			}else{
//				oWordload = sumByTypeAndLabel(planKpiList_noPer, 1, null);
//			}
//			/* 计算内业量 */
//			Float iWordload=0f;
//			iWordload = sumByTypeAndLabel(planKpiList_noPer, 2, null);

			/* 总工作量 */
			Float planComplatePer = 0f;
			if(pmPlan.getPlanWordload() != null && pmPlan.getPlanWordload()!=0){
				planComplatePer = (sumWordload/pmPlan.getPlanWordload())*100;
			}
			pmPlan.setPlanComplatePer(Math.round(planComplatePer*100)/100F);
			Float proPlanProgress = 0f;
			if(pmPlan.getProTotalWordload()!=null && pmPlan.getProTotalWordload()!=0 
					&& pmPlan.getAllPlanWordload()!=null){
				proPlanProgress = (pmPlan.getAllPlanWordload()/pmPlan.getProTotalWordload())*100;
			}
			pmPlan.setProPlanProgress(Math.round(proPlanProgress*100)/100F);
//			System.out.println("sumW:"+sumWordload+", (ow:"+oWordload+", owp:"+owProportion+", iw:"+iWordload+", iwp:"+iwProportion+") planSumW:"+planSumWordload+", compPer:"+planComplatePer);
			//////////////////////////////////////////////////////////////
			// 计划完成率
//			if(dayKpiList.size()==planKpiList_noPer.size()){
//				float kpiComplatePer=0;
//				for (int j = 0; j < planKpiList_noPer.size(); j++) {
//					PmPlanKpi planKpi = (PmPlanKpi)planKpiList_noPer.get(j);
//					Map<String, Object> dayKpi = dayKpiList.get(j);
//					Float planKpiVal = planKpi.getKpiValue();
//					Float dayKpiVal = 0f;
//					try {
//						dayKpiVal = Float.parseFloat(dayKpi.get("kpiValue").toString());
//					} catch (NumberFormatException e) {
//						e.printStackTrace();
//					}
//					if(planKpiVal != 0 && dayKpiVal != 0){
//						Float per = (dayKpiVal/planKpiVal)*100;
//						kpiComplatePer+=per;
//					}
//				}
//				Float per=kpiComplatePer/100.0f*planKpiList.size();
//				per=Math.round(per*10f)/10f;
//				pmPlan.setPlanComplatePer(per);
//			}
			
		}
		return page;
	}
	
	@Override
	public List<PmPlan> queryForList(Object parameters) {
		List<PmPlan> lst = getDao().queryForList(parameters);
		for (int i = 0; i < lst.size(); i++) {
			PmPlan pmPlan = (PmPlan)lst.get(i);
			pmPlan.setListNo(i+1L);
			String sdate = pmPlan.getSdate();
			String edate = pmPlan.getEdate();
			String proNo = pmPlan.getProNo();
//			// 实际完成指标量
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("proNo", proNo);
//			params.put("sdate", sdate);
//			params.put("edate", edate);
//			List<Map> dayKpiList = pmDayKpiDao.findKpiByDate(params);
			// 实际完成工作量
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("proNo", proNo);
			params.put("sdate", sdate);
			params.put("edate", edate);
			Map<String,Object> dayWordload = pmDayWordloadDao.selectWordloadByDate(params);
			Float sumWordload = 0f; 
			try {
				Double sumWordload_t = (Double)dayWordload.get("wordload");
				sumWordload = sumWordload_t.floatValue();
			} catch (Exception e) {
			}
			pmPlan.setSumWordload(sumWordload);
			Integer iwpcnt = 0;
			Integer owpcnt = 0;
			try {
				iwpcnt = Integer.parseInt(dayWordload.get("iwpcnt").toString());
				owpcnt = Integer.parseInt(dayWordload.get("owpcnt").toString());
			} catch (Exception e1) {
			}
			pmPlan.setIwPercnt(iwpcnt);
			pmPlan.setOwPercnt(owpcnt);
			// 计划完成指标量
//			Map<String, Object> params2= new HashMap<String, Object>();
//			params2.put("proNo", pmPlan.getProNo());
//			params2.put("planNo", pmPlan.getPlanNo());
//			List<PmPlanKpi> planKpiList = pmPlanKpiDao.findByProNoAndPlanNo(params2);
//			List<PmPlanKpi> planKpiList_noPer=new ArrayList<PmPlanKpi>();
//			for(int j=0; j<planKpiList.size(); j++){
//				PmPlanKpi planKpi = planKpiList.get(j);
//				if(planKpi.getIsPerCnt() != 1){
//					planKpiList_noPer.add(planKpi);
//				}
//			}
//			// 计划完成工作量
//			PmBaseVo pmBaseVo = (PmBaseVo)pmBaseDao.findById(proNo);
//			// 项目外业类型
//			String owSumType = pmBaseVo.getOwSumType();
//			// 项目阶段
//			int proPhase = pmBaseVo.getProPhase();
//			// 
//			Float owProportion = 0.5f;
//			if(pmBaseVo.getOwProportion() != null){
//				owProportion = pmBaseVo.getOwProportion();
//			}
//			// 
//			Float iwProportion = 0.5f;
//			if(pmBaseVo.getIwProportion() != null){
//				iwProportion = pmBaseVo.getIwProportion();
//			}
//			/* 计算外业量 */
//			Float oWordload=0f;
//			if("1".equals(owSumType)){
//				Float A = sumByTypeAndLabel(planKpiList_noPer, 1, "A");
//				Float B = sumByTypeAndLabel(planKpiList_noPer, 1, "B");
//				Float C = sumByTypeAndLabel(planKpiList_noPer, 1, "C");
//				Float cKm = C/40.0f;
//				if(proPhase == 3){
//					cKm = C/25.0f;
//				}
//				//Float sum = A+B+cKm;
//				oWordload = A*0.4f+B*0.1f+cKm*0.4f;
//			}else if("2".equals(owSumType)){
//				Float A = sumByTypeAndLabel(planKpiList_noPer, 1, "A");
//				Float C = sumByTypeAndLabel(planKpiList_noPer, 1, "C");
//				Float cKm = C/40.0f;
//				if(proPhase==3){
//					cKm = C/25.0f;
//				}
//				//Float sum=A+cKm;
//				oWordload = A*0.5f + cKm*0.5f;
//			}else{
//				oWordload = sumByTypeAndLabel(planKpiList_noPer, 1, null);
//			}
//			/* 计算内业量 */
//			Float iWordload=0f;
//			iWordload = sumByTypeAndLabel(planKpiList_noPer, 2, null);

			/* 总工作量 */
			Float planComplatePer = 0f;
			if(pmPlan.getPlanWordload() != null && pmPlan.getPlanWordload()!=0){
				planComplatePer = (sumWordload/pmPlan.getPlanWordload())*100;
			}
			pmPlan.setPlanComplatePer(Math.round(planComplatePer*100)/100F);
			Float proPlanProgress = 0f;
			if(pmPlan.getProTotalWordload()!=null && pmPlan.getProTotalWordload()!=0 
					&& pmPlan.getAllPlanWordload()!=null){
				proPlanProgress = (pmPlan.getAllPlanWordload()/pmPlan.getProTotalWordload())*100;
			}
			pmPlan.setProPlanProgress(Math.round(proPlanProgress*100)/100F);
//			System.out.println("sumW:"+sumWordload+", (ow:"+oWordload+", owp:"+owProportion+", iw:"+iWordload+", iwp:"+iwProportion+") planSumW:"+planSumWordload+", compPer:"+planComplatePer);
			//////////////////////////////////////////////////////////////
			// 计划完成率
//			if(dayKpiList.size()==planKpiList_noPer.size()){
//				float kpiComplatePer=0;
//				for (int j = 0; j < planKpiList_noPer.size(); j++) {
//					PmPlanKpi planKpi = (PmPlanKpi)planKpiList_noPer.get(j);
//					Map<String, Object> dayKpi = dayKpiList.get(j);
//					Float planKpiVal = planKpi.getKpiValue();
//					Float dayKpiVal = 0f;
//					try {
//						dayKpiVal = Float.parseFloat(dayKpi.get("kpiValue").toString());
//					} catch (NumberFormatException e) {
//						e.printStackTrace();
//					}
//					if(planKpiVal != 0 && dayKpiVal != 0){
//						Float per = (dayKpiVal/planKpiVal)*100;
//						kpiComplatePer+=per;
//					}
//				}
//				Float per=kpiComplatePer/100.0f*planKpiList.size();
//				per=Math.round(per*10f)/10f;
//				pmPlan.setPlanComplatePer(per);
//			}
			
		}
		return lst;
	}

	private Float sumByTypeAndLabel(List<PmPlanKpi> data, int kpiType, String itemLabel) {
		Float sum=0f;
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			PmPlanKpi pmPlanKpi = (PmPlanKpi) iterator.next();
			if(pmPlanKpi.getMergeFlag()!=2){// 不等于2时，参与求和
				if(pmPlanKpi.getKpiType() == kpiType){// 指标类型一致
					if(itemLabel!=null){
						if(itemLabel.equals(pmPlanKpi.getItemLabel())){// 指标标签一致
							sum+=pmPlanKpi.getKpiValue();
						}
					}else{// label为null时直接累加
						sum+=pmPlanKpi.getKpiValue();
					}
				}
			}
		}
		return sum;
	}

	@Override
	public int delete(Object parameters) {
		PmPlan vo=(PmPlan)parameters;
		PmPlan plan = (PmPlan)this.dao.findById(vo.getRowid());
		// 删除指标
		Map params = new HashMap();
		params.put("proNo", plan.getProNo());
		params.put("planNo", plan.getPlanNo());
		pmPlanKpiDao.deleteByProNoAndPlanNo(params);
		return this.dao.delete(vo.getRowid());
	}
	
	@Override
	public boolean insert(Object parameters) {
		PmPlanDao dao = (PmPlanDao)this.dao;
		PmPlan vo=(PmPlan)parameters;
		String kpiData = vo.getKpiData();
		List<PmPlanKpi> kpiList = new ArrayList<PmPlanKpi>();
		if(kpiData!=null){
			Float planWordload=0f;
			String[] kpi_arr = kpiData.split("&");
			for(int i=0;i<kpi_arr.length;i++){
				String kpi = kpi_arr[i];
				if(kpi.indexOf("=")==-1)continue;
				String[] kpi_item = kpi.split("=");
				PmPlanKpi kpiVo=new PmPlanKpi();
				kpiVo.setCompanyCode(vo.getCompanyCode());
				kpiVo.setProNo(vo.getProNo());
				kpiVo.setProName(vo.getProName());
				kpiVo.setPlanNo(vo.getPlanNo());
				kpiVo.setKpiCode(kpi_item[0]);
				PmProKpi pmProKpi = pmProKpiDao.selectByKpiCode(kpi_item[0]);
				if(kpi_item[1].indexOf(",") != -1){
					String[] vals = kpi_item[1].split(",");
					Float val1=0f;
					try {
						val1=Float.parseFloat(vals[0]);
					} catch (NumberFormatException e) {
					}
					kpiVo.setKpiValue(val1);
					// 附属值
					Float val2=0f;
					try {
						val2=Float.parseFloat(vals[1]);
					} catch (NumberFormatException e) {
					}
					kpiVo.setAttachedValue(val2);
				}else{
					Float val=0f;
					try {
						val=Float.parseFloat(kpi_item[1]);
					} catch (NumberFormatException e) {
					}
					kpiVo.setKpiValue(val);
				}
				planWordload+=kpiVo.getKpiValue()*pmProKpi.getKpiWeight();
				kpiList.add(kpiVo);
			}
			vo.setPlanWordload(planWordload);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("proNo", vo.getProNo());
			params.put("date", vo.getSdate());
			Float allPlanWordload = dao.getAllPlanWordload(params);
			if(allPlanWordload==null){
				allPlanWordload = 0f;
			}
			vo.setAllPlanWordload(allPlanWordload+planWordload);
		}
		if(kpiList.size()>0)
			pmPlanKpiDao.batchInsert(kpiList);
		return dao.insert(parameters);
	}
	
	@Override
	public int update(Object parameters) {
		PmPlanDao dao = (PmPlanDao)this.dao;
		PmPlan vo=(PmPlan)parameters;
		String kpiData = vo.getKpiData();
		List<PmPlanKpi> kpiList = new ArrayList<PmPlanKpi>();
		if(kpiData!=null){
			Float planWordload=0f;
			String[] kpi_arr = kpiData.split("&");
			for(int i=0;i<kpi_arr.length;i++){
				String kpi = kpi_arr[i];
				if(kpi.indexOf("=")==-1)continue;
				String[] kpi_item = kpi.split("=");
				PmPlanKpi kpiVo=new PmPlanKpi();
				kpiVo.setCompanyCode(vo.getCompanyCode());
				kpiVo.setProNo(vo.getProNo());
				kpiVo.setProName(vo.getProName());
				kpiVo.setPlanNo(vo.getPlanNo());
				kpiVo.setKpiCode(kpi_item[0]);
				PmProKpi pmProKpi = pmProKpiDao.selectByKpiCode(kpi_item[0]);
				if(kpi_item[1].indexOf(",") != -1){
					String[] vals = kpi_item[1].split(",");
					Float val1=0f;
					try {
						val1=Float.parseFloat(vals[0]);
					} catch (NumberFormatException e) {
					}
					kpiVo.setKpiValue(val1);
					// 附属值
					Float val2=0f;
					try {
						val2=Float.parseFloat(vals[1]);
					} catch (NumberFormatException e) {
					}
					kpiVo.setAttachedValue(val2);
				}else{
					Float val=0f;
					try {
						val=Float.parseFloat(kpi_item[1]);
					} catch (NumberFormatException e) {
					}
					kpiVo.setKpiValue(val);
				}
				planWordload+=kpiVo.getKpiValue()*pmProKpi.getKpiWeight();
				kpiList.add(kpiVo);
			}
			vo.setPlanWordload(planWordload);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("proNo", vo.getProNo());
			params.put("date", vo.getSdate());
			Float allPlanWordload = dao.getAllPlanWordload(params);
			if(allPlanWordload==null){
				allPlanWordload = 0f;
			}
			vo.setAllPlanWordload(allPlanWordload+planWordload);
		}
		if(kpiList.size()>0){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("proNo", vo.getPlanNo());
			params.put("planNo", vo.getPlanNo());
			pmPlanKpiDao.deleteByProNoAndPlanNo(params);
			pmPlanKpiDao.batchInsert(kpiList);
		}
		return dao.update(parameters);
	}
	public List<PmPlanKpi> findByProNoAndPlanNo(Map params){
		return pmPlanKpiDao.findByProNoAndPlanNo(params);
	}
}
