package com.glens.pwCloudOs.pm.taskMgr.task.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.afinal.FinalHttp;
import com.glens.eap.platform.afinal.http.AjaxParams;
import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.config.PWCloudOsConfig;
import com.glens.pwCloudOs.pm.taskMgr.task.service.TaskService;

@FormProcessor(clazz="com.glens.pwCloudOs.pm.taskMgr.task.web.TaskForm")
@RequestMapping("pmsServices/pm/taskMgr/task")
public class TaskController extends EAPJsonAbstractController {
		@RequestMapping(value="importExcel", method=RequestMethod.POST)
		public Object importExcel(HttpServletRequest request, HttpServletResponse response) {
			return this.process(request, response, new JsonProcessRequestHandler() {
	
				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					TaskForm form=(TaskForm)actionForm;
					
					UserToken token = (UserToken)request.getSession().getAttribute(UserToken.KEY_TOKEN);
					
					TaskService service=(TaskService)getService();
					MultipartFile multiFile = form.getExcelFile();
					int rows=service.importExcel(multiFile, token);
					
					if (rows > 1) {
						return ResponseResult.success("ok");
					}else{
						return ResponseResult.success("fail");
					}
				}
			});
		}	
		@RequestMapping(value="userTaskList", method=RequestMethod.GET)
		public Object userTaskList(HttpServletRequest request, HttpServletResponse response) {
			
			return this.process(request, response, new JsonProcessRequestHandler() {
				
				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					TaskService taskService = (TaskService) getService();
					ListResult result = new ListResult();
					List list = taskService.getUserTask(actionForm.toMap());
					if (list != null && list.size() > 0) {
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("获取数据成功！");
						result.setList(list);
					}
					else {
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setResultMsg("获取数据失败！");
					}
					return result;
				}
			});
		}
		@RequestMapping(value="userTaskDetailList", method=RequestMethod.GET)
		public Object userTaskDetailList(HttpServletRequest request, HttpServletResponse response) {
			
			return this.process(request, response, new JsonProcessRequestHandler() {
				
				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					TaskService taskService = (TaskService) getService();
					ListResult result = new ListResult();
					List list = taskService.getUserTaskDetail(actionForm.toMap());
					if (list != null && list.size() > 0) {
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("获取数据成功！");
						result.setList(list);
					}
					else {
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setResultMsg("获取数据失败！");
					}
					return result;
				}
			});
		}
		
		@RequestMapping(value="findTaskCount", method=RequestMethod.GET)
		public Object findTaskCount(HttpServletRequest request, HttpServletResponse response) {
			
			return this.process(request, response, new JsonProcessRequestHandler() {
				
				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					
					BeanResult result = new BeanResult();
					TaskForm taskForm = (TaskForm) actionForm;
					TaskService deviceService = (TaskService) getService();
					Map<String, Integer> mctDevice = deviceService.findTaskCount(taskForm.toMap());
					if (mctDevice != null && mctDevice.size() > 0) {
						result.setResultMsg("获取任务成功！");
						result.setStatusCode(ResponseConstants.OK);
						result.setData(mctDevice);
					}
					else {
						
						result.setResultMsg("获取任务失败！");
						result.setStatusCode(ResponseConstants.NO_DATA);
					}
					
					return result;
				}
			});
		}
		@RequestMapping(value="findTaskCountInRange", method=RequestMethod.GET)
		public Object findTaskCountInRange(HttpServletRequest request, HttpServletResponse response) {
			
			return this.process(request, response, new JsonProcessRequestHandler() {
				
				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					
					BeanResult result = new BeanResult();
					TaskForm taskForm = (TaskForm) actionForm;
					TaskService deviceService = (TaskService) getService();
					Map<String, Integer> mctDevice = deviceService.findTaskCountInRange(taskForm.toMap());
					if (mctDevice != null && mctDevice.size() > 0) {
						result.setResultMsg("获取任务成功！");
						result.setStatusCode(ResponseConstants.OK);
						result.setData(mctDevice);
					}
					else {
						
						result.setResultMsg("获取任务失败！");
						result.setStatusCode(ResponseConstants.NO_DATA);
					}
					
					return result;
				}
			});
		}
		/**
		 * 保存任务处理情况
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value="saveTaskOpInfo", method=RequestMethod.POST)
		public Object saveTaskOpInfo(HttpServletRequest request, HttpServletResponse response) {
			
			return this.process(request, response, new JsonProcessRequestHandler() {
				
				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					Map<String, Object> map = new HashMap<String, Object>();
					TaskForm taskForm = (TaskForm) actionForm;
					
					BeanResult result = new BeanResult();
					
					map=taskForm.toMap();
					String base64ImgData = "";
					String pic1 = taskForm.getImg1();
					String pic2 = taskForm.getImg2();
					String pic3 = taskForm.getImg3();
					if(pic1!=null && pic1.length()>0){
						base64ImgData=pic1;
					}
					if(pic2!=null && pic2.length()>0){
						if(base64ImgData.length()>0){
							base64ImgData+="@";
						}
						base64ImgData+=pic2;
					}
					if(pic3!=null && pic3.length()>0){
						if(base64ImgData.length()>0){
							base64ImgData+="@";
						}
						base64ImgData+=pic3;
					}
					
					PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
					FinalHttp httpClient=new FinalHttp();
					AjaxParams params = new AjaxParams();
					params.put("base64Img", base64ImgData);
					String codeStr = (String)httpClient.postSync(config.getPrefix()+ config.getBatchUploadBase64ImgUrl(), params);
					try {
						JSONObject json = JSON.parseObject(codeStr);
						if("200".equals(json.get("statusCode").toString())){
							String generateKey = json.get("generateKey").toString();
							String [] codes = generateKey.split(",");
							for (int i = 0; i < codes.length; i++) {
								String code = codes[i];
								map.put("pic"+(i+1), code);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					TaskService deviceService = (TaskService) getService();
					int rows = deviceService.saveTaskOpInfo(map);
					if (rows > 0) {
						result.setResultMsg("提交成功");
						result.setStatusCode(ResponseConstants.OK);
						return result;
					}
					else {
						result.setResultMsg("提交失败");
						result.setStatusCode(ResponseConstants.NO_DATA);
						return result;
					}
				}
			});
		}
		
		@RequestMapping(value="batSaveTaskOpInfo", method=RequestMethod.POST)
		public Object batSaveTaskOpInfo(HttpServletRequest request, HttpServletResponse response) {
			
			return this.process(request, response, new JsonProcessRequestHandler() {
				
				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					Map<String, Object> map = new HashMap<String, Object>();
					TaskForm taskForm = (TaskForm) actionForm;
					map=taskForm.toMap();
					String base64ImgData = "";
					String pic1 = request.getParameter("pic1");
					String pic2 = request.getParameter("pic2");
					String pic3 = request.getParameter("pic3");
					if(pic1!=null && pic1.length()>0){
						base64ImgData=pic1;
					}
					if(pic2!=null && pic2.length()>0){
						if(base64ImgData.length()>0){
							base64ImgData+="@";
						}
						base64ImgData+=pic2;
					}
					if(pic3!=null && pic3.length()>0){
						if(base64ImgData.length()>0){
							base64ImgData+="@";
						}
						base64ImgData+=pic3;
					}
					
					PWCloudOsConfig config = (PWCloudOsConfig) EAPContext.getContext().getBean("pwcloudosConfig");
					FinalHttp httpClient=new FinalHttp();
					AjaxParams params = new AjaxParams();
					params.put("base64Img", base64ImgData);
					String codeStr = (String)httpClient.postSync(config.getPrefix()+ config.getBatchUploadBase64ImgUrl(), params);
					try {
						JSONObject json = JSON.parseObject(codeStr);
						if("200".equals(json.get("statusCode").toString())){
							String generateKey = json.get("generateKey").toString();
							String [] codes = generateKey.split(",");
							for (int i = 0; i < codes.length; i++) {
								String code = codes[i];
								map.put("pic"+(i+1), code);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					TaskService deviceService = (TaskService) getService();
					int rows = deviceService.batSaveTaskOpInfo(map);
					if (rows > 0) {
						return ResponseResult.success("提交成功");
					}
					else {
						return ResponseResult.fail("提交失败");
					}
				}
			});
		}
		
		@RequestMapping(value="getList", method=RequestMethod.GET)
		public Object getList(HttpServletRequest request, HttpServletResponse response) {
			
			return this.process(request, response, new JsonProcessRequestHandler() {
				
				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					// TODO Auto-generated method stub
					TaskService taskService = (TaskService) getService();
					PageBeanResult result = new PageBeanResult();
					PageBean page = taskService.getList(actionForm.toMap());
					if (page != null) {
						
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("获取任务数据成功！");
						result.setPageBean(page);
					}
					else {
						
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setResultMsg("获取任务数据失败！");
					}
					
					return result;
				}
			});
		}
		//查看
		@RequestMapping(value="getTask", method=RequestMethod.GET)
		public Object getTask(HttpServletRequest request, HttpServletResponse response) {
			
			return this.process(request, response, new JsonProcessRequestHandler() {
				
				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					// TODO Auto-generated method stub
					
					BeanResult result = new BeanResult();
					TaskForm taskForm = (TaskForm) actionForm;
					TaskService deviceService = (TaskService) getService();
					Map<String, Object> mctDevice = deviceService.getTask(taskForm.getTaskCode());
					if (mctDevice != null && mctDevice.size() > 0) {
						
						result.setResultMsg("获取任务成功！");
						result.setStatusCode(ResponseConstants.OK);
						result.setData(mctDevice);
					}
					else {
						
						result.setResultMsg("获取任务失败！");
						result.setStatusCode(ResponseConstants.NO_DATA);
					}
					
					return result;
				}
			});
		}
		
		
		@RequestMapping(value="update", method=RequestMethod.POST)
		public Object update(HttpServletRequest request,
				HttpServletResponse response) {
			// TODO Auto-generated method stub
			return this.process(request, response, new JsonProcessRequestHandler() {

				@Override
				public Object doWithRequest(HttpServletRequest request,
						HttpServletResponse response, ControllerForm actionForm) {
					// TODO Auto-generated method stub
					TaskService deviceService = (TaskService) getService();
					
					TaskForm form=(TaskForm)actionForm;
					String taskCode=form.getTaskCode();
					String employeeCode=form.getEmployeeCode();
					String employeeName=form.getEmployeeName();
					
					String[] taskCodes=taskCode.split(",");
					
					int iCount=0;
					for(int i=0;i<taskCodes.length;i++){
						TaskForm f=new TaskForm();
						f.setTaskCode(taskCodes[i]);
						f.setEmployeeCode(employeeCode);
						f.setEmployeeName(employeeName);
						iCount=deviceService.updateTask(f.toMap());
					}
					
					
					
					ResponseResult result = new ResponseResult();
					
					if (iCount > 0) {
						
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("修改成功");
					}
					else {
						
						result.setStatusCode(ResponseConstants.SERVER_ERROR);
						result.setResultMsg("修改失败");
					}
					
					return result;
				}
				
			});
		}
		
		
		

}
