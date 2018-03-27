package com.glens.pwCloudOs.pm.taskMgr.taskClass.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.materielMg.comprehensiveQuery.service.ComprehensiveQueryService;
import com.glens.pwCloudOs.pm.taskMgr.taskClass.service.TaskClassService;


@FormProcessor(clazz="com.glens.pwCloudOs.pm.taskMgr.taskClass.web.TaskClassForm")
@RequestMapping("pmsServices/taskMgr/taskClass")
public class TaskClassController extends EAPJsonAbstractController {
	
	@RequestMapping(value="getKpiList", method=RequestMethod.GET)
	public Object getKpiList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ListResult result = new ListResult();
				TaskClassService service = (TaskClassService) getService();
				List<Map<String, Object>> resultList = service.getKpiList(actionForm.toMap());
				if (resultList != null && resultList.size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("指标！");
					result.setList(resultList);
				}
				else {
					
					result.setStatusCode(ResponseConstants. NO_DATA);
					result.setResultMsg("指标失败！");
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
				
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				String taskcode = codeWorker.createCode("task");
				
				TaskClassForm form=(TaskClassForm)actionForm;
				form.setTaskClassCode(taskcode);
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
	
	
}
