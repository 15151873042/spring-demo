package rabbitmq.spring;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;

public class ProducerCustomCallback implements ConfirmCallback, ReturnCallback {

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
			System.err.println("消息成功发送到交换机");
		} else {
			// 失败的情况：
			// 1、发送的交换机不存在
			System.err.println("业务消息id为：" + correlationData.getId()  +"发送到交换机失败");
		}
	}

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		// 消息发送到队列，但是不能路由到队列时才会回调
		System.err.print("消息路由到队列失败");
	}


}
