 package jdk.classs;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * @author 胡鹏
 * @date 2020/07/16
 */
public class ClassClient {
    
    public void testTethod() {
        
    }
    
    @Override
    public String toString() {
         return super.toString();
    }
    
    
    public static class InnerClass {
        
    }
    
    /**
     *
     * @Descripton 匿名类，内部类名称测试
     * @author 胡鹏
     * @date 2020年7月16日 上午10:15:05
     */
    @Test
    public void classNameTest() {
        Object object1 = new Object() {};
        Object object2 = new Object() {};
        // 匿名类为ClassClient$1，$后面的数字标识第几个匿名类
        System.out.println(object1.getClass().isAnonymousClass());
        System.out.println(object1);
        System.out.println(object2.getClass().isAnonymousClass());
        System.out.println(object2);
        
        // 成员内部类为ClassClient$InnerClass，$后面跟成员内部类名
        InnerClass innerClass = new InnerClass();
        System.out.println(innerClass.getClass().isMemberClass());
        System.out.println(innerClass);
    }
    
    
    

    
    
    /**
     *
     * @Descripton Method.getDeclaringClass()，表示对应方法在哪个类中实现的
     * @author 胡鹏
     * @date 2020年7月20日 上午9:38:02
     * @throws Exception
     */
    @Test
    @SuppressWarnings("all")
    public void methodGetDeclaringClass() throws Exception {
        Method method1 = ClassClient.class.getMethod("testTethod", null);
        Method method2 = ClassClient.class.getMethod("hashCode", null);
        Method method3 = ClassClient.class.getMethod("toString", null);
        System.out.println(method1.getDeclaringClass());
        System.out.println(method2.getDeclaringClass());
        System.out.println(method3.getDeclaringClass());
        
        
    }
    
    
    
    

}
