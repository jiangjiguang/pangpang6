package com.pangpang6.utils.cache;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjg on 2017/2/17.
 */
public class GuavaCache {
    public static void main(String[] args) {
        Cache<String, Object> cache;
        cache = CacheBuilder.newBuilder()
                .maximumSize(300)
                .expireAfterWrite(24, TimeUnit.HOURS)
                .build();
        Object existFlag;
        try{
            existFlag = cache.get("", new Callable<Object>() {
                @Override
                public Boolean call() throws Exception {
                    return get();
                }
            });
        }catch (Exception ex){

        }
    }

    public static Boolean get(){
        return true;
    }
}
