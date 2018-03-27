package com.glens.eap.eapAuth.core.authentication.impl;

import java.util.Map;

import com.glens.eap.eapAuth.core.authentication.Principal;

/** 
 * <p>抽象的用户主体实现类。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public class AbstractPrincipal implements Principal {

	/**
	 * 用户主体的唯一标记。
	 */
	protected String id;
	
	/**
	 * 用户主体的其它属性表。
	 */
	protected Map<String, Object> attributes;
	
	public AbstractPrincipal() {
		super();
	}

	public AbstractPrincipal(String id, Map<String, Object> attributes) {
		super();
		this.id = id;
		this.attributes = attributes;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

}
