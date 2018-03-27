package com.glens.pwCloudOs.cj.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.cj.base.dao.CjHwgDao;
import com.glens.pwCloudOs.cj.base.vo.CjHwg;
import com.glens.pwCloudOs.cj.base.web.CjHwgForm;

public class CjHwgService extends EAPAbstractService {

	public List queryCjHwgList(CjHwgForm form) {
		CjHwgDao cjDao = (CjHwgDao) this.dao;
		return cjDao.queryForList(form.toMap());
	}

	public PageBean queryCjHwgForPage(CjHwgForm form) {
		CjHwgDao cjDao = (CjHwgDao) this.dao;
		return dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());
	}

	public Map saveCjHwg(CjHwg cjHwg) {
		CjHwgDao cjDao = (CjHwgDao) this.dao;
		String collectId = UUID.randomUUID().toString().replaceAll("\\-", "");
		cjHwg.setCollectId(collectId);
		cjHwg.setSysCreateTime(new Date());

		Map result = new HashMap();
		if (dao.insert(cjHwg)) {
			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
			result.put("collectId", collectId);
		} else {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增环网柜信息失败");
		}
		return result;
	}

	public Map updateCjHwg(CjHwg cjHwg) {
		CjHwgDao cjDao = (CjHwgDao) this.dao;
		cjHwg.setSysUpdateTime(new Date());
		dao.update(cjHwg);

		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map delCjHwg(CjHwg cjHwg) {
		CjHwgDao cjDao = (CjHwgDao) this.dao;
		dao.delete(cjHwg);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}
}
