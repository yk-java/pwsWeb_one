package com.glens.pwCloudOs.pe.baseMgr.materialUse.web;

import java.util.List;
import java.util.Map;

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
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.pe.baseMgr.materialUse.service.MaterialUserService;


@FormProcessor(clazz="com.glens.pwCloudOs.pe.baseMgr.materialUse.web.MaterialUserForm")
@RequestMapping("pmsServices/pe/baseMgr/materialUse")
public class MaterialUseController extends EAPJsonAbstractController {
	
	
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
				
				MaterialUserForm form=(MaterialUserForm)actionForm;
				
				int num=form.getUseCount();
				String materialBatchno=form.getMaterialBatchno();
				MaterialUserService ser=(MaterialUserService)getService();
				ser.updateMaterialCount(num,materialBatchno);
				
				boolean ok = 
					getService().insert(actionForm.toVo());
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
	
	@RequestMapping(value="materialtypelist", method=RequestMethod.GET)
	public Object materialtypelist(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				MaterialUserForm form=(MaterialUserForm)actionForm;
				String companyCode=form.getCompanyCode();
				MaterialUserService ser=(MaterialUserService)getService();
				List<Map<String, Object>> list=ser.getMaterialType(companyCode);
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
	@RequestMapping(value="materialbaselist", method=RequestMethod.GET)
	public Object materialbaselist(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				MaterialUserForm form=(MaterialUserForm)actionForm;
				String materialtypeCode=form.getMaterialBatchno();
				
				MaterialUserService ser=(MaterialUserService)getService();
				List<Map<String, Object>> list=ser.getMaterialBase(materialtypeCode);
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
	
	@RequestMapping(value="prolist", method=RequestMethod.GET)
	public Object prolist(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				MaterialUserForm form=(MaterialUserForm)actionForm;
				String companyCode=form.getCompanyCode();
				
				MaterialUserService ser=(MaterialUserService)getService();
				List<Map<String, Object>> list=ser.getPros(companyCode);
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
