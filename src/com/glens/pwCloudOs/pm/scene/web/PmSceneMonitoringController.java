package com.glens.pwCloudOs.pm.scene.web;

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
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.pm.scene.service.PmSceneMonitoringService;
import com.glens.pwCloudOs.pm.scene.spot.service.ProSpotService;
import com.glens.pwCloudOs.pm.scene.vo.PmSceneMonitoring;

@RequestMapping("pmsServices/pm/sceneMonitoring")
@FormProcessor(clazz = "com.glens.pwCloudOs.pm.scene.web.PmSceneMonitoringForm")
public class PmSceneMonitoringController extends EAPJsonAbstractController {

	private ProSpotService proSpotService;

	@RequestMapping(value = "statistics", method = RequestMethod.GET)
	public Object statistics(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmSceneMonitoringService monitoringService = (PmSceneMonitoringService) getService();
				List<PmSceneMonitoring> res = monitoringService
						.statistics(actionForm.toMap());
				ListResult result = new ListResult();
				if (res != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(res);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}

		});
	}

	@RequestMapping(value = "listAll", method = RequestMethod.GET)
	public Object listAll(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				List list = getService().queryForList(actionForm.toMap());
				ListResult result = new ListResult();
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

	@RequestMapping(value = "listScene", method = RequestMethod.GET)
	public Object listScene(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmSceneMonitoringService service = (PmSceneMonitoringService) getService();
				List list = service.querySceneList(actionForm.toMap());
				ListResult result = new ListResult();
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Map m = (Map) list.get(i);
						m.put("total", "10");
					}
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

	@RequestMapping(value = "listHis", method = RequestMethod.GET)
	public Object listHis(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmSceneMonitoringService service = (PmSceneMonitoringService) getService();
				List list = service.queryHisList(actionForm.toMap());
				ListResult result = new ListResult();
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

	@RequestMapping(value = "listHeatHis", method = RequestMethod.GET)
	public Object listHeatHis(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmSceneMonitoringService service = (PmSceneMonitoringService) getService();
				List list = service.queryHeatHisList(actionForm.toMap());
				ListResult result = new ListResult();
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

	@Override
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmSceneMonitoringService service = (PmSceneMonitoringService) getService();
				PmSceneMonitoringForm form = (PmSceneMonitoringForm) actionForm;
				String reportEmployeeCode = form.getReportEmployeecode();
				form.setSpotCode(proSpotService
						.queryProspotCode(reportEmployeeCode));
				boolean ok = service.insert(form.toVo());
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

	/**
	 * 统计查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryReport", method = RequestMethod.GET)
	public Object queryReport(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmSceneMonitoringService service = (PmSceneMonitoringService) getService();
				List list = service.queryReport(actionForm.toMap());
				ListResult result = new ListResult();
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

	public void setProSpotService(ProSpotService proSpotService) {
		this.proSpotService = proSpotService;
	}

}
