package com.glens.pwCloudOs.cj.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.cj.base.dao.CjByqDao;
import com.glens.pwCloudOs.cj.base.vo.CjByq;
import com.glens.pwCloudOs.cj.base.web.CjByqForm;

public class CjByqService extends EAPAbstractService {

	public List queryCjByqList(CjByqForm form) {
		CjByqDao cjDao = (CjByqDao) this.dao;
		return cjDao.queryForList(form.toMap());
	}

	public PageBean queryCjByqForPage(CjByqForm form) {
		CjByqDao dao = (CjByqDao) this.dao;
		return dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());
	}

	public Map saveCjByq(CjByq cjByq) {
		CjByqDao cjDao = (CjByqDao) this.dao;
		String collectId = UUID.randomUUID().toString().replaceAll("\\-", "");
		cjByq.setCollectId(collectId);
		cjByq.setSysCreateTime(new Date());

		Map result = new HashMap();
		if (dao.insert(cjByq)) {
			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
			result.put("collectId", collectId);
		} else {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增变压器信息失败");
		}
		return result;
	}

	public Map updateCjPole(CjByq cjByq) {
		CjByqDao cjDao = (CjByqDao) this.dao;
		cjByq.setSysUpdateTime(new Date());
		cjDao.update(cjByq);

		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map delCjByq(CjByq cjByq) {
		CjByqDao cjDao = (CjByqDao) this.dao;
		cjDao.delete(cjByq);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}
}
