package com.glens.pwCloudOs.addrLib.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.addrLib.service.AddAddrClassService;
import com.glens.pwCloudOs.addrLib.service.AddBaseService;

@FormProcessor(clazz = "com.glens.pwCloudOs.addrLib.web.AddAddrClassForm")
@RequestMapping("/pmsServices/addrLib/addrClass")
public class AddAddrClassController extends EAPJsonAbstractController {
	
	
	//获取来源
		@RequestMapping(value="getDatasourceList", method=RequestMethod.GET)
		public Object getDatasourceList(HttpServletRequest request, HttpServletResponse response) {
			// TODO Auto-generated method stub
			return this.process(request, response, new JsonProcessRequestHandler() {

				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					// TODO Auto-generated method stub
					AddAddrClassService ser=(AddAddrClassService)getService();
					
					List list=ser.getDatasourceList();
					
					
					ListResult result = new ListResult();
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
