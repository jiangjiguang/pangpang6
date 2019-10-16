package com.pangpang6.hadoop.hadoop2.tq;

import com.pangpang6.utils.MyJSONMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by jiangjiguang on 2018/3/25.
 */
public class TQReducer extends Reducer<Weather, IntWritable, Text, NullWritable> {
    private static final Logger logger = LoggerFactory.getLogger(TQReducer.class);


    @Override
    protected void reduce(Weather key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        logger.info(String.format("reduce: key=%s, values", key.toString(), MyJSONMapper.nonDefaultMapper().toJSONString(values)));

        int flag = 0;

        for (IntWritable i : values) {
            flag++;
            if (flag > 32) {
                break;
            }
            String msg = key.toString() + "-" + i.get();

            context.write(new Text(msg), NullWritable.get());
        }


    }
}
