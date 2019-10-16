package com.pangpang6.books.guava.cache;

import com.google.common.base.Stopwatch;
import com.google.common.cache.*;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjiguang on 2017/12/16.
 */
public class CacheBuilderTest {


    @Test
    public void concurrencyLevelTest() throws ExecutionException, InterruptedException {
        Cache<Integer, String> cache = CacheBuilder.newBuilder()
                .maximumSize(300)
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .concurrencyLevel(3)
                .build();

        System.out.println(cache.get(1, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "2";
            }
        }));
        System.out.println(cache.getIfPresent(1));
        TimeUnit.SECONDS.sleep(2);
        System.out.println(cache.getIfPresent(1));
        TimeUnit.SECONDS.sleep(2);
        System.out.println(cache.getIfPresent(1));

    }

    @Test
    public void fromTest() throws Exception {
        CacheBuilderSpec spec = CacheBuilderSpec.parse("");
        //CacheBuilderSpec spec = CacheBuilderSpec.parse("expireAfterWrite=3s");

        Cache cache = CacheBuilder.from(spec).build();
        /*
        Cache<Integer, String> cache = CacheBuilder.newBuilder()
                .maximumSize(300)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .concurrencyLevel(3)
                .build();
                */
        System.out.println(cache.get(1, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "2";
            }
        }));
        TimeUnit.SECONDS.sleep(2);
        System.out.println(cache.getIfPresent(1));
        TimeUnit.SECONDS.sleep(2);
        System.out.println(cache.getIfPresent(1));
    }



    // 模拟一个需要耗时2s的数据库查询任务
    private static Callable<String> callable = new Callable<String>() {
        @Override
        public String call() throws Exception {
            System.out.println("begin to mock query db...");
            Thread.sleep(2000);
            System.out.println("success to mock query db...");
            return UUID.randomUUID().toString();
        }
    };

    /*
    // 1s后刷新缓存
    private static LoadingCache<String, String> cache = CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return callable.call();
                }
            });
            */
    private static LoadingCache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return callable.call();
                }
            });
    private static CountDownLatch latch = new CountDownLatch(1);

    //使用refreshAfterWrite可以做到：只阻塞加载数据的线程，其余线程返回旧数据。
    //若过期之前没值，则阻塞所有线程；若有值则只阻塞加载数据的线程
    //若是把refreshAfterWrite改为expireAfterWrite，则阻塞所有线程
    @Test
    public void refreshAfterWriteTest() throws InterruptedException {
        // 手动添加一条缓存数据,睡眠1.5s让其过期
        cache.put("name", "aty");
        Thread.sleep(1500);

        for (int i = 0; i < 8; i++) {
            startThread(i);
        }

        // 让线程运行
        latch.countDown();

        //不让主线程结束
        TimeUnit.SECONDS.sleep(10);
    }

    private static void startThread(int id) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "...begin");
                    latch.await();
                    Stopwatch watch = Stopwatch.createStarted();
                    System.out.println(Thread.currentThread().getName() + "...value..." + cache.get("name"));
                    watch.stop();
                    System.out.println(Thread.currentThread().getName() + "...finish,cost time=" + watch.elapsed(TimeUnit.SECONDS));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(false);
        t.setName("Thread-" + id);
        t.start();
    }

}
