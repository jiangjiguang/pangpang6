package com.pangpang6.hadoop.flink.word.functions;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFlatMapFunction implements FlatMapFunction<String, String> {
    private static final Logger logger = LoggerFactory.getLogger(MyFlatMapFunction.class);

    @Override
    public void flatMap(String value, Collector<String> collector) throws Exception {
        logger.info("flatMap {}, {}", value, collector.getClass());
        String[] words = value.split(" ");
        for (String word : words) {
            collector.collect(word);
        }
    }
}
