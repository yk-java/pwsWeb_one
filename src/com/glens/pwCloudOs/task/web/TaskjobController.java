package com.glens.pwCloudOs.task.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.task.service.TaskService;
import com.glens.pwCloudOs.task.vo.TaskList;

@FormProcessor(clazz = "com.glens.pwCloudOs.task.web.TaskListForm")
@RequestMapping("/pmsServices/jobTask")
public class TaskjobController extends EAPJsonAbstractController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@RequestMapping(value = "refresh", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				TaskService taskService = (TaskService) getService();
				logger.info("刷新定时任务......");
				BeanResult result = new BeanResult();
				try {
					// 可执行的任务列表
					List<TaskList> list = taskService.getTaskList();
					logger.info("任务列表：" + list);
					// 删除所有job
					List<TaskList> currentAllTaskList = taskService
							.getAllJobs();
					for (TaskList t : currentAllTaskList) {
						taskService.deleteJob(t);
					}
					logger.info("删除任务完成......");

					for (TaskList job : list) {
						taskService.addJob(job);
					}
					logger.info("添加任务完成......");

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("刷新成功");

				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("刷新失败");
					result.setData(e.getMessage());
				}

				return result;
			}

		});
	}
}
