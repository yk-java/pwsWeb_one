package com.glens.pwCloudOs.pm.prjMgr.processMgr.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.prjMgr.processMgr.service.ProcessMgrService;
import com.glens.pwCloudOs.pm.prjMgr.processMgr.vo.ProcessVo;

@RequestMapping("pmsServices/pm/prjMgr/processMgr")
public class ProcessMgrController extends EAPJsonAbstractController {

	@RequestMapping(value = "selectToDoList", method = RequestMethod.GET)
	public Object selectToDoList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				String viewtype = request.getParameter("viewtype");
				String proNo = request.getParameter("proNo");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("viewtype", viewtype);
				params.put("proNo", proNo);
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					params.put("deptCode", token.getUnitCode());
				}
				ListResult result = new ListResult();
				ProcessMgrService processMgrService = (ProcessMgrService) getService();

				List<ProcessVo> res = processMgrService.selectToDoList(params);
				if (res.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setList(res);
					result.setResultMsg("获取成功！");
				} else {

					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("获取失败！");
				}

				return result;
			}
		});
	}

	// 代办流程
	@RequestMapping(value = "selectXzToDoList", method = RequestMethod.GET)
	public Object selectXzToDoList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				String viewtype = request.getParameter("viewtype");
				String proNo = request.getParameter("proNo");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("viewtype", viewtype);
				params.put("proNo", proNo);

				PageBeanResult result = new PageBeanResult();
				ProcessMgrService processMgrService = (ProcessMgrService) getService();

				PageBean page = processMgrService.selectXzToDoList(params,
						actionForm.getCurrentPage(), actionForm.getPerpage());
				if (page != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
					result.setResultMsg("获取成功！");
				} else {
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("获取失败！");
				}
				return result;
			}
		});
	}

	@RequestMapping(value = "selectDoneList", method = RequestMethod.GET)
	public Object selectDoneList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				String viewtype = request.getParameter("viewtype");
				String proNo = request.getParameter("proNo");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("viewtype", viewtype);
				params.put("proNo", proNo);
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					params.put("deptCode", token.getUnitCode());
				}
				PageBeanResult result = new PageBeanResult();
				ProcessMgrService processMgrService = (ProcessMgrService) getService();

				// List<ProcessVo> res =
				// processMgrService.selectDoneList(params);

				PageBean page = processMgrService.selectDoneList(params,
						actionForm.getCurrentPage(), actionForm.getPerpage());
				if (page != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
					result.setResultMsg("获取成功！");
				} else {
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("获取失败！");
				}

				return result;
			}
		});
	}

}
