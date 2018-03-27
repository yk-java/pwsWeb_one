package com.glens.pwCloudOs.pm.schedulePlan.worklist.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.dao.PmProKpiDao;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmProKpi;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.dao.PmWorkHoursDao;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.dao.PmWorkListDao;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.vo.PmWorkHours;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.vo.PmWorkList;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.vo.PmWorkListExcelVo;

public class PmWorkHoursService extends EAPAbstractService {
	private PmWorkListDao pmWorkListDao;
	
	private PmProKpiDao pmProKpiDao;
	
	

	public PmProKpiDao getPmProKpiDao() {
		return pmProKpiDao;
	}

	public void setPmProKpiDao(PmProKpiDao pmProKpiDao) {
		this.pmProKpiDao = pmProKpiDao;
	}

	public PmWorkListDao getPmWorkListDao() {
		return pmWorkListDao;
	}

	public void setPmWorkListDao(PmWorkListDao pmWorkListDao) {
		this.pmWorkListDao = pmWorkListDao;
	}
	/**
	 * 新增或修改
	 * @param pmWorkHours
	 * @return
	 */
	@Transactional
	public boolean save(PmWorkHours pmWorkHours){
		// 查询工时是否已经提交过
		PmWorkHours pmWorkHours_t = null;
		PmWorkHoursDao pmWorkHoursDao = (PmWorkHoursDao)this.dao;
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("proNo", pmWorkHours.getProNo());
		parameters.put("worker", pmWorkHours.getWorker());
		parameters.put("workDate", pmWorkHours.getWorkDate());
		pmWorkHours_t = (PmWorkHours)pmWorkHoursDao.findByWorkerAndWorkDate(parameters);
		if(pmWorkHours_t != null){
			// 已存在，则修改
			this.pmWorkListDao.deleteByWorkerAndDate(parameters);
			if(pmWorkHours.getWorkList()!=null && pmWorkHours.getWorkList().size()>0){
				this.pmWorkListDao.batchInsert(pmWorkHours.getWorkList());
			}
			pmWorkHours.setRowid(pmWorkHours_t.getRowid());
			this.dao.update(pmWorkHours);
		}else{
			// 不存在，新增
			if(pmWorkHours.getWorkList()!=null && pmWorkHours.getWorkList().size()>0){
				this.pmWorkListDao.batchInsert(pmWorkHours.getWorkList());
			}
			this.dao.insert(pmWorkHours);
		}
		// 算当日工时
		Map<String,Object> params2 = new HashMap<String, Object>();
		params2.put("proNo", pmWorkHours.getProNo());
		params2.put("workDate", pmWorkHours.getWorkDate());
		params2.put("worker", pmWorkHours.getWorker());
		return pmWorkHoursDao.calculateWorkHours(params2);
	}
	
	@Transactional
	@Override
	public int delete(Object parameters) {
		PmWorkHours pmWorkHours = (PmWorkHours)this.dao.findById(parameters);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("proNo", pmWorkHours.getProNo());
		params.put("worker", pmWorkHours.getWorker());
		params.put("workDate", pmWorkHours.getWorkDate());
		this.pmWorkListDao.deleteByWorkerAndDate(params);
		
		return this.dao.delete(parameters);
	}
	
	@Transactional
	public boolean updateSave(PmWorkHours pmWorkHours){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("proNo", pmWorkHours.getProNo());
		params.put("worker", pmWorkHours.getWorker());
		params.put("workDate", pmWorkHours.getWorkDate());
		this.pmWorkListDao.deleteByWorkerAndDate(params);
		if(pmWorkHours.getWorkList()!=null && pmWorkHours.getWorkList().size()>0){
			this.pmWorkListDao.batchInsert(pmWorkHours.getWorkList());
		}
		this.dao.update(pmWorkHours);
		// 算当日工时
		PmWorkHoursDao pmWorkHoursDao = (PmWorkHoursDao)this.dao;
		Map<String,Object> params2 = new HashMap<String, Object>();
		params2.put("proNo", pmWorkHours.getProNo());
		params2.put("workDate", pmWorkHours.getWorkDate());
		params2.put("worker", pmWorkHours.getWorker());
		return pmWorkHoursDao.calculateWorkHours(params2);
	}
	
	@Override
	public ValueObject findById(Object parameters) {
		PmWorkHours pmWorkHours = null;
		pmWorkHours = (PmWorkHours)this.dao.findById(parameters);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("proNo", pmWorkHours.getProNo());
		params.put("worker", pmWorkHours.getWorker());
		params.put("workDate", pmWorkHours.getWorkDate());
		List lst = this.pmWorkListDao.queryByWorkerAndDate(params);
		pmWorkHours.setWorkList(lst);
		return pmWorkHours;
	}
	
	public PmWorkHours findByWorkerAndWorkDate(Map<String, Object> parameters) {
		PmWorkHours pmWorkHours = null;
		PmWorkHoursDao pmWorkHoursDao = (PmWorkHoursDao)this.dao;
		pmWorkHours = (PmWorkHours)pmWorkHoursDao.findByWorkerAndWorkDate(parameters);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("proNo", pmWorkHours.getProNo());
		params.put("worker", pmWorkHours.getWorker());
		params.put("workDate", pmWorkHours.getWorkDate());
		List lst = this.pmWorkListDao.queryByWorkerAndDate(params);
		pmWorkHours.setWorkList(lst);
		return pmWorkHours;
	}
	
	@Override
	public PageBean queryForPage(Map parameters, int currentPage, int perpage) {
		PageBean res = this.dao.queryForPage(parameters, currentPage, perpage);
		List data = res.getList();
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			PmWorkHours pmWorkHours = (PmWorkHours) iterator.next();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("proNo", pmWorkHours.getProNo());
			params.put("worker", pmWorkHours.getWorker());
			params.put("workDate", pmWorkHours.getWorkDate());
			List lst = this.pmWorkListDao.queryByWorkerAndDate(params);
			pmWorkHours.setWorkList(lst);
		}
		return res;
	}
	
	public int checkWorkHours(Map parameters){
		 PmWorkHoursDao pmWorkHoursDao = (PmWorkHoursDao)this.dao;
		 return pmWorkHoursDao.checkWorkHours(parameters);
	}
	
	public int backWorkHours(Map parameters){
		 PmWorkHoursDao pmWorkHoursDao = (PmWorkHoursDao)this.dao;
		 return pmWorkHoursDao.backWorkHours(parameters);
	}
	
	/* 导出 */
	public List<PmWorkListExcelVo> export(Map parameters) {
		List<PmWorkListExcelVo> data = this.dao.queryForList(parameters);
		for (int i = 0; i < data.size(); i++) {
			PmWorkListExcelVo vo = data.get(i);
			vo.setListNum(i+1);
		}
		return data;
	}
	
	/* 导入 */
	@Transactional
	public int importWorkList(List<PmWorkListExcelVo> datas){
		// 合并同一项目同一日期同一作业人的记录
		PmWorkHoursDao pmWorkHoursDao = (PmWorkHoursDao)this.dao;
		List<PmWorkHours> data = new ArrayList<PmWorkHours>();
		for (Iterator iterator = datas.iterator(); iterator.hasNext();) {
			PmWorkListExcelVo pmWorkListExcelVo = (PmWorkListExcelVo) iterator
					.next();
			// 通过proCode获取proNo
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("proCode", pmWorkListExcelVo.getProCode());
			Map<String, Object> pro = pmWorkHoursDao.selectProByProCode(params1);
			if(pro==null){
				throw new RuntimeException("异常，未找到项目（"+pmWorkListExcelVo.getProName()+", "+pmWorkListExcelVo.getProCode()+"）");
			}
			pmWorkListExcelVo.setProNo((String)pro.get("proNo"));
			
			// 工作量 
			if(pmWorkListExcelVo.getWorkload() != null && pmWorkListExcelVo.getWorkload().floatValue() != 0){
				// 取作业类型名称+取项目类型编号====>查作业类型编号
				Map<String, Object> params2 = new HashMap<String, Object>();
				params2.put("kpiName", pmWorkListExcelVo.getWorkTypeName());
				params2.put("proNo", pro.get("proNo"));
				PmProKpi kpi = pmProKpiDao.queryByProNoAndKpiName(params2);
				if(kpi==null){
					throw new RuntimeException("异常，未找到作业类型（"+pmWorkListExcelVo.getProName()+", "+pmWorkListExcelVo.getWorkTypeName()+"）");
				}
				pmWorkListExcelVo.setWorkType((String)kpi.getKpiCode());
			}
			// 带薪休假工时
			if(pmWorkListExcelVo.getPaidHours() != null && pmWorkListExcelVo.getPaidHours().floatValue() != 0){
				pmWorkListExcelVo.setIsPaidLeave(1);
//				Integer paidLeaveType = 0;
//				if("病假".equals(pmWorkListExcelVo.getPaidLeaveTypeName())){
//					paidLeaveType=1;
//				}else if("产假".equals(pmWorkListExcelVo.getPaidLeaveTypeName())){
//					paidLeaveType=2;
//				}else if("陪产假".equals(pmWorkListExcelVo.getPaidLeaveTypeName())){
//					paidLeaveType=3;
//				}else if("婚假".equals(pmWorkListExcelVo.getPaidLeaveTypeName())){
//					paidLeaveType=4;
//				}else if("丧假".equals(pmWorkListExcelVo.getPaidLeaveTypeName())){
//					paidLeaveType=5;
//				}else if("年假".equals(pmWorkListExcelVo.getPaidLeaveTypeName())){
//					paidLeaveType=6;
//				}else if("哺乳假".equals(pmWorkListExcelVo.getPaidLeaveTypeName())){
//					paidLeaveType=7;
//				}else if("法定假日".equals(pmWorkListExcelVo.getPaidLeaveTypeName())){
//					paidLeaveType=8;
//				}else if("其它".equals(pmWorkListExcelVo.getPaidLeaveTypeName())){
//					paidLeaveType=9;
//				}
//				pmWorkListExcelVo.setPaidLeaveType(paidLeaveType);
			}
			// 异地补贴
//			if(pmWorkListExcelVo.getNotLocalStandardName()!=null && pmWorkListExcelVo.getNotLocalStandardName().length() > 0){
//				if("（省内）组员".equals(pmWorkListExcelVo.getNotLocalStandardName())){
//					pmWorkListExcelVo.setHasNotLocal(1);
//					pmWorkListExcelVo.setNotLocalStandard(20);
//				}else if("（省内）合伙人、经理".equals(pmWorkListExcelVo.getNotLocalStandardName())){
//					pmWorkListExcelVo.setHasNotLocal(1);
//					pmWorkListExcelVo.setNotLocalStandard(30);
//				}else if("（省外）组员".equals(pmWorkListExcelVo.getNotLocalStandardName())){
//					pmWorkListExcelVo.setHasNotLocal(1);
//					pmWorkListExcelVo.setNotLocalStandard(40);
//				}else if("（省外）合伙人、经理".equals(pmWorkListExcelVo.getNotLocalStandardName())){
//					pmWorkListExcelVo.setHasNotLocal(1);
//					pmWorkListExcelVo.setNotLocalStandard(50);
//				}
//			}
			// 餐补
//			if(pmWorkListExcelVo.getMealStandardName()!=null && pmWorkListExcelVo.getMealStandardName().length() > 0){
//				if("有".equals(pmWorkListExcelVo.getMealStandardName())){
//					pmWorkListExcelVo.setHasMeal(1);
//					pmWorkListExcelVo.setMealStandard(15);
//				}
//			}
			
			// 作业人，查找员工信息如果查出多个，则取工号根据工号确定
			Map<String, Object> params3 = new HashMap<String, Object>();
			params3.put("workerName", pmWorkListExcelVo.getWorkerName());
			List<Map<String, Object>> emps = pmWorkHoursDao.selectEmployeeByName(params3);
			if(emps == null || emps.size()==0){
				throw new RuntimeException("异常，未找到作业人（"+pmWorkListExcelVo.getWorkerName()+"）");
			}else{
				if(emps.size()>1){
					for (Iterator iterator2 = emps.iterator(); iterator2.hasNext();) {
						Map<String, Object> map = (Map<String, Object>) iterator2
								.next();
						if(((String)map.get("jobNo")).equals(pmWorkListExcelVo.getJobNo())){
							pmWorkListExcelVo.setWorker((String)map.get("employeecode"));
							break;
						}
					}
				}else{
					Map<String, Object> map = (Map<String, Object>)emps.get(0);
					if(((String)map.get("jobNo")).equals(pmWorkListExcelVo.getJobNo())){
						pmWorkListExcelVo.setWorker((String)map.get("employeecode"));
					}
				}
				if(pmWorkListExcelVo.getWorker() == null){
					throw new RuntimeException("异常，未找到作业人工号（"+pmWorkListExcelVo.getWorkerName()+", "+pmWorkListExcelVo.getJobNo()+"）");
				}
			}
			// 质量核查人
			if(pmWorkListExcelVo.getQualityCheckUserName()!=null && pmWorkListExcelVo.getQualityCheckUserName().length()>0){
				// 查找员工信息如果查出多个，则取工号根据工号确定
				Map<String, Object> params4 = new HashMap<String, Object>();
				params4.put("workerName", pmWorkListExcelVo.getQualityCheckUserName());
				List<Map<String, Object>> emps4 = pmWorkHoursDao.selectEmployeeByName(params4);
				if(emps4 == null || emps4.size()==0){
					throw new RuntimeException("异常，未找到质量核查人员（"+pmWorkListExcelVo.getQualityCheckUserName()+"）");
				}else{
					if(emps4.size()>1){
						for (Iterator iterator2 = emps4.iterator(); iterator2.hasNext();) {
							Map<String, Object> map = (Map<String, Object>) iterator2
									.next();
							if(((String)map.get("jobNo")).equals(pmWorkListExcelVo.getQualityCheckUserJobNo())){
								pmWorkListExcelVo.setQualityCheckUser((String)map.get("employeecode"));
								break;
							}
						}
					}else{
						Map<String, Object> map = (Map<String, Object>)emps4.get(0);
						if(((String)map.get("jobNo")).equals(pmWorkListExcelVo.getQualityCheckUserJobNo())){
							pmWorkListExcelVo.setQualityCheckUser((String)map.get("employeecode"));
						}
					}
					if(pmWorkListExcelVo.getWorker() == null){
						throw new RuntimeException("异常，未找到质量核查人员工号（"+pmWorkListExcelVo.getQualityCheckUserName()+", "+pmWorkListExcelVo.getQualityCheckUserJobNo()+"）");
					}
				}
			}
			// 判断是否和data中所有的同组，如果同组，则合并！！
			boolean exists=false;
			for (Iterator<PmWorkHours> iterator2 = data.iterator(); iterator2.hasNext();) {
				 PmWorkHours pmWorkHours_item =  iterator2.next();
				 if(pmWorkHours_item != null 
							&& pmWorkHours_item.getProCode().equals(pmWorkListExcelVo.getProCode())
							&& pmWorkHours_item.getWorkDate().equals(pmWorkListExcelVo.getWorkDate())
							&& pmWorkHours_item.getJobNo().equals(pmWorkListExcelVo.getJobNo())){
						PmWorkList pmWorkList = new PmWorkList();
						try {
							BeanUtils.copyProperties(pmWorkList, pmWorkListExcelVo);
						} catch (Exception e) {
							e.printStackTrace();
						}
						List<PmWorkList> workList = pmWorkHours_item.getWorkList();
						if(workList==null){
							workList=new ArrayList<PmWorkList>();
							pmWorkHours_item.setWorkList(workList);
						}
						workList.add(pmWorkList);
						exists=true;
						break;
					}
			}
			// 新的一组
			if(!exists){
				PmWorkHours pmWorkHours=new PmWorkHours();
				try {
					BeanUtils.copyProperties(pmWorkHours, pmWorkListExcelVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(pmWorkListExcelVo.getWorkload() != null ){//&& pmWorkListExcelVo.getWorkload() > 0
					PmWorkList pmWorkList = new PmWorkList();
					try {
						BeanUtils.copyProperties(pmWorkList, pmWorkListExcelVo);
					} catch (Exception e) {
						e.printStackTrace();
					}
					List<PmWorkList> workList = new ArrayList<PmWorkList>();
					pmWorkHours.setWorkList(workList);
					workList.add(pmWorkList);
				}
				data.add(pmWorkHours);
			}
		}
		// 保存数据
		int cnt=0;
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			PmWorkHours pmWorkHours = (PmWorkHours) iterator.next();
			if(pmWorkHours.getCheckStatus()!=null){
				pmWorkHours.setCheckStatus(null);// 导入时不修改审核状态
			}
			Map<String,Object> parameters = new HashMap<String, Object>();
			// 查询库里是否存在
			parameters.put("proNo", pmWorkHours.getProNo());
			parameters.put("worker", pmWorkHours.getWorker());
			parameters.put("workDate", pmWorkHours.getWorkDate());
			PmWorkHours pmWorkHours_t = (PmWorkHours)pmWorkHoursDao.findByWorkerAndWorkDate(parameters);
			if(pmWorkHours_t != null){
				if(pmWorkHours_t.getCheckStatus()!=null && pmWorkHours_t.getCheckStatus()==1){
					continue;// 如果已经审核过，不做修改**********
				}
				// 已存在，则修改
				this.pmWorkListDao.deleteByWorkerAndDate(parameters);
				if(pmWorkHours.getWorkList()!=null && pmWorkHours.getWorkList().size()>0){
					this.pmWorkListDao.batchInsert(pmWorkHours.getWorkList());
				}
				pmWorkHours.setRowid(pmWorkHours_t.getRowid());
				pmWorkHoursDao.update(pmWorkHours);
			}else{
				// 新增
				if(pmWorkHours.getWorkList()!=null && pmWorkHours.getWorkList().size()>0){
					this.pmWorkListDao.batchInsert(pmWorkHours.getWorkList());
				}
				pmWorkHoursDao.insert(pmWorkHours);
			}
			// 计算工时
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("proNo", pmWorkHours.getProNo());
			params.put("workDate", pmWorkHours.getWorkDate());
			params.put("worker", pmWorkHours.getWorker());
			if(pmWorkHoursDao.calculateWorkHours(params)){
				cnt++;
			}
			
			// 导入工时
			if(pmWorkHours.getWorkHours()!=null && pmWorkHours.getWorkHours()>0){
				Map<String, Object> params2 = new HashMap<String, Object>();
				params2.put("workDate", pmWorkHours.getWorkDate());
				params2.put("worker", pmWorkHours.getWorker());
				params2.put("proNo", pmWorkHours.getProNo());
				PmWorkHours pmWorkHours2 = (PmWorkHours)pmWorkHoursDao.findByWorkerAndWorkDate(params2);
				pmWorkHours2.setWorkHours(pmWorkHours.getWorkHours());
				pmWorkHoursDao.update(pmWorkHours2);
			}
		}
		return cnt;
	}
	
	
	public List<Map<String, Object>> workHoursStatistics(Map<String, Object> parameters) {
		PmWorkHoursDao pmWorkHoursDao = (PmWorkHoursDao)this.dao;
		List<Map<String, Object>> data = pmWorkHoursDao.workHoursStatistics(parameters);
		return data;
	}
	
	public List<Map<String, Object>> workHoursStatisticsExport(Map<String, Object> parameters) {
		PmWorkHoursDao pmWorkHoursDao = (PmWorkHoursDao)this.dao;
		List<Map<String, Object>> data = pmWorkHoursDao.workHoursStatisticsExport(parameters);
		return data;
	}
	
	public List<Map<String, Object>> proCostStatistics(Map<String, Object> parameters) {
		PmWorkHoursDao pmWorkHoursDao = (PmWorkHoursDao)this.dao;
		List<Map<String, Object>> data = pmWorkHoursDao.proCostStatistics(parameters);
		return data;
	}
}
