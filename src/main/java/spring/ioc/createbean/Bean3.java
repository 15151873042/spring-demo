package spring.ioc.createbean;

public class Bean3 {

	@Override
	public String toString() {
		return "通过@Import()来注册" + this.getClass().getName() + "(bean的id为类的全路径)";
	}
}
