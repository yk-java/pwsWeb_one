package com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.service.CpCommuteConfigService;
import com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.vo.CpCommuteConfig;

@FormProcessor(clazz = "com.glens.pwCloudOs.commuteMgr.setting.commuteSetting.web.CpCommuteConfigForm")
@RequestMapping("/pmsServices/commuteMgr/setting")
public class CpCommuteConfigController extends EAPJsonAbstractController {

	@RequestMapping(method = RequestMethod.GET, value = "commuteSetting")
	public Object commuteSetting(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CpCommuteConfigService cpcommuteconfigservice = (CpCommuteConfigService) EAPContext
						.getContext().getBean("cpCommuteConfigService");
				CpCommuteConfigForm form = (CpCommuteConfigForm) actionForm;
				String ticket = request.getParameter("ticket");
				CpCommuteConfig config = (CpCommuteConfig) form.toVo();
				Map result = cpcommuteconfigservice.commuteSetting(config);
				return result;
			}

		});
	}
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				
				UserToken token = getToken(request);
				
			    CpCommuteConfigForm form=(CpCommuteConfigForm)actionForm;
				
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode()) 
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE.equals(token.getRoleCode())) {
						
						form.setDeptCode(token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						form.setDistrictManager(token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						form.setProManager(token.getEmployeeCode());
					}
				}
				
				PageBean page = 
					getService().queryForPage(form.toMap(), 
							form.getCurrentPage(), form.getPerpage());
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
