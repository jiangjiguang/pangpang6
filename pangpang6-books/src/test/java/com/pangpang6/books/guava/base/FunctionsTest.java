package com.pangpang6.books.guava.base;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class FunctionsTest {

    @Test
    public void constantTest(){
        Function<Object, String> f = Functions.constant("1234");

        String ss = f.apply("1111");
        System.out.println(ss);
    }


    @Test
    public void composeTest(){
        Function<String, String> f1 = x -> x + 1;
        Function<String, String> f2 = x -> x + 2;

        Function<String, String> f3 = Functions.compose(f1, f2);
        System.out.println(f3.apply("aaa"));
    }

    @Test
    public void forMapTest(){
        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");

        Function<String, String> f4 = Functions.forMap(map);
        System.out.println(f4.apply("k1"));

        Function<String, String> f5 = Functions.forMap(map, "aaa");
        System.out.println(f5.apply("kkkk"));

    }
}
