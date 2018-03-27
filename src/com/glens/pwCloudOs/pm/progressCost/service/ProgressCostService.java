package com.glens.pwCloudOs.pm.progressCost.service;

import java.util.List;
import java.util.Map;

import com.glens.pwCloudOs.pm.baseMgr.pmBase.dao.PmBaseDao;

public class ProgressCostService {

	private PmBaseDao pmBaseDao;

	public List queryProgressCostList(Map parasMap) {
		List list = pmBaseDao.queryProBaseList(parasMap);
		return list;
	}

	public void setPmBaseDao(PmBaseDao pmBaseDao) {
		this.pmBaseDao = pmBaseDao;
	}

	public List queryFinishProgress(Map<String, Object> paramsMap) {
		List list = pmBaseDao.queryFinishProgress(paramsMap);
		return list;
	}

	public List<Map<String, Object>> queryFinancial(
			Map<String, Object> paramsMap) {
		List list = pmBaseDao.queryFinancial(paramsMap);
		return list;
	}

	public List<Map<String, Object>> queryDocument(Map<String, Object> paramsMap) {
		List list = pmBaseDao.queryDocument(paramsMap);
		return list;
	}

	public List<Map<String, Object>> queryExpensesClaim(
			Map<String, Object> paramsMap) {
		List list = pmBaseDao.queryExpensesClaim(paramsMap);
		return list;
	}

	public List<Map<String, Object>> queryExpensesDorm(
			Map<String, Object> paramsMap) {
		List list = pmBaseDao.queryExpensesDorm(paramsMap);
		return list;
	}

	public List<Map<String, Object>> queryExpensesAsset(
			Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		List list = pmBaseDao.queryExpensesAsset(paramsMap);
		return list;
	}

	public List queryProMember(Map<String, Object> paramsMap) {
		List list = pmBaseDao.queryProMember(paramsMap);
		return list;
	}

	public List queryContract(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		List list = pmBaseDao.queryContract(paramsMap);
		return list;
	}

	public List queryInvoice(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		List list = pmBaseDao.queryInvoice(paramsMap);
		return list;
	}

	public List queryRepayment(Map<String, Object> paramsMap) {
		List list = pmBaseDao.queryRepayment(paramsMap);
		return list;
	}

	public List queryProDocument(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		List list = pmBaseDao.queryProDocument(paramsMap);
		return list;
	}

	public List queryProAsset(Map<String, Object> paramsMap) {
		List list = pmBaseDao.queryProAsset(paramsMap);
		return list;
	}

	public List queryProDorm(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		List list = pmBaseDao.queryProDorm(paramsMap);
		return list;
	}

	public List queryProReturn(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		List list = pmBaseDao.queryProReturn(paramsMap);
		return list;
	}

}
