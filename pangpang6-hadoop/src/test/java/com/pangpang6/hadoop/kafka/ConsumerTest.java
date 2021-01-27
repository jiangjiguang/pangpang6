package com.pangpang6.hadoop.kafka;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Properties;

public class ConsumerTest {
    Consumer<Long, String> consumer;

    @Before
    public void before() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.GROUP_ID_CONFIG);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Lists.newArrayList("flink-output-p2"));
    }


    @Test
    public void test01() {
        while (true) {
            ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000L);
            if (consumerRecords == null || consumerRecords.count() == 0) {
                continue;
            }
            Iterator<ConsumerRecord<Long, String>> iterator = consumerRecords.iterator();
            while (iterator.hasNext()) {
                System.out.println("================================");
                ConsumerRecord<Long, String> next = iterator.next();
                System.out.println(String.format("partition: %s, offset: %s, value: %s",
                        next.partition(), next.offset(), next.value()));
            }
            consumer.commitAsync();
        }

    }

    @After
    public void after() {
        consumer.close();
    }

}
