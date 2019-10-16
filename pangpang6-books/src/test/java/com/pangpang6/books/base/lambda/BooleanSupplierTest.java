package com.pangpang6.books.base.lambda;

import org.junit.Test;

import java.util.function.BooleanSupplier;

/**
 * Created by jiangjiguang on 2018/1/2.
 */
public class BooleanSupplierTest {

    @Test
    public void getAsBooleanTest(){
        BooleanSupplier b = () -> true;

        System.out.println(b.getAsBoolean());
    }
}
