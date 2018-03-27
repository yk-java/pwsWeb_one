/**
 * @Title: GeoPoint.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.device.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-10-13 下午2:50:21
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

public class GeoPoint implements ValueObject {

	private double x;
	
	private double y;
	
	/**
	  
	 * 创建一个新的实例 GeoPoint. 

	 * <p>Title: </p>

	 * <p>Description: </p>

	 **/

	public GeoPoint() {

		// TODO Auto-generated constructor stub
	}
	
	public GeoPoint(double x, double y) {
		
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
