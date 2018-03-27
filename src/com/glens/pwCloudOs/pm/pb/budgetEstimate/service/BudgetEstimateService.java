/**
 * @Title: BudgetEstimateService.java
 * @Package com.glens.pwCloudOs.pm.pb.budgetEstimate.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-2-8 下午2:26:44
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.pb.budgetEstimate.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.sys.orgEmployee.account.service.PfAccountService;
import com.glens.pwCloudOs.hrm.salMgr.salaryFramwork.service.SalaryFramworkService;
import com.glens.pwCloudOs.notice.service.SmNoticeService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.dao.PmBaseDao;
import com.glens.pwCloudOs.pm.pb.budgetEstimate.dao.BudgetEstimateDao;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

public class BudgetEstimateService extends EAPAbstractService {

	/**
	 * 人力资源预算项代码
	 */
	private static final String LABOUR_COST_ITEM_CODE = "I001";

	/**
	 * 设备资源预算项代码
	 */
	private static final String DEVICE_RES_ITEM_CODE = "I002";

	/**
	 * 外包费用项目代码
	 */
	private static final String OUT_SOURCE_ITEM_CODE = "I003";

	/**
	 * 咨询项目代码
	 */
	private static final String CONSULT_ITEM_CODE = "I004";
	
	/**
	 * 房屋租赁费
	 */
	private static final String HOUSERENT_COSE="I010,I012";
	
	private static final String LABOUR_COST="I001,I070,I071";

	public static String getLabourCost() {
		return LABOUR_COST;
	}

	public static String getHouserentCose() {
		return HOUSERENT_COSE;
	}


	/**
	 * 业务招待项目代码
	 */
	private static final String SERVE_ITEM_CODE = "I005";

	/**
	 * 租车项目代码
	 */
	private static final String CAR_RENTAL_ITEM_CODE = "I006,I007";

	/**
	 * 除了人力资源成本、设备资源成本、外包费用等其他项目代码
	 */
	private static final String NON_FIXED_COST_ITEM_CODE = "I010,I012,I004,I005,I006,I007,I008,I009,I017,I013,I014,I015,I016,I019,I020,I018,I024,I025,I027,I028,I029,I030";
	
	//"I004,I005,I006,I007,I008,I009,I010,I011,I012,I013,I014,I015,I016,I017,I018,I019,I020,I021,I022,I023,I024,I025,I026,I027,I028,I029,I030,I070,I071";

	/**
	 * 除了咨询、招待、租车，其他项目代码
	 */
	private static final String OTHER_COST_ITEM_CODE = "I008,I009,I017,I013,I014,I015,I016,I019,I020,I018,I024,I025,I027,I028,I029,I030";
	
	//I008,I009,I010,"I011,I012,I013,I014,I015,I016,I017,I018,I019,I020,I021,I022,I023,I024,I025,I026,I027,I028,I029,I030,I070,I071";

	/**
	 * 项目概算未填状态
	 */
	private static final int PRO_BUDGET_ESTIMATE_UN_WRITE = 0;

	/**
	 * 项目概算已填状态
	 */
	private static final int PRO_BUDGET_ESTIMATE_WRITED = 1;
	
	private SalaryFramworkService salaryFramworkService;
	
	private PfAccountService pfAccountService;
	
	private SmNoticeService smNoticeService;

	public PfAccountService getPfAccountService() {
		return pfAccountService;
	}

	public void setPfAccountService(PfAccountService pfAccountService) {
		this.pfAccountService = pfAccountService;
	}

	public SmNoticeService getSmNoticeService() {
		return smNoticeService;
	}

	public void setSmNoticeService(SmNoticeService smNoticeService) {
		this.smNoticeService = smNoticeService;
	}

	public List<Map<String, Object>> selectProBudgetEstimateList(
			Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		return budgetEstimateDao.selectProBudgetEstimateList(params);
	}
	public List<Map<String, Object>> getProPlanBooks(
			Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		return budgetEstimateDao.getProPlanBooks(params);
	}
	public List<Map<String, Object>> getProContract(
			Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		return budgetEstimateDao.getProContract(params);
	}
	
	public List<Map<String, Object>> getDistrictManager(
			Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		return budgetEstimateDao.getDistrictManager(params);
	}

	public List<Map<String, String>> selectProPhaseList(
			Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		return budgetEstimateDao.selectProPhaseList(params);
	}
	
	public List<Map<String, Object>> getProCost(
			Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		return budgetEstimateDao.getProCost(params);
	}
	public List<Map<String, Object>> getProTotalCost(
			Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		return budgetEstimateDao.getProTotalCost(params);
	}
	

	public int insertProPhase(Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		// 生成编码
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				"simpleCodeWorker");
		params.put("phaseCode", codeWorker.createCode("P"));
		
		List list=budgetEstimateDao.getVersions(params);
		if(list!=null && list.size()>0){
			Map m=(Map)list.get(0);
			int versionStatus=Integer.parseInt(m.get("VERSION_STATUS").toString());
			if(versionStatus==0){
				params.put("versionStatus", 1);
				budgetEstimateDao.updateVersionStatus(params);
			}
		}
		

		return budgetEstimateDao.insertProPhase(params);
	}
	
	public int addPmpbLog(Map<String, Object> params) {
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		return budgetEstimateDao.addPmpbLog(params);
	}
	

	public int updateProPhase(Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		return budgetEstimateDao.updateProPhase(params);
	}

	public int deleteProPhase(Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		int icount = budgetEstimateDao.deleteProPhase(params);
		if (icount > 0) {

			budgetEstimateDao.deleteProItemCost(params);
			budgetEstimateDao.deleteProLabourCost(params);
			budgetEstimateDao.deleteProRelevenfund(params);
		}

		return icount;
	}

	/**
	 * 获取项目概算
	 * 
	 * @param params
	 * @return 一个Map类型记录
	 * @throws
	 * @author:
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */

	public Map<String, Object> getProBudgetEstimate(Map<String, Object> params) {

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		
		String budgetEstimateCode=params.get("budgetEstimateCode").toString();
		String versionCode=params.get("versionCode").toString();
		
		System.out.println(budgetEstimateCode+" "+versionCode);

		// 获取项目概算整体信息
		Map<String, Object> proBudgetEstimate = budgetEstimateDao
				.selectProBudgetEstimate(params);
		proBudgetEstimate.put("list", resultList);
		// 获取项目阶段
		List<Map<String, String>> phaseList = selectProPhaseList(params);
		if (phaseList != null && phaseList.size() > 0) {
			proBudgetEstimate.put("phaseCount", phaseList.size());
			// 人力资源成本
			/*List<Map<String, Object>> labourCostList = budgetEstimateDao
					.selectProLabourCost(params);*/
			params.put("itemCode", LABOUR_COST);
			List<Map<String, Object>> labourCostList = budgetEstimateDao
					.selectProItemCostByCode(params);
			
			
			// 设备资源成本
			params.put("itemCode", DEVICE_RES_ITEM_CODE);
			List<Map<String, Object>> deviceResList = budgetEstimateDao
					.selectProItemCostByCode(params);
			// 外包费用
			params.put("itemCode", OUT_SOURCE_ITEM_CODE);
			List<Map<String, Object>> outSourceList = budgetEstimateDao
					.selectProItemCostByCode(params);
			// 房屋租赁费
			params.put("itemCode", HOUSERENT_COSE);
			List<Map<String, Object>> consultList = budgetEstimateDao
					.selectProItemCostByCode(params);
			// 招待费
			params.put("itemCode", SERVE_ITEM_CODE);
			List<Map<String, Object>> serveList = budgetEstimateDao
					.selectProItemCostByCode(params);
			// 租车费用
			params.put("itemCode", CAR_RENTAL_ITEM_CODE);
			List<Map<String, Object>> carRentalList = budgetEstimateDao
					.selectProItemCostByCode(params);
			// 其他成本(包含咨询，招待，租车)   A4.1---4.15
			params.put("itemCode", NON_FIXED_COST_ITEM_CODE);
			List<Map<String, Object>> nonFixedList = budgetEstimateDao
					.selectProItemCostByCode(params);
			// 其他(不包含咨询，招待，租车)    4.1-4.15
			params.put("itemCode", OTHER_COST_ITEM_CODE);
			List<Map<String, Object>> otherList = budgetEstimateDao
					.selectProItemCostByCode(params);
			// 收款、激励奖
			List<Map<String, Object>> releventFundList = budgetEstimateDao
					.selectProReleventFundList(params);
			// 成本按阶段小计
			List<Map<String, Object>> subTotCostList = budgetEstimateDao
					.selectProPhaseSubTot(params);

			// 阶段名称
			Map<String, Object> phaseNameMap = new HashMap<String, Object>();
			phaseNameMap.put("itemName", "阶段名称");
			resultList.add(phaseNameMap);
			// 阶段开始时间
			Map<String, Object> phaseStartDateMap = new HashMap<String, Object>();
			phaseStartDateMap.put("itemName", "阶段计划开始时间");
			resultList.add(phaseStartDateMap);
			// 阶段结束时间
			Map<String, Object> phaseEndDateMap = new HashMap<String, Object>();
			phaseEndDateMap.put("itemName", "阶段计划结束时间");
			resultList.add(phaseEndDateMap);
			// 人力资源成本
			Map<String, Object> labourCostMap = new HashMap<String, Object>();
			labourCostMap.put("itemName", "A1人力资源成本");
			resultList.add(labourCostMap);
			params.put("itemCode", LABOUR_COST_ITEM_CODE);
			Map<String, Object> labourTotCost = budgetEstimateDao
					.selectProTotCost(params);
			labourCostMap.put("total", labourTotCost.get("cost"));
			// 设备资源成本
			Map<String, Object> deviceResMap = new HashMap<String, Object>();
			deviceResMap.put("itemName", "A2设备资源成本");
			resultList.add(deviceResMap);
			params.put("itemCode", DEVICE_RES_ITEM_CODE);
			Map<String, Object> deviceResTotCost = budgetEstimateDao
					.selectProTotCost(params);
			deviceResMap.put("total", deviceResTotCost.get("cost"));
			// 外包费用
			Map<String, Object> outsourceMap = new HashMap<String, Object>();
			outsourceMap.put("itemName", "A3外包费用");
			resultList.add(outsourceMap);
			params.put("itemCode", OUT_SOURCE_ITEM_CODE);
			Map<String, Object> outSourceTotCost = budgetEstimateDao
					.selectProTotCost(params);
			outsourceMap.put("total", outSourceTotCost.get("cost"));
			// 其他成本
			Map<String, Object> otherCostMap = new HashMap<String, Object>();
			otherCostMap.put("itemName", "A4其他成本");
			resultList.add(otherCostMap);
			params.put("itemCode", NON_FIXED_COST_ITEM_CODE);
			Map<String, Object> otherTotCost = budgetEstimateDao
					.selectProTotCost(params);
			otherCostMap.put("total", otherTotCost.get("cost"));
			// 房屋租赁费
			Map<String, Object> consultCostMap = new HashMap<String, Object>();
			consultCostMap.put("itemName", "A4.1房屋租赁费");
			resultList.add(consultCostMap);
			params.put("itemCode", HOUSERENT_COSE);
			Map<String, Object> consultTotCost = budgetEstimateDao
					.selectProTotCost(params);
			consultCostMap.put("total", consultTotCost.get("cost"));
			// 招待费
			Map<String, Object> serveCostMap = new HashMap<String, Object>();
			serveCostMap.put("itemName", "A4.2招待费");
			resultList.add(serveCostMap);
			params.put("itemCode", SERVE_ITEM_CODE);
			Map<String, Object> serveTotCost = budgetEstimateDao
					.selectProTotCost(params);
			serveCostMap.put("total", serveTotCost.get("cost"));
			// 租车费
			Map<String, Object> carRentalMap = new HashMap<String, Object>();
			carRentalMap.put("itemName", "A4.3租车费");
			resultList.add(carRentalMap);
			params.put("itemCode", CAR_RENTAL_ITEM_CODE);
			Map<String, Object> carRentalTotCost = budgetEstimateDao
					.selectProTotCost(params);
			carRentalMap.put("total", carRentalTotCost.get("cost"));
			// 其他
			Map<String, Object> otherMap = new HashMap<String, Object>();
			otherMap.put("itemName", "A4.4其他");
			resultList.add(otherMap);
			params.put("itemCode", OTHER_COST_ITEM_CODE);
			Map<String, Object> othTotCost = budgetEstimateDao
					.selectProTotCost(params);
			otherMap.put("total", othTotCost.get("cost"));
			// 交付成本小计
			Map<String, Object> costSubTotMap = new HashMap<String, Object>();
			costSubTotMap.put("itemName", "交付成本小计");
			resultList.add(costSubTotMap);
			params.remove("itemCode");
			Map<String, Object> totCost = budgetEstimateDao
					.selectProTotCost(params);
			costSubTotMap.put("total", totCost.get("cost"));
			// 产值对应情况
			Map<String, Object> outputValueMap = new HashMap<String, Object>();
			outputValueMap.put("itemName", "产值对应情况");
			resultList.add(outputValueMap);
			// 收款计划
			Map<String, Object> paymentMap = new HashMap<String, Object>();
			paymentMap.put("itemName", "收款计划(累计收款)");
			resultList.add(paymentMap);
			// 项目经理奖金发送金额
			Map<String, Object> proManagerBonusMap = new HashMap<String, Object>();
			proManagerBonusMap.put("itemName", "项目经理奖金发送金额");
			resultList.add(proManagerBonusMap);

			// 项目审定人奖金发放金额
			Map<String, Object> proValidorBonusMap = new HashMap<String, Object>();
			proValidorBonusMap.put("itemName", "项目审定人奖金发放金额");
			resultList.add(proValidorBonusMap);

			// 合伙人奖励发放金额
			Map<String, Object> partnerBonusMap = new HashMap<String, Object>();
			partnerBonusMap.put("itemName", "合伙人奖励发放金额");
			resultList.add(partnerBonusMap);

			// 项目决策人薪酬发放金额
			Map<String, Object> decisionMarkerBonusMap = new HashMap<String, Object>();
			decisionMarkerBonusMap.put("itemName", "项目决策人薪酬发放金额");
			resultList.add(decisionMarkerBonusMap);

			// 成员激励奖
			Map<String, Object> memberIncentivesMap = new HashMap<String, Object>();
			memberIncentivesMap.put("itemName", "成员激励奖");
			resultList.add(memberIncentivesMap);

			// 插入各行值
			int i = 0;
			for (Map<String, String> phaseItem : phaseList) {

				phaseNameMap.put("c" + i, phaseItem.get("phaseName"));
				phaseStartDateMap.put("c" + i, phaseItem.get("phaseStartDate"));
				phaseEndDateMap.put("c" + i, phaseItem.get("phaseEndDate"));
				labourCostMap.put(
						"c" + i,
						getPhaseCost(phaseItem.get("phaseCode").toString(),
								labourCostList));
				deviceResMap.put(
						"c" + i,
						getPhaseCost(phaseItem.get("phaseCode").toString(),
								deviceResList));
				outsourceMap.put(
						"c" + i,
						getPhaseCost(phaseItem.get("phaseCode").toString(),
								outSourceList));
				// 其他成本 (包含咨询，招待，租车)
				otherCostMap.put(
						"c" + i,
						getPhaseCost(phaseItem.get("phaseCode").toString(),
								nonFixedList));
				consultCostMap.put(
						"c" + i,
						getPhaseCost(phaseItem.get("phaseCode").toString(),
								consultList));

				serveCostMap.put(
						"c" + i,
						getPhaseCost(phaseItem.get("phaseCode").toString(),
								serveList));
				carRentalMap.put(
						"c" + i,
						getPhaseCost(phaseItem.get("phaseCode").toString(),
								carRentalList));
				otherMap.put(
						"c" + i,
						getPhaseCost(phaseItem.get("phaseCode").toString(),
								otherList));
				// 成本小计
				costSubTotMap.put(
						"c" + i,
						getPhaseCost(phaseItem.get("phaseCode").toString(),
								subTotCostList));

				// 收款计划、成员激励奖 项目经理奖金发送金额 项目审定人奖金发放金额 合伙人奖励发放金额 项目决策人薪酬发放金额
				writeReleventFund("c" + i, phaseItem.get("phaseCode")
						.toString(), paymentMap, memberIncentivesMap,
						proManagerBonusMap, proValidorBonusMap,
						partnerBonusMap, decisionMarkerBonusMap,
						releventFundList);
				i++;
			}

			// 收款计划、成员激励 项目经理奖金发送金额 项目审定人奖金发放金额 合伙人奖励发放金额 项目决策人薪酬发放金额 的总计
			List<Map<String, Object>> getBounsSum = budgetEstimateDao
					.getBounsSum(params);
			if (getBounsSum != null && getBounsSum.size() > 0) {
				Map m = getBounsSum.get(0);
				paymentMap.put("total", m.get("accumulateReceipt"));
				memberIncentivesMap.put("total", m.get("incentiveBonus"));
				proManagerBonusMap.put("total", m.get("proManagerBonus"));
				proValidorBonusMap.put("total", m.get("proValidorBonus"));
				partnerBonusMap.put("total", m.get("partnerBonus"));
				decisionMarkerBonusMap.put("total",
						m.get("decisionMarkerBonus"));
			}

			// 产值对应情况、项目经理奖金发放金额、项目审定人奖金发放金额、合伙人奖励发放金额、项目决策人薪酬发放金额
			i = 0;
			float outputValueTot = 0.0f;
			for (Map<String, String> phaseItem : phaseList) {

				// 产值对应情况
				float accumulateLabourCost = 0.0f;
				for (int k = 0; k <= i; k++) {

					accumulateLabourCost += Float.parseFloat(labourCostMap.get(
							"c" + k).toString());
				}

				float outputValue = 0;
				if (Float.parseFloat(labourCostMap.get("total").toString()) > 0) {

					outputValue = accumulateLabourCost
							/ Float.parseFloat(labourCostMap.get("total")
									.toString())
							* Float.parseFloat(paymentMap.get("total")
									.toString());
				}

				outputValueTot += outputValue;
				outputValueMap.put("c" + i, BigDecimal.valueOf(outputValue)
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

				proManagerBonusMap.put("c" + i + "code",
						phaseItem.get("phaseCode"));
				proValidorBonusMap.put("c" + i + "code",
						phaseItem.get("phaseCode"));
				partnerBonusMap.put("c" + i + "code",
						phaseItem.get("phaseCode"));
				decisionMarkerBonusMap.put("c" + i + "code",
						phaseItem.get("phaseCode"));

				i++;
			}
			outputValueMap.put("total", outputValueTot);

		}

		return proBudgetEstimate;
	}
	
	
	
	public List getPhaseListByPro(Map<String, Object> params){
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		return budgetEstimateDao.getPhaseListByPro(params);
	}

	// 汇总
	public Map<String, Object> proBudgetTotal(Map<String, Object> params) {
		Map<String, Object> map = getProBudgetEstimate(params);

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		// 获取项目概算整体信息
		Map<String, Object> proBudgetEstimate = budgetEstimateDao
				.selectProBudgetEstimate(params);
		List list = (List) map.get("list");

		int publishStatus = Integer.parseInt(proBudgetEstimate.get("proStatus")
				.toString());
		if (publishStatus == 1) {
			proBudgetEstimate.put("proStatus", "登记");
		} else if (publishStatus == 2) {
			proBudgetEstimate.put("proStatus", "激活");
		} else if (publishStatus == 3) {
			proBudgetEstimate.put("proStatus", "关闭");
		}
		double contractValue = 0;
		if (proBudgetEstimate.get("contractValue") != null) {
			contractValue = Double.parseDouble(proBudgetEstimate.get(
					"contractValue").toString());// 合同综合
		}
		double managerVal=0;//管理费比例
		if(proBudgetEstimate.get("managerVal")!=null){
			managerVal = Double.parseDouble(proBudgetEstimate.get(
					"managerVal").toString());
		}

		double empval = 0; // A1人力资源成本
		double deviceval = 0;// A2设备资源成本
		double waibao = 0;// A3外包费用
		double otherval = 0;// A4其他成本

		double proManagerval = 0;// 项目经理奖金发送金额
		double proShendingval = 0;// 项目审定人奖金发放金额
		double partnerval = 0;// 合伙人奖励发放金额
		double proConfirmval = 0;// 项目决策人薪酬发放金额
		double memberval = 0;// 成员激励奖
		for (int i = 0; i < list.size(); i++) {
			Map temp = (Map) list.get(i);
			if (temp.get("itemName").equals("A1人力资源成本")
					&& temp.get("total") != null) {
				empval = Double.parseDouble(temp.get("total").toString());
			}

			if (temp.get("itemName").equals("A2设备资源成本")
					&& temp.get("total") != null) {
				deviceval = Double.parseDouble(temp.get("total").toString());
			}

			if (temp.get("itemName").equals("A3外包费用")
					&& temp.get("total") != null) {
				waibao = Double.parseDouble(temp.get("total").toString());
			}

			if (temp.get("itemName").equals("A4其他成本")
					&& temp.get("total") != null) {
				otherval = Double.parseDouble(temp.get("total").toString());
			}

			if (temp.get("itemName").equals("项目经理奖金发送金额")
					&& temp.get("total") != null) {
				proManagerval = Double
						.parseDouble(temp.get("total").toString());
			}
			if (temp.get("itemName").equals("项目审定人奖金发放金额")
					&& temp.get("total") != null) {
				proShendingval = Double.parseDouble(temp.get("total")
						.toString());
			}
			if (temp.get("itemName").equals("合伙人奖励发放金额")
					&& temp.get("total") != null) {
				partnerval = Double.parseDouble(temp.get("total").toString());
			}
			if (temp.get("itemName").equals("项目决策人薪酬发放金额")
					&& temp.get("total") != null) {
				proConfirmval = Double
						.parseDouble(temp.get("total").toString());
			}
			if (temp.get("itemName").equals("成员激励奖")
					&& temp.get("total") != null) {
				memberval = Double.parseDouble(temp.get("total").toString());
			}
		}

		

		double appointmentPay = proManagerval + proShendingval + partnerval
				+ proConfirmval + memberval;// 约定薪酬

		double proBalance = contractValue * (1 - managerVal - 0.06) - empval * 2.3
				/ 1.4 - deviceval - waibao - otherval - 2.3 * appointmentPay; // 项目结余
		if (proBalance < 0) {
			
			proBalance = 0;
		}

		double expectBalanceRate = 0;
		if (contractValue > 0) {
			expectBalanceRate = proBalance / contractValue;// 预期结余率
		}

		double proProfit = contractValue * (1 - managerVal - 0.06) - empval 
				- deviceval - waibao - otherval - appointmentPay * 1.4;// 项目利润

		double expectedProfitrate = 0;
		if (contractValue > 0) {
			expectedProfitrate = proProfit / contractValue;// 预期利润率
		}

		proBudgetEstimate.put("managerVal",managerVal);// 院管理费比例
		proBudgetEstimate.put("empval", empval);// 人力成本
		proBudgetEstimate.put("deviceval", deviceval);// 设备成本
		proBudgetEstimate.put("waibao", waibao);// 外包费用
		proBudgetEstimate.put("otherval", otherval);// 其他
		proBudgetEstimate.put("appointmentPay", appointmentPay);// 约定薪酬
		proBudgetEstimate.put("proBalance", proBalance);// 项目结余
		proBudgetEstimate.put("expectBalanceRate", expectBalanceRate);// 预期结余率
		proBudgetEstimate.put("proProfit", proProfit);// 项目利润
		proBudgetEstimate.put("expectedProfitrate", expectedProfitrate);// 预期利润率

		return proBudgetEstimate;
	}

	/**
	 * 获取人力资源成本明细
	 * 
	 * @param @param params
	 * @param @return
	 * @return 一个Map类型记录
	 * @throws
	 * @author:
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */

	public Map<String, Object> getProLabourCostDetailList(
			Map<String, Object> params) {

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		// 获取项目概算整体信息
		Map<String, Object> proBudgetEstimate = budgetEstimateDao
				.selectProBudgetEstimate(params);
		proBudgetEstimate.put("list", resultList);
		// 获取项目阶段
		List<Map<String, String>> phaseList = selectProPhaseList(params);
		// 人力成本
		List<Map<String, Object>> labourCostDetailList = budgetEstimateDao
				.selectProLabourCostList(params);
		List<Map<String, Object>> labourCostList = budgetEstimateDao
				.selectProLabourCost(params);
		for (Map<String, String> phaseItem : phaseList) {

			Map<String, Object> phaseMap = new HashMap<String, Object>();
			phaseMap.put("phaseCode", phaseItem.get("phaseCode"));
			phaseMap.put("phaseName", phaseItem.get("phaseName"));
			phaseMap.put("milestone", phaseItem.get("milestone"));
			phaseMap.put("phaseOrder", phaseItem.get("phaseOrder"));
			phaseMap.put("phaseStartDate", phaseItem.get("phaseStartDate"));
			phaseMap.put("phaseEndDate", phaseItem.get("phaseEndDate"));
			resultList.add(phaseMap);

			writeLabourCostToPhase(phaseMap, labourCostDetailList,
					labourCostList);
		}

		return proBudgetEstimate;
	}

	public Map<String, Object> getProCostDetailList(Map<String, Object> params) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		// 获取项目阶段
		List<Map<String, String>> phaseList = selectProPhaseList(params);
		if (phaseList != null && phaseList.size() > 0) {

			StringBuilder sqlSeqBuilder = new StringBuilder();
			for (Map<String, String> phaseItem : phaseList) {

				sqlSeqBuilder.append("SUM(CASE WHEN b.phase_code='");
				sqlSeqBuilder.append(phaseItem.get("phaseCode"));
				sqlSeqBuilder.append("' THEN b.COST ELSE 0 END) ");
				sqlSeqBuilder.append(phaseItem.get("phaseCode"));
				sqlSeqBuilder.append(",");
			}
			params.put("sqlSeq", sqlSeqBuilder.toString());
			params.put("itemCode", OTHER_COST_ITEM_CODE);

			List<Map<String, Object>> proCostList = budgetEstimateDao
					.selectProItemCostDetail(params);
			
			for(int i=0;i<proCostList.size();i++){
				Map m=proCostList.get(i);

				if(m.get("itemCode").toString().equals("I001")){
					proCostList.get(i).put("itemIndex", "A1.1");
				}
				if(m.get("itemCode").toString().equals("I070")){
					proCostList.get(i).put("itemIndex", "A1.2");	
				}
				if(m.get("itemCode").toString().equals("I071")){
					proCostList.get(i).put("itemIndex", "A1.3");	
				}
				if(m.get("itemCode").toString().equals("I002")){
					proCostList.get(i).put("itemIndex", "A2");	
				}
				if(m.get("itemCode").toString().equals("I003")){
					proCostList.get(i).put("itemIndex", "A3");	
				}
				if(m.get("itemCode").toString().equals("I010")){
					proCostList.get(i).put("itemIndex", "A4.1");	
				}
				if(m.get("itemCode").toString().equals("I012")){
					proCostList.get(i).put("itemIndex", "A4.1");	
				}
				if(m.get("itemCode").toString().equals("I005")){
					proCostList.get(i).put("itemIndex", "A4.2");	
				}
				if(m.get("itemCode").toString().equals("I006")){
					proCostList.get(i).put("itemIndex", "A4.3");	
				}
				if(m.get("itemCode").toString().equals("I007")){
					proCostList.get(i).put("itemIndex", "A4.3");	
				}
				if(m.get("itemCode").toString().equals("I008")){
					proCostList.get(i).put("itemIndex", "4.1");	
				}
				if(m.get("itemCode").toString().equals("I009")){
					proCostList.get(i).put("itemIndex", "4.2");	
				}
				
				if(m.get("itemCode").toString().equals("I004")){
					proCostList.get(i).put("itemIndex", "4.3");	
				}
				if(m.get("itemCode").toString().equals("I017")){
					proCostList.get(i).put("itemIndex", "4.4");	
				}
				if(m.get("itemCode").toString().equals("I013")){
					proCostList.get(i).put("itemIndex", "4.5");	
				}
				if(m.get("itemCode").toString().equals("I014")){
					proCostList.get(i).put("itemIndex", "4.5");	
				}
				if(m.get("itemCode").toString().equals("I015")){
					proCostList.get(i).put("itemIndex", "4.6");	
				}
				if(m.get("itemCode").toString().equals("I016")){
					proCostList.get(i).put("itemIndex", "4.6");	
				}
				if(m.get("itemCode").toString().equals("I019")){
					proCostList.get(i).put("itemIndex", "4.7");	
				}
				
				if(m.get("itemCode").toString().equals("I020")){
					proCostList.get(i).put("itemIndex", "4.8");	
				}
				
				if(m.get("itemCode").toString().equals("I018")){
					proCostList.get(i).put("itemIndex", "4.9");	
				}
				
				if(m.get("itemCode").toString().equals("I024")){
					proCostList.get(i).put("itemIndex", "4.10");	
				}
				if(m.get("itemCode").toString().equals("I025")){
					proCostList.get(i).put("itemIndex", "4.11");	
				}
				
				if(m.get("itemCode").toString().equals("I027")){
					proCostList.get(i).put("itemIndex", "4.12");	
				}
				
				if(m.get("itemCode").toString().equals("I028")){
					proCostList.get(i).put("itemIndex", "4.13");	
				}
				
				if(m.get("itemCode").toString().equals("I029")){
					proCostList.get(i).put("itemIndex", "4.14");	
				}
				if(m.get("itemCode").toString().equals("I030")){
					proCostList.get(i).put("itemIndex", "4.15");	
				}
			}
			Map tempa=new HashMap();
			tempa.put("itemName", "其他成本--");
			tempa.put("itemIndex", "A4");
			proCostList.add(5,tempa);
			
			Map tempb=new HashMap();
			tempb.put("itemName", "其他--");
			tempb.put("itemIndex", "A4.4");
			proCostList.add(11,tempb);
			
			resultMap.put("proCostList", proCostList);
			resultMap.put("phaseList", phaseList);
		}

		return resultMap;
	}
	
	public int insertFmProCost(Map<String, Object> params){
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		return budgetEstimateDao.insertFmProCost(params);
	}

	public int insertProBudgetEstimate(Map<String, Object> params) {

		int publishStatus = Integer.parseInt(params.get("publishStatus")
				.toString());
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		int icount = 0;
		String releventFund = params.get("releventFund").toString();
		String[] phaseReleventFunds = releventFund.split(";");
		String companyCode = params.get("companyCode").toString();
		String proNo = params.get("proNo").toString();
		
		String budgetEstimateCode=params.get("budgetEstimateCode").toString();
		String versionCode=params.get("versionCode").toString();
		
		double contractValue=Double.parseDouble(params.get("contractValue").toString());
		
		if(contractValue<5000000){ //中小型
			params.put("proLevel", 1);
		}else if(contractValue>=5000000){//大型
			params.put("proLevel", 2);
		}
		

		// 解析相关款项参数
		List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
		for (String phaseReleventFund : phaseReleventFunds) {

			String[] releventFundParams = phaseReleventFund.split(",");
			Map<String, Object> releventFundMap = new HashMap<String, Object>();
			releventFundMap.put("companyCode", companyCode);
			releventFundMap.put("proNo", proNo);
			releventFundMap.put("phaseCode", releventFundParams[0]);
			releventFundMap.put("accumulateReceipt", releventFundParams[1]);

			releventFundMap.put("proManagerBonus", releventFundParams[2]);
			releventFundMap.put("proValidorBonus", releventFundParams[3]);
			releventFundMap.put("partnerBonus", releventFundParams[4]);
			releventFundMap.put("decisionMarkerBonus", releventFundParams[5]);

			releventFundMap.put("incentiveBonus", releventFundParams[6]);
			releventFundMap.put("budgetEstimateCode", budgetEstimateCode);
			releventFundMap.put("versionCode", versionCode);

			paramList.add(releventFundMap);
		}

		/*int estimateCount = budgetEstimateDao
				.selectProBudgetEstimateCount(params);

		if (estimateCount <= 0) {

			// 未填，需要新增
			icount = budgetEstimateDao.insertProBudgetEstimate(params);
			if (icount > 0) {

				budgetEstimateDao.insertProPhaseReleventFund(paramList);
			}
		} else if (estimateCount > 0) {*/

			// 已填，需要修改
		//icount = budgetEstimateDao.updateProBudgetEstimate(params);
		
		icount = budgetEstimateDao.updateProBudgetEstimateVersion(params);
		
		if (icount > 0) {
			budgetEstimateDao.deleteProReleventFund(params);
			budgetEstimateDao.insertProPhaseReleventFund(paramList);
		}
		//}
		return icount;
	}

	public int insertProPhaseLabourCost(Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		int affectedCount = budgetEstimateDao.insertProPhaseLabourCost(params);
		if (affectedCount > 0) {

			List<Map<String, Object>> employeeLabourCostList = budgetEstimateDao
					.selectProPhaseEmployeeLabourCost(params);
			List<Map<String, Object>> resultlist = budgetEstimateDao
					.selectProPhaseLabourCostCountInItem(params);
			Map<String, Object> labourCostItem = employeeLabourCostList.get(0);
			params.put("labourCost", labourCostItem.get("labourCost"));
			if (resultlist != null && resultlist.size() > 0) {

				budgetEstimateDao.updatePlusProPhaseItemLabourCost(params);
			} else {

				budgetEstimateDao.insertProPhaseItemLabourCost(params);
			}
		}

		return affectedCount;
	}

	public int deleteProPhaseLabourCost(Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		int affectedCount = budgetEstimateDao.deleteProPhaseLabourCost(params);
		if (affectedCount > 0) {
			List<Map<String, Object>> employeeLabourCostList = budgetEstimateDao
					.selectProPhaseEmployeeLabourCost(params);
			if (employeeLabourCostList != null
					&& employeeLabourCostList.size() > 0) {

				Map<String, Object> employeeLabourCostItem = employeeLabourCostList
						.get(0);
				params.put("labourCost",
						employeeLabourCostItem.get("labourCost"));
				budgetEstimateDao.updatePlusProPhaseItemLabourCost(params);
			}
		}

		return affectedCount;
	}

	public int updateProPhaseLabourCost(Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		int affectedCount = budgetEstimateDao.updateProPhaseLabourCost(params);
		if (affectedCount > 0) {
			List<Map<String, Object>> employeeLabourCostList = budgetEstimateDao
					.selectProPhaseEmployeeLabourCost(params);
			if (employeeLabourCostList != null
					&& employeeLabourCostList.size() > 0) {

				Map<String, Object> employeeLabourCostItem = employeeLabourCostList
						.get(0);
				params.put("labourCost",
						employeeLabourCostItem.get("labourCost"));
				budgetEstimateDao.updatePlusProPhaseItemLabourCost(params);
			}
		}

		return affectedCount;
	}

	public int insertProPhaseCost(Map<String, Object> params) {

		String companyCode = params.get("companyCode").toString();
		String proNo = params.get("proNo").toString();
		String itemCosts = params.get("itemCosts").toString();
		String itemDescs = params.get("itemDescs").toString();
		
		String budgetEstimateCode=params.get("budgetEstimateCode").toString();
		String versionCode=params.get("versionCode").toString();
		
		
		int publishStatus = Integer.parseInt(params.get("publishStatus")
				.toString());
		int icount = 0;
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		if (itemCosts != null && !"".equals(itemCosts)) {

			String[] itemPhaseCosts = itemCosts.split(";");
			List<Map<String, Object>> itemPhaseCostParamList = new ArrayList<Map<String, Object>>();
			for (String itemPhaseCost : itemPhaseCosts) {

				Map<String, Object> itemPhaseCostMap = new HashMap<String, Object>();
				String[] itemPhaseCostParams = itemPhaseCost.split(",");
				if (itemPhaseCostParams != null
						&& itemPhaseCostParams.length > 0) {
					itemPhaseCostMap.put("companyCode", companyCode);
					itemPhaseCostMap.put("proNo", proNo);
					itemPhaseCostMap.put("itemCode", itemPhaseCostParams[0]);
					itemPhaseCostMap.put("phaseCode", itemPhaseCostParams[1]);
					itemPhaseCostMap.put("cost", itemPhaseCostParams[2]);
					itemPhaseCostMap.put("budgetEstimateCode", budgetEstimateCode);
					itemPhaseCostMap.put("versionCode", versionCode);

					itemPhaseCostParamList.add(itemPhaseCostMap);
					
				}
			}

			// 说明
			String[] itemCostDescs = itemDescs.split(";");
			List<Map<String, Object>> itemCostDescParamList = new ArrayList<Map<String, Object>>();
			for (String itemCostDesc : itemCostDescs) {

				Map<String, Object> itemCostDescMap = new HashMap<String, Object>();
				String[] itemCostDescParams = itemCostDesc.split(",");
				if (itemCostDescParams != null && itemCostDescParams.length > 0) {

					itemCostDescMap.put("companyCode", companyCode);
					itemCostDescMap.put("proNo", proNo);
					itemCostDescMap.put("itemCode", itemCostDescParams[0]);
					if (itemCostDescParams.length > 1) {

						itemCostDescMap.put("itemDesc", itemCostDescParams[1]);
					} else {

						itemCostDescMap.put("itemDesc", "");
					}

					itemCostDescMap.put("budgetEstimateCode", budgetEstimateCode);
					itemCostDescMap.put("versionCode", versionCode);
					itemCostDescParamList.add(itemCostDescMap);
				}

			}

			/*
			 * if (publishStatus == PRO_BUDGET_ESTIMATE_UN_WRITE) {
			 * 
			 * icount =
			 * budgetEstimateDao.insertProPhaseCost(itemPhaseCostParamList); if
			 * (icount > 0) {
			 * 
			 * budgetEstimateDao.insertProCostDesc(itemCostDescParamList); } }
			 * else if (publishStatus == PRO_BUDGET_ESTIMATE_WRITED) {
			 */
			budgetEstimateDao.deleteProCost(params);
			icount = budgetEstimateDao
					.insertProPhaseCost(itemPhaseCostParamList);
			if (icount > 0) {
				budgetEstimateDao.deleteProCostDesc(params);
				budgetEstimateDao.insertProCostDesc(itemCostDescParamList);
			}
			// }
		}

		return icount;
	}

	//更新概算版本
	public int publishProBudgetEstimate(Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		
		
		int versionStatus=Integer.parseInt(params.get("versionStatus").toString());
		if(versionStatus==12){ //审核通过  创建新的版本
			CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean("simpleCodeWorker");
			String versionCode = codeWorker.createCode("BV");
			
			int versionOrder = Integer.parseInt(params.get("versionOrder").toString()) + 1;
			String versionNo = "V" + versionOrder;
			
			Map<String, Object> newVersionItem = new HashMap<String, Object>();
			newVersionItem.put("companyCode", params.get("companyCode"));
			newVersionItem.put("proNo", params.get("proNo"));
			newVersionItem.put("budgetEstimateCode", params.get("budgetEstimateCode"));
			newVersionItem.put("versionCode", versionCode);
			newVersionItem.put("versionNo", versionNo);
			newVersionItem.put("versionOrder", versionOrder);
			newVersionItem.put("versionStatus", 1);
			
			String  nos=params.get("proNo")+"-GS0000"+versionOrder;
			newVersionItem.put("nos", nos);

			budgetEstimateDao.createProBudgetEstimateVersion(newVersionItem);
			
			//预算项成本填写说明 (复制)
			List<Map<String, Object>> descList=budgetEstimateDao.getPmPbItemCostDesc(params);
			for (int i = 0; i < descList.size(); i++) {
				descList.get(i).put("budgetEstimateCode", params.get("budgetEstimateCode"));
				descList.get(i).put("versionCode", versionCode);
			}
			budgetEstimateDao.insertProCostDesc(descList);//批量插入
			
			//项目阶段 (复制)
			List<Map<String, Object>> phaseList=budgetEstimateDao.getProPhase(params);
			for(int i=0;i<phaseList.size();i++){
				Map phase=phaseList.get(i);
				String oldphaseCode=phase.get("phaseCode").toString();
				params.put("phaseCode", oldphaseCode);
				
				String newPhaseCode = codeWorker.createCode("P");
				
				//人力资源成本（复制）
				List<Map<String, Object>> labourList=budgetEstimateDao.getLabours(params); //获取现阶段人力资源成本
				
				for(int j=0;j<labourList.size();j++){
					labourList.get(j).put("phaseCode", newPhaseCode);
					labourList.get(j).put("budgetEstimateCode", params.get("budgetEstimateCode"));
					labourList.get(j).put("versionCode", versionCode);
				}
				budgetEstimateDao.copyProPhaseLabourCost(labourList);//批量新增人力资源
				
				//项目阶段相关款项 (复制)
				List<Map<String, Object>> pmPbReleventfundList=budgetEstimateDao.getPmPbReleventfund(params); //获取项目阶段相关款项
				for(int k=0;k<pmPbReleventfundList.size();k++){
					pmPbReleventfundList.get(k).put("budgetEstimateCode", params.get("budgetEstimateCode"));
					pmPbReleventfundList.get(k).put("versionCode", versionCode);
					pmPbReleventfundList.get(k).put("phaseCode", newPhaseCode);
				}
				budgetEstimateDao.insertProPhaseReleventFund(pmPbReleventfundList); //批量新增 项目阶段相关款项
				
				//项目阶段预算项成本 (复制)
				List<Map<String, Object>> pmPbItemCostList=budgetEstimateDao.getPmPbItemCost(params); //获取项目阶段预算项成本
				for(int j=0;j<pmPbItemCostList.size();j++){
					pmPbItemCostList.get(j).put("budgetEstimateCode", params.get("budgetEstimateCode"));
					pmPbItemCostList.get(j).put("versionCode", versionCode);
					pmPbItemCostList.get(j).put("phaseCode", newPhaseCode);
				}
				budgetEstimateDao.insertProPhaseCost(pmPbItemCostList); //批量新增  项目阶段预算项成本
				
				
				
				phase.put("budgetEstimateCode", params.get("budgetEstimateCode"));
				phase.put("versionCode", versionCode);
				phase.put("phaseCode", newPhaseCode);
				budgetEstimateDao.insertProPhase(phase);//新增项目阶段
				
			}
			
			
	
		}
		
		
		List prodetail=budgetEstimateDao.getprodetail(params);
		
		
		int plevel=Integer.parseInt(params.get("plevel").toString());//项目大小等级 根据金额定
		List getduptManager=budgetEstimateDao.getDuptManager(params);//获取部门副经理
		List getDeptManager=budgetEstimateDao.getDeptManager(params);//获取部门经理
		List getFinance=budgetEstimateDao.getFinance(params);//获取财务
		List getjianguan=budgetEstimateDao.getjianguan(params);//获取部门监管
		
		if(versionStatus==2){//发布
			//指定部门副经理审核人
			
			if(getduptManager!=null && getduptManager.size()>0){
				Map m=(Map)getduptManager.get(0);
				String employeeCode=m.get("employeecode").toString();
				params.put("deputyManager", employeeCode);
				if(prodetail!=null && prodetail.size()>0){
					Map proMap=(Map)prodetail.get(0);
					String proName=proMap.get("PRO_NAME").toString();
					String proNo=proMap.get("PRO_NO").toString();
					sendMessage(proName,proNo, employeeCode, "");
				}
				
			}else if(getDeptManager!=null && getDeptManager.size()>0){ //部门副经理为空  则部门经理审核
				
					Map m=(Map)getDeptManager.get(0);
					String employeecode=m.get("employeecode").toString();
					params.put("deptmgr", employeecode);
					params.put("versionStatus", 3);
					if(prodetail!=null && prodetail.size()>0){
						Map proMap=(Map)prodetail.get(0);
						String proName=proMap.get("PRO_NAME").toString();
						String proNo=proMap.get("PRO_NO").toString();
						sendMessage(proName,proNo, employeecode, "");
					}
					
			}else if(getjianguan!=null && getjianguan.size()>0){//  部门监管审核
				Map m=(Map)getjianguan.get(0);
				String employeecode=m.get("employeecode").toString();
				params.put("deptmgr", employeecode);
				params.put("versionStatus", 3);
				if(prodetail!=null && prodetail.size()>0){
					Map proMap=(Map)prodetail.get(0);
					String proName=proMap.get("PRO_NAME").toString();
					String proNo=proMap.get("PRO_NO").toString();
					sendMessage(proName,proNo, employeecode, "");
				}
			}
		}
		
		//审核驳回
		if(versionStatus==4 || versionStatus==6 || versionStatus==8 || versionStatus==10){
			if(prodetail!=null && prodetail.size()>0){
				Map proMap=(Map)prodetail.get(0);
				String promanager=proMap.get("EMPLOYEECODE").toString();
				String districtManager=proMap.get("DISTRICT_MANAGER").toString();
				String proName=proMap.get("PRO_NAME").toString();
				String proNo=proMap.get("PRO_NO").toString();
				reback(proName,proNo, promanager, districtManager);
			}
		}
		if(versionStatus==13){ //提交调整驳回
			if(prodetail!=null && prodetail.size()>0){
				Map proMap=(Map)prodetail.get(0);
				String promanager=proMap.get("EMPLOYEECODE").toString();
				String districtManager=proMap.get("DISTRICT_MANAGER").toString();
				String proName=proMap.get("PRO_NAME").toString();
				String proNo=proMap.get("PRO_NO").toString();
				tiaozheng(proName,proNo, promanager, districtManager);
			}
		}
		
		
		
		
		if(versionStatus==3){//部门副经理审核通过
			
				if(getDeptManager!=null && getDeptManager.size()>0){
					Map m=(Map)getDeptManager.get(0);
					String employeecode=m.get("employeecode").toString();
					params.put("deptmgr", employeecode);
					
					if(prodetail!=null && prodetail.size()>0){
						Map proMap=(Map)prodetail.get(0);
						String proName=proMap.get("PRO_NAME").toString();
						String proNo=proMap.get("PRO_NO").toString();
						sendMessage(proName,proNo, employeecode, "");
					}
					
				}else if(getjianguan!=null && getjianguan.size()>0){//  部门监管审核
					Map m=(Map)getjianguan.get(0);
					String employeecode=m.get("employeecode").toString();
					params.put("deptmgr", employeecode);
					if(prodetail!=null && prodetail.size()>0){
						Map proMap=(Map)prodetail.get(0);
						String proName=proMap.get("PRO_NAME").toString();
						String proNo=proMap.get("PRO_NO").toString();
						sendMessage(proName,proNo, employeecode, "");
					}
				}else if(getFinance!=null && getFinance.size()>0){
					Map m=(Map)getFinance.get(0);
					String employeecode=m.get("employeecode").toString();
					params.put("financeMgr", employeecode);
					params.put("versionStatus", 5);
					if(prodetail!=null && prodetail.size()>0){
						Map proMap=(Map)prodetail.get(0);
						String proName=proMap.get("PRO_NAME").toString();
						String proNo=proMap.get("PRO_NO").toString();
						sendMessage(proName,proNo, employeecode, "");
					}
				}
			
		}
		
		if(versionStatus==5){//部门经理审核通过
				if(getFinance!=null && getFinance.size()>0){
					Map m=(Map)getFinance.get(0);
					String employeecode=m.get("employeecode").toString();
					params.put("financeMgr", employeecode);
					
					if(prodetail!=null && prodetail.size()>0){
						Map proMap=(Map)prodetail.get(0);
						String proName=proMap.get("PRO_NAME").toString();
						String proNo=proMap.get("PRO_NO").toString();
						sendMessage(proName,proNo, employeecode, "");
					}
				}
		}
		
		if(versionStatus==7){//财务审核通过
			if(plevel==2){//大型
				List getgeneral=budgetEstimateDao.getgeneral(params);//获取总经理
				if(getgeneral!=null && getgeneral.size()>0){
					Map m=(Map)getgeneral.get(0);
					String employeecode=m.get("employeecode").toString();
					params.put("generalManager", employeecode);
					
					if(prodetail!=null && prodetail.size()>0){
						Map proMap=(Map)prodetail.get(0);
						String proName=proMap.get("PRO_NAME").toString();
						String proNo=proMap.get("PRO_NO").toString();
						sendMessage(proName,proNo, employeecode, "");
					}
				}
			}
		}
		
		
		int affectedCount = budgetEstimateDao.publishProBudgetEstimate(params);
		
		
		return affectedCount;
	}

	private Object getPhaseCost(String phaseCode,
			List<Map<String, Object>> costList) {

		Object cost = 0.0;
		if (costList != null && costList.size() > 0) {

			for (Map<String, Object> costItem : costList) {

				if (phaseCode.equals(costItem.get("phaseCode"))) {

					cost = costItem.get("cost");

					break;
				}

			}
		}
		return cost;
	}
	
	public void sendMessage(String proName,String proNo,String proManager,String districtManager){
		//推送
		String msgContent = "项目【" + proName +"("+proNo+")】需要您来概算审核，请迅速登录云平台审核！";
		List<String> employeeCodes = new ArrayList<String>();
		
		if(!proManager.equals("")){
			employeeCodes.add(proManager);
		}
		if(!districtManager.equals("")){
			employeeCodes.add(districtManager);
		}
		
		List<String> accountCodeList = pfAccountService.selectAccountsByEmployeecodes(employeeCodes);
		smNoticeService.sendMessage("系统通知", "项目概算审核", msgContent, accountCodeList);
		
	}
	public void reback(String proName,String proNo,String proManager,String districtManager){
		//推送
		String msgContent = "项目【" + proName +"("+proNo+")】审核不通过，请迅速登录云平台查看！";
		List<String> employeeCodes = new ArrayList<String>();
		if(!proManager.equals("")){
			employeeCodes.add(proManager);
		}
		if(!districtManager.equals("")){
			employeeCodes.add(districtManager);
		}

		List<String> accountCodeList = pfAccountService.selectAccountsByEmployeecodes(employeeCodes);
		smNoticeService.sendMessage("系统通知", "项目概算审核驳回", msgContent, accountCodeList);
		
	}
	
	public void tiaozheng(String proName,String proNo,String proManager,String districtManager){
		//推送
		String msgContent = "项目【" + proName +"("+proNo+")】调整不通过，请迅速登录云平台查看！";
		List<String> employeeCodes = new ArrayList<String>();
		if(!proManager.equals("")){
			employeeCodes.add(proManager);
		}
		if(!districtManager.equals("")){
			employeeCodes.add(districtManager);
		}

		List<String> accountCodeList = pfAccountService.selectAccountsByEmployeecodes(employeeCodes);
		smNoticeService.sendMessage("系统通知", "项目概算调整驳回", msgContent, accountCodeList);
		
	}

	private void writeReleventFund(String field, String phaseCode,
			Map<String, Object> paymentMap,
			Map<String, Object> memberIncentivesMap,
			Map<String, Object> proManagerBonusMap,
			Map<String, Object> proValidorBonusMap,
			Map<String, Object> partnerBonusMap,
			Map<String, Object> decisionMarkerBonusMap,
			List<Map<String, Object>> releventFundList) {

		paymentMap.put(field, 0.0);
		memberIncentivesMap.put(field, 0.0);
		proManagerBonusMap.put(field, 0.0);
		proValidorBonusMap.put(field, 0.0);
		partnerBonusMap.put(field, 0.0);
		decisionMarkerBonusMap.put(field, 0.0);
		// 4

		paymentMap.put(field + "code", phaseCode);
		memberIncentivesMap.put(field + "code", phaseCode);
		proManagerBonusMap.put(field + "code", phaseCode);
		proValidorBonusMap.put(field + "code", phaseCode);
		partnerBonusMap.put(field + "code", phaseCode);
		decisionMarkerBonusMap.put(field + "code", phaseCode);
		if (releventFundList != null && releventFundList.size() > 0) {

			for (Map<String, Object> releventFundItem : releventFundList) {

				if (phaseCode.equals(releventFundItem.get("phaseCode"))) {

					paymentMap.put(field,
							releventFundItem.get("accumulateReceipt"));
					memberIncentivesMap.put(field,
							releventFundItem.get("incentiveBonus"));

					proManagerBonusMap.put(field,
							releventFundItem.get("proManagerBonus"));
					proValidorBonusMap.put(field,
							releventFundItem.get("proValidorBonus"));
					partnerBonusMap.put(field,
							releventFundItem.get("partnerBonus"));
					decisionMarkerBonusMap.put(field,
							releventFundItem.get("decisionMarkerBonus"));

					//
					break;
				}
			}
		}
	}

	private void writeLabourCostToPhase(Map<String, Object> phaseMap,
			List<Map<String, Object>> labourCostDetailList,
			List<Map<String, Object>> labourCostList) {

		List<Map<String, Object>> phaseLabourCostList = new ArrayList<Map<String, Object>>();
		phaseMap.put("list", phaseLabourCostList);
		if (labourCostDetailList != null && labourCostDetailList.size() > 0) {

			for (Map<String, Object> labourCostDetailItem : labourCostDetailList) {

				if (phaseMap.get("phaseCode").equals(
						labourCostDetailItem.get("phaseCode"))) {

					phaseLabourCostList.add(labourCostDetailItem);
				}
			}
		}

		if (labourCostList != null && labourCostList.size() > 0) {

			for (Map<String, Object> labourCostItem : labourCostList) {

				if (phaseMap.get("phaseCode").equals(
						labourCostItem.get("phaseCode"))) {

					Map<String, Object> totalItem = new HashMap<String, Object>();
					totalItem.put("employeeName", "总计");
					totalItem.put("univalent", "");
					totalItem.put("workTime", labourCostItem.get("workTime"));
					totalItem.put("labourCost", labourCostItem.get("cost"));
					phaseLabourCostList.add(totalItem);
				}
			}
		}
	}

	public List<Map<String, Object>> getContractProperty(
			Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		return budgetEstimateDao.getContractProperty(params);
	}

	public List<Map<String, Object>> getJobs(Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		return budgetEstimateDao.getJobs(params);
	}

	public PageBean queryBudgetForPage(Map parameters, int currentPage,
			int perpage) {
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		PageBean page = budgetEstimateDao.queryForPage(parameters, currentPage,
				perpage);
		List proList = page.getList();
		List resultList = new ArrayList();
		for (int i = 0; i < proList.size(); i++) {
			Map m = (Map) proList.get(i);
			// String proNo=m.get("proNo").toString();
			Map<String, Object> mp = proBudgetTotal(m);
			resultList.add(mp);
		}
		page.setList(resultList);
		return page;
	}

	public List<Map<String, Object>> selectProEstimateLabour(
			Map<String, Object> params) {

		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();

		return budgetEstimateDao.selectProEstimateLabour(params);
	}

	public List<Map<String, Object>> queryForAllList(Map parameters) {
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		List proList = budgetEstimateDao.queryForAllList(parameters);

		List resultList = new ArrayList();
		for (int i = 0; i < proList.size(); i++) {
			Map m = (Map) proList.get(i);
			// String proNo=m.get("proNo").toString();
			Map<String, Object> mp = proBudgetTotal(m);
			resultList.add(mp);
		}

		return resultList;
	}

	public List queryFeeItemsCostList(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		return budgetEstimateDao.queryFeeItemsCostList(paramsMap);
	}
	public String getProNo(String proCode) {
		// TODO Auto-generated method stub
		Map<String, Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("proCode", proCode);
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		List list= budgetEstimateDao.getProNo(paramsMap);
		String proNo="";
		if(list!=null && list.size()>0){
			Map m=(Map)list.get(0);
			if(m!=null && m.get("PRO_NO")!=null){
				proNo=m.get("PRO_NO").toString();
			}
		}
		return proNo;
	}
	

	public Map queryBudgetCost(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		return budgetEstimateDao.queryBudgetCost(paramsMap);
	}
	
	public int importProMemberBudgetSection(Map<String, Object> params) {
		
		List<Map<String, Object>> proMemberLabourList = selectProMemberLabour(params);
		int icount = 0;
		if (proMemberLabourList == null || proMemberLabourList.size() <= 0) {
			
			icount = -1;
		}
		else {
			
			for (Map<String, Object> proMemberLabourItem : proMemberLabourList) {
				
				proMemberLabourItem.put("budgetEstimateCode",params.get("budgetEstimateCode") );
				proMemberLabourItem.put("versionCode", params.get("versionCode") );
				
				icount += this.insertProPhaseLabourCost(proMemberLabourItem);
			}
		}
		
		return icount;
	}
	
	private List<Map<String, Object>> selectProMemberLabour(Map<String, Object> params) {
		
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		List<Map<String, Object>> proMemberLabourList = budgetEstimateDao.selectProMemberLabour(params);
		if (proMemberLabourList != null && proMemberLabourList.size() > 0) {
			
			for (Map<String, Object> proMemberLabourItem : proMemberLabourList) {
				
				Map<String, Object> employeeSalaryItem = salaryFramworkService.selectEmployeeSalary(proMemberLabourItem);
				if (employeeSalaryItem != null) {
					
					proMemberLabourItem.put("univalent", employeeSalaryItem.get("hrmResPrice"));
				}
			}
		}
		
		return proMemberLabourList;
	}

	public SalaryFramworkService getSalaryFramworkService() {
		return salaryFramworkService;
	}

	public void setSalaryFramworkService(SalaryFramworkService salaryFramworkService) {
		this.salaryFramworkService = salaryFramworkService;
	}

	
	public int createProBudgetEstimate(Map<String, Object> params) {
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				"simpleCodeWorker");
		String budgetEstimateCode=codeWorker.createCode("B");
		params.put("budgetEstimateCode", budgetEstimateCode);
		
		
		Map<String, Object> budgetVersionItem = new HashMap<String, Object>();
		String versionCode = codeWorker.createCode("BV");
		budgetVersionItem.put("companyCode", params.get("companyCode"));
		budgetVersionItem.put("proNo", params.get("proNo"));
		budgetVersionItem.put("budgetEstimateCode", budgetEstimateCode);
		budgetVersionItem.put("versionCode", versionCode);
		budgetVersionItem.put("versionNo", "V1");
		budgetVersionItem.put("versionOrder", 1);
		budgetVersionItem.put("versionStatus", 0);
		budgetEstimateDao.createProBudgetEstimateVersion(budgetVersionItem);
		
		return budgetEstimateDao.createProBudgetEstimate(params);
		
	}
	
	public int deleteFmProCost(Map<String, Object> params){
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		return budgetEstimateDao.deleteFmProCost(params);
	}
	
	
	public List getBUdgetByProNo(Map<String, Object> params){
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		return budgetEstimateDao.getBUdgetByProNo(params);
	}
	
	public List<Map<String, Object>> getProPhasesCost(Map<String, Object> params){
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		return budgetEstimateDao.getProPhasesCost1(params);
	}
	
	public List<Map<String, Object>> getPmPblogs(Map<String, Object> params){
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		return budgetEstimateDao.getPmPblogs(params);
	}
	
	public List<Map<String, Object>> getPmVersion(Map<String, Object> params){
		BudgetEstimateDao budgetEstimateDao = (BudgetEstimateDao) getDao();
		return budgetEstimateDao.getPmVersion(params);
	}
	
	
	
}
