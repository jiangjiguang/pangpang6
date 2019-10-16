package com.pangpang6.books.guava.cache;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjiguang on 2017/12/16.
 */
public class RemovalListenerTest {
    CacheLoader<Integer, String> loader = CacheLoader.from(key -> {
        System.out.println("load");
        return key.toString();
    });

    LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
            .maximumSize(300)
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .removalListener(new RemovalListener<Integer, String>() {
                @Override
                public void onRemoval(RemovalNotification<Integer, String> notification) {
                    System.out.println("--------------------");
                    System.out.println(notification.getKey());
                    System.out.println(notification.getValue());
                    System.out.println(notification.getCause());
                }
            })
            .build(loader);

    @Test
    public void onRemovalTest() throws Exception {
        String result = cache.get(2);
        System.out.println(result);
        TimeUnit.SECONDS.sleep(2);
        result = cache.get(2);
        System.out.println(result);
        TimeUnit.SECONDS.sleep(2);
        //只有对缓存再次操作时才会移除
        result = cache.get(2);
        System.out.println(result);

        TimeUnit.SECONDS.sleep(10);
    }
}
