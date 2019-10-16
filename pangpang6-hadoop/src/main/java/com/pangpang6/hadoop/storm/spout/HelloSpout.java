package com.pangpang6.hadoop.storm.spout;

import com.google.common.collect.Maps;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HelloSpout extends BaseRichSpout {

    private SpoutOutputCollector collector;
    private static final Map<Integer, String> map = Maps.newHashMap();

    static {
        map.put(0, "java");
        map.put(1, "php");
        map.put(2, "groovy");
        map.put(3, "python");
        map.put(4, "ruby");
    }

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void nextTuple() {
        final Random random = new Random();
        int run = random.nextInt(5);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.collector.emit(new Values(map.get(run)));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("hello"));
    }
}
