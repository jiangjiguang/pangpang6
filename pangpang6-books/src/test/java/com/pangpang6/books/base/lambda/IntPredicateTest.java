package com.pangpang6.books.base.lambda;

import org.junit.Test;

import java.util.function.IntPredicate;

/**
 * Created by jiangjiguang on 2018/1/2.
 */
public class IntPredicateTest {
    @Test
    public void test(){
        IntPredicate ip = (int i) -> i % 2 == 0;
        boolean r = ip.test(2);
        System.out.println(r);
    }
}
