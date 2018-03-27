package com.glens.pwCloudOs.commuteMgr.performance.statistic.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.utils.HttpUtil;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.sys.orgEmployee.account.dao.PfAccountDao;
import com.glens.eap.sys.orgEmployee.account.vo.PfAccount;
import com.glens.pwCloudOs.commuteMgr.app.myCommute.dao.MyCommuteMongoDao;
import com.glens.pwCloudOs.commuteMgr.monitor.dao.CommuteGpsMongoDao;
import com.glens.pwCloudOs.commuteMgr.monitor.vo.CpCommuteGpsVo;
import com.glens.pwCloudOs.commuteMgr.online.dao.CpOnlineUserHistoryDao;
import com.glens.pwCloudOs.commuteMgr.performance.statistic.dao.CommuteStatisticDao;
import com.glens.pwCloudOs.commuteMgr.performance.statistic.vo.CommuteStatisticVo;
import com.glens.pwCloudOs.commuteMgr.performance.statistic.web.CommuteStatisticForm;

public class CommuteStatisticService extends EAPAbstractService {

	public static final String UNCHECKIN_EXCEPTION_DESC = "未签到，轨迹异常";

	public static final String UNCHECKOUT_POINT_COUNT_UNFINISH_EXCEPTION_DESC = "签到,但未签退,上传点数未达到%s,轨迹异常";

	public static final String UNCHECKOUT_MILEAGE_EXCEPTION_DESC = "签到,但未签退,里程数未达到%s公里";

	public static final String UNCHECKOUT_NORMAL_DESC = "签到，但未签退,轨迹点超%s,二次判定轨迹正常";

	public static final String COMMUTE_NORMAL_DESC = "轨迹正常";

	public static final String CHECKIN_EXCEPTION_DESC = "未按时签到";

	public static final String CHECKOUT_EXCEPTOIN_DESC = "未按时签退";

	private static final String MILEAGE_EXCEPTION_DESC = "里程数未达到%s公里";

	private static final String POINT_COUNT_UNFINISH_DESC = "上传点数未达到%s";

	private static final String WORK_TIMES_EXCEPTION_DESC = "在线时长不满%s小时,轨迹异常";

	private static final String POINT_UPLOAD_EXCEPTION_DEDC = "轨迹点数据上传异常，原因待查";

	private static final int CONFIG_WORK_TIMES = 7;

	private static final int CONFIG_MILEAGE = 5;

	private static final int MIN_POINT_COUNT = 50;

	private static Log logger = LogFactory
			.getLog(CommuteStatisticService.class);

	private static DecimalFormat df = new DecimalFormat("######0.00");

	private MyCommuteMongoDao myCommuteMongoDao;

	private CommuteGpsMongoDao commuteGpsMongoDao;

	private CpOnlineUserHistoryDao cpOnlineUserHistoryDao;

	private PfAccountDao pfAccountDao;

	private CommuteStatisticDao commuteStatisticDao;

	public Map queryList(Map<String, Object> params, int currentPage,
			int perpage) {
		Map result = new HashMap();
		
		CommuteStatisticDao cpcommutecheckdao = (CommuteStatisticDao) this.dao;
		
		try {
			PageBean page = cpcommutecheckdao.queryForPage(params, currentPage,
					perpage);

			if (page != null && page.getList() != null
					&& page.getList().size() > 0) {

				String date = (String) params.get("date");

				List<CommuteStatisticVo> statisticList = page.getList();

				for (CommuteStatisticVo vo : statisticList) {
					validStatisticVo(vo, date);
					vo.setCheckDate(date);
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("accountCode", vo.getAccountCode());
					m.put("date", date);
					Map m1 = cpOnlineUserHistoryDao.queryOnlineTime(m);
					if (m1 != null && m1.containsKey("onlinetime")
							&& StringUtil.isNotNull(m1.get("onlinetime"))) {
						vo.setOnlinetime(Integer.parseInt(m1.get("onlinetime")
								+ ""));
					} else {
						vo.setOnlinetime(0);
					}
				}

				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("currentPage", page.getCurrentPage());
				result.put("perPage", page.getPerpage());
				result.put("totalPage", page.getTotalPage());
				result.put("totalRecord", page.getTotalRecord());
				result.put("list", page.getList());
			} else {

				result.put("statusCode", ResponseConstants.NO_DATA);
				result.put("resultMsg", "返回结果失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取通勤日报数据出错", e);

			result.put("statusCode", ResponseConstants.SERVER_ERROR);
			result.put("resultMsg", "返回结果失败");
		}

		return result;
	}

	private Map queryOnlineTime(Map m) {
		Map result = cpOnlineUserHistoryDao.queryOnlineTime(m);
		return result;
	}

	public List<CommuteStatisticVo> queryListForExport(
			Map<String, Object> params) {
		CommuteStatisticDao cpcommutecheckdao = (CommuteStatisticDao) this.dao;
		try {
			List res = cpcommutecheckdao.queryForList(params);
			if (res != null && res.size() > 0) {

				String date = (String) params.get("date");

				List<CommuteStatisticVo> statisticList = res;
				for (CommuteStatisticVo vo : statisticList) {

					validStatisticVo(vo, date);

					if (vo.getCommuteStatus() == 1) {
						vo.setCommuteStatusName("正常");
					} else if (vo.getCommuteStatus() == 0) {
						vo.setCommuteStatusName("异常");
					}
					vo.setCheckDate(date);
				}
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map statisticByDate(CommuteStatisticForm form) {
		CommuteStatisticDao cpcommutecheckdao = (CommuteStatisticDao) this.dao;
		PageBean page = cpcommutecheckdao.statisticByDate(form.toMap(),
				form.getCurrentPage(), form.getPerpage());
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("currentPage", page.getCurrentPage());
		result.put("perPage", page.getPerpage());
		result.put("totalPage", page.getTotalPage());
		result.put("totalRecord", page.getTotalRecord());
		result.put("list", page.getList());
		return result;
	}

	public Map unitSignPerOfPassStat(CommuteStatisticForm form) {
		CommuteStatisticDao cpcommutecheckdao = (CommuteStatisticDao) this.dao;
		List res = cpcommutecheckdao.unitSignPerOfPassStat(form.toMap());
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("list", res);
		return result;
	}

	public Map signPerOfPassTop10(CommuteStatisticForm form) {
		CommuteStatisticDao cpcommutecheckdao = (CommuteStatisticDao) this.dao;
		List res = cpcommutecheckdao.signPerOfPassTop10(form.toMap());
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("list", res);
		return result;
	}

	private void validStatisticVo(CommuteStatisticVo vo, String date)
			throws ParseException {

		String _url = "";
		// 签到签退
		int checkPointTotal = vo.getCheckPointTotal();
		vo.setCommuteDesc("");

		// 未签到判断
		if (vo.getCheckinTime().equals("")) {

			vo.setCommuteStatus(0);
			vo.setCommuteDesc(UNCHECKIN_EXCEPTION_DESC);

			return;
		}

		String startTime = date + " " + vo.getCheckinTime();
		String endTime = date + " 23:59:59";
		// 点数
		List<CpCommuteGpsVo> gpsList = this.myCommuteMongoDao
				.getCommuteTrackCount(vo.getAccountCode(), startTime, endTime);
		long pointCount = 0;
		if (gpsList != null)
			pointCount = gpsList.size();
		vo.setCheckPointCount(pointCount);

		// 里程数
		double dis = 0;
		if (StringUtil.isNull(vo.getDistance())) {
			PfAccount account = pfAccountDao
					.queryPfAccount(vo.getAccountCode());
			String accountname = "";
			if (account != null) {
				accountname = account.getAccountName();
			}

			// 请求百度，重新获取里程
			_url = "http://api.map.baidu.com/trace/v2/track/gethistory?ak=N3il2wWG56Wn84k3NuS1nbDpmcgeW6BG&service_id=130428&entity_name="
					+ accountname
					+ "&start_time="
					+ DateTimeUtil.Date2TimeStamp(startTime,
							DateTimeUtil.FORMAT_1)
					+ "&end_time="
					+ DateTimeUtil.Date2TimeStamp(endTime,
							DateTimeUtil.FORMAT_1) + "&simple_return=2";
			String result = HttpUtil.executeGet(_url, "UTF-8");
			if (result != null) {
				JSONObject jo = JSON.parseObject(result);
				if (jo.containsKey("distance")) {
					dis = Double.parseDouble(formatDouble(jo
							.getDouble("distance") / 1000));
					vo.setMileage(dis);
					// 更新里程
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("date", date);
					map.put("accountCode", vo.getAccountCode());
					map.put("distance", dis);
					commuteStatisticDao.updateDistance(map);
				}
			}
		} else {
			dis = Double.parseDouble(vo.getDistance());
			vo.setMileage(dis);
		}

		// 未签退判断
		if (vo.getCheckoutTime().equals("")) {

			// 上传坐标点数未达标
			if (pointCount < checkPointTotal) {

				vo.setCommuteStatus(0);
				vo.setCommuteDesc(String.format(
						UNCHECKOUT_POINT_COUNT_UNFINISH_EXCEPTION_DESC,
						checkPointTotal));

				return;
			}

			// 公里数未达标
			if (dis < CONFIG_MILEAGE) {

				vo.setCommuteStatus(0);
				vo.setCommuteDesc(String.format(
						UNCHECKOUT_MILEAGE_EXCEPTION_DESC, CONFIG_MILEAGE));

				return;
			}

			// 上传点数达标，公里数达标，可认为正常
			vo.setCommuteStatus(1);
			vo.setCommuteDesc(String.format(UNCHECKOUT_NORMAL_DESC,
					checkPointTotal));

			return;
		}

		float worktime = vo.getWorktime();
		// 工作时长未达标
		if (worktime < CONFIG_WORK_TIMES) {

			vo.setCommuteStatus(0);
			vo.setCommuteDesc(String.format(WORK_TIMES_EXCEPTION_DESC,
					CONFIG_WORK_TIMES));

			return;
		}

		// 工作时长达标，但上传点数太少，上传坐标异常
		if (pointCount < MIN_POINT_COUNT) {

			vo.setCommuteStatus(0);
			vo.setCommuteDesc(POINT_UPLOAD_EXCEPTION_DEDC);

			return;
		}

		// 工作时长达标，但上传点数未达标
		if (pointCount < checkPointTotal) {

			vo.setCommuteStatus(0);
			vo.setCommuteDesc(String.format(POINT_COUNT_UNFINISH_DESC,
					checkPointTotal));

			return;
		}

		// 工作时长达标，但里程未达标
		if (dis < CONFIG_MILEAGE) {

			vo.setCommuteStatus(0);
			vo.setCommuteDesc(String.format(MILEAGE_EXCEPTION_DESC,
					CONFIG_MILEAGE));

			return;
		}

		// 正常
		vo.setCommuteStatus(1);
		vo.setCommuteDesc(COMMUTE_NORMAL_DESC);
	}

	public MyCommuteMongoDao getMyCommuteMongoDao() {
		return myCommuteMongoDao;
	}

	public void setMyCommuteMongoDao(MyCommuteMongoDao myCommuteMongoDao) {
		this.myCommuteMongoDao = myCommuteMongoDao;
	}

	public CommuteStatisticVo findCommute(Map params) {
		CommuteStatisticDao cpcommutecheckdao = (CommuteStatisticDao) this.dao;
		CommuteStatisticVo vo = cpcommutecheckdao.findCommute(params);
		String date = (String) params.get("date");
		String accountCode = (String) params.get("accountCode");
		try {
			validStatisticVo(vo, date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String startTime = date + " 00:00:00";
		String endTime = date + " 23:59:59";
		List<CpCommuteGpsVo> res = commuteGpsMongoDao.getHisPosition(
				accountCode, startTime, endTime);
		return vo;
	}

	public CommuteStatisticVo findCommuteByDate(Map params) {
		CommuteStatisticDao cpcommutecheckdao = (CommuteStatisticDao) this.dao;
		CommuteStatisticVo vo = cpcommutecheckdao.findCommute(params);
		return vo;
	}

	public CommuteGpsMongoDao getCommuteGpsMongoDao() {
		return commuteGpsMongoDao;
	}

	public void setCommuteGpsMongoDao(CommuteGpsMongoDao commuteGpsMongoDao) {
		this.commuteGpsMongoDao = commuteGpsMongoDao;
	}

	public void setCpOnlineUserHistoryDao(
			CpOnlineUserHistoryDao cpOnlineUserHistoryDao) {
		this.cpOnlineUserHistoryDao = cpOnlineUserHistoryDao;
	}

	/**
	 * 查询在线情况
	 * 
	 * @param params
	 * @param currentPage
	 * @param perpage
	 * @return
	 */
	public Map queryOnlineList(Map<String, Object> params, int currentPage,
			int perpage) {
		Map result = new HashMap();
		try {
			PageBean page = cpOnlineUserHistoryDao.queryForPage(params,
					currentPage, perpage);

			if (page != null && page.getList() != null
					&& page.getList().size() > 0) {

				String date = (String) params.get("date");

				List<Map<String, Object>> statisticList = page.getList();

				for (Map vo : statisticList) {
					// vo.setOnlinetime(queryOnlineTime(vo.getAccountCode()));
					String accountCode = (String) vo.get("accountCode");
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("accountCode", accountCode);
					m.put("date", date);
					Map m1 = cpOnlineUserHistoryDao.queryOnlineTime(m);
					if (m1 != null) {
						vo.put("onlinetime", m1.get("onlinetime"));
						vo.put("avgonlinetime", m1.get("avgonlinetime"));
					} else {
						vo.put("onlinetime", "0");
						vo.put("avgonlinetime", "0");
					}

				}

				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("currentPage", page.getCurrentPage());
				result.put("perPage", page.getPerpage());
				result.put("totalPage", page.getTotalPage());
				result.put("totalRecord", page.getTotalRecord());
				result.put("list", page.getList());
			} else {

				result.put("statusCode", ResponseConstants.NO_DATA);
				result.put("resultMsg", "返回结果失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取在线情况数据出错", e);

			result.put("statusCode", ResponseConstants.SERVER_ERROR);
			result.put("resultMsg", "返回结果失败");
		}

		return result;
	}

	public void setPfAccountDao(PfAccountDao pfAccountDao) {
		this.pfAccountDao = pfAccountDao;
	}

	public static final String formatDouble(double doubleValue) {
		return df.format(doubleValue);
	}

	public void setCommuteStatisticDao(CommuteStatisticDao commuteStatisticDao) {
		this.commuteStatisticDao = commuteStatisticDao;
	}

}
