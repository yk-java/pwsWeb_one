/** 
 * TimeFormatUtil.java Created on May 21, 2009
 * Copyright 2009@JSHX. 
 * All right reserved. 
 */
package com.glens.eap.platform.core.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 时间格式工具类
 * 
 * @Time 5:14:11 PM
 * @author
 */
public class DateTimeUtil {
	/**
	 * 格式yyyy-MM-dd HH:mm:ss
	 */
	public final static String FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 格式yyyy-MM-dd
	 */
	public final static String FORMAT_2 = "yyyy-MM-dd";

	/**
	 * 格式yyyy-MM
	 */
	public final static String FORMAT_3 = "yyyy-MM";

	/**
	 * 
	 * <p>
	 * 格式化日期
	 * </p>
	 * 
	 * @param datestring
	 * @param format
	 * @return
	 * @throws ParseException
	 * @author
	 * @date 2014-1-14下午3:49:10
	 */

	public static String formatDate(Date date, String format)
			throws ParseException {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
		return null;
	}

	/**
	 * 取得日期datestring 格式yyyymmdd
	 * 
	 * @param masktype
	 *            "yyyy-MM-dd" or "yyyyMMdd"
	 * @throws ParseException
	 * */

	public static Date getDateFromDateString(String datestring, String masktype)
			throws ParseException {
		if (StringUtil.isNotNull(datestring)) {
			SimpleDateFormat sdf = new SimpleDateFormat(masktype);
			return sdf.parse(datestring);
		}
		return null;
	}

	/**
	 * 取得系统当前时间
	 * 
	 * @return 系统当前时间
	 */
	public static Date getSystemDateTime() {
		return new Date();
	}

	/**
	 * 根据年度和期间得到某期第一天或最后一天
	 * 
	 * @param type
	 *            'first'or'last'
	 * @param year
	 * @param qj
	 * @return 系统当前时间 如 2010 3 first 返回2010-03-01的date形式
	 * @throws ParseException
	 */
	public static String getDateTimeInACurtainYearAndMonth(String year,
			String qj, String type) throws ParseException {
		if (new Integer(qj) < 10) {
			qj = "0" + qj;
		}
		if ("first".equals(type)) {
			return year + "-" + qj + "-01";
		}
		if ("last".equals(type)) {
			return lastDayOfDate(year + "-" + qj);
		}
		return null;
	}

	/**
	 * 取得系统当前日期组成的字符串 默认格式为：yyyy-MM-dd
	 * 
	 * @return 系统当前日期组成的字符串
	 */
	public static String getSystemDateString() {
		return getDateTime(getSystemDateTime(), "yyyy-MM-dd");
	}

	/**
	 * 取得系统当前日期＋时间组成的字符串 默认格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 系统当前日期＋时间组成的字符串
	 */
	public static String getSystemDateTimeString() {
		return getDateTime(getSystemDateTime(), DateTimeUtil.FORMAT_1);
	}

	/**
	 * 根据给定的时间（为空时默认使用当前时间）和时间格式 返回时间字符串
	 * 
	 * @param aMask
	 *            时间格式
	 * @param aDate
	 *            时间参数
	 * @return 格式化后的时间字符串
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDateTime(Date aDate, String aMask) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (StringUtil.isNull(aMask)) {
			return "";
		} else if (aDate == null) {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(getSystemDateTime());
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 返回时间的字符串
	 * 
	 * @param dt
	 * @return
	 */
	public static String getLongStringByDate(Date aDate) {
		if (aDate == null)
			return "";
		return String.valueOf(aDate.getTime());
	}

	/**
	 * 根据 时间格式 时间Long字符串 转换时间格式
	 * 
	 * @param aMask
	 * @param aDateLong
	 * @return
	 */
	public static String getDateStrByLongStr(String aDateLong, String aMask) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDateLong != null && !"".equals(aDateLong)) {
			if (aMask == null || "".equals(aMask))
				aMask = DateTimeUtil.FORMAT_1;
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(new Date(Long.parseLong(aDateLong)));
		}
		return returnValue;
	}

	/**
	 * 获取上一个月字符串
	 * 
	 * @param aDate
	 *            2010-06-01
	 * @return 05
	 */
	@SuppressWarnings("deprecation")
	public static String getPreMonthStr(Date aDate) {
		return getMonthStr(aDate, -1);
	}

	/**
	 * 根据当前时间和参数 获取月份字符
	 * 
	 * @param aDate
	 * @param m
	 * @return
	 */
	public static String getMonthStr(Date aDate, int m) {
		Calendar calendar = initCalendar(aDate);
		int month = calendar.get(Calendar.MONTH);
		month = (month + m % 12 + 13) % 12;
		month = (month == 0) ? 12 : month;
		return (month > 9) ? String.valueOf(month) : "0"
				+ String.valueOf(month);
	}

	/**
	 * 根据当前时间和参数 获取年份字符
	 * 
	 * @param aDate
	 * @param y
	 * @return
	 */
	public static String getYearStr(Date aDate, int y) {
		Calendar calendar = initCalendar(aDate);
		int year = calendar.get(Calendar.YEAR);
		year = year + y;
		return String.valueOf(year);
	}

	/**
	 * 根据当前时间和参数 获取日期字符
	 * 
	 * @param aDate
	 * @param d
	 * @return
	 */
	public static String getDayStr(Date aDate, int d) {
		Calendar calendar = initCalendar(aDate);
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		day = day + d;
		calendar.set(Calendar.DAY_OF_YEAR, day);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		return (day > 9) ? String.valueOf(day) : "0" + String.valueOf(day);
	}

	/**
	 * 根据日历的规则，为给定的时间字段添加或减去指定的月数
	 * 
	 * @param 时间字段
	 *            （默认为当前时间）
	 * @param 为字段添加的月数
	 * @return
	 */
	public static Date getMonth(Date aDate, int amount) {
		Calendar calendar = initCalendar(aDate);
		calendar.add(Calendar.MONTH, amount);
		return calendar.getTime();
	}

	/**
	 * 根据日历的规则，为给定的时间字段添加或减去指定的年数
	 * 
	 * @param 时间字段
	 *            （默认为当前时间）
	 * @param 为字段添加的年数
	 * @return
	 */
	public static Date getYear(Date aDate, int amount) {
		Calendar calendar = initCalendar(aDate);
		calendar.add(Calendar.YEAR, amount);
		return calendar.getTime();
	}

	/**
	 * 根据日历的规则，为给定的时间字段添加或减去指定的天数
	 * 
	 * @param 时间字段
	 *            （默认为当前时间）
	 * @param 为字段添加的天数
	 * @return
	 */
	public static Date getDay(Date aDate, int amount) {
		Calendar calendar = initCalendar(aDate);
		calendar.add(Calendar.DAY_OF_MONTH, amount);
		return calendar.getTime();
	}

	/**
	 * 设置日期
	 * 
	 * @param aDate
	 * @param field
	 * @param value
	 * @return
	 */
	public static Date set(Date aDate, int field, int value) {
		Calendar c = initCalendar(aDate);
		c.set(field, value);
		return c.getTime();
	}

	public static Calendar initCalendar(Date aDate) {
		Calendar calendar = null;
		if (aDate == null) {
			calendar = Calendar.getInstance();
		} else {
			calendar = new GregorianCalendar();
			calendar.setTime(aDate);
		}
		return calendar;
	}

	/**
	 * 取得2个日期之间的天
	 */
	public static List<String> getDayBetweenTwoDate(Date startdate, Date enddate) {
		List<String> list = new ArrayList();

		Calendar cal_start = Calendar.getInstance();
		Calendar cal_end = Calendar.getInstance();
		cal_start.setTime(startdate);
		cal_end.setTime(enddate);
		// cal_end.set(cal_end.DAY_OF_YEAR,
		// cal_end.get(Calendar.DAY_OF_YEAR)+1);
		for (; cal_start.compareTo(cal_end) <= 0; cal_start.set(
				cal_start.DAY_OF_MONTH,
				cal_start.get(Calendar.DAY_OF_MONTH) + 1)) {
			String dd = new SimpleDateFormat("yyyy-MM-dd").format(cal_start
					.getTime());
			String util[] = dd.split("-");
			// System.out.println(util[0]+"年"+util[1]+"月"+util[2]+"日"+"  ;");
			list.add(util[0] + "-" + util[1] + "-" + util[2]);
		}

		return list;
	}

	/**
	 * 取得2个日期之间的月份
	 */
	public static List<String> getMonthBetweenTwoDate(Date startdate,
			Date enddate) {
		List<String> list = new ArrayList();

		Calendar cal_start = Calendar.getInstance();
		Calendar cal_end = Calendar.getInstance();
		cal_start.setTime(startdate);
		cal_start.set(cal_start.DATE, 1);

		String ddd = new SimpleDateFormat("yyyy-MM-dd").format(cal_start
				.getTime());
		cal_end.setTime(enddate);
		// cal_end.set(cal_end.MONTH, cal_end.get(Calendar.MONTH)+1);
		for (; cal_start.compareTo(cal_end) <= 0; cal_start.set(
				cal_start.MONTH, cal_start.get(Calendar.MONTH) + 1)) {
			String dd = new SimpleDateFormat("yyyy-MM-dd").format(cal_start
					.getTime());
			// System.out.println(dd);
			String util[] = dd.split("-");
			list.add(util[0] + "-" + util[1]);
		}

		return list;
	}

	/**
	 * 根据当前时间和参数 获取某个的日期，正数表示加，负数表示倒退
	 * 
	 * @param startdate
	 *            起始时间yyyy-mm-dd
	 * @param y
	 *            年
	 * @param m
	 *            月
	 * @param d
	 *            日
	 * @return yy-mm-dd
	 * @throws ParseException
	 */
	public static String calDate(String startdatestring, int y, int m, int d)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = sdf.parse(startdatestring);
		Calendar cal_start = Calendar.getInstance();
		cal_start.setTime(startdate);
		if (!StringUtil.isNull(y))
			cal_start.add(cal_start.YEAR, y);
		if (!StringUtil.isNull(m))
			cal_start.add(cal_start.MONTH, m);
		if (!StringUtil.isNull(d))
			cal_start.add(cal_start.DATE, d);
		String dd = new SimpleDateFormat("yyyy-MM-dd").format(cal_start
				.getTime());
		// System.out.println(dd);
		return dd;
	}

	/*
	 * 传月2009－09返回本月最后一天
	 */
	public static String lastDayOfDate(String startdatestring)
			throws ParseException {
		String[] date = startdatestring.split("-");
		String year = date[0];
		int month = Integer.parseInt(date[1]) + 1;
		String datebase = "";
		if (month < 10) {
			datebase = year + "-0" + month + "-01";
		} else {
			datebase = year + "-" + month + "-01";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = sdf.parse(datebase);
		Calendar cal_start = Calendar.getInstance();
		cal_start.setTime(startdate);
		cal_start.add(cal_start.DATE, -1);
		String dd = new SimpleDateFormat("yyyy-MM-dd").format(cal_start
				.getTime());

		return dd;
	}

	/**
	 * 取得2个日期之间的年
	 */
	public static List<String> getYearBetweenTwoDate(Date startdate,
			Date enddate) {
		List<String> list = new ArrayList();

		Calendar cal_start = Calendar.getInstance();
		Calendar cal_end = Calendar.getInstance();
		cal_start.setTime(startdate);
		cal_end.setTime(enddate);
		// cal_end.set(cal_end.YEAR, cal_end.get(Calendar.YEAR)+1);
		for (; cal_start.compareTo(cal_end) <= 0; cal_start.set(cal_start.YEAR,
				cal_start.get(Calendar.YEAR) + 1)) {
			String dd = new SimpleDateFormat("yyyy-MM-dd").format(cal_start
					.getTime());
			String util[] = dd.split("-");
			list.add(util[0]);
		}
		return list;
	}

	/**
	 * 取得2个日期之间的年月
	 */
	public static List<String> getYearMonthBetweenTwoDate(Date startdate,
			Date enddate) {
		List<String> list = new ArrayList();

		Calendar cal_start = Calendar.getInstance();
		Calendar cal_end = Calendar.getInstance();
		cal_start.setTime(startdate);
		cal_end.setTime(enddate);
		// cal_end.set(cal_end.YEAR, cal_end.get(Calendar.YEAR)+1);
		for (; cal_start.compareTo(cal_end) <= 0; cal_start.set(
				cal_start.MONTH, cal_start.get(Calendar.MONTH) + 1)) {
			String dd = new SimpleDateFormat("yyyyMM").format(cal_start
					.getTime());

			list.add(dd);
		}
		return list;
	}

	/**
	 * 取得当前年的前一年的最后一天,或前一月的最后一天,或前一天
	 * 
	 * @param flag
	 *            y,m,d
	 */
	public static String getDateForLastYearOrMonthOrDay(String flag) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String returnResults = "";

		if ("y".equals(flag)) {
			cal.set(cal.MONTH, 0);
			cal.set(cal.DATE, 1);
			cal.add(cal.DATE, -1);
			returnResults = sdf.format(cal.getTime());
		} else if ("m".equals(flag)) {
			// cal.set(cal.MONTH,-1);
			cal.set(cal.DATE, 1);
			cal.add(cal.DATE, -1);
			returnResults = sdf.format(cal.getTime());
		} else {
			cal.add(cal.DATE, -1);
			returnResults = sdf.format(cal.getTime());
		}
		return returnResults;
	}

	// 计算相差天数
	public static int daysOfTwo(Date fDate, Date oDate) {
		Calendar cal1 = Calendar.getInstance();
        cal1.setTime(fDate);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(oDate);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            return day2-day1;
        }
	}

	public static long daysOfSecond(Date fDate, Date oDate) {

		long second1 = fDate.getTime();
		long second2 = oDate.getTime();

		return (second2 - second1) / 1000;
	}

	public static String timestampToString(Timestamp timestamp)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(timestamp);
	}

	public static Date getDateFormat(String strDate, String type) {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat(type);
		try {
			date = df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static List<Map<String, String>> getDates(Date fromDate, Date toDate)
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
			dates.add(dateItem);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			tmpDate = calendar.getTime();
		}

		return dates;
	}

	public static String getPreDate(Date date, int d) throws ParseException {

		Calendar c = Calendar.getInstance();
		String preDate = "";
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, d);
		preDate = formatDate(c.getTime(), FORMAT_2);

		return preDate;
	}

	public static String Date2TimeStamp(String dateStr, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 计算给定时间与当前时间相隔的年数
	 * 
	 * @param @param dateStr
	 * @param @param format
	 * @param @return
	 * @param @throws ParseException
	 * @return 一个Map类型记录
	 * @throws
	 * @author:
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */

	public static int getDateAndCurDateYear(String dateStr, String format)
			throws ParseException {

		Date date = getDateFromDateString(dateStr, format);
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(date);
		int yearDate = cal.get(Calendar.YEAR);
		int monthDate = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthDate = cal.get(Calendar.DAY_OF_MONTH);

		int year = yearNow - yearDate;

		if (monthNow <= monthDate) {
			if (monthNow == monthDate) {

				if (dayOfMonthNow < dayOfMonthDate) {
					year--;
				}
			} else {

				year--;
			}
		}
		return year;

	}

	public static int getDay(Date date) {

		Calendar calendar = initCalendar(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		return day;
	}

	public static int getMonthDiffer(Date startDate, Date endDate) {

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(startDate);
		c2.setTime(endDate);
		int startMonth = c1.get(Calendar.MONTH) + 1;
		int endMonth = c2.get(Calendar.MONTH) + 1;

		int differCount = endMonth - startMonth
				+ (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;

		return differCount;
	}

	public static boolean checkIsQuarterStartMonth(String monthStr) {

		boolean isStartMonth = false;
		Date monthDate = getDateFormat(monthStr, FORMAT_3);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(monthDate);
		int month = c1.get(Calendar.MONTH) + 1;
		isStartMonth = (month == 1) || (month == 4) || (month == 7)
				|| (month == 10);

		return isStartMonth;
	}

	public static Date getQuarterStartTime(String monthStr) {

		Calendar c = Calendar.getInstance();
		Date monthDate = getDateFormat(monthStr, FORMAT_3);
		c.setTime(monthDate);
		int month = c.get(Calendar.MONTH) + 1;
		if (month >= 1 && month <= 3)
			c.set(Calendar.MONTH, 0);
		else if (month >= 4 && month <= 6)
			c.set(Calendar.MONTH, 3);
		else if (month >= 7 && month <= 9)
			c.set(Calendar.MONTH, 6);
		else if (month >= 10 && month <= 12)
			c.set(Calendar.MONTH, 9);
		c.set(Calendar.DATE, 1);

		return c.getTime();
	}

	public static Date getQuarterEndTime(String monthStr) {

		Calendar c = Calendar.getInstance();
		Date monthDate = getDateFormat(monthStr, FORMAT_3);
		c.setTime(monthDate);
		int month = c.get(Calendar.MONTH) + 1;
		if (month >= 1 && month <= 3) {
			c.set(Calendar.MONTH, 2);
			c.set(Calendar.DATE, 31);
		} else if (month >= 4 && month <= 6) {
			c.set(Calendar.MONTH, 5);
			c.set(Calendar.DATE, 30);
		} else if (month >= 7 && month <= 9) {
			c.set(Calendar.MONTH, 8);
			c.set(Calendar.DATE, 30);
		} else if (month >= 10 && month <= 12) {
			c.set(Calendar.MONTH, 11);
			c.set(Calendar.DATE, 31);
		}

		return c.getTime();
	}
	
	public static long getTwoTimeDiff(Date startDate, Date endDate) {
		
		return endDate.getTime() - startDate.getTime();
	}
}
