package com.glens.pwCloudOs.cj.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.cj.base.dao.CjBdsDao;
import com.glens.pwCloudOs.cj.base.vo.CjBds;
import com.glens.pwCloudOs.cj.base.web.CjBdsForm;

public class CjBdsService extends EAPAbstractService {

	public List queryCjBdsList(CjBdsForm form) {
		CjBdsDao cjDao = (CjBdsDao) this.dao;
		return cjDao.queryForList(form.toMap());
	}

	public PageBean queryCjBdsForPage(CjBdsForm form) {
		CjBdsDao cjDao = (CjBdsDao) this.dao;
		return dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());
	}

	public Map saveCjBds(CjBds cjBds) {
		CjBdsDao cjDao = (CjBdsDao) this.dao;
		String collectId = UUID.randomUUID().toString().replaceAll("\\-", "");
		cjBds.setCollectId(collectId);
		cjBds.setSysCreateTime(new Date());

		Map result = new HashMap();
		if (dao.insert(cjBds)) {
			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
			result.put("collectId", collectId);
		} else {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增变电所信息失败");
		}
		return result;
	}

	public Map updateCjBds(CjBds cjBds) {
		CjBdsDao cjDao = (CjBdsDao) this.dao;
		cjBds.setSysUpdateTime(new Date());
		dao.update(cjBds);

		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map delCjBds(CjBds cjBds) {
		CjBdsDao cjDao = (CjBdsDao) this.dao;
		dao.delete(cjBds);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}
}
