package com.glens.pwCloudOs.cj.base.web;

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
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.cj.base.service.CjXldService;
import com.glens.pwCloudOs.cj.base.vo.CjXld;

@FormProcessor(clazz = "com.glens.pwCloudOs.cj.base.web.CjXldForm")
@RequestMapping("pmsServices/cj/base/cjXld")
public class CjXldController extends EAPJsonAbstractController {

	@RequestMapping(method = RequestMethod.POST, value = "queryCjXldForPage")
	public Object queryCjXldForPage(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjXldService service = (CjXldService) getService();
				CjXldForm form = (CjXldForm) actionForm;
				PageBean page = service.queryCjXldForPage(form);
				Map result = new HashMap();
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("currentPage", page.getCurrentPage());
				result.put("perPage", page.getPerpage());
				result.put("totalPage", page.getTotalPage());
				result.put("totalRecord", page.getTotalRecord());
				result.put("list", page.getList());
				return result;
			}
		});
	}

	@RequestMapping(value = "queryCjXldList", method = {RequestMethod.GET,RequestMethod.POST} )
	public Object queryCjXldList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjXldService service = (CjXldService) getService();
				CjXldForm form = (CjXldForm) actionForm;
				List list = service.queryCjXldList(form);
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

	@RequestMapping(value = "queryCjXldByCollectId", method = RequestMethod.POST)
	public Object queryCjXldByCollectId(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjXldService service = (CjXldService) getService();
				CjXldForm form = (CjXldForm) actionForm;
				CjXld cjXld = service.queryCjXldByCollectId(form.getCollectId());
				Map result = new HashMap();
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("cjXld", cjXld != null ? cjXld : "{}");
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
				CjXldService cjXldService = (CjXldService) getService();
				CjXldForm form = (CjXldForm) actionForm;
				CjXld cjXld = (CjXld) form.toVo();
				Map result = cjXldService.saveCjXld(cjXld);
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
				CjXldForm form = (CjXldForm) actionForm;
				CjXld cjXld = (CjXld) form.toVo();
				CjXldService cjXldService = (CjXldService) getService();
				Map result = cjXldService.updateCjXld(cjXld);
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
				CjXldForm form = (CjXldForm) actionForm;
				CjXld cjXld = (CjXld) form.toVo();
				CjXldService cjXldService = (CjXldService) getService();
				Map result = cjXldService.delCjXld(cjXld);
				return result;
			}
		});
	}
}
