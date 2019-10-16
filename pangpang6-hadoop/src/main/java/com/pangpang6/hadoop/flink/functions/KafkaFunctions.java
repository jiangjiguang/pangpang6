package com.pangpang6.hadoop.flink.functions;

import org.apache.flink.api.common.functions.MapFunction;

public class KafkaFunctions implements MapFunction<String, String> {
    @Override
    public String map(String value) throws Exception {
        return String.format("%s---", value);
    }
}
