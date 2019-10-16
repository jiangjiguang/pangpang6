package com.pangpang6.hadoop.flink.source;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TradeSource extends RichParallelSourceFunction<Tuple2<String, Integer>> {

    private volatile boolean running = true;

    @Override
    public void run(SourceContext<Tuple2<String, Integer>> sourceContext) throws Exception {
        Random random = new Random(System.currentTimeMillis());
        while (running) {
            int indexSubTask = getRuntimeContext().getIndexOfThisSubtask();
            TimeUnit.MILLISECONDS.sleep((indexSubTask + 1) * 1000 + 500);
            String key = String.format("类别%s", (char) ('A' + random.nextInt(3)));
            Integer value = random.nextInt(10) + 1;
            //System.out.println(String.format("Start subTask: %d", indexSubTask));
            System.out.println(String.format("Emit: \t(%s, %d)", key, value));
            sourceContext.collect(new Tuple2<>(key, value));
        }
    }

    @Override
    public void cancel() {
        running = false;
    }
}
