package com.glens.pwCloudOs.pe.baseMgr.statistics.web;

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
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.pe.baseMgr.statistics.service.PeStatisticsService;

@FormProcessor(clazz="com.glens.pwCloudOs.pe.baseMgr.statistics.web.PeStatisticsForm")
@RequestMapping("pmsServices/pe/baseMgr/statistics")
public class PeStatisticsController extends EAPJsonAbstractController {
	
	
	@RequestMapping(method=RequestMethod.GET)
	public Object execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PeStatisticsService ser=(PeStatisticsService)getService();
				
				PeStatisticsForm form=(PeStatisticsForm)actionForm;
				
				List<Map<String, Object>> list=ser.statistic(form.toMap());
				
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
	

}
