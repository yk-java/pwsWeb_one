package com.glens.pwCloudOs.om.deviceMgr.deviceHandle.service;

import java.util.Random;

import com.glens.eap.platform.afinal.FinalHttp;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://api.map.baidu.com/geocoder/v2/?ak=3a345e5b8b56a7f80605cd95d64634a8&output=json&address=%s&city=南京";
		FinalHttp httpClient = new FinalHttp();
		for (int i = 0; i < 10000; i++) {
			String url1 = String.format(url, "江苏路");
			Object geoResult = httpClient.getSync(url1, null);
			System.out.println(geoResult + "," + i);
		}

	}
}
