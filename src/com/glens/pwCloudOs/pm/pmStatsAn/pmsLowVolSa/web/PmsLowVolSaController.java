/**
 * @Title: PmsLowVolSaController.java
 * @Package com.glens.pwCloudOs.pm.pmStatsAn.pmsLowVolSa.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-11-8 下午5:44:17
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.pmStatsAn.pmsLowVolSa.web;

import java.util.List;
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
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.pm.pmStatsAn.pmsLowVolSa.service.PmsLowVolSaService;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */
@FormProcessor(clazz="com.glens.pwCloudOs.pm.pmStatsAn.pmsLowVolSa.web.PmsLowVolSaForm")
@RequestMapping("pmsServices/pm/pmStatsAn/pmsLowVolSa")
public class PmsLowVolSaController extends EAPJsonAbstractController {

	@RequestMapping(value="pmMemberWorkloadStatsAn", method=RequestMethod.GET)
	public Object pmMemberWorkloadStatsAn(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				PmsLowVolSaService pmsLowVolSaService = (PmsLowVolSaService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> resultList = pmsLowVolSaService.pmMemberWorkloadStatsAn(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {
					
					result.setResultMsg("获取日完成工作量统计成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(resultList);
				}
				else {
					
					result.setResultMsg("未能获取到日完成工作量统计!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="pmProWorkloadStatsAn", method=RequestMethod.GET)
	public Object getPmProWorkloadStatsAn(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				PmsLowVolSaService pmsLowVolSaService = (PmsLowVolSaService) getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> resultList = pmsLowVolSaService.getPmProWorkloadStatsAn(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {
					
					result.setResultMsg("获取工作量统计成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(resultList);
				}
				else {
					
					result.setResultMsg("未能获取到工作量统计!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
}
