package spring.ioc.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public MyBeanPostProcessor myBeanPostProcessor() {
		return new MyBeanPostProcessor();
	} 
	
	@Bean 
	MyInstantiationAwareBeanPostProcessor myInstantiationAwareBeanPostProcessor() {
		return new MyInstantiationAwareBeanPostProcessor();
	}
	
	@Bean 
	MyBeanFactoryPostProcessor MyBeanFactoryPostProcessor() {
		return new MyBeanFactoryPostProcessor();
	}
	
	@Bean(initMethod="myInit", destroyMethod="myDestory")
	Person person() {
		return new Person();
	}
}
