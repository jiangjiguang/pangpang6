package com.pangpang6.hadoop.hadoop2.tq;

import com.google.common.primitives.Ints;
import com.pangpang6.utils.MyDateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jiangjiguang on 2018/3/25.
 */
public class TQMapper extends Mapper<LongWritable, Text, Weather, IntWritable> {
    private static final Logger logger = LoggerFactory.getLogger(TQMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        logger.info(String.format("----map: key=%s, value=%s", key.get(), value.toString()));

        String[] strs = StringUtils.split(value.toString(), "\t");
        Date date = MyDateUtils.str2Date(strs[0]);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        Weather weather = new Weather();

        weather.setYear(calendar.get(Calendar.YEAR));
        weather.setMonth(calendar.get(Calendar.MONTH));
        weather.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        int wd = Ints.tryParse(strs[1]) == null ? 0 : Ints.tryParse(strs[1]);
        weather.setWd(wd);

        context.write(weather, new IntWritable(wd));

    }
}
