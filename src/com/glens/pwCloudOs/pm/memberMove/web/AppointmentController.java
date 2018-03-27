package com.glens.pwCloudOs.pm.memberMove.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.pm.memberMove.service.AppointmentService;


@FormProcessor(clazz="com.glens.pwCloudOs.pm.memberMove.web.AppointmentForm")
@RequestMapping("pmsServices/appointment")
public class AppointmentController extends EAPJsonAbstractController {

	
	@Override
	@RequestMapping(value="list", method=RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PageBean page = 
					getService().queryForPage(actionForm.toMap(), 
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
				
				AppointmentForm form=(AppointmentForm)actionForm;
				
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				String code = codeWorker.createCode("A");
				form.setAppointmentCode(code);
				form.setAppoinmentType(1);
				String appointdate=form.getAppoinmentDate();
				SimpleDateFormat ds=new SimpleDateFormat("yyyy-MM-dd");
				
				AppointmentService ser=(AppointmentService)getService();
				try {
					Date d=ds.parse(appointdate);
					
					Calendar c = Calendar.getInstance();
					c.setTime(d);
					c.get(Calendar.YEAR);
					
					form.setAppoinmentYear(c.get(Calendar.YEAR));
					form.setAppoinmentMonth(d.getMonth()+1);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				boolean ok = 
						ser.insert(form.toVo());
				
				
				Map map=new HashMap();
				map.put("proNo", form.getProNo());
				map.put("employeeName", form.getEmployeeName());
				map.put("employeeCode", form.getEmployeeCode());
				ser.updateManager(map);
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
	
	
}
