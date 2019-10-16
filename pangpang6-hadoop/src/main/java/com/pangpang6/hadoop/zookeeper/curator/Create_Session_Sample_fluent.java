package com.pangpang6.hadoop.zookeeper.curator;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

//使用Fluent风格的API接口来创建一个ZooKeeper客户端
public class Create_Session_Sample_fluent {
    public static void main(String[] args) throws Exception{
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
        CuratorFrameworkFactory.builder()
                             .connectString(Constants.ips)
                             .sessionTimeoutMs(5000)
                             .retryPolicy(retryPolicy)
                             .build();
        client.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}