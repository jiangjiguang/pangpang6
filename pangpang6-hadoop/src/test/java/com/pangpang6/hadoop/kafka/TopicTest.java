package com.pangpang6.hadoop.kafka;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Properties;

public class TopicTest {
    AdminClient adminClient;

    @Before
    public void before() {
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "node1:9092,node2:9092,node3:9092,node4:9092");
        adminClient = KafkaAdminClient.create(properties);
    }

    @Test
    public void desc() throws Exception {
        DescribeTopicsResult topicsResult = adminClient.describeTopics(Lists.newArrayList("flink-output-p2"));
        KafkaFuture<Map<String, TopicDescription>> kafkaFuture = topicsResult.all();
        Map<String, TopicDescription> map = kafkaFuture.get();
        for (Map.Entry<String, TopicDescription> entry : map.entrySet()) {
            String topic = entry.getKey();
            TopicDescription topicDescription = entry.getValue();
            System.out.println(topicDescription.toString());
        }

    }
}
