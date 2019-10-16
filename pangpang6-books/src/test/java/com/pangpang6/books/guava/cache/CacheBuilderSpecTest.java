package com.pangpang6.books.guava.cache;

import com.google.common.cache.CacheBuilderSpec;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/17.
 */
public class CacheBuilderSpecTest {
    @Test
    public void parseTest(){
        CacheBuilderSpec spec = CacheBuilderSpec.parse("");
        System.out.println(spec);
    }
}
