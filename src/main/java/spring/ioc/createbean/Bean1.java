package spring.ioc.createbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bean1 implements Bean {
    
    @Autowired
    private Bean2 bean2;

	public Bean2 getBean2() {
        return bean2;
    }

    public void setBean2(Bean2 bean2) {
        this.bean2 = bean2;
    }

    @Override
	public String toString() {
	    System.out.println(bean2);
		return "通过@CompnentScan + @Controller、@Service、@Repository的方式注册" + this.getClass().getName();
	}
}
