package com.glens.pwCloudOs.task.quartz;

import java.util.List;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.glens.pwCloudOs.task.service.TaskService;
import com.glens.pwCloudOs.task.vo.TaskList;

public class LoadTask {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TaskService jobService;

	public void initTask() throws SchedulerException {
		// 可执行的任务列表
		List<TaskList> list = jobService.getTaskList();
		logger.info("初始化加载定时任务......");
		for (TaskList job : list) {
			jobService.addJob(job);
		}
	}

}
