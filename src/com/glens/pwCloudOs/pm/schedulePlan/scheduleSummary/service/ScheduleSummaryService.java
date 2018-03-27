/**
 * @Title: ScheduleSummaryService.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.scheduleSummary.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-12 下午2:16:08
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.schedulePlan.scheduleSummary.service;

import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleSummary.dao.ScheduleSummaryDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class ScheduleSummaryService extends EAPAbstractService {

	public List<Map<String, Object>> getScheduleSummary(Map<String, String> params) {
		
		ScheduleSummaryDao scheduleSummaryDao = (ScheduleSummaryDao) getDao();
		return scheduleSummaryDao.getScheduleSummary(params);
	}
	
}
