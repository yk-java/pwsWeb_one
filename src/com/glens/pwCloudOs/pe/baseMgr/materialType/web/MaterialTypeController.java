package com.glens.pwCloudOs.pe.baseMgr.materialType.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.pe.baseMgr.materialType.service.MaterialTypeService;

@FormProcessor(clazz="com.glens.pwCloudOs.pe.baseMgr.materialType.web.MaterialTypeForm")
@RequestMapping("pmsServices/pe/baseMgr/materialType")
public class MaterialTypeController extends EAPJsonAbstractController {
	
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
				
				//CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						//CodeWorker.SIMPLE_CODE_WORKER);
				//String typecode = codeWorker.createCode("M");
				MaterialTypeForm form=(MaterialTypeForm)actionForm;
				form.setMaterialTypeCode(form.getMaterialTypeName());
				boolean ok =
					getService().insert(form.toVo());
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
	

	@Override
	@RequestMapping(value="get", method=RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ValueObject vo = 
					getService().findById(actionForm.toVo());
				BeanResult result = new BeanResult();
				if (vo != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(vo);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				
				return result;
			}
			
		});
	}
	
	@RequestMapping(value="selectCount", method=RequestMethod.GET)
	public Object selectCount(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialTypeForm form=(MaterialTypeForm)actionForm;
				String materialTypeName=form.getMaterialTypeName();
				
				String companyCode=form.getCompanyCode();
				
				MaterialTypeService ser=(MaterialTypeService)getService();
				
				List<Map<String, Object>> list=ser.selectCount(materialTypeName, companyCode);
				PageBeanResult result = new PageBeanResult();
				
				result.setStatusCode(ResponseConstants.OK);
				result.setResultMsg("获取成功!");
				result.setList(list);
				return result;
			}
			
		});
	}
	
	

	
}
