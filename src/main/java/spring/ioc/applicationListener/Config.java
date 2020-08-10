package spring.ioc.applicationListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public MyApplicationListener myApplicationListener() {
		return new MyApplicationListener();
	}
}
