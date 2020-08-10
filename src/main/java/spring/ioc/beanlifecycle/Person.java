package spring.ioc.beanlifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Person implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean{

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("【注入属性】注入属性name");
		this.name = name;
	}

	
	public Person() {
		System.out.println("【构造器】调用Person的构造器实例化");
	}

	public void destroy() throws Exception {
		System.out.println("DiposibleBean.destory()");
		
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean.afterPropertiesSet()");
		
	}

	public void setBeanName(String name) {
		System.out.println("BeanNameAware.setBeanName()");		
		
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryAware.setBeanFactory()");
		
	}
	

    // 通过<bean>的init-method属性指定的初始化方法
    public void myInit() {
        System.out.println("<bean>的init-method方法");
    }

    // 通过<bean>的destroy-method属性指定的初始化方法
    public void myDestory() {
        System.out.println("<bean>的destroy-method方法");
    }
    
    @PostConstruct
    public void myInit2() {
    	System.out.println("@PostConstruct");
    }
    
    @PreDestroy
    public void myDestory2() {
    	System.out.println("@PreDestroy");
    }

}
