package com.glens.pwCloudOs.addrLib.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.pwCloudOs.addrLib.service.AddBaseAddrFieldService;

/**
 * 地址扩展字段查询
 * @author MaDx
 *
 */
@FormProcessor(clazz = "com.glens.pwCloudOs.addrLib.web.AddBaseAddrFieldForm")
@RequestMapping("/pmsServices/addrLib/addrField")
public class AddBaseAddrFieldController extends EAPJsonAbstractController {
	/**
	 * 根据行业类型分组，查询出所有扩展字段信息
	 */
	@RequestMapping(value="allFields", method=RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddBaseAddrFieldService service = (AddBaseAddrFieldService)getService();
				BeanResult res = service.getAllAddrField(actionForm.toMap());
				return res;	
			}
		});
	}
}
