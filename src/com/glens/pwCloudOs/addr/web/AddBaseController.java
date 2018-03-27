package com.glens.pwCloudOs.addr.web;

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
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.addr.service.AddBaseService;
import com.glens.pwCloudOs.pe.baseMgr.remouldBatch.service.RemouldBatchService;


@RequestMapping("/pmsServices/addr/addrBase")
@FormProcessor(clazz = "com.glens.pwCloudOs.addr.web.AddBaseForm")
public class AddBaseController extends EAPJsonAbstractController {

	
	@RequestMapping(value="getAddrList", method=RequestMethod.GET)
	public Object getAddrList(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseService service = (AddBaseService)getService();
				PageBeanResult result=new PageBeanResult();
				
				try {
					PageBean pageBean = service.getAddrList(actionForm.toMap());
					if(pageBean!=null){
						result.setResultMsg("查询成功");
						//result.setStatusCode();
						result.setPageBean(pageBean);
					}
					return PageBeanResult.success("ok", pageBean);
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseResult.fail(e.getMessage());
				}
			}
		});
	}
	
	@RequestMapping(value="insertAddrBase", method=RequestMethod.POST)
	public Object insertAddrBase(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseService service = (AddBaseService)getService();
				
				try {
					int rows = service.insertAddrBase1(actionForm.toMap());
					return ResponseResult.success("updated "+rows+" rows data");
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseResult.fail(e.getMessage());
				}
			}
		});
	}
	
	@RequestMapping(value="updatePoliceAddrBase", method=RequestMethod.POST)
	public Object updatePoliceAddrBase(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				AddBaseService service = (AddBaseService)getService();
				
				int iCount=service.updateAddrBase(actionForm.toMap());
				
				ResponseResult result = new ResponseResult();
				
				if (iCount > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				}
				else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}
				return result;
			}
		});
	}
	
	@RequestMapping(value="updateElectricAddrBase", method=RequestMethod.POST)
	public Object updateElectricAddrBase(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				AddBaseService service = (AddBaseService)getService();
				
				int iCount=service.updateElectricAddrBase(actionForm.toMap());
				
				ResponseResult result = new ResponseResult();
				
				if (iCount > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				}
				else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}
				return result;
			}
		});
	}
	
	
}
