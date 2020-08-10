 package spring.aop.proxyFactory;

/**
 * @author 胡鹏
 * @date 2020/06/19
 */
public class SimplePojo implements Pojo {

    @Override
    public void foo() {
        System.out.println("foo");
//        Pojo currentProxy = (Pojo)AopContext.currentProxy();
//        currentProxy.bar();
    }

    @Override
    public void bar() {
        System.out.println("bar");
    }

}
