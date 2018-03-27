package com.glens.pwCloudOs.pm.projDoc.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.pwCloudOs.pm.checkScore.service.CheckScoreService;
import com.glens.pwCloudOs.pm.projDoc.service.DocumentCheckService;


@FormProcessor(clazz="com.glens.pwCloudOs.pm.projDoc.web.DocumentCheckForm")
@RequestMapping("pmsServices/pm/projDoc/documentCheck")
public class DocumentCheckController extends EAPJsonAbstractController {
	
	private CheckScoreService checkScoreService;
	
	public CheckScoreService getCheckScoreService() {
		return checkScoreService;
	}

	public void setCheckScoreService(CheckScoreService checkScoreService) {
		this.checkScoreService = checkScoreService;
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
				// TODO Auto-generated method stub
				DocumentCheckService ser=(DocumentCheckService)getService();
				boolean ok =ser.insert(actionForm.toVo());
				
				DocumentCheckForm form=(DocumentCheckForm)actionForm;
				
				
				//项目评定表添加一条记录
				String docTypelibName=form.getDocTypelibName();
				System.out.println(docTypelibName);		
				
				if(docTypelibName.equals("项目晨会") || docTypelibName.equals("项目日报")|| docTypelibName.equals("项目日志")
						|| docTypelibName.equals("周计划评定")|| docTypelibName.equals("周计划反馈评定")|| docTypelibName.equals("周报")
						|| docTypelibName.equals("月计划评定")|| docTypelibName.equals("月计划反馈评定")|| docTypelibName.equals("月报")){
					checkScoreService.insertCheckScore(form.getCompanyCode(), docTypelibName, form.getCheckVal(), form.getProNo(),form.getEmployeeCode(),form.getDocNo(),form.getCheckDesc());
				}
				
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
	
	@RequestMapping(value="queryByDocNo", method=RequestMethod.GET)
	public Object queryByDocNo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				Map parameter = new HashMap();
				parameter.put("docNo", request.getParameter("docNo"));
				DocumentCheckService service = (DocumentCheckService)getService();
				Map  data= service.queryByDocNo(parameter);
				BeanResult result = new BeanResult();
				if (data != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(data);
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
