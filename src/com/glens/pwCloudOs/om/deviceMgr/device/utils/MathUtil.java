/**
 * @Title: MathUtil.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.device.utils
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-10-13 下午3:50:15
 * @version V1.0
 */

package com.glens.pwCloudOs.om.deviceMgr.device.utils;

import com.glens.pwCloudOs.om.deviceMgr.device.vo.GeoLine;
import com.glens.pwCloudOs.om.deviceMgr.device.vo.GeoPoint;
import com.glens.pwCloudOs.om.deviceMgr.device.vo.GeoPolygon;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

public class MathUtil {

	private static final double INFINITY = 1e10;
	private static final double ESP = 1e-10;
	private static final int MAX_N = 1000;

	private static final double EARTH_RADIUS = 6378137;// 赤道半径(单位m)

	public static double multiply(GeoPoint p1, GeoPoint p2, GeoPoint p0) {

		return ((p1.getX() - p0.getX()) * (p2.getY() - p0.getY()) - (p2.getX() - p0
				.getX()) * (p1.getY() - p0.getY()));
	}

	public static boolean isOnline(GeoPoint p, GeoLine line) {

		return ((Math
				.abs(multiply(line.getStartPoint(), line.getEndPoint(), p)) < ESP)
				&& ((p.getX() - line.getStartPoint().getX())
						* (p.getX() - line.getEndPoint().getX()) <= 0) && ((p
				.getY() - line.getStartPoint().getY())
				* (p.getY() - line.getEndPoint().getY()) <= 0));
	}

	public static boolean intersect(GeoLine l1, GeoLine l2) {

		return ((Math.max(l1.getStartPoint().getX(), l1.getEndPoint().getX()) >= Math
				.min(l2.getStartPoint().getX(), l2.getEndPoint().getX()))
				&& (Math.max(l2.getStartPoint().getX(), l2.getEndPoint().getX()) >= Math
						.min(l1.getStartPoint().getX(), l1.getEndPoint().getX()))
				&& (Math.max(l1.getStartPoint().getY(), l1.getEndPoint().getY()) >= Math
						.min(l2.getStartPoint().getY(), l2.getEndPoint().getY()))
				&& (Math.max(l2.getStartPoint().getY(), l2.getEndPoint().getY()) >= Math
						.min(l1.getStartPoint().getY(), l1.getEndPoint().getY()))
				&& (multiply(l2.getStartPoint(), l1.getEndPoint(),
						l1.getStartPoint())
						* multiply(l1.getEndPoint(), l2.getEndPoint(),
								l1.getStartPoint()) >= 0) && (multiply(
				l1.getStartPoint(), l2.getEndPoint(), l2.getStartPoint())
				* multiply(l2.getEndPoint(), l1.getEndPoint(),
						l2.getStartPoint()) >= 0));
	}

	public static boolean inPolygon(GeoPolygon polygon, GeoPoint p) {

		boolean contain = false;
		int n = polygon.getPointCount();
		int count = 0;
		GeoPoint infinityPoint = new GeoPoint(-INFINITY, p.getY());
		GeoLine line = new GeoLine(p, infinityPoint);
		for (int i = 0; i < n; i++) {

			GeoPoint p1 = polygon.getVertex(i);
			GeoPoint p2 = polygon.getVertex((i + 1) % n);
			if (p1 != null && p2 != null) {

				GeoLine side = new GeoLine(p1, p2);
				if (isOnline(p, side)) {

					return true;
				}

				if (isOnline(side.getStartPoint(), line)) {

					if (side.getStartPoint().getY() > side.getEndPoint().getY())
						count++;
				} else if (isOnline(side.getEndPoint(), line)) {

					if (side.getEndPoint().getY() > side.getStartPoint().getY())
						count++;
				} else if (intersect(line, side)) {

					count++;
				}
			}
		}
		
		if (count % 2 == 1) {

			contain = true;
		}

		return contain;
	}

	public static double dis(double x1, double y1, double x2, double y2) {

		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	public static boolean inCircle(double x, double y, double cx, double cy,
			float r) {

		double _dis = LantitudeLongitudeDist(x, y, cx, cy);
		return _dis <= r;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	
	/** 
     * 基于余弦定理求两经纬度距离 
     * @param lon1 第一点的精度 
     * @param lat1 第一点的纬度 
     * @param lon2 第二点的精度 
     * @param lat3 第二点的纬度 
     * @return 返回的距离，单位km 
     * */  
    public static double LantitudeLongitudeDist(double lon1, double lat1,double lon2, double lat2) {  
        
    	double radLat1 = rad(lat1);  
        double radLat2 = rad(lat2);  
  
        double radLon1 = rad(lon1);  
        double radLon2 = rad(lon2);  
  
        if (radLat1 < 0)  
            radLat1 = Math.PI / 2 + Math.abs(radLat1);// south  
        if (radLat1 > 0)  
            radLat1 = Math.PI / 2 - Math.abs(radLat1);// north  
        if (radLon1 < 0)  
            radLon1 = Math.PI * 2 - Math.abs(radLon1);// west  
        if (radLat2 < 0)  
            radLat2 = Math.PI / 2 + Math.abs(radLat2);// south  
        if (radLat2 > 0)  
            radLat2 = Math.PI / 2 - Math.abs(radLat2);// north  
        if (radLon2 < 0)  
            radLon2 = Math.PI * 2 - Math.abs(radLon2);// west  
        double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);  
        double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);  
        double z1 = EARTH_RADIUS * Math.cos(radLat1);  
  
        double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);  
        double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);  
        double z2 = EARTH_RADIUS * Math.cos(radLat2);  
  
        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)+ (z1 - z2) * (z1 - z2));  
        //余弦定理求夹角  
        double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d) / (2 * EARTH_RADIUS * EARTH_RADIUS));  
        double dist = theta * EARTH_RADIUS;  
        return dist;  
    }

}
