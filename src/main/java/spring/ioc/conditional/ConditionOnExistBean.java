package spring.ioc.conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;

// FIXME 注意自定义注解的时候，必须要设置@Target和@Retention元注解，否则不生效，具体原因不知道
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(value = {ExistBeanCondition.class})
public @interface ConditionOnExistBean {
	
	Class<?>[] value() default {};

}
