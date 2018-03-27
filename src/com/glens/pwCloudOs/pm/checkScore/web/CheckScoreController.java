package com.glens.pwCloudOs.pm.checkScore.web;

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
import com.glens.pwCloudOs.materielMg.vehicleMg.check.service.CheckService;
import com.glens.pwCloudOs.pm.checkScore.service.CheckScoreService;


@FormProcessor(clazz="com.glens.pwCloudOs.pm.checkScore.web.CheckScoreForm")
@RequestMapping("pmsServices/pm/checkScore")
public class CheckScoreController extends EAPJsonAbstractController {
	
	
	
	
	/*@RequestMapping(value="insertCheckScore", method=RequestMethod.POST)
	public Object insertCheckScore(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				CheckScoreForm form=(CheckScoreForm)actionForm;
				
				CheckScoreService ser=(CheckScoreService)getService();
				boolean ok = ser.insertCheckScore(form);
				KeyResult result = new KeyResult();
				if (ok) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				}
				else {
					
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败");
				}
				
				return result;
			}
			
		});
	}*/
	
	
}
