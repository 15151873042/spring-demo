package spring.ioc.createbean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AnnotatedElementUtils;

public class Client {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(CreateBeanConfig.class);
		Bean1 bean1 = (Bean1)app.getBean("bean1");
		Bean2 bean2 = (Bean2)app.getBean("bean2");
		Bean3 bean3 = (Bean3)app.getBean("spring.ioc.createbean.Bean3");
		Bean4 bean4 = (Bean4)app.getBean("spring.ioc.createbean.Bean4");
		Bean5 bean5 = (Bean5)app.getBean("bean5");
		System.out.println(bean1);
		System.out.println(bean2);
		System.out.println(bean3);
		System.out.println(bean4);
		System.out.println(bean5);
		System.out.println("结束");
	}
	
	
	
	@Test
	public void test() {
	    boolean annotated = AnnotatedElementUtils.isAnnotated(CreateBeanConfig.class, Import.class);
	    System.out.println(annotated);
	}
}
