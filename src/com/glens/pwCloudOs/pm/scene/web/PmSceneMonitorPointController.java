package com.glens.pwCloudOs.pm.scene.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.pm.scene.service.PmSceneMonitorPointService;
import com.glens.pwCloudOs.pm.scene.vo.PmSceneMonitorPoint;

@RequestMapping("pmsServices/pm/monitorPoint")
@FormProcessor(clazz = "com.glens.pwCloudOs.pm.scene.web.PmSceneMonitorPointForm")
public class PmSceneMonitorPointController extends EAPJsonAbstractController {
	@RequestMapping(value="monitorRealData", method=RequestMethod.GET)
	public Object monitorRealData(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				
				String spotCode = request.getParameter("spotCode");
				PmSceneMonitorPointService service = (PmSceneMonitorPointService)getService();
				List<PmSceneMonitorPoint> res = service.getMonitorRealData(spotCode);
				ListResult result = new ListResult();
				if (res != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(res);
				}
				else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				
				return result;
			}
			
		});
	}
}
