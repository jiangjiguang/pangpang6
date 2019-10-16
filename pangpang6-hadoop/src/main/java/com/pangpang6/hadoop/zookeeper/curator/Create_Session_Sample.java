package com.pangpang6.hadoop.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by jiangjiguang on 2018/2/13.
 */
public class Create_Session_Sample {
    public static void main(String[] args) throws Exception{
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.newClient(Constants.ips,
                        5000,
                        3000,
                        retryPolicy);
        client.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
