/**
 * @Title: BinaryEntityHandler.java
 * @Package com.glens.eap.platform.afinal.http.entityhandler
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-10-10 下午5:28:20
 * @version V1.0
 */


package com.glens.eap.platform.afinal.http.entityhandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class BinaryEntityHandler implements EntityHandler {

	private static final int BUFF_SIZE = 1024 * 1024;
	
	/**
	
	 * <p>Title: handleEntity</p>
	
	 * <p>Description: </p>
	
	 * @param entity
	 * @param callback
	 * @param charset
	 * @return
	 * @throws IOException
	
	 * @see com.glens.eap.platform.afinal.http.entityhandler.EntityHandler#handleEntity(org.apache.http.HttpEntity, com.glens.eap.platform.afinal.http.entityhandler.EntityCallBack, java.lang.String)
	
	 **/

	@Override
	public Object handleEntity(HttpEntity entity, EntityCallBack callback,
			String charset) throws IOException {
		// TODO Auto-generated method stub
		if (entity == null)
			return null;
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[BUFF_SIZE];
		
		long count = entity.getContentLength();
		long curCount = 0;
		int len = -1;
		InputStream is = entity.getContent();
		while ((len = is.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
			curCount += len;
			if(callback!=null)
				callback.callBack(count, curCount,false);
		}
		if(callback!=null)
			callback.callBack(count, curCount,true);
		byte[] data = outStream.toByteArray();
		outStream.close();
		is.close();
		return data;
	}

}
