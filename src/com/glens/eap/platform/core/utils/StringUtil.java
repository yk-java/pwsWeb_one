/** 
 * StringUtil.java Created on May 15, 2009
 * Copyright oooo3d 
 * All right reserved. 
 */
package com.glens.eap.platform.core.utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @Time 5:01:04 PM
 * @author 
 */
/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiang.chen
 * @see         [相关类/方法]（可选）
 * @since      [产品/模块版本] （必须）
 */
/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiang.chen
 * @see         [相关类/方法]（可选）
 * @since      [产品/模块版本] （必须）
 */
/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author qiang.chen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （必须）
 */
public class StringUtil {

	public static final String SPLIT_COMMA_STRING = "#%2C#";
	public static final String SPLIT_COMMA = ",";
	public static final String SPLIT_VERTICAL_STRING = "#%7C#";
	public static final String SPLIT_VERTICAL = "|";
	public static final String SPLIT_RETURNANDCHANGEROW = "\r\n";
	public static final String HTML_QUOT_STRING = "&quot;";
	public static final String HTML_QUOT = "\"";
	public static final String HTML_AMP_STRING = "&amp;";
	public static final String HTML_AMP = "&";
	public static final String HTML_LT_STRING = "&lt;";
	public static final String HTML_LT = "<";
	public static final String HTML_GT_STRING = "&gt;";
	public static final String HTML_GT = ">";

	/**
	 * 字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(Object str) {

		return (str == null || str.toString().trim().length() <= 0);
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(Object str) {

		return (str != null && str.toString().trim().length() > 0);
	}

	public static String filterStringForCell(String input) {
		if (isNull(input))
			return "";
		return input.replaceAll(SPLIT_RETURNANDCHANGEROW, " ").replaceAll(
				HTML_QUOT, "“");
	}

	/**
	 * Object to String
	 * 
	 * @param obj
	 * @return
	 */
	public static String getStr(Object obj) {

		return obj != null ? String.valueOf(obj) : "";
	}

	/**
	 * 使用Map中的value替换Input中的Key
	 * 
	 * @param input
	 * @param m
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String filter(String input, Map m) {
		if (isNull(input))
			return "";
		if (m == null)
			return input.replaceAll(SPLIT_COMMA_STRING, SPLIT_COMMA)
					.replaceAll(SPLIT_VERTICAL_STRING, SPLIT_VERTICAL);
		Set st = m.keySet();
		Iterator<String> iter = st.iterator();
		while (iter.hasNext()) {
			String mk = iter.next();
			input = input.replaceAll(mk, (String) m.get(mk));
		}
		return input;
	}

	public static String filterHtml(String input) {
		if (!hasSpecialChars(input)) {
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			default:
				filtered.append(c);
			}
		}
		return filtered.toString();
	}

	private static boolean hasSpecialChars(String input) {
		boolean flag = false;
		if (input != null && input.length() > 0) {
			char c;
			for (int i = 0; i < input.length(); i++) {
				c = input.charAt(i);
				switch (c) {
				case '<':
					flag = true;
					break;
				case '>':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 判断两个字符是否相等
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean isEqual(String s1, String s2) {
		if (s1 == null && s2 == null)
			return true;
		if (s1 == null)
			return false;
		return (s1.compareTo(s2) == 0) ? true : false;
	}

	/**
	 * 字符串是否全是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		if (str != null) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(str);
			return isNum.matches();
		} else {
			return false;
		}

	}

	public static boolean isNum(char ch) {
		return Character.isDigit(ch);
	}

	/**
	 * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
	 * 
	 * @param c
	 *            需要判断的字符
	 * @return 返回true,Ascill字符
	 */
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 字符串是否以数字结尾
	 * 
	 * @param str
	 * @return
	 */
	public static boolean endWithNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]+$");
		Matcher haveNum = pattern.matcher(str);
		return haveNum.find();
	}

	/**
	 * 将字符串中的表达式分割出来(表达式分隔符不能嵌套)
	 * 
	 * @param str
	 *            字符串
	 * @param startFlag
	 *            开始标记
	 * @param endFlag
	 *            结束标记
	 * @return 表达式列
	 */
	public static Set<String> splitBySBrackets(String str, String startFlag,
			String endFlag) {
		str = str.trim();
		Set<String> names = new TreeSet<String>();
		int sj = str.indexOf(startFlag);
		int sk = str.indexOf(endFlag, sj);
		while ((sj > -1) && (sk > sj)) {
			String tn = str.substring(sj, ++sk);
			names.add(tn);
			str = str.substring(sk);
			sj = str.indexOf(startFlag);
			sk = str.indexOf(endFlag, sj);
		}
		return names;
	}

	/**
	 * 获得首字母
	 * 
	 * @param s
	 * @return
	 */
	public static String getInitial(String s) {
		return s.substring(0, 1);
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param s
	 *            需要得到长度的字符串
	 * @return 得到的字符串长度
	 */
	public static int Len(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
		// return s.getBytes().length;
	}

	/**
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	 * 
	 * @param origin
	 *            原始字符串
	 * @param len
	 *            截取长度(一个汉字长度按2算的)
	 * @param c
	 *            后缀
	 * @return 返回的字符串
	 */
	public static String substring(String origin, int len, String c) {
		if (origin == null || origin.equals("") || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > Len(origin)) {
			return origin + c;
		}
		try {
			System.arraycopy(origin.getBytes("GBK"), 0, strByte, 0, len);
			int count = 0;
			for (int i = 0; i < len; i++) {
				int value = (int) strByte[i];
				if (value < 0) {
					count++;
				}
			}
			if (count % 2 != 0) {
				len = (len == 1) ? ++len : --len;
			}
			return new String(strByte, 0, len, "GBK") + c;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static int f(int x) {
		int c = 0;
		while (x != 0) {
			System.out.print(x + "||" + (x - 1) + " is");
			System.out.print(x + "->");
			x = x & (x - 1);
			System.out.println(x);
			c++;
		}
		return c;
	}

	public static long get() {
		long c = 0;
		char a = 0x48;
		char b = 0x52;

		c = b << 8 | a;
		return c;
	}

	/**
	 * 替换字符
	 * 
	 * @param parentStr
	 * @param ch
	 *            被替换字符
	 * @param rep
	 *            代替字符
	 * @return
	 */
	public static String replaceStr(String parentStr, String ch, String rep) {
		int i = parentStr.indexOf(ch);
		StringBuffer sb = new StringBuffer();
		if (i == -1)
			return parentStr;
		sb.append(parentStr.substring(0, i) + rep);
		if (i + ch.length() < parentStr.length())
			sb.append(replaceStr(
					parentStr.substring(i + ch.length(), parentStr.length()),
					ch, rep));
		return sb.toString();
	}

	/**
	 * 
	 * @param arg
	 *            格式：176839703545251841,0|176839703545251841,0|
	 *            176839703545251841 ,0
	 * @return
	 */
	public static List<Long> parseString(String arg) {
		List<Long> resultList = new ArrayList<Long>();
		String regex = "(([123456789]\\d*),[01])(\\|[123456789]\\d*,[01])*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(arg);
		if (m.matches()) {
			boolean b = m.find(0);
			while (b) {
				int nStart = m.end(1);
				long v = Long.valueOf(m.group(2));
				resultList.add(v);
				b = m.find(nStart);
			}
		}
		return resultList;
	}

	/**
	 * 
	 * 过滤掉字符窜中的“”和‘
	 */
	public static String tripChar(String resource) {
		if (null == resource)
			return resource;
		if (resource.indexOf("'") >= 0) {
			resource = resource.replace("'", "");
		}
		if (resource.indexOf("\"") >= 0) {
			resource = resource.replace("\"", "");
		}
		return resource;
	}

	/**
	 * 
	 * 整数数组转字符串数组
	 */
	public static String[] swtichIntToString(Integer[] arr, String per) {
		int len = arr.length;
		String[] s = new String[len];
		for (int i = 0; i < len; i++) {
			s[i] = per + arr[i];
		}
		return s;
	}

	/**
	 * 是否为经纬度
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDegree(String str) {
		if (str != null) {
			String reg = "(\\d{0,3}\\.\\d{1,6})||(\\d{0,3})||(0\\.\\d{1,6})";
			return str.matches(reg);
		} else {
			return false;
		}
		// str.matches(reg);

	}

	/**
	 * 获取查询条件
	 * 
	 * @return
	 */
	public static String getConditionByArr(StringBuffer sql, int[] o) {

		if (o != null) {
			int len = o.length;
			// StringBuffer sb = new StringBuffer();
			if (len == 1) {
				return sql.append("=?").toString();
			} else if (len > 1) {
				sql.append("in (");
				for (int i = 0; i < len; i++) {
					sql.append("?");
					if (i < len - 1) {
						sql.append(",");
					}
				}
				sql.append(")");
				return sql.toString();
			}
			return null;
		} else {
			return null;
		}
	}

	/**
	 * 检验是否为电话
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		String reg = "^\\d{3,4}(\\-)?[1-9]\\d{7,8}$";
		if (!isNull(phone)) {
			return phone.trim().matches(reg);
		}
		return false;

	}

	/**
	 * 检验是否为移动电话
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		String reg = "^(\\+\\d{2,3}\\-)?\\d{11}$";
		if (!isNull(mobile)) {
			return mobile.trim().matches(reg);
		}
		return false;

	}

	public static boolean isMail(String mail) {
		String reg = "^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";
		if (!isNull(mail)) {
			return mail.trim().matches(reg);
		}
		return false;
	}

	/**
	 * 是否整数
	 * 
	 * @param num
	 *            整数值
	 * @param max
	 *            开始
	 * @param min结束
	 * @return
	 */
	public static boolean isZs(String num, int max, int min) {
		String reg = "^(\\d{" + max + "," + min + "})$";
		if (isNotNull(num)) {
			return num.trim().matches(reg);
		}
		return false;
	}

	/**
	 * 是否小数
	 * 
	 * @param num
	 *            整数值
	 * @param b
	 *            整数位
	 * @param e
	 *            小数位
	 * @return
	 */
	public static boolean isNum(String num, int b, int e) {
		String reg = "^((\\d{1," + b + "}\\.\\d{1," + e + "})|(\\d{1," + b
				+ "})|(0\\.\\d{1," + e + "}))$";
		if (isNotNull(num)) {
			return num.trim().matches(reg);
		}
		return false;
	}

	/**
	 * 是否地址
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isURL(String url) {
		if (isNotNull(url)) {
			return false;
		}
		String regEx = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
				+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
				+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
				+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
				+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
				+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
				+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
				+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
		// Pattern p = Pattern.compile(regEx);
		// Matcher matcher = p.matcher(url);
		return url.trim().matches(regEx);
	}

	/**
	 * 是否为一定长度的英文和数字的组合
	 * 
	 * @param str
	 *            检验的字符串
	 * @param min
	 *            开始
	 * @param max
	 *            结束
	 * @return
	 */
	public static boolean isNW(String str, int min, int max) {
		String reg = "^[A-Za-z0-9]{" + min + "," + max + "}$";
		if (isNotNull(str)) {
			System.out.println(str.trim().matches(reg));
			return str.trim().matches(reg);
		}
		return false;

	}

	/**
	 * 
	 * <p>
	 * 检验字符串是否以id分隔的
	 * </p>
	 * 
	 * @return
	 * @author liujian.zhang
	 * @date 2013-12-3下午12:29:50
	 */
	public static boolean isIds(String ids) {
		String reg = "[0-9]{1,11}(,[0-9]{1,11})*";

		if (isNotNull(ids)) {
			return ids.trim().matches(reg);
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * </p>
	 * 
	 * @param ids
	 * @return
	 * @author 张柳健
	 * @date 2014-6-26下午7:50:04
	 */
	public static Float parseFloat(String f) {

		if (isNotNull(f) && isNum(f, 11, 2)) {
			return Float.parseFloat(f);
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * </p>
	 * 
	 * @param ids
	 * @return
	 * @author 张柳健
	 * @date 2014-6-26下午7:50:04
	 */
	public static Integer parseInt(String f) {

		if (isNotNull(f) && isZs(f, 1, 11)) {
			return Integer.parseInt(f);
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * </p>
	 * 
	 * @param ids
	 * @return
	 * @author 张柳健
	 * @date 2014-6-26下午7:50:04
	 */
	public static Long parseLong(String f) {

		if (isNotNull(f) && isZs(f, 1, 20)) {
			return Long.parseLong(f);
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * </p>
	 * 
	 * @param ids
	 * @return
	 * @author 张柳健
	 * @date 2014-6-26下午7:50:04
	 */
	public static Byte parseByte(String f) {

		if (isNotNull(f) && isNum(f, 2, 0)) {
			return Byte.parseByte(f);
		}
		return null;
	}

	public static String StringToZw(String str) {
		String utf = null;
		if (isNotNull(str)) {
			try {
				utf = new String(str.getBytes("iso8859-1"), "gb2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return utf;
	}

	public static String createCode() {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
		String uuidCode = uuid.toString();
		uuidCode = uuidCode.replaceAll("-", "");
		uuidCode = uuidCode.substring(0, 20);
		return uuidCode;
	}

	// 获取季度 格式 "年 - 季度 - 月"
	public static String getJDMonth(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = sdf.format(date);
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		int month = now.get(Calendar.MONTH) + 1;
		if (month == 1 || month == 2 || month == 3) {
			nowTime = nowTime.substring(0, 4) + "-1-" + month;
		} else if (month == 4 || month == 5 || month == 6) {
			nowTime = nowTime.substring(0, 4) + "-2-" + month;
		} else if (month == 7 || month == 8 || month == 9) {
			nowTime = nowTime.substring(0, 4) + "-3-" + month;
		} else if (month == 10 || month == 11 || month == 12) {
			nowTime = nowTime.substring(0, 4) + "-4-" + month;
		}
		return nowTime;
	}

	// 获取季度 格式 "年 - 季度"
	public static String getJD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = sdf.format(date);
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		int month = now.get(Calendar.MONTH) + 1;
		if (month == 1 || month == 2 || month == 3) {
			nowTime = nowTime.substring(0, 4) + "-1";
		} else if (month == 4 || month == 5 || month == 6) {
			nowTime = nowTime.substring(0, 4) + "-2";
		} else if (month == 7 || month == 8 || month == 9) {
			nowTime = nowTime.substring(0, 4) + "-3";
		} else if (month == 10 || month == 11 || month == 12) {
			nowTime = nowTime.substring(0, 4) + "-4";
		}
		return nowTime;
	}
	
	public static String join(Object[] array, char separator)
	  {
	    if (array == null) {
	      return null;
	    }
	    int arraySize = array.length;
	    int bufSize = arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0].toString().length()) + 1) * arraySize;
	    StringBuffer buf = new StringBuffer(bufSize);

	    for (int i = 0; i < arraySize; i++) {
	      if (i > 0) {
	        buf.append(separator);
	      }
	      if (array[i] != null) {
	        buf.append(array[i]);
	      }
	    }
	    return buf.toString();
	  }

	  public static String join(Object[] array, String separator)
	  {
	    if (array == null) {
	      return null;
	    }
	    if (separator == null) {
	      separator = "";
	    }
	    int arraySize = array.length;

	    int bufSize = arraySize == 0 ? 0 : arraySize * ((array[0] == null ? 16 : array[0].toString().length()) + (separator != null ? separator.length() : 0));

	    StringBuffer buf = new StringBuffer(bufSize);

	    for (int i = 0; i < arraySize; i++) {
	      if ((separator != null) && (i > 0)) {
	        buf.append(separator);
	      }
	      if (array[i] != null) {
	        buf.append(array[i]);
	      }
	    }
	    return buf.toString();
	  }
}
