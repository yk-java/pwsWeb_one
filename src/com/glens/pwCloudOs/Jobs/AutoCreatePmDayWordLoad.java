package com.glens.pwCloudOs.Jobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glens.pwCloudOs.pm.baseMgr.pmBase.dao.PmBaseDao;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmBaseVo;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao.PmDayKpiDao;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao.PmDayWordloadDao;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.vo.PmDayKpi;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.vo.PmDayWordloadVo;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.dao.PmWorkHoursDao;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.dao.PmWorkListDao;

@Service("autoCreatePmDayWordload")
public class AutoCreatePmDayWordLoad {
	@Autowired
	private PmBaseDao pmBaseDao;
	@Autowired
	private PmDayWordloadDao pmDayWordloadDao;
	@Autowired
	private PmWorkHoursDao pmWorkHoursDao;
	@Autowired
	private PmWorkListDao pmWorkListDao;
	@Autowired
	private PmDayKpiDao pmDayKpiDao;
	
	public void autoCreateStart(){
		// 1、要检查的项目列表
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("companyCode", "001");
		params.put("proStatus", 2);
		List<PmBaseVo> activPros = pmBaseDao.queryForList(params);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 2、遍历项目列表
		for (Iterator iterator = activPros.iterator(); iterator.hasNext();) {
			PmBaseVo pmBaseVo = (PmBaseVo) iterator.next();
			String proNo=pmBaseVo.getProNo();
			// 3、根据项目编号查最后一次日报
//			String lastWordloadDate = pmDayWordloadDao.getLastWordloadDate(proNo);
			// 4、根据项目编号和最后一次日报，查待产生日报的日期
			List<String> dates = pmWorkHoursDao.dateWillBeCreate(proNo);
			for (Iterator iterator2 = dates.iterator(); iterator2.hasNext();) {
				String date = (String) iterator2.next();
				Date date_t;
				try {
					date_t = sdf.parse(date);
				} catch (ParseException e) {
					continue;// 日期格式不正确，跳到下一个日期
				}
				if(date_t.getTime()>new Date().getTime()){
					break;// 超出今天的日报不生成，结束
				}
				// 5、根据项目编号与日期查询审核状态
				Map<String, Object> params2 = new HashMap<String, Object>();
				params2.put("proNo", proNo);
				params2.put("date", date);
				boolean isChecked = pmWorkHoursDao.workHoursIsChecked(params2);
				// 6、判断审核状态
				// 7、如果都工作明细已经审核，则可以准备生成日报信息
				if(isChecked){
					// 8、生成日报信息
					boolean res = createDayWordload(pmBaseVo, date);
					if(res){
						System.out.println("日报生成："+pmBaseVo.getProName()+"("+pmBaseVo.getProNo()+") "+date+"成功！");
					}else{
						System.out.println("日报生成："+pmBaseVo.getProName()+"("+pmBaseVo.getProNo()+") "+date+"失败！");
					}
				}else{
					break;// 当前日期中的工作明细未审核，中止日报生成（因为当日日报数据依赖上一日日报的数据）
				}
			}
			
		}
	}
	
	
	public boolean manualCreateStart(String proNo, String updateDate){
		// 3、根据项目编号查最后一次日报
//			String lastWordloadDate = pmDayWordloadDao.getLastWordloadDate(proNo);
		PmBaseVo pmBaseVo = (PmBaseVo)pmBaseDao.findById(proNo);
		// 4、根据项目编号和最后一次日报，查待产生日报的日期
		List<String> dates = pmWorkHoursDao.dateWillBeUpdate(proNo, updateDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Iterator iterator2 = dates.iterator(); iterator2.hasNext();) {
			String date = (String) iterator2.next();
			Date date_t;
			try {
				date_t = sdf.parse(date);
			} catch (ParseException e) {
				continue;// 日期格式不正确，跳到下一个日期
			}
			if(date_t.getTime()>new Date().getTime()){
				break;// 超出今天的日报不生成，结束
			}
			// 5、根据项目编号与日期查询审核状态
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("proNo", proNo);
			params2.put("date", date);
			boolean isChecked = pmWorkHoursDao.workHoursIsChecked(params2);
			// 6、判断审核状态
			// 7、如果都工作明细已经审核，则可以准备生成日报信息
			if(isChecked){
				// 8、生成日报信息
				boolean res = createDayWordload(pmBaseVo, date);
				if(res){
					System.out.println("日报生成："+pmBaseVo.getProName()+"("+pmBaseVo.getProNo()+") "+date+"成功！");
				}else{
					System.out.println("日报生成："+pmBaseVo.getProName()+"("+pmBaseVo.getProNo()+") "+date+"失败！");
				}
			}else{
				break;// 当前日期中的工作明细未审核，中止日报生成（因为当日日报数据依赖上一日日报的数据）
			}
		}
		return true;	
	}
	
	private boolean createDayWordload(PmBaseVo pmBaseVo, String date){
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("proNo", pmBaseVo.getProNo());
		params1.put("reportDate", date);
		PmDayWordloadVo pmDayWordloadVoOld = (PmDayWordloadVo)pmDayWordloadDao.findById(params1);
		
		PmDayWordloadVo pmDayWordloadVo = new PmDayWordloadVo();
		pmDayWordloadVo.setCompanyCode(pmBaseVo.getCompanyCode());
		pmDayWordloadVo.setProNo(pmBaseVo.getProNo());
		pmDayWordloadVo.setProName(pmBaseVo.getProName());
		pmDayWordloadVo.setReportDate(date);
		String proNo=pmBaseVo.getProNo();
		// 1、统计日报工时
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("proNo", proNo);
		params.put("workDate", date);
		Float workloadHours = pmWorkHoursDao.statisticsDayWorkloadHours(params);
		pmDayWordloadVo.setWorkHours(workloadHours);// 当日工作量
		Float allWorkloadHours = pmWorkHoursDao.statisticsAllWorkloadHours(params);
		pmDayWordloadVo.setAllWorkHours(allWorkloadHours);// 累计总工作量
		// 2、统计指标量存入PM_DAY_KPI
		List<PmDayKpi> pmDayKpiList = new ArrayList<PmDayKpi>();
		List<Map<String, Object>> proKpiList = pmWorkListDao.kpiDayWorkload(params);
		List<Map<String, Object>> kpiSumList = pmDayKpiDao.queryKpiSum(params);
		Float iWorkload = 0f, oWorkload = 0f, dayWorkload = 0f;
		for (Iterator iterator = proKpiList.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			PmDayKpi dayKpi = new PmDayKpi();
			dayKpi.setCompanyCode(pmBaseVo.getCompanyCode());
			dayKpi.setProNo(proNo);
			dayKpi.setProName(pmBaseVo.getProName());
			dayKpi.setKpiCode((String)map.get("kpiCode"));
			dayKpi.setKpiValue(((Double)map.get("kpiWorkload")).floatValue());
			dayKpi.setReportDate(date);
			for (Iterator iterator2 = kpiSumList.iterator(); iterator2
					.hasNext();) {
				Map<String, Object> kpiSum = (Map<String, Object>) iterator2
						.next();
				if(dayKpi.getKpiCode().equals((String)kpiSum.get("kpiCode"))){
					dayKpi.setAllKpiValue(((Double)kpiSum.get("kpiSum")).floatValue()+dayKpi.getKpiValue());// 数据库中现有指标累计值+当前值
					break;
				}
			}
			pmDayKpiList.add(dayKpi);
			if(1 == (Integer)map.get("ioType")){// 外业
				oWorkload+=dayKpi.getKpiValue()*(Float)map.get("kpiWeight");
			}else if(2 == (Integer)map.get("ioType")){// 内业
				iWorkload+=dayKpi.getKpiValue()*(Float)map.get("kpiWeight");
			}
			if(3 != (Integer)map.get("ioType")){// 不是非标项（内业、外业指标）
				dayWorkload+=dayKpi.getKpiValue()*(Float)map.get("kpiWeight");
			}
		}
		pmDayWordloadVo.setActualIwWordload(iWorkload);// 当日内业工作量
		pmDayWordloadVo.setActualOwWordload(oWorkload);// 当日外业工作量
		pmDayWordloadVo.setDayAccumulativeWordload(dayWorkload);// 当日工作量
		Float dayProgress = 0f;
		if(pmBaseVo.getTotalWorkload()>0){
			dayProgress = dayWorkload/pmBaseVo.getTotalWorkload();
		}
		pmDayWordloadVo.setDayAccumulativeProgress(dayProgress);//TODO 当日进度量
		if(pmDayKpiList.size()>0){
			// 判断日报是否存在？存在则删除后新增，不存则新增
			if(pmDayWordloadVoOld==null){
				pmDayKpiDao.batchInsert(pmDayKpiList);
			}else{
				// 先删除再新增
				pmDayKpiDao.deleteByProNoAndDate(params1);
				pmDayKpiDao.batchInsert(pmDayKpiList);
			}
		}
		
		// 3、统计内外业人数与描述
		List<Map<String, Object>> inOutPerCnt = pmWorkListDao.inOutPerCntAndDescr(params);
		for (Iterator iterator = inOutPerCnt.iterator(); iterator.hasNext();) {
			Map<String, Object> ioPerCnt = (Map<String, Object>) iterator.next();
			if(1 == (Long)ioPerCnt.get("ioType")){// 外业
				pmDayWordloadVo.setActualOwPns(((Long)ioPerCnt.get("perCnt")).intValue());// 外业人数
				pmDayWordloadVo.setActualOwWorkDesc((String)ioPerCnt.get("workDescr"));// 外业描述
			}else if(2 == (Long)ioPerCnt.get("ioType")){// 内业
				pmDayWordloadVo.setActualIwPns(((Long)ioPerCnt.get("perCnt")).intValue());// 内业人数
				pmDayWordloadVo.setActualIwWorkDesc((String)ioPerCnt.get("workDescr"));// 内业描述
			}
		}
		// 4、查询累计量
		float allAccWordload = pmDayWordloadDao.getAllAccumulativeWordload(params);
		pmDayWordloadVo.setAllAccumulativeWordload(allAccWordload+dayWorkload);// 累计总工作量=数据库中现有累计量+当前量
		Float allAccProcess = 0f;
		if(pmBaseVo.getTotalWorkload()>0){
			allAccProcess = (allAccWordload+dayWorkload)/pmBaseVo.getTotalWorkload();
		}
		pmDayWordloadVo.setAllAccumulativeProgress(allAccProcess);// 累计总进度
		// 5、保存日报信息
		// 判断日报是否存在？存在则修改，不存在新增
		boolean res = false;
		if(pmDayWordloadVoOld==null){
			res = pmDayWordloadDao.insert(pmDayWordloadVo);
		}else{
			pmDayWordloadVo.setRowid(pmDayWordloadVoOld.getRowid());
			int rows = pmDayWordloadDao.update(pmDayWordloadVo);
			res = rows>0;
		}
		return res;
	}
}
