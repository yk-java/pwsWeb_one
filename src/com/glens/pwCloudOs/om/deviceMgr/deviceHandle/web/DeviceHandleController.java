/**
 * @Title: DeviceHandleController.java
 * @Package com.glens.pwCloudOs.om.deviceMgr.deviceHandle.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-12-5 上午11:53:44
 * @version V1.0
 */

package com.glens.pwCloudOs.om.deviceMgr.deviceHandle.web;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.pwCloudOs.om.deviceMgr.deviceHandle.service.DeviceHandleService;
import com.glens.pwCloudOs.weixin.service.WeixinService;

/**
 * 
 * 
 * @author
 * @version V1.0
 */
@RequestMapping("/pmsServices/om/deviceMgr/deviceHandle")
@FormProcessor(clazz = "com.glens.pwCloudOs.om.deviceMgr.deviceHandle.web.DeviceHandleForm")
public class DeviceHandleController extends EAPJsonAbstractController {

	private static Map<String, String> resultMap = new ConcurrentHashMap<String, String>();

	/**
	 * 地址转换成坐标
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "batchUpdateFieldMctDeviceLoc", method = RequestMethod.GET)
	public Object batchUpdateFieldMctDeviceLoc(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				DeviceHandleService deviceService = (DeviceHandleService) getService();

				BeanResult result = new BeanResult();
				try {

					String returnResult = deviceService
							.batchUpdateFieldMctDeviceLoc(actionForm.toMap(),
									resultMap);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					result.setData(returnResult);
				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg(e.getMessage());
				}

				return result;
			}
		});
	}

	/**
	 * 同步信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getSyncInfo", method = RequestMethod.GET)
	public Object getSyncInfo(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				String taskCode = request.getParameter("taskCode");

				BeanResult result = new BeanResult();
				try {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					result.setData(resultMap.get(taskCode));
				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg(e.getMessage());
				}
				return result;
			}
		});
	}

	@RequestMapping(value = "batchUpdateMctDeviceLoc", method = RequestMethod.GET)
	public Object batchUpdateMctDeviceLoc(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				DeviceHandleService deviceService = (DeviceHandleService) getService();
				int icount = deviceService.batchUpdateDeviceLoc(actionForm
						.toMap());
				BeanResult result = new BeanResult();
				if (icount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("批量更新坐标成功！");
					result.setData(icount);
				} else {

					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("批量更新坐标失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "batchConvertWGS84ToBD09", method = RequestMethod.POST)
	public Object batchConvertWGS84ToBD09(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceHandleService deviceService = (DeviceHandleService) getService();
				int icount = deviceService.batchConvertWGS84ToBD09(actionForm
						.toMap());
				BeanResult result = new BeanResult();
				if (icount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("批量更新坐标成功！");
					result.setData(icount);
				} else {

					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("批量更新坐标失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "batchConvertBD09ToWGS84", method = RequestMethod.POST)
	public Object batchConvertBD09ToWGS84(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DeviceHandleService deviceService = (DeviceHandleService) getService();
				int icount = deviceService.batchConvertBD09ToWGS84(actionForm
						.toMap());
				BeanResult result = new BeanResult();
				if (icount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("批量更新坐标成功！");
					result.setData(icount);
				} else {

					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("批量更新坐标失败！");
				}

				return result;
			}
		});
	}

}
