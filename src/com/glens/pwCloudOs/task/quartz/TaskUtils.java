package com.glens.pwCloudOs.task.quartz;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.pwCloudOs.task.dao.TaskLogDao;
import com.glens.pwCloudOs.task.vo.TaskList;
import com.glens.pwCloudOs.task.vo.TaskLog;

public class TaskUtils {
	private final static Logger logger = LoggerFactory
			.getLogger(TaskUtils.class);

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokMethod(TaskList scheduleJob) {
		Object object = null;
		Class<?> clazz = null;
		// springId不为空先按springId查找bean
		if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
			object = EAPContext.getContext().getBean(scheduleJob.getSpringId());
		} else if (StringUtils.isNotBlank(scheduleJob.getJobClass())) {// 按jobClass查找
			try {
				clazz = Class.forName(scheduleJob.getJobClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (object == null) {
			logger.error("任务名称 = [" + scheduleJob.getJobName()
					+ "]---------------未启动成功，请检查执行类是否配置正确！！！");
			return;
		}
		clazz = object.getClass();
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
		} catch (NoSuchMethodException e) {
			logger.error("任务名称 = [" + scheduleJob.getJobName()
					+ "]---------------未启动成功，请检查执行类的方法名是否设置错误！！！");
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		TaskLogDao taskLogDao = (TaskLogDao) EAPContext.getContext().getBean(
				"taskLogDao");
		TaskLog log = new TaskLog();
		log.setJobId(scheduleJob.getJobId());
		log.setJobName(scheduleJob.getJobName());
		try {
			log.setStartDate(DateTimeUtil.formatDate(new Date(),
					DateTimeUtil.FORMAT_1));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		taskLogDao.insertSelective(log);
		// 执行方法
		if (method != null) {
			try {
				method.invoke(object);
				log.setEndDate(DateTimeUtil.formatDate(new Date(),
						DateTimeUtil.FORMAT_1));
				log.setRemark("成功");
				taskLogDao.updateSelective(log);
			} catch (Exception e) {
				e.printStackTrace();
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw, true));
				String str = sw.toString();
				log.setRemark(str);
				taskLogDao.updateSelective(log);
			}
		}
	}

	/**
	 * 判断cron时间表达式正确性
	 * 
	 * @param cronExpression
	 * @return
	 */
	public static boolean isValidExpression(final String cronExpression) {
		// CronScheduleBuilder scheduleBuilder =
		// CronScheduleBuilder.cronSchedule(cronExpression);
		CronTriggerImpl trigger = new CronTriggerImpl();
		try {
			trigger.setCronExpression(cronExpression);
			Date date = trigger.computeFirstFireTime(null);
			return date != null && date.after(new Date());
		} catch (ParseException e) {
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isValidExpression("0 0/1 * * * ?"));
	}

	/*
	 * 任务运行状态
	 */
	public enum TASK_STATE {
		NONE("NONE", "未知"), NORMAL("NORMAL", "正常运行"), PAUSED("PAUSED", "暂停状态"), COMPLETE(
				"COMPLETE", ""), ERROR("ERROR", "错误状态"), BLOCKED("BLOCKED",
				"锁定状态");

		private TASK_STATE(String index, String name) {
			this.name = name;
			this.index = index;
		}

		private String index;
		private String name;

		public String getName() {
			return name;
		}

		public String getIndex() {
			return index;
		}
	}
}
