package rabbitmq.spring;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConsumerClient {

	@SuppressWarnings("all")
	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(ConsumerConfig.class);
	} 
}
