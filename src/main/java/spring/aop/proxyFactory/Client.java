 package spring.aop.proxyFactory;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author 胡鹏
 * @date 2020/06/19
 */
public class Client {
    
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setExposeProxy(true);
        proxyFactory.setTarget(new SimplePojo());
//        proxyFactory.setInterfaces(Pojo.class);
        MyMethodInterceptor interceptor1 = new MyMethodInterceptor("advice-1");
        MyMethodInterceptor interceptor2 = new MyMethodInterceptor("advice-2");
        proxyFactory.addAdvice(interceptor1);
        proxyFactory.addAdvice(interceptor2);
        Pojo pojo = (Pojo)proxyFactory.getProxy();
        pojo.foo();
    }
    
    
    
    @Test
    public void aspectjTest() {
        //创建一个可以为给定目标对象生成代理的 
        AspectJProxyFactory factory = new AspectJProxyFactory(new SimplePojo());
        //现在获取代理对象... 
        Pojo pojo = factory.getProxy();
        pojo.foo();
    }
    
    
    static class MyMethodInterceptor implements MethodInterceptor {
        
        private String name;
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }

        public MyMethodInterceptor(String name) {
            this.name = name;
        }

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println(name + "前");
            Object proceed = invocation.proceed();
            System.out.println(name + "后");
            return proceed;
        }
    }
}
