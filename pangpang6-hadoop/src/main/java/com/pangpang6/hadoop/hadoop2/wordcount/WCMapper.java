package com.pangpang6.hadoop.hadoop2.wordcount;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by jiangjiguang on 2018/3/22.
 */
public class WCMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String str = value.toString();
        String[] strs = StringUtils.split(str, ' ');
        for (String s : strs) {
            context.write(new Text(s), new IntWritable(1));
        }
    }
}
