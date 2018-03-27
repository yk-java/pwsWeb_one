package com.glens.pwCloudOs.pm.scene.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.pwCloudOs.pm.scene.spot.service.ProSpotService;

@RequestMapping("pmsServices/pm/sceneEnv")
@FormProcessor(clazz = "com.glens.pwCloudOs.pm.scene.web.PmSceneEnvForm")
public class PmSceneEnvController extends EAPJsonAbstractController {

	private ProSpotService proSpotService;

	@Override
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmSceneEnvForm form = (PmSceneEnvForm) actionForm;
				String reportEmployeeCode = form.getReportEmployeecode();
				form.setSpotCode(proSpotService
						.queryProspotCode(reportEmployeeCode));

				boolean ok = getService().insert(form.toVo());
				KeyResult result = new KeyResult();
				if (ok) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败");
				}

				return result;
			}

		});
	}

	public void setProSpotService(ProSpotService proSpotService) {
		this.proSpotService = proSpotService;
	}

}
