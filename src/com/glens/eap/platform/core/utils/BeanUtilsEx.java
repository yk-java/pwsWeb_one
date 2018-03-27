
package com.glens.eap.platform.core.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;

public class BeanUtilsEx extends BeanUtils {

	private static Logger logger = Logger.getLogger(BeanUtilsEx.class);

	static {
		ConvertUtils.register(new DateConvert(), java.util.Date.class);
		//ConvertUtils.register(new DateConvert(), String.class);
	}

	public static void copyProperties(Object target, Object source) {
		// 支持对日期copy
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(target,
					source);
		} catch (Exception e) {
			logger.error("扩展BeanUtils.copyProperties支持data类型:" + e.getMessage());
			e.printStackTrace();
		}
	}

}
