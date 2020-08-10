package spring.ioc.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
	
	// 条件注解@conditional
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
		Bean2 bean2 = (Bean2)app.getBean("bean2");
		System.out.println(bean2);
	}
}
