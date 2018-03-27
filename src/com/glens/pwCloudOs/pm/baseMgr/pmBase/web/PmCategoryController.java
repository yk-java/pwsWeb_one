/**
 * @Title: PmBaseController.java
 * @Package com.glens.pwCloudOs.pm.baseMgr.pmBase.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 上午10:55:26
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.baseMgr.pmBase.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.service.PmBaseService;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@FormProcessor(clazz="com.glens.pwCloudOs.pm.baseMgr.pmBase.web.PmCategoryForm")
@RequestMapping("pmsServices/pm/baseMgr/pmCategory")
public class PmCategoryController extends EAPJsonAbstractController {

	@Override
	@RequestMapping(value="add", method=RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmCategoryForm form = (PmCategoryForm)actionForm;
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				String categoryCode=codeWorker.createCode("C");
				form.setCategoryCode(categoryCode);
				boolean ok = getService().insert(form.toVo());
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
	}

	
	
}
