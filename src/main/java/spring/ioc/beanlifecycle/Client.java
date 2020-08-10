package spring.ioc.beanlifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

	
	public static void main(String[] args) {
		System.out.println("--------现在开始初始化容器--------");
        ApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
        System.out.println("--------容器初始化成功--------");    
        Person person = app.getBean("person",Person.class);
        System.out.println(person);
        
        System.out.println("--------现在开始关闭容器！--------");
        ((AnnotationConfigApplicationContext)app).registerShutdownHook();
	}
}
