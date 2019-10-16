package com.pangpang6.books.base.lambda;

import org.junit.Test;

import java.util.function.Supplier;

/**
 * Created by jiangjiguang on 2018/1/2.
 */
public class SupplierTest {
    @Test
    public void getTest(){
        Supplier<String> s = () -> "test";

        System.out.println(s.get());
    }
}
