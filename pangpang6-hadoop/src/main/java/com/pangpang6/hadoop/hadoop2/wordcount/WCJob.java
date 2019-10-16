package com.pangpang6.hadoop.hadoop2.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by jiangjiguang on 2018/3/22.
 */
public class WCJob {
    private static final Logger logger = LoggerFactory.getLogger(WCJob.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        System.out.println("start!");
        logger.info("start log");

        Configuration conf = new Configuration();


        Job job = Job.getInstance(conf);
        job.setJarByClass(WCJob.class);
        job.setMapperClass(WCMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(WCReducer.class);

        FileInputFormat.addInputPath(job, new Path("/usr/input/wc"));

        Path outPath = new Path("/usr/output/wc");
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(outPath)) {
            fs.delete(outPath, true);
        }
        FileOutputFormat.setOutputPath(job, outPath);

        boolean flag = job.waitForCompletion(true);
        if (flag) {
            System.out.println("finished!");
        }
        System.out.println("end!");

    }
}
