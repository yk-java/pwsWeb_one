/**
 * @Title: PmChangeController.java
 * @Package com.glens.pwCloudOs.pm.baseMgr.pmChange.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 下午12:10:16
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.baseMgr.pmChange.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.pm.baseMgr.pmChange.service.PmChangeService;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@FormProcessor(clazz="com.glens.pwCloudOs.pm.baseMgr.pmChange.web.PmChangeForm")
@RequestMapping("pmsServices/pm/baseMgr/pmChange")
public class PmChangeController extends EAPJsonAbstractController {

	@RequestMapping(method=RequestMethod.POST)
	public Object registeProChanged(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				
				PmChangeService pmChangeService = (PmChangeService) getService();
				boolean _registeResult = pmChangeService.registeProChanged(actionForm.toVo());
				ResponseResult result = new ResponseResult();
				if (_registeResult) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("变更项目成功！");
				}
				else {
					
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("变更项目失败！");
				}
				
				return result;
			}
		});
	}
	
}
