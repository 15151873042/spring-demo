package spring.ioc.autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

	/**
	 * 上下文中UserDao类型的Bean有多个时，UserService使用@autoWired注入UserDao时是按照UserDao对应Bean的名称注入的
	 * （UserService中的熟悉名称必须和bean名称一致才可以注入）
	 */
	// 
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
        UserService aService = app.getBean("userService", UserService.class);
        app.getEnvironment().setActiveProfiles(args);
        aService.printUserDaoName();
	}
}
