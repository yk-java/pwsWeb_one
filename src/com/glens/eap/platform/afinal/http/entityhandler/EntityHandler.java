/**
 * @Title: EntityHandler.java
 * @Package com.glens.eap.platform.afinal.http.entityhandler
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-10-10 下午5:27:02
 * @version V1.0
 */


package com.glens.eap.platform.afinal.http.entityhandler;

import java.io.IOException;

import org.apache.http.HttpEntity;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public interface EntityHandler {

	public Object handleEntity(HttpEntity entity, EntityCallBack callback,String charset)throws IOException;
	
}
