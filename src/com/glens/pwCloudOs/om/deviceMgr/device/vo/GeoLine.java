/**
 * @Title: GeoLine.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.device.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-10-13 下午3:47:36
 * @version V1.0
 */


package com.glens.pwCloudOs.om.deviceMgr.device.vo;

import com.glens.eap.platform.core.beans.ValueObject;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class GeoLine implements ValueObject {

	private GeoPoint startPoint;
	
	private GeoPoint endPoint;
	
	/**
	  
	 * 创建一个新的实例 GeoLine. 

	 * <p>Title: </p>

	 * <p>Description: </p>

	 **/

	public GeoLine(GeoPoint startPoint, GeoPoint endPoint) {

		// TODO Auto-generated constructor stub
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public GeoPoint getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(GeoPoint startPoint) {
		this.startPoint = startPoint;
	}

	public GeoPoint getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(GeoPoint endPoint) {
		this.endPoint = endPoint;
	}
	
}
