package com.glens.pwCloudOs.cj.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.cj.base.dao.CjZbDao;
import com.glens.pwCloudOs.cj.base.vo.CjZb;
import com.glens.pwCloudOs.cj.base.web.CjZbForm;

public class CjZbService extends EAPAbstractService {

	public List queryCjZbList(CjZbForm form) {
		CjZbDao cjDao = (CjZbDao) this.dao;
		return cjDao.queryForList(form.toMap());
	}

	public PageBean queryCjZbForPage(CjZbForm form) {
		CjZbDao dao = (CjZbDao) this.dao;
		return dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());
	}

	public Map saveCjZb(CjZb cjZb) {
		CjZbDao dao = (CjZbDao) this.dao;
		String collectId = UUID.randomUUID().toString().replaceAll("\\-", "");
		cjZb.setCollectId(collectId);
		cjZb.setSysCreateTime(new Date());

		Map result = new HashMap();
		if (dao.insert(cjZb)) {
			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
			result.put("collectId", collectId);
		} else {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增专变信息失败");
		}
		return result;
	}

	public Map updateCjZb(CjZb cjZb) {
		CjZbDao dao = (CjZbDao) this.dao;
		cjZb.setSysUpdateTime(new Date());
		dao.update(cjZb);

		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map delCjZb(CjZb cjZb) {
		CjZbDao dao = (CjZbDao) this.dao;
		dao.delete(cjZb);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}
}
