package com.pangpang6.books.base.lambda;

import org.junit.Test;

import java.util.function.BiFunction;

/**
 * Created by jiangjiguang on 2018/1/2.
 */
public class BiFunctionTest {
    @Test
    public void applyTest(){
        BiFunction<String, Integer, Boolean> bf = (String s, Integer i) -> s.length() > i;
        boolean r = bf.apply("1234", 3);
        System.out.println(r);
    }
}
