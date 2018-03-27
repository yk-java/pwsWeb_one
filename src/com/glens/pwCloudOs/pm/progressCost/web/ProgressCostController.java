package com.glens.pwCloudOs.pm.progressCost.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.progressCost.service.ProgressCostService;

@RequestMapping("pmsServices/pm/progressCost")
public class ProgressCostController extends EAPJsonAbstractController {

	private ProgressCostService progressCostService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				String proNo = request.getParameter("proNo");
				String searchName = request.getParameter("searchName");
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				try {
					paramsMap.put("proNo", proNo);
					paramsMap.put("searchName", searchName);
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
							.getRoleCode())) {
						paramsMap.put("deptCode", token.getUnitCode());
					}
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE
							.equals(token.getRoleCode())) {
						paramsMap.put("districtManager", token.getEmployeeCode());
					}
					List list = progressCostService
							.queryProgressCostList(paramsMap);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 查询项目成员
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryProMember", method = RequestMethod.GET)
	public Object queryProMember(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNo = request.getParameter("proNo");
				String searchName = request.getParameter("searchName");
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				try {
					paramsMap.put("proNo", proNo);
					// paramsMap.put("searchName", searchName);
					List list = progressCostService.queryProMember(paramsMap);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 查询项目合同
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryContract", method = RequestMethod.GET)
	public Object queryContract(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNo = request.getParameter("proNo");
				String searchName = request.getParameter("searchName");
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				try {
					paramsMap.put("proNo", proNo);
					// paramsMap.put("searchName", searchName);
					List list = progressCostService.queryContract(paramsMap);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 项目开票
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryInvoice", method = RequestMethod.GET)
	public Object queryInvoice(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNo = request.getParameter("proNo");
				String searchName = request.getParameter("searchName");
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				try {
					paramsMap.put("proNo", proNo);
					// paramsMap.put("searchName", searchName);
					List list = progressCostService.queryInvoice(paramsMap);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 项目回款
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryRepayment", method = RequestMethod.GET)
	public Object queryRepayment(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNo = request.getParameter("proNo");
				String searchName = request.getParameter("searchName");
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				try {
					paramsMap.put("proNo", proNo);
					// paramsMap.put("searchName", searchName);
					List list = progressCostService.queryRepayment(paramsMap);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 项目文档
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryDocument", method = RequestMethod.GET)
	public Object queryDocument(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNo = request.getParameter("proNo");
				String type = request.getParameter("type");
				String title = request.getParameter("title");
				String docTypelibCode = request.getParameter("docTypelibCode");
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				try {
					paramsMap.put("proNo", proNo);
					paramsMap.put("type", type);
					paramsMap.put("title", title);
					paramsMap.put("docTypelibCode", docTypelibCode);
					List list = progressCostService.queryProDocument(paramsMap);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 项目设备
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryProAsset", method = RequestMethod.GET)
	public Object queryProAsset(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNo = request.getParameter("proNo");
				String type = request.getParameter("type");
				String searchName = request.getParameter("searchName");
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				try {
					paramsMap.put("proNo", proNo);
					paramsMap.put("type", type);
					// paramsMap.put("searchName", searchName);
					List list = progressCostService.queryProAsset(paramsMap);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 项目宿舍
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryProDorm", method = RequestMethod.GET)
	public Object queryProDorm(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNo = request.getParameter("proNo");
				String type = request.getParameter("type");
				String searchName = request.getParameter("searchName");
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				try {
					paramsMap.put("proNo", proNo);
					paramsMap.put("type", type);
					// paramsMap.put("searchName", searchName);
					List list = progressCostService.queryProDorm(paramsMap);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 项目报销
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryProReturn", method = RequestMethod.GET)
	public Object queryProReturn(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNo = request.getParameter("proNo");
				String type = request.getParameter("type");
				String searchName = request.getParameter("searchName");
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				try {
					paramsMap.put("proNo", proNo);
					paramsMap.put("type", type);
					// paramsMap.put("searchName", searchName);
					List list = progressCostService.queryProReturn(paramsMap);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 完工进度
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "finishProgress", method = RequestMethod.POST)
	public Object finishProgress(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNostr = request.getParameter("proNostr");
				String[] proAttr = proNostr.split(",");
				List<String> strList = Arrays.asList(proAttr);
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				try {
					paramsMap.put("list", strList);
					List<Map<String, Object>> list = progressCostService
							.queryFinishProgress(paramsMap);
					List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
					for (int i = 0; i < strList.size(); i++) {
						String proNo = strList.get(i);
						boolean flag = true;
						for (Map<String, Object> m : list) {
							String currentProNo = (String) m.get("prono");
							if (proNo.equals(currentProNo)) {
								flag = false;
							}
						}

						// 不包括
						if (flag) {
							Map<String, Object> mm = new HashMap<String, Object>();
							mm.put("prono", proNo);
							mm.put("A1", "0");
							mm.put("A2", "0");
							mm.put("A3", "0");
							mm.put("A4", "无");
							list1.add(mm);
						}
					}

					list.addAll(list1);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 综合管控
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "manageControl", method = RequestMethod.POST)
	public Object manageControl(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNostr = request.getParameter("proNostr");
				String[] proAttr = proNostr.split(",");
				List<String> strList = Arrays.asList(proAttr);
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				try {
					List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
					for (int i = 0; i < strList.size(); i++) {
						String proNo = strList.get(i);
						boolean flag = true;

						// 不包括
						if (flag) {
							Map<String, Object> mm = new HashMap<String, Object>();
							mm.put("prono", proNo);
							mm.put("B1", "0");
							mm.put("B2", "0");
							list1.add(mm);
						}
					}

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list1);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 财务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "financial", method = RequestMethod.POST)
	public Object financial(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNostr = request.getParameter("proNostr");
				// 1合同 2开票 3回款
				String type = request.getParameter("type");
				String[] proAttr = proNostr.split(",");
				List<String> strList = Arrays.asList(proAttr);
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> list = null;
				try {
					paramsMap.put("list", strList);
					paramsMap.put("type", type);

					list = progressCostService.queryFinancial(paramsMap);
					for (int i = 0; i < strList.size(); i++) {
						String proNo = strList.get(i);
						boolean flag = true;
						for (Map<String, Object> m : list) {
							String currentProNo = (String) m.get("prono");
							if (proNo.equals(currentProNo)) {
								flag = false;
							}
						}

						// 不包括
						if (flag) {
							Map<String, Object> mm = new HashMap<String, Object>();
							mm.put("prono", proNo);
							if (type.equals("1")) {
								mm.put("C1", "0");
							} else if (type.equals("2")) {
								mm.put("C2", "0");
							} else if (type.equals("3")) {
								mm.put("C3", "0");
							}
							list1.add(mm);
						}
					}

					list.addAll(list1);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 文档
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "document", method = RequestMethod.POST)
	public Object document(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNostr = request.getParameter("proNostr");
				String[] proAttr = proNostr.split(",");
				List<String> strList = Arrays.asList(proAttr);
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> list = null;
				try {
					paramsMap.put("list", strList);
					list = progressCostService.queryDocument(paramsMap);
					for (int i = 0; i < strList.size(); i++) {
						String proNo = strList.get(i);
						boolean flag = true;
						for (Map<String, Object> m : list) {
							String currentProNo = (String) m.get("prono");
							if (proNo.equals(currentProNo)) {
								flag = false;
							}
						}

						// 不包括
						if (flag) {
							Map<String, Object> mm = new HashMap<String, Object>();
							mm.put("prono", proNo);
							mm.put("D1", "0");
							mm.put("D2", "0");
							list1.add(mm);
						}
					}

					list.addAll(list1);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 报销
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "expensesClaim", method = RequestMethod.POST)
	public Object expensesClaim(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNostr = request.getParameter("proNostr");
				String[] proAttr = proNostr.split(",");
				List<String> strList = Arrays.asList(proAttr);
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> list = null;
				try {
					paramsMap.put("list", strList);
					list = progressCostService.queryExpensesClaim(paramsMap);
					for (int i = 0; i < strList.size(); i++) {
						String proNo = strList.get(i);
						boolean flag = true;
						for (Map<String, Object> m : list) {
							String currentProNo = (String) m.get("prono");
							if (proNo.equals(currentProNo)) {
								flag = false;
							}
						}

						// 不包括
						if (flag) {
							Map<String, Object> mm = new HashMap<String, Object>();
							mm.put("prono", proNo);
							mm.put("E4", "0");
							list1.add(mm);
						}
					}

					list.addAll(list1);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 宿舍
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "expensesDorm", method = RequestMethod.POST)
	public Object expensesDorm(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNostr = request.getParameter("proNostr");
				String[] proAttr = proNostr.split(",");
				List<String> strList = Arrays.asList(proAttr);
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> list = null;
				try {
					paramsMap.put("list", strList);
					list = progressCostService.queryExpensesDorm(paramsMap);
					for (int i = 0; i < strList.size(); i++) {
						String proNo = strList.get(i);
						boolean flag = true;
						for (Map<String, Object> m : list) {
							String currentProNo = (String) m.get("prono");
							if (proNo.equals(currentProNo)) {
								flag = false;
							}
						}

						// 不包括
						if (flag) {
							Map<String, Object> mm = new HashMap<String, Object>();
							mm.put("prono", proNo);
							mm.put("E3", "0");
							list1.add(mm);
						}
					}

					list.addAll(list1);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	/**
	 * 设备租用
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "expensesAsset", method = RequestMethod.POST)
	public Object expensesAsset(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String proNostr = request.getParameter("proNostr");
				String[] proAttr = proNostr.split(",");
				List<String> strList = Arrays.asList(proAttr);
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				ListResult result = new ListResult();
				List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> list = null;
				try {
					paramsMap.put("list", strList);
					list = progressCostService.queryExpensesAsset(paramsMap);
					for (int i = 0; i < strList.size(); i++) {
						String proNo = strList.get(i);
						boolean flag = true;
						for (Map<String, Object> m : list) {
							String currentProNo = (String) m.get("prono");
							if (proNo.equals(currentProNo)) {
								flag = false;
							}
						}

						// 不包括
						if (flag) {
							Map<String, Object> mm = new HashMap<String, Object>();
							mm.put("prono", proNo);
							mm.put("E2", "0");
							list1.add(mm);
						}
					}

					list.addAll(list1);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取数据成功");
					result.setList(list);

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("获取数据失败");
				}
				return result;
			}

		});
	}

	public void setProgressCostService(ProgressCostService progressCostService) {
		this.progressCostService = progressCostService;
	}

}
