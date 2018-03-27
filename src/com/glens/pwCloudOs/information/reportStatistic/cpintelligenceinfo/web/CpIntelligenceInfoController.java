package com.glens.pwCloudOs.information.reportStatistic.cpintelligenceinfo.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.pwCloudOs.information.reportStatistic.cpintelligenceinfo.service.CpIntelligenceInfoService;

@FormProcessor(clazz = "com.glens.pwCloudOs.information.reportStatistic.cpintelligenceinfo.web.CpIntelligenceInfoForm")
@RequestMapping("/pmsServices/information/reportStatistic")
public class CpIntelligenceInfoController extends EAPJsonAbstractController {

	@RequestMapping(method = RequestMethod.GET, value = "static")
	public Object static1(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CpIntelligenceInfoService cpintelligenceinfoservice = (CpIntelligenceInfoService) EAPContext
						.getContext().getBean("cpIntelligenceInfoService");

				CpIntelligenceInfoForm form = (CpIntelligenceInfoForm) actionForm;

				String ticket = request.getParameter("ticket");

				Map result = cpintelligenceinfoservice.queryList(form);

				return result;
			}

		});
	}
}
