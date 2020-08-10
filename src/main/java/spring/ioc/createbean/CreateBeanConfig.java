package spring.ioc.createbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages= {"spring.ioc.createbean"})
@Import(value = {Bean3.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class CreateBeanConfig {
	
	
	@Bean
//	@Lazy
//	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Bean2 bean2() {
		return new Bean2();
	}

}
