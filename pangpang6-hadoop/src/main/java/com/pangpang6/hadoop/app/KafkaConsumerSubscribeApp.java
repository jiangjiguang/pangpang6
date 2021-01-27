package com.pangpang6.hadoop.app;

import com.pangpang6.hadoop.constant.KafkaConstant;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class KafkaConsumerSubscribeApp {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1; i++) {
            new Thread(new MyConsumer(i)).start();
        }
        TimeUnit.DAYS.sleep(1);
    }
    static class MyConsumer implements Runnable {
        private int partition;
        public MyConsumer(int partition) {
            this.partition = partition;
        }

        @Override
        public void run() {
            Consumer<String, String> consumer;

            Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.SERVER_PUB);
            props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstant.TOPIC_BIGDATA_LIBRA_LOG);
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList(KafkaConstant.TOPIC_BIGDATA_LIBRA_LOG));
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(1000L);
                if (consumerRecords == null || consumerRecords.count() == 0) {
                    continue;
                }
                Iterator<ConsumerRecord<String, String>> iterator = consumerRecords.iterator();
                while (iterator.hasNext()) {
                    ConsumerRecord<String, String> next = iterator.next();
                    String content = String.format("Subscribe: partition: %s, offset: %s, value: %s",
                            next.partition(), next.offset(), next.value());
                    System.out.println(content);
                }
            }
        }
    }
}
