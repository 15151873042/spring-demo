package rabbitmq.spring;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory.ConfirmType;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ProducerConfig {
	
	public static final String QUEUE_1 = "queue1";
	
	public static final String QUEUE_2 = "queue2";
	
    /**
     * 创建连接工厂
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory () {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setAddresses("127.0.0.1:5672");
        cachingConnectionFactory.setVirtualHost("/app");
        cachingConnectionFactory.setUsername("app");
        cachingConnectionFactory.setPassword("app");
        cachingConnectionFactory.setConnectionTimeout(100000);
        cachingConnectionFactory.setCloseTimeout(100000);
        // 开启消息不能发送到交换机的监听回调（比如交换机名称写错，交换机不存在）
        cachingConnectionFactory.setPublisherConfirmType(ConfirmType.CORRELATED);
        // 开发消息不能路由到队列的监听回调（比如routingKey导致消息不能路由到任务一个队列）
        cachingConnectionFactory.setPublisherReturns(true);
        return cachingConnectionFactory;
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        // 配置json消息转换器，这样发送java Object对象可以直接转换成json字符串，Jackson2JsonMessageConverter内部依赖ObjectMapper，需要导入jackson-annotation包
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(new ObjectMapper()));
        ProducerCustomCallback customCallback = new ProducerCustomCallback();
        // 消息可达交换机回调对象
        rabbitTemplate.setConfirmCallback(customCallback);
        // 开启ReturnCallback的前提
        rabbitTemplate.setMandatory(true);
        // 消息可达队列回调对象
        rabbitTemplate.setReturnCallback(customCallback);
        rabbitTemplate.setReceiveTimeout(50000);
        return rabbitTemplate;
    }

    
    
}
