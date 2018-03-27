package com.glens.pwCloudOs.cj.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.cj.base.dao.CjKgDao;
import com.glens.pwCloudOs.cj.base.vo.CjKg;
import com.glens.pwCloudOs.cj.base.web.CjKgForm;

public class CjKgService extends EAPAbstractService {

	public List queryCjKgList(CjKgForm form) {
		CjKgDao cjDao = (CjKgDao) this.dao;
		return cjDao.queryForList(form.toMap());
	}

	public PageBean queryCjKgForPage(CjKgForm form) {
		CjKgDao cjDao = (CjKgDao) this.dao;
		return dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());
	}

	public Map saveCjKg(CjKg cjKg) {
		CjKgDao cjDao = (CjKgDao) this.dao;
		String collectId = UUID.randomUUID().toString().replaceAll("\\-", "");
		cjKg.setCollectId(collectId);
		cjKg.setSysCreateTime(new Date());

		Map result = new HashMap();
		if (dao.insert(cjKg)) {
			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
			result.put("collectId", collectId);
		} else {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增开关信息失败");
		}
		return result;
	}

	public Map updateCjKg(CjKg cjKg) {
		CjKgDao cjDao = (CjKgDao) this.dao;
		cjKg.setSysUpdateTime(new Date());
		dao.update(cjKg);

		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map delCjKg(CjKg cjKg) {
		CjKgDao cjDao = (CjKgDao) this.dao;
		dao.delete(cjKg);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}
}
