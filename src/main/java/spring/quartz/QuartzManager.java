package spring.quartz;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.SimpleTriggerImpl;

public class QuartzManager {
	
	private Scheduler scheduler;
	
	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	private JobDetail newJobDetailInstance(Notice notice) {
		String noticeId = notice.getId();
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
			.withIdentity(noticeId).build();
		return jobDetail;
	}
	
	private Trigger newTriggerInstance(Notice notice) {
		SimpleTriggerImpl trigger = new SimpleTriggerImpl();
		TriggerKey triggerKey = getTriggerKey(notice);
		trigger.setKey(triggerKey);
		trigger.setName(notice.getId());
		trigger.setStartTime(new Date()); // FIXME
		trigger.setRepeatInterval(notice.getPlayInterval());
		trigger.setRepeatCount(notice.getPlayCount());
		JobDataMap jobDataMap = trigger.getJobDataMap();
		jobDataMap.put("notice", notice);
		return trigger;
	}
	
	private JobKey getJobKey(Notice notice) {
		return JobKey.jobKey(notice.getId());
	}
	
	private TriggerKey getTriggerKey(Notice notice) {
		return TriggerKey.triggerKey(notice.getId());
	}
	
	/**
	 *
	 * @Descripton 添加通知
	 * @author 胡鹏
	 * @date 2020年5月27日 下午2:37:43
	 * @param notice
	 * @throws SchedulerException
	 */
	public void addNotice(Notice notice) throws SchedulerException {
		JobDetail jobDetail = newJobDetailInstance(notice);
		Trigger trigger = newTriggerInstance(notice);
		scheduler.scheduleJob(jobDetail, trigger);
	}
	
	
	/**
	 *
	 * @Descripton 更新通知
	 * @author 胡鹏
	 * @date 2020年5月27日 下午2:37:54
	 * @param notice
	 * @throws SchedulerException
	 */
	public void modifyNotice(Notice notice) throws SchedulerException {
		TriggerKey existTriggerKey = getTriggerKey(notice);
		Trigger existTrigger = scheduler.getTrigger(existTriggerKey);
		if (null == existTrigger) {
			addNotice(notice);
		}
		
		Trigger modifyTrigger = newTriggerInstance(notice);
		scheduler.rescheduleJob(existTriggerKey, modifyTrigger);
	}
	
	/**
	 *
	 * @Descripton 移除通知
	 * @author 胡鹏
	 * @date 2020年5月27日 下午2:38:15
	 * @param notice
	 * @throws SchedulerException
	 */
	public void removeNotice(Notice notice) throws SchedulerException {
		TriggerKey triggerKey = getTriggerKey(notice);
		JobKey jobKey = getJobKey(notice);
		// 停止触发器
		scheduler.pauseTrigger(triggerKey);
		// 移除触发器
		scheduler.unscheduleJob(triggerKey);
		// 删除任务
		scheduler.deleteJob(jobKey);
	}
	
	

	/**
	 *
	 * @Descripton 启动所有定时任务
	 * @author 胡鹏
	 * @throws SchedulerException 
	 * @date 2020年5月27日 下午2:36:48
	 */
	public void startJobs() throws SchedulerException {
		scheduler.start();
	}

	
	
	/**
	 *
	 * @Descripton 关闭所有定时任务
	 * @author 胡鹏
	 * @throws SchedulerException 
	 * @date 2020年5月27日 下午2:37:15
	 */
	public void shutdownJobs() throws SchedulerException {
		if (!scheduler.isShutdown()) {
			scheduler.shutdown();
		}
	}
}
