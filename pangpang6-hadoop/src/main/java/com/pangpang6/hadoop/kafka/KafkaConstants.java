package com.pangpang6.hadoop.kafka;

public class KafkaConstants {
    public static String KAFKA_BROKERS = "node1:9092,node2:9092,node3:9092,node4:9092";
    public static Integer MESSAGE_COUNT = 1000;
    public static String CLIENT_ID = "client1";
    public static String TOPIC_NAME = "test";
    public static String GROUP_ID_CONFIG = "consumerGroup1";
    public static Integer MAX_NO_MESSAGE_FOUND_COUNT = 100;
    public static String OFFSET_RESET_LATEST = "latest";
    public static String OFFSET_RESET_EARLIER = "earliest";
    public static Integer MAX_POLL_RECORDS = 1;

}
