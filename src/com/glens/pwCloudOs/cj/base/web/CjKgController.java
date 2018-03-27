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
import com.glens.pwCloudOs.cj.base.service.CjKgService;
import com.glens.pwCloudOs.cj.base.vo.CjKg;

@FormProcessor(clazz = "com.glens.pwCloudOs.cj.base.web.CjKgForm")
@RequestMapping("pmsServices/cj/base/cjKg")
public class CjKgController extends EAPJsonAbstractController {

	@RequestMapping(method = RequestMethod.POST, value = "queryCjKgForPage")
	public Object queryCjKgForPage(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjKgService service = (CjKgService) getService();
				CjKgForm form = (CjKgForm) actionForm;
				PageBean page = service.queryCjKgForPage(form);
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

	@RequestMapping(value = "queryCjKgList", method = RequestMethod.POST)
	public Object queryCjKgList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjKgService service = (CjKgService) getService();
				CjKgForm form = (CjKgForm) actionForm;
				List list = service.queryCjKgList(form);
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

	@RequestMapping(value = "queryCjKgById", method = RequestMethod.POST)
	public Object queryCjKgById(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjKgService service = (CjKgService) getService();
				CjKgForm form = (CjKgForm) actionForm;
				CjKg cjKg = (CjKg) service.findById(form.getCollectId());
				Map result = new HashMap();
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("cjKg", cjKg != null ? cjKg : "{}");
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
				CjKgService service = (CjKgService) getService();
				CjKgForm form = (CjKgForm) actionForm;
				CjKg cjKg = (CjKg) form.toVo();
				Map result = service.saveCjKg(cjKg);
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
				CjKgService service = (CjKgService) getService();
				CjKgForm form = (CjKgForm) actionForm;
				CjKg cjKg = (CjKg) form.toVo();
				Map result = service.updateCjKg(cjKg);
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
				CjKgService service = (CjKgService) getService();
				CjKgForm form = (CjKgForm) actionForm;
				CjKg cjKg = (CjKg) form.toVo();
				Map result = service.delCjKg(cjKg);
				return result;
			}
		});
	}
}
