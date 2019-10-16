package com.pangpang6.hadoop.hadoop2.fof;

import com.pangpang6.utils.MyJSONMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by jiangjiguang on 2018/3/27.
 */
public class FofMapperOne extends Mapper<LongWritable, Text, Text, IntWritable> {
    private static final Logger logger = LoggerFactory.getLogger(FofMapperOne.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        logger.info(String.format("fofmap: %s, %s", key.get(), value.toString()));

        String[] strs = StringUtils.split(value.toString(), " ");
        logger.info(String.format("fofmap: %s", strs.length));
        logger.info(String.format("fofmap: %s, %s", value.toString(), MyJSONMapper.nonDefaultMapper().toJSONString(strs)));

        for (int i = 0; i < strs.length; i++) {
            //直接好友关系
            String s1 = FofUtils.format(strs[0], strs[i]);
            context.write(new Text(s1), new IntWritable(0));
            for (int j = i + 1; j < strs.length; j++) {
                //二度关系
                String s2 = FofUtils.format(strs[i], strs[j]);
                IntWritable intWritable = new IntWritable(1);
                logger.info(String.format("fofmap result: %s,%s", s2, intWritable.get()));
                context.write(new Text(s2), intWritable);
            }
        }
    }
}
