/**
 * @Title: GeoPolygon.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.device.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-10-13 下午2:38:32
 * @version V1.0
 */


package com.glens.pwCloudOs.om.deviceMgr.device.vo;

import java.util.ArrayList;
import java.util.List;

import com.glens.eap.platform.core.beans.ValueObject;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class GeoPolygon implements ValueObject {

	private String xs;
	
	private String ys;
	
	private List<GeoPoint> points;
	
	/**
	  
	 * 创建一个新的实例 GeoPolygon. 

	 * <p>Title: </p>

	 * <p>Description: </p>

	 **/

	public GeoPolygon(String xs, String ys) {

		// TODO Auto-generated constructor stub
		this.xs = xs;
		this.ys = ys;
		this.points = new ArrayList<GeoPoint>();
		this.build();
	}
	
	public GeoPolygon(List<GeoPoint> points) {
		
		this.points = points;
	}
	
	public GeoExtend getExtend() {
		
		GeoExtend extend = null;
		
		if (this.points != null && this.points.size() > 0) {
			
			double minx = 99999999;
			double miny = 99999999;
			double maxx = -99999999;
			double maxy = -99999999;
			
			for (GeoPoint p : this.points) {
				
				if (minx > p.getX()) minx = p.getX();
				if (miny > p.getY()) miny = p.getY();
				if (maxx < p.getX()) maxx = p.getX();
				if (maxy < p.getY()) maxy = p.getY();
			}
			
			if (minx < 99999999) {
				
				extend = new GeoExtend(minx, miny, maxx, maxy);
			}
		}
		
		return extend;
	}
	
	public int getPointCount() {
		
		int icount = 0;
		if (this.points != null) icount = this.points.size();
		
		return icount;
	}
	
	public GeoPoint getVertex(int index) {
		
		GeoPoint p = null;
		if (this.points != null && this.points.size() > index) {
			
			p = this.points.get(index);
		}
		
		return p;
	}
	
	private void build() {
		
		if (this.xs != null && !"".equals(this.xs) 
				&& this.ys != null && !"".equals(this.ys)) {
			
			String[] _xs = this.xs.split(",");
			String[] _ys = this.ys.split(",");
			for (int i = 0;i < _xs.length;i++) {
				
				double x = Double.parseDouble(_xs[i]);
				double y = Double.parseDouble(_ys[i]);
				
				GeoPoint point = new GeoPoint(x, y);
				this.points.add(point);
			}
		}
	}

	public List<GeoPoint> getPoints() {
		return points;
	}

	public void setPoints(List<GeoPoint> points) {
		this.points = points;
	}
	
}
