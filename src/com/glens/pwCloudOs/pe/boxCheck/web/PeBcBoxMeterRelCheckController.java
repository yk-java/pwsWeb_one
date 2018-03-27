package com.glens.pwCloudOs.pe.boxCheck.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.pe.boxCheck.service.PeBcBoxMeterRelCheckService;
import com.glens.pwCloudOs.pe.boxCheck.service.PeBcCheckBatchService;
import com.glens.pwCloudOs.pe.boxCheck.vo.PeBcBoxMeterRelCheckVo;
import com.glens.pwCloudOs.pe.boxCheck.vo.PeBcCheckBatchVo;
@FormProcessor(clazz = "com.glens.pwCloudOs.pe.boxCheck.web.PeBcBoxMeterRelCheckForm")
@RequestMapping("/pmsServices/pe/boxCheck")
public class PeBcBoxMeterRelCheckController extends EAPJsonAbstractController {
	private PeBcCheckBatchService peBcCheckBatchService;

	public PeBcCheckBatchService getPeBcCheckBatchService() {
		return peBcCheckBatchService;
	}

	public void setPeBcCheckBatchService(PeBcCheckBatchService peBcCheckBatchService) {
		this.peBcCheckBatchService = peBcCheckBatchService;
	}
	
	@RequestMapping(value = "checkBatchList", method = RequestMethod.GET)
	public Object checkBatchList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String searchName = request.getParameter("searchName");
				int currentPage = 1;
				try {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				} catch (NumberFormatException e1) {
				}
				int perpage = 100;
				try {
					perpage = Integer.parseInt(request.getParameter("perpage"));
				} catch (NumberFormatException e) {
				}
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("searchName", searchName);
				params.put("currentPage", currentPage);
				params.put("perpage", perpage);
				PageBean pageBean = peBcCheckBatchService.queryForPage(params, currentPage, perpage);
				if(pageBean != null){
					return PageBeanResult.success("SUCCESS", pageBean);
				}else{
					return PageBeanResult.fail("FAIL");
				}
			}
		});
	}
	
	@RequestMapping(value = "checkBatchAdd", method = RequestMethod.POST)
	public Object checkBatchAdd(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String batchName = request.getParameter("batchName");
				PeBcCheckBatchVo vo = new PeBcCheckBatchVo();
				vo.setBatchName(batchName);
				boolean res = peBcCheckBatchService.insert(vo);
				if(res){
					return BeanResult.success("SUCCESS", res);
				}else{
					return BeanResult.fail("FAIL");
				}
			}
		});
	}
	
	@RequestMapping(value = "checkBatchUpdate", method = RequestMethod.POST)
	public Object checkBatchUpdate(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String batchName = request.getParameter("batchName");
				String rowid = request.getParameter("rowid");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("batchName", batchName);
				params.put("rowid", rowid);
				int res = peBcCheckBatchService.update(params);
				if(res != 0){
					return BeanResult.success("SUCCESS", res);
				}else{
					return BeanResult.fail("FAIL");
				}
			}
		});
	}
	
	@RequestMapping(value = "checkBatchDelete", method = RequestMethod.POST)
	public Object checkBatchDelete(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String rowid = request.getParameter("rowid");
				int res = peBcCheckBatchService.delete(rowid);
				if(res != 0){
					return BeanResult.success("SUCCESS", res);
				}else{
					return BeanResult.fail("FAIL");
				}
			}
		});
	}
	
	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String batchCode = request.getParameter("batchCode");
				int currentPage = 1;
				try {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				} catch (NumberFormatException e1) {
				}
				int perpage = 10;
				try {
					perpage = Integer.parseInt(request.getParameter("perpage"));
				} catch (NumberFormatException e) {
				}
				PeBcBoxMeterRelCheckService service = (PeBcBoxMeterRelCheckService)getService();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("batchCode", batchCode);
				PageBean pageBean = service.queryForPage(map, currentPage, perpage);
				if(pageBean != null){
					return PageBeanResult.success("SUCCESS", pageBean);
				}else{
					return PageBeanResult.fail("FAIL");
				}
			}
		});
	}
	
	@RequestMapping(value = "exportCheckList", method = RequestMethod.GET)
	public Object exportCheckList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String batchCode = request.getParameter("batchCode");
				PeBcBoxMeterRelCheckService service = (PeBcBoxMeterRelCheckService)getService();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("batchCode", batchCode);
				List res = service.queryForList(map);
				return res;
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
						String fileName = URLEncoder.encode("箱表核查结果", "UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper=new ExcelHelper();
						excelHelper.writeData("boxMeterRelCheckResultList", response.getOutputStream(), PeBcBoxMeterRelCheckVo.class, (List)data);
						// ============ END ==============
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
	
	@RequestMapping(value = "importCheckList", method = RequestMethod.POST)
	public Object importCheckList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				//String batchCode = request.getParameter("batchCode");
				PeBcBoxMeterRelCheckForm form = (PeBcBoxMeterRelCheckForm)actionForm;
				MultipartFile excelFile = form.getExcelFile();
				ExcelHelper excelHelper=new ExcelHelper();
				List<PeBcBoxMeterRelCheckVo> data;
				try {
					String fileName = ((CommonsMultipartFile)excelFile).getFileItem().getName();
					data = excelHelper.getData("boxMeterRelCheckList", excelFile.getInputStream(), fileName, PeBcBoxMeterRelCheckVo.class);
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail("解析Excel失败");
				}
				PeBcBoxMeterRelCheckService service = (PeBcBoxMeterRelCheckService)getService();
				List<PeBcBoxMeterRelCheckVo> checkData=new ArrayList<PeBcBoxMeterRelCheckVo>();
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				for (Iterator iterator = data.iterator(); iterator.hasNext();) {
					PeBcBoxMeterRelCheckVo vo = (PeBcBoxMeterRelCheckVo) iterator.next();
					vo.setCompanyCode(form.getCompanyCode());
					vo.setProNo(form.getProNo());
					vo.setProName(form.getProName());
					if(form.getMctCode()==null || "".equals(form.getMctCode())){
						vo.setMctCode("mct20170220115599999999");
					}else{
						vo.setMctCode(form.getMctCode());
					}
					vo.setCheckResult(0);
					vo.setCheckResultName("待核查");
					String deviceObjCode = codeWorker.createCode("BMR");
					vo.setDeviceObjCode(deviceObjCode);
					vo.setDeviceObjName("");
					//vo.setBatchCode(batchCode);
					checkData.add(vo);
				}
				boolean res = service.batchInsert(checkData);
				if(res){
					return BeanResult.success("SUCCESS", res);
				}else{
					return BeanResult.fail("FAIL");
				}
			}
		});
	}
	
	
	@RequestMapping(value = "listByBoxCode", method = RequestMethod.GET)
	public Object listByBoxCode(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String boxCode = request.getParameter("boxCode");
				PeBcBoxMeterRelCheckService service = (PeBcBoxMeterRelCheckService)getService();
				List<PeBcBoxMeterRelCheckVo> res = service.selectByBoxCode(boxCode);
				ListResult result=new ListResult();
				
				if(res != null){
					//return ListResult.success("SUCCESS", res);
					result.setList(res);
					result.setResultMsg("查询成功");
					result.setStatusCode("200");
					return result;
				}else{
					//return ListResult.fail("FAIL");
					result.setList(null);
					result.setResultMsg("查询失败");
					result.setStatusCode("400");
					return result;
				}
			}
		});
	}
	
	@RequestMapping(value = "checkResultSubmit", method = RequestMethod.POST)
	public Object checkResultSubmit(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
//				String checkUser = request.getParameter("checkUser");
				//String checkUserName=request.getParameter("checkUserName");
				String checkResult = request.getParameter("checkResult");// [{boxCode:, meterCode:, transSvsAreaCode:, checkResult:, batchCode:},...]
				//String boxCode=request.getParameter("boxCode");
				List<PeBcBoxMeterRelCheckVo> checkData = JSON.parseArray(checkResult, PeBcBoxMeterRelCheckVo.class);
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				for (Iterator iterator = checkData.iterator(); iterator
						.hasNext();) {
					PeBcBoxMeterRelCheckVo vo = (PeBcBoxMeterRelCheckVo) iterator
							.next();
					if(1==vo.getCheckResult()){
						vo.setCheckResultName("已核查（正常）");
					}else if(2==vo.getCheckResult()){
						vo.setCheckResultName("已核查（不存在）");
					}else if(3==vo.getCheckResult()){
						vo.setCheckResultName("新增");
					}
					
					if(1 == vo.getIsNew()){
						String deviceObjCode = codeWorker.createCode("BMR");
						vo.setDeviceObjCode(deviceObjCode);
						vo.setDeviceObjName("");
					}
				}
				PeBcBoxMeterRelCheckService service = (PeBcBoxMeterRelCheckService)getService();
				boolean res = service.batchCheck(checkData);
				if(res){
					return BeanResult.success("SUCCESS", res);
				}else{
					return BeanResult.fail("FAIL");
				}
			}
		});
	}
	
	
}
