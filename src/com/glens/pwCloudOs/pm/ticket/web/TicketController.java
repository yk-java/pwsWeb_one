package com.glens.pwCloudOs.pm.ticket.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.ticket.service.TicketService;

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.ticket.web.TicketForm")
@RequestMapping("pmsServices/pm/ticket")
public class TicketController extends EAPJsonAbstractController {

	@Override
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TicketService ser = (TicketService) getService();

				TicketForm form = (TicketForm) actionForm;
				int num = ser.getNum(form.getCompanyCode());// 已经存在的数量
				int start = 201700001;
				int worknum = form.getWorkticketNumber();
				int startNo = start + num;
				int endNo = (start + num + worknum) - 1;
				form.setStartNo(startNo + "");
				form.setEndNo(endNo + "");
				boolean ok = getService().insert(form.toVo());
				ser.updateNum(form.getCompanyCode(), (num + worknum));

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

	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				Map map = actionForm.toMap();
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}
				PageBean page = getService().queryForPage(map,
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(page);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

}
