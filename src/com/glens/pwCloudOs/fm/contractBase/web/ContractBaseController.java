package com.glens.pwCloudOs.fm.contractBase.web;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.utils.excel.DataStructReader;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.fm.contractBase.service.ContractBaseService;
import com.glens.pwCloudOs.fm.contractBase.vo.ContractPageBeanResult;
import com.glens.pwCloudOs.fm.contractBase.vo.ProjectSettleUpVo;

@FormProcessor(clazz = "com.glens.pwCloudOs.fm.contractBase.web.ContractBaseForm")
@RequestMapping("pmsServices/fm/contractBase/contractBase")
public class ContractBaseController extends EAPJsonAbstractController {

	@RequestMapping(value = "Tongji", method = RequestMethod.GET)
	public Object Tongji(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				
				ContractBaseService ser = (ContractBaseService) getService();

				PageBean page = ser.getList(actionForm.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new ContractPageBeanResult();
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

	@RequestMapping(value = "export", method = RequestMethod.GET)
	public Object export(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				ContractBaseService ser = (ContractBaseService) getService();
				Map map = actionForm.toMap();
				Map<String, Object> resultWrapper = ser.queryProContract(map);
				return resultWrapper;
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
						String fileName = URLEncoder.encode("项目结算统计", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("项目结算统计");
						Map<String, Object> resultWrapper = (Map<String, Object>) data;
						if (resultWrapper.get("proList") != null) {
							
							List<Map<String, Object>> proList = (List<Map<String, Object>>) resultWrapper.get("proList");
							if (proList != null && proList.size() > 0) {
								
								//获取导出配置文件结构
								Map<String, Map<String, Object>> dataStruct = DataStructReader
										.getDataStruct();
								Map<String, Object> beanStruct = dataStruct.get(ProjectSettleUpVo.class.getName());
								List<Map<String, Object>> yearList = (List<Map<String, Object>>) resultWrapper.get("yearList");
								List<Map<String, Object>> properties = (List<Map<String, Object>>) beanStruct.get("properties");

								for (int i = properties.size() - 1;i >= 0;i--) {
									
									Map<String, Object> propertyItem = properties.get(i);
									if (propertyItem.get("cell_flag") != null 
											&& "1".equals(propertyItem.get("cell_flag"))) {
										
										properties.remove(i);
									} 
								}
								int startIndex = 12;
								if (yearList != null && yearList.size() > 0) {
									
									for (Map<String, Object> yearItem : yearList) {
										
										Map<String, Object> workloadProperyItem = new HashMap<String, Object>();
										workloadProperyItem.put("name", yearItem.get("years") + "_workload");
										workloadProperyItem.put("type", "java.lang.String");
										workloadProperyItem.put("cell-type", "CELL_TYPE_STRING");
										workloadProperyItem.put("comment", yearItem.get("years") + "工作量");
										workloadProperyItem.put("cell_flag", "1");
										properties.add(startIndex + 1, workloadProperyItem);
										
										Map<String, Object> amountProperyItem = new HashMap<String, Object>();
										amountProperyItem.put("name", yearItem.get("years") + "_amount");
										amountProperyItem.put("type", "java.lang.String");
										amountProperyItem.put("cell-type", "CELL_TYPE_STRING");
										amountProperyItem.put("comment", yearItem.get("years") + "金额");
										amountProperyItem.put("cell_flag", "1");
										properties.add(startIndex + 2, amountProperyItem);
										
										startIndex = startIndex + 2;
									}
								}
								
								for (int i = 0;i < properties.size();i++) {
									
									Map<String, Object> propertyItem = properties.get(i);
									propertyItem.put("col-index", i);
								}
								
								excelHelper.writeData(beanStruct, response.getOutputStream(),
										Map.class, proList);
							}
						}
						
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

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ContractBaseService ser = (ContractBaseService) getService();
				int iCount = ser.updateLinks(actionForm.toVo(),actionForm.toMap());

				ResponseResult result = new ResponseResult();

				if (iCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "queryList", method = RequestMethod.GET)
	public Object queryList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				// Map map = actionForm.toMap();
				// map.put("companyCode", token.getCompanyCode());
				ContractBaseService service = (ContractBaseService) getService();
				String proNo = request.getParameter("proNo");
				List list = service.queryList(proNo);
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
	
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ContractBaseService service = (ContractBaseService) getService();
				boolean ok = service.insert(actionForm.toVo(),actionForm.toMap());
				KeyResult result = new KeyResult();
				if (ok) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				}
				else {
					
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败");
				}
				
				return result;
			}
			
		}); 
	}
	
	@RequestMapping(value="get", method=RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ContractBaseService service = (ContractBaseService) getService();
				Map vo = service.getDetail(actionForm.toVo(),actionForm.toMap());
				BeanResult result = new BeanResult();
				if (vo != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(vo);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				
				return result;
			}
			
		});
	}
	/**
	 * 合同项目关系图
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="echatsData", method=RequestMethod.GET)
	public Object echatsData(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ContractBaseService service = (ContractBaseService) getService();
				Map vo = service.echatsData(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (vo != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(vo);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				
				return result;
			}
			
		});
	}
	
	/*
	 *统计 1
	 */
	@RequestMapping(value="getStatistics1", method=RequestMethod.GET)
	public Object getStatistics1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ContractBaseService service = (ContractBaseService) getService();
				Map vo = service.getStatistics1(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (vo != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(vo);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				
				return result;
			}
			
		});
	}
	/**
	 * 统计 2
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getStatistics2", method=RequestMethod.GET)
	public Object getStatistics2(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ContractBaseService service = (ContractBaseService) getService();
				Map vo = service.getStatistics2(actionForm.toMap());
				BeanResult result = new BeanResult();
				if (vo != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(vo);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				
				return result;
			}
			
		});
	}
	

}
