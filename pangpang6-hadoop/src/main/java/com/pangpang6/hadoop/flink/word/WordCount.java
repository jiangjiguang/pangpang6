package com.pangpang6.hadoop.flink.word;

import com.pangpang6.hadoop.flink.word.functions.MyFlatMapFunction;
import com.pangpang6.hadoop.flink.word.functions.MyMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

public class WordCount {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Tuple2<String, Long>> dataStream = env
                .socketTextStream("node1", 9999)
                .flatMap(new MyFlatMapFunction())
                .map(new MyMapFunction())
                .keyBy(0)
                .timeWindow(Time.seconds(2), Time.seconds(6))
                .sum(1).setParallelism(1);
        dataStream.print();
        env.execute("word count");
    }
}
