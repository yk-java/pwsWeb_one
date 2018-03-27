/**

 * @Title: MyCommuteService.java

 * @Package com.glens.pwCloudOs.commuteMgr.app.myCommute.service

 * @Description: TODO

 * Copyright: Copyright (c) 2016 

 * Company:南京量为石信息科技有限公司

 * @author 

 * @date 2016-5-20 下午2:36:47

 * @version V1.0

 **/

package com.glens.pwCloudOs.commuteMgr.app.myCommute.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.commuteMgr.app.myCommute.dao.MyCommuteDao;
import com.glens.pwCloudOs.commuteMgr.app.myCommute.dao.MyCommuteMongoDao;
import com.glens.pwCloudOs.commuteMgr.monitor.vo.CpCommuteGpsVo;

/**
 * 
 * @ClassName: MyCommuteService
 * 
 * @Description: TODO
 * 
 * @author
 * 
 * @date 2016-5-20 下午2:36:47
 **/

public class MyCommuteService extends EAPAbstractService {

	private static final String[] WEEK_DATES = { "星期日", "星期一", "星期二", "星期三",
			"星期四", "星期五", "星期六" };
	
	/** @Fields HOUR_BUFFER : 签到签退可以提前2小时或者延迟2小时**/
	
	private static final int HOUR_BUFFER = 2;

	/** @Fields myCommuteMongoDao : 查询我的通勤mongodb的数据处理类**/
	
	private MyCommuteMongoDao myCommuteMongoDao;

	/**
	  * getMyCommuteStatusByMonth 获取某人某个月的每天通勤情况
	  * @Title: getMyCommuteStatusByMonth
	  * @Description: TODO 获取某人某个月的每天通勤情况
	  * @param companyCode
	  * @param account
	  * @param month
	  * @return    设定文件
	  * @return List<Map<String,String>>    返回类型
	  * @throws
	  **/
	public List<Map<String, String>> getMyCommuteStatusByMonth(
			String companyCode, String unitCode, String account, String month) {

		List<Map<String, String>> dateCommuteStatusList = new ArrayList<Map<String, String>>();
		MyCommuteDao myCommuteDao = (MyCommuteDao) this.getDao();
		try {
			List<Map<String, String>> dates = this.getMonthDates(month);
			Map<String, Object> commuteConfig = myCommuteDao
					.getLastCommuteConfig(companyCode);
			String[] tmp = month.split("-");
			String year = tmp[0];
			String m = tmp[1];
			List<String> dayList = myCommuteDao.getMyRestDate(companyCode, unitCode, 
					account, Integer.parseInt(year), Integer.parseInt(m));
			for (Map<String, String> date : dates) {
				
				if (dayList != null && dayList.size() > 0 
						&& dayList.indexOf(date.get("simpleDate")) > -1) {
					
					continue;
				}
				
				Map<String, String> dateCommuteStatus = new HashMap<String, String>();
				dateCommuteStatus.put("date", date.get("weekDate"));
				dateCommuteStatus.put("vdate", date.get("simpleDate"));
				dateCommuteStatus.put("status", checkDateStatus(commuteConfig, account, date.get("simpleDate")));
				
				dateCommuteStatusList.add(dateCommuteStatus);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return dateCommuteStatusList;
	}

	/**
	
	  * @Title: checkDateStatus
	
	  * @Description: 判断某人某天的通勤情况
	
	  * @param commuteConfig
	  * @param account
	  * @param date
	  * @return
	  * @throws ParseException    设定文件
	
	  * @return String    返回类型
	
	  * @throws
	
	  **/
	  
	private String checkDateStatus(Map<String, Object> commuteConfig,
			String account, String date) throws ParseException {

		String status = "1";
		MyCommuteDao myCommuteDao = (MyCommuteDao) this.getDao();
		Date configCheckinTime = DateTimeUtil.getDateFromDateString(date + " "
				+ commuteConfig.get("checkinTime"), "yyyy-MM-dd HH:mm:ss");
		Date configCheckoutTime = DateTimeUtil.getDateFromDateString(date + " "
				+ commuteConfig.get("checkoutTime"), "yyyy-MM-dd HH:mm:ss");
		int checkPointTotal = (Integer) commuteConfig.get("checkPointTotal");
		Map<String, String> commuteCheck = myCommuteDao
				.getMyCommuteCheckByDate(account, date);
		if (commuteCheck != null && commuteCheck.size() > 0) {
			
			if (commuteCheck.get("checkinTime") == null) {
				
				status = "0";
			}
			else if (commuteCheck.get("checkoutTime") == null) {
				
				Calendar currentTime = Calendar.getInstance();
				Calendar checkoutCalendar = Calendar.getInstance();
				checkoutCalendar.setTime(configCheckoutTime);
				checkoutCalendar.add(Calendar.HOUR_OF_DAY, HOUR_BUFFER);
				if (currentTime.after(checkoutCalendar)) {
					
					status = "0";
				}
			} 
			else {
				
				Date checkin = DateTimeUtil.getDateFromDateString(
						(String) commuteCheck.get("checkinTime"),
						"yyyy-MM-dd HH:mm:ss");
				Date checkout = DateTimeUtil.getDateFromDateString(
						(String) commuteCheck.get("checkoutTime"),
						"yyyy-MM-dd HH:mm:ss");
				Calendar checkinCalendar = Calendar.getInstance();
				checkinCalendar.setTime(configCheckinTime);
				checkinCalendar.add(Calendar.HOUR_OF_DAY, -HOUR_BUFFER);
				
				Calendar checkoutCalendar = Calendar.getInstance();
				checkoutCalendar.setTime(configCheckoutTime);
				checkoutCalendar.add(Calendar.HOUR_OF_DAY, HOUR_BUFFER);
				if (checkin.after(configCheckinTime) || checkin.before(checkinCalendar.getTime())) {
					
					status = "0";
				}
				else if (checkout.before(configCheckoutTime) || checkout.after(checkoutCalendar.getTime())) {
					
					status = "0";
				}
				else {
					
					String startTime = date + " 00:00:00";
					String endTime = date + " 23:59:59";
					List<CpCommuteGpsVo> gpsList = this.myCommuteMongoDao.getCommuteTrackCount(account, startTime, endTime);
					long pointCount = 0;
					if (gpsList != null) pointCount = gpsList.size(); 
					if (pointCount < checkPointTotal) {
						
						status = "0";
					}
				}
			}

		}
		else {
			
			status = "0";
		}

		return status;
	}

	/**
	
	  * @Title: getMonthDates
	
	  * @Description: 获取某月的所有日期，并且要小于等于当天
	
	  * @param month
	  * @return
	  * @throws ParseException    设定文件
	
	  * @return List<Map<String,String>>    返回类型
	
	  * @throws
	
	  **/
	  
	private List<Map<String, String>> getMonthDates(String month)
			throws ParseException {

		List<Map<String, String>> dates = new ArrayList<Map<String, String>>();

		String[] monthSplits = month.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(monthSplits[0]));
		cal.set(Calendar.MONTH, Integer.parseInt(monthSplits[1]));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = cal.getTime();
		Date today = new Date();
		if (lastDate.after(today)) {

			lastDate = today;
		}

		dates = this.getDates(firstDate, lastDate);

		return dates;
	}

	/**
	  * @Title: getDates
	
	  * @Description: 根据跟定时间段获取时间段内所有日期
	
	  * @param fromDate
	  * @param toDate
	  * @return
	  * @throws ParseException    设定文件
	
	  * @return List<Map<String,String>>    返回类型
	
	  * @throws
	
	  **/
	  
	private List<Map<String, String>> getDates(Date fromDate, Date toDate)
			throws ParseException {

		List<Map<String, String>> dates = new ArrayList<Map<String, String>>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fromDate);
		Date tmpDate = calendar.getTime();
		Date end = toDate;
		long endTime = end.getTime();
		while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
			int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			if (w < 0)
				w = 0;
			Map<String, String> dateItem = new HashMap<String, String>();
			dateItem.put("simpleDate", DateTimeUtil.formatDate(
					calendar.getTime(), DateTimeUtil.FORMAT_2));
			dateItem.put(
					"weekDate",
					DateTimeUtil.formatDate(calendar.getTime(),
							DateTimeUtil.FORMAT_2) + " (" + WEEK_DATES[w] + ")");
			dates.add(dateItem);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			tmpDate = calendar.getTime();
		}

		return dates;
	}

	public MyCommuteMongoDao getMyCommuteMongoDao() {
		return myCommuteMongoDao;
	}

	public void setMyCommuteMongoDao(MyCommuteMongoDao myCommuteMongoDao) {
		this.myCommuteMongoDao = myCommuteMongoDao;
	}

}
