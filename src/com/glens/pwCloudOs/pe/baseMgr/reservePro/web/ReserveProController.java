package com.glens.pwCloudOs.pe.baseMgr.reservePro.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.pe.baseMgr.reservePro.service.ReserveProService;
import com.glens.pwCloudOs.pe.baseMgr.reservePro.vo.ReserveProVo;


@FormProcessor(clazz="com.glens.pwCloudOs.pe.baseMgr.reservePro.web.ReserveProForm")
@RequestMapping("pmsServices/pe/baseMgr/reservePro")
public class ReserveProController extends EAPJsonAbstractController {

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
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				String reserveProNo = codeWorker.createCode("r");
				
				
				ReserveProForm form=(ReserveProForm)actionForm;
				form.setReserveProNo(reserveProNo);
				
				boolean ok = 
					getService().insert(form.toVo());
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
	
	@RequestMapping(value="mctlist", method=RequestMethod.GET)
	public Object mctlist(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ReserveProService ser=(ReserveProService)getService();
				
				ReserveProForm form=(ReserveProForm)actionForm;
				
				List<Map<String, Object>> viewlist=ser.getMctView(form.getCompanyCode());
				
				
				PageBeanResult result = new PageBeanResult();
				if (viewlist != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(viewlist);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				
				return result;
			}
			
		});
	}
	
	@RequestMapping(value="reserveStatistics", method=RequestMethod.GET)
	public Object getReserveStatistics(HttpServletRequest request, HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ReserveProService ser = (ReserveProService)getService();
				ListResult result = new ListResult();
				List<Map<String, Object>> proDeviceStatisticsList = ser.getReserveStatistics((ReserveProVo) actionForm.toVo());
				if (proDeviceStatisticsList != null && proDeviceStatisticsList.size() > 0) {
					
					result.setResultMsg("获取储备项目统计信息成功");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(proDeviceStatisticsList);
				}
				else {
					
					result.setResultMsg("获取储备项目统计信息失败");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}
				
				return result;
			}
		});
	}
	
}
