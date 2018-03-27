package com.glens.pwCloudOs.km.catalog.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.km.catalog.service.CatalogService;

@FormProcessor(clazz = "com.glens.pwCloudOs.km.catalog.web.CatalogForm")
@RequestMapping("pmsServices/kmCatalogService")
public class CatalogAppController extends EAPJsonAbstractController {
	private static Log logger = LogFactory.getLog(CatalogAppController.class);

	@RequestMapping(value = "queryKmCatalogList", method = RequestMethod.GET)
	public Object queryKmCatalogList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CatalogService ser = (CatalogService) getService();
				Map<String, Object> m = new HashMap<String, Object>();

				List list = ser.queryKmCatalogList();

				ListResult result = new ListResult();

				if (list != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(list);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}
		});
	}

}
