package com.pangpang6.books.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by jiangjiguang on 2018/2/6.
 */
public class ThreadPoolExecutorTest {
    private static final int THREAD_SUM = Runtime.getRuntime().availableProcessors();

    private static final ThreadFactory ioThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("test-thread-%d")
            .build();

    private static ThreadPoolExecutor executor = null;

    @Before
    public void init() {
        executor = new ThreadPoolExecutor(THREAD_SUM / 2, THREAD_SUM,
                5, SECONDS, new LinkedBlockingQueue<>(1), ioThreadFactory);
    }


    class MyRunnable implements Runnable {
        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            int a = 0;
            int b = 100 / a;
            System.out.println(String.format("%s--%s---start", Thread.currentThread().getName(), name));
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%s--%s---end", Thread.currentThread().getName(), name));
        }
    }

    @Test
    public void test1() throws InterruptedException {
        executor.execute(new MyRunnable("test01"));

        TimeUnit.SECONDS.sleep(1);
        System.out.println("---先开一个---");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("---第二个---");
        executor.execute(new MyRunnable("test01"));
        executor.execute(new MyRunnable("test01"));
        executor.execute(new MyRunnable("test01"));
        executor.execute(new MyRunnable("test01"));
        executor.execute(new MyRunnable("test01"));
        executor.execute(new MyRunnable("test01"));
        executor.execute(new MyRunnable("test01"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());
        System.out.println("---第三个---");
        TimeUnit.SECONDS.sleep(3);

        executor.execute(new MyRunnable("test01"));
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());

        Thread.sleep(8000);
        System.out.println("---最终结果---");

        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());
    }


}
