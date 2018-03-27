package com.glens.pwCloudOs.commuteMgr.performance.digitalSignage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.commuteMgr.performance.digitalSignage.dao.CpCommuteCheckDao;
import com.glens.pwCloudOs.commuteMgr.performance.digitalSignage.web.CpCommuteCheckForm;

public class CpCommuteCheckService extends EAPAbstractService {

	public Map queryList(CpCommuteCheckForm form) {
		CpCommuteCheckDao cpcommutecheckdao = (CpCommuteCheckDao) this.dao;
		PageBean page = cpcommutecheckdao.queryForPage(form.toMap(),
				form.getCurrentPage(), form.getPerpage());
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("currentPage", page.getCurrentPage());
		result.put("perPage", page.getPerpage());
		result.put("totalPage", page.getTotalPage());
		result.put("totalRecord", page.getTotalRecord());
		result.put("list", page.getList());
		return result;
	}
	
	public Map statisticByDate(CpCommuteCheckForm form) {
		CpCommuteCheckDao cpcommutecheckdao = (CpCommuteCheckDao) this.dao;
		PageBean page = cpcommutecheckdao.statisticByDate(form.toMap(),
				form.getCurrentPage(), form.getPerpage());
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("currentPage", page.getCurrentPage());
		result.put("perPage", page.getPerpage());
		result.put("totalPage", page.getTotalPage());
		result.put("totalRecord", page.getTotalRecord());
		result.put("list", page.getList());
		return result;
	}
	
	
	public Map unitSignPerOfPassStat(CpCommuteCheckForm form) {
		CpCommuteCheckDao cpcommutecheckdao = (CpCommuteCheckDao) this.dao;
		List res = cpcommutecheckdao.unitSignPerOfPassStat(form.toMap());
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("list", res);
		return result;
	}
	
	public Map signPerOfPassTop10(CpCommuteCheckForm form) {
		CpCommuteCheckDao cpcommutecheckdao = (CpCommuteCheckDao) this.dao;
		List res = cpcommutecheckdao.signPerOfPassTop10(form.toMap());
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("list", res);
		return result;
	}
	public int insertWithJudging(ValueObject params){
		CpCommuteCheckDao cdao = (CpCommuteCheckDao) this.dao;
		return cdao.insertWithJudging(params);
	}
	public int updateJudging(Map<String, Object> params){
		CpCommuteCheckDao cdao = (CpCommuteCheckDao) this.dao;
		return cdao.updateJudging(params);
	}
}
