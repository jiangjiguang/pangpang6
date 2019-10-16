package com.pangpang6.hadoop.kafka;

import com.pangpang6.hadoop.common.ClickBean;
import com.pangpang6.utils.MyDateUtils;
import com.pangpang6.utils.MyJSONMapper;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProducerTest {

    private static Producer<Long, String> producer;

    private static MyJSONMapper myJSONMapper = MyJSONMapper.nonDefaultMapper();

    private static Random random = new Random(10);

    @Before
    public void before() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKERS);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, KafkaConstants.CLIENT_ID);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        producer = new KafkaProducer<>(properties);
    }


    @Test
    public void flink_test() throws Exception {
        for (int i = 0; i < 1000; i++) {
            Date date = new Date();
            date = DateUtils.addSeconds(date, random.nextInt(10));
            ClickBean clickBean = new ClickBean(MyDateUtils.date2StrDefult(date), i % 2 == 0 ? 1 : 2, i);
            String value = myJSONMapper.toJSONString(clickBean);
            ProducerRecord<Long, String> record = new ProducerRecord<>("flink-input-p2",
                    value);
            try {
                RecordMetadata metadata = producer.send(record).get();
                System.out.println("====================================");
                System.out.println(String.format("partition: %s; offset: %s; value: %s",
                        metadata.partition(), metadata.offset(), value));
            } catch (Exception ex) {
                System.out.println(ExceptionUtils.getFullStackTrace(ex));
            }

            TimeUnit.SECONDS.sleep(1);
        }

    }

    @Test
    public void test01() throws Exception {

        for (int i = 0; i < 10; i++) {
            String value = String.format("This is record: %s ", i);
            ProducerRecord<Long, String> record = new ProducerRecord<>("flink-input-p2",
                    value);
            try {
                RecordMetadata metadata = producer.send(record).get();
                System.out.println("====================================");
                System.out.println(String.format("partition: %s; offset: %s; value: %s",
                        metadata.partition(), metadata.offset(), value));
            } catch (Exception ex) {
                System.out.println("++++++++++++++++++++++++++");
                System.out.println(ExceptionUtils.getFullStackTrace(ex));
            }

            TimeUnit.SECONDS.sleep(1);
        }

    }


}
