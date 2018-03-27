package com.glens.pwCloudOs.om.deviceMgr.device.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.om.deviceMgr.device.service.QualityCheckRecordService;
import com.glens.pwCloudOs.pm.taskMgr.task.service.TaskService;

@RequestMapping("/pmsServices/om/deviceMgr/qualityCheck")
@FormProcessor(clazz = "com.glens.pwCloudOs.om.deviceMgr.device.web.QualityCheckRecordForm")
public class QualityCheckRecordController extends EAPJsonAbstractController {
	
	/**
	 * 保存质量审核信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="saveQualityCheck", method=RequestMethod.POST)
	public Object saveQualityCheck(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				QualityCheckRecordForm form = (QualityCheckRecordForm) actionForm;
				
				BeanResult result = new BeanResult();
				
				String base64ImgData = form.getImgs();
				
				
				PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
				FinalHttp httpClient=new FinalHttp();
				AjaxParams params = new AjaxParams();
				params.put("base64Img", base64ImgData);
				String codeStr = (String)httpClient.postSync(config.getPrefix()+ config.getBatchUploadBase64ImgUrl(), params);
				try {
					JSONObject json = JSON.parseObject(codeStr);
					if("200".equals(json.get("statusCode").toString())){
						String generateKey = json.get("generateKey").toString();
						form.setPics(generateKey);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				QualityCheckRecordService qualityCheckService = (QualityCheckRecordService) getService();
				boolean res = qualityCheckService.insert(form.toVo());
				if (res) {
					result.setResultMsg("提交成功");
					result.setStatusCode(ResponseConstants.OK);
					return result;
				}
				else {
					result.setResultMsg("提交失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
					return result;
				}
			}
		});
	}
	
	@RequestMapping(value="findByDeviceCode", method=RequestMethod.GET)
	public Object findByDeviceCode(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String deviceObjCode = request.getParameter("deviceObjCode");
				Integer checkType = null;
				try {
					checkType = Integer.parseInt(request.getParameter("checkType"));
				} catch (NumberFormatException e) {
				}
				QualityCheckRecordService qualityCheckService =  (QualityCheckRecordService)getService();
				List res = qualityCheckService.findByDeviceCode(deviceObjCode, checkType);
				ListResult result = new ListResult();
				if (res != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(res);
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
