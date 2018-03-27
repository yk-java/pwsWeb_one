package com.glens.eap.platform.framework.web.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.utils.RequestUtils;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;

public abstract class JsonProcessRequestHandler extends
	AbstractProcessRequestHandler {
	Log log=LogFactory.getLog(JsonProcessRequestHandler.class);
	@Override
	public Object doWithException(HttpServletRequest request,
			HttpServletResponse response,
			Exception e) {
		log.error("service is error:", e);
		// TODO Auto-generated method stub
		ResponseResult result = new ResponseResult();
		result.setResultMsg(e.getMessage());
		result.setStatusCode(ResponseConstants.SERVER_ERROR);
		
		ModelAndView modelAndView = RequestUtils.pupolateView(EAPView.JSON_VIEW,
				request, response, null, result);
		return modelAndView;
	}

}
