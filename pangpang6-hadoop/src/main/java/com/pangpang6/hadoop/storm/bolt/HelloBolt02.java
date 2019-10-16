package com.pangpang6.hadoop.storm.bolt;

import org.apache.commons.io.FileUtils;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class HelloBolt02 extends BaseBasicBolt {
    private static final Logger logger = LoggerFactory.getLogger(HelloBolt02.class);

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String hello02 = input.getStringByField("hello01");
        logger.info("hello02: " + hello02);
        try {
            FileUtils.write(new File("/opt/apps/storm/data/" + this), hello02, "urf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
