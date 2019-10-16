package com.pangpang6.hadoop.flink.apps;

import com.pangpang6.hadoop.common.ClickBean;
import com.pangpang6.hadoop.flink.common.Constants;
import com.pangpang6.hadoop.flink.partitioners.MyFlinkKafkaPartitioner;
import com.pangpang6.utils.MyJSONMapper;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.util.serialization.KeyedSerializationSchemaWrapper;

import java.util.Optional;
import java.util.Properties;

public class MainKafka {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        int parallelism = env.getParallelism();
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", Constants.KAFKA_BROKERS);
        properties.setProperty("group.id", Constants.GROUP_ID_CONFIG);

        FlinkKafkaConsumer kafkaConsumer = new FlinkKafkaConsumer<String>(Constants.TOPIC_NAME, new SimpleStringSchema(), properties);
        //默认消费策略
        kafkaConsumer.setStartFromGroupOffsets();


        Properties properties1 = new Properties();
        properties1.setProperty("bootstrap.servers", Constants.KAFKA_BROKERS);
        Optional optional = Optional.of(new MyFlinkKafkaPartitioner());
        FlinkKafkaProducer kafkaProducer = new FlinkKafkaProducer("flink-output-p2", new KeyedSerializationSchemaWrapper(new SimpleStringSchema()), properties1, optional);

        DataStream<String> stream = env
                .addSource(kafkaConsumer)
                .map(new MapFunction<String, ClickBean>() {
                    @Override
                    public ClickBean map(String value) throws Exception {
                        MyJSONMapper myJSONMapper = MyJSONMapper.nonEmptyMapper();
                        return myJSONMapper.parseObject(value, ClickBean.class);
                    }
                })
                .keyBy(new KeySelector<ClickBean, Integer>() {
                    @Override
                    public Integer getKey(ClickBean value) throws Exception {
                        return value.getType();
                    }
                }).map(new MapFunction<ClickBean, String>() {
                    @Override
                    public String map(ClickBean value) throws Exception {
                        MyJSONMapper myJSONMapper = MyJSONMapper.nonEmptyMapper();
                        return myJSONMapper.toJSONString(value);
                    }
                });
        //stream.print();
        stream.addSink(kafkaProducer);

        env.execute(MainKafka.class.getName());
    }
}
