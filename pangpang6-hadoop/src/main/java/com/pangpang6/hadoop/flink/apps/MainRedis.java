package com.pangpang6.hadoop.flink.apps;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

public class MainRedis {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> streamSource = env.socketTextStream("node1", 9999);
        DataStream<Tuple2<String, String>> wordData = streamSource.map(new MapFunction<String, Tuple2<String, String>>() {
            @Override
            public Tuple2<String, String> map(String value) throws Exception {
                return new Tuple2<>("l_" + value, value);
            }
        });

        FlinkJedisPoolConfig config = new FlinkJedisPoolConfig.Builder().setHost("node1").setPort(6379).build();
        RedisSink<Tuple2<String, String>> redisSink = new RedisSink<>(config, new MyRedisMapper());
        wordData.addSink(redisSink);
        env.execute(MainRedis.class.getName());
    }

    public static class MyRedisMapper implements RedisMapper<Tuple2<String, String>> {

        @Override
        public RedisCommandDescription getCommandDescription() {
            return new RedisCommandDescription(RedisCommand.LPUSH);
        }

        @Override
        public String getKeyFromData(Tuple2<String, String> data) {
            return data.f0;
        }

        @Override
        public String getValueFromData(Tuple2<String, String> data) {
            return data.f1;
        }
    }
}
