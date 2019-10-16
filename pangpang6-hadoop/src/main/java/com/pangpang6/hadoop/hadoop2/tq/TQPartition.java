package com.pangpang6.hadoop.hadoop2.tq;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jiangjiguang on 2018/3/25.
 */
public class TQPartition extends HashPartitioner<Weather, IntWritable> {
    private static final Logger logger = LoggerFactory.getLogger(TQPartition.class);


    //默认实现就是key的哈希值 取模 reduce个数
    @Override
    public int getPartition(Weather key, IntWritable value, int numReduceTasks) {
        logger.info(String.format("----getPartition: key=%s, value=%s", key.toString(), value.get()));
        //重写partition的规则
        return (key.getYear() - 1949) % numReduceTasks;
    }
}
