package com.glens.eap.eapAuth.core.authentication;

import java.util.Map;

/** 
 * <p>参数，定义了获得动态参数列表的接口。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public interface Parameter {

	/**
	 * 通过参数名获得参数值的方法。
	 * @param paramName 参数名。
	 * @return 对应的参数值。
	 */
	public Object getParameterValue(String paramName);
	
	
	/**
	 * 获得所有的参数表。
	 * @return 所有的参数列表。
	 */
	public Map<String, Object> getParameters();
	
	/**
	 * 设置参数列表。
	 * @param parameters 要设置的参数表。
	 */
	public void setParameters(Map<String, Object> parameters);
	
}
