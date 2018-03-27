package com.glens.pwCloudOs.login.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.eapAuth.client.web.AuthClientFilter;
import com.glens.eap.eapAuth.core.authentication.Credential;
import com.glens.eap.eapAuth.core.authentication.handlers.PasswordEncoder;
import com.glens.eap.eapAuth.core.authentication.impl.UsernamePasswordCredential;
import com.glens.eap.eapAuth.core.service.EapsoService;
import com.glens.eap.eapAuth.core.service.LoginResult;
import com.glens.eap.eapAuth.web.CredentialResolver;
import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.eap.platform.framework.web.support.response.TokenResult;
import com.glens.pwCloudOs.login.service.LoginService;

@FormProcessor(clazz = "com.glens.pwCloudOs.login.web.LoginForm")
@RequestMapping("/pmsServices/loginService")
public class LoginController extends EAPJsonAbstractController {

	public static final String USER_NAME = "侍尧";

	@RequestMapping(method = RequestMethod.GET, value = "moduleTree")
	public Object moduleTree(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				LoginService loginService = (LoginService) EAPContext
						.getContext().getBean("loginService");
				// 帐号
				String account = request.getParameter("account");
				ListResult lr = new ListResult();
				try {

					lr.setStatusCode(ResponseConstants.OK);
					lr.setResultMsg("返回结果成功");
					List<Map<String, Object>> list = loginService
							.queryModuleList(account);
					lr.setList(list);
				} catch (Exception e) {
					e.printStackTrace();
					lr.setStatusCode(ResponseConstants.SERVER_ERROR);
					lr.setResultMsg("查询失败");
				}

				return lr;
			}

		});
	}

	@RequestMapping(method = RequestMethod.GET)
	public Object login(HttpServletRequest request, HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				CredentialResolver credentialResolver = (CredentialResolver) EAPContext
						.getContext().getBean("credentialResolver");
				TokenResult result = new TokenResult();
				// 解析用户凭据。
				Credential credential = credentialResolver
						.resolveCredential(request);
				if (credential == null) {

					result.setStatusCode(ResponseConstants.UNAUTHORIZED);
					result.setResultMsg("登录失败,请输入用户名、密码信息!");
				} else {

					EapsoService soService = (EapsoService) EAPContext
							.getContext().getBean("eapsoService");
					LoginResult loginResult = soService.login(credential);
					if (loginResult != null) {

						if (loginResult.isSuccess()) {

							LoginService loginService = (LoginService) service;
							UsernamePasswordCredential upCredential = (UsernamePasswordCredential) credential;
							UserToken token = loginService
									.getAccountByAccountName(upCredential
											.getUsername());
							if (token != null) {

								result.setStatusCode(ResponseConstants.OK);
								result.setResultMsg("登录成功!");
								result.setTicket(loginResult
										.getAuthentication().getTicket());
								result.setData(token);

								request.getSession().setAttribute(
										UserToken.KEY_TOKEN, token);
								request.getSession().removeAttribute(AuthClientFilter.USER_STATE_IN_SESSION_KEY);
								System.out
										.println(request.getSession().getId());

							} else {

								result.setStatusCode(ResponseConstants.UNAUTHORIZED);
								result.setResultMsg("登录认证失败!");
							}

						} else {

							result.setStatusCode(ResponseConstants.UNAUTHORIZED);
							result.setResultMsg(loginResult.getMsgKey());
						}
					} else {

						result.setStatusCode(ResponseConstants.UNAUTHORIZED);
						result.setResultMsg("登录认证失败!");
					}
				}

				return result;
			}
		});
	}
	@RequestMapping(method = RequestMethod.GET, value = "logout")
	public Object logout(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				request.getSession().removeAttribute(UserToken.KEY_TOKEN);
				request.getSession().removeAttribute(AuthClientFilter.USER_STATE_IN_SESSION_KEY);
				return BeanResult.success("ok", "ok");
			}
		});
	}
	@RequestMapping(method = RequestMethod.GET, value = "wxlogin")
	public Object wxlogin(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				LoginForm form = (LoginForm) actionForm;
				TokenResult result = new TokenResult();
				if (form.getUserName().equals(USER_NAME)) {
					CredentialResolver credentialResolver = (CredentialResolver) EAPContext
							.getContext().getBean("credentialResolver");

					// 解析用户凭据。
					Credential credential = credentialResolver
							.resolveCredential(request);
					if (credential == null) {

						result.setStatusCode(ResponseConstants.UNAUTHORIZED);
						result.setResultMsg("登录失败,请输入用户名、密码信息!");
					} else {

						EapsoService soService = (EapsoService) EAPContext
								.getContext().getBean("eapsoService");
						LoginResult loginResult = soService.login(credential);
						if (loginResult != null) {

							if (loginResult.isSuccess()) {

								LoginService loginService = (LoginService) service;
								UsernamePasswordCredential upCredential = (UsernamePasswordCredential) credential;
								UserToken token = loginService
										.getAccountByAccountName(upCredential
												.getUsername());
								if (token != null) {

									result.setStatusCode(ResponseConstants.OK);
									result.setResultMsg("登录成功!");
									result.setTicket(loginResult
											.getAuthentication().getTicket());
									result.setData(token);

									request.getSession().setAttribute(
											UserToken.KEY_TOKEN, token);

									System.out.println(request.getSession()
											.getId());

								} else {

									result.setStatusCode(ResponseConstants.UNAUTHORIZED);
									result.setResultMsg("登录认证失败!");
								}

							} else {

								result.setStatusCode(ResponseConstants.UNAUTHORIZED);
								result.setResultMsg(loginResult.getMsgKey());
							}
						} else {

							result.setStatusCode(ResponseConstants.UNAUTHORIZED);
							result.setResultMsg("登录认证失败!");
						}
					}
				} else {
					result.setStatusCode(ResponseConstants.UNAUTHORIZED);
					result.setResultMsg("登录认证失败!");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "psw", method = RequestMethod.POST)
	public Object updatePsw(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				TokenResult result = new TokenResult();
				LoginForm loginForm = (LoginForm) actionForm;
				UserToken token = getToken(request);
				if (token != null) {
					PasswordEncoder pswEncoder = (PasswordEncoder) EAPContext
							.getContext().getBean("passwordEncoder");
					String encodeOldPsw = pswEncoder.encode(loginForm
							.getOldPsw());
					if (token.getAccountCode().equals(
							loginForm.getAccountCode())
							&& token.getAccountPsw().equals(encodeOldPsw)) {

						String encodePsw = pswEncoder.encode(loginForm.getPsw());
						if (!token.getAccountPsw().equals(encodePsw)) {

							LoginService loginService = (LoginService) getService();
							int iCount = loginService.updateUserPsw(
									loginForm.getAccountCode(), encodePsw);

							if (iCount > 0) {

								result.setStatusCode(ResponseConstants.OK);
								result.setResultMsg("密码修改成功!");
								token.setAccountPsw(encodePsw);

							} else {

								result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
								result.setResultMsg("密码修改失败!");
							}
						} else {

							result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
							result.setResultMsg("新密码不能和旧密码一致!");
						}
					} else {

						result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
						result.setResultMsg("无权修改!");
					}
				} else {

					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("登录超时，请重新登录!");
				}

				return result;
			}
		});
	}
}
