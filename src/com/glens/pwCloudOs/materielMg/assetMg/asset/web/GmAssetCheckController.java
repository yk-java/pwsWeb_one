package com.glens.pwCloudOs.materielMg.assetMg.asset.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.materielMg.assetMg.asset.service.GmAssetCheckService;
import com.glens.pwCloudOs.materielMg.assetMg.asset.vo.GmAssetCheck;

@FormProcessor(clazz = "com.glens.pwCloudOs.materielMg.assetMg.asset.web.GmAssetCheckForm")
@RequestMapping("pmsServices/materielMg/assetMg/assetCheck")
public class GmAssetCheckController extends EAPJsonAbstractController {

	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				UserToken token = getToken(request);
				GmAssetCheckForm form = (GmAssetCheckForm) actionForm;
				Map<String, Object> params = form.toMap();
				if (token != null) {
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {
						params.put("districtManager", token.getEmployeeCode());
					}

					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token
							.getRoleCode())) {
						params.put("proManager", token.getEmployeeCode());
					}

				}

				GmAssetCheckService gmAssetCheckService = (GmAssetCheckService) getService();
				PageBean page = gmAssetCheckService.queryForPage(params,
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {
					List list = page.getList();
					for (Object obj : list) {
						Map m = (Map) obj;
						m.put("QUERY_DATE", form.getFromDate());
						String clientsCount = (String) m.get("CLIENTS");
						String clientsMan = (String) m.get("CLIENTSMAN");

						int dayStatus = Integer.parseInt(clientsCount);
						if (dayStatus == 0) {
							m.put("DAYSTATUS", "未扫码");
						} else if (dayStatus >= 2 && clientsMan.startsWith("1")) {
							m.put("DAYSTATUS", "是");
						} else if (dayStatus == 1 && clientsMan.startsWith("1")) {
							m.put("DAYSTATUS", "否");
						} else if (clientsMan.startsWith("2")) {
							m.put("DAYSTATUS", "未盘点先使用");
						}
					}
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

	@RequestMapping(value = "assetUserList", method = RequestMethod.GET)
	public Object assetUserList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				GmAssetCheckForm form = (GmAssetCheckForm) actionForm;
				GmAssetCheckService gmAssetCheckService = (GmAssetCheckService) getService();
				List list = gmAssetCheckService.assetUserList(form.toMap());
				ListResult result = new ListResult();
				if (list != null) {
					for (Object obj : list) {
						Map m = (Map) obj;
						String checkEndDate = (String) m.get("CHECK_END_DATE");
						try {
							if (!form.getFromDate().equals(
									DateTimeUtil.formatDate(new Date(),
											DateTimeUtil.FORMAT_2))) {
								m.put("CHECK_END_DATE", form.getFromDate()
										+ " 23:59:59");
							}
							m.put("QUERY_DATE", form.getFromDate());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
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

	@RequestMapping(value = "queryImages", method = RequestMethod.GET)
	public Object queryImages(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				GmAssetCheckService gmAssetCheckService = (GmAssetCheckService) getService();
				GmAssetCheckForm form = (GmAssetCheckForm) actionForm;
				form.setPerpage(2);
				PageBean page = gmAssetCheckService.queryPageImages(
						form.toMap(), form.getCurrentPage(), form.getPerpage());
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

	@RequestMapping(method = RequestMethod.GET, value = "export")
	public Object export(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				UserToken token = getToken(request);
				GmAssetCheckService gmAssetCheckService = (GmAssetCheckService) getService();
				GmAssetCheckForm form = (GmAssetCheckForm) actionForm;
				Map<String, Object> params = form.toMap();
				if (token != null) {
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {
						params.put("districtManager", token.getEmployeeCode());
					}
				}

				List<Map<String, Object>> result = gmAssetCheckService
						.queryForExport(params);
				for (int i = 0; i < result.size(); i++) {
					Map m = result.get(i);
					m.put("QUERY_DATE", form.getFromDate());
					String clientsCount = (String) m.get("CLIENTS");
					String clientsMan = (String) m.get("CLIENTSMAN");

					int dayStatus = Integer.parseInt(clientsCount);
					if (dayStatus == 0) {
						m.put("DAYSTATUS", "未扫码");
					} else if (dayStatus >= 2 && clientsMan.startsWith("1")) {
						m.put("DAYSTATUS", "是");
					} else if (dayStatus == 1 && clientsMan.startsWith("1")) {
						m.put("DAYSTATUS", "否");
					} else if (clientsMan.startsWith("2")) {
						m.put("DAYSTATUS", "未盘点先使用");
					}

					String status = (Integer) m.get("STATUS") + "";
					if (status.equals("1")) {
						m.put("PRO_UNIT", "闲置");
					} else if (status.equals("2")) {
						m.put("PRO_UNIT", m.get("LOAN_UNIT_NAME"));
					} else if (status.equals("3")) {
						m.put("PRO_UNIT", m.get("LOAN_PRO_NAME"));
					} else if (status.equals("4")) {
						m.put("PRO_UNIT", "项目维修");
					} else if (status.equals("5")) {
						m.put("PRO_UNIT", "维修");
					} else if (status.equals("6")) {
						m.put("PRO_UNIT", "待报废");
					} else if (status.equals("7")) {
						m.put("PRO_UNIT", "已报废");
					} else if (status.equals("8")) {
						m.put("PRO_UNIT", "总部仓库闲置");
						m.put("DAYSTATUS", "在库");
					}
				}
				return result;
			}

			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=AssetCheck.xls");
						ExcelHelper excelHelper = new ExcelHelper();
						List dataList = (List) data;
						excelHelper.writeData(response.getOutputStream(),
								GmAssetCheck.class, dataList);
						// response.getOutputStream().write("abc".getBytes());
					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
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
				GmAssetCheckService service = (GmAssetCheckService) getService();
				Map map = (Map) service.get(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (map != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(map);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}
}
