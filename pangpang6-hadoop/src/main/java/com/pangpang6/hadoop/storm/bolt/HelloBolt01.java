package com.pangpang6.hadoop.storm.bolt;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloBolt01 extends BaseBasicBolt {
    private static final Logger logger = LoggerFactory.getLogger(HelloBolt01.class);

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String hello = input.getStringByField("hello");
        logger.info("hello01: " + hello);
        collector.emit(new Values(hello));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("hello01"));
    }
}
