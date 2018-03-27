package com.glens.pwCloudOs.task.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.task.dao.TaskDao;
import com.glens.pwCloudOs.task.quartz.QuartzJobFactory;
import com.glens.pwCloudOs.task.quartz.QuartzJobFactoryDisallowConcurrentExecution;
import com.glens.pwCloudOs.task.vo.TaskList;

public class TaskService extends EAPAbstractService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	/**
	 * 查询任务列表
	 * 
	 * @return
	 */
	public List<TaskList> getTaskList() {
		List<TaskList> jobs = new ArrayList<TaskList>();
		TaskDao taskDao = (TaskDao) getDao();
		List<TaskList> taskList = taskDao.findAll();
		TaskList job = null;
		for (TaskList bo : taskList) {
			job = getTask(bo);
			if (job != null) {
				jobs.add(job);
			}
		}
		return jobs;
	}

	/**
	 * 查询任务列表
	 * 
	 * @return
	 */
	public TaskList getTask(TaskList bo) {
		TaskList job = null;
		if (bo != null) {
			job = new TaskList();
			job.setJobId(String.valueOf(bo.getJobId()));
			job.setJobName(bo.getJobName());
			job.setJobGroup(bo.getJobGroup());
			job.setJobStatus(bo.getJobStatus());// 初始状态
			job.setCronExpression(bo.getCronExpression());
			job.setSpringId(bo.getSpringId());
			job.setIsConcurrent(bo.getIsConcurrent());
			job.setJobClass(bo.getJobClass());
			job.setMethodName(bo.getMethodName());
			job.setDescription(bo.getDescription());
		}
		return job;
	}

	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public boolean addJob(TaskList job) throws SchedulerException {
		if (job == null || !TaskList.STATUS_RUNNING.equals(job.getJobStatus())) {
			return false;
		}
		if (!CronExpression.isValidExpression(job.getCronExpression())) {
			logger.error("时间表达式错误(" + job.getJobName() + ","
					+ job.getJobGroup() + ")," + job.getCronExpression());
			return false;
		} else {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			// 任务名称和任务组设置规则： // 名称：task_1 .. // 组 ：group_1 ..
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),
					job.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler
					.getTrigger(triggerKey);
			// 不存在，创建一个
			if (null == trigger) {
				// 是否允许并发执行
				Class<? extends Job> clazz = TaskList.CONCURRENT_IS.equals(job
						.getIsConcurrent()) ? QuartzJobFactory.class
						: QuartzJobFactoryDisallowConcurrentExecution.class;
				JobDetail jobDetail = JobBuilder.newJob(clazz)
						.withIdentity(job.getJobName(), job.getJobGroup())
						.build();
				jobDetail.getJobDataMap().put("scheduleJob", job);
				// 表达式调度构建器
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						.cronSchedule(job.getCronExpression());
				// 按新的表达式构建一个新的trigger
				trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
						.withSchedule(scheduleBuilder).build();

				scheduler.scheduleJob(jobDetail, trigger);
			} else { // trigger已存在，则更新相应的定时设置
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						.cronSchedule(job.getCronExpression());
				// 按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
						.withSchedule(scheduleBuilder).build();
				// 按新的trigger重新设置job执行
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		}
		return true;
	}

	/**
	 * 立即执行一个任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void testJob(TaskList scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),
				scheduleJob.getJobGroup());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新任务时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateCronExpression(TaskList scheduleJob)
			throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(),
				scheduleJob.getJobGroup());
		// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
				.cronSchedule(scheduleJob.getCronExpression());
		// 按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
				.withSchedule(scheduleBuilder).build();
		// 按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);
	}

	/**
	 * 删除任务
	 */
	public boolean deleteJob(TaskList scheduleJob) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),
				scheduleJob.getJobGroup());
		try {
			scheduler.deleteJob(jobKey);
			return true;
		} catch (SchedulerException e) {
		}
		return false;
	}

	/**
	 * 暂停任务
	 * 
	 * @param scheduleJob
	 * @return
	 */
	public boolean pauseJob(TaskList scheduleJob) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),
				scheduleJob.getJobGroup());
		try {
			scheduler.pauseJob(jobKey);
			return true;
		} catch (SchedulerException e) {
		}
		return false;
	}

	/**
	 * 所有正在运行的job
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<TaskList> getRunningJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		List<JobExecutionContext> executingJobs = scheduler
				.getCurrentlyExecutingJobs();
		List<TaskList> jobList = new ArrayList<TaskList>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			TaskList job = new TaskList();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobName(jobKey.getName());
			job.setJobGroup(jobKey.getGroup());
			job.setDescription("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler
					.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;
	}

	/**
	 * 获取所有任务
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<TaskList> getAllJobs() throws Exception {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<TaskList> jobList = new ArrayList<TaskList>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler
					.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				TaskList job = new TaskList();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDescription("触发器:" + trigger.getKey());
				job.setNextTime(DateTimeUtil.formatDate(
						trigger.getNextFireTime(), DateTimeUtil.FORMAT_1)); // 下次触发时间
				// trigger.getFinalFireTime();//最后一次执行时间
				job.setPreviousTime(DateTimeUtil.formatDate(
						trigger.getPreviousFireTime(), DateTimeUtil.FORMAT_1));// 上次触发时间
				// trigger.getStartTime();//开始时间
				// trigger.getEndTime();//结束时间
				// 触发器当前状态
				Trigger.TriggerState triggerState = scheduler
						.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				//
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}

	/**
	 * 获取单个任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 * @throws SchedulerException
	 */
	public TaskList getJob(String jobName, String jobGroup) throws Exception {
		TaskList job = null;
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		if (null != trigger) {
			job = new TaskList();
			job.setJobName(jobName);
			job.setJobGroup(jobGroup);
			job.setDescription("触发器:" + trigger.getKey());
			job.setNextTime(DateTimeUtil.formatDate(trigger.getNextFireTime(),
					DateTimeUtil.FORMAT_1)); // 下次触发时间
			job.setPreviousTime(DateTimeUtil.formatDate(
					trigger.getPreviousFireTime(), DateTimeUtil.FORMAT_1));// 上次触发时间
			job.setStartDate(DateTimeUtil.formatDate(trigger.getStartTime(),
					DateTimeUtil.FORMAT_1));
			Trigger.TriggerState triggerState = scheduler
					.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
		}
		return job;
	}
}
