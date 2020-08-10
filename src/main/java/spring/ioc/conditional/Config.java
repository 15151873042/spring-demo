package spring.ioc.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
//	@Bean
//	Bean1 bean1() {
//		return new Bean1();
//	}

	@Bean
	@ConditionOnExistBean(value = {Bean1.class})
	Bean2 bean2() {
		return new Bean2();
	}
}
