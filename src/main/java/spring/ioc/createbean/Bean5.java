package spring.ioc.createbean;

public class Bean5 {

	@Override
	public String toString() {
		return "通过@Import() + ImportBeanDefinitionRegistrar方式来注册" + this.getClass().getName();
	}
}
