package com.glens.pwCloudOs.gm.vm.filling.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.gm.vm.filling.service.GmVmFillingRecordService;
import com.glens.pwCloudOs.gm.vm.filling.vo.GmVmFillingRecord;
import com.glens.pwCloudOs.gm.vm.filling.vo.GmVmSinopecBill;

@RequestMapping("/pmsServices/gm/vm/filling")
@FormProcessor(clazz = "com.glens.pwCloudOs.gm.vm.filling.web.GmVmFillingRecordForm")
public class GmVmFillingRecordController extends EAPJsonAbstractController {
	
	@RequestMapping(value="getSinopecBill", method=RequestMethod.GET)
	public Object getSinopecBill(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				GmVmFillingRecordService fillingService = (GmVmFillingRecordService)service;
				Long rowid = 0l;
				try {
					rowid=Long.parseLong(request.getParameter("rowid"));
				} catch (NumberFormatException e) {
				}
				GmVmSinopecBill vo = fillingService.findSinopecBillById(rowid);
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
	
	@Override
	@RequestMapping(value="add", method=RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				GmVmFillingRecordForm form = (GmVmFillingRecordForm)actionForm;
				
				String base64ImgData = form.getImgs();
				
				
				PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
				FinalHttp httpClient=new FinalHttp();
				AjaxParams params = new AjaxParams();
				params.put("base64Img", base64ImgData);
				String codeStr = (String)httpClient.postSync(config.getPrefix()+ config.getBatchUploadBase64ImgUrl(), params);
				try {
					JSONObject json = JSON.parseObject(codeStr);
					if("200".equals(json.get("statusCode").toString())){
						String generateKey = json.get("generateKey").toString();
						form.setPics(generateKey);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				boolean ok = service.insert(actionForm.toVo());
				KeyResult result = new KeyResult();
				if (ok) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				}
				else {
					
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败，重复提交");// 手机端有提示吗？
				}
				
				return result;
			}
			
		});
	}
	
	
	/**
	 * 导入中石化消费账单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "importSinopecBill", method = RequestMethod.POST)
	public Object importCheckList(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				
				GmVmFillingRecordForm form = (GmVmFillingRecordForm)actionForm;
				MultipartFile excelFile = form.getExcelFile();
				ExcelHelper excelHelper=new ExcelHelper();
				List<GmVmSinopecBill> data;
				try {
					String fileName = ((CommonsMultipartFile)excelFile).getFileItem().getName();
					data = excelHelper.getData("gmVmSinopecBill", excelFile.getInputStream(), fileName, GmVmSinopecBill.class);
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail("解析Excel失败");
				}
				GmVmFillingRecordService service = (GmVmFillingRecordService)getService();
				
				int res = service.batchInsertSinopecBill(data);
				if(res>0){
					return BeanResult.success("SUCCESS", res);
				}else{
					return BeanResult.fail("FAIL");
				}
			}
		});
	}
	
	
	@RequestMapping(value="listSinopecBill", method=RequestMethod.GET)
	public Object listSinopecBill(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				GmVmFillingRecordService fillingService = (GmVmFillingRecordService)service;
					
					PageBean page = fillingService.querySinopecBillForPage(actionForm.toMap(), 
							actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(page);
				}
				else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}
			
		});
	}
	
	@RequestMapping(value="checkWithEachOther", method=RequestMethod.GET)
	public Object checkWithEachOther(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				GmVmFillingRecordService fillingService = (GmVmFillingRecordService)service;
					
				int res = fillingService.checkWithEachOther();
				if(res>0){
					return BeanResult.success("已处理，影响"+res+"条记录", res);
				}else{
					return BeanResult.fail("已处理，未影响任何数据");
				}
			}
			
		});
	}

}
