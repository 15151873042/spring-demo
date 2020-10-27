package kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName: MyKafkaProducer
 * @Description:
 * @Author 胡鹏
 * @Date 2020/10/26
 */
public class MyKafkaProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 设置发送者的参数信息
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.92.131:9092,192.168.92.131:9093,192.168.92.131:9094,192.168.92.131:9095");
        properties.put(ProducerConfig.ACKS_CONFIG, "1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer kafkaProducer = new KafkaProducer(properties);

        for (int i=0; i<10; i++) {
            long currentTimeMillis = System.currentTimeMillis();
            ProducerRecord<String, String> msg = new ProducerRecord<>("p2r2",
                    "java-client-key", String.valueOf(i));

//        // 同步发送，future.get();获取结果的时候会阻塞
//        Future<RecordMetadata> future = kafkaProducer.send(msg);
//        RecordMetadata recordMetadata = future.get();
//        System.out.println("异步方式发送消息结果：" + "topic-" + recordMetadata.topic() + "|partition-"
//                + recordMetadata.partition() + "|offset-" + recordMetadata.offset());

            //异步方式发送消息
            kafkaProducer.send(msg, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception != null) {
                        System.err.println("发送消息失败：" + exception.getStackTrace());

                    }
                    if (metadata != null) {
                        System.out.println("异步方式发送消息结果：" + "topic-" + metadata.topic() + "|partition-"
                                + metadata.partition() + "|offset-" + metadata.offset());
                    }
                }
            });
        }
        Thread.sleep(2000); //如果是异步发送，此处需要sleep，否则主线程结束，异步方法不会执行
    }
}
