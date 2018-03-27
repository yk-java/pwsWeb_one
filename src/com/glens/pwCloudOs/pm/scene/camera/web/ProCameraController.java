package com.glens.pwCloudOs.pm.scene.camera.web;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.pwCloudOs.pm.scene.camera.service.ProCameraService;

@RequestMapping("pmsServices/pm/camera")
@FormProcessor(clazz = "com.glens.pwCloudOs.pm.scene.camera.web.ProCameraForm")
public class ProCameraController extends EAPJsonAbstractController {

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProCameraForm form = (ProCameraForm) actionForm;
				form.setSysProcessFlag("1");
				try {
					CodeWorker codeWorker = (CodeWorker) EAPContext
							.getContext()
							.getBean(CodeWorker.SIMPLE_CODE_WORKER);
					form.setAssetCode(codeWorker.createCode("A"));
					form.setSysCreateTime(DateTimeUtil.formatDate(new Date(),
							DateTimeUtil.FORMAT_1));
					if (StringUtils.isEmpty(form.getSpotCode())) {
						form.setStatus("1");
					} else {
						form.setStatus("2");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				boolean ok = getService().insert(form.toVo());
				KeyResult result = new KeyResult();
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

	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProCameraService service = (ProCameraService) getService();
				ProCameraForm form = (ProCameraForm) actionForm;
				Map m = service.getCamera(form.toVo());
				BeanResult result = new BeanResult();
				if (m != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(m);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

}
