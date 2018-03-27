package com.glens.pwCloudOs.task.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.glens.pwCloudOs.task.vo.TaskList;

public class QuartzJobFactory implements Job {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		TaskList scheduleJob = (TaskList) context.getMergedJobDataMap().get(
				"scheduleJob");
		logger.info("运行任务名称 = [" + scheduleJob.getJobName() + "]");
		TaskUtils.invokMethod(scheduleJob);
	}

}
