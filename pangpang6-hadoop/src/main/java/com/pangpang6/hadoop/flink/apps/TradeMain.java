package com.pangpang6.hadoop.flink.apps;

import com.pangpang6.hadoop.flink.source.TradeSource;
import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

//使用AggregateFunction  重新实现
public class TradeMain {
    private static Logger logger = LoggerFactory.getLogger(TradeMain.class);

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStream<Tuple2<String, Integer>> ds = env.addSource(new TradeSource());

        KeyedStream<Tuple2<String, Integer>, Tuple> keyedStream = ds.keyBy(0);
        keyedStream.sum(1).keyBy(new KeySelector<Tuple2<String, Integer>, Object>() {
            @Override
            public Object getKey(Tuple2<String, Integer> value) throws Exception {
                //为了统计全局信息我们通过在getKey()方法里返回常数“欺骗”flink将所有数据分成一组
                return "";
            }
        }).fold(new HashMap<String, Integer>(), new FoldFunction<Tuple2<String, Integer>, HashMap<String, Integer>>() {
            @Override
            public HashMap<String, Integer> fold(HashMap<String, Integer> accumulator, Tuple2<String, Integer> value) throws Exception {
                accumulator.put(value.f0, value.f1);
                return accumulator;
            }
        }).addSink(new SinkFunction<HashMap<String, Integer>>() {
            @Override
            public void invoke(HashMap<String, Integer> value, Context context) throws Exception {
                Integer sum = value.values().stream().mapToInt(v -> v).sum();
                logger.info("TradeMain: {}", sum);
                System.out.println(sum);
            }
        });
        //AggregateFunction
        //ds.addSink(new TradeSink());
        env.execute();
    }
}
