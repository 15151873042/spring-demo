 package spring.aop.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 胡鹏
 * @date 2020/06/20
 */
 @Configuration
 @EnableAspectJAutoProxy
 @Aspect
public class Config {
     
     @Bean
     UserServiceImpl userServiceImpl() {
         return new UserServiceImpl();
     }
     @Pointcut("execution(* spring.aop.demo.**.*(..))")
     public void pointCut1() {}
     
     @Around("pointCut1()")
     public Object profile(ProceedingJoinPoint pjp) throws Throwable {
         System.out.println("前置增强");
         Object proceed = pjp.proceed();
         System.out.println("后置增强");
         return proceed;
     }

}
