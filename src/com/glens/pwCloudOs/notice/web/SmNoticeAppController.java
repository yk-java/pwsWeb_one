package com.glens.pwCloudOs.notice.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.notice.service.SmNoticeService;
import com.glens.pwCloudOs.notice.vo.SmNotice;

@FormProcessor(clazz = "com.glens.pwCloudOs.notice.web.SmNoticeForm")
@RequestMapping("pmsServices/appNotice")
public class SmNoticeAppController extends EAPJsonAbstractController {

	private static Log logger = LogFactory.getLog(SmNoticeAppController.class);

	/**
	 * 历史记录手机端
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "pageList", method = RequestMethod.GET)
	public Object pageList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				SmNoticeService smNoticeService = (SmNoticeService) service;
				SmNoticeForm form = (SmNoticeForm) actionForm;
				List list = smNoticeService.queryForList(form.toMap());
				BeanResult result = new BeanResult();
				if (list != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					result.setData(list);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("无数据");
				}
				return result;
			}
		});
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// 状态 0 全部 1未读 2 已读
				String status = request.getParameter("status");
				String accountCode = request.getParameter("accountCode");
				BeanResult result = new BeanResult();
				SmNoticeService smNoticeService = (SmNoticeService) service;
				List<Map<String, Object>> list = smNoticeService
						.queryNoticeList(accountCode, status);
				if (list != null && list.size() > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					result.setData(list);
				} else {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("无数据");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public Object detail(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// 消息ID
				String rowid = request.getParameter("rowid");
				// 用户code
				String accountCode = request.getParameter("accountCode");

				BeanResult result = new BeanResult();
				SmNoticeService smNoticeService = (SmNoticeService) service;
				SmNotice sm = smNoticeService.queryDetail(accountCode, rowid);
				if (sm != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					result.setData(sm);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("无数据");
				}

				return result;
			}
		});
	}
}
