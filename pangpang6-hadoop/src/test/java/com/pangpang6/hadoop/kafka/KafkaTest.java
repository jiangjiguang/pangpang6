package com.pangpang6.hadoop.kafka;

import com.google.common.collect.Lists;
import com.pangpang6.utils.MyJSONMapper;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class KafkaTest {
    AdminClient adminClient;

    @Before
    public void before() {
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "node1:9092,node2:9092,node3:9092,node4:9092");
        adminClient = KafkaAdminClient.create(properties);
    }

    @Test
    public void createTopics2() throws ExecutionException, InterruptedException {
        NewTopic topic = new NewTopic("flink-output-p2", 2, (short) 2);
        CreateTopicsResult createTopicsResult = adminClient.createTopics(Lists.newArrayList(topic));
        KafkaFuture kafkaFuture = createTopicsResult.all();
        Object object = kafkaFuture.get();

        System.out.println(MyJSONMapper.nonEmptyMapper().toJSONString(object));
    }

    @Test
    public void createTopics() throws ExecutionException, InterruptedException {
        NewTopic topic = new NewTopic("flink-input01", 1, (short) 1);
        CreateTopicsResult createTopicsResult = adminClient.createTopics(Lists.newArrayList(topic));
        KafkaFuture kafkaFuture = createTopicsResult.all();
        Object object = kafkaFuture.get();

        System.out.println(MyJSONMapper.nonEmptyMapper().toJSONString(object));
    }

    @Test
    public void listTopics() throws Exception {
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        KafkaFuture<Set<String>> kafkaFuture = listTopicsResult.names();
        Set<String> set = kafkaFuture.get();
        for (String topic : set) {
            System.out.println(String.format("topic: %s", topic));
        }
    }


}
