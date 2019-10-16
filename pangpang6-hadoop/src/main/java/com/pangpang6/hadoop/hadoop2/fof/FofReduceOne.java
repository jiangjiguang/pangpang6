package com.pangpang6.hadoop.hadoop2.fof;

import com.pangpang6.utils.MyJSONMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by jiangjiguang on 2018/3/27.
 */
public class FofReduceOne extends Reducer<Text, IntWritable, Text, NullWritable> {
    private static final Logger logger = LoggerFactory.getLogger(FofReduceOne.class);

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        logger.info(String.format("reduce: %s, %s", key.toString(), MyJSONMapper.nonDefaultMapper().toJSONString(values.iterator())));

        //亲密度
        int sum = 0;
        boolean flag = true;
        for (IntWritable i : values) {
            logger.info(String.format("reduceitem: %s, %s", key.toString(), i.get()));
            int tmp = i.get();
            if (tmp == 0) {
                flag = false;
                break;
            }
            sum = sum + tmp;
        }
        if (flag) {
            String msg = String.format("%s-%s", key.toString(), sum);
            context.write(new Text(msg), NullWritable.get());
        }
    }
}
