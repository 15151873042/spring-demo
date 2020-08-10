package rabbitmq.spring;


public class ConsumerServiceImpl {

	public void getMessageFromQueue1(MessageObject msg) {
		System.out.println("queue1>>>>收到消息:" + msg);
	}
	
	public void getMessageFromQueue2(MessageObject msg) {
		System.out.println("queue2>>>>收到消息:" + msg);
	}
}
