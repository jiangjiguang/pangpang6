package com.pangpang6.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Created by jiangjiguang on 2018/3/12.
 */
public class HdfsDemo {
    FileSystem fs;

    public static void main(String[] args) throws IOException {
        HdfsDemo demo = new HdfsDemo();
        demo.begin();
        demo.mkdir();
        demo.end();
    }

    public void mkdir() {
        Path path = new Path("/aaaa");
        try {
            boolean b = fs.exists(path);
            System.out.println(b);
            boolean b2 = fs.mkdirs(path);
            System.out.println(b2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void begin() throws IOException {
        //默认加载src目录下的配置文件
        Configuration conf = new Configuration();
        fs = FileSystem.get(conf);
    }

    public void end() throws IOException {
        fs.close();
    }
}
