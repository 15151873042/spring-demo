 package jdk.classloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
  * 自定义类加载器，集成ClassLoader，复写loadClass方法打破双亲委派机制
 * @author 胡鹏
 * @date 2020/08/10
 */
public class MyBreakParentClassLoader extends ClassLoader{
    
    private String classPath;

    public MyBreakParentClassLoader(String classPath) {
        this.classPath = classPath;
    }

    
    // 如果打破双亲委派机制，复写loadClass方法，则必须在方法内使用AppClassLoader.load()方法并return，否则在调用defineClass()方法的时候会出错
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        String fileName = name.replace(".", "/");
        FileInputStream fis;
        try {
            fis = new FileInputStream(classPath + fileName + ".class");
        } catch (FileNotFoundException e) {
            // 应为所有类的父类都是Object，当调用defineClass方法的时候，会加载Object类，递归调用本方法loadClass()，name值为"java.lang.Object"
            //而在对应的目录下没有Object类，所有这里调用父类加载Object
            return super.loadClass(name, false);
        }
        
        byte[] data;
        try {
            int length = fis.available();
            data = new byte[length];
            fis.read(data);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
        
        return defineClass(null, data, 0, data.length);
        
    }
    
    
    @Override
    protected Class<?> findClass(String name) {
        try {
            byte[] data = loadByte(name);
            return defineClass(null, data, 0, data.length);
        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }
    
    
    private byte[] loadByte(String name) throws Exception  {
        name = name.replace(".", "/");
        FileInputStream fis = new FileInputStream(classPath + name + ".class");
        int length = fis.available();
        byte[] data = new byte[length];
        fis.read(data);
        fis.close();
        return data;        
    }
}
