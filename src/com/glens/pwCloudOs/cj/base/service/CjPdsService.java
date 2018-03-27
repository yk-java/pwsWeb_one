package com.glens.pwCloudOs.cj.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.cj.base.dao.CjPdsDao;
import com.glens.pwCloudOs.cj.base.vo.CjPds;
import com.glens.pwCloudOs.cj.base.web.CjPdsForm;

public class CjPdsService extends EAPAbstractService {

	public List queryCjPdsList(CjPdsForm form) {
		CjPdsDao cjDao = (CjPdsDao) this.dao;
		return cjDao.queryForList(form.toMap());
	}

	public PageBean queryCjPdsForPage(CjPdsForm form) {
		CjPdsDao cjDao = (CjPdsDao) this.dao;
		return dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());
	}

	public Map saveCjPds(CjPds cjPds) {
		CjPdsDao cjDao = (CjPdsDao) this.dao;
		String collectId = UUID.randomUUID().toString().replaceAll("\\-", "");
		cjPds.setCollectId(collectId);
		cjPds.setSysCreateTime(new Date());

		Map result = new HashMap();
		if (dao.insert(cjPds)) {
			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
			result.put("collectId", collectId);
		} else {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增配电所信息失败");
		}
		return result;
	}

	public Map updateCjPds(CjPds cjPds) {
		CjPdsDao cjDao = (CjPdsDao) this.dao;
		cjPds.setSysUpdateTime(new Date());
		dao.update(cjPds);

		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map delCjPds(CjPds cjPds) {
		CjPdsDao cjDao = (CjPdsDao) this.dao;
		dao.delete(cjPds);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}
}
