package com.pangpang6.hadoop.flink.apps;

import com.google.common.collect.Lists;
import com.pangpang6.hadoop.common.ClickBean;
import com.pangpang6.hadoop.flink.common.Constants;
import com.pangpang6.utils.MyDateUtils;
import com.pangpang6.utils.MyJSONMapper;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.util.*;

public class MainWatermarkKafka {
    public static void main(final String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //设置流的时间特性
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.setParallelism(1);

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", Constants.KAFKA_BROKERS);
        properties.setProperty("group.id", Constants.GROUP_ID_CONFIG);
        FlinkKafkaConsumer kafkaConsumer = new FlinkKafkaConsumer<String>(Constants.TOPIC_NAME, new SimpleStringSchema(), properties);
        //默认消费策略
        kafkaConsumer.setStartFromGroupOffsets();

        DataStreamSource<String> text = env.addSource(kafkaConsumer);

        //解析数据
        DataStream<ClickBean> inputMap = text.map(new MapFunction<String, ClickBean>() {
            @Override
            public ClickBean map(String value) throws Exception {
                MyJSONMapper myJSONMapper = MyJSONMapper.nonEmptyMapper();
                return myJSONMapper.parseObject(value, ClickBean.class);
            }
        });

        //生成watermark
        DataStream<ClickBean> waterMarkStream = inputMap.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<ClickBean>() {
            Long currentMaxTimestamp = 0L;
            Long maxOutOfOrder = 10000L;

            //定义生成watermark的逻辑，默认每100ms调用一次
            @Nullable
            @Override
            public Watermark getCurrentWatermark() {
                //System.out.println("==========> getCurrentWatermark: " + currentMaxTimestamp);
                return new Watermark(currentMaxTimestamp - maxOutOfOrder);
            }

            //定义如何提取 timestamp
            @Override
            public long extractTimestamp(ClickBean clickBean, long previousElementTimestamp) {
                long timestamp = MyDateUtils.str2Date(clickBean.getDt()).getTime();
                currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);
                //System.out.println("===========> extractTimestamp: " + MyJSONMapper.nonEmptyMapper().toJSONString(clickBean));
                return timestamp;
            }
        });

        //分组，聚合
        DataStream<String> window = waterMarkStream.keyBy("type")
                //按照消息的EventTime 分配窗口，和调用TimeWindow效果一样
                .timeWindow(Time.seconds(3))
                .apply(new WindowFunction<ClickBean, String, Tuple, TimeWindow>() {
                    @Override
                    public void apply(Tuple tuple, TimeWindow window, Iterable<ClickBean> input, Collector<String> out) throws Exception {
                        String key = tuple.toString();
                        List<ClickBean> list = Lists.newArrayList();
                        Iterator<ClickBean> iterator = input.iterator();
                        while (iterator.hasNext()) {
                            list.add(iterator.next());
                        }
                        Collections.sort(list);

                        System.out.println("------>");
                        System.out.println(String.format("key: %s, size: %s, start: %s, end: %s, winStart: %s, winEnd: %s",
                                key, list.size(),
                                list.get(0).getDt(), list.get(list.size() - 1).getDt(),
                                MyDateUtils.date2StrDefult(new Date(window.getStart())),
                                MyDateUtils.date2StrDefult(new Date(window.getEnd()))));
                        out.collect(MyJSONMapper.nonDefaultMapper().toJSONString(list));
                    }
                });
        //测试-把结果打印到控制台即可
        window.print();
        //注意:因为 flink 是懒加载的，所以必须调用 execute 方法，上面的代码才会执行
        env.execute("eventtime-watermark");

    }
}
