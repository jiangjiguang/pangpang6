package com.pangpang6.hadoop.hadoop2.fof;

import com.pangpang6.hadoop.hadoop2.wordcount.WCJob;
import com.pangpang6.hadoop.hadoop2.wordcount.WCMapper;
import com.pangpang6.hadoop.hadoop2.wordcount.WCReducer;
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
 * Created by jiangjiguang on 2018/3/27.
 */
public class FofJobOne {
    private static final Logger logger = LoggerFactory.getLogger(FofJobOne.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();


        Job job = Job.getInstance(conf);
        job.setJarByClass(FofJobOne.class);

        job.setMapperClass(FofMapperOne.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(FofReduceOne.class);

        FileInputFormat.addInputPath(job, new Path("/usr/input/fof1"));

        Path outPath = new Path("/usr/output/fof1");
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
