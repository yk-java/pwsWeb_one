package com.glens.eap.eapAuth.core.authentication.impl;

import java.util.Map;

/** 
 * <p>默认的用户主体对象。</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public class DefaultUserPrincipal extends AbstractPrincipal {

	public DefaultUserPrincipal() {
		super();
	}

	public DefaultUserPrincipal(String id, Map<String, Object> attributes) {
		super(id, attributes);
	}
	
}
