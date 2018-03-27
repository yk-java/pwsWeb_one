package com.glens.pwCloudOs.fm.expense.web;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.commuteMgr.performance.digitalSignage.service.CpCommuteCheckService;
import com.glens.pwCloudOs.commuteMgr.performance.digitalSignage.web.CpCommuteCheckForm;
import com.glens.pwCloudOs.fm.expense.service.FmMoneyReturnService;
import com.glens.pwCloudOs.fm.expense.vo.FmMoneyReturn;

@FormProcessor(clazz = "com.glens.pwCloudOs.fm.expense.web.FmMoneyReturnForm")
@RequestMapping("pmsServices/fm/expense/feeReturn")
public class FmMoneyReturnController extends EAPJsonAbstractController {

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				FmMoneyReturnService service = (FmMoneyReturnService) getService();
				FmMoneyReturnForm form = (FmMoneyReturnForm) actionForm;
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				PageBean page = service.queryForPage(form.toMap(),
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

	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				FmMoneyReturnService service = (FmMoneyReturnService) getService();
				FmMoneyReturnForm form = (FmMoneyReturnForm) actionForm;
				FmMoneyReturn fmMoneyReturn = service.queryFmMoneyReturn(form
						.getRowid());
				BeanResult result = new BeanResult();
				if (fmMoneyReturn != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(fmMoneyReturn);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}

		});
	}

	@RequestMapping(method = RequestMethod.POST, value = "claim")
	public Object claim(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				FmMoneyReturnService service = (FmMoneyReturnService) getService();
				FmMoneyReturnForm form = (FmMoneyReturnForm) actionForm;
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				int res = 0;
				BeanResult result = new BeanResult();
				try {
					FmMoneyReturn fr = new FmMoneyReturn();
					fr.setRowid(form.getRowid());
					fr.setProNo(form.getProNo());
					fr.setStatus("1");
					fr.setClaimDate(DateTimeUtil.formatDate(new Date(),
							DateTimeUtil.FORMAT_1));
					fr.setClaimUser(token.getAccountCode());
					service.updateSelective(fr);
					result.setStatusCode("200");
					result.setResultMsg("success");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result.setStatusCode("500");
					result.setResultMsg("fail");
				}

				return result;
			}

		});
	}
}
