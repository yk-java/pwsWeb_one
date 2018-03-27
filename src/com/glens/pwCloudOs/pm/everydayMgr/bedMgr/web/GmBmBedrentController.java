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

package com.glens.pwCloudOs.pm.everydayMgr.bedMgr.web;

import java.util.HashMap;
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
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.everydayMgr.bedMgr.service.GmBmBedrentService;
import com.glens.pwCloudOs.pm.everydayMgr.bedMgr.vo.GmBmBedrentVo;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.everydayMgr.bedMgr.web.GmBmBedrentForm")
@RequestMapping("pmsServices/pm/everydayMgr/bedMgr")
public class GmBmBedrentController extends EAPJsonAbstractController {

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

	@RequestMapping(value = "updateRentStatus", method = RequestMethod.POST)
	public Object updateRentStatus(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				Long rowid = 0l;
				rowid = Long.parseLong(request.getParameter("rowid"));
				String rentStatus = request.getParameter("rentStatus");
				String flowStatus = request.getParameter("flowStatus");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("rowid", rowid);
				params.put("rentStatus", rentStatus);
				params.put("flowStatus", flowStatus);
				GmBmBedrentService service = (GmBmBedrentService) getService();
				int iCount = service.updateRentStatus(params);

				ResponseResult result = new ResponseResult();

				if (iCount > 0) {
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

	@RequestMapping(value = "updateDormCode", method = RequestMethod.POST)
	public Object updateDormCode(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				Long rowid = 0l;
				rowid = Long.parseLong(request.getParameter("rowid"));
				String dormCode = request.getParameter("dormCode");
				String employeecode = request.getParameter("employeecode");
				String employeename = request.getParameter("employeename");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("rowid", rowid);
				params.put("dormCode", dormCode);
				params.put("employeecode", employeecode);
				params.put("employeename", employeename);
				GmBmBedrentService service = (GmBmBedrentService) getService();
				int iCount = service.updateDormCode(params);

				ResponseResult result = new ResponseResult();

				if (iCount > 0) {
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

	@RequestMapping(value = "addFaster", method = RequestMethod.POST)
	public Object addFaster(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String dormCodes = request.getParameter("dormCodes");
				String employeecodes = request.getParameter("employeecodes");
				String employeenames = request.getParameter("employeenames");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("dormCodes", dormCodes);
				params.put("employeecodes", employeecodes);
				params.put("employeenames", employeenames);
				GmBmBedrentService service = (GmBmBedrentService) getService();
				int iCount = service.addFaster(
						(GmBmBedrentVo) actionForm.toVo(), params);

				ResponseResult result = new ResponseResult();

				if (iCount > 0) {
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

	@RequestMapping(value = "returnDorm", method = RequestMethod.POST)
	public Object returnDorm(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				Long rowid = 0l;
				rowid = Long.parseLong(request.getParameter("rowid"));
				String dormCode = request.getParameter("dormCode");
				String rentStatus = request.getParameter("rentStatus");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("rowid", rowid);
				params.put("dormCode", dormCode);
				params.put("rentStatus", rentStatus);
				GmBmBedrentService service = (GmBmBedrentService) getService();
				int iCount = service.returnDorm(params);

				ResponseResult result = new ResponseResult();

				if (iCount > 0) {
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
}
