package rabbitmq.spring;

import java.io.IOException;
import java.util.UUID;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProducerClient {

	@SuppressWarnings("all")
	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(ProducerConfig.class);
		RabbitTemplate template = app.getBean("rabbitTemplate", RabbitTemplate.class);
		
		MessageObject messageObject = new MessageObject(UUID.randomUUID().toString(), "胡鹏", 30);
		CorrelationData correlationData = new CorrelationData(messageObject.getId());
		try {
			template.convertAndSend("topicExchange_aaa", "queue1", messageObject, correlationData);
		} catch (Exception e) {
			System.err.println("消息发送失败");
			e.printStackTrace();
		}
		System.in.read();
	}
}
