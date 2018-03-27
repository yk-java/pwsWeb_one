package com.glens.pwCloudOs.pe.baseMgr.rpMaterialBase.web;

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
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.materielMg.assetMg.materialBase.web.MaterialBaseForm;
import com.glens.pwCloudOs.pe.baseMgr.rpMaterialBase.service.RpMaterialBaseService;

@FormProcessor(clazz="com.glens.pwCloudOs.pe.baseMgr.rpMaterialBase.web.RpMaterialBaseForm")
@RequestMapping("pmsServices/pe/baseMgr/materiaBase")
public class RpMaterialBaseController extends EAPJsonAbstractController {
	
	
	
	@RequestMapping(value="getMaterialtype", method=RequestMethod.GET)
	public Object getMaterialtype(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				RpMaterialBaseService ser=(RpMaterialBaseService)getService();
				
				RpMaterialBaseForm form=(RpMaterialBaseForm)actionForm;
				
				String companyCode=form.getCompanyCode();
				Map m=new HashMap();
				m.put("companyCode", companyCode);
				List<Map<String, Object>> list=ser.getMaterialtype(m);
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
				RpMaterialBaseForm form=(RpMaterialBaseForm)actionForm;
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				String materialBatchno = codeWorker.createCode("M");
				form.setMaterialBatchno(materialBatchno);
				boolean ok = getService().insert(actionForm.toVo());
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
