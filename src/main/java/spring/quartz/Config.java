package spring.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {"spring.quartz"})
public class Config {
	
//	@Bean("myJob2")
//	public MyJob2 myJob2() {
//		return new MyJob2();
//	}
//	
//	
//	@Bean("jobtask")
//	public MethodInvokingJobDetailFactoryBean jobtask(MyJob2 myJob2) {
//		MethodInvokingJobDetailFactoryBean jobTask = new MethodInvokingJobDetailFactoryBean();
//		jobTask.setTargetObject(myJob2);
//		jobTask.setTargetMethod("doSomething");
//		jobTask.setConcurrent(false);
//		return jobTask;
//	}
//	
//	
//	@Bean("cronTrigger")
//	public CronTriggerFactoryBean cronTrigger(JobDetail jobtask) {
//		CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
//		factoryBean.setJobDetail(jobtask);
//		factoryBean.setCronExpression("0/50 * * * * ?");
//		return factoryBean;
//	}
//	
//	
//	@Bean(value = "startQuertz")
//	@Lazy(value = true)
//	public SchedulerFactoryBean startQuertz(Trigger cronTrigger) {
//		SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
//		factoryBean.setTriggers(cronTrigger);
//		return factoryBean;
//	}
	
	
//	@Bean(value="quartzManager", initMethod="startJobs")
//	public QuartzManager quartzManager(Scheduler scheduler) throws SchedulerException {
//		quartzManager.setScheduler(scheduler);
//		quartzManager.setScheduler(StdSchedulerFactory.getDefaultScheduler());
//		return quartzManager;
//	}
	
//	@Bean
//	public Person person() {
//		Person person = new Person();
//		person.setName("胡鹏");
//		return person;
//	}
	
	
	
//	@Bean("noticeJobDetail")
//	public JobDetail jobDetail() {
//		return JobBuilder.newJob(MyJob.class).withIdentity("test", "test").build();
//	}

	
	
	@Bean(value="quartzManager", initMethod="startJobs")
	public QuartzManager quartzManager() throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		QuartzManager quartzManager = new QuartzManager();
		quartzManager.setScheduler(scheduler);
		return quartzManager;
	}
	
}






















