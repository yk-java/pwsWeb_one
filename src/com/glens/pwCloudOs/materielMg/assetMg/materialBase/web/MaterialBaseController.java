package com.glens.pwCloudOs.materielMg.assetMg.materialBase.web;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.glens.eap.platform.core.web.ProcessRequestHandler;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.materielMg.assetMg.materialBase.service.MaterialBaseService;
import com.glens.pwCloudOs.pm.pb.budgetEstimate.service.BudgetEstimateService;
import com.glens.pwCloudOs.pm.pb.budgetEstimate.web.BudgetEstimateForm;



@FormProcessor(clazz="com.glens.pwCloudOs.materielMg.assetMg.materialBase.web.MaterialBaseForm")
@RequestMapping("pmsServices/materielMg/assetMg/material")
public class MaterialBaseController extends EAPJsonAbstractController {
	
	
	
	@RequestMapping(value="ishave", method=RequestMethod.GET)
	public Object ishave(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService service=(MaterialBaseService)getService();
				List<Map<String, Object>> resultList = service.ishave(actionForm.toVo());
				ListResult result = new ListResult();
				if (resultList != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setList(resultList);
				}
				else {
					
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
				MaterialBaseForm form=(MaterialBaseForm)actionForm;
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				String materialBatchno = codeWorker.createCode("M");
				form.setMaterialBatchno(materialBatchno);
				
				int invoiceType=form.getInvoiceType();
				
				if(invoiceType==1){//普通发票
					form.setPrice2(form.getPrice1());
					form.setTotal2(form.getTotal2());
				}
				
				boolean ok = getService().insert(actionForm.toVo());
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
	
	
	
	/**
	 * app 端  汇总统计
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getMaterialSummary", method=RequestMethod.GET)
	public Object getMaterialSummary(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				MaterialBaseForm materialBaseForm = (MaterialBaseForm) actionForm;
				UserToken token = getToken(request);
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode()) 
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setDeptCode(token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setDistrictManager(token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setProManager(token.getEmployeeCode());
					}
				}
				Map<String, Object> materialmap = materialBaseService.getMaterialSummary(materialBaseForm.toMap());
				
				BeanResult result=new BeanResult();
				if (materialmap != null) {
					
					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(materialmap);
				}else{
					result.setResultMsg("获取失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	/**
	 * 耗材申请
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="addMaterial", method=RequestMethod.POST)
	public Object addMaterial(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseForm form=(MaterialBaseForm)actionForm;
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				String flowcode = codeWorker.createCode("F");
				
				
				form.setFlowCode(flowcode);
				
				SimpleDateFormat ds=new SimpleDateFormat("yyyy-MM-dd");
				
				String date=ds.format(new Date());
				form.setUseDate(date);
				
				MaterialBaseService ser=(MaterialBaseService)getService();
				
				boolean ok = ser.addMaterial(form.toMap());
				KeyResult result = new KeyResult();
				if (ok) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("申请成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				}
				else {
					
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("申请失败");
				}
				
				return result;
			}
			
		});
	}
	
	/**
	 *  得到领用列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getLinyongList", method=RequestMethod.GET)
	public Object getLinyongList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				MaterialBaseForm materialBaseForm = (MaterialBaseForm) actionForm;
				UserToken token = getToken(request);
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode()) 
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setDeptCode(token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setDistrictManager(token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setProManager(token.getEmployeeCode());
					}
				}
				
				MaterialBaseService service=(MaterialBaseService)getService();
				List<Map<String, Object>> resultList = service.getLinyongList(materialBaseForm.toMap());
				ListResult result = new ListResult();
				if (resultList != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setList(resultList);
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
	 *  得到借用列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getJieyongList", method=RequestMethod.GET)
	public Object getJieyongList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				MaterialBaseForm materialBaseForm = (MaterialBaseForm) actionForm;
				UserToken token = getToken(request);
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode()) 
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setDeptCode(token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setDistrictManager(token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setProManager(token.getEmployeeCode());
					}
				}
				
				MaterialBaseService service=(MaterialBaseService)getService();
				List<Map<String, Object>> resultList = service.getJieyongList(materialBaseForm.toMap());
				ListResult result = new ListResult();
				if (resultList != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setList(resultList);
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
	 * 得到项目列表
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="getProList", method=RequestMethod.GET)
	public Object getProList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				MaterialBaseForm materialBaseForm = (MaterialBaseForm) actionForm;
				UserToken token = getToken(request);
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode()) 
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setDeptCode(token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setDistrictManager(token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setProManager(token.getEmployeeCode());
					}
				}
				List<Map<String, Object>> materialmap = materialBaseService.getProList(materialBaseForm.toMap());
				
				BeanResult result=new BeanResult();
				if (materialmap != null) {
					
					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(materialmap);
				}else{
					result.setResultMsg("获取失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	/**
	 * 得到租用列表  rentstatus 1 2
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value="getRentList", method=RequestMethod.GET)
	public Object getRentList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				MaterialBaseForm materialBaseForm = (MaterialBaseForm) actionForm;
				UserToken token = getToken(request);
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode()) 
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setDeptCode(token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setDistrictManager(token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						materialBaseForm.setProManager(token.getEmployeeCode());
					}
				}
				List<Map<String, Object>> materialmap = materialBaseService.getRentList(materialBaseForm.toMap());
				
				BeanResult result=new BeanResult();
				if (materialmap != null) {
					
					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(materialmap);
				}else{
					result.setResultMsg("获取失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	
	/**
	 * 得到项目组人员
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getProMembers", method=RequestMethod.GET)
	public Object getProMembers(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				
				
				List<Map<String, Object>> materialmap = materialBaseService.getProMembers(actionForm.toMap());
				
				BeanResult result=new BeanResult();
				if (materialmap != null) {
					
					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(materialmap);
				}else{
					result.setResultMsg("获取失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}

	/**
	 * 详细信息
	 */
	@RequestMapping(value="getDetail", method=RequestMethod.GET)
	public Object getDetail(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				
				
				List<Map<String, Object>> materialmap = materialBaseService.getDetail(actionForm.toMap());
				
				BeanResult result=new BeanResult();
				if (materialmap != null) {
					
					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(materialmap);
				}else{
					result.setResultMsg("获取失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	/**
	 * 查询库存
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getTotalMaterialNum", method=RequestMethod.GET)
	public Object getTotalMaterialNum(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				
				
				List<Map<String, Object>> materialmap = materialBaseService.getTotalMaterialNum(actionForm.toMap());
				
				BeanResult result=new BeanResult();
				if (materialmap != null) {
					
					result.setResultMsg("获取成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(materialmap);
				}else{
					result.setResultMsg("获取失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	/**
	  * 耗材归还申请
	  * 
	  * @param @param request
	  * @param @param response
	  * @param @return
	  * @return 一个Map类型记录
	  * @throws
	  * @author:
	  * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	  */
	  
	@RequestMapping(value="returnMaterial", method=RequestMethod.POST)
	public Object returnMaterial(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				int affectedCount = materialBaseService.returnMaterial(actionForm.toMap());
				ResponseResult result = new ResponseResult();
				if (affectedCount > 0) {
					
					result.setResultMsg("提交耗材归还申请成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("提交耗材归还申请失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="applyMaterial", method=RequestMethod.GET)
	public Object selectApplyMaterialPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				PageBeanResult result = new PageBeanResult();
				PageBean page = materialBaseService.selectApplyMaterialPage(actionForm.toMap());
				if (page != null) {
					
					result.setResultMsg("获取耗材申请待办流程数据成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				}
				else {
					
					result.setResultMsg("获取耗材申请待办流程数据失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="processedMaterial", method=RequestMethod.GET)
	public Object selectProcessedMaterialPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				PageBeanResult result = new PageBeanResult();
				PageBean page = materialBaseService.selectProcessedMaterialPage(actionForm.toMap());
				if (page != null) {
					
					result.setResultMsg("获取耗材申请已办流程数据成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				}
				else {
					
					result.setResultMsg("获取耗材申请已办流程数据失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="materialFlow", method=RequestMethod.GET)
	public Object selectMaterialFlow(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				MaterialBaseForm materialForm = (MaterialBaseForm) actionForm;
				BeanResult result = new BeanResult();
				Map<String, Object> flowItem = materialBaseService.selectMaterialFlow(materialForm.getFlowCode());
				if (flowItem != null) {
					
					result.setResultMsg("获取流程详细数据成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(flowItem);
				}
				else {
					
					result.setResultMsg("获取流程详细数据失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="appliedMaterial", method=RequestMethod.GET)
	public Object selectAppliedMaterial(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				MaterialBaseForm materialForm = (MaterialBaseForm) actionForm;
				ListResult result = new ListResult();
				List<Map<String, Object>> materailList = materialBaseService.selectApplyedMaterial(materialForm.getFlowCode());
				if (materailList != null && materailList.size() > 0) {
					
					result.setResultMsg("获取审批的耗材数据成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(materailList);
				}
				else {
					
					result.setResultMsg("获取审批的耗材数据失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="auditBorrowMaterial", method=RequestMethod.POST)
	public Object auditBorrowMaterial(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				int affectedCount = materialBaseService.auditBorrowMaterial(actionForm.toMap());
				ResponseResult result = new ResponseResult();
				if (affectedCount > 0) {
					
					result.setResultMsg("批准耗材领用或借用申请成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("批准耗材领用或借用申请失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="auditReturnMaterial", method=RequestMethod.POST)
	public Object auditReturnMaterial(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				int affectedCount = materialBaseService.auditReturnMaterial(actionForm.toMap());
				ResponseResult result = new ResponseResult();
				if (affectedCount > 0) {
					
					result.setResultMsg("批准耗材归还申请成功！");
					result.setStatusCode(ResponseConstants.OK);
				}
				else {
					
					result.setResultMsg("批准耗材归还申请失败！");
					result.setStatusCode(ResponseConstants.UPDATE_DATA_ERROR);
				}
				
				return result;
			}
		});
	}
	
	
	@RequestMapping(value="exportTableList", method=RequestMethod.GET)
	public Object exportTableList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new ProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService ser=(MaterialBaseService)getService();
				List<Map<String, Object>> personMoveList =ser.exportTableList(actionForm.toMap());
				
				return personMoveList;
			}
			
			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String methodName,
					EAPController controller) {
				// TODO Auto-generated method stub
				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {
					
					@Override
					public void render(Map<String, ?> model, HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						// TODO Auto-generated method stub
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("耗材已办流程",
								"UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("耗材已办流程");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("materialuseSheet", response.getOutputStream(),
								Map.class, dataList);
					}
					
					@Override
					public String getContentType() {
						// TODO Auto-generated method stub
						
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};
				
				view.setData(data);
				modelAndView.setView(view);
				
				return modelAndView;
			}
			
			@Override
			public Object doWithException(HttpServletRequest request,
					HttpServletResponse response, Exception e) {
				// TODO Auto-generated method stub
				
				return null;
			}
		});
	}
	
	
	@RequestMapping(value="exportMaterialUseBase", method=RequestMethod.GET)
	public Object exportMaterialUseBase(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new ProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService ser=(MaterialBaseService)getService();
				List<Map<String, Object>> personMoveList =ser.exportMaterialUseBase(actionForm.toMap());
				
				return personMoveList;
			}
			
			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String methodName,
					EAPController controller) {
				// TODO Auto-generated method stub
				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {
					
					@Override
					public void render(Map<String, ?> model, HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						// TODO Auto-generated method stub
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("耗材列表",
								"UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("耗材列表");
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
						excelHelper.writeData("materialuseBase", response.getOutputStream(),
								Map.class, dataList);
					}
					
					@Override
					public String getContentType() {
						// TODO Auto-generated method stub
						
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};
				
				view.setData(data);
				modelAndView.setView(view);
				
				return modelAndView;
			}
			
			@Override
			public Object doWithException(HttpServletRequest request,
					HttpServletResponse response, Exception e) {
				// TODO Auto-generated method stub
				
				return null;
			}
		});
	}
	
	
	
	
	/**
	 * 库存管理 列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getStaticsMaterial", method=RequestMethod.GET)
	public Object getStaticsMaterial(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				PageBeanResult result = new PageBeanResult();
				PageBean page = materialBaseService.getStaticsMaterial(actionForm.toMap(),actionForm.getCurrentPage(),actionForm.getPerpage());
				if (page != null) {
					
					result.setResultMsg("获取数据成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setPageBean(page);
				}
				else {
					
					result.setResultMsg("获取数据失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	/**
	 * 获取库存管理 详细信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getDetailMaterial", method=RequestMethod.GET)
	public Object getDetailMaterial(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				MaterialBaseService materialBaseService = (MaterialBaseService) getService();
				ListResult result = new ListResult();
				List list = materialBaseService.getDetailMaterial(actionForm.toMap());
				if (list != null) {
					
					result.setResultMsg("获取数据成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(list);
				}
				else {
					
					result.setResultMsg("获取数据失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	
	
}
