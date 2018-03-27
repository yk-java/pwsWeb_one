package com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.service.PmDayKpiService;

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.web.PmDayKpiForm")
@RequestMapping("pmsServices/pm/schedulePlan/pmDayKpi")
public class PmDayKpiController extends EAPJsonAbstractController {
	@RequestMapping(value = "queryProKpiProgress", method = RequestMethod.GET)
	public Object queryProKpiProgress(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				PmDayKpiService service = (PmDayKpiService) getService();
				Map params = new HashMap();
				params.put("categoryCode", request.getParameter("categoryCode"));
				params.put("beginDate", request.getParameter("beginDate"));
				params.put("endDate", request.getParameter("endDate"));
				params.put("proNos", request.getParameter("proNos"));
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					params.put("deptCode", token.getUnitCode());
				}
				Map res = service.queryProKpiProgress(params);
				BeanResult result = new BeanResult();
				if (res != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(res);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "queryByProNoAndReportDate", method = RequestMethod.GET)
	public Object queryByProNoAndReportDate(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmDayKpiService service = (PmDayKpiService) getService();
				Map params = new HashMap();
				params.put("proNo", request.getParameter("proNo"));
				params.put("reportDate", request.getParameter("reportDate"));
				List<Map> res = service.queryByProNoAndReportDate(params);
				PageBeanResult result = new PageBeanResult();
				if (res != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setList(res);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}
}
