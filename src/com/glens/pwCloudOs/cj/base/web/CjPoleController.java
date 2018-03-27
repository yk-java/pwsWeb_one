package com.glens.pwCloudOs.cj.base.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.cj.base.service.CjPoleService;
import com.glens.pwCloudOs.cj.base.vo.CjPole;

@FormProcessor(clazz = "com.glens.pwCloudOs.cj.base.web.CjPoleForm")
@RequestMapping("pmsServices/cj/base/cjPole")
public class CjPoleController extends EAPJsonAbstractController {

	@RequestMapping(value = "queryCjPoleList", method = RequestMethod.POST)
	public Object queryCjPoleList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjPoleService service = (CjPoleService) getService();
				CjPoleForm form = (CjPoleForm) actionForm;
				List list = service.queryCjPoleList(form);
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

	@RequestMapping(value = "queryByXlGtCollectId", method = RequestMethod.POST)
	public Object queryCjPoleByCollectId(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjPoleService service = (CjPoleService) getService();
				CjPoleForm form = (CjPoleForm) actionForm;
				CjPole cjPole = (CjPole) service.queryByXlGtCollectId(form);
				Map result = new HashMap();
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("cjPole", cjPole != null ? cjPole : "{}");
				System.out.println(JSON.toJSON(result));
				return result;
			}
		});
	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "add")
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjPoleService cjPoleService = (CjPoleService) getService();
				CjPoleForm form = (CjPoleForm) actionForm;
				String cjXlghJson = form.getCjXlgh_tg_json();

				CjPole cjPole = (CjPole) form.toVo();
				Map result = cjPoleService.saveCjPole(cjPole, cjXlghJson);
				return result;
			}
		});
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjPoleForm form = (CjPoleForm) actionForm;
				String cjXlghJson = form.getCjXlgh_tg_json();
				CjPole cjPole = (CjPole) form.toVo();
				CjPoleService cjPoleService = (CjPoleService) getService();
				Map result = cjPoleService.updateCjPole(cjPole, cjXlghJson);
				return result;
			}
		});
	}

	@RequestMapping(value = "delect", method = RequestMethod.POST)
	public Object delect(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjPoleForm form = (CjPoleForm) actionForm;
				CjPole cjPole = (CjPole) form.toVo();
				CjPoleService cjPoleService = (CjPoleService) getService();
				Map result = cjPoleService.delCjPole(cjPole);
				return result;
			}
		});
	}
}
