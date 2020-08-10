package spring.ioc.autowired;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void printUserDaoName() {
		System.out.println(userDao.getName());
	}
}
