/**
 * @Title: PwBaoAppController.java
 * @Package com.glens.pwCloudOs.pwBaoApp.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-8-13 上午11:46:53
 * @version V1.0
 */

package com.glens.pwCloudOs.pwBaoApp.web;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.eapAuth.core.authentication.handlers.PasswordEncoder;
import com.glens.eap.eapAuth.core.authentication.impl.UsernamePasswordCredential;
import com.glens.eap.eapAuth.core.service.EapsoService;
import com.glens.eap.eapAuth.core.service.LoginResult;
import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.utils.HttpUtil;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.ListsResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.eap.platform.framework.web.support.response.TokenResult;
import com.glens.pwCloudOs.login.service.LoginService;
import com.glens.pwCloudOs.materielMg.assetMg.asset.vo.AssetVo;
import com.glens.pwCloudOs.materielMg.assetMg.asset.vo.GmAssetCheck;
import com.glens.pwCloudOs.materielMg.goods.service.GoodsCategoryService;
import com.glens.pwCloudOs.materielMg.goods.service.GoodsDetailService;
import com.glens.pwCloudOs.materielMg.goods.service.GoodsService;
import com.glens.pwCloudOs.materielMg.goods.service.StoreHouseService;
import com.glens.pwCloudOs.materielMg.goods.vo.GoodsDetail;
import com.glens.pwCloudOs.notice.service.SmNoticeService;
import com.glens.pwCloudOs.notice.vo.SmNotice;
import com.glens.pwCloudOs.pm.scene.spot.service.ProSpotService;
import com.glens.pwCloudOs.pm.scene.workers.service.ProWorkersService;
import com.glens.pwCloudOs.pwBaoApp.service.PwBaoAppService;
import com.glens.pwCloudOs.weixin.bean.WeixinResult;
import com.glens.pwCloudOs.weixin.service.WeixinService;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@FormProcessor(clazz = "com.glens.pwCloudOs.pwBaoApp.web.PwBaoAppForm")
@RequestMapping("/pmsServices/pwBaoAppService")
public class PwBaoAppController extends EAPJsonAbstractController {

	private static Log logger = LogFactory.getLog(PwBaoAppController.class);

	private SmNoticeService smNoticeService;

	private WeixinService weixinService;

	private StoreHouseService storeHouseService;

	private ProWorkersService proWorkersService;

	private final static String PASSWORD = "123456";

	private LoginService loginService;

	private GoodsService goodsService;

	private GoodsDetailService goodsDetailService;

	private GoodsCategoryService goodsCategoryService;

	private ProSpotService proSpotService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public Object login(HttpServletRequest request, HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PwBaoAppService appService = (PwBaoAppService) getService();
				PwBaoAppForm appForm = (PwBaoAppForm) actionForm;
				BeanResult result = new BeanResult();
				UsernamePasswordCredential credential = new UsernamePasswordCredential();
				credential.setPassword(appForm.getPsw());
				credential.setUsername(appForm.getAccountName());
				EapsoService soService = (EapsoService) EAPContext.getContext()
						.getBean("eapsoService");
				LoginResult loginResult = soService.login(credential);
				if (loginResult != null) {

					if (loginResult.isSuccess()) {

						Map<String, Object> userInfo = appService
								.getAccountByAccountName(appForm
										.getAccountName());
						if (userInfo != null && userInfo.size() > 0) {

							userInfo.put("ticket", loginResult
									.getAuthentication().getTicket());
							result.setStatusCode(ResponseConstants.OK);
							result.setResultMsg("登录成功！");

							result.setData(userInfo);

							UserToken token = new UserToken();
							token.setAccountCode((String) userInfo
									.get("accountCode"));
							token.setAccountName((String) userInfo
									.get("accountName"));
							token.setCompanyCode((String) userInfo
									.get("companyCode"));
							token.setEmployeeCode((String) userInfo
									.get("employeeCode"));
							token.setEmployeeName((String) userInfo
									.get("employeeName"));
							token.setUnitCode((String) userInfo.get("unitCode"));
							token.setUnitName((String) userInfo.get("unitName"));
							request.getSession().setAttribute(
									UserToken.KEY_TOKEN, token);
						} else {

							result.setStatusCode(ResponseConstants.NO_DATA);
							result.setResultMsg("该用户不存在");
						}
					} else {

						result.setStatusCode(ResponseConstants.UNAUTHORIZED);
						// result.setResultMsg(loginResult.getMsgKey());
						result.setResultMsg("输入的密码不正确，请验证！");
					}
				} else {

					result.setStatusCode(ResponseConstants.UNAUTHORIZED);
					result.setResultMsg("登录认证失败!");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "commuteConfig", method = RequestMethod.GET)
	public Object getCommuteConfig(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PwBaoAppService appService = (PwBaoAppService) getService();
				PwBaoAppForm appForm = (PwBaoAppForm) actionForm;
				BeanResult result = new BeanResult();
				Map<String, Object> commuteConfigInfo = appService
						.getCommuteConfig(appForm.toMap());
				if (commuteConfigInfo != null && commuteConfigInfo.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("通勤设置信息获取成功！");
					result.setData(commuteConfigInfo);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到通勤设置信息");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "lastMobileVersion", method = RequestMethod.GET)
	public Object getLastMobileVersion(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PwBaoAppService appService = (PwBaoAppService) getService();
				BeanResult result = new BeanResult();
				Map<String, Object> mobileVersonInfo = appService
						.getLastMobileVersion();
				if (mobileVersonInfo != null && mobileVersonInfo.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("外业宝最新版本获取成功！");
					result.setData(mobileVersonInfo);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到外业宝最新版本");
				}

				return result;
			}
		});
	}

	// 获取物资所在仓库物资信息列表
	@RequestMapping(value = "getStoreHouseGoods", method = RequestMethod.GET)
	public Object getStoreHouse(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ListsResult result = new ListsResult();
				List<Map<String, Object>> storeHouseList = storeHouseService
						.getStoreHouseList();
				List<Map<String, Object>> goodsList = goodsCategoryService
						.getGoodsList();
				if (storeHouseList != null && storeHouseList.size() > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取物资所在仓库物资信息信息获取成功！");
					result.setFirlist(storeHouseList);
					result.setSeclist(goodsList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到所在仓库物资信息信息");
				}
				return result;
			}
		});
	}

	// 获取物资库存
	@RequestMapping(value = "getGoodsCount", method = RequestMethod.GET)
	public Object getGoodsCount(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				BeanResult result = new BeanResult();
				String specCode = request.getParameter("specCode");
				String storeCode = request.getParameter("storeCode");
				Map paramsMap = new HashMap<String, Object>();
				paramsMap.put("specCode", specCode);
				paramsMap.put("storeCode", storeCode);
				Map map = goodsService.getGoodsCount(paramsMap);
				if (map != null && map.size() > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取到物资库存信息获取成功！");
					result.setData(map);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到物资库存");
				}
				return result;
			}
		});
	}

	@RequestMapping(value = "typeList", method = RequestMethod.GET)
	public Object getIntelligenceType(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PwBaoAppService appService = (PwBaoAppService) getService();
				ListResult result = new ListResult();
				List<Map<String, String>> list = appService
						.getIntelligenceType();
				if (list != null && list.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取情报类型数据成功！");
					result.setList(list);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有情报类型数据");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "urgencyList", method = RequestMethod.GET)
	public Object getIntelligenceUrgency(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PwBaoAppService appService = (PwBaoAppService) getService();
				ListResult result = new ListResult();
				List<Map<String, String>> list = appService
						.getIntelligenceUrgency();
				if (list != null && list.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取情报紧急程度类型数据成功！");
					result.setList(list);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("有情报紧急程度类型数据！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "intelligenceReport", method = RequestMethod.POST)
	public Object insertIntelligence(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PwBaoAppService appService = (PwBaoAppService) getService();
				ResponseResult result = new ResponseResult();
				int icount = appService.insertIntelligence(actionForm.toMap());
				if (icount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("提交情报数据成功！");
				} else {

					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
					result.setResultMsg("提交情报数据失败！");
				}

				return result;
			}
		});
	}

	/**
	 * 物资出入库
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "commitGoods", method = RequestMethod.POST)
	public Object commitGoods(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PwBaoAppService appService = (PwBaoAppService) getService();
				PwBaoAppForm appForm = (PwBaoAppForm) actionForm;
				BeanResult result = new BeanResult();

				String goodsCode = request.getParameter("goodsCode");
				String goodsName = request.getParameter("goodsName");
				String specCode = request.getParameter("specCode");
				String specName = request.getParameter("specName");
				String price = request.getParameter("price");
				String amount = request.getParameter("amount");
				String gtype = request.getParameter("gtype");
				String goodsType = request.getParameter("goodsType");
				String goodsTime = request.getParameter("goodsTime");
				String storeCode = request.getParameter("storeCode");
				String pic1 = request.getParameter("pic1");
				String pic2 = request.getParameter("pic2");
				String pic3 = request.getParameter("pic3");

				GoodsDetail goodsDetail = new GoodsDetail();
				goodsDetail.setGoodsCode(goodsCode);
				goodsDetail.setGoodsName(goodsName);
				goodsDetail.setSpecCode(specCode);
				goodsDetail.setSpecName(specName);
				goodsDetail.setPrice(new BigDecimal(price));
				goodsDetail.setAmount(Integer.parseInt(amount));
				goodsDetail.setGtype(gtype);
				goodsDetail.setGoodsType(goodsType);
				goodsDetail.setGoodsTime(goodsTime);
				goodsDetail.setStoreCode(storeCode);
				goodsDetail.setPic1(pic1);
				goodsDetail.setPic2(pic2);
				goodsDetail.setPic3(pic3);
				result = appService.commitGoods(goodsDetail);
				return result;
			}
		});
	}

	/**
	 * 盘点资产
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkAssets", method = RequestMethod.POST)
	public Object checkAssets(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PwBaoAppService appService = (PwBaoAppService) getService();
				PwBaoAppForm appForm = (PwBaoAppForm) actionForm;
				BeanResult result = new BeanResult();
				// 资产编号
				String assetCode = request.getParameter("assetCode");

				// 盘点日期
				String checkDealDate = "";
				try {
					checkDealDate = DateTimeUtil.formatDate(new Date(),
							DateTimeUtil.FORMAT_1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 盘点人员
				String checkEmployeeCode = request
						.getParameter("checkEmployeeCode");
				// 照片1
				String pic1 = request.getParameter("pic1");

				// 照片2
				String pic2 = request.getParameter("pic2");

				// 照片3
				String pic3 = request.getParameter("pic3");

				// 客户端
				String client = request.getParameter("client3");

				GmAssetCheck gmAssetCheck = new GmAssetCheck();
				gmAssetCheck.setAssetCode(assetCode);
				gmAssetCheck.setCheckDealDate(checkDealDate);
				gmAssetCheck.setCheckEmployeeCode(checkEmployeeCode);
				gmAssetCheck.setPic1(pic1);
				gmAssetCheck.setPic2(pic2);
				gmAssetCheck.setPic3(pic3);
				gmAssetCheck.setClient3(client);
				result = appService.checkAssets(gmAssetCheck);

				return result;
			}
		});
	}

	/**
	 * 查询资产信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getAsset", method = RequestMethod.GET)
	public Object getAsset(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PwBaoAppService appService = (PwBaoAppService) getService();
				PwBaoAppForm appForm = (PwBaoAppForm) actionForm;
				// 资产编号
				String assetCode = request.getParameter("assetCode");
				BeanResult result = new BeanResult();
				AssetVo assetsInfo = appService.getAsset(assetCode);
				if (assetsInfo != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("资产信息获取成功！");
					result.setData(assetsInfo);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到资产信息");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "checkin", method = RequestMethod.POST)
	public Object checkin(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PwBaoAppService appService = (PwBaoAppService) getService();
				PwBaoAppForm appForm = (PwBaoAppForm) actionForm;
				System.out.println(appForm);
				BeanResult result = new BeanResult();
				int checkinType = appForm.getCheckinType();
				Map<String, Object> checkinResultMap = appService
						.checkin(actionForm.toMap());
				Integer icount = (Integer) checkinResultMap.get("icount");
				if (icount > 0) {

					result.setStatusCode(ResponseConstants.OK);

					if (checkinType == 1) {

						result.setResultMsg("签到成功！");
						result.setData(checkinResultMap.get("checkInTime"));
					} else if (checkinType == 2) {

						result.setResultMsg("签退成功！");
					} else if (checkinType == 3) {

						result.setResultMsg("距离更新成功！");
					}

				} else {

					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
					if (checkinType == 1) {

						result.setResultMsg("签到失败！");
					} else if (checkinType == 2) {

						result.setResultMsg("签退失败！");
					}
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "myCommute", method = RequestMethod.GET)
	public Object getMyCommuteInfoByDate(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PwBaoAppService appService = (PwBaoAppService) getService();
				BeanResult result = new BeanResult();
				Map<String, Object> commuteInfo = appService
						.getMyCommuteInfoByDate(actionForm.toMap());
				if (commuteInfo != null && commuteInfo.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("通勤信息获取成功！");
					result.setData(commuteInfo);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到通勤信息");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "userPsw", method = RequestMethod.POST)
	public Object modifyUserPassword(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PwBaoAppService appService = (PwBaoAppService) getService();
				PwBaoAppForm appForm = (PwBaoAppForm) actionForm;
				ResponseResult result = new ResponseResult();
				int icount = appService.updateUserPsw(appForm.getAccountCode(),
						appForm.getPsw());
				if (icount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改密码成功！");
				} else {

					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("修改密码失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "employeeInfo", method = RequestMethod.GET)
	public Object getEmployeeInfo(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PwBaoAppService appService = (PwBaoAppService) getService();
				PwBaoAppForm appForm = (PwBaoAppForm) actionForm;
				BeanResult result = new BeanResult();

				logger.info("开始获取账号代码:" + appForm.getAccountCode() + "对应人员信息！");
				Map<String, Object> employeeInfo = appService
						.getEmployeeInfo(appForm.getAccountCode());
				if (employeeInfo != null && employeeInfo.size() > 0) {

					result.setResultMsg("获取人员信息成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(employeeInfo);

					logger.info("获取账号代码:" + appForm.getAccountCode()
							+ "对应人员信息成功！");
				} else {

					result.setResultMsg("获取人员信息失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);

					logger.info("开始获取账号代码:" + appForm.getAccountCode()
							+ "对应人员失败！");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "employeeField", method = RequestMethod.POST)
	public Object updateEmployeeField(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PwBaoAppService appService = (PwBaoAppService) getService();
				ResponseResult result = new ResponseResult();
				logger.info("开始更新人员信息");
				int updateCount = appService.updateEmployeeField(actionForm
						.toMap());
				if (updateCount > 0) {

					result.setResultMsg("更新人员信息成功！");
					result.setStatusCode(ResponseConstants.OK);
					logger.info("更新人员成功！");
				} else {

					result.setResultMsg("更新人员信息失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					logger.info("更新人员信息失败！");
				}

				return result;
			}
		});
	}

	// 轻应用
	@RequestMapping(value = "lightApp", method = RequestMethod.GET)
	public Object roleApp(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PwBaoAppService appService = (PwBaoAppService) getService();

				PwBaoAppForm form = (PwBaoAppForm) actionForm;
				ListResult result = new ListResult();
				List<Map<String, Object>> list = appService.getRoleApp(form
						.getAccountCode());
				if (list != null && list.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取轻应用成功！");
					result.setList(list);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有轻应用");
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "noticeList", method = RequestMethod.GET)
	public Object noticeList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// 状态 0 全部 1未读 2 已读
				String status = request.getParameter("status");
				String accountCode = request.getParameter("accountCode");
				BeanResult result = new BeanResult();
				List<Map<String, Object>> list = smNoticeService
						.queryNoticeList(accountCode, status);
				if (list != null && list.size() > 0) {
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

	@RequestMapping(value = "noticeDetail", method = RequestMethod.GET)
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

	/**
	 * 历史记录手机端
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "myNoticeList", method = RequestMethod.GET)
	public Object pageList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				Map<String, Object> map = new HashMap<String, Object>();
				String startDate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");
				String accountCode = request.getParameter("accountCode");
				String title = request.getParameter("title");
				String status = request.getParameter("status");
				String orderType = request.getParameter("orderType");

				map.put("startDate", startDate);
				map.put("endDate", endDate);
				map.put("accountCode", accountCode);
				if (StringUtil.isNotNull(title)) {
					map.put("title", "%" + title + "%");
				}
				map.put("status", status);
				map.put("orderType", orderType);

				List list = smNoticeService.queryForList(map);
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

	@RequestMapping(value = "userCheckStatus", method = RequestMethod.GET)
	public Object getUserCheckStatus(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				PwBaoAppService appService = (PwBaoAppService) getService();
				Map<String, Object> userCheckInfo = appService
						.getUserCheckStatus(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (userCheckInfo != null && userCheckInfo.size() > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取用户签到信息成功！");
					result.setData(userCheckInfo);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取用户签到信息失败！");
				}

				return result;
			}
		});
	}

	/**
	 * 发送验证码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "sendValidateCode", method = RequestMethod.GET)
	public Object sendValidateCode(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// 验证码code
				String code = request.getParameter("code");
				String mobilePhone = request.getParameter("mobilePhone");
				String accountName = request.getParameter("accountName");
				WeixinResult result = new WeixinResult();

				result = weixinService.sendValidateCode(code, mobilePhone,
						accountName);
				System.out.println(result);
				return result;

			}
		});
	}

	@RequestMapping(value = "resetPsw", method = RequestMethod.GET)
	public Object resetPsw(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				TokenResult result = new TokenResult();

				String mobilePhone = request.getParameter("mobilePhone");
				String accountName = request.getParameter("accountName");

				PasswordEncoder pswEncoder = (PasswordEncoder) EAPContext
						.getContext().getBean("passwordEncoder");
				String encodePsw = pswEncoder.encode(PASSWORD);

				int iCount = loginService.updateUserPswByMobile(mobilePhone,
						accountName, encodePsw);

				if (iCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("密码修改成功!");

				} else {
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
					result.setResultMsg("密码修改失败!");
				}

				return result;
			}
		});
	}

	/**
	 * 查询项目现场人员
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryProWorkers", method = RequestMethod.POST)
	public Object queryProWorkers(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				String workerCard = request.getParameter("workerCard");
				List<String> workCardList = new ArrayList<String>();
				String[] cardArray = workerCard.split(",");
				for (String card : cardArray) {
					workCardList.add(card);
				}
				ListResult result = new ListResult();
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("workCardList", workCardList);
				List list = proWorkersService.queryProWorker(paramsMap);
				System.out.println(list);
				if (list != null && list.size() > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					result.setList(list);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("无数据");
				}

				return result;
			}
		});
	}

	/**
	 * 通过卡号查询人员详细信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryProWorkerByCardNo", method = RequestMethod.POST)
	public Object queryProWorkerByCardNo(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				String workerCard = request.getParameter("workerCard");
				BeanResult result = new BeanResult();
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("workerCard", workerCard);
				Map m = proWorkersService.queryProWorkerByCardNo(paramsMap);
				System.out.println(m);
				if (m != null && m.size() > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					result.setData(m);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("无数据");
				}

				return result;
			}
		});
	}

	/**
	 * 获取莹石token
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getYSCameraAccessToken", method = RequestMethod.GET)
	public Object getYSCameraAccessToken(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String url = "https://open.ys7.com/api/lapp/token/get";
				String token = HttpUtil
						.doYSPost(
								url,
								"appKey=9a640f9595c6416dab9d055622b7429f&appSecret=353a9fa02ca3c25b0c254fc8ed40b9c3",
								"UTF-8");
				System.out.println(token);
				return JSONObject.parse(token);
			}
		});
	}

	/**
	 * 现在看板，摄像头监控
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryProSpotInfo", method = RequestMethod.POST)
	public Object queryProSpotInfo(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				String keyWord = request.getParameter("keyWord");
				ListResult result = new ListResult();
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("keyWord", keyWord);
				List list = proSpotService.queryProSpotInfo(paramsMap);

				List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
				Map<String, String> resultMap = new HashMap<String, String>();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						Map m = (Map) list.get(i);
						String proNo = m.get("PRO_NO") + "";
						String proName = m.get("PRO_NAME") + "";
						if (!resultMap.containsKey(proNo)) {
							resultMap.put(proNo, proName);
						}
					}

					for (Map.Entry<String, String> entry : resultMap.entrySet()) {
						Map<String, Object> m = new HashMap<String, Object>();
						m.put("code", entry.getKey());
						m.put("name", entry.getValue());
						resultList.add(m);
					}

					for (Map<String, Object> mm : resultList) {
						String code = (String) mm.get("code");
						List<Map<String, Object>> subList = new ArrayList<Map<String, Object>>();
						for (int i = 0; i < list.size(); i++) {
							Map m2 = (Map) list.get(i);
							String proNo = m2.get("PRO_NO") + "";
							if (code.equals(proNo)) {
								subList.add(m2);
							}
						}
						mm.put("list", subList);
					}

					System.out.println(resultList);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("无数据");
				}

				return result;
			}
		});
	}

	/**
	 * 查询工人信息列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getSpotWorkersList", method = RequestMethod.GET)
	public Object getSpotWorkersList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// 验证码code
				String code = request.getParameter("code");
				ListResult result = new ListResult();
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("spotCode", code);
				List list = proWorkersService.getSpotWorkers(paramsMap);
				System.out.println(list);
				if (list != null && list.size() > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
					result.setList(list);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("无数据");
				}

				return result;

			}
		});
	}

	public void setSmNoticeService(SmNoticeService smNoticeService) {
		this.smNoticeService = smNoticeService;
	}

	public void setWeixinService(WeixinService weixinService) {
		this.weixinService = weixinService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public void setStoreHouseService(StoreHouseService storeHouseService) {
		this.storeHouseService = storeHouseService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public void setGoodsDetailService(GoodsDetailService goodsDetailService) {
		this.goodsDetailService = goodsDetailService;
	}

	public void setGoodsCategoryService(
			GoodsCategoryService goodsCategoryService) {
		this.goodsCategoryService = goodsCategoryService;
	}

	public void setProWorkersService(ProWorkersService proWorkersService) {
		this.proWorkersService = proWorkersService;
	}

	public void setProSpotService(ProSpotService proSpotService) {
		this.proSpotService = proSpotService;
	}

}
