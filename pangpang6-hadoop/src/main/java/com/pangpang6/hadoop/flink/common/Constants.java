package com.pangpang6.hadoop.flink.common;

public class Constants {
    public static final String KAFKA_BROKERS = "node1:9092,node2:9092,node3:9092,node4:9092";
    public static final String TOPIC_NAME = "flink-input-p2";
    public static final String GROUP_ID_CONFIG = "kafka";
    public static final String OFFSET_RESET_LATEST = "latest";
    public static final String OFFSET_RESET_EARLIER = "earliest";
}
