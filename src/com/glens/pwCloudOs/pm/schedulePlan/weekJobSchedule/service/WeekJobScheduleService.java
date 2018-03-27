/**
 * @Title: WeekJobScheduleService.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.weekJobSchedule.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-1-19 上午9:37:22
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.schedulePlan.weekJobSchedule.service;

import java.util.List;
import java.util.Map;

import bsh.EvalError;
import bsh.Interpreter;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.schedulePlan.weekJobSchedule.dao.WeekJobScheduleDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class WeekJobScheduleService extends EAPAbstractService {

	public Map<String, Object> selectProWeekWorkload(Map<String, Object> params) {
		
		WeekJobScheduleDao jobScheduleDao = (WeekJobScheduleDao) getDao();
		
		return jobScheduleDao.selectProWeekWorkload(params);
	}
	
	/**
	
	  * <p>Title: insert</p>
	
	  * <p>Description: </p>
	
	  * @param parameters
	  * @return
	
	  * @see com.glens.eap.platform.framework.service.impl.EAPAbstractService#insert(java.lang.Object)
	
	  **/
	
	
	@Override
	public boolean insert(Object parameters) {
		// TODO Auto-generated method stub
		
		Map<String, Object> params = (Map<String, Object>) parameters;
		double totalWorkload = Double.parseDouble(params.get("totalWorkload").toString());
		double weekAccumulativeWorkload = Double.parseDouble(params.get("weekAccumulativeWorkload").toString());;
		double completionStage = weekAccumulativeWorkload / totalWorkload;
		params.put("completionStage", completionStage);
		
		return super.insert(params);
	}
	
	/**
	
	  * <p>Title: update</p>
	
	  * <p>Description: </p>
	
	  * @param parameters
	  * @return
	
	  * @see com.glens.eap.platform.framework.service.impl.EAPAbstractService#update(java.lang.Object)
	
	  **/
	
	
	@Override
	public int update(Object parameters) {
		// TODO Auto-generated method stub
		
		Map<String, Object> params = (Map<String, Object>) parameters;
		double totalWorkload = Double.parseDouble(params.get("totalWorkload").toString());
		double weekAccumulativeWorkload = Double.parseDouble(params.get("weekAccumulativeWorkload").toString());;
		double completionStage = weekAccumulativeWorkload / totalWorkload;
		params.put("completionStage", completionStage);
		
		return super.update(parameters);
	}
	
	public boolean save(Map<String, Object> params) {
		
		if (params.get("rowId") != null && !"".equals("rowId") 
				&& Long.parseLong(params.get("rowId").toString()) > 0) {
			
			return update(params) > 0;
		}
		else {
			
			return insert(params);
		}
	}
	
	public List<Map<String, String>> selectWorkloadTrend() {
		
		WeekJobScheduleDao jobScheduleDao = (WeekJobScheduleDao) getDao();
		
		return jobScheduleDao.selectWorkloadTrend();
	}
	
}
