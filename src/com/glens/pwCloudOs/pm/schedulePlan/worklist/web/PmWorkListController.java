package com.glens.pwCloudOs.pm.schedulePlan.worklist.web;

import java.util.List;
import java.util.Map;

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
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.pm.schedulePlan.worklist.service.PmWorkListService;
@RequestMapping("pmsServices/pm/schedulePlan/workList")
@FormProcessor(clazz="com.glens.pwCloudOs.pm.schedulePlan.worklist.web.PmWorkListForm")
public class PmWorkListController extends EAPJsonAbstractController {
	@RequestMapping(value="projectDayWorkHours", method=RequestMethod.GET)
	public Object projectDayWorkHours(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmWorkListService pmWorkListService = (PmWorkListService)getService();
				Object data = pmWorkListService.projectDayWorkHours(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (data != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(data);
				}
				else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}
		});
	}
	
	@RequestMapping(value="kpiDayWorkload", method=RequestMethod.GET)
	public Object kpiDayWorkload(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmWorkListService pmWorkListService = (PmWorkListService)getService();
				List<Map<String, Object>> list = pmWorkListService.kpiDayWorkload(actionForm.toMap());
				ListResult result = new ListResult();
				if (list != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setList(list);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}
		});
	}
	
	@RequestMapping(value="inOutPerCntAndDescr", method=RequestMethod.GET)
	public Object inOutPerCntAndDescr(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmWorkListService pmWorkListService = (PmWorkListService)getService();
				List<Map<String, Object>> list = pmWorkListService.inOutPerCntAndDescr(actionForm.toMap());
				ListResult result = new ListResult();
				if (list != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setList(list);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}
		});
	}
	
}
