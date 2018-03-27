package com.glens.pwCloudOs.km.catalog.web;

import java.io.IOException;
import java.util.List;

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
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.km.catalog.service.CatalogService;

@FormProcessor(clazz = "com.glens.pwCloudOs.km.catalog.web.CatalogForm")
@RequestMapping("pmsServices/km/catalog/catalog")
public class CatalogController extends EAPJsonAbstractController {

	@RequestMapping(value = "getTreeList", method = RequestMethod.GET)
	public Object getTreeList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				CatalogService ser = (CatalogService) getService();

				List list = ser.getTreeList(actionForm.toMap());

				ListResult result = new ListResult();

				if (list != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(list);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	@Override
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				CatalogService ser = (CatalogService) getService();

				CatalogForm form = (CatalogForm) actionForm;

				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
						.getBean(CodeWorker.SIMPLE_CODE_WORKER);
				String catgalogCode = codeWorker.createCode("c");

				form.setCatalogCode(catgalogCode);
				boolean ok;
				KeyResult result = new KeyResult();
				
				ok = ser.insert(actionForm.toVo());
				
				if (ok) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败");
				}

					
				
				return result;
				
			}

		});
	}
	
	@Override
	@RequestMapping(value="update", method=RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				CatalogService ser = (CatalogService) getService();
				ResponseResult result = new ResponseResult();
				int iCount;
				try {
					iCount = ser.update(actionForm.toVo(), getRootPath(request));
					
					
					if (iCount > 0) {
						
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("修改成功");
					}
					else {
						
						result.setStatusCode(ResponseConstants.SERVER_ERROR);
						result.setResultMsg("修改失败");
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result;
				
			}
			
		});
	}
	
	
	

}
