package jdk.dynamicproxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 * @author 胡鹏
 *
 */
public class JdkProxyClient {

	/**
	 * 使用JDK动态代理的步骤：
	 * 1、实现自定义InvocationHandler，其内部需要包含目标对象，否则invoke方法回调的时候无法调用目标对象对应的方法
	 * 2、通过Proxy.getProxyClass()方法传入目标对象所实现的接口获得动态代理类
	 * 3、反射获取代理类的构造方法
	 * 4、传入自定义的InvocationHandler作为构造函数参数，创建代理对象
	 * 5、
	 */
	@SuppressWarnings("all")
	public static void main(String[] args) throws Exception {
        // =========================第一种==========================
        // 1、生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 2、获取动态代理类
		Class proxyClazz = Proxy.getProxyClass(HelloService.class.getClassLoader(), HelloService.class);
        // 3、获得代理类的构造函数，并传入参数类型InvocationHandler.class
        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
        // 4、通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
        HelloService proxyHello1 = (HelloService) constructor.newInstance(new MyInvocationHandler(new HelloServiceImpl()));
        // 5、通过代理对象调用目标方法
        proxyHello1.sayHello();
		
        
        
        // ==========================第二种=============================
        /**
         * Proxy类中还有个将2~4步骤封装好的简便方法来创建动态代理对象，
         *其方法签名为：newProxyInstance(ClassLoader loader,Class<?>[] instance, InvocationHandler h)
         */
//		HelloService proxyHello2 = (HelloService)Proxy.newProxyInstance(
//			HelloServiceImpl.class.getClassLoader(), 
//			HelloServiceImpl.class.getInterfaces(),
//			new MyInvocationHandler(new HelloServiceImpl()));
//		proxyHello2.sayHello();
	}
	
}
