package spring.ioc.createbean;

public class Bean4 {

	@Override
	public String toString() {
		return "通过@Import() + ImportSelector方式来注册" + this.getClass().getName() + "(bean的id为类的全路径)";
	}
}
