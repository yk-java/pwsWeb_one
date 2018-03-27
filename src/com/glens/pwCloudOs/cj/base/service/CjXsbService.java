package com.glens.pwCloudOs.cj.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.cj.base.dao.CjXsbDao;
import com.glens.pwCloudOs.cj.base.vo.CjXsb;
import com.glens.pwCloudOs.cj.base.web.CjXsbForm;

public class CjXsbService extends EAPAbstractService {

	public List queryCjXsbList(CjXsbForm form) {
		CjXsbDao cjDao = (CjXsbDao) this.dao;
		return cjDao.queryForList(form.toMap());
	}

	public PageBean queryCjXsbForPage(CjXsbForm form) {
		CjXsbDao cjDao = (CjXsbDao) this.dao;
		return dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());
	}

	public Map saveCjXsb(CjXsb cjXsb) {
		CjXsbDao cjDao = (CjXsbDao) this.dao;
		String collectId = UUID.randomUUID().toString().replaceAll("\\-", "");
		cjXsb.setCollectId(collectId);
		cjXsb.setSysCreateTime(new Date());

		Map result = new HashMap();
		if (dao.insert(cjXsb)) {
			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
			result.put("collectId", collectId);
		} else {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增箱式变信息失败");
		}
		return result;
	}

	public Map updateCjXsb(CjXsb cjXsb) {
		CjXsbDao cjDao = (CjXsbDao) this.dao;
		cjXsb.setSysUpdateTime(new Date());
		dao.update(cjXsb);

		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map delCjXsb(CjXsb cjXsb) {
		CjXsbDao cjDao = (CjXsbDao) this.dao;
		dao.delete(cjXsb);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}
}
