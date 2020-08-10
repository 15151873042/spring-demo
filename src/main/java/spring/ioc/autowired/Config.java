package spring.ioc.autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	
	
	@Bean("userService")
	UserService aService() {
		return new UserService();
	}
	

//	@Primary
	//Primary可以是该Bean作为主对象
	@Bean(name = "zhangSan")
	UserDao userDao1() {
		UserDao userDao = new UserDao();
		userDao.setName("张三");
		return userDao;
	}
	
	
	@Bean(name = "liSi")
	UserDao userDao2() {
		UserDao userDao = new UserDao();
		userDao.setName("李四");
		return userDao;
	}
}
