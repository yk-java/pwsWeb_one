package com.glens.pwCloudOs.cj.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.cj.base.dao.CjFzxDao;
import com.glens.pwCloudOs.cj.base.vo.CjFzx;
import com.glens.pwCloudOs.cj.base.web.CjFzxForm;

public class CjFzxService extends EAPAbstractService {

	public List queryCjFzxList(CjFzxForm form) {
		CjFzxDao cjDao = (CjFzxDao) this.dao;
		return cjDao.queryForList(form.toMap());
	}

	public PageBean queryCjFzxForPage(CjFzxForm form) {
		CjFzxDao cjDao = (CjFzxDao) this.dao;
		return dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());
	}

	public Map saveCjFzx(CjFzx cjFzx) {
		CjFzxDao cjDao = (CjFzxDao) this.dao;
		String collectId = UUID.randomUUID().toString().replaceAll("\\-", "");
		cjFzx.setCollectId(collectId);
		cjFzx.setSysCreateTime(new Date());

		Map result = new HashMap();
		if (dao.insert(cjFzx)) {
			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
			result.put("collectId", collectId);
		} else {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增分支箱信息失败");
		}
		return result;
	}

	public Map updateCjFzx(CjFzx cjFzx) {
		CjFzxDao cjDao = (CjFzxDao) this.dao;
		cjFzx.setSysUpdateTime(new Date());
		dao.update(cjFzx);

		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map delCjFzx(CjFzx cjFzx) {
		CjFzxDao cjDao = (CjFzxDao) this.dao;
		dao.delete(cjFzx);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}
}
