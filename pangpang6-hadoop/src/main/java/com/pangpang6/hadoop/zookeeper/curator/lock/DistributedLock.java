package com.pangpang6.hadoop.zookeeper.curator.lock;

import com.pangpang6.hadoop.zookeeper.curator.Constants;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjiguang on 2018/3/26.
 */
public class DistributedLock {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static int count = 0;

    public static void main(String[] args) throws Exception {
        //unsafe();
        safe();
    }

    private static void unsafe() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new MyThread2());
            thread.start();
        }
        countDownLatch.countDown();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("--------------------" + count);
    }


    private static void safe() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new MyThread());
            thread.start();
        }

        countDownLatch.countDown();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("--------------------" + count);
    }


    static class MyThread implements Runnable {
        @Override
        public void run() {
            //创建zookeeper的客户端
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            CuratorFramework client = CuratorFrameworkFactory.newClient(Constants.ips, retryPolicy);
            client.start();
            try {
                //创建分布式锁, 锁空间的根节点路径为/curator/lock
                InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");
                mutex.acquire();
                //获得了锁, 进行业务流程

                for (int i = 0; i < 10000; i++) {
                    count++;
                }
                TimeUnit.SECONDS.sleep(5);

                //完成业务流程, 释放锁
                mutex.release();

                //关闭客户端
                client.close();
            } catch (Exception ex) {
                System.out.println(ExceptionUtils.getFullStackTrace(ex));
            }

        }
    }

    static class MyThread2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        }
    }
}
