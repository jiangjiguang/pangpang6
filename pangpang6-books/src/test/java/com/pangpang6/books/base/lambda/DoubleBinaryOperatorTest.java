package com.pangpang6.books.base.lambda;

import org.junit.Test;

import java.util.function.DoubleBinaryOperator;

/**
 * Created by jiangjiguang on 2018/1/2.
 */
public class DoubleBinaryOperatorTest {

    @Test
    public void applyAsDoubleTest() {
        DoubleBinaryOperator d = (x, y) -> x * y;
        System.out.println(d.applyAsDouble(0.23, 0.45));
    }
}
