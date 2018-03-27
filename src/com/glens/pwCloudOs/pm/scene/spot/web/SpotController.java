package com.glens.pwCloudOs.pm.scene.spot.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.pm.scene.spot.service.ProSpotService;

@RequestMapping("pmsServices/pm/spot")
@FormProcessor(clazz = "com.glens.pwCloudOs.pm.scene.spot.web.ProSpotForm")
public class SpotController extends EAPJsonAbstractController {

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PageBean page = getService().queryForPage(actionForm.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(page);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "listSpot", method = RequestMethod.GET)
	public Object listSpot(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProSpotService pservice = (ProSpotService) getService();
				PageBean page = pservice.queryForPage1(actionForm.toMap());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(page);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProSpotService pservice = (ProSpotService) getService();

				KeyResult result = new KeyResult();
				try {
					pservice.insert(actionForm.toVo(), getRootPath(request));
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				} catch (Exception e) {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ProSpotService pservice = (ProSpotService) getService();
				ResponseResult result = new ResponseResult();
				try {
					pservice.updateSpot(actionForm.toVo(), getRootPath(request));
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				} catch (Exception e) {

					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Object delete(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				ResponseResult result = new ResponseResult();
				ProSpotService pservice = (ProSpotService) getService();
				try {
					pservice.deleteSpot(actionForm.toVo());
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("删除成功");
				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("删除出错");
				}
				return result;
			}

		});
	}

	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ProSpotService pservice = (ProSpotService) getService();
				ProSpotForm form = (ProSpotForm) actionForm;
				Map m = pservice.get(form.getSpotCode());

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
