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
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.cj.base.service.CjAttachmentService;
import com.glens.pwCloudOs.cj.base.vo.CjAttachment;

@FormProcessor(clazz = "com.glens.pwCloudOs.cj.base.web.CjAttachmentForm")
@RequestMapping("pmsServices/cj/base/cjAttachment")
public class CjAttachmentController extends EAPJsonAbstractController {

	@RequestMapping(value = "queryCjAttachmentList", method = RequestMethod.POST)
	public Object queryCjAttachmentList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjAttachmentService service = (CjAttachmentService) getService();
				CjAttachmentForm form = (CjAttachmentForm) actionForm;
				List list = service.queryCjAttachmentList(form);
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

	@RequestMapping(value = "queryCjAttachmentById", method = RequestMethod.POST)
	public Object queryCjAttachmentById(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjAttachmentService service = (CjAttachmentService) getService();
				CjAttachmentForm form = (CjAttachmentForm) actionForm;
				CjAttachment cjAttachment = (CjAttachment) service
						.findById(form.getAttachmentId());
				Map result = new HashMap();
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("cjAttachment", cjAttachment != null ? cjAttachment
						: "{}");
				return result;
			}
		});
	}

	@RequestMapping(method = RequestMethod.POST, value = "mobileSync")
	public Object mobileSync(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjAttachmentService service = (CjAttachmentService) getService();
				String attachmentJson = request.getParameter("attachmentJson");

				String systemPath = request.getSession().getServletContext()
						.getRealPath("/");// 系统绝对路径
				Map result = service.mobileSync(systemPath,attachmentJson);
				return result;
			}
		});
	}

	@RequestMapping(value = "delectById", method = RequestMethod.POST)
	public Object delectById(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjAttachmentService service = (CjAttachmentService) getService();
				String attachmentIds = request.getParameter("attachmentIds");
				String[] delAttachmentIds = attachmentIds.split(",");
				List<String> attachmentIdList = java.util.Arrays
						.asList(delAttachmentIds);
				Map result = service.delCjAttachmentById(attachmentIdList);
				return result;
			}
		});
	}

	@RequestMapping(value = "delByObjId", method = RequestMethod.POST)
	public Object delByObjId(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				CjAttachmentService service = (CjAttachmentService) getService();
				CjAttachmentForm form = (CjAttachmentForm) actionForm;
				Map result = service.delCjAttachmentByObjId(form
						.getAttachmentAttachId());
				return result;
			}
		});
	}
}
