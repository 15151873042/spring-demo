 package jdk.classloader;

import java.lang.reflect.Field;

/**
 * @author 胡鹏
 * @date 2020/08/10
 */
public class ClassLoaderClient {
    
    
    public static void main(String[] args) throws Exception {
        MyBreakParentClassLoader classLoader = new MyBreakParentClassLoader("d:/test/");
        Class<?> clazz = classLoader.loadClass("java.lang.String");
        
        Object newInstance = clazz.newInstance();
        Field field = clazz.getField("age");
        field.set(newInstance, 10);
        System.out.println(newInstance);
        System.out.println(clazz.getClassLoader());
        
    }

}
