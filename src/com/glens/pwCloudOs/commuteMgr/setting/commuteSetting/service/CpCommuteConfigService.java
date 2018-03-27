package com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.service;

import java.util.HashMap;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.dao.CpCommuteConfigDao;
import com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.vo.CpCommuteConfig;

public class CpCommuteConfigService extends EAPAbstractService {

	public Map commuteSetting(CpCommuteConfig config) {
		CpCommuteConfigDao dao = (CpCommuteConfigDao) this.dao;
		dao.deleteCommuteSetting(config.getCompanyCode());
		dao.insert(config);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

}