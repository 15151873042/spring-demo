package spring.ioc.conditional;

import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ExistBeanCondition implements Condition {

	@Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionOnExistBean.class.getName());
		Class<?>[] clazzs = (Class<?>[])annotationAttributes.get("value");
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		for (Class<?> clazz : clazzs) {
			String[] beanNames = beanFactory.getBeanNamesForType(clazz);
			if(null == beanNames || beanNames.length == 0) {
				return false;
			}
		}
		return true;
	}

}
