package com.pangpang6.books.guava.cache;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjiguang on 2017/12/18.
 */
public class ForwardingCacheTest {

    @Test
    public void delegateTest() throws InterruptedException, ExecutionException {
        Cache<Integer, String> cache = new MyForwardingCache();
        String ss = cache.get(2, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "333";
            }
        });
        System.out.println(ss);
    }





    public class MyForwardingCache extends ForwardingCache<Integer, String> {

        CacheLoader<Integer, String> loader = CacheLoader.from(key -> {
            System.out.println("load");
            return key.toString();
        });

        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .maximumSize(300)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build(loader);

        @Override
        protected Cache delegate() {
            System.out.println("delegate");
            return cache;
        }
    }
}
