package com.glens.eap.platform.framework.web.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.utils.RequestUtils;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.core.web.ProcessRequestHandler;

public abstract class AbstractProcessRequestHandler implements
		ProcessRequestHandler {

	@Override
	public Object doWithFinish(HttpServletRequest request,
			HttpServletResponse response, Object data, String viewType,
			EAPController controller) {
		// TODO Auto-generated method stub
		ModelAndView modelAndView = RequestUtils.pupolateView(viewType,
				request, response, controller, data);

		return modelAndView;
	}

}
