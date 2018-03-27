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
import com.glens.pwCloudOs.cj.base.service.CjHwgService;
import com.glens.pwCloudOs.cj.base.vo.CjHwg;

@FormProcessor(clazz = "com.glens.pwCloudOs.cj.base.web.CjHwgForm")
@RequestMapping("pmsServices/cj/base/cjHwg")
public class CjHwgController extends EAPJsonAbstractController {

	@RequestMapping(method = RequestMethod.POST, value = "queryCjHwgForPage")
	public Object queryCjHwgForPage(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjHwgService service = (CjHwgService) getService();
				CjHwgForm form = (CjHwgForm) actionForm;
				PageBean page = service.queryCjHwgForPage(form);
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

	@RequestMapping(value = "queryCjHwgList", method = RequestMethod.POST)
	public Object queryCjHwgList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjHwgService service = (CjHwgService) getService();
				CjHwgForm form = (CjHwgForm) actionForm;
				List list = service.queryCjHwgList(form);
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

	@RequestMapping(value = "queryCjHwgById", method = RequestMethod.POST)
	public Object queryCjHwgById(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjHwgService service = (CjHwgService) getService();
				CjHwgForm form = (CjHwgForm) actionForm;
				CjHwg cjHwg = (CjHwg) service.findById(form.getCollectId());
				Map result = new HashMap();
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("cjHwg", cjHwg != null ? cjHwg : "{}");
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
				CjHwgService service = (CjHwgService) getService();
				CjHwgForm form = (CjHwgForm) actionForm;
				CjHwg cjHwg = (CjHwg) form.toVo();
				Map result = service.saveCjHwg(cjHwg);
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
				CjHwgService service = (CjHwgService) getService();
				CjHwgForm form = (CjHwgForm) actionForm;
				CjHwg cjHwg = (CjHwg) form.toVo();
				Map result = service.updateCjHwg(cjHwg);
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
				CjHwgService service = (CjHwgService) getService();
				CjHwgForm form = (CjHwgForm) actionForm;
				CjHwg cjHwg = (CjHwg) form.toVo();
				Map result = service.delCjHwg(cjHwg);
				return result;
			}
		});
	}
}
