package com.pangpang6.hadoop.flink.word.functions;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyMapFunction implements MapFunction<String, Tuple2<String, Long>> {
    private static final Logger logger = LoggerFactory.getLogger(MyMapFunction.class);

    @Override
    public Tuple2<String, Long> map(String s) {
        logger.info("map: {}", s);
        return new Tuple2<>(s, 1l);
    }
}
