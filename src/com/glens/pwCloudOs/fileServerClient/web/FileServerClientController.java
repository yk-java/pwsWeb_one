/**
 * @Title: FileServerClientController.java
 * @Package com.glens.pwCloudOs.fileServerClient.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-15 下午2:01:47
 * @version V1.0
 */

package com.glens.pwCloudOs.fileServerClient.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.pwCloudOs.config.PWCloudOsConfig;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@RequestMapping("/pmsServices/fileServerClientService")
public class FileServerClientController extends EAPJsonAbstractController {

	@RequestMapping(method = RequestMethod.GET)
	public Object getFile(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				return null;
			}

			/**
			 * 
			 * <p>
			 * Title: doWithFinish
			 * </p>
			 * 
			 * <p>
			 * Description:
			 * </p>
			 * 
			 * @param request
			 * @param response
			 * @param data
			 * @param viewType
			 * @param controller
			 * @return
			 * 
			 * @see com.glens.eap.platform.framework.web.support.AbstractProcessRequestHandler#doWithFinish(javax.servlet.http.HttpServletRequest,
			 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
			 *      java.lang.String,
			 *      com.glens.eap.platform.core.web.EAPController)
			 **/

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub

				PWCloudOsConfig config = (PWCloudOsConfig) EAPContext
						.getContext().getBean("pwcloudosConfig");
				System.out.println("图片地址：" + config.getDownloadUrl() + "?"
						+ "fileCode=" + request.getParameter("fileCode")
						+ "&attachment=" + request.getParameter("attachment"));
				ModelAndView mv = new ModelAndView(new RedirectView(config.getPrefix()+  config
						.getDownloadUrl()
						+ "?"
						+ "fileCode="
						+ request.getParameter("fileCode")
						+ "&attachment="
						+ request.getParameter("attachment")));

				return mv;
			}
		});
	}
}
