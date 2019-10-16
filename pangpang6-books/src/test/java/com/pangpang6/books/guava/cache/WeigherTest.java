package com.pangpang6.books.guava.cache;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjiguang on 2017/12/17.
 */
public class WeigherTest {
    CacheLoader<Integer, String> loader = CacheLoader.from(key -> {
        return key.toString();
    });

    LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
            .maximumWeight(10000)
            //.maximumSize(300)

            .expireAfterWrite(30, TimeUnit.SECONDS)
            .removalListener(new RemovalListener<Integer, String>() {
                @Override
                public void onRemoval(RemovalNotification<Integer, String> notification) {
                    System.out.println("onRemoval" + notification.getKey());

                }
            })
            .weigher(new Weigher<Integer, String>() {
                @Override
                public int weigh(Integer key, String value) {
                    return key * 500;
                }
            })
            .build(loader);

    @Test
    public void test() throws Exception {
        for (int i = 1; i < 5; i++) {
            String r = cache.get(i);

            System.out.println("result" + r);
        }

        TimeUnit.SECONDS.sleep(5);
    }
}
