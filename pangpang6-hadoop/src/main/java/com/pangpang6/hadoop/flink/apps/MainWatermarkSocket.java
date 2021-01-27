package com.pangpang6.hadoop.flink.apps;

import com.google.common.collect.Lists;
import com.pangpang6.utils.MyDateUtils;
import com.pangpang6.utils.MyJSONMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainWatermarkSocket {
    public static void main(final String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //设置流的时间特性
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        //设置水印获取周期
        //env.getConfig().setAutoWatermarkInterval(50000);

        env.setParallelism(1);

        DataStreamSource<String> text = env.socketTextStream("localhost", 9999);

        //解析数据
        DataStream<Tuple2<String, Long>> inputMap = text.flatMap(new FlatMapFunction<String, Tuple2<String, Long>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Long>> out) throws Exception {
                if (StringUtils.isBlank(value)) {
                    return;
                }
                String[] arr = value.split(",");
                Date date = MyDateUtils.str2Date(arr[1]);
                out.collect(new Tuple2<>(arr[0], date.getTime()));
            }
        });

        //抽取timestamp和生成watermark
        DataStream<Tuple2<String, Long>> waterMarkStream = inputMap.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<Tuple2<String, Long>>() {
            Long currentMaxTimestamp = 0L;
            // 最大允许的乱序时间是
            final Long maxOutOfOrder = 5000L;

            /**
             * 定义生成watermark的逻辑 & 默认100ms被调用一次
             */
            @Nullable
            @Override
            public Watermark getCurrentWatermark() {
                Watermark watermark = new Watermark(currentMaxTimestamp - maxOutOfOrder);
                //System.out.println("getCurrentWatermark: " + MyDateUtils.date2StrDefult(new Date()));
                //System.out.println("getCurrentWatermark: " + MyJSONMapper.nonEmptyMapper().toJSONString(watermark));
                return watermark;
            }

            //定义如何提取timestamp
            @Override
            public long extractTimestamp(Tuple2<String, Long> element, long previousElementTimestamp) {
                List<String> content = Lists.newArrayList();
                long timestamp = element.f1;
                currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);
                content.add(String.format("threadId: %s", Thread.currentThread().getId()));
                content.add(String.format("key: %s", element.f0));
                content.add(String.format("eventTime: %s", MyDateUtils.date2StrDefult(element.f1)));
                content.add(String.format("currentMaxTimestamp: %s", MyDateUtils.date2StrDefult(currentMaxTimestamp)));
                content.add(String.format("waterMark: %s", MyDateUtils.date2StrDefult(getCurrentWatermark().getTimestamp())));
                System.out.println(String.format("======> %s", MyJSONMapper.nonEmptyMapper().toJSONString(content)));
                return timestamp;
            }
        });

        //分组，聚合
        SingleOutputStreamOperator<String> window = waterMarkStream.keyBy(0)
                .window(SlidingEventTimeWindows.of(Time.seconds(2), Time.seconds(3)))
                //.window(TumblingEventTimeWindows.of(Time.seconds(3)))
                //.allowedLateness(Time.seconds(2)) //允许数据迟到2秒
                .apply(new WindowFunction<Tuple2<String, Long>, String, Tuple, TimeWindow>() {

                    @Override
                    public void apply(Tuple tuple, TimeWindow window, Iterable<Tuple2<String, Long>> input, Collector<String> out) throws Exception {

                        System.out.println("---------------------");
                        String w = String.format("window: %s~%s",
                                MyDateUtils.date2StrDefult(window.getStart()),
                                MyDateUtils.date2StrDefult(window.getEnd()));
                        System.out.println(MyDateUtils.date2StrDefult(new Date()) + ", start window: " + w);
                        TimeUnit.SECONDS.sleep(10);
                        String key = tuple.toString();
                        List<String> content = Lists.newArrayList();
                        Iterator<Tuple2<String, Long>> it = input.iterator();
                        while (it.hasNext()) {
                            Tuple2<String, Long> next = it.next();
                            content.add(MyDateUtils.date2StrDefult(new Date(next.f1)));
                        }
                        Collections.sort(content);
                        List<String> resultList = Lists.newArrayList();
                        resultList.add(String.format("key: %s", key));
                        resultList.add(String.format("content: %s", MyJSONMapper.nonDefaultMapper().toJSONString(content)));
                        resultList.add(String.format("window: %s~%s",
                                MyDateUtils.date2StrDefult(window.getStart()),
                                MyDateUtils.date2StrDefult(window.getEnd())));
                        String result = MyJSONMapper.nonDefaultMapper().toJSONString(resultList);
                        out.collect(result);
                    }
                });

        //可以把迟到的地方存到其它的地方
        //DataStream<Tuple2<String, Long>> dataStream = window.getSideOutput(outputTag);
        //dataStream.addSink()
        //dataStream.print();
        //测试-把结果打印到控制台即可
        window.print();
        //注意:因为 flink 是懒加载的，所以必须调用 execute 方法，上面的代码才会执行
        env.execute("eventtime-watermark");

    }
}
