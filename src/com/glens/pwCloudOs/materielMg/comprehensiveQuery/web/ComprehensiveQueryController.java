/**
 * @Title: ComprehensiveQueryController.java
 * @Package com.glens.pwCloudOs.materielMg.comprehensiveQuery.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-29 下午4:47:15
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.comprehensiveQuery.web;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.materielMg.comprehensiveQuery.service.ComprehensiveQueryService;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */
@FormProcessor(clazz="com.glens.pwCloudOs.materielMg.comprehensiveQuery.web.ComprehensiveQueryForm")
@RequestMapping("pmsServices/materielMg/comprehensiveQuery")
public class ComprehensiveQueryController extends EAPJsonAbstractController {

	@RequestMapping(value="vehicleOilConsumptionStatistics", method=RequestMethod.GET)
	public Object vehicleOilConsumptionStatistics(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService service = (ComprehensiveQueryService) getService();
				List<Map<String, Object>> resultList = service.vehicleOilConsumptionStatistics(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取车辆油耗统计结果成功！");
					result.setList(resultList);
				}
				else {
					
					result.setStatusCode(ResponseConstants. NO_DATA);
					result.setResultMsg("获取车辆油耗统计结果失败！");
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="vehicleFuelCharge", method=RequestMethod.GET)
	public Object selectVehicleFuelCharge(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService service = (ComprehensiveQueryService) getService();
				List<Map<String, Object>> resultList = service.selectVehicleFuelCharge(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取车辆加油记录结果成功！");
					result.setList(resultList);
				}
				else {
					
					result.setStatusCode(ResponseConstants. NO_DATA);
					result.setResultMsg("获取车辆加油记录结果失败！");
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="assetRepair", method=RequestMethod.GET)
	public Object selectAssetRepair(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService service = (ComprehensiveQueryService) getService();
				List<Map<String, Object>> resultList = service.selectAssetRepair(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取资产维修记录成功！");
					result.setList(resultList);
				}
				else {
					
					result.setStatusCode(ResponseConstants. NO_DATA);
					result.setResultMsg("获取资产维修记录失败！");
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="assetStorage", method=RequestMethod.GET)
	public Object selectAssetStorage(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService service = (ComprehensiveQueryService) getService();
				List<Map<String, Object>> resultList = service.selectAssetStorage(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取资产入库记录成功！");
					result.setList(resultList);
				}
				else {
					
					result.setStatusCode(ResponseConstants. NO_DATA);
					result.setResultMsg("获取资产入库记录失败！");
				}
				
				return result;
			}
		});
	}
	
	//耗材入库
	@RequestMapping(value="materialStorage", method=RequestMethod.GET)
	public Object materialStorage(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService service = (ComprehensiveQueryService) getService();
				List<Map<String, Object>> resultList = service.materialStorage(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取耗材入库记录成功！");
					result.setList(resultList);
				}
				else {
					
					result.setStatusCode(ResponseConstants. NO_DATA);
					result.setResultMsg("获取耗材入库记录失败！");
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="assetExwarehouse", method=RequestMethod.GET)
	public Object selectAssetRent(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService service = (ComprehensiveQueryService) getService();
				List<Map<String, Object>> resultList = service.selectAssetRent(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取资产出库记录成功！");
					result.setList(resultList);
				}
				else {
					
					result.setStatusCode(ResponseConstants. NO_DATA);
					result.setResultMsg("获取资产出库记录失败！");
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="assetRentReturn", method=RequestMethod.GET)
	public Object selectAssetRentReturn(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService service = (ComprehensiveQueryService) getService();
				List<Map<String, Object>> resultList = service.selectAssetRentReturn(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取资产归还记录成功！");
					result.setList(resultList);
				}
				else {
					
					result.setStatusCode(ResponseConstants. NO_DATA);
					result.setResultMsg("获取资产归还记录失败！");
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="assetScrap", method=RequestMethod.GET)
	public Object selectAssetScrap(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService service = (ComprehensiveQueryService) getService();
				List<Map<String, Object>> resultList = service.selectAssetScrap(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取资产报废记录成功！");
					result.setList(resultList);
				}
				else {
					
					result.setStatusCode(ResponseConstants. NO_DATA);
					result.setResultMsg("获取资产报废记录失败！");
				}
				
				return result;
			}
		});
	}
	
	//资产租赁
	@RequestMapping(value="assetRentalCost", method=RequestMethod.GET)
	public Object selectAssetRentalCost(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PageBeanResult result = new PageBeanResult();
				ComprehensiveQueryService service = (ComprehensiveQueryService) getService();
				//List<Map<String, Object>> resultList = service.selectAssetRentalCost(actionForm.toMap());
				
				PageBean page=service.selectAssetRentalCost(actionForm.toMap(), actionForm.getCurrentPage(), actionForm.getPerpage());
				
				if (page != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取资产租赁费用记录成功！");
					result.setPageBean(page);
				}
				else {
					
					result.setStatusCode(ResponseConstants. NO_DATA);
					result.setResultMsg("获取资产租赁费用记录失败！");
				}
				
				return result;
			}
		});
	}
	
	
	
	
	@RequestMapping(value="exportAssetRentalCost", method=RequestMethod.GET)
	public Object exportAssetRentalCost(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				ComprehensiveQueryService service = (ComprehensiveQueryService) getService();
				
				
				List<Map<String, Object>> proList = service.exportAssetRentalCost(actionForm.toMap());
				
				return proList;
			}
			
			/**
			
			  * <p>Title: doWithFinish</p>
			
			  * <p>Description: </p>
			
			  * @param request
			  * @param response
			  * @param data
			  * @param viewType
			  * @param controller
			  * @return
			
			  * @see com.glens.eap.platform.framework.web.support.AbstractProcessRequestHandler#doWithFinish(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.String, com.glens.eap.platform.core.web.EAPController)
			
			  **/
			
			
			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub
				
				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("资产租赁费用表",
								"UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("资产租赁费用表");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("exportAssetRentalCost", response.getOutputStream(),
								Map.class, dataList);

					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
			}
		});
	}
	
	
	//宿舍租赁费用
	@RequestMapping(value="rentDormCost", method=RequestMethod.GET)
	public Object selectRentDorm(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService service = (ComprehensiveQueryService) getService();
				List<Map<String, Object>> resultList = service.selectRentDorm(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取宿舍租赁费用记录成功！");
					result.setList(resultList);
				}
				else {
					
					result.setStatusCode(ResponseConstants. NO_DATA);
					result.setResultMsg("获取宿舍租赁费用记录失败！");
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="assetDistribute", method=RequestMethod.GET)
	public Object queryAssetDistributing(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService queryService = (ComprehensiveQueryService) getService();
				List<Map<String, Object>> assetList = queryService.queryAssetDistributing(actionForm.toMap());
				if (assetList != null && assetList.size() > 0) {
					
					result.setResultMsg("获取设备分布统计成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(assetList);
				}
				else {
					
					result.setResultMsg("获取设备分布统计失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="distributeAssetList", method=RequestMethod.GET)
	public Object selectDistributeAsset(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService queryService = (ComprehensiveQueryService) getService();
				List<Map<String, Object>> assetList = queryService.selectDistributeAsset(actionForm.toMap());
				if (assetList != null && assetList.size() > 0) {
					
					result.setResultMsg("获取各城市分布的设备信息成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(assetList);
				}
				else {
					
					result.setResultMsg("获取各城市分布的设备信息失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="proAssetCostPage", method=RequestMethod.GET)
	public Object selectProAssetCostForPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PageBeanResult result = new PageBeanResult();
				ComprehensiveQueryService queryService = (ComprehensiveQueryService) getService();
				Map<String, Object> params = actionForm.toMap();
				params.put("assetMark", 2);
				UserToken token = getToken(request);
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("deptCode", token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("districtManager", token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("proManager", token.getEmployeeCode());
					}
				}
				PageBean page = queryService.selectProAssetCostForPage(params);
				if (page != null) {
					
					result.setResultMsg("获取项目资产租用汇总信息成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				}
				else {
					
					result.setResultMsg("获取项目资产租用汇总信息失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="exportProAssetCost", method=RequestMethod.GET)
	public Object exportProAssetCost(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				ComprehensiveQueryService queryService = (ComprehensiveQueryService) getService();
				Map<String, Object> params = actionForm.toMap();
				params.put("assetMark", 2);
				UserToken token = getToken(request);
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("deptCode", token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("districtManager", token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("proManager", token.getEmployeeCode());
					}
				}
				
				List<Map<String, Object>> proList = queryService.selectProAssetCostForList(params);
				
				return proList;
			}
			
			/**
			
			  * <p>Title: doWithFinish</p>
			
			  * <p>Description: </p>
			
			  * @param request
			  * @param response
			  * @param data
			  * @param viewType
			  * @param controller
			  * @return
			
			  * @see com.glens.eap.platform.framework.web.support.AbstractProcessRequestHandler#doWithFinish(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.String, com.glens.eap.platform.core.web.EAPController)
			
			  **/
			
			
			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub
				
				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("资产租用信息汇总表",
								"UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("资产租用信息汇总表");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("proAssetCost", response.getOutputStream(),
								Map.class, dataList);

					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
			}
		});
	}
	
	@RequestMapping(value="proAssetList", method=RequestMethod.GET)
	public Object selectProAssetList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService queryService = (ComprehensiveQueryService) getService();
				Map<String, Object> params = actionForm.toMap();
				params.put("assetMark", 2);
				List<Map<String, Object>> assetList = queryService.selectProAssetList(params);
				if (assetList != null && assetList.size() > 0) {
					
					result.setResultMsg("获取项目租用资产信息成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(assetList);
				}
				else {
					
					result.setResultMsg("获取项目租用资产信息失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="proVehicleCostPage", method=RequestMethod.GET)
	public Object selectProVehicleCostForPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PageBeanResult result = new PageBeanResult();
				ComprehensiveQueryService queryService = (ComprehensiveQueryService) getService();
				Map<String, Object> params = actionForm.toMap();
				params.put("assetMark", 1);
				UserToken token = getToken(request);
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("deptCode", token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("districtManager", token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("proManager", token.getEmployeeCode());
					}
				}
				PageBean page = queryService.selectProAssetCostForPage(params);
				if (page != null) {
					
					result.setResultMsg("获取项目车辆租用汇总信息成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				}
				else {
					
					result.setResultMsg("获取项目车辆租用汇总信息失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="proVehicleList", method=RequestMethod.GET)
	public Object selectProVehicleList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService queryService = (ComprehensiveQueryService) getService();
				Map<String, Object> params = actionForm.toMap();
				params.put("assetMark", 1);
				List<Map<String, Object>> assetList = queryService.selectProAssetList(params);
				if (assetList != null && assetList.size() > 0) {
					
					result.setResultMsg("获取项目租用车辆信息成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(assetList);
				}
				else {
					
					result.setResultMsg("获取项目租用车辆信息失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="exportProVehicleCost", method=RequestMethod.GET)
	public Object exportProVehicleCost(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				ComprehensiveQueryService queryService = (ComprehensiveQueryService) getService();
				Map<String, Object> params = actionForm.toMap();
				params.put("assetMark", 1);
				UserToken token = getToken(request);
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("deptCode", token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("districtManager", token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("proManager", token.getEmployeeCode());
					}
				}
				
				List<Map<String, Object>> proList = queryService.selectProAssetCostForList(params);
				
				return proList;
			}
			
			/**
			
			  * <p>Title: doWithFinish</p>
			
			  * <p>Description: </p>
			
			  * @param request
			  * @param response
			  * @param data
			  * @param viewType
			  * @param controller
			  * @return
			
			  * @see com.glens.eap.platform.framework.web.support.AbstractProcessRequestHandler#doWithFinish(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.String, com.glens.eap.platform.core.web.EAPController)
			
			  **/
			
			
			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub
				
				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("车辆租用信息汇总表",
								"UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("车辆租用信息汇总表");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("proAssetCost", response.getOutputStream(),
								Map.class, dataList);

					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
			}
		});
	}
	
	@RequestMapping(value="proDormCostPage", method=RequestMethod.GET)
	public Object selectProDormCostForPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PageBeanResult result = new PageBeanResult();
				ComprehensiveQueryService queryService = (ComprehensiveQueryService) getService();
				Map<String, Object> params = actionForm.toMap();
				UserToken token = getToken(request);
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("deptCode", token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("districtManager", token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("proManager", token.getEmployeeCode());
					}
				}
				PageBean page = queryService.selectProDormCostForPage(params);
				if (page != null) {
					
					result.setResultMsg("获取项目宿舍租用汇总信息成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				}
				else {
					
					result.setResultMsg("获取项目宿舍租用汇总信息失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="proDormList", method=RequestMethod.GET)
	public Object selectProDormList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				ComprehensiveQueryService queryService = (ComprehensiveQueryService) getService();
				Map<String, Object> params = actionForm.toMap();
				List<Map<String, Object>> dormList = queryService.selectProDormList(params);
				if (dormList != null && dormList.size() > 0) {
					
					result.setResultMsg("获取项目租用宿舍信息成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(dormList);
				}
				else {
					
					result.setResultMsg("获取项目租用宿舍信息失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="exportProDormCost", method=RequestMethod.GET)
	public Object exportProDormCost(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				ComprehensiveQueryService queryService = (ComprehensiveQueryService) getService();
				Map<String, Object> params = actionForm.toMap();
				UserToken token = getToken(request);
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("deptCode", token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("districtManager", token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						params.put("proManager", token.getEmployeeCode());
					}
				}
				
				List<Map<String, Object>> proList = queryService.selectProDormCostForList(params);
				
				return proList;
			}
			
			/**
			
			  * <p>Title: doWithFinish</p>
			
			  * <p>Description: </p>
			
			  * @param request
			  * @param response
			  * @param data
			  * @param viewType
			  * @param controller
			  * @return
			
			  * @see com.glens.eap.platform.framework.web.support.AbstractProcessRequestHandler#doWithFinish(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.String, com.glens.eap.platform.core.web.EAPController)
			
			  **/
			
			
			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {
				// TODO Auto-generated method stub
				
				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("宿舍租用信息汇总表",
								"UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("宿舍租用信息汇总表");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("proDormCost", response.getOutputStream(),
								Map.class, dataList);

					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
			}
		});
	}
	
}
