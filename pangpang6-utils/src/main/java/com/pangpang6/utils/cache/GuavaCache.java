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
        Cache<String, Boolean> cache;
        cache = CacheBuilder.newBuilder()
                .maximumSize(5000)
                .expireAfterWrite(24, TimeUnit.HOURS)
                .build();
        Boolean existFlag;
        try{
            existFlag = cache.get("", new Callable<Boolean>() {
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
