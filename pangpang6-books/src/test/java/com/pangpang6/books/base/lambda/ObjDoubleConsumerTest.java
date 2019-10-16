package com.pangpang6.books.base.lambda;

import org.junit.Test;

import java.util.function.ObjDoubleConsumer;

/**
 * Created by jiangjiguang on 2018/1/2.
 */
public class ObjDoubleConsumerTest {
    @Test
    public void acceptTest() {
        ObjDoubleConsumer<String> i = (s, d) -> System.out.println(s + d);
        i.accept("123", 0.333);
    }

}
