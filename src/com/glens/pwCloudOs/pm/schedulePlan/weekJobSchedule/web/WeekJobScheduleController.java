/**
 * @Title: WeekJobScheduleController.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.weekJobSchedule.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-1-19 上午11:15:00
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.schedulePlan.weekJobSchedule.web;

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
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.schedulePlan.weekJobSchedule.service.WeekJobScheduleService;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.schedulePlan.weekJobSchedule.web.WeekJobScheduleForm")
@RequestMapping("pmsServices/pm/schedulePlan/weekJobSchedule")
public class WeekJobScheduleController extends EAPJsonAbstractController {

	/**
	 * 
	 * <p>
	 * Title: list
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 * @see com.glens.eap.platform.framework.web.EAPJsonAbstractController#list(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 **/

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

				List<Map<String, Object>> workloadList = getService()
						.queryForList(map);
				ListResult result = new ListResult();
				if (workloadList != null && workloadList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取项目周进度数据成功");
					result.setList(workloadList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取项目周进度数据");
				}

				return result;
			}

		});
	}

	@RequestMapping(method = RequestMethod.GET)
	public Object selectProWeekWorkload(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				WeekJobScheduleService weekJobScheduleService = (WeekJobScheduleService) getService();
				Map<String, Object> workloadItem = weekJobScheduleService
						.selectProWeekWorkload(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (workloadItem != null && workloadItem.size() > 0) {

					result.setResultMsg("获取某项目周进度数据成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(workloadItem);
				} else {

					result.setResultMsg("获取某项目周进度数据失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				WeekJobScheduleService weekJobScheduleService = (WeekJobScheduleService) getService();
				boolean saveResult = weekJobScheduleService.save(actionForm
						.toMap());
				ResponseResult result = new ResponseResult();
				if (saveResult) {

					result.setResultMsg("更改项目周进度成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("更改项目周进度失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "progressTrendList", method = RequestMethod.GET)
	public Object selectWorkloadTrend(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				WeekJobScheduleService weekJobScheduleService = (WeekJobScheduleService) getService();
				List<Map<String, String>> trendList = weekJobScheduleService
						.selectWorkloadTrend();
				ListResult result = new ListResult();
				if (trendList != null && trendList.size() > 0) {

					result.setResultMsg("获取项目周进度趋势成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(trendList);
				} else {

					result.setResultMsg("获取项目周进度趋势失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

}
