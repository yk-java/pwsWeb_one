package com.glens.eap.eapAuth.core.authentication.impl;

import java.util.Map;

import com.glens.eap.eapAuth.core.authentication.Parameter;

/** 
 * 抽象的参数化实现类。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public abstract class AbstractParameter implements Parameter {

	/**
	 * 其它参数表。
	 */
	protected Map<String, Object> paramters;
	
	@Override
	public Object getParameterValue(String paramName) {
		return this.paramters==null?null:this.paramters.get(paramName);
	}

	@Override
	public Map<String, Object> getParameters() {
		return this.paramters;
	}

	@Override
	public void setParameters(Map<String, Object> parameters) {
		this.paramters = parameters;
	}
	
}
