package com.glens.eap.sys.funcConfig.role.web;

import java.util.HashMap;
import java.util.List;
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
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.sys.funcConfig.role.service.SmRoleService;
import com.glens.eap.sys.funcConfig.role.vo.SmRole;

@FormProcessor(clazz = "com.glens.eap.sys.funcConfig.role.web.SmRoleForm")
@RequestMapping("/pmsServices/sys/funcConfig/role")
public class SmRoleController extends EAPJsonAbstractController {

	
	@Override
	@RequestMapping(value="list", method=RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PageBean page = 
					getService().queryForPage(actionForm.toMap(), 
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
	
	@RequestMapping(value="getRoleList", method=RequestMethod.GET)
	public Object getRoleList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				
				SmRoleService  ser=(SmRoleService)getService();
				PageBeanResult result = new PageBeanResult();
				List list=ser.getRoleList(actionForm.toMap());
				
				//ListResult result=new ListResult();
				
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
				SmRoleService ser=(SmRoleService)getService();
				boolean ok = ser.saveSmRole(actionForm.toVo());
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



	

	

	@RequestMapping(method = RequestMethod.GET, value = "moduleList")
	public Object moduleList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				SmRoleService smRoleService = (SmRoleService) EAPContext
						.getContext().getBean("smRoleService");
				SmRoleForm form = (SmRoleForm) actionForm;
				String ticket = request.getParameter("ticket");
				Map result = smRoleService.queryModuleList(form);
				return result;
			}

		});
	}

	@RequestMapping(method = RequestMethod.GET, value = "linkModule")
	public Object linkModule(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				SmRoleService smRoleService = (SmRoleService) EAPContext
						.getContext().getBean("smRoleService");
				SmRoleForm form = (SmRoleForm) actionForm;
				String ticket = request.getParameter("ticket");
				SmRole smrole = (SmRole) form.toVo();
				Map result = smRoleService.linkModule(smrole);
				return result;
			}

		});
	}
}
