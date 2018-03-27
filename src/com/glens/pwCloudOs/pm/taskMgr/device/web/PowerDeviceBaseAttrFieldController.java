package com.glens.pwCloudOs.pm.taskMgr.device.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;

@FormProcessor(clazz="com.glens.pwCloudOs.pm.taskMgr.device.web.PowerDeviceBaseAttrFieldForm")
@RequestMapping("pmsServices/pm/taskMgr/device")
public class PowerDeviceBaseAttrFieldController extends EAPJsonAbstractController {

	@Override
	@RequestMapping(value="listAll", method=RequestMethod.GET)
	public Object listAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken)request.getSession().getAttribute(UserToken.KEY_TOKEN);
				Map map = actionForm.toMap();
				map.put("companyCode", token.getCompanyCode());
				List res = getService().queryForList(map);
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
	
	@Override
	@RequestMapping(value="list", method=RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken)request.getSession().getAttribute(UserToken.KEY_TOKEN);
				Map map = actionForm.toMap();
				map.put("companyCode", token.getCompanyCode());
				PageBean page = getService().queryForPage(map, 
							actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(page);
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
