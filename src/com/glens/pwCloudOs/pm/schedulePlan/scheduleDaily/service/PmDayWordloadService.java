/**
 * @Title: PmDayWordloadService.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 下午5:30:32
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.dao.PmBaseDao;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmBaseVo;
import com.glens.pwCloudOs.pm.projDoc.dao.DocumentCheckDao;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao.PmDayKpiDao;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.dao.PmDayWordloadDao;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.vo.PmDayKpi;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.vo.PmDayWordloadVo;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

public class PmDayWordloadService extends EAPAbstractService {
	private PmBaseDao pmBaseDao;
	private PmDayKpiDao pmDayKpiDao;
	private DocumentCheckDao documentCheckDao;

	public DocumentCheckDao getDocumentCheckDao() {
		return documentCheckDao;
	}

	public void setDocumentCheckDao(DocumentCheckDao documentCheckDao) {
		this.documentCheckDao = documentCheckDao;
	}

	public PmDayKpiDao getPmDayKpiDao() {
		return pmDayKpiDao;
	}

	public void setPmDayKpiDao(PmDayKpiDao pmDayKpiDao) {
		this.pmDayKpiDao = pmDayKpiDao;
	}

	public PmBaseDao getPmBaseDao() {
		return pmBaseDao;
	}

	public void setPmBaseDao(PmBaseDao pmBaseDao) {
		this.pmBaseDao = pmBaseDao;
	}

	/**
	 * 
	 * <p>
	 * Title: 上报进度日报
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param parameters
	 * @return
	 * 
	 * @see com.glens.eap.platform.framework.service.impl.EAPAbstractService#insert(java.lang.Object)
	 **/

	@Override
	public boolean insert(Object parameters) {

		PmDayWordloadVo vo = (PmDayWordloadVo) parameters;
		/* 日报指标项数据入库 */
		String kpiData = vo.getKpiData();
		List<PmDayKpi> kpiList = new ArrayList<PmDayKpi>();
		if (kpiData != null) {
			String[] kpi_arr = kpiData.split("&");
			for (int i = 0; i < kpi_arr.length; i++) {
				String kpi = kpi_arr[i];
				if (kpi.indexOf("=") == -1)
					continue;
				String[] kpi_item = kpi.split("=");
				PmDayKpi kpiVo = new PmDayKpi();
				kpiVo.setCompanyCode(vo.getCompanyCode());
				kpiVo.setProNo(vo.getProNo());
				kpiVo.setProName(vo.getProName());
				kpiVo.setReportDate(vo.getReportDate());
				kpiVo.setKpiCode(kpi_item[0]);
				if (kpi_item[1].indexOf(",") != -1) {
					String[] vals = kpi_item[1].split(",");
					Float val1 = 0f;
					try {
						val1 = Float.parseFloat(vals[0]);
					} catch (NumberFormatException e) {
					}
					kpiVo.setKpiValue(val1);
					// 附属值
					Float val2 = 0f;
					try {
						val2 = Float.parseFloat(vals[1]);
					} catch (NumberFormatException e) {
					}
					// kpiVo.setAttachedValue(val2);// 附属值？
					kpiVo.setStandardHours(val2);
				} else {
					Float val = 0f;
					try {
						val = Float.parseFloat(kpi_item[1]);
					} catch (NumberFormatException e) {
					}
					kpiVo.setKpiValue(val);
				}
				kpiList.add(kpiVo);
			}
		}
		if (kpiList.size() > 0)
			pmDayKpiDao.batchInsert(kpiList);
		/* 日报指标项数据入库 END */

		PmDayWordloadDao dayWordloadDao = (PmDayWordloadDao) getDao();
		PmDayWordloadVo proVo = dayWordloadDao.selectProByNo(vo);
		int formulaType = dayWordloadDao.selectFormulaType(vo.getProNo());
		if (proVo != null) {
			if (proVo.getTotalWordload() > 0) {
				float planProgress = (vo.getPlanIwWordload()
						* proVo.getIwProportion() + vo.getPlanOwWordload()
						* proVo.getOwProportion())
						/ proVo.getTotalWordload();
				vo.setPlanProgress(planProgress);
			}
			float planWordload = vo.getPlanIwWordload()
					* proVo.getIwProportion() + vo.getPlanOwWordload()
					* proVo.getOwProportion();
			vo.setPlanWordload(planWordload);
			PmDayWordloadVo lastDayVo = dayWordloadDao
					.selectDayWordloadByNo(vo);
			if (lastDayVo != null) {

				vo.setClonePlanIwWordload(lastDayVo.getPlanIwWordload());
				vo.setClonePlanOwWordload(lastDayVo.getPlanOwWordload());
				vo.setClonePlanProgress(lastDayVo.getPlanProgress());
				vo.setClonePlanWordload(lastDayVo.getPlanWordload());

				Float lastDayAllWorkHours = 0f;
				if (lastDayVo != null && lastDayVo.getAllWorkHours() != null) {
					lastDayAllWorkHours = lastDayVo.getAllWorkHours();
				}
				vo.setAllWorkHours(vo.getWorkHours() + lastDayAllWorkHours);

				if ("1".equals(proVo.getOwSumType())) {
					// A/B/C
					Float c = vo.getActualOwCard() / 40.0f;
					if (proVo.getProPhase() == 3) {
						c = vo.getActualOwCard() / 25.0f;
					}
					Float ow = vo.getActualOwCable() + vo.getActualOwLocate()
							+ c;
					vo.setRemainOwWordload(vo.getClonePlanOwWordload() - ow);
					vo.setRemainIwWordload(vo.getClonePlanIwWordload()
							- vo.getActualIwWordload());
				} else if ("2".equals(proVo.getOwSumType())) {
					// A/C
					Float c = vo.getActualOwCard() / 40.0f;
					if (proVo.getProPhase() == 3) {
						c = vo.getActualOwCard() / 25.0f;
					}
					Float ow = vo.getActualOwCable() + c;
					vo.setRemainOwWordload(vo.getClonePlanOwWordload() - ow);
					vo.setRemainIwWordload(vo.getClonePlanIwWordload()
							- vo.getActualIwWordload());
				} else {
					vo.setRemainOwWordload(vo.getClonePlanOwWordload()
							- vo.getActualOwWordload());
					vo.setRemainIwWordload(vo.getClonePlanIwWordload()
							- vo.getActualIwWordload());
				}
			} else {

				vo.setClonePlanIwWordload(vo.getActualIwWordload());
				vo.setClonePlanOwWordload(vo.getActualOwWordload());
				if (proVo.getTotalWordload() > 0) {
					float clonePlanProgress = (vo.getClonePlanIwWordload()
							* proVo.getIwProportion() + vo
							.getClonePlanOwWordload() * proVo.getOwProportion())
							/ proVo.getTotalWordload();
					vo.setClonePlanProgress(clonePlanProgress);
				}
				float clonePlanWordload = vo.getClonePlanIwWordload()
						* proVo.getIwProportion() + vo.getClonePlanOwWordload()
						* proVo.getOwProportion();
				vo.setClonePlanWordload(clonePlanWordload);
				vo.setRemainIwWordload(0f);
				vo.setRemainOwWordload(0f);
			}

			float dayActualLoad = vo.getActualIwWordload()
					* proVo.getIwProportion() + vo.getActualOwWordload()
					* proVo.getIwProportion();
			vo.setDayAccumulativeProgress(dayActualLoad);// 注意：日进度与日工作量写反了
			if (proVo.getTotalWordload() > 0) {
				vo.setDayAccumulativeWordload(getWordloadByFormaluType(
						formulaType, vo, proVo.getTotalWordload()));// 注意：日进度与日工作量写反了
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("proNo", vo.getProNo());
			params.put("workDate", vo.getReportDate());
			float allAccumulativeWordload = dayWordloadDao
					.getAllAccumulativeWordload(params);
			if (proVo.getTotalWordload() > 0) {
				vo.setAllAccumulativeWordload(allAccumulativeWordload
						+ vo.getDayAccumulativeProgress());// 今天前的历史累积工作量+今天的工作量
				vo.setAllAccumulativeProgress(vo.getAllAccumulativeWordload()
						/ proVo.getTotalWordload());
			}
		}

		return super.insert(vo);
	}

	@Override
	public int update(Object parameters) {

		PmDayWordloadVo vo = (PmDayWordloadVo) parameters;
		/* 日报指标项数据入库 */
		String kpiData = vo.getKpiData();
		List<PmDayKpi> kpiList = new ArrayList<PmDayKpi>();
		if (kpiData != null) {
			String[] kpi_arr = kpiData.split("&");
			for (int i = 0; i < kpi_arr.length; i++) {
				String kpi = kpi_arr[i];
				if (kpi.indexOf("=") == -1)
					continue;
				String[] kpi_item = kpi.split("=");
				PmDayKpi kpiVo = new PmDayKpi();
				kpiVo.setCompanyCode(vo.getCompanyCode());
				kpiVo.setProNo(vo.getProNo());
				kpiVo.setProName(vo.getProName());
				kpiVo.setReportDate(vo.getReportDate());
				kpiVo.setKpiCode(kpi_item[0]);
				if (kpi_item[1].indexOf(",") != -1) {
					String[] vals = kpi_item[1].split(",");
					Float val1 = 0f;
					try {
						val1 = Float.parseFloat(vals[0]);
					} catch (NumberFormatException e) {
					}
					kpiVo.setKpiValue(val1);
					// 附属值
					Float val2 = 0f;
					try {
						val2 = Float.parseFloat(vals[1]);
					} catch (NumberFormatException e) {
					}
					// kpiVo.setAttachedValue(val2); // 附属值？
					kpiVo.setStandardHours(val2);// 标准工时
				} else {
					Float val = 0f;
					try {
						val = Float.parseFloat(kpi_item[1]);
					} catch (NumberFormatException e) {
					}
					kpiVo.setKpiValue(val);
				}
				kpiList.add(kpiVo);
			}
		}
		if (kpiList.size() > 0) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("proNo", vo.getProNo());
			params.put("reportDate", vo.getReportDate());
			pmDayKpiDao.deleteByProNoAndDate(params);
			pmDayKpiDao.batchInsert(kpiList);
		}
		/* 日报指标项数据入库 END */

		PmDayWordloadDao dayWordloadDao = (PmDayWordloadDao) getDao();
		PmDayWordloadVo proVo = dayWordloadDao.selectProByNo(vo);
		int formulaType = dayWordloadDao.selectFormulaType(vo.getProNo());
		if (proVo != null) {
			if (proVo.getTotalWordload() > 0) {
				float planProgress = (vo.getPlanIwWordload()
						* proVo.getIwProportion() + vo.getPlanOwWordload()
						* proVo.getOwProportion())
						/ proVo.getTotalWordload();
				vo.setPlanProgress(planProgress);
			}
			float planWordload = vo.getPlanIwWordload()
					* proVo.getIwProportion() + vo.getPlanOwWordload()
					* proVo.getOwProportion();
			vo.setPlanWordload(planWordload);
			PmDayWordloadVo lastDayVo = dayWordloadDao
					.selectDayWordloadByNo(vo);
			if (lastDayVo != null) {

				vo.setClonePlanIwWordload(lastDayVo.getPlanIwWordload());
				vo.setClonePlanOwWordload(lastDayVo.getPlanOwWordload());
				vo.setClonePlanProgress(lastDayVo.getPlanProgress());
				vo.setClonePlanWordload(lastDayVo.getPlanWordload());

				if ("1".equals(proVo.getOwSumType())) {
					// A/B/C
					Float c = vo.getActualOwCard() / 40.0f;
					if (proVo.getProPhase() == 3) {
						c = vo.getActualOwCard() / 25.0f;
					}
					Float ow = vo.getActualOwCable() + vo.getActualOwLocate()
							+ c;
					vo.setRemainOwWordload(vo.getClonePlanOwWordload() - ow);
					vo.setRemainIwWordload(vo.getClonePlanIwWordload()
							- vo.getActualIwWordload());
				} else if ("2".equals(proVo.getOwSumType())) {
					// A/C
					Float c = vo.getActualOwCard() / 40.0f;
					if (proVo.getProPhase() == 3) {
						c = vo.getActualOwCard() / 25.0f;
					}
					Float ow = vo.getActualOwCable() + c;
					vo.setRemainOwWordload(vo.getClonePlanOwWordload() - ow);
					vo.setRemainIwWordload(vo.getClonePlanIwWordload()
							- vo.getActualIwWordload());
				} else {
					vo.setRemainOwWordload(vo.getClonePlanOwWordload()
							- vo.getActualOwWordload());
					vo.setRemainIwWordload(vo.getClonePlanIwWordload()
							- vo.getActualIwWordload());
				}
			} else {

				vo.setClonePlanIwWordload(vo.getActualIwWordload());
				vo.setClonePlanOwWordload(vo.getActualOwWordload());
				if (proVo.getTotalWordload() > 0) {
					float clonePlanProgress = (vo.getClonePlanIwWordload()
							* proVo.getIwProportion() + vo
							.getClonePlanOwWordload() * proVo.getOwProportion())
							/ proVo.getTotalWordload();
					vo.setClonePlanProgress(clonePlanProgress);
				}
				float clonePlanWordload = vo.getClonePlanIwWordload()
						* proVo.getIwProportion() + vo.getClonePlanOwWordload()
						* proVo.getOwProportion();
				vo.setClonePlanWordload(clonePlanWordload);
				vo.setRemainIwWordload(0f);
				vo.setRemainOwWordload(0f);
			}

			float dayActualLoad = vo.getActualIwWordload()
					* proVo.getIwProportion() + vo.getActualOwWordload()
					* proVo.getOwProportion();
			vo.setDayAccumulativeProgress(dayActualLoad);// 注意：日进度与日工作量写反了
			if (proVo.getTotalWordload() > 0) {
				vo.setDayAccumulativeWordload(getWordloadByFormaluType(
						formulaType, vo, proVo.getTotalWordload()));// 注意：日进度与日工作量写反了
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("proNo", vo.getProNo());
			params.put("workDate", vo.getReportDate());
			float allAccumulativeWordload = dayWordloadDao
					.getAllAccumulativeWordload(params);
			if (proVo.getTotalWordload() > 0) {
				vo.setAllAccumulativeWordload(allAccumulativeWordload
						+ vo.getDayAccumulativeProgress());// 今天前的历史累积工作量+今天的工作量
				vo.setAllAccumulativeProgress(vo.getAllAccumulativeWordload()
						/ proVo.getTotalWordload());
			}
		}

		return super.update(vo);
	}

	public List<PmDayWordloadVo> getDayWordload(Map<String, String> params) {

		List<PmDayWordloadVo> resultList = new ArrayList<PmDayWordloadVo>();
		PmDayWordloadDao dayWordloadDao = (PmDayWordloadDao) getDao();
		String fromDate = params.get("fromDate");
		String toDate = params.get("toDate");
		PmBaseVo pmBaseVo = (PmBaseVo) pmBaseDao.findById(params);
		List<PmDayWordloadVo> list = dayWordloadDao
				.selectPmDayWordload2(params);

		List<Map<String, String>> dates;
		try {
			dates = DateTimeUtil
					.getDates(DateTimeUtil.getDateFormat(fromDate,
							DateTimeUtil.FORMAT_2), DateTimeUtil.getDateFormat(
							toDate, DateTimeUtil.FORMAT_2));
			if (dates != null && dates.size() > 0) {

				for (Map<String, String> dateItem : dates) {

					String date = dateItem.get("simpleDate");
					boolean flag = false;
					for (Iterator iterator = list.iterator(); iterator
							.hasNext();) {
						PmDayWordloadVo vo = (PmDayWordloadVo) iterator.next();
						if (date.equals(vo.getReportDate())) {
							resultList.add(vo);
							flag = true;
						}
					}
					if (!flag) {
						PmDayWordloadVo vo = new PmDayWordloadVo();
						vo.setReportDate(date);
						vo.setProNo(pmBaseVo.getProNo());
						vo.setProCode(pmBaseVo.getProCode());
						vo.setProName(pmBaseVo.getProName());
						vo.setProManager(pmBaseVo.getProManager());
						vo.setReportStatus("0");
						resultList.add(vo);
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return resultList;
	}

	public PmDayWordloadVo getLastDayWordload(PmDayWordloadVo vo) {

		PmDayWordloadDao dayWordloadDao = (PmDayWordloadDao) getDao();
		PmDayWordloadVo lastDayWordload = dayWordloadDao
				.selectDayWordloadByNo(vo);
		if (lastDayWordload == null)
			lastDayWordload = new PmDayWordloadVo();
		return lastDayWordload;
	}

	public List<PmDayWordloadVo> selectDanWordloadByDate(
			Map<String, String> params) {

		PmDayWordloadDao dayWordloadDao = (PmDayWordloadDao) getDao();
		List<PmDayWordloadVo> res = dayWordloadDao
				.selectDanWordloadByDate(params);
		return res;
	}

	private float getWordloadByFormaluType(int formaluType, PmDayWordloadVo vo,
			int totalWordload) {

		float wordload = 0.0f;
		if (formaluType == 1) {

			wordload = (vo.getActualIwWordload() * 1.0f) / totalWordload;
		} else if (formaluType == 2) {

			wordload = vo.getDayAccumulativeProgress() / totalWordload;
		} else if (formaluType == 3) {

			wordload = (vo.getActualOwWordload() * 1.0f) / totalWordload;
		}

		return wordload;
	}

	public PmDayWordloadVo findByRowid(Long rowid) {
		PmDayWordloadDao dayWordloadDao = (PmDayWordloadDao) getDao();
		return dayWordloadDao.findByRowid(rowid);
	}

	public Object getProWorkLoad(Map<String, Object> pm) {
		// TODO Auto-generated method stub
		PmDayWordloadDao dayWordloadDao = (PmDayWordloadDao) getDao();
		return dayWordloadDao.getProWorkLoad(pm);
	}
}
