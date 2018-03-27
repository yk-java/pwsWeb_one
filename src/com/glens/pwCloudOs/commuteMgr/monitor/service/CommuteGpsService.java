package com.glens.pwCloudOs.commuteMgr.monitor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.commuteMgr.monitor.dao.CommuteGpsDao;
import com.glens.pwCloudOs.commuteMgr.monitor.dao.CommuteGpsMongoDao;
import com.glens.pwCloudOs.commuteMgr.monitor.vo.CpCommuteGpsVo;

public class CommuteGpsService extends EAPAbstractService {
	private CommuteGpsMongoDao commuteGpsMongoDao;

	public PageBean getRealTimePosition(Map<String, Object> params) {

		CommuteGpsDao commuteGpsDao = (CommuteGpsDao) getDao();
		PageBean page = commuteGpsDao.selectRtCommuteStatus(params);
		if (page != null && page.getList() != null && page.getList().size() > 0) {

			List<CpCommuteGpsVo> commuteList = page.getList();
			for (CpCommuteGpsVo commuteVo : commuteList) {

				CpCommuteGpsVo gpsVo = commuteGpsMongoDao
						.getRealTimePosition(commuteVo.getAccountCode());
				if (gpsVo != null) {

					commuteVo.setMobile(gpsVo.getMobile());
					commuteVo.setX(gpsVo.getX());
					commuteVo.setY(gpsVo.getY());
					if (gpsVo.getRpTime() != null) {

						commuteVo.setRpTimeStr(DateTimeUtil.getDateTime(
								gpsVo.getRpTime(), DateTimeUtil.FORMAT_1));
					}
				}
			}
		}

		return page;
	}

	public PageBean selectOutdoorWorkers(Map<String, Object> params) {

		CommuteGpsDao commuteGpsDao = (CommuteGpsDao) getDao();

		return commuteGpsDao.selectOutdoorWorkers(params);
	}

	public Map getHisPosition(String accountCode, String date) {
		Map result = new HashMap();
		try {

			String startTime = date + " 00:00:00";
			String endTime = date + " 23:59:59";

			List<CpCommuteGpsVo> res = commuteGpsMongoDao.getHisPosition(
					accountCode, startTime, endTime);
			if (res != null && res.size() > 0) {

				for (int i = 0; i < res.size() - 1; i++) {

					CpCommuteGpsVo vo = res.get(i);
					CpCommuteGpsVo nexVo = res.get(i + 1);

					vo.setStayTime(DateTimeUtil.daysOfSecond(vo.getRpTime(),
							nexVo.getRpTime()));
					nexVo.setStayTime(0);
				}
			}
			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
			result.put("list", res);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("statusCode", ResponseConstants.SERVER_ERROR);
			result.put("resultMsg", e.getMessage());
		}
		return result;
	}

	public CommuteGpsMongoDao getCommuteGpsMongoDao() {
		return commuteGpsMongoDao;
	}

	public void setCommuteGpsMongoDao(CommuteGpsMongoDao commuteGpsMongoDao) {
		this.commuteGpsMongoDao = commuteGpsMongoDao;
	}

}
