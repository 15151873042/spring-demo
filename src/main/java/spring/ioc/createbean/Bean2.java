package spring.ioc.createbean;

public class Bean2 implements Bean {

	@Override
	public String toString() {
		return "通过@Bean的方式注册" + this.getClass().getName() + "(适用于导入第三方组件类Bean)";
	}
}
