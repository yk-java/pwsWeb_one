package com.glens.pwCloudOs.OA.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.utils.HttpUtil;
import com.glens.eap.sys.orgEmployee.employee.dao.MdEmployeeDao;
import com.glens.eap.sys.orgEmployee.employee.vo.MdEmployee;
import com.glens.eap.sys.orgEmployee.orgunit.dao.OrgUnitDao;
import com.glens.eap.sys.orgEmployee.orgunit.vo.OrgUnit;
import com.glens.pwCloudOs.OA.vo.ExpensesResult;
import com.glens.pwCloudOs.OA.vo.OaEmployee;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.fm.expense.dao.FmMoneyReturnDao;
import com.glens.pwCloudOs.fm.expense.dao.FmMoneyReturnDetailDao;
import com.glens.pwCloudOs.fm.expense.vo.FmMoneyReturn;
import com.glens.pwCloudOs.fm.expense.vo.FmMoneyReturnDetail;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.dao.PmBaseDao;

@Service("oaService")
public class OaService {

	@Autowired
	private MdEmployeeDao mdEmployeeDao;

	@Autowired
	private PWCloudOsConfig pWCloudOsConfig;

	@Autowired
	private FmMoneyReturnDao fmMoneyReturnDao;

	@Autowired
	private PmBaseDao pmBaseDao;

	@Autowired
	private OrgUnitDao orgUnitDao;

	@Autowired
	private FmMoneyReturnDetailDao fmMoneyReturnDetailDao;

	private static final String APP_CODE = "793627e6c2074ea8b26de5f62117c1f7";

	/**
	 * 推送人员至OA
	 * 
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class })
	public void pushEmployee() throws Exception {
		System.out.println("-----------推送人员至OA-------------");
		List<Map> employeeList = mdEmployeeDao.queryEmployeeForOAList();
		int count = 0;
		for (Map me : employeeList) {
			System.out.println("共:" + employeeList.size() + ",正在处理:" + count++);
			OaEmployee oe = new OaEmployee();

			// 银行帐号
			oe.setBankAccount(me.get("BANK_ACCOUNT") == null ? "" : me
					.get("BANK_ACCOUNT") + "");

			// 出生日期
			oe.setBirthday(me.get("BIRTHDAY") == null ? "" : me.get("BIRTHDAY")
					+ "");

			// 编号
			oe.setEmployeeCode(me.get("EMPLOYEECODE") == null ? "" : me
					.get("EMPLOYEECODE") + "");

			// 名称
			oe.setEmployeeName(me.get("EMPLOYEENAME") == null ? "" : me
					.get("EMPLOYEENAME") + "");

			// 身份证
			oe.setIdCard(me.get("IDCARD") == null ? "" : me.get("IDCARD") + "");

			// 职位
			oe.setJob(me.get("JOB_NAME") == null ? "" : me.get("JOB_NAME") + "");

			// 工号
			oe.setJobNo(me.get("JOB_NO") == null ? "" : me.get("JOB_NO") + "");
			oe.setJobProperty(me.get("JOB_PROPERTY_NAME") == null ? "" : me
					.get("JOB_PROPERTY_NAME") + "");
			oe.setLeaderCode(me.get("LEADER_CODE") == null ? "" : getJObNo(me
					.get("LEADER_CODE") + ""));
			oe.setLeaderName(me.get("LEADER_NAME") == null ? "" : me
					.get("LEADER_NAME") + "");
			oe.setWorkStatus(me.get("WORK_STATUS") == null ? "" : me
					.get("WORK_STATUS") + "");
			if (!StringUtils.isEmpty(me.get("MARITAL"))) {
				oe.setMarital(getMarital(Integer.parseInt(me.get("MARITAL")
						+ "")));
			} else {
				oe.setMarital("");
			}

			oe.setMobile(me.get("MOBILEPHONE1") == null ? "" : me
					.get("MOBILEPHONE1") + "");
			oe.setNativePlace(me.get("NATIVE_PLACE") == null ? "" : me
					.get("NATIVE_PLACE") + "");
			if (!StringUtils.isEmpty(me.get("SEX"))) {
				oe.setSex(getSex(me.get("SEX") + ""));
			} else {
				oe.setSex("");
			}

			oe.setUnitName(me.get("UNIT_NAME") == null ? "" : me
					.get("UNIT_NAME") + "");
			// 所属项目
			oe.setProject(getPro(me.get("JOB_NO") + ""));

			// 紧急联系人
			oe.setEmergencyContractorName(me.get("EMERGENCY_CONTACTOR_NAME") == null ? ""
					: me.get("EMERGENCY_CONTACTOR_NAME") + "");

			// 紧急联系人电话 EMERGENCY_CONTACTOR_PHONE
			oe.setEmergencyContractorPhone(me.get("EMERGENCY_CONTACTOR_PHONE") == null ? ""
					: me.get("EMERGENCY_CONTACTOR_PHONE") + "");

			// 合同性质
			oe.setContractPropertyCode(me.get("CONTRACT_PROPERTY_CODE") == null ? ""
					: me.get("CONTRACT_PROPERTY_CODE") + "");

			oe.setContractPropertyName(me.get("CONTRACT_PROPERTY_NAME") == null ? ""
					: me.get("CONTRACT_PROPERTY_NAME") + "");

			// 合同开始时间

			oe.setContractStartDate(me.get("CONTRACT_DATE_S") == null ? "" : me
					.get("CONTRACT_DATE_S") + "");
			// 合同结束时间

			oe.setContractEndDate(me.get("CONTRACT_DATE_E") == null ? "" : me
					.get("CONTRACT_DATE_E") + "");

			String jsonStr = JSON.toJSONString(oe);
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("employee", jsonStr);
			paramsMap.put("appCode", APP_CODE);
			String result = HttpUtil.executeHttpsPost(
					pWCloudOsConfig.getPushEmployeeToOAUrl(), paramsMap,
					"UTF-8");
			System.out.println(result);
		}
	}

	private String getJObNo(String employeeCode) {
		MdEmployee me = mdEmployeeDao.queryEmployeeByCode(employeeCode);
		if (me != null)
			return me.getJobNo();
		return "";
	}

	private String getEmployeeName(String employeeCode) {
		MdEmployee me = mdEmployeeDao.queryEmployeeByCode(employeeCode);
		if (me != null)
			return me.getEmployeename();
		return "";
	}

	/**
	 * 获取财务报销数据
	 * 
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class })
	public void getExpenses() throws Exception {
		System.out.println("---------获取财务数据-----------");
		String queryDate = "";
		try {
			queryDate = DateTimeUtil.formatDate(new Date(),
					DateTimeUtil.FORMAT_2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		// "2017-01-03"
		map.put("queryDate", queryDate);
		map.put("appCode", APP_CODE);
		String result = HttpUtil.executeHttpsPost(
				pWCloudOsConfig.getGetExpensesFromOAUrl(), map, "UTF-8");
		System.out.println(result);
		ExpensesResult expensesResult = JSON.parseObject(result,
				ExpensesResult.class);
		if (expensesResult != null
				&& expensesResult.getStatusCode().equals("200")) {
			List<FmMoneyReturn> returnList = expensesResult.getData();
			for (FmMoneyReturn r : returnList) {
				System.out.println(r);
				// 查询流程编号是否存在，存在，则更新
				List<FmMoneyReturn> workFlowList = fmMoneyReturnDao
						.queryFmMoneyReturnList(r.getWorkflowCode());
				if (workFlowList.size() > 0) {
					FmMoneyReturn re = workFlowList.get(0);
					r.setRowid(re.getRowid());
					r.setSysUpdateTime(new Date());
					fmMoneyReturnDao.updateSelective1(r);
					fmMoneyReturnDetailDao.deleteFmReturnDetail(r.getRowid());
					List<FmMoneyReturnDetail> detailList = r.getData();
					for (FmMoneyReturnDetail d : detailList) {
						d.setFeeId(r.getRowid());
						d.setSysProcessFlag("1");
						d.setSysCreateTime(new Date());
						fmMoneyReturnDetailDao.insertSelective(d);
					}
				} else {
					r.setSysProcessFlag("1");
					r.setRealMoney(r.getTotalMoney());
					Map<String, Object> m = mdEmployeeDao
							.queryEmployeeByJObNo(r.getJobNo());
					if (m != null) {
						if (m.containsKey("EMPLOYEENAME")
								&& m.get("EMPLOYEENAME") != null) {
							r.setEmployeeName(m.get("EMPLOYEENAME") + "");
						}
						if (m.containsKey("BANK_ACCOUNT")
								&& m.get("BANK_ACCOUNT") != null) {
							r.setBankAccount(m.get("BANK_ACCOUNT") + "");
						}
						if (m.containsKey("UNIT_CODE")
								&& m.get("UNIT_CODE") != null) {
							r.setUnitCode(m.get("UNIT_CODE") + "");
						}
						if (m.containsKey("UNIT_NAME")
								&& m.get("UNIT_NAME") != null) {
							r.setUnitName(m.get("UNIT_NAME") + "");
						}
					}
					r.setStatus("0");
					r.setSysCreateTime(new Date());
					fmMoneyReturnDao.insertSelective(r);
					List<FmMoneyReturnDetail> detailList = r.getData();
					for (FmMoneyReturnDetail d : detailList) {
						d.setFeeId(r.getRowid());
						d.setSysCreateTime(new Date());
						d.setSysProcessFlag("1");
						fmMoneyReturnDetailDao.insertSelective(d);
					}
				}

			}
		}

		System.out.println("----------获取数据ok-------------");
	}

	/**
	 * 项目更新
	 * 
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class })
	public void projectUpdate() throws Exception {
		System.out.println("-------项目更新------------");
		List<Map<String, Object>> list = pmBaseDao.queryPmBaseForOAList();
		int count = 0;
		for (Map m : list) {
			JSONObject json = new JSONObject();
			json.put("proNo", m.get("proNo") == null ? "" : m.get("proNo"));
			json.put("proCode",
					m.get("proCode") == null ? "" : m.get("proCode"));
			json.put("proName",
					m.get("proName") == null ? "" : m.get("proName"));
			json.put("province",
					m.get("province") == null ? "" : m.get("province"));
			json.put("city", m.get("city") == null ? "" : m.get("city"));
			json.put("district",
					m.get("district") == null ? "" : m.get("district"));
			json.put("proStatus",
					m.get("proStatus") == null ? "" : m.get("proStatus"));
			json.put("employeeCode", m.get("employeeCode") == null ? ""
					: getJObNo(m.get("employeeCode") + ""));
			json.put("proManager",
					m.get("proManager") == null ? "" : m.get("proManager"));
			json.put("districtManager", m.get("districtManager") == null ? ""
					: getEmployeeName(m.get("districtManager") + ""));
			json.put("manageDept", m.get("manageDept") == null ? ""
					: getUnitName(m.get("manageDept") + ""));
			System.out.println("共：" + list.size() + "," + (count++)
					+ json.toJSONString());
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("project", json.toJSONString());
			paramsMap.put("appCode", APP_CODE);
			String result = HttpUtil.executeHttpsPost(
					pWCloudOsConfig.getUpdateProjectUrl(), paramsMap, "UTF-8");
			System.out.println(result);
		}

	}

	private String getUnitName(String unitCode) {
		// TODO Auto-generated method stub
		OrgUnit ou = orgUnitDao.queryOrgUnit(unitCode);
		if (ou != null)
			return ou.getUnitName();
		return "";
	}

	/**
	 * 批量获取
	 * 
	 * @throws Exception
	 */
	public void getAllExpenses() throws Exception {
		System.out.println("-----------获取财务报销数据00000000000-------------");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.DATE, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 0; i < 38; i++) {
			System.out.println("===========获取财务报销数据=============:"
					+ sdf.format(cal.getTime()));
			Thread.sleep(5000);
			cal.add(Calendar.DATE, 1);

			String queryDate = "";
			try {
				queryDate = DateTimeUtil.formatDate(new Date(),
						DateTimeUtil.FORMAT_2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Map<String, String> map = new HashMap<String, String>();
			// "2017-01-03"
			map.put("queryDate", sdf.format(cal.getTime()));
			map.put("appCode", APP_CODE);
			String result = HttpUtil.executeHttpsPost(
					pWCloudOsConfig.getGetExpensesFromOAUrl(), map, "UTF-8");
			ExpensesResult expensesResult = JSON.parseObject(result,
					ExpensesResult.class);
			if (expensesResult != null
					&& expensesResult.getStatusCode().equals("200")) {
				List<FmMoneyReturn> returnList = expensesResult.getData();
				for (FmMoneyReturn r : returnList) {
					// 查询流程编号是否存在，存在，则更新
					List<FmMoneyReturn> workFlowList = fmMoneyReturnDao
							.queryFmMoneyReturnList(r.getWorkflowCode());
					if (workFlowList.size() > 0) {
						FmMoneyReturn re = workFlowList.get(0);
						r.setRowid(re.getRowid());
						r.setSysUpdateTime(new Date());
						fmMoneyReturnDao.updateSelective1(r);
						fmMoneyReturnDetailDao.deleteFmReturnDetail(r
								.getRowid());
						List<FmMoneyReturnDetail> detailList = r.getData();
						for (FmMoneyReturnDetail d : detailList) {
							d.setFeeId(r.getRowid());
							d.setSysProcessFlag("1");
							d.setSysCreateTime(new Date());
							fmMoneyReturnDetailDao.insertSelective(d);
						}
					} else {
						r.setSysProcessFlag("1");
						r.setRealMoney(r.getTotalMoney());
						Map<String, Object> m = mdEmployeeDao
								.queryEmployeeByJObNo(r.getJobNo());
						if (m != null) {
							if (m.containsKey("EMPLOYEENAME")
									&& m.get("EMPLOYEENAME") != null) {
								r.setEmployeeName(m.get("EMPLOYEENAME") + "");
							}
							if (m.containsKey("BANK_ACCOUNT")
									&& m.get("BANK_ACCOUNT") != null) {
								r.setBankAccount(m.get("BANK_ACCOUNT") + "");
							}
							if (m.containsKey("UNIT_CODE")
									&& m.get("UNIT_CODE") != null) {
								r.setUnitCode(m.get("UNIT_CODE") + "");
							}
							if (m.containsKey("UNIT_NAME")
									&& m.get("UNIT_NAME") != null) {
								r.setUnitName(m.get("UNIT_NAME") + "");
							}
						}
						r.setStatus("0");
						r.setSysCreateTime(new Date());
						fmMoneyReturnDao.insertSelective(r);
						List<FmMoneyReturnDetail> detailList = r.getData();
						for (FmMoneyReturnDetail d : detailList) {
							d.setFeeId(r.getRowid());
							d.setSysCreateTime(new Date());
							d.setSysProcessFlag("1");
							fmMoneyReturnDetailDao.insertSelective(d);
						}
					}

				}
			}
		}

		System.out.println("----------获取数据ok-------------");
	}

	private String getPro(String jobNo) {
		List<Map<String, Object>> list = mdEmployeeDao.queryProList(jobNo);
		String result = "";
		if (list.size() > 0) {
			for (Map<String, Object> m : list) {
				String proName = (String) m.get("PRO_NAME");
				result += proName + ",";
			}
		}
		return result;
	}

	private String getSex(String sex) {
		// TODO Auto-generated method stub
		if (sex.equals("0"))
			return "男";
		else
			return "女";
	}

	private String getMarital(Integer marital) {
		// TODO Auto-generated method stub
		/**
		 * 1：未婚 2：已婚 3：未知
		 */
		String result = "";
		switch (marital) {
		case 1:
			result = "未婚";
			break;
		case 2:
			result = "已婚";
			break;
		case 3:
			result = "未知";
			break;
		}
		return result;
	}

}
