package com.glens.eap.platform.framework.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;

import org.springframework.util.StringUtils;

public abstract class EAPFilter implements Filter {

	/**
	 * 获取过滤器参数值，带有默认值，若没有配置，则使用默认值。
	 * @param filterConfig
	 * @param paramName
	 * @param defalutValue
	 * @return
	 */
	protected String getInitParameterWithDefalutValue(FilterConfig filterConfig, String paramName, String defalutValue){
		String value = filterConfig.getInitParameter(paramName);
		if(StringUtils.isEmpty(value)){
			value = defalutValue;
		}
		return value;
	}

}
