package com.glens.eap.platform.core.view.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.glens.eap.platform.core.view.EAPView;

public class EAPXmlView extends EAPView {

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return "application/xml";
	}

	@Override
	public void render(Map<String, ?> arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) throws Exception {
		// TODO Auto-generated method stub

	}

}
