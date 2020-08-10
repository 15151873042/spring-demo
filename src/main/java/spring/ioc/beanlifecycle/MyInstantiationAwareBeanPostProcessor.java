package spring.ioc.beanlifecycle;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

public class MyInstantiationAwareBeanPostProcessor extends
        InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantiationAwareBeanPostProcessor() {
        System.out.println("InstantiationAwareBeanPostProcessor构造函数");
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation()");
        return null;
    }

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		System.out.println("InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation()");
		return true;
	}


    // 接口方法、设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs,
            PropertyDescriptor[] pds, Object bean, String beanName)
            throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法");
        return pvs;
    }
}