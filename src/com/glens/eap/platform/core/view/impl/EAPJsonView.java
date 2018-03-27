package com.glens.eap.platform.core.view.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.glens.eap.platform.core.view.EAPView;

public class EAPJsonView extends EAPView {

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return "application/json;charset=UTF-8";
	}

	@Override
	public void render(Map<String, ?> arg0, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setContentType(this.getContentType());
		response.setHeader("Cache-Control", "no-cache");
		OutputStream out = null;
		String resposeContent = JSON.toJSONStringWithDateFormat(this.getData(),
				JSON.DEFFAULT_DATE_FORMAT,
				SerializerFeature.DisableCircularReferenceDetect);
		try {
			out = response.getOutputStream();
			out.write(resposeContent.getBytes("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (out != null) {
				
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}

}
