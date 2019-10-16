package com.pangpang6.books.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjiguang on 2017/12/16.
 */
public class LoadingCacheTest {

    CacheLoader<Integer, String> loader = CacheLoader.from(key -> {
        System.out.println("load");
        return key.toString();
    });

    LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
            .maximumSize(300)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build(loader);

    @Test
    public void refreshTest() {
        try {
            String r = cache.get(1);
            System.out.println(r);
            System.out.println(cache.get(444));
            System.out.println(cache.get(444));
            cache.refresh(444);
            System.out.println(cache.get(444));


        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
