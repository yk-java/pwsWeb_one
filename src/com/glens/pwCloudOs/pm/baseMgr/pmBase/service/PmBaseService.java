/**
 * @Title: PmBaseService.java
 * @Package com.glens.pwCloudOs.pm.baseMgr.pmBase.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 上午10:45:54
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.baseMgr.pmBase.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.sys.orgEmployee.account.dao.PfAccountDao;
import com.glens.eap.sys.orgEmployee.account.vo.PfAccount;
import com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.dao.CpCommuteConfigDao;
import com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.vo.CpCommuteConfig;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.dao.PmBaseDao;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmBaseVo;
import com.glens.pwCloudOs.pm.plan.dao.PmPlanKpiDao;
import com.glens.pwCloudOs.pm.plan.vo.PmPlanKpi;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

public class PmBaseService extends EAPAbstractService {
	PfAccountDao accountDao;
	PmPlanKpiDao pmPlanKpiDao;

	public PmPlanKpiDao getPmPlanKpiDao() {
		return pmPlanKpiDao;
	}

	public void setPmPlanKpiDao(PmPlanKpiDao pmPlanKpiDao) {
		this.pmPlanKpiDao = pmPlanKpiDao;
	}

	public PfAccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(PfAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public ValueObject findByProCode(Object parameters) {
		PmBaseDao pmBaseDao = (PmBaseDao) this.dao;
		return pmBaseDao.findByProCode(parameters);
	}

	CpCommuteConfigDao cpCommuteConfigDao;

	public CpCommuteConfigDao getCpCommuteConfigDao() {
		return cpCommuteConfigDao;
	}

	public void setCpCommuteConfigDao(CpCommuteConfigDao cpCommuteConfigDao) {
		this.cpCommuteConfigDao = cpCommuteConfigDao;
	}

	@Override
	@Transactional
	public boolean insert(Object o) {
		PmBaseVo vo = (PmBaseVo) o;
		// KPI TotalWordload START
		String kpiData = vo.getKpiData();
		List<PmPlanKpi> kpiList = new ArrayList<PmPlanKpi>();
		if (kpiData != null) {
			String[] kpi_arr = kpiData.split("&");
			for (int i = 0; i < kpi_arr.length; i++) {
				String kpi = kpi_arr[i];
				if (kpi.indexOf("=") == -1)
					continue;
				String[] kpi_item = kpi.split("=");
				PmPlanKpi kpiVo = new PmPlanKpi();
				kpiVo.setCompanyCode(vo.getCompanyCode());
				kpiVo.setProNo(vo.getProNo());
				kpiVo.setProName(vo.getProName());
				kpiVo.setPlanNo("-1");
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
			pmPlanKpiDao.batchInsert(kpiList);
		// KPI LIST END
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("employeecode", vo.getEmployeecode());
		PfAccount account = accountDao.selectAccountByEmployeecode(params);
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		if (account != null) {
			vo.setAccountCode(account.getAccountCode());
		}
		CpCommuteConfig cfg = new CpCommuteConfig();
		cfg.setCompanyCode(vo.getCompanyCode());
		cfg.setProNo(vo.getProNo());
		cfg.setCheckinTime("08:30:00");
		cfg.setCheckoutTime("17:30:00");
		cfg.setCheckinExMinites(150);
		cfg.setCheckoutExMinites(150);
		cfg.setCheckPointTotal(400);

		cpCommuteConfigDao.insert(cfg);
		return pmDao.insert(vo);
	}

	@Override
	public int update(Object parameters) {
		PmBaseVo vo = (PmBaseVo) parameters;

		// KPI TotalWordload START
		// Map params = new HashMap();
		// params.put("proNo", vo.getProNo());
		// params.put("planNo", "-1");
		// pmPlanKpiDao.deleteByProNoAndPlanNo(params);
		// String kpiData = vo.getKpiData();
		// List<PmPlanKpi> kpiList = new ArrayList<PmPlanKpi>();
		// if (kpiData != null) {
		// String[] kpi_arr = kpiData.split("&");
		// for (int i = 0; i < kpi_arr.length; i++) {
		// String kpi = kpi_arr[i];
		// if (kpi.indexOf("=") == -1)
		// continue;
		// String[] kpi_item = kpi.split("=");
		// PmPlanKpi kpiVo = new PmPlanKpi();
		// kpiVo.setCompanyCode(vo.getCompanyCode());
		// kpiVo.setProNo(vo.getProNo());
		// kpiVo.setProName(vo.getProName());
		// kpiVo.setPlanNo("-1");
		// kpiVo.setKpiCode(kpi_item[0]);
		// if (kpi_item[1].indexOf(",") != -1 &&
		// kpi_item[1].split(",").length>0) {
		// String[] vals = kpi_item[1].split(",");
		// Float val1 = 0f;
		// try {
		// val1 = Float.parseFloat(vals[0]);
		// } catch (Exception e) {
		// }
		// kpiVo.setKpiValue(val1);
		// // 附属值
		// Float val2 = 0f;
		// try {
		// val2 = Float.parseFloat(vals[1]);
		// } catch (Exception e) {
		// }
		// // kpiVo.setAttachedValue(val2);// 附属值？
		// kpiVo.setStandardHours(val2);
		// } else {
		// Float val = 0f;
		// try {
		// val = Float.parseFloat(kpi_item[1]);
		// } catch (Exception e) {
		// }
		// kpiVo.setKpiValue(val);
		// }
		// kpiList.add(kpiVo);
		// }
		// }
		// if (kpiList.size() > 0)
		// pmPlanKpiDao.batchInsert(kpiList);
		// KPI LIST END
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("employeecode", vo.getEmployeecode());
		PfAccount account = accountDao.selectAccountByEmployeecode(params2);
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		if (account != null) {
			vo.setAccountCode(account.getAccountCode());
		}
		return super.update(vo);
	}

	public int active(ValueObject vo) {

		PmBaseDao pmDao = (PmBaseDao) this.dao;

		return pmDao.active(vo);
	}

	public int close(ValueObject vo) {

		PmBaseDao pmDao = (PmBaseDao) this.dao;

		return pmDao.close(vo);
	}

	public int insertProMember(Map<String, String> member) {

		PmBaseDao pmDao = (PmBaseDao) this.dao;

		return pmDao.insertProMember(member);
	}

	public int batchAddProMember(Map<String, String> member) {

		// (#{item.companyCode},#{item.proNo},#{item.employeeCode},#{item.accountCode},#{item.inDate},1)

		String employeeCodes = member.get("employeeCodes");
		String accountCodes = member.get("accountCodes");
		member.remove("employeeCodes");
		member.remove("accountCodes");

		String[] empCodes_arr = employeeCodes.split(",");
		// String[] accCodes_arr = accountCodes.split(",");
		List<Map<String, Object>> members = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < empCodes_arr.length; i++) {
			String empCode = empCodes_arr[i];
			// String accCode = accCodes_arr[i];
			Map<String, Object> item = new HashMap<String, Object>();
			item.putAll(member);
			item.put("employeeCode", empCode);
			// item.put("accountCode", accCode);
			members.add(item);
		}
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		return pmDao.batchAddProMember(members);
	}

	public int deleteProMember(String companyCode, String proNo,
			String employeeCode) {

		PmBaseDao pmDao = (PmBaseDao) this.dao;
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyCode", companyCode);
		params.put("proNo", proNo);
		params.put("employeeCode", employeeCode);

		return pmDao.deleteProMember(params);
	}

	public int logoffProMember(String companyCode, String proNo,
			String employeeCode) {

		PmBaseDao pmDao = (PmBaseDao) this.dao;
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyCode", companyCode);
		params.put("proNo", proNo);
		params.put("employeeCode", employeeCode);

		return pmDao.logoffProMember(params);
	}

	public List<Map<String, Object>> selectProMember(String companyCode,
			String proNo, String workStatue) {

		PmBaseDao pmDao = (PmBaseDao) this.dao;
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyCode", companyCode);
		params.put("proNo", proNo);
		params.put("workStatue", workStatue);
		List<Map<String, Object>> memberList = pmDao.selectProMember(params);
		if (memberList == null)
			memberList = new ArrayList<Map<String, Object>>();
		PmBaseVo proVo = (PmBaseVo) pmDao.findById(proNo);
		if (proVo != null) {

			Map<String, Object> manager = new HashMap<String, Object>();
			manager.put("employeeCode", proVo.getEmployeecode());
			manager.put("employeeName", proVo.getProManager());
			manager.put("proNo", proVo.getProNo());
			manager.put("proName", proVo.getProName());
			manager.put("inDate", proVo.getSdate());
			manager.put("outDate", "");
			manager.put("workStatue", 1);
			manager.put("isProManager", 1);

			memberList.add(0, manager);
		}

		return pmDao.selectProMember(params);
	}

	public List<Map<String, String>> getFileType(Object params) {

		PmBaseDao pmDao = (PmBaseDao) this.dao;
		// Map<String, String> params = new HashMap<String, String>();

		List<Map<String, String>> memberList = pmDao.getFileType(params);
		// if (memberList == null) memberList = new
		// ArrayList<Map<String,Object>>();

		return memberList;
	}

	public List getemplist(Object params) {

		PmBaseDao pmDao = (PmBaseDao) this.dao;
		// Map<String, String> params = new HashMap<String, String>();

		List memberList = pmDao.getemplist(params);
		// if (memberList == null) memberList = new
		// ArrayList<Map<String,Object>>();

		return memberList;
	}

	public List getaccount(Object params) {

		PmBaseDao pmDao = (PmBaseDao) this.dao;
		// Map<String, String> params = new HashMap<String, String>();

		List memberList = pmDao.getaccount(params);
		// if (memberList == null) memberList = new
		// ArrayList<Map<String,Object>>();

		return memberList;
	}

	public Float getProTimeProgress(String proNo, String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Float progress = 0f;
			PmBaseDao pmDao = (PmBaseDao) this.dao;
			PmBaseVo pm = (PmBaseVo) pmDao.findByProCode(proNo);
			String sdate = pm.getSdate();
			String edate = pm.getEdate();
			if (sdate != null && sdate.length() > 0 && edate != null
					&& edate.length() > 0) {
				try {
					Date sdate_d = sdf.parse(sdate);
					Date edate_d = sdf.parse(edate);
					Date curDate = sdf.parse(sdf.format(new Date()));
					if (date != null && date.length() > 0)
						curDate = sdf.parse(date);
					if (curDate.getTime() < edate_d.getTime()) {
						long pmTime = edate_d.getTime() - sdate_d.getTime();
						long curTime = curDate.getTime() - sdate_d.getTime();
						progress = curTime * 1.0f / pmTime * 1.0f;
						BigDecimal bg = new BigDecimal(progress);
						progress = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.floatValue();
					} else {
						progress = 1.0f;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			return progress;
		} catch (Exception e) {
			e.printStackTrace();
			return 0f;
		}
	}

	public List queryList() {
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		return pmDao.queryList();
	}

	public List queryEmployeeProList(String jobNo) {
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		return pmDao.queryEmployeeProList(jobNo);
	}

	public List queryAllProList() {
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		return pmDao.queryAllProList();
	}

	public Map getpro(String rowid) {
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		return pmDao.getpro(rowid);
	}

	public PageBean queryProCostForPage(Map<String, Object> paramsMap) {
		return this.dao.queryForPage(paramsMap, "queryProCostCount",
				"queryProCostForPage");
	}

	public PageBean getDialogPros(Map<String, Object> paramsMap,
			int currentPage, int perpage) {
		PmBaseDao pmDao = (PmBaseDao) this.dao;

		return pmDao.getDialogPros(paramsMap, currentPage, perpage);
	}

	public PageBean queryProCostForPage1(Map<String, Object> paramsMap) {
		return this.dao.queryForPage(paramsMap, "queryProCostCount",
				"queryProCostForPage1");
	}

	public List queryFeeTypeCostList(Map<String, Object> paramsMap) {
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		return pmDao.queryFeeTypeCostList(paramsMap);
	}

	public Map getProCost(Map<String, Object> paramsMap) {
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		return pmDao.getProCost(paramsMap);
	}

	public Map queryBudgetCost(Map<String, Object> paramsMap) {
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		return pmDao.queryBudgetCost(paramsMap);
	}

	public List<Map<String, Object>> queryAllFeeItem() {
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		return pmDao.queryAllFeeItem();
	}

	public List queryFeeItemsCostList(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		return pmDao.queryFeeItemsCostList(paramsMap);
	}

	public List<Map<String, Object>> queryProCostForList(
			Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		return pmDao.queryProCostForList(paramsMap);
	}

	public PageBean queryProPeopleCostForPage(Map<String, Object> paramsMap) {
		return this.dao.queryForPage(paramsMap, "queryProPeopleCostCount",
				"queryProPeopleCostForPage");
	}

	public List<Map<String, Object>> queryProPeopleCostForList(
			Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		PmBaseDao pmDao = (PmBaseDao) this.dao;
		return pmDao.queryProPeopleCostForList(paramsMap);
	}

	public PageBean queryWaitingList(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return this.dao.queryForPage(paramsMap, "queryWaitingListCount",
				"queryWaitingListForPage");
	}

	public PageBean queryProPreWarningList(Map paramsMap) {
		// TODO Auto-generated method stub
		return this.dao.queryForPage(paramsMap, "queryProPreWarningListCount",
				"queryProPreWarningListForPage");
	}
}
