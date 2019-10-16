package com.pangpang6.hadoop.flink.sinks;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

public class TradeSink implements SinkFunction<Tuple2<String, Integer>> {
    @Override
    public void invoke(Tuple2<String, Integer> value, Context context) throws Exception {
        System.out.println(String.format("Get: \t(%s, %d)", value.f0, value.f1));

    }
}
