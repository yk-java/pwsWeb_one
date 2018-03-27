/**
 * @Title: GeoExtend.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.device.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-10-13 下午2:34:56
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

public class GeoExtend implements ValueObject {

	private double minx;
	
	private double miny;
	
	private double maxx;
	
	private double maxy;
	
	/**
	  
	 * 创建一个新的实例 GeoExtend. 

	 * <p>Title: </p>

	 * <p>Description: </p>

	 **/

	public GeoExtend() {

		// TODO Auto-generated constructor stub
	}
	
	/**
	  
	 * 创建一个新的实例 GeoExtend. 

	 * <p>Title: </p>

	 * <p>Description: </p>

	 **/

	public GeoExtend(double minx, double miny, double maxx, double maxy) {

		// TODO Auto-generated constructor stub
		this.minx = minx;
		this.miny = miny;
		this.maxx = maxx;
		this.maxy = maxy;
	}

	public double getMinx() {
		return minx;
	}

	public void setMinx(double minx) {
		this.minx = minx;
	}

	public double getMiny() {
		return miny;
	}

	public void setMiny(double miny) {
		this.miny = miny;
	}

	public double getMaxx() {
		return maxx;
	}

	public void setMaxx(double maxx) {
		this.maxx = maxx;
	}

	public double getMaxy() {
		return maxy;
	}

	public void setMaxy(double maxy) {
		this.maxy = maxy;
	}
	
}
