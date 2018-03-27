/**
 * @Title: PmDayWordloadController.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-12 上午9:51:07
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.glens.pwCloudOs.Jobs.AutoCreatePmDayWordLoad;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.service.PmDayWordloadService;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.vo.PmDayWordloadVo;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@RequestMapping("pmsServices/pm/schedulePlan/scheduleDaily")
@FormProcessor(clazz = "com.glens.pwCloudOs.pm.schedulePlan.scheduleDaily.web.PmDayWordloadForm")
public class PmDayWordloadController extends EAPJsonAbstractController {
	@Autowired
	AutoCreatePmDayWordLoad autoCreatePmDayWordLoad;

	public AutoCreatePmDayWordLoad getAutoCreatePmDayWordLoad() {
		return autoCreatePmDayWordLoad;
	}

	public void setAutoCreatePmDayWordLoad(
			AutoCreatePmDayWordLoad autoCreatePmDayWordLoad) {
		this.autoCreatePmDayWordLoad = autoCreatePmDayWordLoad;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Object getProDayWordload(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				PmDayWordloadService dayWordloadService = (PmDayWordloadService) getService();
				ListResult result = new ListResult();
				Map map = actionForm.toMap();
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}
				List<PmDayWordloadVo> resultList = dayWordloadService
						.getDayWordload(map);

				if (resultList != null && resultList.size() > 0) {

					result.setResultMsg("获取进度日报成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(resultList);
				} else {

					result.setResultMsg("获取进度日报失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "findByRowid", method = RequestMethod.GET)
	public Object findByRowid(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PmDayWordloadService dayWordloadService = (PmDayWordloadService) getService();
				BeanResult result = new BeanResult();
				Long rowid = 0l;
				try {
					rowid = Long.parseLong(request.getParameter("rowid"));
				} catch (NumberFormatException e) {
				}
				PmDayWordloadVo vo = dayWordloadService.findByRowid(rowid);
				if (vo != null) {
					result.setResultMsg("成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(vo);
				} else {
					result.setResultMsg("失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				return result;
			}
		});
	}

	@RequestMapping(value = "lastDayWordload", method = RequestMethod.GET)
	public Object getLastDayWordload(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmDayWordloadService dayWordloadService = (PmDayWordloadService) getService();
				BeanResult result = new BeanResult();
				PmDayWordloadVo lastDayWordload = dayWordloadService
						.getLastDayWordload((PmDayWordloadVo) actionForm.toVo());
				if (lastDayWordload != null) {

					result.setData(lastDayWordload);
					result.setResultMsg("获取本日计划成功！");
					result.setStatusCode(ResponseConstants.OK);
				} else {

					result.setResultMsg("获取本日计划失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "dateDayWordload", method = RequestMethod.GET)
	public Object getDateDayWordload(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmDayWordloadService dayWordloadService = (PmDayWordloadService) getService();
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				ListResult result = new ListResult();

				
				Map map = actionForm.toMap();

				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				List<PmDayWordloadVo> dayWordloadList = dayWordloadService
						.selectDanWordloadByDate(map);

				if (dayWordloadList != null && dayWordloadList.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取项目日报成功！");
					result.setList(dayWordloadList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取项目日报失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "manualCreate", method = RequestMethod.GET)
	public Object manualCreate(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNo = request.getParameter("proNo");
				String date = request.getParameter("date");
				boolean res = autoCreatePmDayWordLoad.manualCreateStart(proNo,
						date);
				BeanResult result = new BeanResult();
				if (res) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("项目日报更新成功！");
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("项目日报更新失败！");
				}
				return result;
			}
		});
	}

}
