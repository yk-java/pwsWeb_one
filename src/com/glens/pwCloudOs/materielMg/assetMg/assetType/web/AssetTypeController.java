/**
 * @Title: AssetTypeController.java
 * @Package com.glens.pwCloudOs.materielMg.assetMg.assetType.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-22 上午11:29:09
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.assetMg.assetType.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.materielMg.assetMg.assetType.service.AssetTypeService;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@FormProcessor(clazz="com.glens.pwCloudOs.materielMg.assetMg.assetType.web.AssetTypeForm")
@RequestMapping("pmsServices/materielMg/assetMg/assetType")
public class AssetTypeController extends EAPJsonAbstractController {
	
	@RequestMapping(value="typelist", method=RequestMethod.GET)
	public Object typelist(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				AssetTypeService  ser=(AssetTypeService)getService();
				List list=ser.getTypeList(actionForm.toMap());
				
				PageBeanResult result = new PageBeanResult();
				if (list != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(list);
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
