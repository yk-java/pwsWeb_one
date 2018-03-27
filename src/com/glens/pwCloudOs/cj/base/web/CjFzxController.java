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
import com.glens.pwCloudOs.cj.base.service.CjFzxService;
import com.glens.pwCloudOs.cj.base.vo.CjFzx;

@FormProcessor(clazz = "com.glens.pwCloudOs.cj.base.web.CjFzxForm")
@RequestMapping("pmsServices/cj/base/cjFzx")
public class CjFzxController extends EAPJsonAbstractController {

	@RequestMapping(method = RequestMethod.POST, value = "queryCjFzxForPage")
	public Object queryCjFzxForPage(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjFzxService service = (CjFzxService) getService();
				CjFzxForm form = (CjFzxForm) actionForm;
				PageBean page = service.queryCjFzxForPage(form);
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

	@RequestMapping(value = "queryCjFzxList", method = RequestMethod.POST)
	public Object queryCjFzxList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjFzxService service = (CjFzxService) getService();
				CjFzxForm form = (CjFzxForm) actionForm;
				List list = service.queryCjFzxList(form);
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

	@RequestMapping(value = "queryCjFzxById", method = RequestMethod.POST)
	public Object queryCjFzxById(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjFzxService service = (CjFzxService) getService();
				CjFzxForm form = (CjFzxForm) actionForm;
				CjFzx cjFzx = (CjFzx) service.findById(form.getCollectId());
				Map result = new HashMap();
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("cjFzx", cjFzx != null ? cjFzx : "{}");
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
				CjFzxService service = (CjFzxService) getService();
				CjFzxForm form = (CjFzxForm) actionForm;
				CjFzx cjFzx = (CjFzx) form.toVo();
				Map result = service.saveCjFzx(cjFzx);
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
				CjFzxService service = (CjFzxService) getService();
				CjFzxForm form = (CjFzxForm) actionForm;
				CjFzx cjFzx = (CjFzx) form.toVo();
				Map result = service.updateCjFzx(cjFzx);
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
				CjFzxService service = (CjFzxService) getService();
				CjFzxForm form = (CjFzxForm) actionForm;
				CjFzx cjFzx = (CjFzx) form.toVo();
				Map result = service.delCjFzx(cjFzx);
				return result;
			}
		});
	}
}