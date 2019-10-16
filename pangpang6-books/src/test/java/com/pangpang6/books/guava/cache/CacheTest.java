package com.pangpang6.books.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjiguang on 2017/12/14.
 */
public class CacheTest {
    Cache<Integer, String> cache = CacheBuilder.newBuilder()
            .maximumSize(300)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build();

    @Test
    public void asMapTest(){
        cache.put(2, "33");
        cache.put(3, "444");
        Map<Integer, String> map = cache.asMap();
        System.out.println(map);
    }

    @Test
    public void getAllPresentTest(){
        cache.put(2, "33");
        cache.put(3, "444");
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(4);
        Map<Integer, String> r = cache.getAllPresent(list);
        System.out.println(r);
    }

    @Test
    public void cacheTest() throws InterruptedException {
        System.out.println(this.getName(3));

        TimeUnit.SECONDS.sleep(3);
        System.out.println(this.getName(3));

        TimeUnit.SECONDS.sleep(3);
        System.out.println(this.getName(3));
        TimeUnit.SECONDS.sleep(3);
        System.out.println(this.getName(3));
    }

    @Test
    public void sizeTest(){
        cache.put(2, "33");
        cache.put(3, "444");
        System.out.println(cache.size());
    }

    @Test
    public void statsTest(){
        CacheStats cacheStats = cache.stats();
        System.out.println(cacheStats);
    }


    private String getName(final int cityId){
        String result = "1234";
        try {
            result = cache.get(cityId, new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("call");

                    int a = 0;
                    //int b = 1/a;

                    return "testaa";
                }
            });
        } catch (Exception ex) {
            System.out.println(ExceptionUtils.getFullStackTrace(ex));
        }
        return result;
    }
}
