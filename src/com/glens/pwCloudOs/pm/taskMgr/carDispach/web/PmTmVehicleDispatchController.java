/**
 * @Title: PmBaseController.java
 * @Package com.glens.pwCloudOs.pm.baseMgr.pmBase.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 上午10:55:26
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.taskMgr.carDispach.web;

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
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.taskMgr.carDispach.service.PmTmVehicleFuelchargeService;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.taskMgr.carDispach.web.PmTmVehicleDispatchForm")
@RequestMapping("pmsServices/pm/taskMgr/carDispach")
public class PmTmVehicleDispatchController extends EAPJsonAbstractController {

	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				Map map = actionForm.toMap();
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				PageBean page = getService().queryForPage(map,
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

	@RequestMapping(value = "selectList", method = RequestMethod.POST)
	public Object selectList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmTmVehicleFuelchargeService server = (PmTmVehicleFuelchargeService) getService();
				PmTmVehicleDispatchForm form = (PmTmVehicleDispatchForm) actionForm;
				String drivers = form.getPtjDriver();

				List list = server.selectList(drivers);
				PageBeanResult result = new PageBeanResult();
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

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				boolean ok = getService().insert(actionForm.toVo());
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

	@RequestMapping(value = "updateState", method = RequestMethod.POST)
	public Object updateState(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmTmVehicleFuelchargeService server = (PmTmVehicleFuelchargeService) getService();

				PmTmVehicleDispatchForm form = (PmTmVehicleDispatchForm) actionForm;

				String sDate = form.getsDate();
				String splaceX = form.getSplaceX();
				String splaceY = form.getSplaceY();
				String eDate = form.geteDate();
				String dplaceX = form.getDplaceX();
				String dplaceY = form.getDplaceY();
				String smileageImg = form.getSmileageImg();
				String remarks  = form.getRemarks();
				String dmileageImg = form.getDmileageImg();
				Long rowid = form.getRowid();
				String trackXs = form.getTrackXs();
				String trackYs = form.getTrackYs();
				int sMileage = 0;
				if (form.getSMileage() != null) {
					sMileage = Integer.parseInt(form.getSMileage().toString());
				}
				int dMileage = 0;

				if (form.getDMileage() != null) {
					dMileage = Integer.parseInt(form.getDMileage().toString());
				}

				Map map = new HashMap();

				map.put("sDate", sDate);
				map.put("splaceX", splaceX);
				map.put("splaceY", splaceY);
				map.put("eDate", eDate);
				map.put("dplaceX", dplaceX);
				map.put("dplaceY", dplaceY);

				map.put("rowid", rowid);
				map.put("trackXs", trackXs);
				map.put("trackYs", trackYs);
				map.put("sMileage", sMileage);
				map.put("dMileage", dMileage);
				map.put("remarks", remarks);

				int res = server.updateState(map, smileageImg, dmileageImg);

				ResponseResult result = new ResponseResult();

				if (res > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "getByRowid", method = RequestMethod.GET)
	public Object getByRowid(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmTmVehicleFuelchargeService server = (PmTmVehicleFuelchargeService) getService();

				PmTmVehicleDispatchForm form = (PmTmVehicleDispatchForm) actionForm;

				Long rowid = form.getRowid();
				Object vo = server.getDispatchVo(rowid);

				BeanResult result = new BeanResult();
				if (vo != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(vo);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

}
