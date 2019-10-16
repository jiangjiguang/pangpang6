package com.pangpang6.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.AclStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by jiangjiguang on 2018/3/12.
 */
public class HdfsDemo {
    FileSystem fs;


    @Test
    public void mkdir() {
        Path path = new Path("/usr/aaa");
        try {
            boolean b = fs.exists(path);
            System.out.println(b);
            boolean b2 = fs.mkdirs(path);
            fs.setOwner(path, "root", "supergroup");
            System.out.println(b2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Before
    public void begin() throws IOException {
        //默认加载src目录下的配置文件
        Configuration conf = new Configuration();
        conf.addResource(new Path("/Users/jiangjiguang/Documents/share/core-site.xml"));
        conf.addResource(new Path("/Users/jiangjiguang/Documents/share/hdfs-site.xml"));
        fs = FileSystem.get(conf);
    }

    @After
    public void end() throws IOException {
        fs.close();
    }
}
