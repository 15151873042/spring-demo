package spring.quartz;

import java.util.Date;

import org.quartz.SchedulerException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws SchedulerException {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
		QuartzManager quartzManager = (QuartzManager)app.getBean("quartzManager");
		Notice notice = new Notice();
		notice.setId("notice1");
		notice.setNoticeName("直播间公告名");
		notice.setNoticeContent("直播间公告内容");
		notice.setStartTime(new Date());
		notice.setPlayInterval(1000);
		notice.setPlayCount(1000);
		quartzManager.addNotice(notice);
		
		
		
		Notice notice2 = new Notice();
		notice2.setId("notice2");
		notice2.setNoticeName("公共聊天室公告名");
		notice2.setNoticeContent("公共聊天室公告内容");
		notice2.setStartTime(new Date());
		notice2.setPlayInterval(5000);
		notice2.setPlayCount(10);
		quartzManager.addNotice(notice2);
		
		
		
		Notice notice3 = new Notice();
		notice3.setId("notice1");
		notice3.setNoticeName("直播间公告名");
		notice3.setNoticeContent("直播间公告内容");
		notice3.setStartTime(new Date());
		notice3.setPlayInterval(3000);
		notice3.setPlayCount(5);
		quartzManager.modifyNotice(notice3);
		
		quartzManager.removeNotice(notice);
		quartzManager.removeNotice(notice2);
		
		System.out.println("结束");
	}
}
