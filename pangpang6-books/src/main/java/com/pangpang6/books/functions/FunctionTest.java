package com.pangpang6.books.functions;

import java.util.function.Function;

/**
 * Created by jiangjiguang on 2017/12/10.
 */
public class FunctionTest {
    public static void main(String[] args) {
        test();
    }

    private static void test(){
        Function<Integer, Integer> times2 = e -> e * 2;

        Function<Integer, Integer> squared = e -> e * e;
        int i1= times2.compose(squared).apply(4);
        // Returns 32
        System.out.println(i1);
        int i2 = times2.andThen(squared).apply(4);
        // Returns 64
        System.out.println(i2);
    }
}
