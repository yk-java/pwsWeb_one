package com.glens.eap.sys.orgEmployee.account.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.sys.orgEmployee.account.service.PfAccountService;
import com.glens.eap.sys.orgEmployee.account.vo.PfAccount;

@FormProcessor(clazz = "com.glens.eap.sys.orgEmployee.account.web.PfAccountForm")
@RequestMapping("/pmsServices/sys/orgEmployee/account")
public class PfAccountController extends EAPJsonAbstractController {
	@Override
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				PfAccountService pfAccountService = (PfAccountService) EAPContext
						.getContext().getBean("pfAccountService");
				PfAccountForm form = (PfAccountForm) actionForm;
				String ticket = request.getParameter("ticket");
				Map result = pfAccountService.querypfAccountList(form);

				return result;
			}

		});
	}
	@Override
	@RequestMapping(method = RequestMethod.POST, value = "add")
	public Object insert(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				PfAccountService pfAccountService = (PfAccountService) EAPContext
						.getContext().getBean("pfAccountService");
				PfAccountForm form = (PfAccountForm) actionForm;
				String ticket = request.getParameter("ticket");
				PfAccount pfaccount = (PfAccount) form.toVo();
				pfaccount.setSysCreateTime(new Date());
				Map result = pfAccountService.savePfAccount(pfaccount);
				return result;
			}

		});
	}
	@Override
	@RequestMapping(method = RequestMethod.POST, value = "update")
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				PfAccountService pfAccountService = (PfAccountService) EAPContext
						.getContext().getBean("pfAccountService");
				PfAccountForm form = (PfAccountForm) actionForm;
				String ticket = request.getParameter("ticket");
				PfAccount pfaccount = (PfAccount) form.toVo();
				pfaccount.setSysUpdateTime(new Date());
				Map result = pfAccountService.updatePfAccount(pfaccount);
				return result;
			}

		});
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "resetPass")
	public Object resetPass(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				PfAccountService pfAccountService = (PfAccountService) EAPContext
						.getContext().getBean("pfAccountService");
				PfAccountForm form = (PfAccountForm) actionForm;
				PfAccount pfaccount = (PfAccount) form.toVo();
				pfaccount.setSysUpdateTime(new Date());
				
				Map result1 = pfAccountService.queryPfAccount(form);
				if(result1==null){
					Map result = new HashMap();
					result.put("statusCode", ResponseConstants.UPDATE_DATA_ERROR);
					result.put("resultMsg", "无权操作");
					return result;
				}
				PfAccount pfaccount_old = (PfAccount)result1.get("data");
				
				UserToken token = getToken(request);
				if (token != null && (token.getAccountCode().equals(pfaccount.getAccountCode()) || token.getCompanyCode().equals(pfaccount_old.getCompanyCode()))) {
					
				}else{
					Map result = new HashMap();
					result.put("statusCode", ResponseConstants.UPDATE_DATA_ERROR);
					result.put("resultMsg", "无权操作");
					return result;
				}
				Map result = pfAccountService.reset(pfaccount);
				return result;
			}

		});
	}
	
	@Override
	@RequestMapping(method = RequestMethod.POST, value = "delete")
	public Object delete(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				PfAccountService pfAccountService = (PfAccountService) EAPContext
						.getContext().getBean("pfAccountService");
				PfAccountForm form = (PfAccountForm) actionForm;
				String ticket = request.getParameter("ticket");
				PfAccount pfaccount = (PfAccount) form.toVo();
				pfaccount.setSysProcessFlag("0");
				pfaccount.setSysDeleteTime(new Date());
				Map result = pfAccountService.updatePfAccount(pfaccount);
				return result;
			}

		});
	}
	@Override
	@RequestMapping(method = RequestMethod.GET, value = "get")
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				PfAccountService pfAccountService = (PfAccountService) EAPContext
						.getContext().getBean("pfAccountService");
				PfAccountForm form = (PfAccountForm) actionForm;
				String ticket = request.getParameter("ticket");
				Map result = pfAccountService.queryPfAccount(form);
				return result;
			}

		});
	}
}
