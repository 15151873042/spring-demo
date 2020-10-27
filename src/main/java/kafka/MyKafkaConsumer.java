package kafka;

import com.got.common.core.util.DateUtil;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @ClassName: MyKafkaConsumer
 * @Description:
 * @Author 胡鹏
 * @Date 2020/10/26
 */
public class MyKafkaConsumer {

    public static void main(String[] args)  {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.92.131:9092,192.168.92.131:9093,192.168.92.131:9094,192.168.92.131:9095");
        props.setProperty("group.id", "java-client");
        // 是否自动提交offset
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 设置消费主题的名字
        consumer.subscribe(Arrays.asList("p2r2"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5000));
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }




    @Test
    public void manualCommit() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.92.131:9092,192.168.92.131:9093,192.168.92.131:9094,192.168.92.131:9095");
        props.setProperty("group.id", "java-client");
        // 是否自动提交offset
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 设置消费主题的名字
        consumer.subscribe(Arrays.asList("p2r2"));
        final int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5000));
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                consumer.commitSync();
                buffer.clear();
            }
        }
    }


    // --from-beginning --offset 参数调试
    @Test
    public void fromBeginningAndOffSetTest() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.92.131:9092,192.168.92.131:9093,192.168.92.131:9094,192.168.92.131:9095");
        props.setProperty("group.id", "java-client");
        // 是否自动提交offset
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        String topicName = "p2r2";
        TopicPartition p2r2_0 = new TopicPartition(topicName, 0);
        TopicPartition p2r2_1 = new TopicPartition(topicName, 1);

        // 设置从哪些主题以及对应的哪些partition消费消息
        consumer.assign(Arrays.asList(p2r2_0, p2r2_1));
        // 设置从partition1 offset=13的位置消费
        consumer.seek(p2r2_1, 13);
        // 设置从partition0的起始位置消费
        consumer.seekToBeginning(Arrays.asList(p2r2_0));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(MessageFormat.format("offset = {0}, key = {1}, value = {2} partition = {3}",
                        String.valueOf(record.offset()), record.key(), record.value(), String.valueOf(record.partition())));
            }
        }
    }


    // 从指定时间点消费
    @Test
    public void test() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.92.131:9092,192.168.92.131:9093,192.168.92.131:9094,192.168.92.131:9095");
        props.setProperty("group.id", "java-client");
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        String topicName = "p2r2";
        // 1、查看p2r2主题的所有partition
        List<PartitionInfo> p2r2PartitionList = consumer.partitionsFor(topicName);

        Map<TopicPartition, Long> topicPartitonMap = new HashMap<>(p2r2PartitionList.size());
        long specifiedTim = DateUtil.parseDate("2020-10-26 14:00:00", "yyyy-MM-dd HH:mm:ss").getTime();
        p2r2PartitionList.forEach(partitionInfo -> {
            TopicPartition topicPartition = new TopicPartition(partitionInfo.topic(), partitionInfo.partition());
            topicPartitonMap.put(topicPartition, specifiedTim);
        });

        // 2、查看指定主题指定partition下指定时间的offset（例如查看主题p2r2下partition0下2020-10-26 11:00:00之后的消息的offset起始位置）
        Map<TopicPartition, OffsetAndTimestamp> topicPartitionOffsetAndTimestampMap = consumer.offsetsForTimes(topicPartitonMap);
        Set<Map.Entry<TopicPartition, OffsetAndTimestamp>> entries = topicPartitionOffsetAndTimestampMap.entrySet();


        consumer.assign(topicPartitionOffsetAndTimestampMap.keySet());
        for (Map.Entry<TopicPartition, OffsetAndTimestamp> entry : entries) {
            TopicPartition topicPartition = entry.getKey();
            OffsetAndTimestamp offsetAndTimestamp = entry.getValue();
            if (null != offsetAndTimestamp) {
                // 说明指定时间之后有消息可以消费
                long offset = offsetAndTimestamp.offset();
                consumer.seek(topicPartition, offset);
            }
        }

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(MessageFormat.format("offset = {0}, key = {1}, value = {2} partition = {3}",
                        String.valueOf(record.offset()), record.key(), record.value(), String.valueOf(record.partition())));
            }
        }
    }
}
