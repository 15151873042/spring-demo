 package spring.aop.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 胡鹏
 * @date 2020/06/20
 */
public class Client {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
        UserServiceImpl userServiceImpl = app.getBean("userServiceImpl", UserServiceImpl.class);
        userServiceImpl.insert();
    }
}
