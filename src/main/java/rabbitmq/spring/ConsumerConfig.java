package rabbitmq.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ConsumerConfig {

	public static final String EXCHANGE = "topicExchange";

	public static final String QUEUE1 = "queue1";

	public static final String QUEUE1_ROUTING_KEY = "#.queue1.#";

	public static final String QUEUE2 = "queue2";

	public static final String QUEUE2_ROUTING_KEY = "#.queue2.#";

	/**
	 * 创建连接工厂
	 *
	 * @return
	 */
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setAddresses("127.0.0.1:5672");
		cachingConnectionFactory.setVirtualHost("/app");
		cachingConnectionFactory.setUsername("app");
		cachingConnectionFactory.setPassword("app");
		cachingConnectionFactory.setConnectionTimeout(100000);
		cachingConnectionFactory.setCloseTimeout(100000);
		return cachingConnectionFactory;
	}

	// 在配置RabbitAdmin
	// 配置该bean对象的情况下，交换机bean对象、队列bean对象、绑定关系bean对象都会在rabbitMQ服务中自动动创建并绑定
	// 如果在mq服务中已存在则不会创建队列、交换机
	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
		// spring容器启动加载该类
		rabbitAdmin.setAutoStartup(true);
		return rabbitAdmin;
	}

	/** 声明交换机 */
	@Bean
	public TopicExchange topicExchange() {
		// durable:设置是否持久化。durable设置为true时表示持久化，反之非持久化.持久化可以将交换器存入磁盘，在服务器重启的时候不会丢失相关信息
		/*
		 * autodelete:设置是否自动删除。autoDelete设置为true时，则表示自动删除。
		 * 自动删除的前提是至少有一个队列或者交换器与这个交换器绑定，之后，所有与这个交换器绑定的队列或者交换器都与此解绑。
		 * 不能错误的理解—当与此交换器连接的客户端都断开连接时，RabbitMq会自动删除本交换器
		 */
		return new TopicExchange(EXCHANGE, true, false);
	}

	/** 声明队列 */
	@Bean
	public Queue queue1() {
		// durable: 是否持久化，服务重启队列是否存在，队列中的消息是否存在
		// autodelete:表示没有程序和队列建立连接 那么就会自动删除队列
		// exclusive:有且只有一个消费者监听,服务停止的时候删除该队列
		HashMap<String, Object> ququeArguments = new HashMap<String, Object>();
		// 设置队列最大长度2
		ququeArguments.put("x-max-length", 2);
		Queue queue1 = new Queue(QUEUE1, true, false, false, ququeArguments);
		return queue1;
	}

	@Bean
	public Queue queue2() {
		return new Queue(QUEUE2, true, false, false, null);
	}

	/** 声明交换机和队列的绑定关系 */
	@Bean
	public Binding queue1Binding() {
		return BindingBuilder.bind(queue1()).to(topicExchange()).with(QUEUE1_ROUTING_KEY);
	}

	@Bean
	public Binding queue2Binding() {
		return BindingBuilder.bind(queue2()).to(topicExchange()).with(QUEUE2_ROUTING_KEY);
	}

	/** 队列消费监听对象，监听方法名称和队列名称有绑定关系 */
	@Bean
	ConsumerServiceImpl consumerService() {
		return new ConsumerServiceImpl();
	}

	@Bean
	public SimpleMessageListenerContainer simpleMessageListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(
				connectionFactory());
		// 设置监听的队列
		simpleMessageListenerContainer.setQueues(queue1(), queue2());
		// 设置当前消费者1
		simpleMessageListenerContainer.setConcurrentConsumers(1);
		// 最大消费者个数10
		simpleMessageListenerContainer.setMaxConcurrentConsumers(10);
		// 设置签收模式
		/** AcknowledgeMode.AUTO，如果回调方法正常执行则ack，如果方法执行抛出异常则nack */
		simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
		// 设置拒绝重回队列
		/** 一般拒绝之后不让其回到原来队列，如果回到原来队列，会一直拒绝->消费->报错->拒绝 */
		simpleMessageListenerContainer.setDefaultRequeueRejected(false);
		// 消费端的标签策略
		simpleMessageListenerContainer.setConsumerTagStrategy(new ConsumerTagStrategy() {
			@Override
			public String createConsumerTag(String s) {
				return UUID.randomUUID().toString() + "-" + s;
			}
		});

		// 创建消息监听适配器对象
		// 自己创建一个消息委托器对象
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(consumerService());
		// 不同队列由指定对应方法监听，key：队列名称；value：消费消息的方法名称
		Map<String, String> queuToMethodNameMap = new HashMap<>();
		queuToMethodNameMap.put(QUEUE1, "getMessageFromQueue1");
		queuToMethodNameMap.put(QUEUE2, "getMessageFromQueue2");
		messageListenerAdapter.setQueueOrTagToMethodName(queuToMethodNameMap);
		
		Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter(new ObjectMapper());
		messageListenerAdapter.setMessageConverter(jackson2JsonMessageConverter);
		simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);

		return simpleMessageListenerContainer;
	}

}
